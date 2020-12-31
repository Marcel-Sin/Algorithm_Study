import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE / 4;
	static final int MAX_SIZE = 100001;
	
	static int N;
	static ArrayList<Pair> problem = new ArrayList<Main.Pair>();
	
	
	public static void main(String[] args) throws IOException {
		problem.ensureCapacity(MAX_SIZE);
		Init();
		System.out.println(DC(0,N-1));
	}

	static void Init() throws IOException {
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			problem.add(new Pair(nextInt(stk),nextInt(stk)));
		}
		Collections.sort(problem, new X_Comparator());
	}
	
	static int DC(int low, int high) {
		if(low >= high) return INF;
		else if(high-low == 1) return Dist(problem.get(low),problem.get(high));
		
		int mid = (low+high)/2;
		
		int leftMin = DC(low,mid);
		int rightMin = DC(mid+1,high);
		int ans = Min(leftMin,rightMin);
		
		int d = (int)Math.sqrt(ans)+1;
		int minX = problem.get(mid).x - d;
		int maxX = problem.get(mid).x + d;
		
		//이 트리는 y에 대해서 정렬 되어있음.
		TreeSet<Pair> tree = new TreeSet<Main.Pair>();
		for (int i = low; i <= high; i++) {
			Pair pair = problem.get(i);
			if(minX <= pair.x && pair.x <= maxX) tree.add(pair);
		}
		Iterator<Pair> mainIter = tree.iterator();
		while(mainIter.hasNext()) {
			Pair selected_Pair = mainIter.next();
			Pair toBound = new Pair(0,selected_Pair.y+d);
			SortedSet<Pair> stree = tree.subSet(selected_Pair, toBound);
			Iterator<Pair> iter = stree.iterator();
			int check = 0;
			while(check < 6 && iter.hasNext()) {
				Pair cmp = iter.next();
				if(cmp == selected_Pair) continue;
				int dist = Dist(selected_Pair,cmp);
				ans = Min(ans,dist);
				check++;
			}
		}
		return ans;
	}
	
	
	static int Dist(Pair a, Pair b) {
		int xx = (b.x-a.x), yy = (b.y-a.y);
		xx *= xx;	yy *= yy;
		return xx+yy;
	}
	
	static class X_Comparator implements Comparator<Pair> {

		@Override
		public int compare(Pair o1, Pair o2) {
			if(o1.x == o2.x) return 0;
			else return (o1.x < o2.x) ? -1:1;
		}
		
	}
	static class Pair implements Comparable<Pair>{
		public int x,y;

		public Pair(int a, int b) {
			super();
			this.x = a;
			this.y = b;
		}

		@Override
		public int compareTo(Pair o) {
			if(this.y == o.y) {
				if(this.x == o.x) return 0;
				else return (this.x < o.x) ? -1:1;
			}
			else return (this.y < o.y) ? -1:1;
		}


		
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