import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;

	static int R,C,ans;
	static int[][] map;
	static int[][] visited = new int[20][20]; 
	
	static int[] dirR = {0,0,-1,1};
	static int[] dirC = {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		R = nextInt(stk); C = nextInt(stk);
		map = new int[R][C];	
		for (int i = 0; i < R; i++) {
			char[] arr = io.inputStr().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = arr[j]-'A';
				visited[i][j] = 0;
			}
		}
	}
	static void Solve() throws IOException {
		ans = 0;
		DFS(0,0,Set_Bit(0,map[0][0]),1);
		System.out.println(ans);
	}
	
	static void DFS(int r, int c,int visit_Data,int counter) throws IOException {
		visited[r][c] = visit_Data;
		
		ans = Max(ans,counter);
		if(26 <= ans) return;
		
		int nr, nc, next_Data;
		for (int i = 0; i < 4; i++) {
			nr = r+dirR[i]; nc = c+dirC[i];
			
			if(0 <= nr && nr < R && 0 <= nc && nc < C) {
				next_Data = Set_Bit(visit_Data, map[nr][nc]);
				
				// 방문 할 곳이 알파벳 중복되지 않고, 방문 할 곳이 이전과 다른 새로운 방식으로 찾은 길인가?
				if( visit_Data != next_Data && visited[nr][nc] != next_Data) {
					// 그러면 가볼만하다.
					DFS(nr,nc,next_Data,counter+1);
				}
			}
		}
	}
	private static int temp;
	static int Set_Bit(int originData ,int pos) {
		temp = 1 << pos;
		return originData | temp;
	}
	static int Unset_Bit(int originData ,int pos) {
		temp = 1 << pos;
		temp = ~temp;
		return originData & temp;
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
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
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