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
	static Queue<Long> queue = new LinkedList<Long>();
	static int[][] problem = new int[3][3];
	static int[][] finish = {{1,2,3},{4,5,6},{7,8,0}};
	static long problemCode,finishCode;
	static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
	
	
	
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Bi_BFS());
	}
	static void Init() throws IOException{
		StringTokenizer stk;
		for(int i = 0; i < 3; i++) {
			stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 3; j++) {
				problem[i][j] = nextInt(stk);
			}
		}
		
		problemCode = ArrayToHash(problem);
		finishCode = ArrayToHash(finish);
	}
	static int Bi_BFS() {
		if(problemCode == finishCode) return 0;
		
		queue.add(problemCode);
		checkMap.put(problemCode, 0);
		
		long hereCode,adjCode;
		int hereCost;
		while(!queue.isEmpty()) {
			hereCode = queue.poll();
			hereCost = checkMap.get(hereCode);
			
			ArrayList<Long> adj = GetAdjacent(hereCode);
			for(int i = 0; i < adj.size(); i++) {
				adjCode = adj.get(i);
				if(checkMap.containsKey(adjCode) == false) {
					if(adjCode == finishCode) return hereCost+1;
					queue.add(adjCode);
					checkMap.put(adjCode, hereCost+1);
				}
			}
		}
		return -1;
	}

	static long ArrayToHash(int[][] arr) {
		long ret = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				ret = ret | arr[i][j];
				ret = ret << 4;
			}
		}
		ret = ret >>> 4;
		return ret;
	}
	
	static ArrayList<Long> GetAdjacent(long hereCode) {
		ArrayList<Long> ret = new ArrayList<Long>();
		int curR, curC;
		int zeroPos = GetPos(hereCode, 0);
		curR = zeroPos/3; curC = zeroPos%3;
		
		for (int i = 0; i < 4; i++) {
			int nr = curR+dir[i][0];
			int nc = curC+dir[i][1];
			if(nr >= 0 && nc >= 0 && nr < 3 && nc < 3) {
				long nextCode = SwapPos(hereCode,curR,curC,nr,nc);
				ret.add(nextCode);
			}
		}
		return ret;
	}
	static long SwapPos(long code, int r, int c, int swapR, int swapC) {
		int pos = r*3+c, pos2 = swapR*3+swapC;
		int temp = GetNum(code, pos);
		code = SetNum(code, GetNum(code, pos2), pos);
		code = SetNum(code, temp, pos2);
		
		return code;
	}

	static long SetNum(long code, int num, int pos) {
		int shifter = (8-pos)*4;
		//Bits Clear
		long temp = (long)0b1111 << shifter;
		temp = ~temp;
		code = temp & code;
		
		//Set Bits
		temp = (long)num << shifter;
		code = temp | code;
		return code;
	}
	static int GetNum(long code,int pos) {
		int shift = (8-pos)*4;
		long temp = (long)0b1111 << shift;
		temp = temp & code;
		temp = temp >> shift;
		return (int)temp;
	}
	static int GetPos(long code,int num) {
		for (int i = 8; i >=  0; i--) {
			if( (code & 0b1111) == num) return i;
			code = code >> 4;
		}
		return -1;
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