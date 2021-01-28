import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 1001;
	
	static int N,M,V;
	static TreeSet<Integer>[] MAP = new TreeSet[MAX];
	
	static boolean[] visit = new boolean[MAX];
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {	
		for (int i = 0; i < MAX; i++) MAP[i] = new TreeSet<Integer>();
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		V = nextInt(stk);
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk)-1, nextInt(stk)-1);
		}

	}
	
	static void Solve() throws IOException {
		sb = new StringBuilder();
		Arrays.fill(visit, false);
		DFS(V-1);
		System.out.println(sb.toString());
		
		sb = new StringBuilder();
		Arrays.fill(visit, false);
		BFS(V-1);
		System.out.println(sb.toString());
	}

	static void DFS(int here) {
		visit[here] = true;
		sb.append(here+1);
		sb.append(' ');
		Iterator<Integer> iter = MAP[here].iterator();
		while(iter.hasNext()) {
			int there = iter.next();
			if(!visit[there]) DFS(there);
		}
	}
	
	static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visit[start] = true;
		
		while(!queue.isEmpty()) {
			int here = queue.poll();
			sb.append(here+1);
			sb.append(' ');
			Iterator<Integer> iter = MAP[here].iterator();
			while(iter.hasNext()) {
				int there = iter.next();
				if(!visit[there]) {
					queue.add(there);
					visit[there] = true;
				}
			}
			
		}
	}
	
	
	static void Connect(int a, int b) {
		MAP[a].add(b);
		MAP[b].add(a);
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