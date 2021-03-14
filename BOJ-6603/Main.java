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

	static boolean testLoop;
	static int N = 6,K;
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int[] output = new int[6];
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		while(true) {
			testLoop = Init();
			if(!testLoop) break;
			Solve();	
		}
	}
	
	static boolean Init() throws IOException {
		StringTokenizer stk =new StringTokenizer(io.inputStr());
		sb = new StringBuilder();
		K = nextInt(stk);
		list.clear();
		if(K == 0) return false;
		while(stk.hasMoreTokens()) list.add(nextInt(stk));
		Collections.sort(list);
		return true;
	}
	static void Solve() throws IOException {
		DFS(-1,0);
		System.out.print(sb.toString());
		System.out.println();
	}
	
	private static int temp;
	static void DFS(int here, int count) throws IOException {
		if(count == N) {
			for (int i = 0; i < output.length; i++) {sb.append(output[i]); sb.append(' ');}
			sb.append('\n');
			return;
		}
		for (int i = here+1; i < list.size(); i++) {
			temp = list.get(i);
			output[count] = temp;
			DFS(i,count+1);
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