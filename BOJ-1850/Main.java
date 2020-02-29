import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		StringBuilder sb = new StringBuilder();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		long a = Long.parseLong(stk.nextToken());
		long b = Long.parseLong(stk.nextToken());
		if (a < b ) {long temp = a; a = b; b = temp;}
		long rest, temp1 = a, temp2 = b;
		while(true) {
			rest = temp1 % temp2;
			if (rest == 0) break;
			temp1 = temp2;
			temp2 = rest;
		}
		for(int i=0; i < temp2; i++) sb.append("1");
		System.out.println(sb);
		

		
		
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
