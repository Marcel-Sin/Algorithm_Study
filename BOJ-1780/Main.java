import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static int onePaper = 0, zeroPaper = 0, negaPaper = 0;
	
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		int[][] arr = new int[size][size];
		StringTokenizer stk;
		
		for(int i = 0; i < size; i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j= 0; j < size; j++) {
				arr[i][j] = nextInt(stk);
			}
		}
		DC(arr,size,0,0);
		System.out.println(negaPaper);
		System.out.println(zeroPaper);
		System.out.println(onePaper);
		
	}

	
	static void DC(int[][] arr, int size, int row, int col) {
		boolean lumpPaper = false;
		if (CheckPaper(arr, size,row, col, -1)) { negaPaper++; lumpPaper = true; }
		if (CheckPaper(arr, size,row, col, 1)) { onePaper++; lumpPaper = true; }
		if (CheckPaper(arr, size,row, col, 0)) { zeroPaper++; lumpPaper = true; }
		
		// size = 9 일 때,
		// 0 , 3 , 6
		if(lumpPaper == false && size > 1) {
			int nextSize = size/3;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					int nextRow = row+j*nextSize;
					int nextCol = col+i*nextSize;
					DC(arr,nextSize,nextRow,nextCol);
				}
			}
		}
		else return;
		
	}
	
	static boolean CheckPaper(int[][] arr,int checkSize,int row, int col ,int checkTarget) {
		if(checkSize == 1 && arr[row][col] == checkTarget) return true;
		boolean check = true;
		
		for(int i = row; i < row+checkSize; i++) {
			for(int j = col; j < col+checkSize; j++) {
				if(arr[i][j] != checkTarget) {
					check = false;
					break;
				}
			}
			if(check == false) break;
		}
		return check;
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
