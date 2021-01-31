import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 1000;
	
	static int ROW,COL;
	
	static int[][] matrix = new int[MAX][MAX];
	static int[] dirRow = {-1,1,0,0};
	static int[] dirCol = {0,0,-1,1};
	static Queue<Pair> queue = new LinkedList<Pair>();

	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		COL = nextInt(stk);
		ROW = nextInt(stk);


		for (int i = 0; i < ROW ; i++) {
			stk = new StringTokenizer(io.inputStr());
			int k = 0;
			while(stk.hasMoreTokens()) {
				matrix[i][k] = nextInt(stk);
				if(matrix[i][k] == 1) queue.add(new Pair(i,k));
				k++;
			}
		}
	}
	
	static void Solve() throws IOException {
		int day = BFS();
		boolean check = true;
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COL; j++) {
				if(matrix[i][j] == 0) {check = false; break; } 
			}
		}
		if(check == false) System.out.println(-1);
		else System.out.println(day);
	}
	static int BFS() {
		
		int ret = 0;

		boolean check;
		int size,r,c,nr,nc;
		Pair pair;
		while(!queue.isEmpty()) {
			check = false;
			size = queue.size();
			for (int i = 0; i < size; i++) {
				pair = queue.poll();
				r = pair.a;
				c = pair.b;
				for (int k = 0; k < 4; k++) {
					nr = r + dirRow[k];
					nc = c + dirCol[k];
					if(0 <= nr && nr < ROW && 0 <= nc && nc < COL && matrix[nr][nc] == 0) {
						matrix[nr][nc] = 1;
						queue.add(new Pair(nr,nc));
						check = true;
					}
				}
			}
			if(check == false) return ret;
			ret++;
		}
		return ret;
	}
	
	static class Pair {
		public int a,b;

		public Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
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