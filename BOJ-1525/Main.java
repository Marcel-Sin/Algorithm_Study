import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int target = 123456789;
	static int input;
	static HashMap<Integer, Boolean> check = new HashMap<Integer, Boolean>();
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			for(int j = 0; j < 3; j++) sb.append(stk.nextToken());	
		}
		char[] temp = sb.toString().toCharArray();
		for(int i = 0; i < 9; i++) if(temp[i] == '0') temp[i] = '9';
		input = Integer.parseInt(new String(temp));
		
		if(BFS(input) == false) System.out.println(-1);
		
		
		
	}	
	
	static boolean BFS(int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		check.put(start, true);
		queue.add(start);
		int counter = 0;
		int queueSize, row, col, pos,parent;
		int next;
		char[] parentChar;
		while(queue.isEmpty() == false) {
			queueSize = queue.size();
			for(int i = 0; i < queueSize; i++) {
				parent = queue.poll();
				if(parent == target) {
					System.out.println(counter);
					return true;
				}
				pos = NinePos(parent);
				row = pos/3;
				col = (pos)%3;
				
				if(row-1 >= 0) {
					next = (row-1)*3+col;
					next = SwapInteger(parent, pos, next);
					if(check.containsKey(next) == false) {
						queue.add(next);
						check.put(next,true);
					}
				}
				if(row+1 < 3) {
					next = (row+1)*3+col;
					next = SwapInteger(parent, pos, next);
					if(check.containsKey(next) == false) {
						queue.add(next);
						check.put(next,true);
					}
				}
				if(col-1 >= 0) {
					next = (row)*3+col-1;
					next = SwapInteger(parent, pos, next);
					if(check.containsKey(next) == false) {
						queue.add(next);
						check.put(next,true);
					}
				}
				if(col+1 < 3) {
					next = (row)*3+col+1;
					next = SwapInteger(parent, pos, next);
					if(check.containsKey(next) == false) {
						queue.add(next);
						check.put(next,true);
					}
				}
			}
			counter++;
		}
		return false;
	}
	
	static int NinePos(int num) {
		int Pos=8;
		while(num != 0) {
			if(num%10 == 9) return Pos;
			num /= 10;
			Pos--;
		}
		return -1;
	}
	
	static int SwapInteger(int num,int a, int b) {
		char[] charNum = String.valueOf(num).toCharArray();
		char temp = charNum[a];
		charNum[a] = charNum[b];
		charNum[b] = temp;
		return Integer.parseInt(new String(charNum));
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
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
