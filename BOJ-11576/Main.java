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

	static int A,B;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException {	
		
		Init();
		Solve();
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		A = nextInt(stk);
		B = nextInt(stk);
		int k = io.inputInt();
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < k; i++) {
			list.add(nextInt(stk));
		}
	}
	
	static int Pow(int a , int b) {
		if(a == 0) return 0;
		if(b == 0) return 1;
		int ret = 1;
		for (int i = 0; i < b; i++) {
			ret *= a;
		}
		return ret;
	}
	static void Solve() {
		int n = 0;
		int p = list.size()-1;
		for (int i = 0; i < list.size(); i++) {
			n += list.get(i)*Pow(A,p--);
		}
		
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while(n >= B) {
			int r = n % B;
			ans.add(r);
			n = n / B;
		}
		if(n != 0) ans.add(n);
		for (int i = ans.size()-1; i >= 0; i--) {
			System.out.print(ans.get(i)+" ");
		}
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