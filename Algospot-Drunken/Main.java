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
	
	static final int MAX_VERTEX = 500; 
	
	static int TOTAL,V,E;
	
	
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	static int[][] worst = new int[MAX_VERTEX][MAX_VERTEX];
	//actual[virtual Index] = actual Index
	static int[] actual = new int[MAX_VERTEX];
	
	//virtual[actual Index] = virtual Index
	static int[] virtual = new int[MAX_VERTEX];
	static int[] addCost = new int[MAX_VERTEX];
	
	
	public static void main(String[] args) throws IOException{
		Init();
		Solve();
	}

	static void Solve() throws IOException{
		Floyd(V);
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			int a,b;
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			a = virtual[nextInt(stk)-1];
			b = virtual[nextInt(stk)-1];
			
			System.out.println(worst[a][b]);
		}
	}
	
	static void Init() throws IOException {
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		V = nextInt(stk);
		E = nextInt(stk);
		
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				matrix[i][j] = (i == j) ? 0:INF;
			}
		}
		
		ArrayList<Pair> list = new ArrayList<Main.Pair>();
		
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; stk.hasMoreTokens(); i++) list.add(new Pair(nextInt(stk),i));

		Collections.sort(list);
		
		for (int virIdx = 0; virIdx < list.size(); virIdx++) {
			int actIdx = list.get(virIdx).b;
			addCost[virIdx] = list.get(virIdx).a;
			actual[virIdx] = actIdx;
			virtual[actIdx] = virIdx;
		}
		
		
		
		for (int i = 0; i < E; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(virtual[nextInt(stk)-1],virtual[nextInt(stk)-1],nextInt(stk));
		}
		
		for (int i = 0; i < V; i++) {
			for (int j = 0; j < V; j++) {
				worst[i][j] = (i == j) ? 0:matrix[i][j];
			}
		}
		

	}
	
	static void Floyd(int size) {
		
		for (int x = 0; x < size; x++) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					matrix[i][j] = Min(matrix[i][x]+matrix[x][j],matrix[i][j]);
					worst[i][j] = Min(matrix[i][x]+matrix[x][j]+addCost[x],worst[i][j]);
				}
			}
		}
		
	}
	

	static void Connect(int a, int b, int w) {
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