import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		StringBuilder sb = new StringBuilder(io.inputStr());
		for (int i = 0; i < sb.length(); i++) {
			sb.setCharAt(i, (char)ROT13(sb.charAt(i)));
		}
		io.write(sb.toString());
		io.close();
		
		
		
		
		
		
	}		
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	static public int ROT13(int c) {
		int temp = c;
		if ('a' <= temp && temp <= 'z') {
			temp += 13;
			if ('z' < temp) temp = 'a' + (temp-'z'-1); 
			return temp;
		}
		else if ('A' <= temp && temp <= 'Z') {
			temp += 13;
			if ('Z' < temp) temp = 'A' + (temp-'Z'-1); 
			return temp;
		}
		else return c;
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
