import sys
stack = []

def QuickSort(arr) :
    global stack
    stack.append(len(arr)-1)
    stack.append(0)
    
    while(len(stack) != 0) :
        first = stack.pop()
        last = stack.pop()
        pivot = int((first+last)/2)

        if (last - first > 0) :
            P = arr[pivot]
            Swap(arr,pivot,last)
            SI = first
            for i in range(first,last) :
                if(arr[i] <= P) :
                    Swap(arr,i,SI)
                    SI += 1
            Swap(arr,SI,last)
            stack.append(last)
            stack.append(SI+1)
            stack.append(SI-1)
            stack.append(first)

            
def Swap(arr,i,j) :
    arr[i],arr[j] = arr[j],arr[i]   


