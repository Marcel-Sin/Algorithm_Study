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
	static final int MAX = 2187;
	
	
	static int N;
	static int[] paper = new int[3];
	static int[][] map = new int[MAX][MAX];
	
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve(N/2,N/2,N);
		System.out.println(paper[0]);
		System.out.println(paper[1]);
		System.out.println(paper[2]);
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < N; j++) {
				map[i][j] = nextInt(stk) + 1;
			}
		}
		
		
	}
	static void Solve(int center_Row, int center_Col, int size) throws IOException{
		// 1. 자신을 센다.
		int offset = size/2;
		boolean here_is_Paper = true;
		int paper_type = map[center_Row][center_Col];
		for (int i = center_Row-offset; i <= center_Row+offset; i++) {
			for (int j = center_Col-offset; j <= center_Col+offset; j++) {	
				if(paper_type != map[i][j]) { here_is_Paper = false;  break; }
			}	
		}
		// 2. 자신이 종이가 아니고, 현재 사이즈가 1이 아니면 잘라서 센다.
		if(!here_is_Paper && size != 1) {
			offset = size/3;
			for (int i = center_Row-offset; i <= center_Row+offset; i+=offset) {
				for (int j = center_Col-offset; j <= center_Col+offset; j+=offset) {	
					Solve(i,j,offset);
				}	
			}
		}
		else paper[paper_type]++;
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