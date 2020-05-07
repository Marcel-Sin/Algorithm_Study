import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int INF = Integer.MAX_VALUE;
	static int N,S;
	static int[] A;
	static ArrayList<Integer> v1 = new ArrayList<Integer>();
	static ArrayList<Integer> v2 = new ArrayList<Integer>();
	static HashSet<Integer> v1key = new HashSet<Integer>();
	static HashSet<Integer> v2key = new HashSet<Integer>();
	public static void main(String[] args) throws IOException {
		Init();
		
		if(N == 1) {
			if(A[0] == S) System.out.println(1);
			else System.out.println(0);
		}
		else if(N <= 20){
			DFS(-1, 0, N, v1,v1key);
			Collections.sort(v1);
			System.out.println(Upper_Bound(v1, S)-Lower_Bound(v1, S));
		}
		else {
		
			int mid = N/2;
			long counter = 0;
			DFS(-1,0,mid,v1,v1key);
			DFS(mid-1,0,N,v2,v2key);
			Collections.sort(v1);
			Collections.sort(v2);

			counter += (Upper_Bound(v1,S)-Lower_Bound(v1,S));
			counter += (Upper_Bound(v2,S)-Lower_Bound(v2,S));
			
			for(int x : v1key) {
				int rest = S-x;
				if(v2key.contains(rest)) {
					int v1_counting = (Upper_Bound(v1,x)-Lower_Bound(v1,x));
					int v2_counting = (Upper_Bound(v2,rest)-Lower_Bound(v2,rest));
					counter += (long)v1_counting*(long)v2_counting;
				}
			}
			System.out.println(counter);
		
		}
	}

	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		N = nextInt(stk);
		S = nextInt(stk);
		A = new int[N];
		stk = new StringTokenizer(io.inputStr());
		for(int i = 0; i < N; i++) A[i] = nextInt(stk);
	}
	
	static void DFS(int start,int sum, int end, ArrayList<Integer> v,HashSet<Integer> h) {
		if(start == end) return;
		
		for(int i = start+1; i < end; i++) {
			int n = sum+A[i];
			DFS(i,n,end,v,h);
			v.add(n);
			h.add(n);
		}
	}
	
	static int Lower_Bound(ArrayList<Integer> a, int target) {
		int low=0,high=a.size()-1,mid = 0;
		while(low < high) {
			mid = (low+high)/2;
			if(a.get(mid) < target) low = mid+1;
			else high = mid;
		}
		if(a.get(high) != target) return Integer.MIN_VALUE;
		else return high;
	}
	static int Upper_Bound(ArrayList<Integer> a, int target) {
		int low=0,high=a.size()-1,mid = 0;
		while(low < high) {
			mid = (low+high)/2;
			if(a.get(mid) <= target) low = mid+1;
			else high = mid;
		}
		if(a.get(high) == target) return high+1;
		else if(high-1 < 0) return Integer.MIN_VALUE;
		else if(a.get(high-1) != target) return Integer.MIN_VALUE;
		return high;
	}

	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
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
