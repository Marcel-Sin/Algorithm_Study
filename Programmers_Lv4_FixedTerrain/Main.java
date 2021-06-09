import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		long ans = mainObj.solve.solution(new int[][] {{4, 4, 3}, {3, 2, 2}, { 2, 1, 0 }},5,3	);
		System.out.println(ans);
		// Display(ans, ans.length);
	}

	public class Solution {
		int[][] LAND;
		int P,Q;
		public long solution(int[][] land, int P, int Q) {
			this.LAND = land;
			this.P = P;
			this.Q = Q;
			
			long ans = Long.MAX_VALUE;
			long low = 0, high = Max_Block(land)+1,mid;
			long up,down;
			while(low < high) {
				mid = (low+high)/2;
				up = Calc(mid+1);
				down = Calc(mid);
				ans = (ans < up) ? ans:up;
				ans = (ans < down) ? ans:down;
				
				if(down <= up) {
					high = mid;
				}
				else low = mid+1;
			}
			return ans;
		}
		public int Max_Block(int[][] arr) {
			int ret = 0;
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					ret = (ret < arr[i][j])? arr[i][j]:ret;
				}
			}
			return ret;
		}
		public long Calc(long height) {
			long ret = 0;
			for (int i = 0; i < LAND.length; i++) {
				for (int j = 0; j < LAND.length; j++) {
					if(LAND[i][j] == height) continue;
					else if(LAND[i][j] < height) ret += (height-LAND[i][j])*P;
					else ret += (LAND[i][j]-height)*Q;
				}
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