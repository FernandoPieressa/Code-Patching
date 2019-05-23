import csv
import pandas as pd

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
with open("shuffledatabase.csv", mode="r") as csv_file, open("trainprefile.txt", mode='w') as trainpreFile, open("trainpostfile.txt", mode='w') as trainpostFile, open("testprefile.txt", mode='w') as testpreFile, open("testpostfile.txt", mode='w') as testpostFile:
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
                if len(pre) > temp and len(post) > temp:
                    if current < total_train:
                        trainpreFile.write(pre[temp])
                        trainpostFile.write(post[temp])
                    else:
                        testpreFile.write(pre[temp])
                        testpostFile.write(post[temp])
        current += 1
"""
