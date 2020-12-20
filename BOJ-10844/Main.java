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
	static final int MAX_SIZE = 100;
	static final int MOD = 1000000000;
	
	static long[][] DP = new long[MAX_SIZE][10];
	static long[] SUM = new long[MAX_SIZE];
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		N = io.inputInt();
		for (int i = 0; i < DP.length; i++) Arrays.fill(DP[i], 0);	
		Arrays.fill(SUM,-1);
		
		//Init Value
		SUM[0] = 9;
		for (int i = 1; i < 10; i++) DP[0][i] = 1; 
		
		
		System.out.println(Solve(N-1));
	}
	
	static long Solve(int n) {
		if(SUM[n] != -1) return SUM[n];

		Solve(n-1);
		DP[n][0] = DP[n-1][1] % MOD;
		for (int i = 1; i <= 8; i++) DP[n][i] = (DP[n-1][i-1]+DP[n-1][i+1])% MOD;
		DP[n][9] = DP[n-1][8] % MOD;
			
		SUM[n] = 0;
		for (int i = 0; i <= 9; i++) SUM[n] += DP[n][i];
		SUM[n] = SUM[n] % MOD;
		return SUM[n];
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