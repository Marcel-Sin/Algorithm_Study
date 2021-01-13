import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;
	
	static int N,K,ANS;
	
	static int[] list = new int[5000000];
	
	public static void main(String[] args) throws IOException {
		Init();
		QuickSearch(list, 0, N-1, K-1);
	}

	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk); K = nextInt(stk);
		ANS = INF;
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N; i++) {
			list[i] = nextInt(stk);
		}
	}

	static int Partition(int[] arr,int left, int right) {
		int pivot = (left+right)/2;
		int temp = arr[left];
		arr[left] = arr[pivot];
		arr[pivot] = temp;
		
		
		int i = left, j = right;
		while(i < j) {
			while(arr[j] > arr[left]) j--;
			while(i < j && arr[i] <= arr[left]) i++;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
		temp = arr[i];
		arr[i] = arr[left];
		arr[left] = temp;
		return i;
	}
	
	static void QuickSearch(int[] arr,int left, int right,int k) {
		if(left > right) return;
		int part = Partition(arr,left,right);
		
		if(part == k) {
			ANS = arr[k];
			System.out.println(ANS);
			return;
		}
		if(k > part) QuickSearch(arr,part+1,right,k);
		else QuickSearch(arr,left,part-1,k);
	}
	



	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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