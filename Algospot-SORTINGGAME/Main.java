import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 

	static int TOTAL,N;
	static HashMap<Integer, Integer> preCalc = new HashMap<Integer, Integer>(); 
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] problem;
	static StringBuilder strs = new StringBuilder();
	
	public static void main(String[] args) throws IOException {	
		int[] arr = {0,1,2,3,4,5,6,7};
		BFS(arr,8);
		
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			Solve();
		}
		System.out.print(strs.toString());
	}
	
	
	static void Init() throws IOException{
		// Clear
		N = io.inputInt();
		int[] temp = new int[N];
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; stk.hasMoreTokens(); i++) temp[i] = nextInt(stk);
		problem = temp;
	}
	
	static void Solve() {
		problem = Relative_Array(problem, problem.length);
		strs.append(preCalc.get(Hashing(problem,8)));
		strs.append('\n');
	
	}
	
	static int[] Relative_Array(int[] arr,int len) {
		int[] ret = {0,1,2,3,4,5,6,7};
		int smaller;
		
		for (int s = 0; s < len; s++) {
			smaller = 0;
			for (int i = 0; i < len; i++) {
				if(arr[s] > arr[i]) smaller++;
			}
			ret[s] = smaller;
		}
		return ret;
	}
	static void BFS(int[] problem,int len) {
		queue.add(problem);
		preCalc.put(Hashing(problem,len), 0);
		
		int counter = 0, qsize = 0,code;
		int[] origin = problem;
		while(!queue.isEmpty()) {
			qsize = queue.size();
			for(int i = 0; i < qsize; i++) {
				origin = queue.poll();
				
				// 모두 다해봄
				for(int o = 1; o < len; o++) {
					for(int s = 0; s+o < len; s++) {
						Reverse(origin,s,o);
						code = Hashing(origin,len);
						if(preCalc.containsKey(code) == false) {
							preCalc.put(code, counter+1);
							queue.add(origin.clone());
						}
						Reverse(origin,s,o);
					}
				}
				// 다해봄 끝
			}
			counter++;
		}
	}
	
	
	static void Reverse(int[] ret, int start, int offset) {
		int i = start, j = start+offset, temp = 0;
		
		while(i < j) {
			temp = ret[i];
			ret[i] = ret[j];
			ret[j] = temp;
			i++;
			j--;
		}
	}
	static boolean IsSorted(int[] arr,int len) {
		for(int i = 1; i < len; i++) {
			if(arr[i-1] > arr[i]) return false;
		}
		return true;
	}
	
	static int Hashing(int[] arr, int len) {
		int ret = arr[0];
		for(int i = 1; i < len; i++) {
			ret = ret << 3;
			ret = ret | arr[i];
			
		}
		return ret;
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