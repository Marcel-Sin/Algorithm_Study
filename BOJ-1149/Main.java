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
	static final int MAX_SIZE = 1001;
	static final int R = 0,G = 1,B = 2;
	
	
	static int[][] MAP = new int[3][MAX_SIZE];
	static int[][] DP  = new int[3][MAX_SIZE];
	static int TOTAL,N;
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve(N));
	}

	static void Init() throws IOException{
		N = io.inputInt();
		
		for (int col = 1; col <= N; col++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			MAP[R][col] = nextInt(stk);
			MAP[G][col] = nextInt(stk);
			MAP[B][col] = nextInt(stk);
		}
	}

	static int Solve(int n) {
		DP[R][1] = MAP[R][1];
		DP[G][1] = MAP[G][1];
		DP[B][1] = MAP[B][1];
		
		for (int i = 2; i <= n; i++) {
			DP[R][i] = MAP[R][i] + Min(DP[G][i-1],DP[B][i-1]);
			DP[G][i] = MAP[G][i] + Min(DP[R][i-1],DP[B][i-1]);
			DP[B][i] = MAP[B][i] + Min(DP[R][i-1],DP[G][i-1]);
		}
		return Min(DP[R][n],Min(DP[G][n],DP[B][n]));
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