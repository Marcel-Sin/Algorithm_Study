import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 64;
	
	static int N;
	static char[][] matrix = new char[MAX][MAX];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve(0,0,N);
		System.out.println(sb.toString());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			matrix[i] = io.inputStr().toCharArray();
		}
	}
	
	static void Solve(int R,int C, int n) throws IOException{
		// (기본) 자신을 판단하고, 아닐 경우 4등분하여 표기한다.
		// (재귀종료) n == 1
		
		// 자신을 판단한다.
		boolean check_Self = true;
		char value = matrix[R][C];
		for (int i = R; i < (R+n); i++) {
			for (int j = C; j < (C+n); j++) {
				if(value != matrix[i][j]) {check_Self = false;  break;}
			}
		}
		if(check_Self == true) {sb.append(value); return;} 
		
    /* 자신이 false이기 때문에, 4등분 압축 한다. 아래와 동일한 상황. 항상 기준점은 최상단 좌측
		 else if(check_Self == false) 	{                                                       */
		sb.append('(');
		n = n/2;
		Solve(R,C,n);
		Solve(R,C+n,n);
		Solve(R+n,C,n);
		Solve(R+n,C+n,n);
		sb.append(')');
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