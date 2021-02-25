import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 500001;
	static final int CURRENT_CHANNEL = 100;
	
	
	static int N,numberSize,minPress;
	static ArrayList<Integer> number = new ArrayList<Integer>();
	static boolean[] check = new boolean[MAX*2];
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		int k = io.inputInt();
		boolean[] notpossible = new boolean[10];
		if( k != 0) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int i = 0; i < k; i++) notpossible[nextInt(stk)] = true;
		}
		for (int i = 0; i < 10; i++) if (!notpossible[i]) number.add(i);
		
		number.sort((a, b) -> {
			if(a == b) return 0; 
			else return (a < b) ? 1:-1; 
		});
		numberSize = number.size();
	}

	
	/*
	 * [해결 전략]
	 * 1) 현재 채널에서 +-로만으로 이동한다. (오직 1가지)
	 * 2) 채널 버튼을 누르고 +-로 이동한다.
	 * 
	 * 채널 버튼을 누르고(조합) 이동하는 것은 모든 경우를 조사한다. - DFS 이용
	 * <특이사항> 버튼이 0만 주어지는 경우, 0 ~ 49까지의 채널은 0+ 이동하면 유리하다.
	 */
	static int Solve() throws IOException {
		minPress = Math.abs(N-CURRENT_CHANNEL);
		for (int i = 0; i < numberSize; i++) {
			DFS(number.get(i),1);
		}
		return minPress;
	}
	
	static void DFS(int here, int buttonPress) {
		check[here] = true;
		// Min갱신
		minPress = Min(minPress,buttonPress+Math.abs(here-N));
		
		if(here == 0) return;
		for (int i = 0; i < numberSize; i++) {
			int next = Concat_Number(here,number.get(i));
			if(buttonPress < 6 && check[next] == false) DFS(next,buttonPress+1);
		}
	}
	
	static int Concat_Number(int origin, int target) {
		origin *= 10;
		return origin+target;
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