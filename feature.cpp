#include <iostream>
#include <string>
#include <cstdio>
#include <fstream>
#include <map>
#include <cstring>
#include <vector>
#include <cstdlib>
using namespace std;
vector <int> v;
map <string,int>mymap;
map <string,int>finalmap;
int main()
{
	char line[1024];
	FILE *myfile,*file,*f,*f2;
	map <string,int> :: iterator it,it1;
	myfile = fopen("data.txt","r");
	file = fopen("newdata1.txt","w");
	f=fopen("skiplabel.txt","w");
	f2=fopen("finallabel.txt","w");
	int size,i=1,t=0,flag,n,len,linecount;
	if(myfile!=NULL)
	{
		while(fgets(line,1024,myfile)!=NULL)
		{
			char *p=strtok(line," ");
			while (p!=NULL && *p!=10)
			{
				mymap[p]++;
				p=strtok(NULL," ");
			}
		}
		fclose(myfile);
	}
	else
		cout << "unable to open file" << endl;

	size=mymap.size();
	cout << "Number of unique words=" << size << endl;

	it=mymap.begin();
	while(it!=mymap.end())
	{
		if(it->second>=40)
		{
			finalmap[it->first]=i;
			i++;
		}
		it++;
	}
	size=finalmap.size();
	cout << "Number of unique words in final data=" << size << endl;

	myfile = fopen("data.txt","r");
	i=0;
	if(myfile!=NULL)
	{
		while(fgets(line,1024,myfile)!=NULL)
		{
			i++;
			flag=0;
			char *p=strtok(line," ");
			while (p!=NULL && *p!=10)
			{
				it=finalmap.find(p);
				if(it!=finalmap.end())
				{
					flag=1;
					t=it->second;
					fprintf(file,"%d ",t);
				}
				p=strtok(NULL," ");
			}
			if(flag==1)
				fprintf(file, "%s","\n");
			else
			{
				v.push_back(i);
				fprintf(f,"%d\n",i);
			}
		}
	}
	mymap.clear();
	fclose(myfile);
	fclose(file);
	fclose(f);
	myfile=fopen("label.txt","r");
	f=fopen("skiplabel.txt","r");
	i=0;
	t=1;
	n=1;
	len=v.size();
	if(myfile!=NULL)
	{
		while(fgets(line,1024,myfile)!=NULL)
		{
			if(i<len && t==v[i])
			{
				i++;
			}
			else
			{
				char *p=strtok(line,",");
				while (p!=NULL && *p!=10)
				{
					it=mymap.find(p);
					if(it==mymap.end())
					{
						mymap[p]=n;
						n++;
					}
					p=strtok(NULL,",");
				}
			}
			t++;	
		}
	}
	fclose(myfile);
	fclose(f);
	i=0;
	t=1;
	myfile=fopen("label.txt","r");
	if(myfile!=NULL)
	{
		while(fgets(line,1024,myfile)!=NULL)
		{
			if(i<len && t==v[i])
			{
				i++;
			}
			else
			{
				linecount=0;
				char *p=strtok(line,",");
				while (p!=NULL && *p!=10)
				{
					linecount++;
					it=mymap.find(p);
					fprintf(f2, "%d ",it->second);
					p=strtok(NULL,",");
				}
				// while(linecount<5)
				// {
				// 	fprintf(f2,"%s ","0");
				// 	linecount++;
				// }
				fprintf(f2,"%s","\n");
			}
			t++;
		}
	}
	// int array[size+1];
	// memset(array,0,sizeof(array));
	// file=fopen("newdata1.txt","r");
	// myfile=fopen("mainfinaldata.txt","w");
	// if(file!=NULL)
	// {
	// 	while(fgets(line,1024,file)!=NULL)
	// 	{
	// 		char *p=strtok(line," ");
	// 		while (p!=NULL && *p!=10)
	// 		{
	// 			t=atoi(p);
	// 			array[t]=1;
	// 			p=strtok(NULL," ");
	// 		}
	// 		for(i=1;i<=size;i++)
	// 		{
	// 			fprintf(myfile,"%d ", array[i]);
	// 		}
	// 		fprintf(myfile, "%s","\n" );
	// 		memset(array,0,sizeof(array));
	// 	}
	// 	fclose(file);
	// 	fclose(myfile);
	// }
	return 0;
}