import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX_SIZE = 1000001;
	
	static int N,M;
	static boolean[] prime = new boolean[MAX_SIZE];
	static int[] pre_result = new int[MAX_SIZE];
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	static void Init() throws IOException{
		Arrays.fill(prime, true);
		prime[1] = false;
		for (int i = 2; i < MAX_SIZE; i++) {
			if(prime[i] == false) continue;
			for (int j = i+i; j < MAX_SIZE; j+=i) {
				prime[j] = false;
			}
		}
		// 100만 이하에 대해, 78,497개의 홀수 소수
		ArrayList<Integer> odd = new ArrayList<Integer>(100001);
		for (int i = 3; i < MAX_SIZE; i+=2) {
			if(prime[i] == true) odd.add(i);
		}
		Arrays.fill(pre_result, -1);
		int size = odd.size();
		int a,b;
		for (int n = 6; n <= 1000000; n += 2) {
			if(pre_result[n] != -1) continue;
			for (int i = 0; i < size; i++) {
				a = odd.get(i);
				b = n - a;
				if(b < 3) break;
				if(prime[b] == true) {
					pre_result[n] = a;
					break;
				}
			}
		}
		
	}
	
	static void Solve() throws IOException{
		int n = 0;
		StringBuilder sb = new StringBuilder();
		while(true) {
			n = io.inputInt();
			if(n == 0) break;
			if(pre_result[n] != -1) {
				sb.append(n);
				sb.append(" = ");
				sb.append(pre_result[n]);
				sb.append(" + ");
				sb.append(n-pre_result[n]);
				sb.append('\n');
			}
			else sb.append("Goldbach's conjecture is wrong.\n");
		}
		System.out.println(sb.toString());
	}


	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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