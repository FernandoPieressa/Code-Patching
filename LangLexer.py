import sys
from sys import argv
import os
import pygments
from pygments.lexers import get_lexer_by_name
from pygments.token import Comment, Text, String, Name
import fnmatch

# If set to False, treats any string as a generic "str"
lexStrings = False

if len(sys.argv) < 3:
	print("Must pass at least two arguments: language and input directory (plus optional output directory)")
	sys.exit(0)

# Set up directories
enc = sys.getfilesystemencoding()
lang = sys.argv[1]
inDir = sys.argv[2]
outDir = inDir + "-lexed" if len(sys.argv) < 4 else sys.argv[3]
if not os.path.exists(outDir):
	os.mkdir(outDir)

# Acquire lexer from Pygments
lexer = get_lexer_by_name(lang)
exts = lexer.filenames
if lang.lower() == 'plpgsql':
	exts.append('*.sql')
print("Will use lexer %s, lexing files with extensions: %s" % (lexer, exts))

count = 0
for dirpath, dirs, files in os.walk(inDir):
	for f in files:
		if not any([fnmatch.fnmatch(f, ext) for ext in exts]):
			continue
		count += 1
		if count % 1000 == 0:
			print(count)
		outdirpath = dirpath.replace(inDir, outDir, 1)
		inPath = os.path.join(dirpath, f)
		outPath = inPath.replace(inDir, outDir, 1)
		inPath = inPath.encode(enc)
		outPath = outPath.encode(enc)
		#if os.path.exists(outPath):
		#	continue
		os.makedirs(os.path.dirname(outPath), exist_ok=True)
		try:
			with open(inPath, "r", encoding='ascii', errors='ignore') as inFile, open(outPath, "w", encoding='ascii', errors='ignore') as outFile:
				content = inFile.read()
				tokens = pygments.lex(content, lexer)
				inString = False
				for ttype, value in tokens:
					if ttype in String.Doc:
						continue
					#print("%s, %s" % (ttype, value))
					if ttype in String:
						if not lexStrings:
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
		except Exception as e:
			print(e)
			continue