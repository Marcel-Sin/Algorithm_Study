import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		Vector<Integer> vint = new Vector<Integer>();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int number = nextTokenInt(stk);
		int radix = nextTokenInt(stk);
		int value = number,rest = 0;
		
		
		while(value != 0) {
			rest = value % radix;
			vint.add(rest);
			value = (int)(value/radix);
		}
		
		for (int i=vint.size()-1; i >= 0; i--) {
			int n = vint.elementAt(i);
			if (n > 9) {
				n = 'A'+(n-10);
				char c = (char)n;
				io.write(c+"");
			}
			else io.write(n+"");
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
