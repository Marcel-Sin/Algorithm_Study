import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[][] MAP;
	static int SIZE;
	static boolean[][] isBox;
	static boolean[][] isRow;
	static boolean[][] isCol;
	
	static State state;
	public static void main(String[] args) throws IOException {
		Initializing();
		DFS(state);
	}
	
	static boolean Initializing() throws IOException{
		MAP = new int[9][9];
		isRow = new boolean[9][];
		isCol = new boolean[9][];
		isBox = new boolean[9][];
		
		SIZE = 0;
		ArrayList<Cord> cords = new ArrayList<Cord>(); 
		for(int i = 0; i < 9; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < 9; j++) { 
				MAP[i][j] = nextInt(stk);
				if(MAP[i][j] == 0) {
					cords.add(new Cord(i,j));
					SIZE++;
				}
			}
		}
		for(int x = 0; x < 9; x++) {
			isBox[x] = new boolean[10];
			isCol[x] = new boolean[10];
			isRow[x] = new boolean[10];
			Cord cur = BoxToCord(x);
			for(int i = cur.row; i < cur.row+3; i++) {
				for(int j = cur.col; j < cur.col+3; j++) {
					isBox[x][MAP[i][j]] = true;
				}
			}
			for(int j = 0; j < 9; j++) isRow[x][MAP[x][j]] = true;
			for(int i = 0; i < 9; i++) isCol[x][MAP[i][x]] = true;
		}
		
		
		Cord[] cordlist = new Cord[SIZE];
		for(int i = 0; i < cords.size(); i++) {
			cords.get(i).CreatePlist();
			cordlist[i] = cords.get(i);
		}
		state = new State(cordlist,0);

		return true;
	}

	static Cord BoxToCord(int boxNum) {
		switch(boxNum) {
			case 0:
				return new Cord(0,0);
			case 1:
				return new Cord(0,3);
			case 2:
				return new Cord(0,6);
			case 3:
				return new Cord(3,0);
			case 4:
				return new Cord(3,3);
			case 5:
				return new Cord(3,6);
			case 6:
				return new Cord(6,0);
			case 7:
				return new Cord(6,3);
			case 8:
				return new Cord(6,6);
		}
		return null;
	}
	static int CordToBox(Cord boxCord) {
		int r = (boxCord.row / 3)*3;
		int c = boxCord.col / 3;
		return r+c;
	}
	
	static void DFS(State s) {
		if(s.stopRecursion == true) return;
		if(s.sp == SIZE) {
			Display();
			s.stopRecursion = true;
			return;
		}
		Cord cur = s.GetCord();
		for(int i = 0; i < cur.plist.size(); i++) {
			if(cur.CheckPos(i)) {
				s.Select(i);
				DFS(s);
				s.Deselect();
			}
		}
	}
	
	static class State {
		Cord[] storage;
		int sp;
		boolean stopRecursion;
		
		public State(Cord[] storage, int sp) {
			super();
			this.storage = storage;
			this.sp = sp;
		}
		
		public Cord GetCord() {
			return storage[sp];
		}
		
		public void Select(int idx) {
			Cord cur = GetCord();
			int N = cur.plist.get(idx);
			MAP[cur.row][cur.col] = N;
			isBox[cur.boxNum][N] = true;
			isRow[cur.row][N] = true;
			isCol[cur.col][N] = true;
			sp++;
		}
		
		public void Deselect() {
			sp--;
			Cord cur = GetCord();
			int N = MAP[cur.row][cur.col];
			isBox[cur.boxNum][N] = false;
			isRow[cur.row][N] = false;
			isCol[cur.col][N] = false;
		}
	}
	
	static class Cord{
		public int row,col,boxNum;
		public ArrayList<Integer> plist;
		public Cord(int row, int col) {
			super();
			this.row = row;
			this.col = col;
			this.boxNum = CordToBox(this);
			plist = new ArrayList<Integer>();
		}
		public void CreatePlist() {
			boolean[] check = isBox[boxNum].clone();
			for(int n = 1; n <= 9; n++) if(isRow[row][n] == true) check[n] = true;
			for(int n = 1; n <= 9; n++) if(isCol[col][n] == true) check[n] = true;
			
			// plist Creation
			for(int i = 1; i <= 9; i++) {
				if(check[i] == false) plist.add(i);
			}
			
		}
		public boolean CheckPos(int idx) {
			int N = plist.get(idx);
			if(isBox[boxNum][N] == true) return false;
			if(isRow[row][N] == true) return false;
			if(isCol[col][N] == true) return false;
			return true;
		}

		
	}  // MAIN END Point
	
	static void Display() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(MAP[i][j] + " ");
			}
			System.out.println();
		}
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
