import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
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
		int ans = mainObj.solve.solution(2,10,new int[] {7,4,5,6});
		System.out.println(ans);
	}
	
	class Solution {
		public int solution(int bridge_length, int weight, int[] truck_weights) {
			int sec = 1, remainWeight = weight;
			Queue<Truck> wait = new LinkedList<Truck>();
			Queue<Truck> bridge = new LinkedList<Truck>();
			
			for (int i = 0; i < truck_weights.length; i++) wait.add(new Truck(truck_weights[i],0));
			Truck lastTruck = wait.poll();
			remainWeight -= lastTruck.truckWeight;
			bridge.add(lastTruck); 
			
			while(!wait.isEmpty() || !bridge.isEmpty()) {
				sec += 1;
				Bridge_Truck_Move(bridge);
				while( !bridge.isEmpty() && bridge.peek().stateValue >= bridge_length) {
					remainWeight += bridge.poll().truckWeight;
				}
				if(!wait.isEmpty() && wait.peek().truckWeight <= remainWeight) {
					lastTruck = wait.poll();
					remainWeight -= lastTruck.truckWeight;
					bridge.add(lastTruck); 
				}

			}
			return sec;
		}
		
		void Bridge_Truck_Move(Queue<Truck> queue) {
			Iterator<Truck> iter = queue.iterator();
			while(iter.hasNext()) {
				Truck truck = iter.next();
				truck.stateValue += 1;
			}
		}
		class Truck {
			public int truckWeight;
			public int stateValue;
			public Truck(int truckWeight, int stateValue) {
				super();
				this.truckWeight = truckWeight;
				this.stateValue = stateValue;
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