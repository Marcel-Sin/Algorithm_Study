import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;







public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	
	
	static void Execute() {
		Main mainObj = new Main();
		int ans = mainObj.solve.solution(new int[][] {{0, 3}, {1, 9}, {2, 6}});
		System.out.println(ans);
	}
	
	//import java.util.*;
	class Solution {
		public int solution(int[][] jobs) {
			int currentTime = 0, complete_Time = 0,size = jobs.length, waiting_Time = 0;
			PriorityQueue<Work> pq = new PriorityQueue<Work>();
			PriorityQueue<Work> workGiver = new PriorityQueue<Work>((Work a,Work b) -> {
					if(a.start == b.start) return 0;
					else return (a.start < b.start) ? -1:1;
			});
			// 작업 등록
			for (int i = 0; i < jobs.length; i++) workGiver.add(new Work(jobs[i][0],jobs[i][1]));
			Work work = null;
			// 시간의 흐름에 따른 작업 투입, 처리
			while(!pq.isEmpty() || !workGiver.isEmpty()) {
				// 현재 시간의 작업을 준다.
				while(!workGiver.isEmpty() && currentTime >= workGiver.peek().start) {
					work = workGiver.poll();
					pq.add(work);
				}
				
				// 현재 시간의 작업이 존재한다.
				if(!pq.isEmpty()) {
					work = pq.poll();
					// 작업 시작까지 대기 시간 계산 (음수는 대기가 없었다.)
					waiting_Time = (currentTime - work.start <= 0) ? 0 : currentTime - work.start;
					
					// 작업 시작
					currentTime += work.workTime;
					complete_Time += waiting_Time + work.workTime;
				}
				// 작업이 없으면 시간이 흐른다. (작업 대기 상태됨)
				else currentTime += 1;
			}
			return complete_Time/size;
		}
		class Work implements Comparable<Work>{
			int start,end,workTime;

			public Work(int start, int time) {
				super();
				this.start = start;
				this.workTime = time;
				this.end = start+time;
			}

			@Override
			public int compareTo(Work o) {
				if(this.workTime == o.workTime) {
					if(this.start == o.start) return 0;
					else return (this.start < o.start) ? -1:1;
				}
				else return (this.workTime < o.workTime) ? -1:1;
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