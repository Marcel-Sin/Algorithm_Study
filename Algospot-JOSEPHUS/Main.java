import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int N,K;
	static LinkedList<Integer> list = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			Solve(N,K);
		}
		
	}
	
	static void Solve(int n, int k) {
		for(int i = 1; i <= n; i++) list.add(i);
		
		ListIterator<Integer> iter = list.listIterator();
		iter.next();
		iter.remove();
		
		while(list.size() > 2) {
			for(int skip = k; skip > 0; skip--) {
				if(iter.hasNext() == false) {
					iter = list.listIterator();
					iter.next();
				}
				else {
					iter.next();
				}
			}
			iter.remove();
		}
		System.out.printf("%d %d\n",list.get(0),list.get(1));
	}
	
	
	static void Init() throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		list.clear();
		N = nextInt(stk);
		K = nextInt(stk);
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b)?b:a;
	}
	static long Max(long a, long b) {
		return (a > b)?a:b;
	}
	static int Min(int a, int b) {
		return (a > b)?b:a;
	}
	static int Max(int a, int b) {
		return (a > b)?a:b;
	}
	static void Display(int[] arr,int limit) {
		System.out.println("요소갯수 : " + arr.length);
		for(int i = 0; i < limit; i++) System.out.print(arr[i]+" ");
		System.out.println();
	}
	static void Display(int[][] arr,int limit) {
		System.out.println("요소갯수 : " + (arr.length*arr[0].length));
		for(int i = 0; i < limit; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+" ");
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
	}}
