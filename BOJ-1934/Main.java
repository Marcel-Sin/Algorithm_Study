import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		int count = io.inputInt();
		
		for (int i = 0; i < count; i++) {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int a = nextTokenInt(stk),b = nextTokenInt(stk);
		if (a < b) {int temp = a; a = b; b = temp;}
		int rest = 0,temp1 = a,temp2 = b;
		while(true) {
			rest = temp1 % temp2;
			if (rest == 0) break;
			temp1 = temp2;
			temp2 = rest;
		}
		System.out.println(a*b/temp2);
		}
		
		
	}
		
	static public int nextTokenInt(StringTokenizer stk) {
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
