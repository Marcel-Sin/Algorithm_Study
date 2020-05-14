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
		QuickSort(A,0,A.length-1,500000);
		endTime = System.currentTimeMillis();
		System.out.println("QuickSearch Time(ms) : "+(endTime-startTime));

		startTime = System.currentTimeMillis();
		MergeSort_NotRecursive(B);
		System.out.println(BinarySearch(B, 500000));
		endTime = System.currentTimeMillis();
		System.out.println("MergeSort+BinarySearch Time(ms) : "+(endTime-startTime));
	
		
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
	static void QuickSort(int[] a, int low, int high,int target) {
		if(low >= high) return;
		int part = Partition(a, low, high, (low+high)/2);
		if (a[part] == target) {
			System.out.println("Found it! : "+target);
			return;
		}
		QuickSort(a, low, part-1,target);
		QuickSort(a, part+1, high,target);
	}
	static void Swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	static int BinarySearch(int[] a,int target) {
		int s = 0, e = a.length-1,mid=0;
		while(s <= e) {
			mid = (s+e)/2;
			if (a[mid] == target) return mid;
			else if(a[mid] < target) s = mid+1;
			else e = mid-1;
		}
		if(a[mid] == target) return mid;
		else return -1;
	}
	
	
	
	
	
	static void Init() {
		A = new int[10000000];
		for(int i = 0; i < 10000000; i++) A[i] = Random(0,10000000);
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
