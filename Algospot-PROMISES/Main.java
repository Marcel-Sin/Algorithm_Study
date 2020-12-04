import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/4;
	static final int INF = Integer.MAX_VALUE/4;
	
	static final int MAX_VERTEX = 200; 
	
	static int TOTAL,V,E,M;
	
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	
	
	public static void main(String[] args) throws IOException{
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve());
		}
	}

	static int Solve() throws IOException{
		
		Floyd(V);
		int counter = 0;
		for (int i = 0; i < M; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			if(Floyd_Update(nextInt(stk), nextInt(stk), nextInt(stk)) == false) counter++;
		}
		
		return counter;
	}
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk);E = nextInt(stk);M = nextInt(stk);
		
		//필요만큼 Clear
		for (int i = 0; i < V; i++) for (int j = 0; j < V; j++) matrix[i][j] = (i == j) ? 0:INF;
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Min_Connect(nextInt(stk),nextInt(stk),nextInt(stk));
		}
		
	}
	
	static void Floyd(int size) {
		
		for (int x = 0; x < size; x++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if(matrix[i][x]+matrix[x][j] < matrix[i][j]) {
						matrix[i][j] = matrix[i][x]+matrix[x][j];
					}
				}
			}
		}
		
	}
	static boolean Floyd_Update(int a, int b, int w) {
		//직접 연결 상태보다 새 도로가 더 안좋거나 같은 경우 
		if(matrix[a][b] <= w) return false;
		matrix[a][b] = w;
		matrix[b][a] = w;
		
		int newCost;
		//신규 도로를 거쳐서 갈 경우, a,b 간선을 거칠 경우
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				newCost = Min(matrix[i][a]+matrix[b][j]+w,matrix[i][b]+matrix[a][j]+w);
				if(newCost < matrix[i][j]) {
					matrix[i][j] = newCost;
				}
			}	
		}
		return true;
	}
	
	static void Min_Connect(int a, int b, int w) {
		if(matrix[a][b] < w) return;
		matrix[a][b] = w;
		matrix[b][a] = w;
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
			else return (this.a < o.a) ? -1:1;
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