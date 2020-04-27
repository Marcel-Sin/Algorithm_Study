import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int LOTTO = 6;
	static int[] A;
	static int size;
	public static void main(String[] args) throws IOException {
		while(Initializing() == true) {
			State st = new State();
			DFS(st, 0);
			io.bw.flush();
			io.bw.write('\n');
		}
	}
	
	static boolean Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		size = nextInt(stk);
		if(size == 0) return false;
		A = new int[size];
		for(int i = 0; i < size; i++) A[i] = nextInt(stk);
		return true;
	}
	
	static void DFS(State s,int curIdx) throws IOException{
		if(s.sp == LOTTO) {
			io.write(s.toString());
			return;
		}
		
		for(int i = curIdx; i < size; i++) {
			s.Select(i);
			DFS(s,i+1);
			s.sp--;
		}
	}

	static class State {
		int[] storage;
		int sp;
		
		public State() {
			super();
			this.storage = new int[LOTTO];
			this.sp = 0;
		}
		
		public void Select(int idx) {
			storage[sp] = A[idx];
			sp++;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < sp; i++) {
				sb.append(storage[i]);
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
