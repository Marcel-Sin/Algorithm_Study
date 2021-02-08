import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 500001;
	static final int MIN_VALUE = -10000000;
	static final int MAX_VALUE = 10000000;
	
	static int N1,N2;
	
	static int[] problem;
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	static void Init() throws IOException{
		N1 = io.inputInt();
		problem = new int[N1];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N1; i++) {
			problem[i] = nextInt(stk);
		}
		Arrays.sort(problem);
	}

	static void Solve() throws IOException {
		N2 = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N2; i++) {
			int num = nextInt(stk);
			int value1 = Lower_Bound(num, N1);
			if(value1 == -1) {sb.append(0); sb.append(' '); continue;}
			int value2 = Upper_Bound(num, N1);
			sb.append(value2-value1);
			sb.append(' ');
		}
		System.out.println(sb.toString());
	}
	
	//키 값의 첫번째 위치 반환
	static int Lower_Bound(int key,int size) {
		int low=0, high=size, mid;
		while(low < high) {
			mid = (low+high)/2;
			if(problem[mid] < key) { low = mid+1; }
			else high = mid;
		}
		if(low < size && problem[low] == key) return low;
		else return -1;
	}
	
	//값보다 큰 첫번째 위치 반환
	static int Upper_Bound(int key,int size) {
			int low=0, high=size, mid;
			while(low < high) {
				mid = (low+high)/2;
				if(problem[mid] <= key) { low = mid+1; }
				else high = mid;
			}
			if(0 <= low-1 && problem[low-1] == key) return low;
			else return -1;
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