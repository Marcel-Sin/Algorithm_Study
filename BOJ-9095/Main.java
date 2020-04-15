import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int counter = 0;
	static int input = 0;
	static Queue<Integer> Q = new LinkedList<Integer>();
	public static void main(String[] args) throws IOException {
		int caseCount = io.inputInt();
		for(int i = 0 ; i < caseCount; i++) {
			input = io.inputInt();
			BFS(0);
			System.out.println(counter);
			counter = 0;
		}
		
	}	

	static void DFS(int num) {
		if(num == input) {
			counter++;
			return;
		}	
		else if(num > input) return;
		for(int i = 1; i <= 3; i++) DFS(num+i);
	}
	static void BFS(int num) {
		Q.add(1);
		Q.add(2);
		Q.add(3);
		while(Q.isEmpty() == false) {
			int root = Q.poll();
			if(root == input) counter++;
			else if(root < input) {
				for(int i = 1; i <= 3; i++) 
					if(root+i <= input) Q.add(root+i);
			}
		}
	}
	
	static int nextInt(StringTokenizer stk) {
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
