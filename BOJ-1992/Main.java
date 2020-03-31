import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		int[][] arr = new int[size][size];
		
		char[] temp;
		for (int i = 0; i < arr.length; i++) {
			temp = io.inputStr().toCharArray();
			for(int j = 0; j < temp.length; j++) arr[i][j] = temp[j]-48;
		}
		
		Quad(arr,size,0,0);
		System.out.println(sb.toString());
		
	}

	static void Quad(int[][] arr,int quadSize,int row, int col) {
		boolean result0, result1 = true;
		result0 = Quadize(arr,quadSize,row,col,0);
		if(result0 == false) result1 = Quadize(arr,quadSize,row,col,1);
		
		if(result0 == false && result1 == false) {
			sb.append('(');
			int nextSize = quadSize/2;
			Quad(arr,nextSize,row,col);
			Quad(arr,nextSize,row,col+nextSize);
			Quad(arr,nextSize,row+nextSize,col);
			Quad(arr,nextSize,row+nextSize,col+nextSize);
			sb.append(')');
		}
		else {
			if(result0 == true) sb.append(0);
			else if (result1 == true) sb.append(1);
			return;
		}
	}
	
	
	
	
	static boolean Quadize(int[][] arr,int quadSize,int row,int col, int target) {
		if (quadSize == 1) return (arr[row][col] == target);
		boolean check = true;
		for(int i = row; i < row+quadSize; i++) {
			for(int j = col; j < col+quadSize; j++) {
				if(arr[i][j] != target) {
					check = false;
					break;
				}
			}
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
