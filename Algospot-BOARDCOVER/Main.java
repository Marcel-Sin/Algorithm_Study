import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[][] MAP;
	static int H,W;
	static int TOTAL_CASE;
	static int ANSWER;
	static int SPACE;
	static int[][][] SHAPE = new int[][][] { { { 1, 1 }, { 0, 1 } }, { { 1, 0 }, { 1, 1 } }, { { 1, 1 }, { 1, 0 } },
			{ { 0, 1 }, { 1, 1 } } };
			
	public static void main(String[] args) throws IOException {
		TOTAL_CASE = io.inputInt();
		for(int i = 0; i < TOTAL_CASE; i++) {
			SPACE = Init();
			if(SPACE%3 == 0) {
				DFS();
				System.out.println(ANSWER);
			}
			else System.out.println(0);
			ANSWER = 0;
		}
	}
	
	static void DFS() {
		int selectR= -1, selectC = -1;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(MAP[i][j] == 0) {
					selectR = i;
					selectC = j;
					break;
				}
			}
			if(selectR != -1) break;
		}
		if(selectR == -1) { ANSWER++; return; }
		for(int type = 0; type < 4; type++) {
			if(IsOkay(selectR, selectC, SHAPE[type])) {
				InsertShape(selectR, selectC, SHAPE[type]);
				DFS();
				DeleteShape(selectR, selectC, SHAPE[type]);
			}
		}
		
	}
	
	static void Display() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(MAP[i][j]); 
			}
			System.out.println();
		}
	}
	
	static void DeleteShape(int r, int c, int[][] shape) {
		if(shape == SHAPE[3]) c--;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(shape[i][j] == 1) MAP[r+i][c+j] = 0; 
			}
		}
	}
	static void InsertShape(int r, int c, int[][] shape) {
		if(shape == SHAPE[3]) c--;
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 2; j++) {
				if(shape[i][j] == 1) MAP[r+i][c+j] = 1; 
			}
		}
	}
	static boolean IsOkay(int r, int c, int[][] shape) {
		if(r+1 >= H) return false;
		
		if(shape == SHAPE[3]) c--;
		if (c < 0) return false;
		if (c+1 >= W) return false;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (shape[i][j] == 1 && MAP[r + i][c + j] == 1)
					return false;
			}
		}
		
		return true;
	}
	static int Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		H = nextInt(stk);
		W = nextInt(stk);
		MAP = new int[H][W];
		int countSpace = 0;
		for(int i = 0; i < H; i++) {
			char[] temp = io.inputStr().toCharArray();
			for(int c = 0; c < W; c++) {
				if(temp[c] == '#') MAP[i][c] = 1;
				else countSpace++;
			}
		}
		return countSpace;
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
	}}
