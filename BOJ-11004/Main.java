import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 5000001;
	
	static int[] problem,temp;
	static int N,K;

	
	public static void main(String[] args) throws IOException {

		
		Init();
		MergeSort(problem, 0, N-1);
		System.out.println(problem[K-1]);
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk);
		
		problem = new int[MAX_SIZE];
		temp = new int[MAX_SIZE];
		
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N; i++) problem[i] = nextInt(stk);
		
	}
	
	static void MergeSort(int[] arr,int left,int right) {
		// Base condition
		if(left >= right) return;
		
		// Divide
		int mid = (left+right)/2;
		
		// Conquer
		MergeSort(arr,left,mid);
		MergeSort(arr,mid+1,right);
		
		// Merge
		int i=left, j=mid+1, k = left;
		while(k <= right) {
			if(mid < i) temp[k++] = arr[j++];
			else if(right < j) temp[k++] = arr[i++];
			else if(arr[i] <= arr[j]) temp[k++] = arr[i++];
			else temp[k++] = arr[j++];
		}
		
		// copy
		for (i = left; i <= right; i++) arr[i] = temp[i];
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
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
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