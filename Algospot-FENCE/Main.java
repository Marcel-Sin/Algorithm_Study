import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL, N;
	static int[] WOOD = new int[20001];
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Max_Fence());
		}
	}


	static void Init() throws IOException {
		N = io.inputInt();
		Arrays.fill(WOOD, 0);
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for(int i = 0; stk.hasMoreTokens(); i++) WOOD[i] = nextInt(stk);
	}
	
	static int Max_Fence() {
		Stack<Integer> stack = new Stack<Integer>();
		int ret = 0;
		int right_idx = 0, left_idx = 0, width = 0, square = 0;
		
		for(int i = 0; i <= N; i++) {
			// 이번 차례에 i번째 판자가 Stack.Top() 보다 작다는 건, 스택 탑의 경계점(right)가 i번째 판자
			while(!stack.isEmpty() && WOOD[stack.peek()] >= WOOD[i] ) {
				int current_height = WOOD[stack.pop()];
				
				right_idx = i;
				
				if(stack.isEmpty()) left_idx = -1;
				else left_idx = stack.peek();
				
				width = right_idx - left_idx - 1;
				
				square = current_height * width;
				ret = Max(ret,square);
			}
			stack.push(i);
		}
		return ret;
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
		System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
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