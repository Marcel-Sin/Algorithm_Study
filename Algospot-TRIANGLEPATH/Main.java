import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int[][] MAP = new int[101][101];
	static int[][] DP = new int[101][101];;
	static int BOUNDER;
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(TrianglePath(0, 0));
		}
	} 

	static int TrianglePath(int row,int col) {
		if(BOUNDER <= row || BOUNDER <= col) return 0;
		if(DP[row][col] != -1) return DP[row][col];
		DP[row][col] = Max(TrianglePath(row+1,col+1), TrianglePath(row+1,col)) + MAP[row][col];
		return DP[row][col];
	}
	
	static void Init() throws IOException{
		int size = io.inputInt();
		BOUNDER = size;
		for (int i = 0; i < size; i++) {
			Arrays.fill(MAP[i], 0);
			Arrays.fill(DP[i], -1);
			int j = 0;
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			while(stk.hasMoreTokens()) MAP[i][j++] = nextInt(stk);
		}
		
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
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
