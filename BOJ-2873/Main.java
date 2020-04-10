import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int Row=0,Col=0;
	static boolean collision = false;
	static int[][] map;
	static int[][] check;
	static int lastRow, lastCol;
	static char[] AI = new char[4];
	static boolean UP_arise = false;
	static boolean zigzag = false;
	public static void main(String[] args) throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int r = nextInt(stk),c=nextInt(stk);
		map = new int[r][c];
		check = new int[r][c];
		lastRow = r-1;
		lastCol = c-1;
		for(int i = 0; i < r; i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j = 0; j < c; j++) map[i][j] = nextInt(stk);
		}
		
		if(r%2 == 1) {
			char dir = 'R';
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c-1; j++) {
					io.bw.write(dir);
				}
				if(i != r-1) io.bw.write('D');
				if(dir == 'L') dir = 'R';
				else if(dir == 'R') dir = 'L';
			}
		}
		else if(c%2 == 1) {
			char dir = 'D';
			for(int i = 0; i < c; i++) {
				for(int j = 0; j < r-1; j++) {
					io.bw.write(dir);
				}
				if(i != c-1) io.bw.write('R');
				if(dir == 'D') dir = 'U';
				else if(dir == 'U') dir = 'D';
			}
		}
		else if(r%2 == 0 && c%2 == 0){
			int min = 10000;
			int y=0,x=0;
			for(int i=0; i < r; i++) {
				for(int j=0; j < c; j++) {
					if(i+j % 2 == 0) continue;
					if(map[i][j] < min) {
						min = map[i][j];
						x = j;
						y = i;
					}
				}
			}
			check[0][0] = 1;
			check[y][x] = 99999999;
			if(y >= lastRow-1) zigzag = true;
			Priority_Change('R','L','D','U');
			
			while (Row != lastRow || Col != lastCol) {
				int value = Priority_Movement();
				if(value == 99999999) { 
					Move('D');
					Priority_Change(AI[3],AI[0],AI[2],AI[1]);
					collision = true;
				}
			}
		}
		io.close();
	}
		
		
		
	
	
	
	static int Move(char dir) throws IOException{
		if(dir == 'D' && Row+1 <= lastRow) {
			if(check[Row+1][Col] == 0) {
				Row++;
				check[Row][Col] = 1;
				io.bw.write('D');
				if(collision && UP_arise == true) {
					Priority_Change(AI[2], AI[1], AI[0], AI[3]);
					UP_arise = false;
				}
				else if(zigzag && Row >= lastRow-1) Priority_Change('U','D','R','L');	
				else if(collision && Col == lastCol) Priority_Change(AI[0], 'L', AI[2], 'R');
				else if(collision && Col == 0) Priority_Change(AI[0], 'R', AI[2], 'L');
				else if(!collision && Col == lastCol) Priority_Change('L', 'R',AI[2],AI[3]);
				else if(!collision && Col == 0) Priority_Change('R', 'L',AI[2],AI[3]);
				return 1;
			}
			else if (check[Row+1][Col] == 1) return 0;
		}
		
		else if(dir == 'U' && Row-1 >= 0) {
			if(check[Row-1][Col] == 0) {
				Row--;
				check[Row][Col] = 1;
				io.bw.write('U');
				if (zigzag == false) {
					UP_arise = true;
					Priority_Change(AI[2], AI[1], AI[0], AI[3]);
				}
				return 1;
			}
			else if (check[Row-1][Col] == 1) {
				return 0;
			}
		}
		else if(dir == 'R' && Col+1 <= lastCol) {
			if (check[Row][Col+1] == 0) {
				Col++;
				check[Row][Col] = 1;
				io.bw.write('R');
				return 1; 
			}
			else if (check[Row][Col+1] == 1) return 0;
			else return 99999999;
		}
		else if(dir == 'L' && Col-1 >= 0) {
			if (check[Row][Col-1] == 0) {
				Col--;
				check[Row][Col] = 1;
				io.bw.write('L');
				return 1;
			}
			else if (check[Row][Col-1] == 1) return 0;
			else return 99999999;
		}
		return 0;
	}
	
	static int Priority_Movement() throws IOException{
		int checker = -1;
		checker = Move(AI[0]);
		if(checker == 0) checker = Move(AI[1]);
		if(checker == 0) checker = Move(AI[2]);
		if(checker == 0) checker = Move(AI[3]);
		return checker;
	}

	static void Priority_Change(char a, char b, char c, char d) {
		AI[0] = a;
		AI[1] = b;
		AI[2] = c;
		AI[3] = d;
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
