import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;
	
	static int N;
	static MyStack stack = new MyStack(10001);
	

	public static void main(String[] args) throws IOException {
		Init();
	}

	static void Init() throws IOException{
		N = io.inputInt(); 
		StringTokenizer stk;
		String command = "";
		int operand = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			stk = new StringTokenizer(io.inputStr());
			command = stk.nextToken();
			if(stk.hasMoreElements()) operand = nextInt(stk);
			
			switch(command) {
				case "push":
					stack.Push(operand);
					break;
				case "pop":
					sb.append(stack.Pop());
					sb.append('\n');
					break;
				case "size":
					sb.append(stack.Size());
					sb.append('\n');
					break;
				case "empty":
					sb.append(stack.Empty());
					sb.append('\n');
					break;
				case "top":
					sb.append(stack.Top());
					sb.append('\n');
					break;
			}
		}
		
		System.out.println(sb.toString());
	}

	
	static class MyStack {
		int[] arr;
		int max_size;
		int count;
		int top;
		
		public MyStack(int size) {
			super();
			this.arr = new int[size];
			this.max_size = size;
			this.count = 0;
			this.top = -1; 
		}
		
		public void Push(int x) {
			if(count >= max_size) return;
			arr[count] = x;
			top = count;
			count ++;
		}
		public int Size() {
			return count;
		}
		public int Pop() {
			if(count <= 0) return -1;
			int ret = arr[top];
			count--;
			top--;
			return ret;
		}
		public int Empty() {
			if(count == 0) return 1;
			else return 0;
		}
		
		public int Top() {
			if(count == 0) return -1;
			else return arr[top];
		}
		
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
			System.out.print("[" + i + "] : ");
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