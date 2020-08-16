import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL,N;
	static final int MOD = 10000000;
	static int[][] DP = new int[101][101];
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			if(N == 1) System.out.println(0);
			else System.out.println(Solve(N));
		}
		
	}
	
	static int Solve(int n) {
		int ans = 0;
		for(int i = 1; i <= n; i++) { 
			ans += Poly(n,i);
			ans %= MOD;
		}
		return ans;
	}
	
	static int Poly(int n, int top) {
		if(n == top) return 1;
		if(DP[n][top] != -1) return DP[n][top];
		int sum = 0, rest = n-top;
		for(int x = 1; x <= rest; x++) {
			sum += Poly(rest,x)*(top+x-1);
			sum %= MOD;
		}
		DP[n][top] = sum;
		return DP[n][top];
	}
	
	
	static void Init() throws IOException {
		N = io.inputInt();
		for (int i = 0; i < DP.length; i++) {
			Arrays.fill(DP[i], -1);
		}
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
