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
	static TreeSet<Pair> candidate = new TreeSet<Main.Pair>();

	
	public static void main(String[] args) throws IOException {
		problem.ensureCapacity(MAX_SIZE);
		Init();
		System.out.println(LineSweep());
	}

	static void Init() throws IOException {
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			problem.add(new Pair(nextInt(stk),nextInt(stk)));
		}
		Collections.sort(problem, new X_Comparator());
	}
	
	static int LineSweep() {
		//최소 2개
		candidate.add(problem.get(0));
		candidate.add(problem.get(1));
		int ans = Dist(problem.get(0),problem.get(1));
		
		//이 라인은 X축에 대해서 앞으로 전진한다. (뒤쪽 영역 d이하에 대한 점들이 점차 제거됨)
		int sweepLine = 0, x = 0,dist = 0;
		for (int here = 2; here < N; here++) {
			//이 지점은 스윕라인 전진하는 쪽 점이다. (아직 전진안함)
			Pair herePoint = problem.get(here);
			
			// 스윕 라인 전진을 위하여, X축에 대하여 스윕 기준선과 거리를 본 후 미달 후보를 제거한다. [1차 가지치기]
			while(sweepLine < here) {
				Pair backPoint = problem.get(sweepLine);
				x = herePoint.x - backPoint.x;
				if(x*x > ans) {
					candidate.remove(backPoint);
					sweepLine++;
				}
				//아직 스윕 라인이 정답 가능한 X 범위 내에 존재한다.
				else break;
			}
			
			// 다음은 현재 지점에서 y+d y-d 영역의 후보점들을 검사한다. [+-d 2차 가지치기]
			dist = (int)Math.sqrt(ans)+1;
			Pair fromBound = new Pair(dist,herePoint.y-dist);
			Pair toBound = new Pair(dist,herePoint.y+dist);
			SortedSet<Pair> tree = candidate.subSet(fromBound, toBound);
			Iterator<Pair> iter = tree.iterator();
			while(iter.hasNext()) {
				Pair iterPoint = iter.next();
				dist = Dist(iterPoint,herePoint);
				if(dist < ans) ans = dist;
			}
			candidate.add(herePoint);
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