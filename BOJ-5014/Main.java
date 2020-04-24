import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int F,S,G,U,D;
	static int[] check;
	public static void main(String[] args) throws IOException {
		Initializing();
		BFS(S);
		if(check[G] == -1) System.out.println("use the stairs");
		else System.out.println(check[G]);
	}
	
	static void BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		check[start] = 0;
		queue.add(start);
		int queueSize = 0, parent = 0, counter = 1, n1,n2;
		while(queue.isEmpty() == false) {
			queueSize = queue.size();
			for (int i = 0; i < queueSize; i++) {
				parent = queue.poll();
				if(parent == G) return;
				
				n1 = parent + U;
				n2 = parent - D;
				if(1 <= n1 && n1 <= F && check[n1] == -1) {
					check[n1] = counter;
					queue.add(n1);
				}
				if(1 <= n2 && n2 <= F && check[n2] == -1) {
					check[n2] = counter;
					queue.add(n2);
				}
			}
			counter++;
		}
		
		
		
		
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		F = nextInt(stk);
		S = nextInt(stk);
		G = nextInt(stk);
		U = nextInt(stk);
		D = nextInt(stk);
		check = new int[1000001];
		Arrays.fill(check, -1);
	}
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
		}
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
