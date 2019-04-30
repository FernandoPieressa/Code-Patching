import csv
valid_file_type = 0
valid_commit = 0

precommit_file = open("pre-commit.txt", "w")
postcommit_file = open("post-commit.txt", "w")
before_list = []
after_list = []
def parse(file_name, before_list, after_list):
	with open(file_name) as file:
		start = False
		start2 = False
		before = ''
		after = ''
		for line in file:
			list_line = line.split(" ")
			if ("diff --git" in line):
				valid_commit = 0
				valid_file_type = 0
				if (".java" in list_line[2] and ".java" in list_line[3]):
					valid_file_type = 1

			if (valid_file_type == 1 and "@@" in line):
				if (list_line[1][1:] == list_line[2][1:]):
					valid_commit = 1
				else:
					valid_commit = 0

			if (valid_commit == 1):
				if ("-" in line or "+" in line):
					if ('-' in list_line[0]):
						if start2:
							after_list.append(after[:-2])
							after = ''
						start = True
						start2 = False
						newline = line.rstrip('\n').replace("+",'').replace("* ",'').replace(" *",'').replace("-",'').replace("\x1b[m\n",'').lstrip() + '\n'
						if newline != '\n':
							before += newline
					if ('+' in list_line[0]):
						if start:
							before_list.append(before[:-2])
							before = ''
						start = False
						start2 = True
						newline = line.rstrip('\n').replace("+",'').replace("* ",'').replace("-",'').replace(" *",'').replace("\x1b[m\n",'').lstrip() + '\n'
						if newline != '\n':
							after += line.rstrip('\n').replace("+",'').replace("* ",'').replace("-",'').replace(" *",'').replace("\x1b[m\n",'').lstrip() + '\n'
		after_list.append(after)
		return list(filter(None, before_list)), list(filter(None, after_list))
before_list, after_list = parse('output.diff', before_list, after_list)
with open('database.csv', "w") as csv_file:
	writer = csv.writer(csv_file, delimiter=',')
	writer.writerow(['deletion','addition'])
	for i,j in zip(before_list, after_list):
		writer.writerow([i,j])