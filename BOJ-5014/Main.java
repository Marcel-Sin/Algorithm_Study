import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int PROBLEM_MAX = 1000001;
	
	static int F,S,G,U,D;
	static int[] caseArray = new int[2];
	static int[] visited = new int[PROBLEM_MAX];
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}

	static void Init() throws IOException {
		Arrays.fill(visited, -1);
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		F = nextInt(stk);
		S = nextInt(stk);
		G = nextInt(stk);
		caseArray[0] = nextInt(stk);
		caseArray[1] = nextInt(stk)*-1;
	}

	static void Solve() throws IOException {
		queue.add(S);
		visited[S] = 0;
		while(!queue.isEmpty()) {
			int here = queue.poll();
			if(here == G) break;
			int next;
			for (int i = 0; i < caseArray.length; i++) {
				next = here+caseArray[i];
				if(1 <= next && next <= F && visited[next] == -1) {
					visited[next] = visited[here] + 1;
					queue.add(next);
				}
			}
		}
		if(visited[G] == -1) System.out.println("use the stairs");
		else System.out.println(visited[G]);
	}

	
	
	
	
//	===================== ETC functions for PS =====================
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
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}


//-------------IO_Manager--------------
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