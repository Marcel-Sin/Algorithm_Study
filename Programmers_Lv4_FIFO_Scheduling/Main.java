import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
		int ans = mainObj.solve.solution(12,new int[] {1,2,3,4});
		 System.out.println(ans);
		//Display(ans, ans.length);
	}
	//import java.util.*;
	class Solution {
		int N;
		int[] core;
		public int solution(int n, int[] cores) {
			N = n;
			core = cores;
			int finished_Time = Get_Total_WorkingTime();
			System.out.println(finished_Time);
			return The_Last_Core(finished_Time)+1;
		}
		
		public int Get_Total_WorkingTime() {
			
			int low = N/core.length;
			int high = (10000*N)/core.length + 1;
			int mid=0,work=0,minTime=Integer.MAX_VALUE;
			
			while(low < high) {
				mid = (low+high)/2;
				work = Max_Work(mid);
				//목표 작업량보다 많거나 같다. => 시간을 줄인다.
				if(N <= work) {
					minTime = (mid < minTime) ? mid:minTime;
					high = mid;
				}
				//목표 작업량보다 모자르다. => 시간을 늘려준다.
				else low = mid+1;
			}
			return minTime;
		}
		
		public int The_Last_Core(int finishTime) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < core.length; i++) {
				if(finishTime%core[i] == 0) {
					list.add(i);
				}
			}
			int work = Max_Work(finishTime) - list.size();
			int idx = -1;
			while(work != N) {
				work++;
				idx++;
			}
			return list.get(idx);
		}
		
		public int Max_Work(int time) {
			int ret = core.length;
			for (int i = 0; i < core.length; i++) {
				ret += time/core[i];
			}
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