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
		Vector<Integer> vint = new Vector<Integer>();
		Vector<Integer> result = new Vector<Integer>();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int count = nextTokenInt(stk);
		int moveSpeed = nextTokenInt(stk)-1;
		int choice = 0;
		for (int i=0; i < count; i++) vint.add(i+1);
		
		int size = count;
		for (int i=0; i < count; i++) {
			choice = (choice + moveSpeed) % size;
			size--;
			result.add(vint.elementAt(choice));
			vint.remove(choice);
		}
		//<3, 6, 2, 7, 5, 1, 4>
		
		io.write("<");
		for (int i = 0; i < count; i++) {
			if (i == count-1) io.write(result.elementAt(i)+">");
			else io.write(result.elementAt(i)+", ");
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
