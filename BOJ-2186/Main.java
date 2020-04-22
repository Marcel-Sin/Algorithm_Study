import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int N,M,K;
	static char[][] MAP;
	static char[] TARGET;
	static int TARGET_LEN;
	static int[][][] DP;
	static int SUM = 0;
	public static void main(String[] args) throws IOException {
		Initializing();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(MAP[i][j] == TARGET[0]) SUM += DFS(i,j,0);
			}
		}
		System.out.println(SUM);
	}	

	//DFS 정의 : [i,j]에서 시작한 문자열의 경로의 수 탐색. 시작은 DFS(i,j,0);
	static int DFS(int i, int j, int target_idx) {
		// Basic Condition
		if (DP[i][j][target_idx] != -1) return DP[i][j][target_idx];
		if ( target_idx == TARGET_LEN) return 1;
		
		// Retrieve ABCD
		DP[i][j][target_idx] = 0;
		for(int n = 1; n <= K; n++) {
			if(i-n >= 0 && MAP[i-n][j] == TARGET[target_idx+1]) DP[i][j][target_idx] += DFS(i-n,j,target_idx+1);
			if(i+n < N && MAP[i+n][j] == TARGET[target_idx+1]) DP[i][j][target_idx] += DFS(i+n,j,target_idx+1);
			if(j-n >= 0 && MAP[i][j-n] == TARGET[target_idx+1]) DP[i][j][target_idx] += DFS(i,j-n,target_idx+1);
			if(j+n < M && MAP[i][j+n] == TARGET[target_idx+1]) DP[i][j][target_idx] += DFS(i,j+n,target_idx+1);
		}
		return DP[i][j][target_idx];
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		K = nextInt(stk);
		MAP = new char[N][M];
	
		for(int i = 0; i<N; i++) {
			char[] temp = io.inputStr().toCharArray();
			for(int j = 0; j<M; j++) MAP[i][j] = temp[j];
		}	
		TARGET = (io.inputStr()+" ").toCharArray();
		TARGET_LEN = TARGET.length-2;
		
		DP = new int[100][100][80];
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				Arrays.fill(DP[i][j], -1);
			}
		}
	}
	
	

	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
		}
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
