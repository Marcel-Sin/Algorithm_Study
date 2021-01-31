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
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 100001;
	
	static int TEST,N;
	
	static int[] graph = new int[MAX];
	static int[] visit = new int[MAX];
	static boolean[] cycle = new boolean[MAX];
	
	public static void main(String[] args) throws IOException {	
		TEST = io.inputInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < TEST; i++) {
			Init();
			sb.append(Solve());
			sb.append('\n');
			
		}
		System.out.print(sb.toString());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N ; i++) {

			graph[i] = nextInt(stk)-1;
			visit[i] = (graph[i] == i) ? INF:-1;
			cycle[i] = (graph[i] == i) ? true:false;
			
		}

	}
	
	static int Solve() throws IOException {
		int ret = 0;
		int t = 0;
		boolean check = false;
		for (int i = 0; i < N; i++) {
			if(visit[i] == -1) Cycle_Check(i,++t);
		}
		for (int i = 0; i < N; i++) {
			if(cycle[i]) ret++;
		}
		return N-ret;
	}

	//항상 사이클 정점 갯수 리턴. (정점들이 사이클이 항상 존재하는 문제)
	static void Cycle_Check(int here,int travel) {
		int there = graph[here];
		
		//신규 사이클 발견 생성
		if(visit[here] == travel && cycle[here] == false) {
			cycle[here] = true;
			int pos = graph[here];
			while(pos != here) {
				cycle[pos] = true;
				pos = graph[pos];
			}
			return;
		}
		else if(cycle[here] == true) return;
		else if(visit[here] != -1) return;
		
		// 탐색의 시작
		visit[here] = travel;
		Cycle_Check(there, travel);
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