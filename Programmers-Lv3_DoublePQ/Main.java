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
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	public Solution solve = new Solution();

	public static void main(String[] args) throws IOException {
		Execute();
	}
	
	
	static void Execute() {
		Main mainObj = new Main();
		int[] ans = mainObj.solve.solution(new String[] {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"});
		Display(ans, ans.length);
	}
	
	//import java.util.*;
	class Solution {
		public int[] solution(String[] operations) {
			
			PriorityQueue<Qdata> maxQueue = new PriorityQueue<Qdata>(new Max_Comparator());
			PriorityQueue<Qdata> minQueue = new PriorityQueue<Qdata>(new Min_Comparator());
			
			for (int i = 0; i < operations.length; i++) {
				char command = operations[i].charAt(0);
				int operand = Integer.parseInt(operations[i].substring(2));
				switch(command) {
					case 'I' :
						Insert_Number(maxQueue,minQueue,operand);
						break;
					case 'D' :
						if(operand < 0) Delete_Min(maxQueue, minQueue);  
						else Delete_Max(maxQueue, minQueue);
						break;
				}
			}
			
			if(maxQueue.isEmpty()) return new int[] {0,0};
			else return new int[] {maxQueue.peek().value , minQueue.peek().value};
		}
		
		void Insert_Number(PriorityQueue<Qdata> maxQueue,PriorityQueue<Qdata> minQueue,int value) {
			Qdata data = new Qdata(value);
			maxQueue.add(data);
			minQueue.add(data);
		}
		
		void Delete_Min(PriorityQueue<Qdata> maxQueue,PriorityQueue<Qdata> minQueue) {
			if(minQueue.isEmpty()) return;
			Qdata deletedQdata = minQueue.poll();
			maxQueue.remove(deletedQdata);
		}
		void Delete_Max(PriorityQueue<Qdata> maxQueue,PriorityQueue<Qdata> minQueue) {
			if(minQueue.isEmpty()) return;
			Qdata deletedQdata = maxQueue.poll();
			minQueue.remove(deletedQdata);
		}
		
		
		class Qdata{
			public int value;
			public Qdata(int value) {
				super();
				this.value = value;
			}
		}
		class Min_Comparator implements Comparator<Qdata> {
			@Override
			public int compare(Qdata o1, Qdata o2) {
				if(o1.value == o2.value) return 0;
				else return (o1.value < o2.value) ? -1:1;
			}
		}
		class Max_Comparator implements Comparator<Qdata> {
			@Override
			public int compare(Qdata o1, Qdata o2) {
				if(o1.value == o2.value) return 0;
				else return (o1.value > o2.value) ? -1:1;
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