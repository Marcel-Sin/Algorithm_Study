def solution(n, lost, reserve):

    
    person=[1 for i in range(n)]
    for i in range(len(lost)) :
        person[lost[i]-1]=0

    for i in range(len(reserve)):
        person[reserve[i]-1] += 1


    l = 0
    r = n-1
    while l < r :
        if person[l]==2 :
            if 0 <= l-1 and person[l-1]==0:
                person[l] = 1
                person[l-1] = 1
            elif person[l+1] == 0 :
                person[l] = 1
                person[l+1] = 1
        if person[r] == 2 :        
            if r+1 < n and person[r+1]==0:
                person[r] = 1
                person[r+1] = 1
            elif person[r-1] == 0 :
                person[r] = 1
                person[r-1] = 1
        l+=1
        r-=1
    if l==r and person[l]==2 :  
       if person[l-1]==0:
           person[l] = 1
           person[l-1] = 1
       elif person[l+1] == 0 :
          person[l] = 1
          person[l+1] = 1
    count = 0
    for i in range(n) :
        if person[i] >= 1 :
            count+=1
    return count
