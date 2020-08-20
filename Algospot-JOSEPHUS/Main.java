import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL, N, K;
	static My_Queue queue = new My_Queue(3000);
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Queue_Solve(N, K);
		}
	}
	
	static void Queue_Solve(int n, int k) {
		for(int i = 0; i < n; i++) queue.Push(i+1);
		
		// First Kill
		queue.Pop();
		// the others
		while (queue.count > 2) {
			queue.Head_To_Tail(k-1);
			queue.Pop();
		}
		int a = queue.Pop();
		int b = queue.Pop();
		if(a < b) System.out.printf("%d %d\n", a, b);
		else System.out.printf("%d %d\n", b, a);
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
		public void Push(int n) {
			if (count + 1 > size)
				return;
			arr[tail] = n;
			tail = (tail + 1) % size;
			count++;
			return;
		}
		
		public int Pop() {
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
	
		public void Head_To_Tail(int skip) {
			for(int i = 0; i < skip; i++) arr[(tail+i)%size] = arr[(head+i)%size];
			tail = (tail+skip)%size;
			head = (head+skip)%size;
		}
		
	}

	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk);
	}
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