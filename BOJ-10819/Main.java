import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 531442; //3의 10승가지
	
	static int N,ans;
	static int[] arr;
	public static void main(String[] args) throws IOException{
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		arr = new int[N];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N; i++) arr[i] = nextInt(stk);	
		
	}

	static int Solve() throws IOException {
		ans = NINF;
		DFS(0,true);
		return ans;
	}
	
	static void DFS(int here, boolean changed) {
		// 이전에 변화가 있었니?
		if(changed == true) ans = Max(Array_Dif_Sum(),ans);
		for (int i = here; i < N; i++) { 
			// 바꿔본다
			if(here != i) {
				Swap(here,i);
				DFS(here+1,true);
				Swap(here,i);
			}
			// 바꿔보지 않고 넘어가본다.
			else DFS(here+1,false);
		}
	}
	
	static int Array_Dif_Sum() {
		int ret = 0;
		// (0,1) (2,3) (N = 4)    (0,1) (2) (N = 3)
		for (int i = 0; i < N-1; i++) ret += Math.abs(arr[i] - arr[i+1]);
		return ret;
	}
	private static int Swaper;
	static void Swap(int i, int j) {
		Swaper = arr[i];
		arr[i] = arr[j];
		arr[j] = Swaper;
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