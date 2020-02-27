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
		
		String str = io.inputStr();
		int count = io.inputInt();
		String[] orders = new String[count];
		StringTokenizer stk;
		Editor edit = new Editor(str);
		
		for(int i = 0; i < count; i++) orders[i] = io.br.readLine();
		
		for (int i = 0; i < count; i++) {
			stk = new StringTokenizer(orders[i]);
			Character opcode = stk.nextToken().charAt(0);
			if (opcode == 'L') edit.Left();
			else if (opcode == 'P') edit.Plus(stk.nextToken().charAt(0));
			else if (opcode == 'B') edit.BackSpace();
			else if (opcode == 'D') edit.Drive();
		}
		io.write(edit.Result());
		io.close();
	}		
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

}


class Editor {
	private LinkedList<Character> txt;
	private ListIterator<Character> iter;
	
	public Editor(String str) {
		super();
		this.txt = new LinkedList<Character>();
		for (int i = 0; i < str.length(); i++) txt.add(str.charAt(i));
		iter = txt.listIterator(txt.size());
	}
	
	public void Left() {
		if (iter.hasPrevious() == false) return;
		iter.previous();
	}
	public void Drive() {
		if(iter.hasNext() == false) return;
		iter.next();
	}
	public void BackSpace() {
		if (iter.hasPrevious() == false) return;
		iter.previous();
		iter.remove();
	}
	public void Plus(char ch) {
		iter.add(ch);
	}
	public String Result() {
		char[] arr = new char[txt.size()];
		iter = txt.listIterator(0);
		for (int i = 0; i < txt.size(); i++) {
			arr[i] = iter.next();
		}
		return String.valueOf(arr);
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
