import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static int MAX_VERTEX = 20000,scc_Counter,visit_Counter,N,M,tmp;
	
	static ArrayList<Integer>[] graph = new ArrayList[MAX_VERTEX];
	static int[] scc = new int[MAX_VERTEX];
	static int[] visited = new int[MAX_VERTEX];
	static Stack<Integer> stack = new Stack<Integer>();
	static int[] answer = new int[MAX_VERTEX];
	/*
	 * 3 4
	 * -1 2
	 * -2 3
	 * 1 3
	 * 3 2
	 */
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < graph.length; i++) graph[i] = new ArrayList<Integer>();
		Solve_2Sat();
	}
	static void Solve_2Sat() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		Arrays.fill(scc, -1);
		Arrays.fill(visited, -1);
		
		int a,b;
		for (int i = 0; i < M; i++) {
			stk = new StringTokenizer(io.inputStr());
			a = nextInt(stk); b = nextInt(stk);
			Connect(Indexing(-a),Indexing(b));
			Connect(Indexing(-b),Indexing(a));
		}
		int size = N*2;
		for (int i = 0; i < size; i++) {
			if(visited[i] == -1) TarjanSCC(i);
		}
		
		for (int i = 0; i < size; i+=2) {
			if(scc[i] == scc[i+1]) {
				
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
		ArrayList<Pair> list = new ArrayList<Main.Pair>();
		for (int i = 0; i < size; i++) {
			list.add(new Pair(scc[i],i));
		}
		Collections.sort(list);
		Arrays.fill(answer, -1);
		for (int i = 0; i < list.size(); i++) {
			int vertex = list.get(i).b;
			int variable = vertex/2;
			//2로 딱떨어지면 x, 남으면 ~x, 먼저나온 결과가 항상 F 가 되야함
			int value = (vertex % 2 == 0) ? 0:1; 
			if(answer[variable] != -1) continue;
			else answer[variable] = value;
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(answer[i]+" ");
		}
		
	}
	
	
	//항상 최소 방문카운트 지점 반환.
	static int TarjanSCC(int here) {
		stack.add(here);
		visited[here] = visit_Counter;
		int ret = visit_Counter++;
		for (int i = 0; i < graph[here].size(); i++) {
			int there = graph[here].get(i);
			if(visited[there] == -1) ret = Min(TarjanSCC(there),ret);
			else if(scc[there] == -1) ret = Min(visited[there],ret);
		}
		
		if(ret == visited[here]) {
			while(true) {
				int value = stack.pop();
				scc[value] = scc_Counter;
				if(value == here) break;
			}
			scc_Counter++;
		}
		return ret;
	}

	static int Indexing(int value) {
		int v = Math.abs(value)*2-2;
		if(value < 0) return v+1;
		else return v;
	}
	
	static void Connect(int a, int b) {
		graph[a].add(b);
	}
	static class Pair implements Comparable<Pair>{
		public int a,b;

		public Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.a == o.a) return 0;
			else return (this.a > o.a) ? -1:1;
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