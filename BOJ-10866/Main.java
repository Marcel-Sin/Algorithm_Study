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
	static MyDeque dq = new MyDeque(10000); 

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
			//h h _ _ e t n k  ( [3]의 char로 명령 구분 )
			switch(command[3]) {
				//push
				case 'h':
					operand = nextInt(stk);
					if(command[5] == 'f') dq.Push_Front(operand);
					else dq.Push_Back(operand);
					break;
					
				//pop_
				case '_':
					if(command[4] == 'f') { sb.append(dq.Pop_Front()); sb.append('\n'); }
					else { sb.append(dq.Pop_Back()); sb.append('\n'); }
					break;
				
				//size
				case 'e':
					sb.append(dq.Size());
					sb.append('\n');
					break;
				
				//empt
				case 't':
					sb.append(dq.Empty());
					sb.append('\n');
					break;
				
				//fron
				case 'n':
					sb.append(dq.Front());
					sb.append('\n');
					break;
					
				//back
				case 'k':
					sb.append(dq.Back());
					sb.append('\n');
					break;
			}
		}
		System.out.print(sb.toString());
	}
	
	// push_front, push_back, pop_front, pop_back, size, empty, front, back
	static class MyDeque {
		int[] arr;
		int size,count;
		int front,rear;
		
		public MyDeque(int size) {
			super();
			this.arr = new int[size];
			this.size = size;
			this.count = 0;
			this.front = 0;
			this.rear = 0;
		}
		
		public void Push_Front(int x) {
			if(count == size) return;
			if(count != 0) front = (front-1+size)%size;
			arr[front] = x;
			count++;
		}
		
		public void Push_Back(int x) {
			if(count == size) return;
			if(count != 0) rear = (rear+1)%size;
			arr[rear] = x;
			count++;
		}
		
		public int Pop_Front() {
			if(count == 0) return -1;
			int ret = arr[front];
			count--;
			if(count != 0) front = (front+1)%size; 
			return ret;
		}
		
		public int Pop_Back() {
			if(count == 0) return -1;
			int ret = arr[rear];
			count--;
			if(count != 0) rear = (rear-1+size)%size; 
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