import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
		int[] ans = mainObj.solve.solution(new int[] {95, 90, 99, 99, 80, 99},new int[] {1, 1, 1, 1, 1, 1});
		Display(ans,ans.length);
	}
	
	//import java.util.*;
	class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			// 작업 입력
			Queue<Work> queue = new LinkedList<Work>();
			for (int i = 0; i < progresses.length; i++) {
				queue.add(new Work(i,progresses[i],speeds[i]));
			}
			
			while(!queue.isEmpty()) {
				// 가장 앞 작업 완료까지 필요량 계산
				Work work = queue.peek();
				int required_Day = (100-work.progress)/work.speed;
				if((100-work.progress)%work.speed != 0) required_Day++;

				// 작업 실시
				Working(queue,required_Day);
				
				// 릴리즈
				list.add(Release(queue));
			}
			int[] ans = new int[list.size()];
			for (int i = 0; i < ans.length; i++) ans[i] = list.get(i);
			return ans;
		}
		
		void Working(Queue<Work> workQueue,int workDay) {
			Iterator<Work> iter = workQueue.iterator();
			while(iter.hasNext()) {
				Work work = iter.next();
				work.progress += work.speed*workDay;
			}
		}
		int Release(Queue<Work> workQueue) {
			int ret = 0;
			while(!workQueue.isEmpty() && workQueue.peek().progress >= 100) {
				workQueue.poll();
				ret++;
			}
			return ret;
		}
		class Work {
			int number,progress,speed;


			public Work(int number, int progress, int speed) {
				super();
				this.number = number;
				this.progress = progress;
				this.speed = speed;
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