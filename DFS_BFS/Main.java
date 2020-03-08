import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<String, Node> graph = new HashMap<String, Node>();
		graph.put("1", new Node(graph, "1", new String[] { "2", "3" }));
		graph.put("2", new Node(graph, "2", new String[] { "1", "3", "4", "5" }));
		graph.put("3", new Node(graph, "3", new String[] { "1", "2", "6", "7" }));
		graph.put("4", new Node(graph, "4", new String[] { "2" }));
		graph.put("5", new Node(graph, "5", new String[] { "2" }));
		graph.put("6", new Node(graph, "6", new String[] { "3" }));
		graph.put("7", new Node(graph, "7", new String[] { "3" }));
		
		System.out.println("-----------------");
		dfs(graph,"1");
	}
	
	static public void bfs(HashMap<String, Node> graph, String start) {
		Queue<Node> que = new LinkedList<Node>();
		que.add(graph.get(start));
		graph.get(start).checked = true;
		
		while (que.size() != 0) {
			Node parent = que.poll();
			parent.Action();
			for (String key : parent.links) {
				Node nextNode = graph.get(key);
				if (nextNode.checked == false) {
					nextNode.checked = true;
					que.add(nextNode);
				}
			}
		}
	}
	static public void dfs(HashMap<String, Node> graph, String pos) {
		Node parent = graph.get(pos);
		parent.Action();
		parent.checked = true;
		for (String nextNode : parent.links) {
			if (graph.get(nextNode).checked == false) {
				dfs(graph,nextNode);
			}
		}
	}
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
}

class Node {
	public String name;
	public boolean checked = false;
	public HashMap<String, Node> graph;
	public Vector<String> links;

	public Node(HashMap<String, Node> graph, String name) {
		super();
		this.graph = graph;
		this.name = name;
		this.links = new Vector<String>();
	}
	public Node(HashMap<String, Node> graph, String name, String[] linking) {
		super();
		this.graph = graph;
		this.name = name;
		this.links = new Vector<String>();
		for (String key : linking) {
			links.add(key);
		}
	}
	public void Action() {
		System.out.print(name + " ");
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
