import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int totalvertex = io.inputInt();
		
		@SuppressWarnings("unchecked")
		ArrayList<NodeLink>[] tree = new ArrayList[totalvertex+1];
		int[] past;
		boolean[] check;
		StringTokenizer stk;

		for(int i=1; i <= totalvertex; i++) 
			tree[i] = new ArrayList<NodeLink>();

		int from,to,dist;
		for(int i=1; i < totalvertex; i++) {
			stk = new StringTokenizer(io.inputStr());
			from = nextInt(stk);
			to = nextInt(stk);
			dist = nextInt(stk);
			Join(tree,from,to,dist);
			
		}
		MaxIndex longest = new MaxIndex();
		longest.value = 1;
		check = new boolean[totalvertex+1];
		past = new int[totalvertex+1];
		DFS(tree,past,check,longest,1);
		
		check = new boolean[totalvertex+1];
		past = new int[totalvertex+1];
		int leaf = longest.value;
		DFS(tree,past,check,longest,leaf);
		System.out.println(past[longest.value]);
	}

	public static void Join(ArrayList<NodeLink>[] tree, int from, int to,int dist) {
		tree[from].add(new NodeLink(to,dist));
		tree[to].add(new NodeLink(from,dist));
	}
	
	public static void DFS(ArrayList<NodeLink>[] tree, int[] past,boolean[] check ,MaxIndex max,int pos) {
		if(past[max.value] < past[pos]) max.value = pos;
		check[pos] = true;
		for(NodeLink link: tree[pos]) {
			if(check[link.to] == false) {
				past[link.to] = past[pos] + link.dist;
				DFS(tree,past,check,max,link.to);
			}
		}
	}
	
	public static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static class MaxIndex {
		int value;
	}
	
	static class NodeLink {

		int to;
		int dist;
		
		public NodeLink(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
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
