import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<Integer, Node> graph = new HashMap<Integer, Node>();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int N = Integer.parseInt(stk.nextToken());
		int M = Integer.parseInt(stk.nextToken());
		
		for(int i=1; i <= N; i++) graph.put(i,new Node(graph,i));
		
		for(int i=0; i < M; i++) {
			stk = new StringTokenizer(io.inputStr());
			Join_Eachother(graph,Integer.parseInt(stk.nextToken()),Integer.parseInt(stk.nextToken()));
		}
		
		int nextVisit = 1, connect = 0;
		while(nextVisit != -1) {
			if (BFS(graph,nextVisit)) connect++;
			nextVisit = Unvisit(graph,1,N);
		}
		System.out.println(connect);
	}
	
	
	
	
	static public void Join_Eachother(HashMap<Integer,Node> graph, int a, int b) {
		graph.get(a).links.add(b);
		graph.get(b).links.add(a);
	}
	static public void ClearVisit(HashMap<Integer,Node> graph) {
		for(int key : graph.keySet()) graph.get(key).checked = false;
	}
	static public int Unvisit(HashMap<Integer,Node> graph,int start, int end) {
		int result = -1;
		for(int i = start; i <= end; i++) {
			if(graph.get(i).checked == false) {
				return i;
			}
		}
		return result;
		
	}
	
	static public boolean BFS(HashMap<Integer,Node> graph, int start) {
		Queue<Node> nodeQueue = new LinkedList<Node>();
		nodeQueue.add(graph.get(start));
		graph.get(start).checked = true;
		int vertex = 0;
		while(nodeQueue.size() != 0) {
			vertex++;
			Node parent = nodeQueue.poll();
			for (int key : parent.links) {
				Node nextNode = graph.get(key);
				if (nextNode.checked == false) {
					nextNode.checked = true;
					nodeQueue.add(nextNode);
				}
			}
		}
		return true;
		
	}
}

class Node {
	public int name;
	public boolean checked;
	public HashMap<Integer, Node> graph;
	public SortedSet<Integer> links;

	public Node(HashMap<Integer, Node> graph, int name) {
		this.checked = false;
		this.graph = graph;
		this.name = name;
		this.links = new TreeSet<Integer>();
	}
	public void Action() {
		System.out.print(name +" ");
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
