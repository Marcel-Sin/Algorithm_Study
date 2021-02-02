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
	
	static int N;
	
	static int[][] map = new int[MAX][MAX];
	static boolean[][] visit = new boolean[MAX][MAX];
	
	static int[] bridge = new int[10000];
	
	static int[] dirRow = {-1,1,0,0};
	static int[] dirCol = {0,0,-1,1};
	
	static Queue<WorkSite> queue = new LinkedList<WorkSite>();

	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(BFS());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		
		int temp = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; stk.hasMoreTokens(); j++) {
				temp = nextInt(stk);
				map[i][j] = (temp == 1) ? -1:0;
			}
		}
		int landnum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == -1) DFS(i,j,landnum++); 
			}
		}
	}
	static void DFS(int i, int j, int land) {
		map[i][j] = land;
		visit[i][j] = true;
		queue.add(new WorkSite(i, j, land));
		int nr,nc;
		for (int k = 0; k < 4; k++) {
			nr = i + dirRow[k]; nc = j + dirCol[k];
			if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == -1 && visit[nr][nc] == false) DFS(nr,nc,land);
		}
	}

	static int BFS() {
		
		//DFS에서 이미 출발 지점이 처리됐음. (Land 번호 순으로 queue에 존재)
		
		int ret = INF,nr,nc,current_Island;
		WorkSite worksite;
		current_Island = 0; 
		while(!queue.isEmpty()) {
			
			worksite = queue.poll();
			
			// 정답 찾은 상황에서, 모든 섬을 한바퀴 다 돌았음. 더 나아질수는 없음.
			if(ret != INF && current_Island > worksite.land ) break;
			// 이번 섬의 외곽 다리 건설이 시작됨. (섬의 번호는 1번부터 시작되므로 반드시 동작)
			else if(current_Island != worksite.land) {
				current_Island = worksite.land;
				bridge[current_Island]++;
			}
			
			for (int i = 0; i < 4; i++) {
				nr = worksite.row + dirRow[i];
				nc = worksite.col + dirCol[i];
				if(0 <= nr && nr < N && 0 <= nc && nc < N) {
					// *Case1* 다음 탐색 지역이 바다면, 현재 섬의 번호로 다리를 짓는다.
					if(map[nr][nc] == 0) {
						map[nr][nc] = current_Island;
						queue.add(new WorkSite(nr,nc,current_Island));
					}
					// *Case2* 바다도 아닌데, 현재 섬의 번호랑 다르다. (정답인 또 다른 섬 발견)
					else if(map[nr][nc] != current_Island) {
						int another = map[nr][nc];
						// 현재 처리 타이밍에 더 빠른게 있을 수 있음.
						ret = Min(ret,bridge[another]+bridge[current_Island]-1);
					}
					// 그외는 같은 섬의 번호이므로 무시
				}
			}

		}
		return ret;
	}
	
	static class WorkSite {
		public int row,col,land;

		public WorkSite(int row, int col, int land) {
			super();
			this.row = row;
			this.col = col;
			this.land = land;
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