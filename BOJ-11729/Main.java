import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static int counter = 0;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int hanoiSize = io.inputInt();
		
		// Hanoi01    ,   Hanoi02    ,    Hanoi03
		HanoiStack hanoi01 = new HanoiStack("1");
		HanoiStack hanoi02 = new HanoiStack("2");
		HanoiStack hanoi03 = new HanoiStack("3");
		
		
		for(int i=hanoiSize; i > 0; i--) hanoi01.stack.push(i);
		Hanoi(hanoiSize,hanoi01,hanoi03,hanoi02);
		io.write(counter+"\n");
		io.write(sb.toString());
		io.close();
	}

	
	static void Hanoi(int plate,HanoiStack source,HanoiStack dest, HanoiStack freeSpace) {
		if(plate == 1) { 
			HanoiMove(source, dest); 
			return;
		}
		Hanoi(plate-1,source,freeSpace,dest);
		HanoiMove(source,dest);
		Hanoi(plate-1,freeSpace,dest,source);
	}
	
	static void HanoiMove(HanoiStack source,HanoiStack dest) {
		int temp = source.stack.pop();
		dest.stack.push(temp);
		counter++;
		sb.append(""+ source.name +" "+ dest.name +" \n");
	}
		
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
	
	
	static class HanoiStack {
		String name;
		Stack<Integer> stack;
		public HanoiStack(String name) {
			super();
			this.name = name;
			this.stack = new Stack<Integer>();
		}
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
