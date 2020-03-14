import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int N = io.inputInt();
		Vector<Integer> v = new Vector<Integer>();
		boolean[][] mapCheck = new boolean[N][];
		int[][] map = new int[N][];
		for(int i=0;i < N; i++) { 
			map[i] = new int[N];
			mapCheck[i] = new boolean[N]; 
		}
		
		char[] data;
		String str = "";
		for(int i=0; i < N; i++) {
			str = io.inputStr().trim();
			data = str.toCharArray();
			for(int j=0; j<N; j++) map[i][j] = data[j]-48;
		}
		
		for(int i=0; i < N; i++) {
			for(int j=0; j < N; j++) {
				if(map[i][j] == 1 && mapCheck[i][j] == false) {
					Node visit = new Node(i,j);
					v.add(BFS(map,mapCheck,visit));
				}
			}
		}
		Collections.sort(v);
		io.write(v.size()+"\n");
		for(int n : v) io.write(n+"\n");
		io.close();
	}
	
	static int BFS(int[][] map, boolean[][] checker, Node start) {
		int counter = 0;
		Queue<Node> nodeQueue = new LinkedList<Node>();
		checker[start.row][start.col] = true;
		nodeQueue.add(start);
		
		while(nodeQueue.isEmpty() == false) {
			Node curPos = nodeQueue.poll();
			counter++;
			Node[] dir = new Node[4];
			if (curPos.col+1 < map.length && map[curPos.row][curPos.col+1] == 1 && checker[curPos.row][curPos.col+1] == false) dir[0] = new Node(curPos.row,curPos.col+1);
			if (curPos.col-1 >= 0 && map[curPos.row][curPos.col-1] == 1 && checker[curPos.row][curPos.col-1] == false) dir[1] = new Node(curPos.row,curPos.col-1);
			if (curPos.row+1 < map.length && map[curPos.row+1][curPos.col] == 1 && checker[curPos.row+1][curPos.col] == false) dir[2] = new Node(curPos.row+1,curPos.col);
			if (curPos.row-1 >= 0 && map[curPos.row-1][curPos.col] == 1 && checker[curPos.row-1][curPos.col] == false) dir[3] = new Node(curPos.row-1,curPos.col);
			
			for (int i =0; i < 4; i++) {
				if(dir[i] != null) {
					checker[dir[i].row][dir[i].col] = true;
					nodeQueue.add(dir[i]);
				}
			}
		}
		return counter;
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
