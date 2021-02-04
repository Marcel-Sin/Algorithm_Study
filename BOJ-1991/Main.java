import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100;
	
	static int N;
	
	static char[] left = new char[100];
	static char[] right = new char[100];
	

	public static void main(String[] args) throws IOException {	
		Init();
		
		MLR('A');
		System.out.println();
		LMR('A');
		System.out.println();
		LRM('A');
	}
	static void MLR(int here) {
		System.out.print((char)here);
		if(left[here] != '.') MLR(left[here]);
		if(right[here] != '.') MLR(right[here]);
	}
	static void LMR(int here) {
		if(left[here] != '.') LMR(left[here]);
		System.out.print((char)here);
		if(right[here] != '.') LMR(right[here]);
	}
	static void LRM(int here) {
		if(left[here] != '.') LRM(left[here]);
		if(right[here] != '.') LRM(right[here]);
		System.out.print((char)here);
	}
	static void Init() throws IOException{
		N = io.inputInt();
		Arrays.fill(left, '.');
		Arrays.fill(right, '.');
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			char c = stk.nextToken().charAt(0);
			if(c < 65) continue;
			left[c] = stk.nextToken().charAt(0);
			right[c] = stk.nextToken().charAt(0);
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
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ",arr[i][j]);
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