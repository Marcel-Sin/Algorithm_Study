import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;

	static int N,S,ans;
	static int[] arr;

	
	public static void main(String[] args) throws IOException {
		Init();
		Solve(0,NINF,true);
		System.out.println(ans);
	}
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		S = nextInt(stk);
		stk = new StringTokenizer(io.inputStr());
		arr = new int[N];
		int i = 0;
		while(stk.hasMoreTokens()) arr[i++] = nextInt(stk);
	}
	static void Solve(int here,int sum,boolean changed) throws IOException {
		if(changed && sum == S) ans++;
		if(here == N) return;
		if(sum == NINF) {
			Solve(here+1,NINF,false);
			Solve(here+1,arr[here],true);
		}
		else {
			Solve(here+1,sum,false);
			Solve(here+1,sum+arr[here],true);
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