import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000001;

	
	static int NEED,N,M;
	static int[] A = new int[MAX];
	static int[] B = new int[MAX];
	static ArrayList<Integer> pizzaListA = new ArrayList<Integer>();
	static ArrayList<Integer> pizzaListB = new ArrayList<Integer>();
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	
	static void Init() throws IOException {
		NEED = io.inputInt();
		StringTokenizer stk =new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		for (int i = 0; i < N; i++) pizzaListA.add(io.inputInt());
		for (int i = 0; i < M; i++) pizzaListB.add(io.inputInt());
	}
	static long Solve() throws IOException {
		long ret = 0;
		
		//A,B 각각에 대해, 1조각씩, 2조각씩 , 3조각씩 ... n-1조각씩
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum = 0;
			for (int piece = 0; piece < N-1; piece++) {
				sum += pizzaListA.get((i+piece)%N);
				CountUp(A,sum);
			}
			if(i == 0) CountUp(A,sum+pizzaListA.get(N-1));			//All 조각 Sum
		}
		for (int i = 0; i < M; i++) {
			sum = 0;
			for (int piece = 0; piece < M-1; piece++) {
				sum += pizzaListB.get((i+piece)%M);
				CountUp(B,sum);
			}
			if(i == 0) CountUp(B,sum+pizzaListB.get(M-1));
		}
		
		ret += A[NEED];
		ret += B[NEED];
		//각 피자는 자연수이므로, [NEED-1]까지만 확인 한다.
		for (int a = 1; a <= NEED-1; a++) {
			ret += A[a]*B[NEED-a];
		}
		return ret;
	}
	
	
	
	static void CountUp(int[] arr,int value) {
		arr[value]++;
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