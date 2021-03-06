import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int PROBLEM_MAX = 201;
	
	static int problem;
	static WaterCup[] cup = new WaterCup[3];
	static Queue<Integer> queue = new LinkedList<Integer>();
	static HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
	static TreeSet<Integer> ansList = new TreeSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}

	static void Init() throws IOException {
		int[] arr = new int[3];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < arr.length; i++) arr[i] = nextInt(stk);
		
		problem = 0;
		problem = SetWater(problem, 0, 0);
		problem = SetWater(problem, 1, 0);
		problem = SetWater(problem, 2, arr[2]);
		
		cup[0] = new WaterCup(0, arr[0]);
		cup[1] = new WaterCup(0, arr[1]);
		cup[2] = new WaterCup(arr[2], arr[2]);
	}
	private static int giveAbleAmount,nextData;
	static void Solve() throws IOException {
		queue.add(problem);
		visited.put(problem, true);
		
		while(!queue.isEmpty()) {
			int hereData = queue.poll();
			cup[0].amount = GetWater(hereData, 0);
			cup[1].amount = GetWater(hereData, 1);
			cup[2].amount = GetWater(hereData, 2);
			if(cup[0].amount == 0) ansList.add(cup[2].amount);
			for (int selecter = 0; selecter < cup.length; selecter++) {
				for (int to = 0; to < cup.length; to++) {
					if(selecter == to) continue;
					giveAbleAmount = cup[selecter].GiveAmount(cup[to]);
					cup[selecter].MinusAmount(giveAbleAmount);
					cup[to].PlusAmount(giveAbleAmount);
					nextData = SetWaterData(cup[0], cup[1], cup[2]);
					if(visited.containsKey(nextData) == false) {
						visited.put(nextData, true);
						queue.add(nextData);
					}
					cup[selecter].PlusAmount(giveAbleAmount);
					cup[to].MinusAmount(giveAbleAmount);
				}
			}
		}
		Iterator<Integer> iter = ansList.iterator();
		while(iter.hasNext()) System.out.print(iter.next()+" ");
	}
	
	private static int temp;
	static int GetWater(int data,int pos) {		
		pos = (2-pos)*10;
		temp = 0b1111111111 << pos;
		temp = data & temp;
		temp = temp >> pos;
		return temp;
	}
	static int SetWater(int data,int pos,int value) {		
		pos = (2-pos)*10;
		temp = 0b1111111111 << pos;
		temp = ~temp;
		temp = data & temp;
		value = value << pos;
		return temp | value;
	}
	static int SetWaterData(WaterCup a,WaterCup b,WaterCup c) {		
		int ret = 0;
		ret = SetWater(ret, 0, a.amount);
		ret = SetWater(ret, 1, b.amount);
		ret = SetWater(ret, 2, c.amount);
		return ret;
	}
	
	static class WaterCup {
		public int amount,volume;
		public WaterCup(int amount, int volume) {
			this.amount = amount;
			this.volume = volume;
		}
		public int GiveAmount(WaterCup to) {
			if(this.amount == 0) return 0;
			int emptySpace = to.volume-to.amount;
			int giveAmount = (emptySpace < this.amount) ? emptySpace:this.amount;
			return giveAmount;
		}
		public void PlusAmount(int value) {
			amount+=value;
		}
		public void MinusAmount(int value) {
			amount-=value;
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
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
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