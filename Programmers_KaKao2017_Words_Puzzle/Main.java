import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
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
		int ans = mainObj.solve.solution(new String[] { "app", "ap", "p", "l", "e", "ple", "pp" }, "apple");
		System.out.println(ans);
		// Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		HashMap<Integer, Integer> DP_5Bit = new HashMap<Integer, Integer>();
		String[] words;
		int[] DP = new int[20000 + 1];
		char[] target;
		final int INF = Integer.MAX_VALUE / 4;

		public int solution(String[] strs, String t) {
			for (int i = 0; i < strs.length; i++) {
				int hashValue = Hashing_5Bit(strs[i]);
				DP_5Bit.put(hashValue, 1);
			}
			target = t.toCharArray();
			DP[0] = 0;
			int len = 1;
			while (len <= target.length) {
				DP[len] = INF;
				int start = (0 < len - 5) ? len - 5 : 0;
				for (int i = start; i < len; i++) {
					String sub = t.substring(i, len);
					int hash = Hashing_5Bit(sub);
					int value = DP_5Bit.getOrDefault(hash, INF) + DP[i];
					DP[len] = (value < DP[len]) ? value : DP[len];
				}
				len++;
			}
			if (DP[target.length] == INF)
				return -1;
			else
				return DP[target.length];
		}
		public int Hashing_5Bit(String str) {
			int ret = 0;
			char[] arr = str.toCharArray();
			for (int i = 0; i < str.length(); i++) {
				ret = ret << 5;
				int c = arr[i] - 'a' + 1;
				ret += c;
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