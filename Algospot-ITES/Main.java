import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static long DIVIDER = 2l << 31l;
	static int K,N;
	static long LAST_SIGNAL;
	static long AFTER_SIGNAL;
	static short[] SIGNALS = new short[10000000];
	static My_Queue queue = new My_Queue(5000001);
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		Create_Signal(10000000);
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Queue_Solve());
		}
	}

	static int Queue_Solve() {
		
		int sum = 0, count = 0, num = 0,idx = 0;
			
		while(true) {
			while(sum < K && idx < N) {
				num = Next_Signal(idx++);
				queue.Enqueue(num);
				sum += num;
			}
			if(sum == K) count++;
			
			if(sum >= K) sum -= queue.Dequeue();
			
			if(sum < K && idx == N) break;
		}
		
		queue.Clean();
		return count;
	}
	
	

	static void Create_Signal(int n) {
		long signal = 1983;
		
		for(int i = 0; i < n; i++) {
			SIGNALS[i] = (short)(signal % 10000 + 1);
			signal = (signal * 214013 + 2531011) % DIVIDER;
		}
		LAST_SIGNAL = signal;
	}
	
	static int Next_Signal(int n) {
		if(n < 10000000) return SIGNALS[n];
		
		AFTER_SIGNAL = (AFTER_SIGNAL* 214013 + 2531011) % DIVIDER;
		return (int)(AFTER_SIGNAL % 10000 + 1);
	}
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		K = nextInt(stk);
		N = nextInt(stk);
		AFTER_SIGNAL = LAST_SIGNAL;
	}
	

	static class My_Queue {
		int head, tail, count, size;
		int[] arr;

		public My_Queue(int size) {
			super();
			this.head = 0;
			this.tail = 0;
			this.count = 0;
			this.size = size;
			this.arr = new int[size];
		}
		public void Enqueue(int n) {
			if (count + 1 > size)
				return;
			arr[tail] = n;
			tail = (tail + 1) % size;
			count++;
			return;
		}
		
		public int Dequeue() {
			if (count == 0)
				return -1;
			int ret = arr[head];
			head = (head + 1) % size;
			if (count == 1)
				tail = head;
			count--;
			return ret;
		}
		public int Peek() {
			if (count == 0)
				return -1;
			return arr[head];
		}
		public int Count() {
			return count;
		}
		
		public boolean isEmpty() {
			return (count == 0);
		}
		
		public void Clean() {
			tail = head;
			count = 0;
		}
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