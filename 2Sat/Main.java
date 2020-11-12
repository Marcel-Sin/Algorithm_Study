import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static final int MAX_LETTER = 26; 
	static final int MAX_VERTEX = 8; 
	
	
	static int[][] matrix = new int[MAX_VERTEX][MAX_VERTEX];
	static Stack<Integer> stack = new Stack<Integer>();
	static int[] discover = new int[MAX_VERTEX];
	static boolean[] sccAssigned = new boolean[MAX_VERTEX];
	static int idCount,sccCount;

	
	/*
	 * [문제]
	 * (x2 ∨ ￢x1) ∧ (￢x1 ∨ ￢x2) ∧ (x1 ∨ x3) ∧ (￢x2 ∨ ￢x3) ∧ (x1 ∨ x4)
	 * ~x2 -> ~x1 , ~~x1 -> x2
	 * ~~x1 -> ~x2, ~~x2 -> ~x1
	 * ~x1 -> x3, ~x3-> x1
	 * ~~x2 -> ~x3, ~~x3 -> ~x2
	 * ~x1 -> x4, ~x4-> x1 
	 */
	
	
	public static void main(String[] args) throws IOException {	
		
		Init();
		
	}
	
	static void Init() throws IOException{
		for(int i = 0; i < MAX_VERTEX; i++) Arrays.fill(matrix[i], 0);
		Arrays.fill(discover, -1);
		Arrays.fill(sccAssigned, false);
		idCount = 0;
		sccCount = 0;
		stack.clear();
		/*
		 * [문제]
		 * (x2 ∨ ￢x1) ∧ (￢x1 ∨ ￢x2) ∧ (x1 ∨ x3) ∧ (￢x2 ∨ ￢x3) ∧ (x1 ∨ x4)
		 * ~x2 -> ~x1 , x1 -> x2
		 * x1 -> ~x2, x2 -> ~x1
		 * ~x1 -> x3, ~x3-> x1
		 * x2 -> ~x3, x3 -> ~x2
		 * ~x1 -> x4, ~x4-> x1 
		 * [0,1] [2,3] [4,5] [6,7]
		 */
		Connect(3, 1); Connect(0, 2);
		Connect(0, 3); Connect(2, 1);
		Connect(1, 4); Connect(5, 0);
		Connect(2, 5); Connect(4, 3);
		Connect(1, 6); Connect(7, 0);
		
		
		
		int[] ans = Two_SAT();
		if(ans != null) {
			System.out.println("OKAY");
			for(int i = 0; i < ans.length; i++) {
				System.out.print(ans[i] + " ");
			}
		}
		
	}

	static int[] Two_SAT() {
		ArrayList<ArrayList<Integer>> tarjan = tarjanScc();
		for(int i = 0; i < tarjan.size(); i++) {
			int first = tarjan.get(i).get(0);
			int second = 0;
			for (int j = 1; j < tarjan.get(i).size(); j++) {
				second = tarjan.get(i).get(j);
				//정답이 존재하지 않는다. 사이클에 해당 변수에 대한 참,거짓 둘다 존재.
				if(first/2 == second/2 && first == second+1) return null;
				first = second;
			}
		}
		
		//정답은 존재한다.
		int[] ans = new int[MAX_VERTEX];
		Arrays.fill(ans, -1);
		
		//위상정렬
		Collections.reverse(tarjan);
		
		for(int i = 0; i < tarjan.size(); i++) {
			for (int j = 0; j < tarjan.get(i).size(); j++) {
				int vertex = tarjan.get(i).get(j);
				//이미 어느 쪽에 의해 할당 되었다.
				if(ans[vertex] != -1) continue;
				
				//일반 정점일 경우,
				if(vertex%2 == 0) {
					ans[vertex] = 0; //0, false
					ans[vertex+1] = 1; //1, true
				}
				//Not 정점일 경우,
				else {
					ans[vertex] = 0; //0, false
					ans[vertex-1] = 1; //1, true
				}
			}
		}
		
		return ans;
	}
	
	
	// 반환은 SCC 묶음에 대한 리스트
	static ArrayList<ArrayList<Integer>> tarjanScc() {
		ArrayList<ArrayList<Integer>> tarjan_ret = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < MAX_VERTEX; i++) {
			if(discover[i] == -1) SCC(i,tarjan_ret);
		}
		return tarjan_ret;
	}
	
	// 반환은 최소 방문 가능 지점, 그와 동시에 SCC 끼리는 ArrayList로 묶임.
	static int SCC(int here,ArrayList<ArrayList<Integer>> sccList) {
		discover[here] = idCount++;
		stack.push(here);
		int ret = discover[here];
		
		for(int there = 0 ; there < MAX_VERTEX; there++) {
			if(matrix[here][there] == 1) {
				if(discover[there] == -1) {
					ret = Min(ret, SCC(there,sccList));
				}
				else if(sccAssigned[there] == false) ret = Min(ret,discover[there]); 
			}
		}
		
		if(ret == discover[here]) {
			sccList.add(new ArrayList<Integer>());
			while(true) {
				int popValue = stack.pop();
				sccAssigned[popValue] = true;
				sccList.get(sccCount).add(popValue);
				if(popValue == here) break; 
			}
			Collections.sort(sccList.get(sccCount));
			sccCount++;
		}
		
		return ret;
	}
	
	static void Connect(int a, int b) {
		matrix[a][b] = 1;
	}
	
	static class Pair {
		public int key,value;

		public Pair(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}	
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