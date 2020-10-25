import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static int TOTAL,N,Q,NODE_NUMBERING;
	static ArrayList<Integer>[] DATA = new ArrayList[100000];
	static ArrayList<Integer> CASE_PATH = new ArrayList<Integer>();
	
	//DATA_TO_NODE[데이터 인덱스] = 노드 인덱스
	static int[] DATA_TO_NODE = new int[100000];
	
	//Node index : 전위순회 시 매겨지는 넘버링
	//Data index : 실제 데이터에 붙여진 넘버링
	static int[] NODE_FIRST_VISITED = new int[100000];
	static int[] NODE_DEPTH = new int[100000];
	
	static Ranged_Min_Tree rtree;
	
	static StringBuilder sb;
	
	
	public static void main(String[] args) throws IOException {
		BeforeInit();
		TOTAL = io.inputInt();
		for(int a = 0; a < TOTAL; a++) {
			Init();
			sb = new StringBuilder();
			for(int b = 0; b < Q; b++) {
				StringTokenizer stk = new StringTokenizer(io.inputStr());
				sb.append(Solve(nextInt(stk),nextInt(stk))+"\n");
			}
			System.out.println(sb.toString());
		}
		

		
	}
	
	static void BeforeInit() {
		//for(int i = 0; i < 100000; i++) DATA[i] = new ArrayList<Integer>();
		rtree = new Ranged_Min_Tree(800000);
	}
	static void Init() throws IOException  {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		Q = nextInt(stk);
		
		NODE_NUMBERING = 0;
		CASE_PATH.clear();
		Arrays.fill(DATA_TO_NODE, -1);
		for(int i = 0; i < N; i++) DATA[i] = new ArrayList<Integer>();
		

		stk = new StringTokenizer(io.inputStr());
		for(int i = 1; stk.hasMoreTokens(); i++) DATA[nextInt(stk)].add(i);
		
		Traversal(0,0);
		
		rtree.Setup(CASE_PATH);
	}
	static void Traversal(int data_idx,int depth) {
		if(DATA_TO_NODE[data_idx] == -1) {
			DATA_TO_NODE[data_idx] = NODE_NUMBERING;
			NODE_FIRST_VISITED[NODE_NUMBERING] = CASE_PATH.size();
			NODE_DEPTH[NODE_NUMBERING] = depth;
			NODE_NUMBERING++;
		}
		CASE_PATH.add(DATA_TO_NODE[data_idx]);
		for(int i = 0; i < DATA[data_idx].size(); i++) {
			Traversal(DATA[data_idx].get(i),depth+1);
			CASE_PATH.add(DATA_TO_NODE[data_idx]);
		}
	}
	
	static int LCA(int a,int b) {
		return rtree.Query(a, b);
	}
	
	static int Solve(int data_idx1,int data_idx2 ) {
		int n1 = DATA_TO_NODE[data_idx1];
		int n2 = DATA_TO_NODE[data_idx2];
		
		//구간 [i, j]
		int i = NODE_FIRST_VISITED[n1];
		int j = NODE_FIRST_VISITED[n2];
		int lca = LCA(i,j);
		
		return NODE_DEPTH[n1]+NODE_DEPTH[n2]-(NODE_DEPTH[lca]*2);
	}
	
	static class Ranged_Min_Tree {
		int[] treeData;
		int treeSize;
		
		public Ranged_Min_Tree(int size) {
			treeData = new int[size];
		}
		
		public void Setup(ArrayList<Integer> arr) {
			//Arrays.fill(treeData, INF);
			treeSize = arr.size();
			Init(arr,0,0,treeSize-1);
		}
		public void Init(ArrayList<Integer> arr,int node, int nodeLeft, int nodeRight) {
			// only a node
			if(nodeLeft == nodeRight) {
				treeData[node] = arr.get(nodeLeft);
				return;
			}
			// else for nodes
			int mid = (nodeLeft+nodeRight)/2, ln = node*2+1, rn = node*2+2;
			Init(arr, ln , nodeLeft, mid);
			Init(arr, rn , mid+1, nodeRight);
			
			treeData[node] = Min(treeData[ln],treeData[rn]);
			return;
		}
		public int Query(int i, int j, int node, int nodeLeft, int nodeRight ) {
			if( nodeRight < i || j < nodeLeft) return INF;
			if(i <= nodeLeft && nodeRight <= j) return treeData[node];
			
			int mid = (nodeLeft+nodeRight)/2, ln = node*2+1, rn = node*2+2;
			int leftResult = Query(i,j,ln,nodeLeft,mid);
			int rightResult = Query(i,j,rn,mid+1,nodeRight);
			return Min(leftResult,rightResult);
		}
		public int Query(int i, int j) {
			if(i < j) return Query(i,j,0,0,treeSize-1);
			else return Query(j,i,0,0,treeSize-1);
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