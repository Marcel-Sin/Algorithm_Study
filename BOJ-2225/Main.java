import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 201;
	static final int MOD = 1000000000;
	
	
	static int[][] DP = new int[MAX_SIZE][MAX_SIZE];
	static int TOTAL,N,K;
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}

	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk);
		for (int i = 0; i < DP.length; i++) Arrays.fill(DP[i],-1);
		for (int i = 1; i < DP.length; i++) {
			//1개로 i를 만드는 갯수
			DP[i][1] = 1;
			
			//i개로 1을 만드는 경우의수
			DP[1][i] = i;
			
			//0을 i개로 만드는 갯수
			DP[0][i] = 1;
		}
	}

	static int Solve() {
		for (int k = 2; k <= K; k++) {
			int sum = DP[0][k-1] % MOD;
			for (int n = 1; n <= 200; n++) {
				sum += DP[n][k-1];
				sum %= MOD;
				DP[n][k] = sum;
			}
		}
		return DP[N][K];
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