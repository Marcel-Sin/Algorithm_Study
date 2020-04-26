import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int N,M;
	static int[] A;
	public static void main(String[] args) throws IOException {
		Initializing();
		State s = new State();
		Arrays.sort(A);
		DFS(s,0);
		io.close();
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		M = nextInt(stk);
		A = new int[N];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0 ; i < N; i++) A[i] = nextInt(stk); 
	}
	
	static void DFS(State state,int curIdx) throws IOException{
		if(state.storage_Pointer == M) {
			io.write(state+"");
			return;
		}
		
		for (int i = curIdx; i < N; i++) {
				state.Select(i);
				DFS(state,i+1);
				//state.check[i] = false;
				state.storage_Pointer--;
			
		}
	}
	
	static class State {
		boolean[] check;
		int[] storage;
		int storage_Pointer;
		public State() {
			super();
			storage = new int[M];
			check = new boolean[N];
			this.storage_Pointer = 0;
		}
		
		public void Select(int idx) {
			check[idx] = true;
			storage[storage_Pointer] = idx;
			storage_Pointer++;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < M; i++) {
				sb.append(A[storage[i]]);
				sb.append(' ');
			}
			sb.append('\n');
			return sb.toString();
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
