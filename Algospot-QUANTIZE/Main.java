import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	
	static int INF = 999999999;
	static int TOTAL,NUMBER_OF_PICECS,PROBLEM_SIZE;
	static int[] PROBLEM_ARRAY;
	static int[][] DP = new int[100][100];
	static int[][] DP_MIN_ERROR = new int[100][100];
	
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Arrays.sort(PROBLEM_ARRAY);
			System.out.println(DP_Solve(0,NUMBER_OF_PICECS));
		}
	} 
   
	static int DP_Solve(int from,int piece) {
		if(from == PROBLEM_SIZE) return 0;
		if(piece <= 0) return INF;
		
		if(DP[from][piece] != -1) return DP[from][piece];
		int min = INF, value = 0;
		for(int part = 1; from+part <= PROBLEM_SIZE; part++) {
			value = MinError(from,from+part-1) + DP_Solve(from+part,piece-1);
			min = Min(min,value);
		}
		DP[from][piece] = min;
		return DP[from][piece];
	}
	
	static int MinError(int from,int to) {
		if(to-from <= 0) return PROBLEM_ARRAY[from];
		if(DP_MIN_ERROR[from][to] != -1) return DP_MIN_ERROR[from][to];
		int min = INF, d;
		for(int n = PROBLEM_ARRAY[from]; n <= PROBLEM_ARRAY[to]; n++) {
			d = 0;
			for(int i = from; i <= to; i++) {
				int dif = (PROBLEM_ARRAY[i]-n);
				dif = dif*dif;
				d += dif;
			}
			min = Min(min,d);
		}
		DP_MIN_ERROR[from][to] = min;
		return DP_MIN_ERROR[from][to];
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		PROBLEM_SIZE = nextInt(stk);
		NUMBER_OF_PICECS = nextInt(stk);
		PROBLEM_ARRAY = new int[PROBLEM_SIZE];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreElements(); i++) {
			PROBLEM_ARRAY[i] = nextInt(stk);
			Arrays.fill(DP[i], -1);
			Arrays.fill(DP_MIN_ERROR[i], -1);
		}
		
		
	}
	
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b)?b:a;
	}
	static long Max(long a, long b) {
		return (a > b)?a:b;
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	static void Display(int[] arr) {
		System.out.println("요소갯수 : " + arr.length);
		for(int i = 0; i < arr.length; i++) System.out.print(arr[i]+" ");
		System.out.println();
	}
	static void Display(int[][] arr) {
		System.out.println("요소갯수 : " + (arr.length*arr[0].length));
		for(int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
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
	}}
