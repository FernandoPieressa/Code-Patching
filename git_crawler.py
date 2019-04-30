import os
import sys
import subprocess
import csv

def get_git_revision_hash(path):
    return subprocess.check_output(['git', '-C', path, 'rev-parse', '--abbrev-ref', 'HEAD']).decode("utf-8").rstrip("\n")

def get_entire_history(path, curr_branch):
	return subprocess.check_output(['git', '-C', path, 'rev-list', curr_branch, '--first-parent']).decode("utf-8").rstrip("\n").split("\n")

def get_diff(path, commit_id, out_file):
	return subprocess.call(['git', '-C', path, 'diff', commit_id + '~', commit_id, '-U0'], stdout=out_file)

def parse_line_indices(text):
	text = text.strip()
	if text.startswith("+") or text.startswith("-"): text = text[1:] # Remove + or -
	if "," in text:
		s = text.split(",")
		return int(s[0]), int(s[1])
	else: return int(text), int(text)

def parse(org, project, commit_id, file_name):
	valid_diffs = {}
	with open(file_name, "r", encoding="utf8", errors='ignore') as file:
		curr_file = curr_line = None # We'll use these variables to confirm we are in a valid diff
		for line in file:
			parts = line.rstrip().split(" ")
			
			# First, if this line describes the files changed, assert that the extensions are appropriate (in our case, Java)
			if line.startswith("diff --git"):
				if ".java" in parts[2] and ".java" in parts[3]: # Check if we can easily infer alignment (e.g. same line removed and added)
					curr_file = parts[2][2:] # Remove the "a/" part. Should check that this is always sound
				else: curr_file = None
			
			# Next, check that the exact line diff is valid. In our case, this means making sure that we can easily infer alignment (e.g. same line removed and added)
			elif line.startswith("@@"):
				if parts[1].startswith('+') or parts[2] == '@@': continue # Skip changes with no additions or deletions
				rm_start, rm_end = parse_line_indices(parts[1])
				add_start, add_end = parse_line_indices(parts[2])
				if rm_start != rm_end or rm_start != add_start or rm_end != add_end: # For now, skip any case where the diff is unequal
					curr_line = None
				else: curr_line = add_start
			
			# Finally, if this is a line added (and provided we are in a valid file and line), store fix text.
			elif line.startswith("+") and not line.startswith("+++"):
				if curr_file == None or curr_line == None: continue
				content = line[1:].lstrip()
				if content.startswith("*") or content.startswith("//") or content.startswith("/*"): continue # Sanity check; not conclusive, but helps remove some comments right-away
				fix_line = line.rstrip()[1:]
				if curr_file not in valid_diffs: valid_diffs[curr_file] = {}
				valid_diffs[curr_file][curr_line] = fix_line
				curr_line += 1
	
	# One more check: tentatively we only consider commits with a single changed permissable file
	if len(valid_diffs) != 1: return
	
	# Append results to data file
	with open(out_file, 'a', encoding='utf8', newline='') as csv_file:
		writer = csv.writer(csv_file, delimiter=',')
		for file in valid_diffs.keys():
			for line_ix, fix in valid_diffs[file].items():
				writer.writerow([org, project, commit_id, file, line_ix, fix])

def main(in_dir, out_file):
	orgs_list = os.listdir(in_dir)
	with open(out_file, 'w', encoding='utf8', newline='') as csv_file:
		writer = csv.writer(csv_file, delimiter=',')
		writer.writerow(['organization', 'project', 'commit', 'file', 'line_no', 'fix_line']) # Empty the output file safe for the header; we will append to it for every project
	for org in orgs_list:
		projects_list = os.listdir(os.path.join(in_dir, org))
		for project in projects_list:
			print(project)
			dir_path = os.path.join(in_dir, org, project)
			curr_branch = get_git_revision_hash(dir_path)
			all_commit_ids = get_entire_history(dir_path, curr_branch)
			for commit_id in all_commit_ids:
				print('Working on commit ' + commit_id)
				with open("output.diff", "w", encoding="utf8") as of:
					get_diff(dir_path, commit_id, of)
				parse(org, project, commit_id, 'output.diff')


if __name__ == '__main__':
	in_dir = sys.argv[1] if len(sys.argv) > 1 else 'Repos'
	out_file = sys.argv[2] if len(sys.argv) > 2 else  'database.csv'
	main(in_dir, out_file)
