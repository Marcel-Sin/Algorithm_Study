import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100000, START = 1, VISITED = 2;
	
	
	static int N,ans;
	static int[][] map;
	static int[] visited;
	
	public static void main(String[] args) throws IOException{
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		map = new int[N][N];
		visited = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < N; j++) {
				map[i][j] = nextInt(stk);
			}
		}
	}

	static int Solve() throws IOException {
		ans = INF;
		DFS(0,0,0);
		return ans;
	}
	
	static void DFS(int here,int dist,int visitCount) {
		// 출발지
		if(visitCount == 0) {
			for (int i = 0; i < N; i++) {
				
				visited[i] = START;
				DFS(i,0,1);
				visited[i] = 0;
			}
		}
		// 아직 다 방문 못함.
		else if (visitCount < N) {
			for (int there = 0; there < N; there++) {
				if(visited[there] == 0 && map[here][there] != 0) {
					visited[there] = VISITED;
					DFS(there,dist+map[here][there],visitCount+1);
					visited[there] = 0;
				}
			}
		}
		//다 방문 했음.
		else {
			int startDist = INF;
			for (int i = 0; i < N; i++) if(visited[i] == START && map[here][i] != 0) {startDist = map[here][i]; break;} 
			ans = Min(ans,dist+startDist);
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