import sys
from queue import PriorityQueue
from sys import stdin
sys.setrecursionlimit(100000)

inf=100000000
n,s=list(map(int,stdin.readline().split()))
arr=list(map(int,stdin.readline().split()))
offset=n*100000
count1=[0 for i in range(offset*2+1)]
count2=[0 for i in range(offset*2+1)]

def CountUp(value,count):
  global offset
  count[value+offset] += 1

def GetCount(value,count) :
  global offset
  return count[value+offset]

def dfs1(here,ac,changed,size) :
  global count1
  if(changed == True) :
    CountUp(ac,count1)
  if(here < size) :
    if(ac==inf) :
       dfs1(here+1,inf,False,size)
       dfs1(here+1,arr[here],True,size)
    else : 
       dfs1(here+1,ac,False,size)
       dfs1(here+1,ac+arr[here],True,size)

def dfs2(here,ac,changed,size,slist) :
  global count2
  if(changed == True) :
    CountUp(ac,count2)
    slist.add(ac)
  if(here < size) :
    if(ac==inf) :
       dfs2(here+1,inf,False,size,slist)
       dfs2(here+1,arr[here],True,size,slist)
    else : 
       dfs2(here+1,ac,False,size,slist)
       dfs2(here+1,ac+arr[here],True,size,slist)
    
ans=0
if(10 <= n) :
  mid = int(n/2)
  slist= set()
  dfs1(0,inf,False,mid)
  dfs2(mid,inf,False,n,slist)
  ans += GetCount(s,count1)
  ans += GetCount(s,count2)
  nlist=list(slist)
  for i in range(len(nlist)):
    num=nlist[i]
    a = GetCount(num,count2)
    if(-offset <= s-num <= offset) :
      b = GetCount(s-num,count1)
      ans+= a*b
    
else :
  dfs1(0,inf,False,n)
  ans+=GetCount(s,count1)
print(ans)
