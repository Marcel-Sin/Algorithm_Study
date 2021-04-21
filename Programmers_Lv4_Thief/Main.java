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
		int ans = mainObj.solve.solution(new int[] {1,2,3,1,2});
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		int[][] dp = new int[2][1000000];
		final int NOT = 0, CHOOSED = 1;
		
		public int solution(int[] money) {
			int ret = 0;
			//맨앞을 포함하는 경우(-1까지만 실행)
			dp[NOT][0] = 0;
			dp[CHOOSED][0] = money[0];
			for (int i = 1; i < money.length-1; i++) {
				dp[NOT][i] = Maximum(dp[NOT][i-1], dp[CHOOSED][i-1]);
				dp[CHOOSED][i] = dp[NOT][i-1]+money[i];
			}
			ret = Maximum(dp[NOT][money.length-2], dp[CHOOSED][money.length-2]);
			
			//맨앞을 제외하는 경우(끝까지 실행)
			dp[NOT][0] = 0;
			dp[CHOOSED][0] = 0;
			for (int i = 1; i < money.length; i++) {
				dp[NOT][i] = Maximum(dp[NOT][i-1], dp[CHOOSED][i-1]);
				dp[CHOOSED][i] = dp[NOT][i-1]+money[i];
			}
			ret = Maximum(dp[NOT][money.length-1], ret);
			ret = Maximum(dp[CHOOSED][money.length-1], ret);
			
			return ret;
		}
		
		int Maximum(int a, int b) {
			return (a > b) ? a:b;
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