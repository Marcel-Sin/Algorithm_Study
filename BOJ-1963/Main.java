import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int PROBLEM_MAX = 9999;
	static final int PROBLEM_MIN = 1000;
	static final int[] POW = {1,10,100,1000,10000};
	
	
	static int N,M,TEST;
	static int[] visited = new int[PROBLEM_MAX+1];
	static boolean[] PRIME = new boolean[PROBLEM_MAX+1];
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException{
		Before_Init();
		TEST = io.inputInt();
		for (int i = 0; i < TEST; i++) {
			Init();
			Solve();
		}
	}
	
	static void Before_Init() throws IOException{
		Arrays.fill(PRIME, true);
		for (int i = 2; i < 100; i++) {
			for (int j = 0; j < PRIME.length; j+=i) {
				PRIME[j] = false;
			}
		}
	}

	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		Arrays.fill(visited, -1);
	}

	static void Solve() throws IOException {
		queue.clear();
		queue.add(N);
		visited[N] = 0;
		int next = 0;
		while(!queue.isEmpty()) {
			int here = queue.poll();
			if(here == M) break;
			for (int pos = 0; pos < 4; pos++) {
				for (int num = 0; num < 10; num++) {
					next = Numset(here,pos,num);
					if(PROBLEM_MIN <= next && PRIME[next] && visited[next] == -1) {
						queue.add(next);
						visited[next] = visited[here]+1;
					}
				}
			}
		}
		if(visited[M] == -1) System.out.println(-1);
		else System.out.println(visited[M]);
	}
	
	private static int unit,front,rear;
	static int Numset(int value, int pos, int num) {
		unit = POW[pos];
		front = value/POW[pos+1];
		rear = value % unit;
		return num*unit + front*POW[pos+1] + rear;
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