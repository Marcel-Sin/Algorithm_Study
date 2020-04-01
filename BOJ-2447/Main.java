import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		char[][] arr = new char[size][size];
		for(int i = 0; i < arr.length; i++) Arrays.fill(arr[i],' ');
		
		DC_star(arr,size,0,0);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr.length; i++) {
			sb.append(arr[i]);
			sb.append('\n');
		}
		io.write(sb.toString());
		io.close();
	}
		
	
	static void DC_star(char[][] arr,int size ,int row, int col) {
		if (size == 3) {
			Star(arr,row,col);
			return;
		}
		// 0,0 0,3 0,6
		// 3,0 3,3 3,6
		// 6,0 6,3 6,6
		
		// 0,0    0,9   0,18
		// 9,0    9,9   9,18
		// 18,0  18,9  18,18
		
		int nextSize = size/3;
		DC_star(arr,nextSize,row,col);
		DC_star(arr,nextSize,row,col+nextSize*1);
		DC_star(arr,nextSize,row,col+nextSize*2);
		
		DC_star(arr,nextSize,row+nextSize*1,col);
		//DC_star(arr,nextSize,row+nextSize*1,col+nextSize*1);
		DC_star(arr,nextSize,row+nextSize*1,col+nextSize*2);
		
		DC_star(arr,nextSize,row+nextSize*2,col);
		DC_star(arr,nextSize,row+nextSize*2,col+nextSize*1);
		DC_star(arr,nextSize,row+nextSize*2,col+nextSize*2);
		
	}
	
	static void Star(char[][] arr, int row, int col) {
		arr[row][col] = '*';
		arr[row][col+1] = '*';
		arr[row][col+2] = '*';
		arr[row+1][col] = '*';
		//arr[row+1][col+1] = '*';
		arr[row+1][col+2] = '*';
		arr[row+2][col] = '*';
		arr[row+2][col+1] = '*';
		arr[row+2][col+2] = '*';
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
