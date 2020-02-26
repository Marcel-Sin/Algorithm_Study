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
		HashMap<Character, Integer> hm = new HashMap<Character,Integer>();
		Vector<String> vstr = new Vector<String>();
		hm.put('a', 0);
		hm.put('A', 0);
		hm.put('0', 0);
		hm.put(' ', 0);
		
		String str = "";
		while(str != null) {
			str = io.br.readLine();
			if(str != null) vstr.add(str);
		}
		
		for(String s : vstr) {
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				if ('a' <= c && c <= 'z') hm.put('a', hm.get('a')+1);
				if ('A' <= c && c <= 'Z') hm.put('A', hm.get('A')+1);
				if ('0' <= c && c <= '9') hm.put('0', hm.get('0')+1);
				if (' ' == c) hm.put(' ', hm.get(' ')+1);
			}
			io.write(hm.get('a')+" "+hm.get('A')+" "+hm.get('0')+" "+hm.get(' ')+"\n");
			hm.put('a', 0);
			hm.put('A', 0);
			hm.put('0', 0);
			hm.put(' ', 0);
		}
		io.close();
		
		
		
		
		
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
