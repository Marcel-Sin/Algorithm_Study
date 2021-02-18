import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100001;

	static int N;
	static char[] problem;
	static char[] memory = new char[MAX];
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		int data = 0;
		int i = 0;
		problem = io.inputStr().toCharArray();
		N = problem.length;
	}
	static void Solve() throws IOException{
		Char_MergeSort_Reverse(problem,0,N-1);
		int ac = 0;
		boolean check = false;
		for (int i = 0; i < N; i++) {
			ac += problem[i] - '0';
			if(problem[i] == '0') {check = true; break; }
		}
		if(ac%3 == 0 && check) System.out.println(new String(problem));
		else System.out.println(-1);
	}
	//자바 래퍼클래스의 오토 박싱으로 느려짐. 직접 원시타입용 구현해야 ...
	static void Char_MergeSort_Reverse(char[] arr,int low, int high) {
		if(high <= low) return;
		
		int mid = (low+high)/2;
		Char_MergeSort_Reverse(arr,low,mid);
		Char_MergeSort_Reverse(arr,mid+1,high);
		
		int l = low, h = mid+1;
		for (int k = low; k <= high; k++) {
			if(mid < l) memory[k] = arr[h++];
			else if(high < h) memory[k] = arr[l++];
			else if( arr[l] > arr[h]) memory[k] = arr[l++];
			else memory[k] = arr[h++];
		}
		
		for (int i = low; i <= high; i++) problem[i] = memory[i];
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
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
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
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ",arr[i][j]);
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