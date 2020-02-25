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
		Vector<String> strs = new Vector<String>();
		StringTokenizer st;
		MyStack stack = new MyStack(10000);
		
		// Process input
		int count = io.InputInt();
		for(int i = 0; i < count; i++) strs.add(io.InputStr());

		
		for(int i = 0; i < count; i++) {
			st = new StringTokenizer(strs.elementAt(i));
			String operator = st.nextToken();
			if (operator.compareTo("push") == 0) {
				stack.Push(Integer.parseInt(st.nextToken()));
			}
			else if (operator.compareTo("pop") == 0) io.Print(stack.Pop()+"\n");
			else if (operator.compareTo("size") == 0) io.Print(stack.Size()+"\n");
			else if (operator.compareTo("empty") == 0) io.Print(stack.Empty()+"\n");
			else if (operator.compareTo("top") == 0) io.Print(stack.Top()+"\n");
		}
		io.Close();
	
		
	}
}

class MyStack {
	
	private int[] arr;
	private int stackTop,stackCount,stackMax;
	
	public MyStack(int arrSize) {
		this.arr = new int[arrSize];
		this.stackMax = arrSize;
		this.stackCount = 0;
		this.stackTop = -1;
	}
	public void Push(int x) {
		if (stackCount+1 == stackMax) return;
		stackTop++;
		arr[stackTop] = x;
		stackCount++;
	}
	public int Pop() {
		int temp;
		if (stackCount == 0) return -1;
		temp = arr[stackTop];
		stackTop--;
		stackCount--;
		return temp;
	}
	public int Size() {
		return stackCount;
	}
	public int Empty() {
		if (stackCount == 0) return 1;
		else return 0;
	}
	public int Top() {
		if (stackCount == 0) return -1;
		else return arr[stackTop];
	}
	
}




// ************************************** //
// *-------------IO_Manager--------------* //
// ************************************** //
class IO_Manager {
	private BufferedReader br;
	private BufferedWriter bw;
	
	public IO_Manager() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}
	public Integer InputInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	public Long InputLong() throws IOException {
		return Long.parseLong(br.readLine());
	}
	public String InputStr() throws IOException {
		return br.readLine();
	}
	public void Print(String str) throws IOException {
		bw.write(str);
	}
	public void Close() throws IOException {
		br.close();
		bw.close();
	}
}
