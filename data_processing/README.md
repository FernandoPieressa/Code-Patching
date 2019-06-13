# DataProcessing289G

The detail of each data processing can be find below:

## LangLexer.py

This file contains the function used to lex a code file. It removes all code that doesn't help with the model training e.g. one-line and multi-line tokens.
It uses pygments to detect and remove tokens.

The function parameters are:

- *language*: Represents the language the code is written in e.g. java, python, etc...
- *file*: Represent the name of the file to lex.
- *dir1*: Represents the path of the file to lex.
- *dir2*: Represents the path of the output lexed file.

## dataparser.py

Given a shuffled database between commits of the different repositories, "shuffledatabase.csv" containing the following format:

```
organization,project,commit,files_changed,java_files_changed,file,file_diffs,lines_removed,lines_added,line_rm_start,line_rm_end,line_add_start,line_add_end
```

It saves 15% of the files in test and 85% in training.

Its possible to change 56,57,59,60 to remove/add more context to the line prior and after change, by selecting which lines after the line of the change (variable temp) are saved on the files.

As output it creates 4 files: "train.enc", "train.dec", "test.enc", "test.dec".

## git_crawler.py

Given a file containing all commits with changes, and a Folder "Repos" containing the folders of every single single repo, it crawls the commits of the changes and generates the files prior and after the line change. It also filters to create this files for only those commits that are 1 line change and contain one of the commit message keywords. To run this file the command is:

```
python git_crawler.py <repo_folder> <database.csv>
```
