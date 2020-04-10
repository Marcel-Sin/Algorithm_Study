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
	static int[][] map;
	static boolean[][] check;
	static int lastRow, lastCol,minRow,minCol,zigzagStartRow;
	public static void main(String[] args) throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int r = nextInt(stk),c=nextInt(stk);
		map = new int[r][c];
		check = new boolean[r][c];
		lastRow = r-1;
		lastCol = c-1;
		for(int i = 0; i < r; i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j = 0; j < c; j++) map[i][j] = nextInt(stk);
		}
		
		if (r % 2 == 1)
			Odd_Horizontal();
		else if (c % 2 == 1)
			Odd_Vertical();
		else if (r % 2 == 0 && c % 2 == 0) {
			Finding_Minimum();
			zigzagStartRow = (minRow/2)*2;
			Row = 0;
			Col = 0;
			char dir = 'R';
			for(int i = 0; i < zigzagStartRow; i++) {
				LineMove(dir,lastCol);
				dir = (dir == 'R')? 'L':'R';
				io.bw.write('D');
				Row=i+1;
			} 
			check[Row][Col] = true;
			check[minRow][minCol] = true;
			ZigzagBarrier(zigzagStartRow-1,zigzagStartRow+2);
			int zigloop = (lastCol+1)*2-1;
			for(int i=0; i < zigloop; i++) {
				boolean checker = ZigzagUP();
				if(checker == false) checker = ZigzagDOWN();
				if(checker == false) checker = ZigzagRIGHT();
			}
			if(Row != lastRow) {
				io.bw.write('D');
				int finalLoop = lastRow-Row;
				dir = 'L';
				for(int i = 0; i < finalLoop; i++) {
					LineMove(dir,lastCol);
					dir = (dir == 'R')? 'L':'R';
					if(i != finalLoop-1) io.bw.write('D');
				}
			}
		}// Even if End
		io.close();
	}
	
	
	static void Odd_Horizontal() throws IOException{
		char dir = 'R';
		for(int i = 0; i <= lastRow; i++) {
			for(int j = 0; j < lastCol; j++) io.bw.write(dir);
			if(i != lastRow) io.bw.write('D');
			dir = (dir == 'R')? 'L':'R';
		}
	}
	static void Odd_Vertical() throws IOException{
		char dir = 'D';
		for(int i = 0; i <= lastCol; i++) {
			for(int j = 0; j < lastRow; j++) io.bw.write(dir);
			if(i != lastCol) io.bw.write('R');
			dir = (dir == 'U')? 'D':'U';
		}
	}

	static void Finding_Minimum() {
		int min = 10000;
		for (int i = 0; i <= lastRow; i++) {
			int startCol = (i%2 == 1)? 0:1;
			for (int j = startCol; j <= lastCol; j+=2) {
				if (map[i][j] < min) {
					min = map[i][j];
					minRow = i;
					minCol = j;
				}
			}
		}
	}
	static void LineMove(char dir, int moveCount) throws IOException{
		for(int j = 0; j < moveCount; j++) io.bw.write(dir);
	}
	static void	ZigzagBarrier(int startRow, int endRow) {
		if(startRow >= 0) {
			for(int i = 0; i <= lastCol; i++) {
				check[startRow][i] = true;
			}
		}
		if(endRow <= lastRow) {
			for(int i = 0; i <= lastCol; i++) {
				check[endRow][i] = true;
			}
		}
	}
	static boolean ZigzagUP() throws IOException{
		if(Row-1 >= 0 && check[Row-1][Col] == false) {
			Row--;
			check[Row][Col] = true;
			io.bw.write('U');
			return true;
		}
		return false;
	}
	static boolean ZigzagDOWN() throws IOException{
		if(Row+1 <= lastRow  && check[Row+1][Col] == false) {
			Row++;
			check[Row][Col] = true;
			io.bw.write('D');
			return true;
		}
		return false;
	}
	static boolean ZigzagRIGHT() throws IOException{
		if(Col+1 <= lastCol  && check[Row][Col+1] == false) {
			Col++;
			check[Row][Col] = true;
			io.bw.write('R');
			return true;
		}
		return false;
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
