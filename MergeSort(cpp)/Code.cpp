#include <iostream>
#include <cstring>
using namespace std;
void MergeSort(int* A, int size);
void MergeSorting(int* A, int start, int end, int* temp);

int main() {
	int a[] = { 0,1,0,0,3,3,2 };
	MergeSort(a,7);
	for (int n : a) cout << n << ' ';
}

void MergeSort(int* A,int size) {
	int* temp = new int[size];
	MergeSorting(A,0,size-1,temp);
	delete[] temp;
}

void MergeSorting(int* A, int start, int end, int* temp) {
	// Base Condition
	if (start >= end) return;

	// Divide
	int mid = (start + end) / 2;

	// Conquer
	MergeSorting(A,start,mid,temp);
	MergeSorting(A, mid+1,end,temp);

	// Combine
	int i = start, j = mid+1;
	for (int k = start; k <= end; k++) {
		if (i > mid) temp[k] = A[j++];
		else if (j > end) temp[k] = A[i++];
		else if (A[i] <= A[j]) temp[k] = A[i++];
		else temp[k] = A[j++];
	}

	// Copy
	for (i = start; i <= end; i++) A[i] = temp[i];
}
