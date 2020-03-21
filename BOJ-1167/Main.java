import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int totalvertex = io.inputInt();
		Node[] tree = new Node[totalvertex+1];
		StringTokenizer stk;
		for(int i=1; i<=totalvertex; i++) tree[i] = new Node(i);
		
		int from,to,dist;
		for(int i=1; i <= totalvertex; i++) {
			stk = new StringTokenizer(io.inputStr());
			from = nextInt(stk);
			while((to = nextInt(stk)) != -1) {
				dist = nextInt(stk);
				Join(tree,from,to,dist);
			}
		}
		BFS(tree,1);
		int root = MaxVertex(tree);
		ClearPast(tree);
		BFS(tree,root);
		root = MaxVertex(tree);
		System.out.println(tree[root].past);
	}

	public static int MaxVertex(Node[] tree) {
		int max = 1;
		for(int i=1; i<tree.length; i++) {
			if(tree[max].past < tree[i].past) max = i;
		}
		return max;
	}
	
	public static void ClearPast(Node[] tree) {
		for(int i=1; i<tree.length; i++) tree[i].past = 0;
	}
	
	public static void Join(Node[] tree, int from, int to,int dist) {
		tree[from].links.add(new NodeLink(to,dist));
	}
	
	public static void BFS(Node[] tree, int start) {
		boolean[] check = new boolean[tree.length+1];
		Queue<Node> nodeQueue = new LinkedList<Node>();
		nodeQueue.add(tree[start]);
		check[start] = true;
		
		while(nodeQueue.isEmpty() == false) {
			Node root = nodeQueue.poll();
			for(NodeLink link  :  root.links) {
				if(check[link.to]  == false) {
					tree[link.to].past = root.past + link.dist;
					check[link.to] = true;
					nodeQueue.add(tree[link.to]);
				}
			}
		}
		
	}
	
	public static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	

	
}

class Node {

	public int name;
	public int past;
	public Vector<NodeLink> links;
	
	public Node(int name) {
		this.name = name;
		this.past = 0;
		this.links = new Vector<NodeLink>();
	}
	
}

class NodeLink {

	public int to;
	public int dist;
	
	public NodeLink(int to, int dist) {
		super();
		this.to = to;
		this.dist = dist;
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
