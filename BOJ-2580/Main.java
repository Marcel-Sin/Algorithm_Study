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
	static State state;
	public static void main(String[] args) throws IOException {
		Initializing();
		DFS(state);
	}
	
	static boolean Initializing() throws IOException{
		MAP = new int[9][9];
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
		Cord[] cordlist = new Cord[SIZE];
		for(int i = 0; i < cords.size(); i++) {
			cords.get(i).CreatePlist();
			cordlist[i] = cords.get(i);
		}
		Arrays.sort(cordlist,new Comparator<Cord>() {
			@Override
			public int compare(Cord o1, Cord o2) {
				if(o1.plist.size() == o2.plist.size() ) return 0;
				else return (o1.plist.size() < o2.plist.size() )? -1:1;
			}
		});
		state = new State(cordlist,0);
		return true;
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
			MAP[cur.row][cur.col] = cur.plist.get(idx);
			sp++;
		}
		
		public void Deselect() {
			sp--;
			Cord cur = GetCord();
			MAP[cur.row][cur.col] = 0;
		}
	}
	
	
	static class Cord{
		public int row,col;
		public ArrayList<Integer> plist;
		public Cord(int row, int col) {
			super();
			this.row = row;
			this.col = col;
			plist = new ArrayList<Integer>();
		}
		public void CreatePlist() {
			boolean[] check = new boolean[10];
			int limitR,limitC,startR,startC;
			//3*3 Check
			startR = (row/3)*3;
			startC = (col/3)*3;
			limitR = startR+3;
			limitC = startC+3;
			
			for (int i = startR; i < limitR; i++) {
				for (int j = startC; j < limitC; j++) {
					check[MAP[i][j]] = true;
				}
			}
			
			// Row Check
			for(int i = 0; i < 9; i++) {
				check[MAP[i][col]] = true;
			}
			// Col Check
			for(int j = 0; j < 9; j++) {
				check[MAP[row][j]] = true;
			}
			
			// plist Creation
			for(int i = 1; i <= 9; i++) {
				if(check[i] == false) plist.add(i);
			}
			
		}
		
		public boolean CheckPos(int idx) {
			int N = plist.get(idx);
			int limitR,limitC,startR,startC;
			//3*3 Check
			startR = (row/3)*3;
			startC = (col/3)*3;
			limitR = startR+3;
			limitC = startC+3;
			for (int i = startR; i < limitR; i++) {
				for (int j = startC; j < limitC; j++) {
					if(MAP[i][j] == N && (row == i && col == j) == false) return false;
				}
			}
			// Row Check
			for(int i = 0; i < 9; i++) {
				if(MAP[i][col] == N && row != i) return false;
			}
			// Col Check
			for(int j = 0; j < 9; j++) {
				if(MAP[row][j] == N && col != j) return false;
			}
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
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
		}
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
