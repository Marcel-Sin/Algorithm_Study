import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int LEN,SIZE;
	static char[] A;
	public static void main(String[] args) throws IOException {
		Initializing();
		State state = new State();
		DFS(state,0);
		io.close();
	}
	
	static boolean Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		LEN = nextInt(stk);
		SIZE = nextInt(stk);
		A = new char[SIZE];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < SIZE; i++) A[i] = stk.nextToken().charAt(0);
		Arrays.sort(A);
		return true;
	}
	static void DFS(State s,int curIdx) throws IOException{
		if(s.sp == LEN) {
			if(s.Condition()) {
				io.write(s.toString());
				io.bw.write('\n');
			}
			return;
		}
		
		for(int i = curIdx; i < SIZE; i++) {
			s.Select(i);
			DFS(s,i+1);
			s.sp--;
		}
	}

	static class State {
		char[] storage;
		int sp;
		
		public State() {
			super();
			this.storage = new char[LEN];
			this.sp = 0;
		}
		
		public boolean Condition() {
			int vow=0,conso=0;
			for(int i = 0; i < storage.length; i++) {
				if(storage[i] == 'a' || storage[i] == 'e' || storage[i] == 'i' || storage[i] == 'o' || storage[i] == 'u') vow++;
				else conso++;
			}
			if(vow >= 1 && conso >= 2) return true;
			return false;
		}
		public void Select(int idx) {
			storage[sp] = A[idx];
			sp++;
		}
		public String toString() {
			return new String(storage);
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
