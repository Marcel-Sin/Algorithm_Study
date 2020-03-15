import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		Vector<Integer> v = new Vector<Integer>();
		StringTokenizer stk;
		int col=-1,row=-1;

		while(true) {
			stk = new StringTokenizer(io.inputStr());
			col = nextInt(stk);
			row = nextInt(stk);
			if(row == 0) break;
			int[][] map = new int[row][col];
			boolean[][] check = new boolean[row][col];
			for(int i=0; i<row; i++) {
				stk = new StringTokenizer(io.inputStr());
				for(int j=0; j<col; j++) map[i][j] = nextInt(stk);
			}
			
			for(int i=0; i<row; i++) {
				for(int j=0; j<col; j++) {
					if(map[i][j] == 1 && check[i][j] == false) {
						Node visit = new Node(i,j);
						v.add(BFS(map,check,visit));
					}
				}
			}
			System.out.println(v.size());
			v.clear();
		}
		
		
		
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	static int BFS(int[][] map, boolean[][] check, Node start) {
		int counter = 0;
		Queue<Node> nodeQueue = new LinkedList<Node>();
		check[start.row][start.col] = true;
		nodeQueue.add(start);
		
		while(nodeQueue.isEmpty() == false) {
			Node curPos = nodeQueue.poll();
			counter++;
			SurroundingTravel(nodeQueue, curPos, map, check);
		}
		return counter;
	}
	
	static void SurroundingTravel(Queue<Node> que, Node cur,int[][] map,boolean[][] check) {
		int rowMin = (cur.row-1 < 0) ? 0:(cur.row-1);
		int rowMax = (cur.row+1 >= map.length) ? map.length-1:cur.row+1;
		int colMin = (cur.col-1 < 0) ? 0:(cur.col-1);
		int colMax = (cur.col+1 >= map[0].length) ? map[0].length-1:cur.col+1;
		
		for(int i = rowMin; i <= rowMax; i++) {
			for(int j = colMin; j <= colMax; j++) {
				if(map[i][j] == 1 && check[i][j] == false) {
					Node newNode = new Node(i,j);
					check[i][j] = true;
					que.add(newNode);
				}
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
