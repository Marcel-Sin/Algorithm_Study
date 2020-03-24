import sys

def LanCounting(arr,cut) :
    SUM = 0
    for i in range(0,len(arr)):
        SUM += int(arr[i]/cut)
    return SUM

lan = []
K,N = input().split(' ')
K,N = int(K), int(N)

for i in range(0,K):
    lan.append(int(sys.stdin.readline()))

left = 1;
right = max(lan)
maxLength = 1;

while( left <= right) :
    mid = int((left+right)/2)
    count = LanCounting(lan,mid)
    if(count < N) :
        right = mid-1
    if(count >= N) :
        maxLength = mid 
        left = mid+1

print(maxLength)
