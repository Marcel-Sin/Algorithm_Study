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
		MergeSort(A,0,A.length-1);
		endTime = System.currentTimeMillis();
		System.out.println("Recursive MergeSort Time(ms) :" + (endTime-startTime));
		
		System.out.println();
		
		startTime = System.currentTimeMillis();
		MergeSort_NotRecursive(B);
		endTime = System.currentTimeMillis();
		System.out.println("Not-Recursive MergeSort Time(ms) :" + (endTime-startTime));
		
		for(int i = 999900; i < 1000000; i++) System.out.print(A[i]+" ");
		System.out.println();
		for(int i = 999900; i < 1000000; i++) System.out.print(B[i]+" ");
	}

	
	static void MergeSort_NotRecursive(int[] a) {
		int low = 0, size = a.length, high = size,mid,div=2;
		while(true) {
			if(div > size) break;
			int loop = size-div;
			for(int i = 0; i <= loop; i+=div) {
				Merge(a,i,i+div-1,div);
			}
			int rest = size%div;
			if(rest != 0) {
				Merge(a,size-rest,size-1,div);
			}
			div *= 2;
		}	
		Merge(a,0,size-1,div);

	}

	static void Merge(int a[], int start, int end, int div) {
		int mid = start+(div/2)-1;
		int i = start, j = mid+1;
		for(int k = start; k <= end; k++) {
			if(mid < i) TEMP[k] = a[j++];
			else if(end < j) TEMP[k] = a[i++];
			else if(a[i] < a[j]) TEMP[k] = a[i++];
			else TEMP[k] = a[j++];
		}
		for(i = start; i <= end; i++) a[i] = TEMP[i];
	}
	
	static void MergeSort(int a[], int start, int end) {
		if (end <= start) return;
		
		int mid = (start+end)/2;
		
		MergeSort(a, start, mid);
		MergeSort(a, mid+1,end);
		
		int i = start, j = mid+1;
		for(int k = start; k <= end; k++) {
			if (mid < i) TEMP[k] = a[j++];
			else if (end < j) TEMP[k] = a[i++];
			else if (a[i] < a[j]) TEMP[k] = a[i++];
			else TEMP[k] = a[j++];
		}
		
		for(int k = start; k <= end; k++) a[k] = TEMP[k];
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
