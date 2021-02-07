import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 200001;
	
	static int N,C;
	
	static int max_Dist;
	static int[] distX;
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.println(Solve());
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		// N가 집의 갯수, C는 최소 요구 갯수
		N = nextInt(stk);
		C = nextInt(stk);
		max_Dist = 0;
		distX = new int[N];
		for (int i = 0; i < N; i++) {
			distX[i] = io.inputInt();
			max_Dist = Max(distX[i],max_Dist);
		}
		Arrays.sort(distX);
	}
	
	static long Solve() {
		// 이진탐색에서 최대 범위는 exclusive, +1을 해준다.
		int count,dist = 0;
		int low=0, high=max_Dist+1, mid;
		
		
		// 만들 수 없는 경우를 주지 않음. (모든 상황에서 이진탐색 100% 성공)
		while(low < high) {
			mid = (low+high)/2;
			count = Connecter_Setup(mid);
			if(C <= count) {
				dist = Max(dist,mid);
				low = mid+1;
			}
			else {
				high = mid;
			}
		}
		
		return dist;
	}
	
	// 설치한 공유기의 대수 반환
	static int Connecter_Setup(int interDist) {
		int between = 0,count = 0;
		for (int i = 0; i < N; i++) {
			if (distX[i] >= between) {
				between = distX[i]+interDist;
				count++;
			}
		}
		return count;
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