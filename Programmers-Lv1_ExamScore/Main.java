import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
		int[] ans = mainObj.solve.solution(new int[] {1,3,2,4,2});
		Display(ans, ans.length);
	}
	
	//import java.util.*;
	class Solution {
		public int[] solution(int[] answers) {
			int[][] user_Answer = new int[3][];
			user_Answer[0] = GiveUp_Player(new int[] {1,2,3,4,5},answers.length);
			user_Answer[1] = GiveUp_Player(new int[] {2,1,2,3,2,4,2,5},answers.length);
			user_Answer[2] = GiveUp_Player(new int[] {3,3,1,1,2,2,4,4,5,5},answers.length);
			int[] score = new int[3];
			for (int i = 0; i < answers.length; i++) {
				for (int user = 0; user < user_Answer.length; user++) {
					score[user] += (user_Answer[user][i] == answers[i]) ? 1:0;
				}
			}
			// 최고 득점을 계산한다.
			int max = score[0];
			max = (max < score[1]) ? score[1]:max;
			max = (max < score[2]) ? score[2]:max;
			
			// 해당 득점자를 리스트에 담는다.
			ArrayList<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < score.length; i++) if(max == score[i]) list.add(i+1);
			int[] ret = new int[list.size()];
			for (int i = 0; i < list.size(); i++) ret[i] = list.get(i);
			return ret;
		}
		int[] GiveUp_Player(int[] cycleArr,int size) {
			int[] ret = new int[size];
			for (int i = 0; i < ret.length; i++) ret[i] = cycleArr[i%cycleArr.length];
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