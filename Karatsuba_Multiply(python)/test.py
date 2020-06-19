import sys
import time
sys.setrecursionlimit(10**6)     #Because Python recursive default = 1000

def Multiply(a,b) :
    if(len(a) < len(b)) : return Multiply(b,a)
    c = [0 for k in range(len(a)+len(b))]
    for i in range(0,len(a)) :
        for j in range(0,len(b)) :
            c[i+j] += a[i]*b[j]
    Flow_Process(c)
    return c

def Flow_Process(a) :
    a.append(0)
    for i in range(len(a)) :
        n = a[i]
        if (n < 0) :
            borrow = int( ( abs(n)+9 )/10 )
            a[i+1] -= borrow
            a[i] += (borrow*10)
        else :
            if (n < 10) : continue
            carry = int(n/10)
            a[i] -= (carry*10);
            a[i+1] += carry

    for i in range(len(a)-1,-1,-1) :
        if(a[i] != 0) : break
        del a[i]

def Add(a,b,k) :
    size = len(b)+k
    c = list.copy(a)
    loopCount = size-len(a)
    for i in range(0,loopCount) : c.append(0)
    for i in range(len(b)) :
        c[k+i] += b[i]
    Flow_Process(c)
    return c

def Sub(a,b) :
    c = list.copy(a)
    for i in range(len(b)) :
        c[i] -= b[i]
    return c

def Karatsuba(a,b) :
    if(len(a) < len(b)) : return Karatsuba(b,a)
    if(len(a) == 0 or len(b) == 0) : return []
    if(len(a)+len(b) <= 50) : return Multiply(a,b)
    
    midA = int(len(a)/2)
    a0 = a[0:midA]
    a1 = a[midA:]
    midB = min(midA,len(b))
    b0 = b[0:midB]
    b1 = b[midB:]

    z0 = Karatsuba(a0,b0)
    z2 = Karatsuba(a1,b1)

    a0_a1 = Add(a0,a1,0)
    b0_b1 = Add(b0,b1,0)
    z1 = Karatsuba(a0_a1,b0_b1)
    z1 = Sub(z1,z0)
    z1 = Sub(z1,z2)

    ret = []
    ret = Add(ret,z2,midA*2)
    ret = Add(ret,z1,midA)
    ret = Add(ret,z0,0)

    Flow_Process(ret)
    return ret
    
def Display(a) :
    for i in range(len(a)) :
        print(a[i],end = '')
    return



valueA = str(sys.stdin.readline().strip())
valueB = str(sys.stdin.readline().strip())
valueA = valueA[::-1]
valueB = valueB[::-1]
valueA = list(map(int, valueA))
valueB = list(map(int, valueB))

start = time.time()
result = Karatsuba(valueA,valueB)
print("Karatsuba Time : {0}".format(time.time()-start))

start = time.time()
result = Multiply(valueA,valueB)
print("Normal_Multiply Time : {0}".format(time.time()-start))





