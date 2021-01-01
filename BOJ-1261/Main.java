import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 101;
	
	static int N,M;
	static int[][] MAP = new int[MAX_SIZE][MAX_SIZE];
	
	@SuppressWarnings("unchecked")
	static ArrayList<Pair>[] link = new ArrayList[MAX_SIZE*MAX_SIZE];
	static int[] dirX = {0,0,-1,1};
	static int[] dirY = {-1,1,0,0};
	static int[] dist = new int[MAX_SIZE*MAX_SIZE];
	static PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new My_Comparator());
	
	
	public static void main(String[] args) throws IOException {
		Init();
		for (int i = 0; i < link.length; i++) link[i] = new ArrayList<Pair>();
		System.out.println(Solve(0,GetVertex(N-1, M-1)));
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		M = nextInt(stk);
		N = nextInt(stk);
		
		for (int i = 0; i < N; i++) {
			char[] tmp = io.inputStr().toCharArray();
			for (int j = 0; j < tmp.length; j++) MAP[i][j] = tmp[j]-'0';
		}
		
		Arrays.fill(dist, INF);
	}
	
	static int Solve(int start, int arrive) {
		int nx, ny;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 4; k++) {
					nx = i+dirX[k]; ny = j+dirY[k];
					if( (0 <=  nx && nx < N) && (0 <=  ny && ny < M)) {
						Connect(GetVertex(i,j), GetVertex(nx, ny), MAP[nx][ny]);
					}
				}
			}
		}
		
		dist[start] = 0;
		pq.add(new Pair(start,0));
		while(!pq.isEmpty()) {
			Pair here = pq.poll();
			if(here.w > dist[here.v]) continue;
			for (int i = 0; i < link[here.v].size(); i++) {
				int there = link[here.v].get(i).v;
				int cost = link[here.v].get(i).w;
				if(dist[here.v]+cost < dist[there]) {
					dist[there] = dist[here.v]+cost;
					pq.add(new Pair(there,dist[there]));
				}
			}
		}
		return dist[arrive];
	}
	static int GetVertex(int i, int j) {
		return i*M+j;
	}
	static void Connect(int a, int b, int w) {
		link[a].add(new Pair(b,w));
	}
	static class Pair {
		public int v,w;

		public Pair(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	static class My_Comparator implements Comparator<Pair>{
		@Override
		public int compare(Pair a, Pair b) {
			if(a.w == b.w) return 0;
			else return (a.w < b.w) ? -1:1;
		}
	}
	// ===================== functions for PS =====================
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