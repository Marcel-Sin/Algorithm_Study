#include <string>
#include <algorithm>
#include <vector>
#include <iostream>
int Partition(int* a, int start, int end, int pivot);
int QuickPivot(int start, int end);
void Swap(int* a, int i, int j);
void QuickSearch(int* a, int start, int end,int k);

using namespace std;
const int MAX = 5000000;
int a[MAX + 1];


int main()
{
    int count, k;
    cin.tie(NULL);
    cin.sync_with_stdio(false);

    cin >> count >> k;
    for (int i = 0; i < count; i++) {
        cin >> a[i];
    }
    
    QuickSearch(a, 0, count - 1, k - 1);

    return 0;
}



void Swap(int* a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

int Partition(int* a, int start, int end, int pivot) {
    int PV = a[pivot];
    Swap(a, pivot, end);
    int SI = start;
    for (int i = start; i < end; i++) {
        if (a[i] <= PV) {
            Swap(a, SI, i);
            SI++;
        }
    }
    Swap(a, SI, end);
    return SI;
}

int QuickPivot(int start, int end) {
    return (start + end) / 2;
}

void QuickSearch(int* a,int start,int end,int k) {
    int pivot = Partition(a, start, end, QuickPivot(start, end));
    if (pivot == k) {
        cout << a[pivot];
        return;
    }
    if (end - start <= 0) return;
    else if (k < pivot) QuickSearch(a, start, pivot - 1, k);
    else QuickSearch(a, pivot + 1, end, k);

}


