import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static long startTime,endTime;
	static int[] A,B,C,D;
	static int[] TEMP = new int[10000000];
	public static void main(String[] args) throws IOException {
		Init();
		QuickSort(A,0,A.length-1);
		MergeSort(B,0,B.length-1);
		System.out.println(BinarySearch(A,-1));
		System.out.println(BinarySearch(B,-1));
		System.out.println(Upper(A,-1)-Lower(A,-1));
		System.out.println(Upper(B,-1)-Lower(B,-1));
		for(int i = 0; i < 100; i++) System.out.print(A[i]+" ");
		System.out.println();
		for(int i = 0; i < 100; i++) System.out.print(B[i]+" ");
		
		// Until Final Test, Total Time : PM 01:36
	}
	// 퀵소트, 머지소트, 이진검색, Lower, Upper, Fast 구현 1:10 PM ~
	
	static void Swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	static int Partition(int[] a, int low, int high, int pivot) {
		int P = a[pivot], SI = low;
		Swap(a,pivot,high);
		for(int i = low; i < high; i++) {
			if(a[i] < P) Swap(a,i,SI++);
		}
		Swap(a,SI,high);
		return SI;
	}
	static void QuickSort(int[] a, int low, int high) {
		if(low > high) return;
		int mid = Partition(a,low,high,(low+high)/2);
		QuickSort(a,low,mid-1);
		QuickSort(a,mid+1,high);
	}
	// QuickSort Complete 1:14PM  [5분] 
	static void MergeSort(int[] a, int low, int high) {
		if(low >= high) return;
		
		int mid = (low+high)/2;
		
		MergeSort(a,low,mid);
		MergeSort(a,mid+1,high);
		
		int i = low, j = mid+1;
		for(int k = low; k <= high; k++) {
			if(mid < i) TEMP[k] = a[j++];
			else if(high < j) TEMP[k] = a[i++];
			else if(a[i] < a[j]) TEMP[k] = a[i++];
			else TEMP[k] = a[j++];
		}
		
		for(i = low; i <= high; i++) a[i] = TEMP[i];
	}
	// MergeSort Complete 1:18 PM [4분] 
	
	static int BinarySearch(int[] a, int target) {
		int low = 0, high = a.length-1, mid=-1;
		while(low <= high) {
			mid = (low+high)/2;
			if(a[mid] < target) low = mid+1;
			else if(a[mid] > target) high = mid-1;
			else break;
		}
		if(a[mid] == target) return mid;
		else return -1;
	}
	//BinarySearch Complete 1:21 PM [3분]
	
	static int Lower(int[] a, int target) {
		int low = 0, high = a.length, mid = 0;
		while(low < high) {
			mid = (low+high)/2;
			if(a[mid] < target) low = mid + 1;
			else high = mid;
		}
		if(a[high] == target) return high;
		else return -1;
	}
	static int Upper(int[] a, int target) {
		int low = 0, high = a.length, mid = 0;
		while(low < high) {
			mid = (low+high)/2;
			if(a[mid] <= target) low = mid + 1;
			else high = mid;
		}
		if(high == 0 && a[high] == target) return high;		
		else if(high-1 >= 0 &&a[high-1] == target) return high;
		else return -1;
	}
	// Lower And Upper Complete 1:26 PM [5분]
	
	
	
	
	
	
	static void Init() {
		A = new int[10000000];
		for(int i = 0; i < 10000000; i++) A[i] = Random(0,1000000);
		B = A.clone();
	}
	static int Random(int min,int max) {
		int number = (int)(Math.random()*max);
		return number;
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
}


// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
class IO_Manager {
	public BufferedReader br;
	public BufferedWriter bw;

	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public Integer inputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long inputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public String inputStr() throws IOException {
		return br.readLine();
	}
	public void write(String str) throws IOException {
		bw.write(str);
	}
	public void close() throws IOException {
		br.close();
		bw.close();
	}
}
