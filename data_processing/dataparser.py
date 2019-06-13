import csv
import pandas as pd

### Commented code to save consecutive line changes
"""
with open("shuffledatabase.csv", mode="r") as csv_file, open("trainprefile2.txt", mode='w') as trainpreFile, open("trainpostfile2.txt", mode='w') as trainpostFile, open("testprefile2.txt", mode='w') as testpreFile, open("testpostfile2.txt", mode='w') as testpostFile:
    csv_reader = csv.reader(csv_file, delimiter=',')
    total_train = 154165 * 0.85
    current = 0
    for row in csv_reader:
        filename_pre = row[1]+"#"+row[2]+"#"+row[3]+"#"+row[6].replace("/","__")+"#pre.java"
        filename_post = row[1]+"#"+row[2]+"#"+row[3]+"#"+row[6].replace("/","__")+"#post.java"
        with open("files/files-pre/"+filename_pre, mode="r") as preFile, open("files/files-post/"+filename_post, mode="r") as postFile:
            pre = preFile.readlines()
            post = postFile.readlines()
            prestart = int(row[10]) - 1
            preend = int(row[11]) - 1
            poststart = int(row[12]) - 1
            postend = int(row[13]) - 1
            if len(pre) > preend and len(post) > postend:
                rem = ''
                add = ''
                if current < total_train:
                    for i in range(prestart, preend+1):
                        rem += pre[i][:-1] + " SPACE "
                    trainpreFile.write(rem[:-6]+"\n")
                    for i in range(poststart, postend+1):
                        add += post[i][:-1] + " SPACE "
                    trainpostFile.write(add[:-6]+"\n")
                else:
                    for i in range(prestart, preend+1):
                        rem += pre[i][:-1] + " SPACE "
                    testpreFile.write(rem[:-6]+"\n")
                    for i in range(poststart, postend+1):
                        add += post[i][:-1] + " SPACE "
                    testpostFile.write(add[:-6]+"\n")
        current += 1

"""

### Saves 85% as training and the rest as test data. Currently it saves the 2 previous and after lines of code as context
with open("shuffledatabase.csv", mode="r") as csv_file, open("train.enc", mode='w') as trainpreFile, open("train.dec", mode='w') as trainpostFile, open("test.enc", mode='w') as testpreFile, open("test.dec", mode='w') as testpostFile:
    csv_reader = csv.reader(csv_file, delimiter=',')
    total_train = 154165 * 0.85
    current = 0
    for row in csv_reader:
        if int(row[8]) == 1 and int(row[9]) == 1:
            filename_pre = row[1]+"#"+row[2]+"#"+row[3]+"#"+row[6].replace("/","__")+"#pre.java"
            filename_post = row[1]+"#"+row[2]+"#"+row[3]+"#"+row[6].replace("/","__")+"#post.java"
            with open("files/files-pre/"+filename_pre, mode="r") as preFile, open("files/files-post/"+filename_post, mode="r") as postFile:
                pre = preFile.readlines()
                post = postFile.readlines()
                temp = int(row[10]) - 1
                if len(pre) > temp+2 and len(post) > temp+2:
                        if current < total_train:
                            trainpreFile.write(pre[temp-2][:-1] + " " + pre[temp-1][:-1] + " " + pre[temp][:-1] + " " + pre[temp+1][:-1] + " " + pre[temp+2])
                            trainpostFile.write(post[temp-2][:-1] + " " + post[temp-1][:-1] + " " + post[temp][:-1] + " " + post[temp+1][:-1] + " " + post[temp+2])
                        else:
                            testpreFile.write(pre[temp-2][:-1] + " " + pre[temp-1][:-1] + " " + pre[temp][:-1] + " " + pre[temp+1][:-1] + " " + pre[temp+2])
                            testpostFile.write(post[temp-2][:-1] + " " + post[temp-1][:-1] + " " + post[temp][:-1] + " " + post[temp+1][:-1] + " " + post[temp+2])
        current += 1
