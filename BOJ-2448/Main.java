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
		char[][] arr = new char[size][size*2-1];
		for(int i = 0; i < arr.length; i++) Arrays.fill(arr[i],' ');
		
		DC_Star(arr,size,size-1,size-1);
		for(int i = 0; i < arr.length; i++) {
			io.bw.write(arr[i]);
			io.bw.write('\n');
		}
		io.close();

	}
		
	
	static void DC_Star(char[][] arr,int size ,int row, int col) {
		if (size == 3) {
			Star(arr,row,col);
			return;
		}
		int nextSize = size/2;
		DC_Star(arr,nextSize,row-nextSize,col);
		DC_Star(arr,nextSize,row,col+nextSize);
		DC_Star(arr,nextSize,row,col-nextSize);
	}
	
	static void Star(char[][] arr, int row, int col) {
		//Top
		arr[row-2][col] = '*';

		//Center
		arr[row-1][col-1] = '*';
		arr[row-1][col+1] = '*';

		//bottom
		arr[row][col-2] = '*';
		arr[row][col-1] = '*';
		arr[row][col] = '*';
		arr[row][col+1] = '*';
		arr[row][col+2] = '*';

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
