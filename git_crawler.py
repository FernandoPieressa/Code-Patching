import os
import subprocess
import git

def get_git_revision_hash():
    return subprocess.check_output(['git', 'rev-parse', '--abbrev-ref', 'HEAD']).decode("utf-8").rstrip("\n")

def get_entire_history(cur_branch):
	return subprocess.check_output(['git', 'rev-list', cur_branch, '--first-parent']).decode("utf-8").rstrip("\n").split("\n")

orgs_list = os.listdir("Repos")
for org in orgs_list:
	projects_list = os.listdir("Repos/" + org)
	for project in projects_list:
		print(project)
		dir_path = "Repos/" + org + "/" + project
		os.chdir(dir_path)
		cur_branch = get_git_revision_hash()
		all_commit_ids = get_entire_history(cur_branch)
		for commit_id in all_commit_ids:
			print('Working on commit ' + commit_id)
			create_output_diff_cmd = 'git diff ' + commit_id + ' > ../../../output.diff'
			process = subprocess.run(create_output_diff_cmd, shell=True, check=True)
			os.chdir('../../../')
			parse_output_diff_cmd = 'python parse_diff.py'
			process = subprocess.run(parse_output_diff_cmd, shell=True, check=True)
			os.chdir(dir_path)
	os.chdir('../')
