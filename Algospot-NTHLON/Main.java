import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = -100000000; 
	static final int INF = 100000000;
	static final int MAX_VERTEX = 402; 
	
	static int TOTAL,M;
	
	static ArrayList<Vertex>[] graph = new ArrayList[MAX_VERTEX];
	static ArrayList<Data> data = new ArrayList<Data>();
	
	static PriorityQueue<Vertex> pq = new PriorityQueue<Main.Vertex>((a,b) -> {
		if(a.value == b.value) return 0;
		else return (a.value < b.value)? -1:1;
	});
	static int[] dist = new int[MAX_VERTEX];
	
	public static void main(String[] args) throws IOException{
		
		for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<Vertex>();
		
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
		
		
		
		
	}
	
	static void Init() throws IOException {
		M = io.inputInt();
		for (int i = 0; i < graph.length; i++) graph[i].clear();
		data.clear();
		HashMap<Integer, Integer> c = new HashMap<Integer, Integer>();
		for (int i = 0; i < M; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			int a = nextInt(stk);
			int b = nextInt(stk);
			
			if (c.containsKey(a-b) == false) c.put(a-b, a);
			else if (c.containsKey(a-b) && c.get(a-b) > a) c.put(a-b, a);
		}
		for(int key : c.keySet()) data.add(new Data(c.get(key),key));
		
		for (int v = -200; v <= 200; v++) {
			for(int i = 0; i < data.size(); i++) {
				int nextVertex = v + data.get(i).dif;
				if(Math.abs(nextVertex) > 200) continue;
				Connect(v+200,nextVertex+200,data.get(i).a);
			}
		}
		
		for (int i = 0; i < data.size(); i++) {
			Connect(401,data.get(i).dif+200,data.get(i).a);
		}
	}
	
	static void Solve() {
		Dijkstra(401);
		if(dist[200] == INF) System.out.println("IMPOSSIBLE");
		else System.out.println(dist[200]);
	}
	static void Dijkstra(int start) {
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Vertex(start,dist[start]));
		
		while(!pq.isEmpty()) {
			Vertex p = pq.poll();
			int hereCost = p.value;
			int here = p.num;
			if(dist[here] < hereCost) continue;
			
			for(int i = 0; i < graph[here].size(); i++) {
				int there = graph[here].get(i).num;
				int thereCost = hereCost+graph[here].get(i).value;
				if(thereCost < dist[there]) {
					dist[there] = thereCost;
					pq.add(new Vertex(there,thereCost));
				}
			}
		}
	}

	static int Delta(int n) {
		return 200+n;
	}
	static class Vertex {
		public int num,value;

		public Vertex(int a, int b) {
			super();
			this.num = a;
			this.value = b;
		}
		
	}	
	static class Data {
		public int a,dif;

		public Data(int a, int b) {
			super();
			this.a = a;
			dif = b;
		}
		
	}
	static void Connect(int a, int b, int w) {
		graph[a].add(new Vertex(b,w));
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