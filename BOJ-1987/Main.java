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
		Coord cur = s.GetCoord();
		if(cur == null) return;
		Coord[] nextCoord = new Coord[4];
		for(int i = 0; i < 4; i++) {
			nextCoord[i] = cur.Add(DIR[i]);
			if(nextCoord[i].row < 0 || nextCoord[i].col < 0 || nextCoord[i].row >= R || nextCoord[i].col >= C) nextCoord[i] = null;
			else if (s.Check(MAP[nextCoord[i].row][nextCoord[i].col])) nextCoord[i] = null;
		}
		
		for(int i = 0; i < 4; i++) {
			if(nextCoord[i] != null) {
				s.Select(nextCoord[i]);
				DFS(s);
				s.Deselect();
			}
		}
	}
	
	static class State {
		ArrayList<Coord> coordList;
		int count;
		boolean[] check;
		
		public State() {
			coordList = new ArrayList<Main.Coord>();
			check = new boolean[26];
			count = 0;
		}
		
		public void Select(Coord co) {
			char c = MAP[co.row][co.col];
			coordList.add(co);
			check[c-65] = true;
			count++;
			if(ANS < count) ANS = count;
		}
		
		public void Deselect() {
			int idx = coordList.size()-1;
			Coord co = coordList.get(idx);
			char c = MAP[co.row][co.col];
			check[c-65] = false;
			coordList.remove(idx);
			count--;
		}
		
		public boolean Check(char c) {
			return check[c-65];
		}
		
		public Coord GetCoord() {
			if(count == 0) return null;
			return coordList.get(count-1);
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
		DIR = new Coord[4];
		DIR[0] = new Coord(0,1);
		DIR[1] = new Coord(0,-1);
		DIR[2] = new Coord(1,0);
		DIR[3] = new Coord(-1,0);
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
