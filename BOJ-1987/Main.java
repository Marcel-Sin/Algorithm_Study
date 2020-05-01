import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static char[][] MAP;
	static int R,C,ANS = 0;
	static Coord[] DIR;
	public static void main(String[] args) throws IOException {
		Initializing();
		State state = new State();
		state.Select(new Coord(0,0));
		DFS(state);
		System.out.println(ANS);
	}
	
	static void DFS(State s) {
		if(s.stopRecur == true) return;
		if(s.count == 26) {	
			s.stopRecur = true;
			return;
		}
		Coord cur = s.GetCoord();
		if(cur == null) return;
		Coord[] nextCoord = new Coord[4];
		if(cur.row+1 < R && s.Check(MAP[cur.row+1][cur.col]) == false) nextCoord[0] = new Coord(cur.row+1,cur.col);
		if(cur.col+1 < C && s.Check(MAP[cur.row][cur.col+1]) == false) nextCoord[1] = new Coord(cur.row,cur.col+1);
		if(cur.row-1 >= 0 && s.Check(MAP[cur.row-1][cur.col]) == false) nextCoord[2] = new Coord(cur.row-1,cur.col);
		if(cur.col-1 >= 0 && s.Check(MAP[cur.row][cur.col-1]) == false) nextCoord[3] = new Coord(cur.row,cur.col-1);
		
		for(int i = 0; i < 4; i++) {
			if(nextCoord[i] != null) {
				s.Select(nextCoord[i]);
				DFS(s);
				s.Deselect();
			}
		}
	}
	
	static class State {
		Coord[] coordList;
		int count;
		boolean[] check;
		boolean stopRecur;
		
		public State() {
			coordList = new Coord[26];
			check = new boolean[26];
			count = 0;
			stopRecur = false;
		}
		
		public void Select(Coord co) {
			char c = MAP[co.row][co.col];
			coordList[count] = co;
			check[c-65] = true;
			count++;
			if(ANS < count) ANS = count;
		}
		
		public void Deselect() {
			int idx = count-1;
			Coord co = coordList[idx];
			char c = MAP[co.row][co.col];
			check[c-65] = false;
			count--;
		}
		
		public boolean Check(char c) {
			return check[c-65];
		}
		
		public Coord GetCoord() {
			if(count == 0) return null;
			return coordList[count-1];
		}
	}
	
	static class Coord {
		public int row,col;
		public Coord(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public Coord Add(Coord adder) {
			return new Coord(this.row+adder.row,this.col+adder.col);
		}
	}
	
	static void Initializing() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		R = nextInt(stk);
		C = nextInt(stk);
		MAP = new char[R][];
		for (int i = 0; i < R; i++) MAP[i] = io.inputStr().toCharArray();
	}
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
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
