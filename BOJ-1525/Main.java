import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int PROBLEM_MAX = 10000;
	
	static int N, M;
	
	static StringBuilder sb; 
	static Queue<Long> queue = new LinkedList<Long>();
	static HashMap<Long, Integer> visited = new HashMap<Long, Integer>();
	static long answer;
	static long problem;
	static int[] dirR = {0,0,-1,1};
	static int[] dirC = {-1,1,0,0};
	
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	
	}

	static void Init() throws IOException {
		int[][] arr = new int[3][3];
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 3; j++) {
				int num = nextInt(stk);
				if(num == 0) problem = SetPane(problem, i*3+j, 9);
				else problem = SetPane(problem, i*3+j,num);
				
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				answer = SetPane(answer,i*3+j,i*3+j+1);
			}
		}
	}

	
	static void Solve() throws IOException {
		queue.clear();
		queue.add(problem);
		visited.put(problem, 0);
		while(!queue.isEmpty()) {
			long here = queue.poll();
			if(here == answer) break;
			int herePos = GetPos(here,9);
			int hereCount = visited.get(here);
			int row = herePos/3, col = herePos%3;
			int nextPos,temp;
			for (int i = 0; i < dirR.length; i++) {
				if(0 <= row+dirR[i] && row+dirR[i] < 3 && 0 <= col+dirC[i] && col+dirC[i] < 3) {
					nextPos = (row+dirR[i])*3 + col+dirC[i];
					//다음 위치에 존재하는 값을 임시보관
					temp = GetPosValue(here, nextPos);
					//다음 위치를 9로 바꿈
					long there = SetPane(here, nextPos, 9);
					//현재 위치는 다음위치에 있던 값이 옴
					there = SetPane(there, herePos, temp);
					if(visited.containsKey(there) == true) continue;
					visited.put(there, hereCount+1);
					queue.add(there);
				}
			}
		}
		
		if(visited.containsKey(answer) == false) System.out.println(-1);
		else System.out.println(visited.get(answer));
	}
	
	private static long tempA;
	static long SetPane(long curValue, int pos, int setValue) {
		// Step.1 : 원하는 곳을 0000로 만든다.
		pos = 4*(8-pos);
		tempA = 0b1111L << pos;
		tempA = ~tempA;
		curValue = tempA & curValue;
		//System.out.println(Long.toBinaryString(curValue));
		
		// Step.2 : 원하는 곳 위치한 숫자를 만든다.
		tempA = setValue;
		tempA = tempA << pos;
		
		// Step.3 : 비어있는 녀석과 넣을 녀석을 OR 후 리턴한다.
		//System.out.println(Long.toBinaryString(curValue | tempA));
		return curValue | tempA;
	}
	private static long tempB;
	static int GetPos(long curValue, long findValue) {
		for (int p = 0; p < 9; p++) {
			tempB = (curValue >> 4*(8-p)) & 0b1111l;
			if(tempB == findValue) return p;
		}
		return -1;
	}
	static int GetPosValue(long curValue, int pos) {
		tempA = 0b1111L << 4*(8-pos);
		tempA = tempA & curValue;
		tempA = tempA >> 4*(8-pos);
		return (int)tempA;
	}
	
//	===================== ETC functions for PS =====================
//	===================== ETC functions for PS =====================
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
		// System.out.println("요소갯수 : " + arr.length);
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