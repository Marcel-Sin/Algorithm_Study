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
	static int TOTAL,N;
	static ArrayList<char[]> PROBLEM = new ArrayList<char[]>();
	static int[][] PRIORITY = new int[26][26];
	static boolean[] VISITED = new boolean[26];
	static boolean[] FINISHED = new boolean[26];
	static Stack<Integer> STACK = new Stack<Integer>();
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
	}
	static void Init() throws IOException{
		N = io.inputInt();
		PROBLEM.clear();
		for(int i = 0; i < N; i++) PROBLEM.add(io.inputStr().toCharArray());
		
	}
	
	static void Solve() {
		STACK.clear();
		Arrays.fill(VISITED, false);
		Arrays.fill(FINISHED, false);
		for(int i = 0; i < PRIORITY.length; i++) Arrays.fill(PRIORITY[i], 0);	
		for(int i = 0; i < PROBLEM.size()-1; i++) Check_Words(PROBLEM.get(i), PROBLEM.get(i+1));
		
		
		boolean ret = true;
		for (int i = PRIORITY.length-1; i >= 0; i--) {
			if(VISITED[i] == false) ret = DFS(i);
			if(ret == false) break;
		}
		
		
		
		if(ret == false) System.out.println("INVALID HYPOTHESIS");
		else {
			while (STACK.size() != 0) {
				char c = (char) (STACK.pop() + 'a');
				System.out.print(c);
			}
			System.out.println();
		}
		
		
	}
	static boolean DFS(int sp) {
		VISITED[sp] = true;
		
		boolean ret = true;
		int i = 0;
		while(i < 26 && ret == true) {
			//간선이 존재한다.
			if (PRIORITY[sp][i] == 1) {
				
				//아직 방문하지 않았다.
				if(VISITED[i] == false) ret = DFS(i);
				
				//방문은 했으나, 방문이 끝나지 않았음에도 같은 곳을 다시 가려한다. (Cycle)
				else if(FINISHED[i] == false) return false;
			}
			i++;
		}
		FINISHED[sp] = true;
		STACK.add(sp);
		return ret;
	}
	
	
	// a가 더 우선적
	static void Check_Words(char[] a, char[] b) {
		int minlen = (a.length < b.length) ? a.length:b.length;
		int idx = 0;
		while(idx < minlen && a[idx] == b[idx]) idx++;
		if(idx < minlen && a[idx] != b[idx]) PRIORITY[Numberize(a[idx])][Numberize(b[idx])] = 1;
	}

	static int Numberize(char c) {
		return c - 'a';
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