import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	static void Execute() {
		Main mainObj = new Main();
		int ans = mainObj.solve.solution(new int[][] {{5,3},{3,10},{10,6}});
		System.out.println(ans);
		// Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		int N;
		int[][] DP;
		int[][] matrix;
		
		public int solution(int[][] matrix) {
			DP = new int[matrix.length][matrix.length];
			N = matrix.length;
			this.matrix = matrix;
			for (int i = 0; i < DP.length; i++) Arrays.fill(DP[i], -1); 	
			
			return Solve(0,N-1);
		}
		public int Solve(int s,int e) {
			if(DP[s][e] != -1) return DP[s][e];
			if(s == e) return DP[s][e] = 0;
			DP[s][e] = Integer.MAX_VALUE;
			for (int k = 0; s + k < e; k++) {
				int j = s + k; // 0 0 1 2 0 1 2 2 1 1 2 2
				int value = Solve(s, j) + Solve(j + 1, e) + matrix[s][0] * matrix[j + 1][0] * matrix[e][1];
				DP[s][e] = (value < DP[s][e]) ? value : DP[s][e];
			}
			return DP[s][e];
		}
	}

//	===================== ETC functions for PS =====================
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
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

//-------------IO_Manager--------------
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