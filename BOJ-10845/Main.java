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
		MyQueue que = new MyQueue(10000);
		
		// Processing inputs
		int count = io.InputInt();
		for(int i = 0; i < count; i++) strs.add(io.InputStr());

		
		for(int i = 0; i < count; i++) {
			st = new StringTokenizer(strs.elementAt(i));
			String operator = st.nextToken();
			if (operator.compareTo("push") == 0) {
				que.Push(Integer.parseInt(st.nextToken()));
			}
			else if (operator.compareTo("pop") == 0) io.Print(que.Pop()+"\n");
			else if (operator.compareTo("size") == 0) io.Print(que.Size()+"\n");
			else if (operator.compareTo("empty") == 0) io.Print(que.Empty()+"\n");
			else if (operator.compareTo("front") == 0) io.Print(que.Front()+"\n");
			else if (operator.compareTo("back") == 0) io.Print(que.Back()+"\n");
		}
		io.Close();
	
		
	}
}

class MyQueue {
	
	private int[] arr;
	private int queueFront,queueBack,queueCount,maxSize;
	
	public MyQueue(int arrSize) {
		this.arr = new int[arrSize];
		this.maxSize = arrSize;
		this.queueCount = 0;
		this.queueFront = -1;
		this.queueBack = -1;
	}
	public void Push(int x) {
		if (queueCount == maxSize) return;
		queueBack++;
		queueBack = queueBack % maxSize;
		arr[queueBack] = x;
		if (queueCount == 0) queueFront = queueBack;
		queueCount++;
	}
	public int Pop() {
		int temp;
		if (queueCount == 0) return -1;
		temp = arr[queueFront];
		queueFront++;
		queueFront = queueFront % maxSize;
		queueCount--;
		return temp;
	}
	public int Size() {
		return queueCount;
	}
	public int Empty() {
		if (queueCount == 0) return 1;
		else return 0;
	}
	public int Front() {
		if (queueCount == 0) return -1;
		else return arr[queueFront];
	}
	public int Back() {
		if (queueCount == 0) return -1;
		else return arr[queueBack];
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
