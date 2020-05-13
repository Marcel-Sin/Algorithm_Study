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
		startTime = System.currentTimeMillis();
		QuickSort(A,0,A.length-1);
		endTime = System.currentTimeMillis();
		System.out.println("QuickSort(Recur) Time(ms) : "+(endTime-startTime));

		startTime = System.currentTimeMillis();
		QuickSort_NotRecursive(B);
		endTime = System.currentTimeMillis();
		System.out.println("QuickSort(NotRecur) Time(ms) : "+(endTime-startTime));
		

		startTime = System.currentTimeMillis();
		MergeSort(C,0,C.length-1,TEMP);
		endTime = System.currentTimeMillis();
		System.out.println("MergeSort(Recur) Time(ms) : "+(endTime-startTime));

		startTime = System.currentTimeMillis();
		MergeSort_NotRecursive(D);
		endTime = System.currentTimeMillis();
		System.out.println("MergeSort(NotRecur) Time(ms) : "+(endTime-startTime));
		
	}

	static void Insertion(int[] a, int size, int input) {
		int i = 0;
		while(i < size && a[i] < input) i++;
		for(int j = size; j > i; j--) a[j] = a[j-1];	
		a[i] = input;
	}
	static void InsertionSort(int[] a) {
		int size = a.length;
		for(int i = 0; i < size; i++) {
			Insertion(a,i,a[i]);
		}
	}
	
	static void MergeSort(int[] a, int low, int high, int[] mem) {
		if(high <= low) return;
		
		int mid = (low+high)/2;
		MergeSort(a,low,mid,mem);
		MergeSort(a,mid+1,high,mem);
		
		int i = low, j = mid+1;
		for(int k = low; k <= high; k++) {
			if(i > mid) mem[k] = a[j++];
			else if(j > high) mem[k] = a[i++];
			else if(a[i] > a[j]) mem[k] = a[j++];
			else mem[k] = a[i++];
		}
		
		for(i = low; i <= high; i++) a[i] = mem[i];	
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
		if(low >= high) return;
		int part = Partition(a, low, high, (low+high)/2);
		QuickSort(a, low, part-1);
		QuickSort(a, part+1, high);
	}
	static void Swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	static void QuickSort_NotRecursive(int[] a) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(a.length-1);
		stack.push(0);
		int low, high, part;
		int s,e,pivot,P,SI;
		while(stack.isEmpty() == false) {
			low = stack.pop();
			high = stack.pop();
			if(high <= low) continue;
			//Partition
			pivot = (low+high)/2;
			P = a[pivot]; SI = low;
			Swap(a,pivot,high);
			for(int i = low; i < high; i++) {
				if(a[i] <= P) Swap(a,i,SI++);
			}
			Swap(a,SI,high);
			part = SI;
			
			stack.push(part-1);
			stack.push(low);
		
			stack.push(high);
			stack.push(part+1);
		}
	}
	static void MergeSort_NotRecursive(int[] a) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(a.length-1);
		stack.push(0);
		int low,high,mid;
		int i,j;
		while(stack.isEmpty() == false) {
			low = stack.pop();
			high = stack.pop();
			if(low >= high) continue;
			mid = (low+high)/2;
			stack.push(mid);
			stack.push(low);
			
			stack.push(high);
			stack.push(mid+1);
			
			i = low; j = mid+1;
			for(int k = low; j <= high; k++) {
				if(i > mid) TEMP[k] = a[j++];
				else if(high < j) TEMP[k] = a[i++];
				else if(a[i] > a[j]) TEMP[k] = a[j++];
				else TEMP[k] = a[i++];
			}
			
			for(i = low; i <= high; i++) a[i] = TEMP[i];
		}
	}
	
	static void Init() {
		A = new int[10000000];
		for(int i = 0; i < 10000000; i++) A[i] = Random(0,10000000);
		B = A.clone();
		C = A.clone();
		D = A.clone();
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
