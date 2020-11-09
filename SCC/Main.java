import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static final int MAX_LETTER = 26; 
	
	static int[][] MATRIX = new int[10][10];
	static int[] DISCOVER = new int[10];
	static boolean[] SCC_CHECK = new boolean[10];
	
	static ArrayList<ArrayList<Integer>> SCC_LIST = new ArrayList<ArrayList<Integer>>();
	
	static Stack<Integer> STACK = new Stack<Integer>();
	static int SCC_COUNT, ID_COUNT;
	
	

	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	
	static void Init() {
		for (int i = 0; i < MATRIX.length; i++) Arrays.fill(MATRIX[i], 0);	
		Arrays.fill(DISCOVER, -1);
		Arrays.fill(SCC_CHECK, false);
		SCC_LIST.clear();
		STACK.clear();
		SCC_COUNT = 0;
		ID_COUNT = 0;
		
		Connect(4,2);
		Connect(2,1);
		Connect(1,3);
		Connect(3,2);
		Connect(5,3);
		Connect(5,6);
		Connect(6,9);
		Connect(9,8);
		Connect(8,5);
		
	}
	
	static void Solve() {
		for(int i = 0; i < MATRIX.length; i++) {
			if(DISCOVER[i] == -1) DFS(i);
		}
		
		for (int i = 0; i < SCC_LIST.size(); i++) {
			System.out.print("[" +i+ "] : ");
			for (int j = 0; j < SCC_LIST.get(i).size(); j++) {
				System.out.print(SCC_LIST.get(i).get(j) + ", ");
			}
			System.out.println();
		}
	}
	
	// 우회로로 방문할 수 있는 가장 최소점 반환
	static int DFS(int here) {
		DISCOVER[here] = ID_COUNT++;
		int parent = DISCOVER[here];
		STACK.add(here);
		
		for(int there = 0; there < MATRIX.length; there++) {
			if(MATRIX[here][there] == 1) {
				if(DISCOVER[there] == -1) parent = Min(parent, DFS(there));
				else if(SCC_CHECK[there] == false) parent = Min(parent,DISCOVER[there]);
			}
		}
		
		if(parent == DISCOVER[here]) {
			SCC_LIST.add(new ArrayList<Integer>());
			while(true) {
				int vertex = STACK.pop();
				SCC_LIST.get(SCC_COUNT).add(vertex);
				SCC_CHECK[vertex] = true;
				if(vertex == here) break;
			}
			SCC_COUNT++;
		}
		return parent;
	}
	
	static void Connect(int a, int b) {
		MATRIX[a][b] = 1;
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