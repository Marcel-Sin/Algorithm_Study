import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 4;
	static final int INF = Integer.MAX_VALUE;

	static int N,K;
	static SegmentTree stree;
	
	public static void main(String[] args) throws IOException {
		Init();
		Solve();
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		K = nextInt(stk)-1;
		stree = new SegmentTree(N);
	}
	
	static void Solve() {
		int kth = 0;
		int deletePos = 0;
		int mod = N;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		
		for (int i = 0; i < N; i++) {
			kth = (kth + K) % mod;
			deletePos = stree.Kth_Query(kth);
			stree.Zero_Update(deletePos);
			answer.add(deletePos+1);
			mod--;
		}
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		for (int i = 0; i < answer.size()-1; i++) {
			sb.append(answer.get(i));
			sb.append(',');
			sb.append(' ');
		}
		sb.append(answer.get(answer.size()-1));
		sb.append('>');
		System.out.println(sb.toString());
	}
	

	static class  SegmentTree {
		public int arr[];
		int size;
		
		public SegmentTree(int size) {
			super();
			this.size = size;
			arr = new int[size*4];
			Init(1,0,size-1);
		}
		
		public int Init(int node, int nodeLeft, int nodeRight) {
			if(nodeLeft == nodeRight) return arr[node] = 1;
			int mid = (nodeLeft+nodeRight)/2;
			return arr[node] = Init(node*2,nodeLeft,mid)+Init(node*2+1,mid+1,nodeRight);
		}
		
		public int Kth_Query(int k, int node, int nodeLeft,int nodeRight) {
			if(nodeLeft == nodeRight) return nodeLeft;
			int mid = (nodeLeft+nodeRight)/2;
			if(k <= arr[node*2]) return Kth_Query(k, node*2, nodeLeft, mid);
			else return Kth_Query(k-arr[node*2], node*2+1, mid+1,nodeRight);
		}
		
		public int Zero_Update(int pos,int node, int nodeLeft,int nodeRight) {
			if(pos < nodeLeft || nodeRight < pos) return arr[node];
			if(nodeLeft == nodeRight) return arr[node] = 0;
			int mid = (nodeLeft+nodeRight)/2;
			return arr[node] = Zero_Update(pos, node*2, nodeLeft,mid)+Zero_Update(pos, node*2+1, mid+1, nodeRight);
		}
		
		public void Zero_Update(int pos) {
			Zero_Update(pos, 1, 0,size-1);
		}
		public int Kth_Query(int k) {
			return Kth_Query(k+1, 1, 0, size-1);
		}
		
	}


	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
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
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("[" + i + "] : ");
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