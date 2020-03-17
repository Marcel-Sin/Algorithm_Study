import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		Vector<Node> passNode = new Vector<Node>();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int row=nextInt(stk),col = nextInt(stk);
		int[][] maze = new int[row][col];
		
		for(int i=0; i<row; i++) {
			char[] temp = io.inputStr().toCharArray();
			for(int j=0; j<col; j++) maze[i][j] = temp[j]-48;
		}
		
		BFS(maze,passNode,new Node(0,0,0));
		System.out.println(passNode.elementAt(0).past);
		
		
		
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static void BFS(int[][] map, Vector<Node> passedQueue, Node start) {
		Queue<Node> nodeQueue = new LinkedList<Node>();
		map[start.row][start.col] = 2;
		nodeQueue.add(start);
		while(nodeQueue.isEmpty() == false) {
			Node curPos = nodeQueue.poll();
			curPos.past += 1;
			if(curPos.row == map.length-1 && curPos.col == map[0].length-1) passedQueue.add(curPos);
			FindingPath(map, nodeQueue, curPos);
		}
	}
	
	static void FindingPath(int[][] map, Queue<Node> queue,Node cur) {
		Node[] dir = new Node[4];
		if(cur.row-1 >= 0 && map[cur.row-1][cur.col] == 1) dir[0] = new Node(cur.row-1,cur.col,cur.past);
		if(cur.col-1 >= 0 && map[cur.row][cur.col-1] == 1) dir[1] = new Node(cur.row,cur.col-1,cur.past);
		if(cur.row+1 < map.length && map[cur.row+1][cur.col] == 1) dir[2] = new Node(cur.row+1,cur.col,cur.past);
		if(cur.col+1 < map[0].length && map[cur.row][cur.col+1] == 1) dir[3] = new Node(cur.row,cur.col+1,cur.past);
		
		for(int i = 0; i<dir.length; i++) {
			if(dir[i] != null) {
				map[dir[i].row][dir[i].col] = 2;
				queue.add(dir[i]);
			}
		}
	}
	
}

class Node {
	public int row;
	public int col;
	public int past;
	public Node(int row, int col, int past) {
		super();
		this.row = row;
		this.col = col;
		this.past = past;
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
