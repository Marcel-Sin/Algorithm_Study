import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE;
	
	static final int MAX_VERTEX = 10000; 
	
	//최대 정점 : 10000, 최대 간선 : 20000 , 리스트 그래프가 유리
	static int TOTAL,N,M;
	static ArrayList<Pair>[] adj = new ArrayList[MAX_VERTEX];
	static Queue<Pair> pq = new PriorityQueue<Pair>((a,b) -> {
		if(a.value == b.value) return 0;
		else return (a.value < b.value) ? -1:1;
	});
	
	static double[] dist = new double[MAX_VERTEX];
	
		
	public static void main(String[] args) throws IOException{
		for (int i = 0; i < MAX_VERTEX; i++) adj[i] = new ArrayList<Pair>();	
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.printf("%.10f",Solve(0));
			System.out.println();
		}
	}
	
	static void Init() throws IOException{
		//Clear
		Arrays.fill(dist, Double.MAX_VALUE);
		for (int i = 0; i < adj.length; i++) adj[i].clear();	
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk),nextInt(stk),Double.valueOf(stk.nextToken()));
		}

	}
	
	static double Solve(int start) {
		dist[start] = 1d;
		pq.add(new Pair(start,1d));
		
		while(!pq.isEmpty()) {
			Pair pair = pq.poll();
			int here = pair.vertex;
			double hereValue = pair.value; 
			if(hereValue < dist[here]) continue;
			for (int i = 0; i < adj[here].size(); i++) {
				Pair nextPair = adj[here].get(i);
				if(hereValue*nextPair.value < dist[nextPair.vertex]) {
					dist[nextPair.vertex] = hereValue*nextPair.value;
					pq.add(new Pair(nextPair.vertex,dist[nextPair.vertex]));
				}
			}
		}
		return dist[N-1];
	}
	
	static void Connect(int a, int b,double w) {
		adj[a].add(new Pair(b,w));
		adj[b].add(new Pair(a,w));
	}
	static class Pair {
		int vertex;
		double value;
		public Pair(int vertex, double value) {
			super();
			this.vertex = vertex;
			this.value = value;
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