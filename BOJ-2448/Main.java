import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 1024*3;
	
	static int N;
	static char[][] matrix = new char[MAX][MAX];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve(0,N-1,N);
		for (int i = 0; i < N; i++) {sb.append(matrix[i]); sb.append('\n');}
		System.out.println(sb.toString());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			matrix[i] = new char[N*2];
			Arrays.fill(matrix[i],' ');
		}
	}
	
	static void Solve(int R,int C, int n) throws IOException{
		// (기본) 기준점을 상단 Row, 중심 Col 기준으로 잡고 재귀한다.
		// (재귀종료) offset이 1이 되었을 시, matrix로 반환한다. 
		int offset = n / 2;
		if(offset > 1) {
			Solve(R,C,offset);
			Solve(R+offset,C-offset,offset);
			Solve(R+offset,C+offset,offset);
		}
		else {
			matrix[R][C] = '*';
			matrix[R+1][C-1] = matrix[R+1][C+1] = '*';
			matrix[R+2][C-2] = matrix[R+2][C-1] = matrix[R+2][C] = matrix[R+2][C+1] = matrix[R+2][C+2] = '*';
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