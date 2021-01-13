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
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 100001;
	
	
	static ArrayList<Pair> list = new ArrayList<Main.Pair>();
	static int N;
	
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}

	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			list.add(new Pair(stk.nextToken(),nextInt(stk),nextInt(stk),nextInt(stk)));
		}
	}

	static void Solve() {
		Collections.sort(list);
        StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
            sb.append('\n');
		}
        System.out.print(sb.toString());
	}
	
	static class Pair implements Comparable<Pair>{
		int a,b,c;
		String name;
		
		

		public Pair(String name,int a, int b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
			this.name = name;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.a == o.a) {
				if(this.b == o.b) {
					if(this.c == o.c) return this.name.compareTo(o.name);
					else return (this.c > o.c) ? -1:1;
				}
				else return (this.b < o.b) ? -1:1;
			}
			else return (this.a > o.a) ? -1:1;
		}
		@Override
		public String toString() {
			return this.name;
		}
		
		
	}
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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
	static void Display(int[] arr, int limit) {
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
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
	}
}