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
	static int N,M;
	static int[] PROBLEM;
	static long[][] DP = new long[100][11];
	static int[][] DP_ERROR = new int[100][101];
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Arrays.sort(PROBLEM);
			System.out.println(DP_Solve(0,M));
		}
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk); 
		M = nextInt(stk);
		PROBLEM = new int[N];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) PROBLEM[i] = nextInt(stk);
		for(int i = 0; i < DP_ERROR.length; i++) {			
			Arrays.fill(DP_ERROR[i],-1);
			Arrays.fill(DP[i],-1);
		}
	}
	
	static int Error(int inFrom, int exTo) {
		if(exTo-inFrom <= 1) return 0;
		if(DP_ERROR[inFrom][exTo] != -1) return DP_ERROR[inFrom][exTo];
		
		int minSum = Integer.MAX_VALUE, first = PROBLEM[inFrom], last = PROBLEM[exTo-1];
		for(int n = first; n <= last; n++) {
			int sum = 0, x = 0;
			for(int idx = inFrom; idx < exTo; idx++) {
				x = Math.abs(PROBLEM[idx] - n);
				sum += x*x;
			}
			minSum = Min(sum,minSum);
		}
		DP_ERROR[inFrom][exTo] = minSum;
		return DP_ERROR[inFrom][exTo];
	}
	
	static long DP_Solve(int from, int piece) {
		if(N-from <= piece) return 0;
		if(piece < 0 || N <= from) return Integer.MAX_VALUE;
		
		if(DP[from][piece] != -1) return DP[from][piece];
		long minSum = Integer.MAX_VALUE;
		for(int size = 1; (from+size) <= N; size++) {
			minSum = Min(minSum, Error(from,from+size)+DP_Solve(from+size,piece-1)  );
		}
		DP[from][piece] = minSum;
		return DP[from][piece];
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
	static void Display(int[] arr) {
		System.out.println("요소갯수 : " + arr.length);
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i]+" ");
		System.out.println();
	}
	static void Display(int[][] arr) {
		System.out.println("요소갯수 : " + (arr.length*arr[0].length));
		for(int i = 0; i < arr.length; i++) {
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
