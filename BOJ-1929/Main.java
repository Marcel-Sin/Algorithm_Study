import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int min = nextTokenInt(stk), max = nextTokenInt(stk);
		int target = 0, num = 0;
		LinkedList<Integer> vint = new LinkedList<Integer>();
		ListIterator<Integer> iter;
		
		for (int i=2; i <= max; i++) vint.add(i);
		
		for (int i=0; i < (int)Math.sqrt(max); i++) {
			target=vint.get(i);
			if(target > (int)Math.sqrt(max)) break;
			iter = vint.listIterator(i);
			iter.next();
			while(iter.hasNext()) {
				num = iter.next();
				if (num % target == 0) iter.remove();
			}
		}
		iter = vint.listIterator(0);
		while(iter.hasNext()) {
			num = iter.next();
			if(min <= num && num <= max) io.write(num+"\n");
			else continue;
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
