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
	static final int MAX = 1000;
	
	static int N,M;
	static int[] problem;
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}
	
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		
		problem = new int[N];
		for (int i = 0; i < problem.length; i++) problem[i] = i+1;
	}
	static void Solve() throws IOException {
		//Reverse(problem,0,N);
		while(Next_Permutation(problem) == true) {
			Display(problem, M);
		}
	}
	
	static boolean Next_Permutation(int[] arr) {
		int size = arr.length;
		if(size == 1) return false;
		int i=-1,ii=size-1,j=size-1;
		while(0 < ii && arr[ii-1] > arr[ii]) ii--; 	
		if(ii == 0) return false;
		
		i = ii-1;
			while(arr[i] > arr[j]) j--;
			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			Reverse(arr,ii,size);
			return true;
		
	}
	private static int left,right,temp;
	static void Reverse(int [] arr,int inStart,int exEnd) {
		left = inStart;
		right = exEnd-1;
		while(left < right) {
			temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++; right--;
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