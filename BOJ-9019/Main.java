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
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int PROBLEM_MAX = 10000;
	
	static int N, M, TEST;
	static int[] rr_Result = new int[PROBLEM_MAX];
	static int[] lr_Result = new int[PROBLEM_MAX];
	
	// 방문체크 및 재귀 스트링 형성
	static Pair[] visited_Pair = new Pair[PROBLEM_MAX];
	static StringBuilder sb; 
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		Before_Init();
		TEST = io.inputInt();
		for (int i = 0; i < TEST; i++) {
			Init();
			Solve();
		}
	}
	static void Before_Init() throws IOException {
		for (int i = 0; i < PROBLEM_MAX; i++) {
			rr_Result[i] = Right_Rotate_Decimal(i);
			lr_Result[i] = Left_Rotate_Decimal(i);
		}
		for (int i = 0; i < PROBLEM_MAX; i++) visited_Pair[i] = new Pair(0,'0');
	}
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		for (int i = 0; i < PROBLEM_MAX; i++) visited_Pair[i].parent = -1;
	}

	static int carry, rest;
	static int Right_Rotate_Decimal(int value) {
		// ABCD
		// (1) D를 가져온다.
		carry = value % 10;
		// (2) ABC를 가져온다.
		rest = value / 10;
		// 결과 : DABC
		return carry * 1000 + rest;
	}
	static int Left_Rotate_Decimal(int value) {
		// ABCD
		// (1) A를 가져온다.
		carry = value / 1000;
		// (2) BCD를 가져온다.
		rest = value % 1000;
		// 결과 : BCDA
		return rest * 10 + carry;
	}
	static void Recursive_String(int idx) {
		if(visited_Pair[idx].parent != idx) {
			Recursive_String(visited_Pair[idx].parent);
			sb.append(visited_Pair[idx].c);
		}
	}
	
	static void Solve() throws IOException {
		queue.clear();
		visited_Pair[N].parent = N;
		queue.add(N);
		int[] nextNumber = new int[4];
		
		while(!queue.isEmpty()) {
			int here = queue.poll();
			if(here == M) break;
			nextNumber[0] = (here*2)%10000;
			nextNumber[1] = (here-1+10000)%10000;
			nextNumber[2] = rr_Result[here];
			nextNumber[3] = lr_Result[here];
			for (int i = 0; i < 4; i++) {
				if(visited_Pair[nextNumber[i]].parent == -1) {
					visited_Pair[nextNumber[i]].parent = here;
					switch(i) {
						case 0 : 
							visited_Pair[nextNumber[i]].c = 'D';
							break;
						case 1 : 
							visited_Pair[nextNumber[i]].c = 'S';
							break;
						case 2 : 
							visited_Pair[nextNumber[i]].c = 'R';
							break;
						case 3 : 
							visited_Pair[nextNumber[i]].c = 'L';
							break;
					}
					queue.add(nextNumber[i]);
				}
			}
		}
		sb = new StringBuilder();
		Recursive_String(M);
		System.out.println(sb.toString());
	}
	
	static class Pair {
		public int parent;
		public char c;
		public Pair(int parent, char c) {
			super();
			this.parent = parent;
			this.c = c;
		}
	}
//	===================== ETC functions for PS =====================
//	===================== ETC functions for PS =====================
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
		// System.out.println("요소갯수 : " + arr.length);
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