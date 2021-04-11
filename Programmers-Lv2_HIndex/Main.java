import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
		System.out.println(mainObj.solve.solution(new int[] {3, 0, 6, 1, 5}));
	}
	
	//import java.util.*;
	class Solution {
		public int solution(int[] citations) {
			Fenwick fenwick = new Fenwick(1001);
			for (int i = 0; i < citations.length; i++) {
				int cite = citations[i];
				if(cite < 1000) fenwick.Add_Sum(cite, 1);
				else fenwick.Add_Sum(1000, 1);
			}
			int h_Index = 0;
			for (int h = 1; h <= 1000; h++) {
				int counter = fenwick.Prefix_Sum(1000)-fenwick.Prefix_Sum(h-1);
				if(h <= counter) h_Index = h;
			}
			return h_Index;
		}

			
		class Fenwick {
			int[] arr;
			int size;
			public Fenwick(int size) {
				super();
				this.arr = new int[size+1];
				this.size = size+1;
			}
			public void Add_Sum(int idx,int value) {
				idx++;
				while(idx < size) {
					arr[idx] += value;
					idx += idx & -idx;
				}
			}
			public int Prefix_Sum(int idx) {
				idx++;
				int sum = 0;
				while(0 < idx) {
					sum += arr[idx];
					idx -= idx & -idx;
				}
				return sum;
			}
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