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
	static long NEGINF = -9999999999l;
	static long[] A;
	static long[] B;
	static int[][] DP = new int[101][101];
	//DP[a][b] 정의 : A[a]번째로 시작 증가수열 [+] B[b] 번째로 시작 증가수열
	static int SIZE_A;
	static int SIZE_B;
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			int ans = 0;
			for(int a = 0; a < SIZE_A; a++) {
				for(int b = 0; b < SIZE_B; b++) {
					ans = Max(ans,DP_Solve(a,b));
				}
			}
			System.out.println(DP[0][1]);
			System.out.println(ans);
		}
	} 
	
	static int DP_Solve(int posA, int posB) {
		if(DP[posA][posB] != -1) return DP[posA][posB];
	
		int count = 2;
		long maxValue = Max(A[posA],B[posB]);
		for(int i = posA+1; i < SIZE_A; i++) {
			if( maxValue < A[i]) count = Max(count, DP_Solve(i,posB)+1);
		}
		for(int i = posB+1; i < SIZE_B; i++) {
			if( maxValue < B[i]) count = Max(count, DP_Solve(posA,i)+1);
		}
		DP[posA][posB] = count;
		return DP[posA][posB];
	}
	
	
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		SIZE_A = nextInt(stk);
		SIZE_B = nextInt(stk);
		A = new long[SIZE_A];
		B = new long[SIZE_B];
		
		for(int i = 0; i < DP.length; i++) Arrays.fill(DP[i], -1);
		
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) A[i] = nextInt(stk);
		
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) B[i] = nextInt(stk);
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
