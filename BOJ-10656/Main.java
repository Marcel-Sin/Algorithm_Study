import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;

	static int N,M;
	static int[][] MAP = new int[54][54];
	static int[] CHECK = {-1,0,0,0};
	static ArrayList<Pair> list = new ArrayList<Pair>();
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		for (int i = 0; i < MAP.length; i++) Arrays.fill(MAP[i], -1); 
			
		for (int i = 1; i <= N; i++) {
			char[] temp = io.inputStr().toCharArray();
			for (int j = 0; j < temp.length; j++) {
				if(temp[j] == '.') MAP[i][j+1] = 0;
				else MAP[i][j+1] = -1;
			}
		}
		
	}
	static long Solve() throws IOException{
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(Horizontal_Checking(i, j)) {list.add(new Pair(i,j)); continue;}
				else if(Vertical_Checking(i, j)) {list.add(new Pair(i,j)); continue;}
			}
		}
		return 0;
	}

	static boolean Horizontal_Checking(int r, int c) {
		for (int i = 0; i < 4; i++) {
			if(MAP[r][c+i-1] != CHECK[i]) return false;
		}
		return true;
	}
	static boolean Vertical_Checking(int r, int c) {
		for (int i = 0; i < 4; i++) {
			if(MAP[r+i-1][c] != CHECK[i]) return false;
		}
		return true;
	}

	static class Pair {
		public int a,b;

		public Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return a +" "+b;
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