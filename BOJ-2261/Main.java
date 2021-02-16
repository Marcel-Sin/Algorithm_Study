import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 100001;
	
	static int N;
	static boolean interrupt;
	static ArrayList<Pair> problem;
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve(0,N-1));
	}
	
	static void Init() throws IOException{
		problem = new ArrayList<Main.Pair>(MAX);
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			Pair pair = new Pair(nextInt(stk),nextInt(stk),i);
			problem.add(pair);
		}
		Collections.sort(problem);
		interrupt = false;
	}
	static int Solve(int low, int high) throws IOException{
		int ret = INF;
		int size = high-low+1;
		// 3개 이하 범위
		if(size <= 3) {
			if(size == 1) return ret;
			if(size > 1) ret = GetDist(problem.get(low),problem.get(low+1));
			if(size > 2) {
				ret = Min(ret,GetDist(problem.get(low),problem.get(low+2)));
				ret = Min(ret,GetDist(problem.get(low+1),problem.get(low+2)));
			}
			return ret;
		}
		// 3개 이상 범위
		else {
			int mid = (low+high)/2;
			ret = Min(ret, Solve(low,mid));
			ret = Min(ret, Solve(mid+1,high));
			int d = (int)Math.sqrt(ret)+1;
			if(ret == 0) return 0;
			
			// [low,high]범위에서 mid.x +-d 범위를 가져온다.
			ArrayList<Pair> ylist = new ArrayList<Main.Pair>();
			Pair midPair = problem.get(mid);
			int minRangeValue = midPair.x-d;
			int maxRangeValue = midPair.x+d;
			
			for (int i = low; i <= high; i++) {
				Pair pair = problem.get(i);
				if(minRangeValue <= pair.x && pair.x <= maxRangeValue) {
					ylist.add(problem.get(i));
				}
			}
			Collections.sort(ylist,new Y_Comparator());
			int iterA = 0, iterB = 0, ylist_size = ylist.size();
			while(iterA < ylist_size) {
				Pair here = ylist.get(iterA);
				iterB = iterA+1;
				while(iterB < ylist_size) {
					Pair there = ylist.get(iterB++);
					if(here.y+d < there.y) break;
					ret = Min(ret,GetDist(here, there));
				}
				iterA++;
			}
			return ret;
		}
	}
	static public int GetDist(Pair a,Pair b) {
		return (b.x-a.x)*(b.x-a.x)+(b.y-a.y)*(b.y-a.y);
	}
	
	static class Pair implements Comparable<Pair>{
		public int x,y,id;
		public Pair(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
		@Override
		public int compareTo(Pair o) {
			if(this.x == o.x) {
				if(this.y == o.y) return 1;
				else return (this.y < o.y) ? -1:1;
			}
			else return (this.x < o.x) ? -1:1;
		}
	}

	static class Y_Comparator implements Comparator<Pair> {
		@Override
		public int compare(Pair o1, Pair o2) {
			if(o1.y == o2.y) return (o1.id < o2.id) ? -1:1; 
			else return (o1.y < o2.y) ? -1:1;
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
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
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
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ",arr[i][j]);
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