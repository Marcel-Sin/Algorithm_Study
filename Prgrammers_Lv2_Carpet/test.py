def solution(brown, yellow):
    ans = []
    ac = 0
    for h in range(1,2497) : 
        if yellow < h : break
        if yellow % h != 0 : continue
        w = yellow // h
        if w < h : continue
        ac = 4+w*2+h*2
        if ac == brown :
            ans = [w+2,h+2]
    return ans
