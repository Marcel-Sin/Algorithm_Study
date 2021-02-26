import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 101;
	
	static int N,M;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		map = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			char[] temp =  io.inputStr().toCharArray();
			for (int j = 1; j <= M; j++) {
				map[i][j] = temp[j-1]-'0';
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				map[i][j] += map[i][j-1];
				map[i][j] += map[i-1][j];
				map[i][j] -= map[i-1][j-1];
			}
		}
		
	}

	static long Solve() throws IOException {
		// 제한 범위 (시작은 inclusive , 종료는 exclusive)
		long ret = 0,temp = 0;

		// [Pattern-1] - Row
		for (int a = 1; a <= N - 2; a++) {
			for (int b = a + 1; b <= N - 1; b++) {
				temp = Square(1, 1, a, M);
				temp *= Square(a+1, 1 , b, M);
				temp *= Square(b+1, 1, N, M);
				ret = Max(ret, temp);
			}
		}
		
		// [Pattern-2] - Col
		for (int a = 1; a <= M - 2; a++) {
			for (int b = a + 1; b <= M - 1; b++) {
				temp = Square(1, 1, N, a);
				temp *= Square(1, a + 1, N, b);
				temp *= Square(1, b + 1, N, M);
				ret = Max(ret, temp);
			}
		}

		// [Pattern-3] - small small large
		for (int row = 1; row <= N - 1; row++) {
			for (int col = 1; col <= M - 1; col++) {
				temp = Square(1, 1, row, col);
				temp *= Square(1, col+1 , row, M);
				temp *= Square(row+1, 1, N, M);
				ret = Max(ret, temp);
			}
		}

		// [Pattern-4] - large small small
		for (int row = 1; row <= N - 1; row++) {
			for (int col = 1; col <= M - 1; col++) {
				temp = Square(1, 1, row, M);
				temp *= Square(row+1, 1 , N, col);
				temp *= Square(row+1, col+1, N, M);
				ret = Max(ret, temp);
			}
		}
		
		// [Pattern-5] - large small small 
		for (int col = 1; col <= M - 1; col++) {
			for (int row = 1; row <= N - 1; row++) {
				temp = Square(1, 1, N, col);
				temp *= Square(1, col+1, row, M);
				temp *= Square(row+1, col+1, N, M);
				ret = Max(ret, temp);
			}
		}
		
		// [Pattern-6] - small small large
		for (int col = 1; col <= M - 1; col++) {
			for (int row = 1; row <= N - 1; row++) {
				temp = Square(1, 1, row, col);
				temp *= Square(row+1, 1, N, col);
				temp *= Square(1, col+1, N, M);
				ret = Max(ret, temp);
			}
		}
		return ret;
	}
	private static int square_sum = 0;
	static int Square(int r, int c, int r2, int c2) {
		square_sum = map[r2][c2];
		square_sum -= map[r-1][c2];
		square_sum -= map[r2][c-1];
		square_sum += map[r-1][c-1];		
		return square_sum;
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
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
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