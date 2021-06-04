import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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
		int ans = mainObj.solve.solution(4,5,new int[][] {{1140, 1180}, {1160, 1170}, {1180, 1190}, {1210, 1300}, {1320, 1380}});
		System.out.println(ans);
		// Display(ans, ans.length);
	}

	// import java.util.*;
	class Solution {
		int N,M;
		int[][] map;
		public int solution(int n, int m, int[][] timetable) {
			N = n; M = m;
			map = new int[N][N];
			int max_Person = Max_Overlaped_Clients(timetable);
			if(n == 1) return 0;
			else if(max_Person >= n*n) return 1;
			else return Get_MaxGap(max_Person);
		}
		
		public int Max_Overlaped_Clients(int[][] table) {

			Pair[] timeList = new Pair[table.length];
			for (int i = 0; i < table.length; i++) {
				timeList[i] = new Pair(table[i][0],table[i][1]);
			}
			Arrays.sort(timeList);
			
			int max = 1, counter = 1;
			for (int i = 0; i < timeList.length; i++) {
				counter = 1;
				for (int j = 0; j < timeList.length; j++) {
					if(i == j) continue;
					if(timeList[j].a <= timeList[i].b && timeList[i].b <= timeList[j].b){
						counter++;
					}
				}
				max = (max < counter) ? counter:max;
			}
			return max;
		}
		public int Get_MaxGap(int people) {
			if(people == 1) return 0;
			int maxDist = 0;
			for(int dist = (N-1)*2; dist > 0; dist--) {
				int possiblePeople = Max_People(dist);
				if(possiblePeople >= people) return dist;
			}
			return 0;
		}
		public int Max_People(int targetDistance) {
			int maxPeople = 1, gap = 0;
			if(targetDistance == 1) return N*N;
			else if(targetDistance == 2) return N*N/2 + 1;
			int x=0,y=0;
			for (int start = 0; start < map.length; start++) {
				//Clear
				for (int i = 0; i < map.length; i++) Arrays.fill(map[i], 0);	
				int counter = 1;
				map[0][start] = 1;
				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map.length; j++) {
						if(Check(i,j,targetDistance) == true) {
							map[i][j] = 1;
							counter++;
						}
					}
				}
				maxPeople = (maxPeople < counter) ? counter:maxPeople;
			}
			return maxPeople;
		}
		public boolean Check(int r, int c, int distance) {
			int tr, tc, d = 0;
			distance--;
			int row=r-distance;
			while(0 <= d) {
				if(0 <= row && row < map.length) {
					for (int j = c-d; j <= c+d; j++) {
						if(j < 0 || map.length <= j) continue;
						if(map[row][j] == 1) return false;
					}	
				}
				if(row < r) d++;
				else d--;
				row++;
			}
			return true;
		}

		class Pair implements Comparable<Pair> {
			public int a,b;

			public Pair(int a, int b) {
				super();
				this.a = a;
				this.b = b;
			}

			@Override
			public int compareTo(Pair o) {
				if(this.b == o.b) return 0;
				return (this.b < o.b) ? -1:1;
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
				System.out.printf(arr[i][j] + " ");
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