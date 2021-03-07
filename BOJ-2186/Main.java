import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
	static final int PROBLEM_MAX = 101;
	
	static int N,M,K,ans,searchWord_LastIDX;
	
	static char[][] charMap;
	static char[] searchWord;
	
	static int[][][] checkMap;
	static int[][] dirR;
	static int[][] dirC;
	
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}

	static void Init() throws IOException {
		//1 <= K <= 5 이므로
		int[] dr = {0,0,-1,1};
		int[] dc = {-1,1,0,0};
		dirR = new int[6][4]; dirC = new int[6][4];
		for (int k = 1; k <= 5; k++) {
			for (int dir = 0; dir < 4; dir++) {dirR[k][dir] = dr[dir]*k; dirC[k][dir] = dc[dir]*k;	}
		}
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);		M = nextInt(stk);		K = nextInt(stk);
		
		charMap = new char[N][];
		for (int i = 0; i < N; i++) charMap[i] = io.inputStr().toCharArray();
		searchWord = io.inputStr().toCharArray();
		searchWord_LastIDX = searchWord.length-1;
		
		checkMap = new int[N][M][searchWord.length];
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++)
				for (int z = 0; z < searchWord.length; z++)	checkMap[i][j][z] = -1;
	}

	static int Solve() throws IOException {
		ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(charMap[i][j] == searchWord[0]) {
					ans += DFS(i,j,0);
					
				}
			}
		}
		return ans;
	}
	static int DFS(int curR, int curC, int curCount) {
		if(curCount == searchWord_LastIDX) {return 1;}
		int nr,nc,nextCount;
		nextCount = curCount+1;
		checkMap[curR][curC][curCount] = 0;
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i < 4; i++) {
				nr = curR+dirR[k][i]; 
				nc = curC+dirC[k][i];
				if(0 <= nr && nr < N && 0 <= nc && nc < M && searchWord[nextCount] == charMap[nr][nc]) {
					if(checkMap[nr][nc][nextCount] == -1) checkMap[curR][curC][curCount] += DFS(nr,nc,nextCount);
					else checkMap[curR][curC][curCount] +=checkMap[nr][nc][nextCount];
				}
			}
		}
		return checkMap[curR][curC][curCount];
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