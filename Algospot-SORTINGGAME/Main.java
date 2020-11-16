import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 

	static int TOTAL,N;
	
	static Queue<int[]> queue = new LinkedList<int[]>();
	
	public static void main(String[] args) throws IOException {	
		
		Init();
		
	}
	
	static void Init() throws IOException{
		// Clear
		N = io.inputInt();
		int[] temp = new int[N];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; stk.hasMoreTokens(); i++) temp[i] = nextInt(stk);
		
		System.out.println(BFS(temp,N));
		
	}
	
	static int BFS(int[] problem,int len) {
		if (len == 1) return 0;
		queue.add(problem);
		
		int ret = 0, qsize = 0;
		int[] origin = problem;
		while(true) {
			qsize = queue.size();
			for(int i = 0; i < qsize; i++) {
				origin = queue.poll();
				if(IsSorted(origin, len) == true) return ret;
				for (int o = 1; o < len; o++) {
					for (int s = 0; s+o < len; s++) {
						queue.add(Swap_Area(origin,s,o));
					}
				}
			}
			ret++;
		}
	}
	
	static int[] Swap_Area(int[] arr, int start, int offset) {
		int[] ret = arr.clone();
		int i = start, j = start+offset, temp = 0;
		
		while(i < j) {
			temp = ret[i];
			ret[i] = ret[j];
			ret[j] = temp;
			i++;
			j--;
		}
		return ret;
	}
	static boolean IsSorted(int[] arr,int len) {
		for(int i = 1; i < len; i++) {
			if(arr[i-1] > arr[i]) return false;
		}
		return true;
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
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
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