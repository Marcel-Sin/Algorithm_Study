import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static int TOTAL,N,ANS;
	static int[] PROBLEM = new int[50000];
	static int[] TEMP = new int[50000];
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve());
		}
	}
	static void Init() throws IOException{
		N = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; stk.hasMoreTokens(); i++) PROBLEM[i] = nextInt(stk);
	}
	
	static int Solve() {
		ANS = 0;
		MergeSort(PROBLEM,TEMP,0,N-1);
		return ANS;
	}
	
	static void MergeSort(int[] arr, int[] temp, int left, int right) {
		// Base Condition
		if(right <= left) return;
		
		// Divide
		int mid = (left+right)/2;
		
		//Conquer
		MergeSort(arr, temp, left, mid);
		MergeSort(arr, temp, mid+1, right);
		
		//Combine
		int a = left, b = mid+1;
		
		for(int i = left; i <= right; i++) {
			if(mid < a) temp[i] = arr[b++];
			else if(right < b) temp[i] = arr[a++];
			else if(arr[a] > arr[b]) {
				ANS += (mid-a+1);
				temp[i] = arr[b++];
			}
			else temp[i] = arr[a++];
		}
		
		for(int i = left; i <= right; i++) {
			arr[i] = temp[i];
		}

	}




	// ===================== functions for PS =====================
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b) ? b : a;
	}
	static long Max(long a, long b) {
		return (a > b) ? a : b;
	}
	static int Min(int a, int b) {
		return (a > b) ? b : a;
	}
	static int Max(int a, int b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
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