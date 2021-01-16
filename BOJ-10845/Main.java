import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;
	
	static int N;
	static MyQueue queue = new MyQueue(10001); 

	public static void main(String[] args) throws IOException {
		Init_Solve();
	}
	
	
	
	static void Init_Solve() throws IOException{
		N = io.inputInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			char[] command = stk.nextToken().toCharArray();
			int operand = 0;
			switch(command[0]) {
				case 'p':
					if(command[1] == 'u') {
						operand = nextInt(stk);
						queue.Push(operand);
					}
					else if(command[1] == 'o') {
						sb.append(queue.Pop());
						sb.append('\n');
					}
					break;
				
				case 's':
					sb.append(queue.Size());
					sb.append('\n');
					break;
					
				case 'e':
					sb.append(queue.Empty());
					sb.append('\n');
					break;
					
				case 'f':
					sb.append(queue.Front());
					sb.append('\n');
					break;
					
				case 'b':
					sb.append(queue.Back());
					sb.append('\n');
					break;
			}
		}
		
		System.out.print(sb.toString());
		
	}
	/**
	push X: 정수 X를 큐에 넣는 연산이다.
	pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	size: 큐에 들어있는 정수의 개수를 출력한다.
	empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
	front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
	**/
	static class MyQueue {
		int[] arr;
		int size,count;
		int front,rear;
		public MyQueue(int size) {
			super();
			this.arr = new int[size];
			this.size = size;
			this.count = 0;
			this.front = 0;
			this.rear = -1;
		}
		
		public void Push(int x) {
			if(count == size) return;
			rear = (rear+1) % size;
			arr[rear] = x;
			count++;
		}
		
		public int Pop() {
			if(count == 0) return -1;
			int ret = arr[front++];
			count--;
			if(count == 0) rear = front-1;
			return ret;
		}
		
		public int Empty() {
			return (count == 0) ? 1:0;
		}
		
		public int Size() {
			return count;
		}
		
		public int Front() {
			if(count == 0) return -1;
			return arr[front];
		}
		public int Back() {
			if(count == 0) return -1;
			return arr[rear];
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