import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;

	
	static int N,SIZE;
	static ArrayList<Integer> list = new ArrayList<Integer>(500000);
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException {
		N = io.inputInt();
		boolean[] notPrime = new boolean[N+1];
		int root = Math.abs(N)+1;
		notPrime[1] = true;
		for (int i = 2; i <= root; i++) {
			for (int j = i+i; j <= N; j+=i) {
				notPrime[j] = true;
			}
		}
		for (int i = 2; i <= N; i++) {
			if(!notPrime[i]) list.add(i);
		}
		SIZE = list.size();
	}
	static int Solve() throws IOException {
		int ret = 0;
		int i=0,j=0,sum=0;
		while(true) {
			if(sum < N && j < SIZE) sum += list.get(j++);
			else if(sum >= N && i < j) {
				sum -= list.get(i++);
			}
			if(sum == N) ret++;
			if(j == SIZE && sum < N) break;
		}
		return ret;
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