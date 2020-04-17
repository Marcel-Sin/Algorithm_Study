import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int A,B,ANSWER;
	static boolean[] check = new boolean[100001];
	public static void main(String[] args) throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		A = nextInt(stk);
		B = nextInt(stk);
		ANSWER = Math.abs(B-A);
		BFS();
		System.out.println(ANSWER);
	}	

	
	static void BFS() {
		Queue<Finder> queue = new LinkedList<Finder>();
		check[A] = true;
		queue.add(new Finder(A,0));
		while(queue.isEmpty() == false) {
			Finder parent = queue.poll();
			if(parent.count > ANSWER) continue;
			if(parent.value == B) {
				if(parent.count < ANSWER) {
					ANSWER = parent.count;
				}
			}
			int next01 = parent.value*2;
			int next02 = parent.value+1;
			int next03 = parent.value-1;
			int nextCount = parent.count+1;
			if( next01 <= 100000 && check[next01] == false) {
				check[next01] = true;
				queue.add(new Finder(next01,nextCount));
			}
			if( next02 <= 100000 && check[next02] == false) {
				check[next02] = true;
				queue.add(new Finder(next02,nextCount));
			}
			if( next03 >= 0 && check[next03] == false) {
				check[next03] = true;
				queue.add(new Finder(next03,nextCount));
			}
			
		}
	}
	

	static class Finder{
		int value;
		int count;
		public Finder(int value, int count) {
			super();
			this.value = value;
			this.count = count;
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
