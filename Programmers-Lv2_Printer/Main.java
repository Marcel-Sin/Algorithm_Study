import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
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
		int ans = mainObj.solve.solution(new int[] {1, 1, 9, 1, 1, 1},0);
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		public int solution(int[] priorities, int location) {
			int ret = 0,count = 0;
			int[] result = new int[priorities.length];
			Queue<PrintWork> wait = new LinkedList<PrintWork>();
			for (int i = 0; i < priorities.length; i++) wait.add(new PrintWork(i,priorities[i]));
			
			while(!wait.isEmpty()) {
				// 인쇄 대상 가져오기
				Getting_PrintWork(wait);
				count++;
				PrintWork print = wait.poll();
				result[print.idx] = count;
			}
			return result[location];
		}
		
		void Getting_PrintWork(Queue<PrintWork> wait) {
			// 가장 가까우면서 높은 우선순위를 찾는다.
			int maxPriority = -1;
			PrintWork targetWork = null;
			Iterator<PrintWork> iter = wait.iterator();
			while(iter.hasNext()) {
				PrintWork iterWork = iter.next();
				if(iterWork.priority > maxPriority) {
					maxPriority = iterWork.priority;
					targetWork = iterWork;
				}
			}
			// 나머지를 뒤로 옮긴다.
			while(wait.peek() != targetWork) wait.add(wait.poll());
		}
		
		class PrintWork{
			int idx,priority;

			public PrintWork(int idx, int priority) {
				super();
				this.idx = idx;
				this.priority = priority;
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