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
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE;
	
	static final int MAX_VERTEX = 1000; 
	
	static int N,M,X;
	
	//adj[vertex].get(there) = vertex-there weight
	static ArrayList<Pair>[] adj = new ArrayList[MAX_VERTEX];
	static PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> {
		if(a.second == b.second) return 0;
		else return (a.second < b.second) ? -1:1;
	});
	static int[] dist = new int[MAX_VERTEX];
	
		
	public static void main(String[] args) throws IOException{
		Init();
		System.out.println(Solve());
	}
	
	static int Solve() {
		int ret = 0;
		
		
		int[] toX = new int[N];
		int[] fromX = Dijkstra(X);
		
		for (int i = 0; i < N; i++) {
			if(i == X) continue;
			else {
				int[] temp = Dijkstra(i);
				toX[i] = temp[X];
			}
		}
		
		for (int i = 0; i < N; i++) {
			if(X != i)ret = Max(ret, fromX[i]+toX[i]);
		}
		
		return ret;
	}
	
	
	static int[] Dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		// Pair<Vertex, Cost>
		pq.add(new Pair(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int here = p.first;
			int hereCost = p.second;
			if(dist[here] < hereCost) continue;
			
			for(int i = 0; i < adj[here].size(); i++) {
				Pair adjPair = adj[here].get(i);
				if(hereCost+adjPair.second < dist[adjPair.first]) {
					dist[adjPair.first] = hereCost+adjPair.second;
					pq.add(new Pair(adjPair.first,dist[adjPair.first]));
				}
			}
		}
		int[] retDist = new int[N];
		for (int i = 0; i < retDist.length; i++) {
			retDist[i] = dist[i];
		}
		return retDist;
	}
	
	
	static void Init() throws IOException{
		for (int i = 0; i < MAX_VERTEX; i++) adj[i] = new ArrayList<Pair>();
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		X = nextInt(stk)-1;
		
		
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk)-1,nextInt(stk)-1,nextInt(stk));
		}
		
	}
	
	static void Connect(int a, int b,int w) {
		adj[a].add(new Pair(b,w));
	}
	static class Pair {
		int first, second;

		public Pair(int first, int second) {
			super();
			this.first = first;
			this.second = second;
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
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
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