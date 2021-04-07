import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;



public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	public Solution solve = new Solution();
	static String[] genres;
	static int[] plays;

	public static void main(String[] args) throws IOException {
		Execute();
	}
	
	
	static void Execute() {
		Main mainObj = new Main();
		int[] ans = mainObj.solve.solution(new int[] {1, 2, 3, 2, 3});
		Display(ans,ans.length);
	}
	
	class Solution {
		public int[] solution(int[] prices) {
			int[] ans = new int[prices.length];
			PriorityQueue<SolveData> pq = new PriorityQueue<Main.Solution.SolveData>();
			for (int i = 0; i < prices.length; i++) {
				SolveData here = new SolveData(i, prices[i], 0);
				while(!pq.isEmpty() &&  here.value < pq.peek().value) {
					SolveData outData = pq.poll();
					ans[outData.idx] = outData.counter+1;
				}
				Iterator<SolveData> iter = pq.iterator();
				while(iter.hasNext()) {
					SolveData temp = iter.next();
					temp.counter++;
				}
				pq.add(here);
			}
			while(!pq.isEmpty()) {
				SolveData outData = pq.poll();
				ans[outData.idx] = outData.counter;
			}
			return ans;
		}
		
		class SolveData implements Comparable<SolveData>{
			public int idx,value,counter;

			public SolveData(int idx, int value, int counter) {
				super();
				this.idx = idx;
				this.value = value;
				this.counter = counter;
			}

			@Override
			public int compareTo(SolveData o) {
				if(this.value == o.value) return 0;
				else return (this.value < o.value)? 1:-1;
			}
			
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