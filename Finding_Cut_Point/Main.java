import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static final int MAX_LETTER = 26; 
	
	static int TOTAL;
	static int[][] MATRIX = new int[10][10];

	static int COUNT;
	static int[] DISCOVERED = new int[10];
	static boolean[] CUT_POINT = new boolean[10];
	
	
	public static void main(String[] args) throws IOException {	
		Solve();
	}
	
	static void Solve() {
		Connect(0,1);
		Connect(0,3);
		Connect(0,7);
		Connect(0,8);
		Connect(3,5);
		Connect(3,4);
		Connect(5,6);
		Connect(7,2);
		Connect(2,8);
		Connect(4,6);
		
		COUNT = 0;
		Arrays.fill(DISCOVERED, -1);
		Arrays.fill(CUT_POINT, false);
		
		Find_Lowest_Connect_Point(0);
		
		for(int i = 0; i < CUT_POINT.length; i++) {
			System.out.print(DISCOVERED[i]+" ");
		}
		System.out.println();
		for(int i = 0; i < CUT_POINT.length; i++) {
			System.out.print(CUT_POINT[i]+" ");
		}

	}
	
	//가장 방문 했던 곳중 가장 빠른 정점 반환
	static int Find_Lowest_Connect_Point(int here) {
		DISCOVERED[here] = COUNT++;
		int ret = DISCOVERED[here];
		int child = 0;
		for(int i = 0; i < MATRIX.length; i++) {
			if(MATRIX[here][i] == 1) {
				// 아직 방문안한 정점은 가봐야 알 수 있다.
				if(DISCOVERED[i] == -1) {
					child++;
					int subLowest = Find_Lowest_Connect_Point(i);
					// [Question] "서브트리에서 아무리 가도, here 이전으로는 못 가면 절단점"
					if(DISCOVERED[here] != 0 && subLowest >= DISCOVERED[here]) CUT_POINT[here] = true;
					ret = Min(ret,subLowest);
				}
				// 방문한 정점들인데 길이 있으면 가장 낮은 곳을 저장하자.
				else ret = Min(ret,DISCOVERED[i]);
			}
		}
		// 루트도 자식이 1개보다 많으면 절단점
		if(DISCOVERED[here] == 0 && child > 1) CUT_POINT[here] = true;
		return ret;
	}
	
	static void Connect(int a, int b) {
		MATRIX[a][b] = 1;
		MATRIX[b][a] = 1;
		
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