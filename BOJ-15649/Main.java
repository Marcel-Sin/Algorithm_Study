import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int N,M;
	public static void main(String[] args) throws IOException {
		Initializing();
		State s = new State();
		DFS(s);
		io.close();
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
	}
	
	static void DFS(State state) throws IOException{
		if(state.storage_Pointer == M) {
			for(int i = 0; i < M; i++) { io.bw.write(state.storage[i]);
			io.bw.write(' ');
			}
			io.bw.write('\n');
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(state.check[i] == false) {
				state.Select(i);
				DFS(state);
				state.check[i] = false;
				state.storage_Pointer--;
			}
		}
	}
	
	static class State {
		boolean[] check;
		char[] storage;
		int storage_Pointer;
		public State() {
			super();
			check = new boolean[N+1];
			storage = new char[M];
			this.storage_Pointer = 0;
		}
		
		public void Select(int n) {
			check[n] = true;
			storage[storage_Pointer] = (char)(n+48);
			storage_Pointer++;
		}
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
