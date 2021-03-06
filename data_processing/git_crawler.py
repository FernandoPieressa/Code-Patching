import os
import sys
import re
import subprocess
import csv
import shutil
from LangLexer import lex_file

def get_git_revision_hash(path):
	return subprocess.check_output(['git', '-C', path, 'rev-parse', '--abbrev-ref', 'HEAD']).decode("utf-8").rstrip("\n")

def get_entire_history(path, curr_branch):
	return subprocess.check_output(['git', '-C', path, 'rev-list', curr_branch, '--first-parent']).decode("utf-8").rstrip("\n").split("\n")

# Compute diff of commit ID, relative to previous commit if one exists
def get_diff(path, commit_id, out_file, relative_to_parent=True):
	if relative_to_parent: return subprocess.call(['git', '-C', path, 'diff', commit_id + '^1', commit_id, '-U0'], stdout=out_file)
	else: return subprocess.call(['git', '-C', path, 'diff', commit_id, '-U0'], stdout=out_file)

def get_precommit_file(path, commit_id, file, out_file):
	return subprocess.call(['git', '-C', path, 'show', commit_id + '~1:' + file], stdout=out_file)

def get_postcommit_file(path, commit_id, file, out_file):
	return subprocess.call(['git', '-C', path, 'show', commit_id + ':' + file], stdout=out_file)

def check_git_commit_message(path, hash):
	message = subprocess.check_output(['git', '-C', path, 'show-branch', '--no-name', hash]).decode("utf-8").rstrip("\n")
	if any(s in message for s in ["fix", "bug", "typo", "error", "mistake", "fault", "defect", "flaw", "incorrect"]):
		return message
	return None

def parse_line_indices(text):
	text = text.strip()
	if text.startswith("+") or text.startswith("-"): text = text[1:] # Remove + or -
	if "," in text:
		s = text.split(",")
		start = int(s[0])
		return start, start + int(s[1]) - 1
	else: return int(text), int(text)

class Diff():
	def __init__(self, org, project, commit_id):
		self.org = org
		self.project = project
		self.commit_id = commit_id
		self.files_changed = {}

	def add_changed_file(self, file):
		if file in self.files_changed:
			print("Changed file already recorded for commit?", file)
			return
		self.files_changed[file] = []

	def add_changed_lines(self, file, rm_start, rm_end, add_start, add_end):
		if file not in self.files_changed:
			print("Changed file not recorded for commit?", file)
			return
		self.files_changed[file].append((rm_start, rm_end, add_start, add_end))

def parse(diff, file_name, dir_path):
	valid_diffs = {}
	with open(file_name, "r", encoding="utf8", errors='ignore') as file:
		curr_file = curr_line = None # We'll use these variables to confirm we are in a valid diff
		offset_r = 0 # To track the shift in line alignment
		for line in file:
			# First, if this line describes the files changed, assert that the extensions are appropriate (in our case, Java)
			if line.startswith("diff --git"):
				start, end = [m.span() for m in re.finditer(" a/.+ b/", line)][0]
				changed_file = line[start:end][3:-3].strip()
				diff.add_changed_file(changed_file) # Store the changed file for bookkeeping, even if it is not valid
				if changed_file.endswith(".java"):
					curr_file = changed_file
					offset_r = 0
				else: curr_file = None
			if curr_file == None: continue # Don't bother parsing if we are not in a valid file

			# Next, check that the exact line diff is valid. In our case, this means making sure that we can easily infer alignment (e.g. same line removed and added)
			if line.startswith("@@"):
				parts = line.rstrip().split(" ")
				if parts[1].startswith('+') or parts[2] == '@@': continue # Skip changes with no additions or deletions
				rm_start, rm_end = parse_line_indices(parts[1])
				add_start, add_end = parse_line_indices(parts[2])
				# To ensure we are capturing alignment, shift line numbers in new file, first in comparison, end then to store new offset
				if rm_end < (add_start + offset_r) or (add_end + offset_r) < rm_start:
					return # If we find a violation in alignment, exit immediately
				offset_r += (rm_end - rm_start) - (add_end - add_start) # Captures degree to which new file is "ahead" (or behind)
				diff.add_changed_lines(curr_file, rm_start, rm_end, add_start, add_end)

	java_files_changed = len([f for f in diff.files_changed if f.endswith(".java")])
	if java_files_changed == 0: return

	# Append results to data file
	with open(out_file, 'a', encoding='utf8', newline='') as csv_file:
		writer = csv.writer(csv_file, delimiter=',')
		for file in diff.files_changed:
			for (rm_start, rm_end, add_start, add_end) in diff.files_changed[file]:
				writer.writerow([diff.org, diff.project, diff.commit_id, len(diff.files_changed), java_files_changed, file, len(diff.files_changed[file]), rm_end - rm_start + 1, add_end - add_start + 1, rm_start, rm_end, add_start, add_end])
			file_name = diff.org + '_' + diff.project + '_' + diff.commit_id + '_' + str(file[:-5]).replace("/","") + "_pre.java"
			file_name2 = diff.org + '_' + diff.project + '_' + diff.commit_id + '_' + str(file[:-5]).replace("/","") + "_post.java"
			with open("files/"+file_name, "w", encoding="utf8") as of:
				get_precommit_file(dir_path, diff.commit_id, file, of)
				if not os.path.exists("lexedfiles"):
					os.mkdir("lexedfiles")
				lex_file("java", file_name, "files", "lexedfiles")
			with open("filespost/"+file_name2, "w", encoding="utf8") as of:
				if not os.path.exists("lexedfilespost"):
					os.mkdir("lexedfilespost")
				get_postcommit_file(dir_path, diff.commit_id, file, of)
				lex_file("java", file_name2, "filespost", "lexedfilespost")

def main(in_dir, out_file):
	orgs_list = os.listdir(in_dir)
	with open(out_file, 'w', encoding='utf8', newline='') as csv_file:
		writer = csv.writer(csv_file, delimiter=',')
		writer.writerow(['organization', 'project', 'commit', 'files_changed', 'java_files_changed', 'file', 'file_diffs', 'lines_removed', 'lines_added', 'line_rm_start', 'line_rm_end', 'line_add_start', 'line_add_end']) # Empty the output file safe for the header; we will append to it for every project
	for org in orgs_list:
		projects_list = os.listdir(os.path.join(in_dir, org))
		for project in projects_list:
			print("Processing {0}/{1}".format(org, project))
			dir_path = os.path.join(in_dir, org, project)
			curr_branch = get_git_revision_hash(dir_path)
			all_commit_ids = get_entire_history(dir_path, curr_branch)
			for ix, commit_id in enumerate(all_commit_ids):
				fixline = check_git_commit_message(dir_path, commit_id)
				if fixline:
					with open("output.diff", "w", encoding="utf8") as of:
						is_last = ix == len(all_commit_ids) - 1
						get_diff(dir_path, commit_id, of, relative_to_parent=not is_last)
					try:
						parse(Diff(org, project, commit_id), 'output.diff', dir_path)
					except Exception as e:
						print("Exception parsing diff", org, project, commit_id, "--", e)

	with open(out_file) as csv_file, open('postlinelexed.csv', mode='w') as outFile:
		writer = csv.writer(outFile, delimiter=',', quotechar='"')
		writer.writerow(['hash', 'line'])
		csv_reader = csv.reader(csv_file, delimiter=',')
		next(csv_reader)
		for row in csv_reader:
			dir_path = os.path.join(in_dir, row[0], row[1])
			fixline = check_git_commit_message(dir_path, row[2])
			if fixline:
				result = [row[2]]
				with open("lexedfilespost/"+row[0]+"_"+row[1]+"_"+row[2]+"_"+row[5][:-5].replace("/","")+"_post.java", "r", encoding='ascii', errors='ignore') as inFile:
					newline = ""
					for j, line in enumerate(inFile):
						temp1 = int(row[11])
						temp2 = int(row[12])
						if temp1-1 <= j <= temp2-1:
							newline += line+"\n"
					result.append(newline+"\n")
				writer.writerow(result)

if __name__ == '__main__':
	if not os.path.exists("files"):
		os.mkdir("files")
	if not os.path.exists("filespost"):
		os.mkdir("filespost")
	in_dir = sys.argv[1] if len(sys.argv) > 1 else 'Repos'
	out_file = sys.argv[2] if len(sys.argv) > 2 else  'database.csv'
	main(in_dir, out_file)
