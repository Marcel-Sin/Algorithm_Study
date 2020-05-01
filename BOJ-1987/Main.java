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
	static State state = new State();
	public static void main(String[] args) throws IOException {
		Initializing();
		state.Select(0, 0);
		DFS(state,0,0);
		System.out.println(ANS);
	}
	
	static void DFS(State s,int row, int col) {
		if(ANS == 26) return;
		
		int north = row-1, south = row+1, east = col+1, west = col-1;
		if(south < R && s.Check(MAP[south][col]) == false) {
			s.Select(south, col);
			DFS(s,south,col);
			s.Deselect(south, col);
		}
		if(east < C && s.Check(MAP[row][east]) == false) {
			s.Select(row, east);
			DFS(s,row,east);
			s.Deselect(row, east);
		}
		if(north >= 0 && s.Check(MAP[north][col]) == false) {
			s.Select(north, col);
			DFS(s,north,col);
			s.Deselect(north, col);
		}
		if(west >= 0 && s.Check(MAP[row][west]) == false) {
			s.Select(row, west);
			DFS(s,row,west);
			s.Deselect(row, west);	
		}
		
	}
	
	static class State {
		int count;
		boolean[] check;
		
		public State() {
			check = new boolean[26];
			count = 0;
		}
		
		public void Select(int r, int c) {
			char ch = MAP[r][c];
			check[ch-65] = true;
			count++;
			if(ANS < count) ANS = count;
		}
		
		public void Deselect(int r, int c) {
			char ch = MAP[r][c];
			check[ch-65] = false;
			count--;
		}
		
		public boolean Check(char c) {
			return check[c-65];
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
