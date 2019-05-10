import sys
from sys import argv
import os
import pygments
from pygments.lexers import get_lexer_by_name
from pygments.token import Comment, Text, String, Name
import fnmatch

def lex_file(language, file):
	if not os.path.exists("lexedfiles"):
		os.mkdir("lexedfiles")
	# Acquire lexer from Pygments
	enc = sys.getfilesystemencoding()
	lexer = get_lexer_by_name(language)
	exts = lexer.filenames
	if language.lower() == 'plpgsql':
		exts.append('*.sql')
	with open(file, "r", encoding='ascii', errors='ignore') as inFile, open("lexedfiles/"+file[6:], "w", encoding='ascii', errors='ignore') as outFile:
		content = inFile.read()
		tokens = pygments.lex(content, lexer)
		inString = False
		for ttype, value in tokens:
			if ttype in String.Doc:
				continue
			#print("%s, %s" % (ttype, value))
			if ttype in String:
				if not False:
					if inString:
						continue
					value = '"str"'
				inString = True
			else:
				inString = False
			if value == ' ':
				continue
			elif value == '\n':
				outFile.write("%s" % value)
				continue

			# Explicitly lex import/include paths if present
			if ttype in Name.Namespace:
				parts = value.split(".")
				if len(parts) > 1:
					for ix, p in enumerate(parts):
						if ix < len(parts) - 1:
							outFile.write(p)
							outFile.write("\t.\t")
						else:
							outFile.write(p.replace(";", ""))
							if ";" in p:
								outFile.write("\t;\t")
							else:
								outFile.write("\t")
					continue

			# Do include pre-processor directives, but beware that they may need additional splitting on spaces
			if ttype in Comment.Preproc or ttype in Comment.PreprocFile:
				parts = value.split(" ")
				if len(parts) > 1:
					for p in parts:
						p = p.strip()
						if len(p) > 0:
							outFile.write("%s\t" % p)
					continue
			elif ttype in Comment or ttype in Text:
				continue
			value = value.replace("\t", "\\t").strip()
			if len(value) > 0:
				outFile.write("%s\t" % value)
