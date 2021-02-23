import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE/2;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 10001;
	
	static int N;
	static ArrayList<Integer> minus = new ArrayList<Integer>(MAX); //이 문제에선 0은 음수에 포함시킴
	static ArrayList<Integer> plus = new ArrayList<Integer>(MAX);
	static ArrayList<Integer> ans = new ArrayList<Integer>(MAX);
	
	
	
	public static void main(String[] args) throws IOException {
		Init();
		System.out.println(Solve());
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		for (int i = 0; i < N; i++) {
			int num = io.inputInt();
			if(num > 0) {
				if(num == 1) ans.add(num);
				else plus.add(num);
			}
			else minus.add(num);
		}
	}

	//[해결 전략]
	// [0을 포함한 음수파트], [0을 제외한 양수 파트]
	// (곱하면 반드시 유리함) , (0을 곱하지 않으면 무조건 유리함)
	// 음수파트는 모두 곱한다. 양수파트는 뒤에서부터 곱해준다.(편의상 내림차정렬)
	// !!! 양수 1은 곱하는 것보다 더하는게 낫다. (즉시 ans로)
	static int Solve() throws IOException {
		// 0,음수파트 처리
		Collections.sort(minus);
		int minus_Size = minus.size();
		for (int i = 0; i < minus_Size; i+=2) {
			if(i+1 < minus_Size) ans.add(minus.get(i)*minus.get(i+1));
			else ans.add(minus.get(i));
		}
		
		//양수파트 처리
		Collections.sort(plus, (a,b) -> {
			if(a == b) return 0;
			else return (a > b) ? -1:1;
		});
		
		int plus_Size = plus.size();
		for (int i = 0; i < plus_Size; i+=2) {
			if(i+1 < plus_Size) ans.add(plus.get(i)*plus.get(i+1));
			else ans.add(plus.get(i));
		}

		//토탈 총합
		int ret = 0;
		for (int i = 0; i < ans.size(); i++) ret += ans.get(i);
		return ret;
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