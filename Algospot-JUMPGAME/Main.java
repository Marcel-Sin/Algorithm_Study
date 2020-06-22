import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int[][] MAP;
	static int[][] DP;	
	static int SIZE; 
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			int ans = DFS(0,0);
			if(ans == 1) System.out.println("YES");
			else System.out.println("NO");
		}
	} 
		
	static int DFS(int curR, int curC) {
		if(MAP[curR][curC] == 0) return 1;
		if(DP[curR][curC] != -1) return DP[curR][curC];
		int walkCount = MAP[curR][curC], way1 = 0, way2 = 0;
		if(curR+walkCount < SIZE) way1 = DFS(curR+walkCount,curC);
		if(curC+walkCount < SIZE) way2 = DFS(curR,curC+walkCount);
		DP[curR][curC] = Max(way1,way2);
		return DP[curR][curC];
	}
	
	
	
	
	static void Init() throws IOException{
		SIZE = io.inputInt();	
		MAP = new int[SIZE][SIZE];
		DP = new int[SIZE][SIZE];
		for(int a = 0; a < SIZE; a++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int b = 0; stk.hasMoreTokens(); b++) MAP[a][b] = nextInt(stk);
			Arrays.fill(DP[a], -1);
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
