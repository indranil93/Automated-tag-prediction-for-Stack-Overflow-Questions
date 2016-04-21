import numpy as np
import sys
import random
import csv
import operator
import math
from numpy import genfromtxt
from sklearn.svm import SVC
from sklearn.linear_model import SGDClassifier
from sklearn.tree import DecisionTreeClassifier

from sklearn.svm import LinearSVC
from sklearn import preprocessing

from sklearn.ensemble import RandomForestClassifier

from sklearn import cross_validation
from sklearn.decomposition import PCA
from sklearn.multiclass import OneVsRestClassifier
import pickle
def pca(data,pc_count):
	return PCA(n_components=pc_count).fit(data).transform(data)

def getDefault(testy):
	deafult_t=[]
	tag_dict={}
	for label_list in testy:
		for label in label_list:
			if label in tag_dict.keys():
				tag_dict[label]+=1
			else:
				tag_dict[label]=1
	pickle.dump(tag_dict,open('tag_dict.p','wb'))
	tag_dict_sorted=sorted(tag_dict.items(),key=operator.itemgetter(1),reverse=True)
	tag_dict_sorted=[tag[0] for tag in tag_dict_sorted]
	#print tag_dict_sorted
	return tag_dict_sorted[:5]



# x=genfromtxt('mainfinaldata.txt',delimiter=' ')
# y=genfromtxt('finallabel.txt',delimiter=' ')					
# print "genfromtxt done"
# print x.shape
# print y.shape
# print y[0]
# #x=pca(x,100)
# #print "pca shape=", x.shape

# #print "pca done"
# clf=SVC()
# clf.fit(x,y)
# print "fit completed"
# scores = cross_validation.cross_val_score(clf,x,y,cv=5)
# print scores
# print scores.mean()

f1=open('mainfinaldata_old.txt','rb')
f2=open('finallabel_old.txt','rb')
lines1=csv.reader(f1,delimiter=" ")
lines2=csv.reader(f2,delimiter=" ")
print "read"
data1=list(lines1)
data2=list(lines2)
print "list made"
#print data1
#print data2
# data1=np.array(data1).astype(int)
# data2=np.array(data2).astype(int)

for i in xrange(len(data1)):
	data1[i]=[data1[i][j] for j in xrange(len(data1[i])-1)]
	data2[i]=[data2[i][j] for j in xrange(len(data2[i])-1)]
# print data1[0]
# print data2[0]
X=[]
Y=[]
for i in range(len(data1)):
	t1=[]
 	for j in range(len(data1[i])):	
 		t1.append(int(data1[i][j]))
 	X.append(t1)
 	t2=[]
 	t2_tup=()
 	for k in range(len(data2[i])):
 		t2.append(int(data2[i][k]))
 	Y.append(t2)
#print X[0]
print Y
x=[]
y=[]
for i in range(10000):
	x.append(X[i])
	y.append(Y[i])
x=np.array(x)
y=np.array(y)
print len(X)
print len(y)
testx=[]
testy=[]
for i in range(16000,17000):
	testx.append(X[i])
	testy.append(Y[i])

default_tags=getDefault(y)
print default_tags
print "np array done"
lb=preprocessing.MultiLabelBinarizer()
Y=lb.fit_transform(y)
# clf = OneVsRestClassifier(SGDClassifier(loss='modified_huber'))
# clf = OneVsRestClassifier(LinearSVC(loss='squared_hinge'))
# clf = OneVsRestClassifier(DecisionTreeClassifier())
clf = OneVsRestClassifier(RandomForestClassifier())
clf.fit(x,Y)
print "fit done"
#print X[0]
#print X
acc=tot=0
print len(testy)
for i in range(len(testy)):
	result=clf.predict(np.array([testx[i]]))
	r=result[0]
	norm_pred=[(0 if x < 0.5 else 1) for x in r]
	result_data=[j+1 for j in xrange(len(norm_pred)) if len(norm_pred)>0 and norm_pred[j]>0 ]
	#print result_data
	if len(result_data)>0:
		tot+=1
	else:
		result_data=default_tags[:]
	if set(result_data).intersection(set(testy[i])):
			acc+=1
	print i
	print result_data, testy[i]

print acc/float(tot),acc,tot,acc/float(len(testy)),len(testy)
print len(testx[0])


# print(clf.predict(np.array(X[3000])))
# print Y[3000]

# print(clf.predict(np.array(X[2500])))
# print Y[2500]