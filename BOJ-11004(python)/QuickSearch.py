import sys
sys.setrecursionlimit(10**6)

arr = []
def Partition(arr, start, end, pivot) :
    PV = arr[pivot]
    Swap(arr, pivot, end)
    si = start
    for i in range(start,end):
        if (arr[i] <= PV) :
            Swap(arr, i, si)
            si += 1
    Swap(arr, si, end)
    return si

def QuickSearch(arr, start, end, k) :
    pivot = Partition(arr,start,end,QuickPivot(start,end))
    if (pivot == k) :
        print(arr[k])
        return
    elif (start >= end) : return
    elif (k < pivot) : QuickSearch(arr, start, pivot-1, k)
    else : QuickSearch(arr, pivot+1, end, k)

def Swap(arr, a , b) :
    arr[a],arr[b] = arr[b],arr[a]

def QuickPivot(start,end) :
    return int((start+end)/2)


count, k = input().split(' ')
count, k = int(count), int(k)
arr.extend(list(map(int,sys.stdin.readline().split(' '))))

QuickSearch(arr,0, count-1, k-1)
