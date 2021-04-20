import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
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
		int ans = mainObj.solve.solution(4,3,new int[][] {{2,2}});
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		int[][] map;
		int[][] dp;
		final int PATH=0,BAN=1;
		final int MOD = 1000000007;
		
		public int solution(int m, int n, int[][] puddles) {
			map = new int[m][n];
			dp = new int[m][n];
			//dp -1로 초기화
			for (int i = 0; i < dp.length; i++) Arrays.fill(dp[i], -1);	
			
			// 장애물 입력
			for (int i = 0; i < puddles.length; i++) map[puddles[i][0]-1][puddles[i][1]-1] = BAN;	
			
			int value = 1;
			// DP 첫가로줄(Row) 경우의수 입력
			for (int i = 0; i < n; i++) {
				if(map[0][i] == BAN) value=0;
				dp[0][i] = value;
			}
			value = 1;
			// DP 첫세로줄(Col) 경우의수 입력
			for (int i = 0; i < m; i++) {
				if(map[i][0] == BAN) value=0;
				dp[i][0]= value;
			}
			return Recursive(m-1,n-1);
		}
		
		int Recursive(int r, int c) {
			if(dp[r][c] != -1) return dp[r][c];
			dp[r][c] = 0;
			if(map[r-1][c] == PATH) dp[r][c] += Recursive(r-1, c);
			if(map[r][c-1] == PATH) dp[r][c] += Recursive(r, c-1);
			dp[r][c] = dp[r][c] % MOD;			
			return dp[r][c];
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
				System.out.printf(arr[i][j]+" ");
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