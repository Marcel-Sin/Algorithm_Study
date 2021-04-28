import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
		long ans = mainObj.solve.solution(5, new int[][] { { 4, 3 }, { 4, 2 }, { 3, 2 }, { 1, 2 }, { 2, 5 } });
		// Display(ans, ans.length);
		System.out.println(ans);
	}

	// import java.util.*;
	class Solution {
		int N,there;
		Fighter[] fighter;
		boolean[] visited;

		public int solution(int n, int[][] results) {
			// 초기화
			N = n;
			fighter = new Fighter[n+1];
			visited = new boolean[n+1];

			for (int i = 1; i < n + 1; i++) fighter[i] = new Fighter();			
			
			//연결 형성
			for (int i = 0; i < results.length; i++) {
				int winner = results[i][0];
				int loser = results[i][1];
				fighter[winner].winList.add(loser);
				fighter[loser].loseList.add(winner);
			}
			
			for (int i = 1; i < n+1; i++) {
				Arrays.fill(visited,false);
				Win_Update(i);
				Arrays.fill(visited,false);
				Lose_Update(i);
			}
			int ret = 0;
			for (int i = 1; i < n+1; i++) {
				if(fighter[i].win+fighter[i].lose+1 == n) ret++;
			}
			return ret;
		}
		
		void Win_Update(int here) {
			visited[here] = true;
			for (int i = 0; i < fighter[here].loseList.size(); i++) {
				there = fighter[here].loseList.get(i);
				if(visited[there] == false) {
					fighter[there].win += 1;
					Win_Update(there);
				}
			}
		}
		void Lose_Update(int here) {
			visited[here] = true;
			for (int i = 0; i < fighter[here].winList.size(); i++) {
				there = fighter[here].winList.get(i);
				if(visited[there] == false) {
					fighter[there].lose += 1;
					Lose_Update(there);
				}
			}
		}

		class Fighter {
			public int win,lose;
			public ArrayList<Integer> winList;
			public ArrayList<Integer> loseList;
			public Fighter() {
				super();
				winList = new ArrayList<Integer>();
				loseList = new ArrayList<Integer>();
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