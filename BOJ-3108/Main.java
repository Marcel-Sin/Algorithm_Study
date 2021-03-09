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
	static final int PROBLEM_MAX = 2001;
	static final int NO_PATH = -1, PATH = 0, VISITED = 1;
	
	static int N;
	static int[][] checkMap = new int[PROBLEM_MAX][PROBLEM_MAX];
	static ArrayList<Pair> startList = new ArrayList<Main.Pair>();
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}

	static void Init() throws IOException {
		N = io.inputInt();
		for (int i = 0; i < checkMap.length; i++) Arrays.fill(checkMap[i], NO_PATH);	
		//크기 범위는 -500~500 이므로 +500해버리면, 0~1000까지로 그 상태에서 좌표를 2배 한다.

		
		int arr[] = new int[4];
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 4; j++) arr[j] = (nextInt(stk)+500)*2;
			Write_Rect(arr);
			startList.add(new Pair(arr[0],arr[1]));
		}
	}

	static int Solve() throws IOException {
		int ret = 0;
		if(checkMap[1000][1000] == PATH) DFS(1000,1000);
		for (int i = 0; i < startList.size(); i++) {
			Pair pair = startList.get(i);
			if(checkMap[pair.a][pair.b] == PATH) {
				ret++;
				DFS(pair.a,pair.b);
			}
		}
		return ret;
	}
	
	static void Write_Rect(int[] arr) {
		// 세로 만들기
		for (int i = arr[1]; i <= arr[3]; i++) {
			checkMap[arr[0]][i] = PATH;
			checkMap[arr[2]][i] = PATH;
		}
		// 가로 만들기
		for (int i = arr[0]; i <= arr[2]; i++) {
			checkMap[i][arr[1]] = PATH;
			checkMap[i][arr[3]] = PATH;
		}
	}
	
	private static int[] dirR = {0,0,-1,1};
	private static int[] dirC = {-1,1,0,0};
	private static int nr,nc;
	static void DFS(int r, int c) {
		checkMap[r][c] = 1;
		for (int i = 0; i < 4; i++) {
			nr = r+dirR[i]; nc = c+dirC[i];
			if(0 <= nr && nr < PROBLEM_MAX && 0 <= nc && nc < PROBLEM_MAX && checkMap[nr][nc] == PATH) {
				DFS(nr,nc);
			}
		}
	}
	
	static class Pair {
		public int a,b;

		public Pair(int a, int b) {
			super();
			this.a = a;
			this.b = b;
		}
		
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