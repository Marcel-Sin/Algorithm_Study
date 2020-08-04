import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int NUM;
	static int[] DP = new int[31];
	public static void main(String[] args) throws IOException {
		NUM = io.inputInt();
		Init();
		System.out.println(DP_Solve(NUM));
	}
	
	static int DP_Solve(int n) {
		if(n < 0) return 0;
		if(DP[n] != -1) return DP[n];
		int count = 0;
		count += 3*DP_Solve(n-2);
		for(int x = 4; n-x >= 0; x+= 2) {
			count += 2*DP_Solve(n-x);
		}
		DP[n] = count;
		return DP[n];
	}
	
	static void Init() throws IOException{
		Arrays.fill(DP, -1);
		DP[0] = 0; //Only for the case, 4 <= n
		for(int i = 1; i < DP.length; i+=2) DP[i] = 0;
		DP[2] = 3;
		DP[4] = 11;
		
		
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
