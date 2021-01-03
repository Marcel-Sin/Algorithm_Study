import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 100001;
	static final int UP = 0, DOWN = 1;
	

	static int N,TOTAL;
	static int[][] MAP = new int[2][MAX_SIZE];
	static int[][] DP = new int[2][MAX_SIZE];

	
	public static void main(String[] args) throws IOException {
		
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve(N));
		}
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		DP[UP][0] = 0;
		DP[DOWN][0] = 0;

		
		stk = new StringTokenizer(io.inputStr());
		for (int i = 1; i <= N; i++) MAP[UP][i] = nextInt(stk);	
		
		stk = new StringTokenizer(io.inputStr());
		for (int i = 1; i <= N; i++) MAP[DOWN][i] = nextInt(stk);	
		
		DP[UP][1] = MAP[UP][1];
		DP[DOWN][1] = MAP[DOWN][1];
	}
	static int Solve(int n) throws IOException {

		for (int i = 2; i <= n; i++) {
			DP[UP][i] = MAP[UP][i];
			DP[UP][i] += Max(DP[DOWN][i-1],DP[DOWN][i-2]);
			
			DP[DOWN][i] = MAP[DOWN][i];
			DP[DOWN][i] += Max(DP[UP][i-1],DP[UP][i-2]);
		}

		return Max(DP[UP][n],DP[DOWN][n]);
	}

	// ===================== functions for PS =====================
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
	static void Display(int[] arr, int limit) {
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
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