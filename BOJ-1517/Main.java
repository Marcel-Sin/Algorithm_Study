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
	static final int MAX = 500000;
	
	static int N;
	static long swap_counter;
	static int[] problem = new int[MAX];
	static int[] merge_memory = new int[MAX];
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {	
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		N = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < N; i++) {
			problem[i] = nextInt(stk);
		}
	}
	static void Solve() throws IOException{
		swap_counter = 0;
		MergeSort(problem, 0, N-1, merge_memory);
		System.out.println(swap_counter);
	}
	
		
	static void MergeSort(int[] arr,int low, int high, int[] temp) {
		//base condition
		if(high <= low) return;
		
		//Divide and Conquer
		int mid = (low+high)/2;
		MergeSort(arr,low,mid,temp);
		MergeSort(arr,mid+1,high,temp);
		
		//Merge
		int l = low, h = mid+1;
		for (int k = low; k <= high; k++) {
			if(mid < l) temp[k] = arr[h++];
			else if(high < h) temp[k] = arr[l++];
			else if(arr[l] <= arr[h]) temp[k] = arr[l++];    //'<=' 상위인덱스 배열이 더 큰 경우만 else로 추출
			else {
				swap_counter += mid-l+1;
				temp[k] = arr[h++];
			}
		}
		
		//Copy
		for (int k = low; k <= high; k++) arr[k] = temp[k]; 
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