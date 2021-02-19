import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100001;

	static int row,col;
	static char[] problem;
	static char[] memory = new char[MAX];
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		
		row = nextInt(stk);
		col = nextInt(stk);
	}
	static int Solve() throws IOException{
		int move = 0, r = 0;
		col--; //col은 오른쪽 이동가능한 남은 칸
		// Greedy : 오른쪽으로 최대한 느리게 갈수록 유리하다. 가능한 오른쪽1 이동 사용.
		if(row >= 3) {
			// <CASE1> 모든 이동 사용 조건을 먼저 해제한 후, 남은 것은 위2,아래2 지그재그 반복
			int case1_Move = 0,case2_Move = 0; 
			r = col -2 -2 -1 -1;
			case1_Move = r + 4; 
			
			// <CASE2> 최대 3회 동작, 위2아래2만 사용한다.
			r = col;
			for (int i = 0; i < 3; i++) {
				if(r-1 >= 0) { r -= 1; case2_Move++;}  
				else break;
			}
			move = Max(case1_Move,case2_Move);
		}
		else if (row == 2) {
			// 최대 3회, 위1아래1만 사용한다.
			r = col;
			for (int i = 0; i < 3; i++) {
				if(r-2 >= 0) {r -= 2; move++;}
				else break;
			}
		}
		return move+1;
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