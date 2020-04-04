import sys
sys.setrecursionlimit(10**6)     #Because Python recursive default = 1000

temp = [0 for i in range(0,500000)]
counter = 0
def MergeSort(A,start,end) :
    global temp,counter
    #Base Condition
    if (start >= end) : return;

    #Divide
    mid = int((start+end)/2)

    #Conquer
    MergeSort(A,start,mid)
    MergeSort(A,mid+1,end)
    
    #Combine
    i = start;
    j = mid+1;
    for k in range(start,end+1,1):
        if (i > mid) :
            temp[k] = A[j];
            j += 1
        elif (j > end) :
            temp[k] = A[i]
            i += 1
        elif (A[i] <= A[j]) :
            temp[k] = A[i]
            i += 1
        else :
            counter += mid-i+1
            temp[k] = A[j]
            j += 1
        k += 1

    #Copy
    for i in range(start,end+1) :
        A[i] = temp[i]

size = int(sys.stdin.readline());
targetlist = list(map(int,sys.stdin.readline().split(' ')))
MergeSort(targetlist,0,len(targetlist)-1)
print(counter)

