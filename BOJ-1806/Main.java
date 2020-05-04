import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int SIZE,TARGET,COUNT = 99999999;
	static int[] A;
	public static void main(String[] args) throws IOException {
		Initializing();
		int start=0,end=0,sum = 0;
		
		// start(inclusive) , end (exclusive)
		while(true) {
			if (sum >= TARGET) sum -= A[start++];
			else if( end == SIZE) break;
			else sum += A[end++];
			if(sum >= TARGET) {
				int len = end-start;
				if(len < COUNT) COUNT = len;
			}
		}	
		if(COUNT == 99999999) System.out.println(0);
		else System.out.println(COUNT);
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		SIZE = nextInt(stk);
		TARGET = nextInt(stk);
		stk = new StringTokenizer(io.inputStr());
		A = new int[SIZE];
		for(int i = 0; i < SIZE; i++) A[i] = nextInt(stk);
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
