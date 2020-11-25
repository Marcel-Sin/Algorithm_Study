import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE;
	
	static final int MAX_VERTEX = 100; 
	
	
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	static PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> {
		if(a.first == b.first) return 0;
		else return (a.first < b.first) ? -1:1;
	});
	static int[] previous = new int[MAX_VERTEX];
	static int[] dist = new int[MAX_VERTEX];
	
	public static void main(String[] args) {
		Init(); 
		Dijkstra(0);
		for(int i = 0; i < 7; i++) {
			System.out.println("["+ (char)(i+'a') +"] Dist : " + dist[i]);
			System.out.print(""+ (char)(0+'a') +"->"+ (char)(i+'a') +" 경로 : ");
			Path(i);
			System.out.println();
			System.out.println();
		}
	}
	
	
	static void Dijkstra(int start) {
		dist[start] = 0;
		previous[start] = start;
		// Pair<Cost, Vertex>
		pq.add(new Pair(dist[start], start));
		
		while(!pq.isEmpty()) {
			Pair p = pq.poll();
			int hereCost = p.first;
			int here = p.second;
			if(dist[here] < hereCost) continue;
			
			for(int there = 0; there < matrix.length; there++) {
				if(matrix[here][there] > 0 && hereCost+matrix[here][there] < dist[there]) {
					dist[there] = hereCost+matrix[here][there];
					previous[there] = here;
					pq.add(new Pair(dist[there], there));
				}
			}
		}
		
		
		
	}
	
	static void Init() {
		for (int i = 0; i < dist.length; i++) Arrays.fill(matrix[i], 0);
		Arrays.fill(dist, INF);
		Arrays.fill(previous, -1);
		
		Connect(0,1,5);
		Connect(0,2,1);
		Connect(2,3,2);
		Connect(3,1,1);
		Connect(3,4,5);
		Connect(3,5,3);
		Connect(1,6,3);
		Connect(1,5,3);
		Connect(6,5,2);
	}
	
	static void Path(int there) {
		if(previous[there] == there) {
			System.out.print((char)(there+'a')+ " - ");
			return;
		}
		Path(previous[there]);
		System.out.print((char)(there+'a')+ " - ");
	}
	static void Connect(int a, int b,int w) {
		matrix[a][b] = w;
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