import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		Queue<Node> nodeQueue = new LinkedList<Node>();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int col=nextInt(stk),row=nextInt(stk);
		int[][] tomato = new int[row][col];
		Node justCounter = new Node(0,0);
		int resultTomato = 0;
		
		for(int i=0; i<row;i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j=0; j<col; j++) { 
				tomato[i][j] = nextInt(stk);
				if(tomato[i][j] == 0) resultTomato++;
				else if(tomato[i][j] == 1) { 
					resultTomato++;
					Node start = new Node(i,j);
					tomato[i][j] = 2;
					nodeQueue.add(start);
				}
			}
		}
		BFS(tomato,justCounter,nodeQueue);
		int result = resultTomato - justCounter.row;
		if(result != 0) System.out.println(-1);
		else System.out.println(justCounter.col-1);
		
		
		
		
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static void BFS(int[][] tomato, Node counter, Queue<Node> turnQueue) {
		while (turnQueue.isEmpty() == false) {
			int fix = turnQueue.size();
			for (int i = 0; i < fix; i++) {
				Node visit = turnQueue.poll();
				counter.row++;
				FindingNext(tomato, turnQueue, visit);
			}
			counter.col++;
		}
		
	}
	
	static void FindingNext(int[][] tomato, Queue<Node> turnQueue, Node curPos) {
		Node[] dirs = new Node[4];
		if(curPos.row-1 >= 0 && tomato[curPos.row-1][curPos.col] == 0) dirs[0] = new Node(curPos.row-1,curPos.col);
		if(curPos.row+1 < tomato.length && tomato[curPos.row+1][curPos.col] == 0) dirs[1] = new Node(curPos.row+1,curPos.col);
		if(curPos.col-1 >= 0 && tomato[curPos.row][curPos.col-1] == 0) dirs[2] = new Node(curPos.row,curPos.col-1);
		if(curPos.col+1 < tomato[0].length && tomato[curPos.row][curPos.col+1] == 0) dirs[3] = new Node(curPos.row,curPos.col+1);
		
		for(int i =0; i <dirs.length; i++) {
			if(dirs[i] != null) { 
				tomato[dirs[i].row][dirs[i].col] = 2;
				turnQueue.add(dirs[i]);
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
