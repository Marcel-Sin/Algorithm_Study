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
	static final int MAX_SIZE = 5001;
	static final int MOD = 1000000;
	
	static int[] MAP = new int[MAX_SIZE];	
	static int[] DP = new int[MAX_SIZE];
	static int TOTAL,N;
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve(N));
	}

	static void Init() throws IOException{
		Arrays.fill(DP,-1);
		Arrays.fill(MAP,-1);
		
		char[] temp = io.inputStr().toCharArray();
		for (int i = 0; i < temp.length; i++) MAP[i+1] = temp[i]-'0';
		N = temp.length;
		MAP[0] = 3;
		DP[0] = 1;
		DP[1] = 1;
		
	}

	static int Solve(int n) {
		//맨 앞자리가 0인 경우 무조건 false
		if(MAP[1] == 0) return 0;
		
		for(int i = 2; i <= n; i++) {
			int pre = MAP[i-1];
			int cur = MAP[i];
			int value = pre*10+cur;
			// 현재 읽는 위치가 0
			if(cur == 0) {
				if(value != 10 && value != 20) return 0;
				else DP[i] = DP[i-2] % MOD;
			}
			// 현재 읽는 위치가 1 ~ 9
			else {
				if(value == 10 || value == 20) DP[i] = DP[i-2] % MOD;
				else if(value < 10) DP[i] = DP[i-1] % MOD;
				else if(value <= 26) DP[i] = (DP[i-1]+DP[i-2]) % MOD;
				else DP[i] = DP[i-1] % MOD;
			}
		}
		return DP[n];
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