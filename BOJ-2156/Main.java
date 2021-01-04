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
	static final int NOT_CHOICE = 0,FIRST = 1, SECOND = 2;
	

	static int N,TOTAL;
	static int[] MAP = new int[MAX_SIZE];
	static int[] maxSum = new int[MAX_SIZE];
	static int[][] DP = new int[3][MAX_SIZE];
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve(N));
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		for (int i = 1; i <= N; i++) MAP[i] = io.inputInt();	
		
		DP[NOT_CHOICE][0] = DP[FIRST][0] = DP[SECOND][0] = maxSum[0] = 0;
		DP[NOT_CHOICE][1] = 0;
		DP[FIRST][1] = MAP[1];
		DP[SECOND][1] = MAP[1];
		maxSum[1] = MAP[1];
	}
	static int Solve(int n) throws IOException {

		for (int i = 2; i <= n; i++) {
			DP[NOT_CHOICE][i] = maxSum[i-1];
			DP[FIRST][i] = maxSum[i-2]+MAP[i];
			DP[SECOND][i] = DP[1][i-1]+MAP[i];
			maxSum[i] = Max(DP[NOT_CHOICE][i],DP[FIRST][i]);
			maxSum[i] = Max(maxSum[i],DP[SECOND][i]);
		}
		
		return maxSum[n];
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