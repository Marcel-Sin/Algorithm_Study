import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 25;
	
	static int N,HOUSE;
	
	static int[][] matrix = new int[MAX][MAX];
	static int[][] visit = new int[MAX][MAX];
	static int[] dirRow = {0,0,-1,1};
	static int[] dirCol = {1,-1,0,0};
	

	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i < N; i++) Arrays.fill(visit[i], -1);
		
		for (int i = 0; i < N ; i++) {
			char[] map = io.inputStr().toCharArray();
			for (int j = 0; j < N; j++) {
				matrix[i][j] = map[j]-'0';
				if(matrix[i][j] == 1) visit[i][j] = 0;
			}
		}
	}
	
	static void Solve() throws IOException {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N ; i++) {
			for (int j = 0; j < N; j++) {
				if(visit[i][j] == 0) {
					HOUSE++;
					list.add(DFS(i,j));
				}
			}
		}
		Collections.sort(list);
		System.out.println(HOUSE);
		Iterator<Integer> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	static int DFS(int r, int c) {
		visit[r][c] = 1;
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int nr = r + dirRow[i];
			int nc = c + dirCol[i];
			if(0 <= nr && nr < N && 0 <= nc && nc < N && visit[nr][nc] == 0) {
				ret += DFS(nr,nc);
			}
		}
		return ret+1;
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