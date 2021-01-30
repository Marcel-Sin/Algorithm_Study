import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 1000000;
	
	static int A,P;
	
	static boolean[] visit = new boolean[MAX];
	static int[] pow = new int[10];
	
	// 1. 9^5 = 59049
	// 2. 사실상 최대치는 잘해봐야 "999999" = 354294 예상,
	// 3. 더불어 문제 자체가 사이클이 반드시 존재해야 풀림을 본다면,
	// => [1,000,000] 정도 check[N] 크기는 충분하다는 것을 추측할 수 있다.
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		A = nextInt(stk);
		P = nextInt(stk);
		for (int i = 0; i < pow.length; i++) {
			pow[i] = (int)Math.pow(i, P);
		}
		Arrays.fill(visit, false);
	}
	
	static int Solve() throws IOException {
		int v = A;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(!visit[v]) {
			visit[v] = true;
			list.add(v);
			v = Calc(v);
		}
		int ret = -1;
		while(list.get(++ret) != v);
		return ret;
	}

	static int Calc(int n) {
		int ret = 0;
		while(n != 0) {
			ret += pow[n%10];
			n /= 10;
		}
		return ret;
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