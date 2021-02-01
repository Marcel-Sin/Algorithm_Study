import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100;
	
	static int ROW,COL;
	
	static int[][] matrix = new int[MAX][MAX];
	static boolean[][] visit = new boolean[MAX][MAX];
	
	static int[] dirRow = {-1,1,0,0};
	static int[] dirCol = {0,0,-1,1};
	
	static Queue<Pair> queue = new LinkedList<Main.Pair>();

	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		ROW = nextInt(stk);
		COL = nextInt(stk);
		
		for (int i = 0; i < ROW ; i++) {
			char[] temp = io.inputStr().toCharArray();
			int k = 0;
			for (int j = 0; j < temp.length; j++) {
				matrix[i][j] = temp[j]-'0';
			}
		}
	}
	
	static int Solve() throws IOException {
		return BFS(0,0);
	}
	
	// 함수 정의 : 해당 지점에서 목표까지의 최단거리 항상 리턴
	static int BFS(int r, int c) {
		queue.clear();
		queue.add(new Pair(r,c));
		visit[r][c] = true;
		boolean change = false;
		int ret = 1,nr,nc,size;
		while(!queue.isEmpty()) {
			change = false;
			size = queue.size();
			for (int i = 0; i < size; i++) {
				Pair pair = queue.poll();
				for (int j = 0; j < 4; j++) {
					nr = pair.a+dirRow[j]; nc = pair.b+dirCol[j];
					if(0 <= nr&&nr < ROW && 0 <= nc&&nc < COL && matrix[nr][nc] == 1 && !visit[nr][nc]) {
						if(nr == ROW-1 && nc == COL-1) return ret+1;
						queue.add(new Pair(nr,nc));
						visit[nr][nc] = true;
						change = true;
					}
				}
			}
			if(!change) break;
			else ret++;
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