import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		HashMap<Integer, HashSet<Integer>> graph = new HashMap<Integer, HashSet<Integer>>();
		StringTokenizer stk;
		boolean[] check = new boolean[1001];
		
		int totalCase = io.inputInt();
		for(int k = 0; k < totalCase; k++) {
			
			int numberCount = io.inputInt();
			int result = 0;
			stk = new StringTokenizer(io.inputStr());
			for (int i = 1; i <= numberCount; i++) {
				int linkNode = Integer.parseInt(stk.nextToken());
				if(i == linkNode) {
					result++;
					check[i] = true;
					continue;
				}
				graph.put(i, new HashSet<Integer>() );
				graph.get(i).add(linkNode);
			}
			int nextVisit = NextUnvisit(check,numberCount);
			while(nextVisit != -1) {
				if(BFS(graph,check,nextVisit)) result++;
				nextVisit = NextUnvisit(check,numberCount);
			}
			System.out.println(result);
			graph.clear();
			ClearCheck(check);
		}
		
	}
	
	
	
	static public void ClearCheck(boolean[] barr) {
		for (int i = 1; i < barr.length; i++) barr[i] = false;
	}
	
	static public int NextUnvisit(boolean[] check,int max) {
		for(int i = 1; i <= max; i++) {
			if(check[i] == false) return i;
		}
		return -1;
	}
	
	static public boolean BFS(HashMap<Integer, HashSet<Integer>> graph, boolean[] check, int start) {
		int startingNode = start;
		check[start] = true;
		Queue<Integer> nodeQueue = new LinkedList<Integer>();
		nodeQueue.add(start);
		
		while(nodeQueue.isEmpty() == false) {
			int parent = nodeQueue.poll();
			for(int nextNode : graph.get(parent)) {
				if (nextNode == startingNode) return true;	
				else if (check[nextNode] == false) {
					check[nextNode] = true;
					nodeQueue.add(nextNode);
				}
			}
		}
		return false;
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
