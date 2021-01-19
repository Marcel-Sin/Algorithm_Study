import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;

	static int N;
	static MyEditor editor = new MyEditor();
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();

	}
	
	static void Init() throws IOException{
		char[] str = io.inputStr().toCharArray();
		N = io.inputInt();
		for (int i = 0; i < str.length; i++) {
			editor.Plus(str[i]);
		}
	}
	static void Solve() throws IOException{
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			int command = stk.nextToken().charAt(0);
			int operand = 0;
			switch(command) {
				case 'P':
					operand = stk.nextToken().charAt(0);
					editor.Plus((char)operand);
					break;
				case 'B':
					editor.Back();
					break;
				case 'L':
					editor.Left();
					break;
				case 'D':
					editor.Drive();
					break;
			}
		}
		System.out.println(editor);
	}
	
	//L,D,B,P x
	static class MyEditor {
		Stack<Character> backStack = new Stack<Character>();
		Stack<Character> frontStack = new Stack<Character>();
		
		
		public void Left() {
			if(!backStack.empty()) {
				frontStack.add(backStack.pop());
			}
		}
		
		public void Drive() {
			if(!frontStack.empty()) {
				backStack.add(frontStack.pop());
			}
		}
		
		public void Back() {
			if(!backStack.empty()) {
				backStack.pop();
			}
		}
		
		public void Plus(char c) {
			backStack.add(c);
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			ListIterator<Character> iter = backStack.listIterator();
			while(iter.hasNext()) sb.append(iter.next());
			iter = frontStack.listIterator(frontStack.size());
			while(iter.hasPrevious()) sb.append(iter.previous());
			return sb.toString();
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