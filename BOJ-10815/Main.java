import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int MAX = 10000000;
		StringBuilder sb = new StringBuilder();
		//input
		byte[] belongings = new byte[MAX*2+1]; 
		int have = io.inputInt();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int data;
		while(stk.hasMoreTokens()) {
			data = nextInt(stk);
			if(data < 0) data = data*(-1)+MAX;
			belongings[data] = 1; 
		}
		int findCount = io.inputInt();
		int[] findValues = new int[findCount];
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < findValues.length; i++) findValues[i] = nextInt(stk);
		
		for (int i = 0; i < findValues.length; i++) {
			data = findValues[i];
			if(data < 0) data = data*(-1)+MAX;
			sb.append((char)(belongings[data]+48));
			sb.append(' ');
		}
		io.write(sb.toString());
		io.close();
		
		
		
		
	}


	public static int nextInt(StringTokenizer stk) {
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
