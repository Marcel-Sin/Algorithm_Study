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
		System.out.println(mainObj.solve.solution(new int[] {3, 30, 34, 5, 9}));
	}
	
	//import java.util.*;
	class Solution {
		public String solution(int[] numbers) {
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> list = new ArrayList<Integer>();
			boolean checkAllZero = true;
			for (int i = 0; i < numbers.length; i++) {
				if(numbers[i] != 0) checkAllZero = false;
				list.add(numbers[i]);
			}
			
			Collections.sort(list,new Custom_Comparator());
			for (int i = 0; i < numbers.length; i++) sb.append(list.get(i));
			
			if(checkAllZero == false) return sb.toString();
			else return "0";
		}
		class Custom_Comparator implements Comparator<Integer> {
			
			int[] POW_10 = {1,10,100,1000,10000};
			
			int Get_Int_Length(int value) {
				int ret = 1;
				while((value /= 10) != 0) ret++;
				return ret;
			}
			@Override
			public int compare(Integer o1, Integer o2) {
				int a = o1 * POW_10[Get_Int_Length(o2)]+o2;
				int b = o2 * POW_10[Get_Int_Length(o1)]+o1;
				if(a == b) return 0;
				else return (a > b) ? -1:1;
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