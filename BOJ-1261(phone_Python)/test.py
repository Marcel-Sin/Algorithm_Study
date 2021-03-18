import sys
from queue import PriorityQueue
from sys import stdin
sys.setrecursionlimit(100000)

m,n=list(map(int,stdin.readline().split()))
dirs=[[-1,0],[1,0],[0,1],[0,-1]]

tr = n-1
tc = m-1
visited=[[] for i in range(n)]
for i in range(n) :
    visited[i] = [False for j in range(m)]

arr=[]
for i in range(n):
    temp = []
    text=stdin.readline()
    for j in range(m):
        temp.append(ord(text[j])-48)
    arr.append(temp)
    


q = PriorityQueue()
visited[0][0]=True
q.put((0,[0,0]))

##arr=stdin.readline()
ans = -1
while (q.qsize() != 0 and ans == -1) :
    here = q.get()
    for i in range(4):
        nr = here[1][0]+dirs[i][0]
        nc = here[1][1]+dirs[i][1]
        if (0<=nr<n and 0<=nc<m and visited[nr][nc] == False) :
            if(nr == tr and nc == tc):
                ans = here[0]
                break
            else :
                visited[nr][nc]=True
                q.put((here[0]+arr[nr][nc],[nr,nc]))
        
if(n == 1 and m == 1):
    print(0)
else : print(ans)
