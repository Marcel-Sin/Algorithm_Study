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
	static final int MAX_SIZE = 10001;

	//[N][0] : 현시점 포도주 선택 안할시 최대값,
	//[N][1] : 현시점을 첫번째 포도주로 선택 시,
	//[N][2] : 현시점을 두번째 포도주로 선택 시,
	static int[] MAP = new int[MAX_SIZE];
	static int[][] DP = new int[MAX_SIZE][3];
	static int TOTAL,N;
	
	
	public static void main(String[] args) throws IOException {


		Init();
		Solve(N);
		System.out.println(Max(Max(DP[N][0],DP[N][1]),DP[N][2]));
	
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i <= N; i++) {
			MAP[i] = -1;
			DP[i][0] = -1;
			DP[i][1] = -1;
			DP[i][2] = -1;
		}
		MAP[0] = 0;
		for (int i = 1; i <= N; i++) MAP[i] = io.inputInt();
		
		DP[0][0] = DP[0][1] = DP[0][2] = 0;
		
		DP[1][0] = 0;
		DP[1][1] = MAP[1];
		DP[1][2] = MAP[1];
		
	}
	
	static void Solve(int n) {
		if(n == 1) return;
		Solve(n-1);
		
		DP[n][0] = Max(Max(DP[n-1][0],DP[n-1][1]),DP[n-1][2]);
		DP[n][1] = DP[n-1][0]+MAP[n];
		DP[n][2] = DP[n-1][1]+MAP[n];
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