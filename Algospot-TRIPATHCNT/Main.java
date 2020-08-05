import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL,LINE;
	static final int NINF = -999999999;
	static int[][] MAP = new int[100][100];
	static int[][] DP_CASE = new int[100][100];
	static int[][] DP_SUM = new int[100][100];
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve(LINE));
		}
		
	}
	

	static int Solve(int level) {
		int maxValue = 0,count = 0;
		for(int j = 0; j < level; j++) {
			maxValue = Max(maxValue,DP_Solve(level-1,j));
		}
		for(int j = 0; j < level; j++) {
			if(DP_SUM[level-1][j] == maxValue) count += DP_CASE[level-1][j];
		}
		return count;
	}
	
	static int DP_Solve(int row,int col) {
		if(DP_SUM[row][col] != -1) return DP_SUM[row][col];
		if(col == 0) {
			DP_SUM[row][col] = MAP[row][col]+DP_Solve(row-1,col);
			DP_CASE[row][col] = DP_CASE[row-1][col];
			return DP_SUM[row][col];
		}
		else {
			DP_Solve(row-1,col-1);
			DP_Solve(row-1,col);
			if(DP_SUM[row-1][col-1] == DP_SUM[row-1][col]) {
				DP_SUM[row][col] = MAP[row][col]+DP_SUM[row-1][col-1];
				DP_CASE[row][col] = DP_CASE[row-1][col-1]+DP_CASE[row-1][col];
			}
			else if(DP_SUM[row-1][col-1] > DP_SUM[row-1][col]) {
				DP_SUM[row][col] = MAP[row][col]+DP_Solve(row-1,col-1);
				DP_CASE[row][col] = DP_CASE[row-1][col-1];
			}
			else {
				DP_SUM[row][col] = MAP[row][col]+DP_Solve(row-1,col);
				DP_CASE[row][col] = DP_CASE[row-1][col];
			}
			return DP_SUM[row][col];
		}
	}
	
	static void Init() throws IOException{
		LINE = io.inputInt();
		for(int i = 0; i < 100; i++) {
			Arrays.fill(MAP[i], NINF);
			Arrays.fill(DP_SUM[i], NINF);
			Arrays.fill(DP_CASE[i], NINF);			
		}
		for(int i = 0; i < LINE; i++) {
			int j = 0;
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			while(stk.hasMoreTokens()) {
				MAP[i][j] = nextInt(stk);
				DP_SUM[i][j] = -1;
				DP_CASE[i][j] = -1;
				j++;
			}
		}
		DP_SUM[0][0] = MAP[0][0];
		DP_CASE[0][0] = 1;
		
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b)?b:a;
	}
	static long Max(long a, long b) {
		return (a > b)?a:b;
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	static void Display(int[] arr,int limit) {
		System.out.println("요소갯수 : " + arr.length);
		for(int i = 0; i < limit; i++) System.out.print(arr[i]+" ");
		System.out.println();
	}
	static void Display(int[][] arr,int limit) {
		System.out.println("요소갯수 : " + (arr.length*arr[0].length));
		for(int i = 0; i < limit; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
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
	}}
