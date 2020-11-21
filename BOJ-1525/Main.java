import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 

	static int TOTAL;
	static HashMap<Long, Integer> checkMap = new HashMap<Long, Integer>(); 
	static Queue<int[][]> queue = new LinkedList<int[][]>();
	static int[][] problem = new int[3][3]; 
	static long finish;
	static int[][] fourDir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	
	
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(BFS());
		
	}
	static void Init() throws IOException{
		StringTokenizer stk;
		for(int i = 2; i >= 0; i--) {
			stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 3; j++) {
				problem[i][j] = nextInt(stk);
			}
		}
		int[][] ans = {{7,8,0},{4,5,6},{1,2,3}};
		finish = Array_Hashcode(ans);
	}

	// 첫시도, 매우 직관적인 방법 2차원 배열 사용
	static int BFS() {
		if(Array_Hashcode(problem) == finish) return 0;
		
		queue.add(problem);
		checkMap.put(Array_Hashcode(problem),0);
		
		while(!queue.isEmpty()) {
			int[][] here = queue.poll();
			int curCost = checkMap.get(Array_Hashcode(here));
			
			ArrayList<int[][]> adj = GetAdjacent(here);
			for(int i = 0; i < adj.size(); i++) {
				int[][] adjArr = adj.get(i);
				long arrCode = Array_Hashcode(adjArr);
				if(checkMap.containsKey(arrCode) == false) {
					if(arrCode == finish) return curCost+1;
					checkMap.put(arrCode,curCost+1);
					queue.add(adjArr);
				}
			}
		}
		
		return -1;
	}
	
	static long Array_Hashcode(int[][] arr) {
		long ret = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				ret += arr[i][j];
				ret = ret << 4;
			}
		}
		return ret;
	}
	
	static ArrayList<int[][]> GetAdjacent(int[][] here) {
		ArrayList<int[][]> ret = new ArrayList<int[][]>();
		
		// 0의 위치 찾기
		int curR = 0,curC = 0;
		for (int i = 0; i < here.length; i++) {
			for (int j = 0; j < here.length; j++) {
				if(here[i][j] == 0) {
					curR = i; curC = j;
					break;
				}
			}
		}
		
		for (int k = 0; k < fourDir.length; k++) {
			int nr = curR+fourDir[k][0], nc = curC+fourDir[k][1];
			if(nr >= 0 && nc >= 0 && nr < here.length && nc < here.length) {
				ret.add(Create_Array(here, curR, curC, nr, nc));
			}
		}
		
		return ret;
	}
	
	static int[][] Create_Array(int[][] here,int curR,int curC, int r, int c) {
		int[][] ret = new int[here.length][];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = here[i].clone();
		}
		ret[curR][curC] = here[r][c];
		ret[r][c] = here[curR][curC];
		
		return ret;
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
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
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