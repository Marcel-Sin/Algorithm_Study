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
	static final long MOD = 1000000007l;
	static long DP_SYM[] = new long[101];
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			long ans = AsymValue(N);
			if( ans < 0) ans = (MOD+ans)%MOD;
			System.out.println(ans);
		}
		
	}
	
	static long AsymValue(int n) {
		if (n == 1) return 1;
		if (n == 2) return 0;
		long result = 0;
		// 짝수의 경우
		if(n%2 == 0) {
			result = SymValue(n-1) + SymValue(n-2) - SymValue(n/2) - SymValue( (n-2)/2 );
		}
		// 홀수의 경우
		else {
			result = SymValue(n-1) + SymValue(n-2) - SymValue( (n-1)/2 );
		}
		result = result % MOD;
		return result;
	}
	
	static long SymValue(int n) {
		if(DP_SYM[n] != -1) return DP_SYM[n];
		return DP_SYM[n] = ( SymValue(n-2) + SymValue(n-1) ) % MOD; 
	}
	
	static void Init() throws IOException{
		Arrays.fill(DP_SYM, -1);
		DP_SYM[0] = 0;
		DP_SYM[1] = 1;
		DP_SYM[2] = 2;
		
		N = io.inputInt();
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
