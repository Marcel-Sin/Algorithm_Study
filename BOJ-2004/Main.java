import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 2000000000;
	
	static int[] DIV2;
	static int[] DIV5;
	static int N,M;

	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Solve());
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		
		// 5
		int limit = 0;
		long v = 1;
		while(v < MAX) {  v *= 5; limit++; }
		DIV5 = new int[limit];
		DIV5[0] = 1;
		for (int i = 1; i < limit; i++) {
			DIV5[i] = DIV5[i-1]*5;
		}
		
		// 2
		limit = 0;
		v = 1;
		while(v < MAX) {  v *= 2; limit++; }
		DIV2 = new int[limit];
		DIV2[0] = 1;
		for (int i = 1; i < limit; i++) {
			DIV2[i] = DIV2[i-1]*2;
		}
	}
	
	
	static int Solve() throws IOException{
		int n = N;
		int r = N-M;
		int num5 = Num5_Counter(n);
		int num2 = Num2_Counter(n);
	
		num5 -= Num5_Counter(r);
		num2 -= Num2_Counter(r);

		num5 -= Num5_Counter(n-r);
		num2 -= Num2_Counter(n-r);

		return Min(num5,num2);
	}

	static int Num5_Counter(int value) {
		int ret = 0;
		for (int i = 1; i < DIV5.length; i++) {
			if(value < DIV5[i]) break;
			ret += (value/DIV5[i]);
		}
		return ret;
	}
	static int Num2_Counter(int value) {
		int ret = 0;
		for (int i = 1; i < DIV2.length; i++) {
			if(value < DIV2[i]) break;
			ret += (value/DIV2[i]);
		}
		return ret;
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