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
		int ans = mainObj.solve.solution(5,5550);
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		ArrayList<Integer>[] possible_Nums = new ArrayList[9];
		final int INF = Integer.MAX_VALUE;
		int[] DP = new int[32001];
		int num;
		public int solution(int N, int number) {
			for(int i = 0; i < possible_Nums.length; i++) possible_Nums[i] = new ArrayList<Integer>();
			Arrays.fill(DP, INF);
			num = N;
			DFS(1);
			if(DP[number] == INF) return -1;
			else return DP[number];
		}
		
		public void DFS(int count) {
			if(count == 9) return;
			int left = count-1, right=count-left;
			int num1,num2;
			int[] result = new int[4];
			while(1 <= left) {
				for(int i = 0; i < possible_Nums[left].size(); i++) {
					for (int j = 0; j < possible_Nums[right].size(); j++) {
						num1 = possible_Nums[left].get(i);
						num2 = possible_Nums[right].get(j);
						result[0] = num1+num2;
						result[1] = num1-num2;
						result[2] = num1*num2;
						result[3] = num1/num2;
						for (int k = 0; k < result.length; k++) {
							if(1 <= result[k] && result[k] <= 32000 && DP[result[k]] == INF) {
								DP[result[k]] = Minimum(count, DP[result[k]]);
								possible_Nums[count].add(result[k]);
							}
						}
					}
				}
				left--;
				right++;
			}
			int appendNumber = 0;
			for (int i = 0; i < count; i++) appendNumber=appendNumber*10+num;
			if(1 <= appendNumber && appendNumber <= 32000 && DP[appendNumber] == INF) {
				DP[appendNumber] = Minimum(count, DP[appendNumber]);
				possible_Nums[count].add(appendNumber);
			}
			DFS(count+1);
		}
		
		int Minimum(int a,int b) {
			return (a < b) ? a:b;
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