import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 

	static int TOTAL,N,M;
	static ArrayList<Integer> decList = new ArrayList<Integer>();
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	static int[] previous = new int[20000];
	static int[] pick = new int[20000];
	
	
	// [문제해결목표]
	//(1) C >= N+M
	//(2) C%N = M
	//(3) C는 D의 숫자들로만 구성 되어있음
	
	/* [해결방안]
	 * next R은 계속적 등장되는 경향이 있음. (싸이클)
	 * next R이 연산을 통해 1회 등장하면 Visited[R]을 사용한다. (최단거리가 필요하므로,)
	 * 
	 */
	
	public static void main(String[] args) throws IOException {	
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}

	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		char[] ds = stk.nextToken().toCharArray();
		N = nextInt(stk);
		M = nextInt(stk);
		decList.clear();
		for (int i = 0; i < ds.length; i++) decList.add(ds[i]-'0'); 	
		Collections.sort(decList);
		Arrays.fill(previous, -1);
		Arrays.fill(pick, -1);
		
	}
	
	// [0,N-1] [N,2N] , N은 1이상. M은 0이 될수도 있음.
	static void Solve() {
		// N 1이상, 그래서 [0]에 시작으로 등록
		queue.add(0);
		previous[0] = 0;
		pick[0] = -1;
		
		int rest, nextRest;
		while(!queue.isEmpty()) {
			rest = queue.poll();
			for(int i = 0; i < decList.size(); i++) {
				nextRest = Remainder(rest, decList.get(i), N);
				if(previous[nextRest] == -1) {
					queue.add(nextRest);
					previous[nextRest] = rest;
					pick[nextRest] = decList.get(i);
				}
			}
		}
		
		if(previous[N+M] == -1) {
			System.out.println("IMPOSSIBLE");
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		int recur = N+M;
		while(recur != 0) {
			sb.append((char)(pick[recur]+'0'));
			recur = previous[recur];
		}
		sb = sb.reverse();
		System.out.println(sb.toString());
		return;
	}
	
	static int Remainder(int preR, int x, int mod) {
		int value = preR*10+x;
		//한번 mod를 넘으면, 계속 mod가 더해진다. (God Code)
		if(value >= mod) return mod + value%mod;
		return value%mod;
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