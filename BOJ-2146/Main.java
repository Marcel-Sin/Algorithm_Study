import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		StringTokenizer stk;
		Vector<Integer> v = new Vector<Integer>();
		int sizeN = io.inputInt(); 
		int[][] map = new int[sizeN][sizeN];
		
		for(int i=0; i<sizeN; i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j=0; j<sizeN; j++) {
				map[i][j] = nextInt(stk);
			}
		}
		
		int lastContin = Connection(map);
		int curContin = 2;
		while(curContin <= lastContin) {
			for(int i=0; i<sizeN; i++) {
				for(int j=0; j<sizeN; j++) {
					if(map[i][j] == curContin) {
						Bridge(map, v, new Node(i,j));
						BridgeClear(map);
						curContin++;
					}
				}		
			}
		}
		Collections.sort(v);
		System.out.println(v.elementAt(0));
		
	}
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static public int Connection(int[][] map) {
		Queue<Node> nodeQueue = new LinkedList<Node>();
		int continent = 1;
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1) {
					continent++;
					
					map[i][j] = continent;
					Node newVisit = new Node(i,j);
					nodeQueue.add(newVisit);
					while(nodeQueue.isEmpty() == false) {
						Node visit = nodeQueue.poll();
						MakingContin(map, nodeQueue, visit, continent);
					}
				}
			}
		}
		return continent;
	}
	
	static public void BridgeClear(int[][] map) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == -1) map[i][j] = 0;
			}
		}
	}
	
	static public void Bridge(int[][] map,Vector<Integer> storage,Node start) {
		Queue<Node> bridgeQueue = new LinkedList<Node>();
		int bridgeSize = 0;
		int contin = map[start.row][start.col];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[0].length; j++) {
				if(map[i][j] == contin) bridgeQueue.add(new Node(i,j));
			}
		}
		while(bridgeQueue.isEmpty() == false) {
			int loop = bridgeQueue.size();
			for(int i=0; i<loop; i++) {
				Node visit = bridgeQueue.poll();
				if(MakingBridge(map, bridgeQueue, visit, contin) == false) {
					bridgeQueue.clear();
					storage.add(bridgeSize);
					break;
				}
			}
			bridgeSize++;
		}
		
	}
	
	public static boolean MakingBridge(int[][] map, Queue<Node> queue,Node cur,int contin) {
		Node[] dir = new Node[4];
		

		if(cur.row-1 >= 0 ) dir[0] = new Node(cur.row-1,cur.col); //south
		if(cur.col-1 >= 0 ) dir[1] = new Node(cur.row,cur.col-1); //west
		if(cur.row+1 < map.length ) dir[2] = new Node(cur.row+1,cur.col); //north
		if(cur.col+1 < map[0].length ) dir[3] = new Node(cur.row,cur.col+1); //east
		
		for(int i = 0; i < dir.length; i++) {
			if(dir[i] != null && map[dir[i].row][dir[i].col] != contin && map[dir[i].row][dir[i].col] > 1) {
				return false;
			}
		}
		
		for(int i = 0; i < dir.length; i++) {
			if(dir[i] != null && map[dir[i].row][dir[i].col] == 0) {
				map[dir[i].row][dir[i].col] = -1;
				queue.add(dir[i]);
			}
		}
		return true;
	}

	static void MakingContin(int[][] map, Queue<Node> queue,Node cur,int contin) {
		Node[] dir = new Node[4];
		

		if(cur.row-1 >= 0 ) dir[0] = new Node(cur.row-1,cur.col); //south
		if(cur.col-1 >= 0 ) dir[1] = new Node(cur.row,cur.col-1); //west
		if(cur.row+1 < map.length ) dir[2] = new Node(cur.row+1,cur.col); //north
		if(cur.col+1 < map[0].length ) dir[3] = new Node(cur.row,cur.col+1); //east
		
		
		for(int i = 0; i < dir.length; i++) {
			if(dir[i] != null && map[dir[i].row][dir[i].col] == 1) {
				map[dir[i].row][dir[i].col] = contin;
				queue.add(dir[i]);
			}
		}
	}
	
	
}

class Node {
	public int row;
	public int col;
	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
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
