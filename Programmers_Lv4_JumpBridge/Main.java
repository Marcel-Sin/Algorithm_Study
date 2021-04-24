import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
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
		int ans = mainObj.solve.solution(25,new int[] {7,14,20},2);
		//Display(ans, ans.length);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		int DIST,K;
		int[] rock;
		public int solution(int distance, int[] rocks, int n) {
			DIST=distance;
			rock = rocks;
			K = n;
			Arrays.sort(rock);
			
			int low = 0, high = 1000000001, mid,deleteCount = 0,maxGap=0;
			
			while(low < high) {
				mid = (low+high)/2;
				deleteCount = Delete_RockCount(mid);
				if(deleteCount <= K) {
					System.out.println(deleteCount);
					maxGap = (mid > maxGap) ? mid:maxGap;
					low = mid+1;
				}
				else high = mid;
			}
			
			return maxGap;
		}

		int Delete_RockCount(int gap) {
			int ret = 0;
			int i = 0;
			int prePos = 0;
			// 중간지점
			while(i < rock.length) {
				if(rock[i]-prePos < gap) {
					ret++;
					i++;
				}
				else {
					prePos = rock[i];
					i++;
				}
			}
			if(prePos == rock[i-1] && DIST-rock[i-1] < gap) ret++;
			return ret;
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