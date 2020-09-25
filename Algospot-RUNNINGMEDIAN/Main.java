import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL,A,B,N;
	static PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a,b) -> (a > b) ? -1:1);
	static PriorityQueue<Integer> minQueue = new PriorityQueue<>((a,b) -> (a < b) ? -1:1);
	
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve(N,A,B));
		}
	}
	
	static class Problem_Creater {
		int a,b;
		long nexter;
		
		public Problem_Creater(int a , int b, int seed) {
			this.a = a;
			this.b = b;
			this.nexter = seed;
		}
		public int next() {
			int ret = (int)nexter;
			nexter = (nexter * a + b)%20090711;
			return ret;
		}
	}
	
	static int Solve(int n,int a, int b) {
		
		int ret = 0;
		Problem_Creater PC = new Problem_Creater(a, b, 1983);
		maxQueue.clear();
		minQueue.clear();
		for(int i = 0; i < n; i++) {
			if(maxQueue.size() == minQueue.size()) {
				maxQueue.add(PC.next());
			} else minQueue.add(PC.next());
			
			if(!maxQueue.isEmpty() && !minQueue.isEmpty() && maxQueue.peek() > minQueue.peek()) {
				int t1 = maxQueue.poll(),t2 = minQueue.poll();
				minQueue.add(t1);
				maxQueue.add(t2);
			}
			
			ret += maxQueue.peek();
			ret %= 20090711;
		}
		
		return ret;
	}

	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk); A = nextInt(stk); B = nextInt(stk);
	}
	
	// ===================== functions for PS =====================
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
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
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