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

	static int N;
	static boolean interrupt;
	static int[][] map = new int[9][9];
	static ArrayList<Integer>[][] possible = new ArrayList[9][9];
	static ArrayList<Pair> slotList = new ArrayList<Main.Pair>();

	public static void main(String[] args) throws IOException {
		Init();
		DFS(0);
	}
	
	static void Init() throws IOException {
		for (int i = 0; i < 9; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 9; j++) map[i][j] = nextInt(stk);
		}
		N = 0;
		interrupt = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if(map[i][j] == 0) {
					Possible_Check(i, j);
					N++;
					slotList.add(new Pair(i,j));
				}
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	static void Possible_Check(int r, int c) {
		boolean[] arr = new boolean[10];
		Arrays.fill(arr, true);
		// Row 체크
		for (int i = 0; i < 9; i++) arr[map[r][i]] = false;
		// Col 체크
		for (int i = 0; i < 9; i++) arr[map[i][c]] = false;
		
		// 9칸 파트 체크
		int row = (r/3)*3, col = (c/3)*3;
		for (int i = row; i < row+3; i++) {
			for (int j = col; j < col+3; j++) {
				arr[map[i][j]] = false;
			}
		}
		possible[r][c] = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) if(arr[i]) possible[r][c].add(i);
		
	}
	
	static void DFS(int slot) throws IOException {
		if(interrupt == true) return;
		if(slot == N) {
			Display(map,9);
			interrupt = true;
			return;
		}
		Pair here = slotList.get(slot);
		for (int i = 0; i < possible[here.a][here.b].size(); i++) {
			int nextInput = possible[here.a][here.b].get(i);
			if( !interrupt && Check_Input(here.a,here.b,nextInput)) {
				map[here.a][here.b] = nextInput;
				DFS(slot+1);
				map[here.a][here.b] = 0;
			}
		}
	}
	static boolean Check_Input(int r,int c,int number) {
		// Row 체크
		for (int i = 0; i < 9; i++) if(map[r][i] == number) return false;
		
		// Col 체크
		for (int i = 0; i < 9; i++) if(map[i][c] == number) return false;
		
		// 9칸 파트 체크
		int row = (r/3)*3, col = (c/3)*3;
		for (int i = row; i < row+3; i++) {
			for (int j = col; j < col+3; j++) {
				if(map[i][j] == number) return false;
			}
		}
		return true;
	}
	
	static class Pair {
		public int a,b;
		public Pair(int a, int b) {
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