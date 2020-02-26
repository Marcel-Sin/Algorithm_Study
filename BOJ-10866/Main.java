import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
	
	public static void main(String[] args) throws IOException{
		IO_Manager io = new IO_Manager();
		MyDeque mq = new MyDeque(10000);
		int count = io.InputInt();
		StringTokenizer stk;
		Vector<String> v = new Vector<String>();
		
		for(int i = 0; i < count; i++) v.add(io.InputStr());
		for(int i = 0; i < count; i++) {
			stk = new StringTokenizer(v.elementAt(i));
			String op = stk.nextToken();
			if (op.compareTo("push_front") == 0) mq.push_front(nextTokenInt(stk));
			else if (op.compareTo("push_back") == 0) mq.push_back(nextTokenInt(stk));
			else if (op.compareTo("pop_front") == 0) io.Print(mq.pop_front()+"\n");
			else if (op.compareTo("pop_back") == 0) io.Print(mq.pop_back()+"\n");
			else if (op.compareTo("size") == 0) io.Print(mq.size()+"\n");
			else if (op.compareTo("empty") == 0) io.Print(mq.empty()+"\n");
			else if (op.compareTo("front") == 0) io.Print(mq.front()+"\n");
			else if (op.compareTo("back") == 0) io.Print(mq.back()+"\n");
		}
		io.Close();
		
		
	}	
	static public int nextTokenInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	
}



class MyDeque {
	private int[] arr;
	private int maxSize;
	private int count;
	private int front;
	private int back;
	
	public MyDeque(int size) {
		arr = new int[size];
		this.maxSize = size;
		this.count = 0;
		this.front = 0;
		this.back = 0;
	}
	
	public void push_front(int x) {
		if (count == maxSize) return;
		front--;
		if (front < 0) front = maxSize-1;
		arr[front] = x;
		if (count == 0) back = front;
		count++;
	}
	
	public void push_back(int x) {
		if (count == maxSize) return;
		back++;
		back = back % maxSize;
		arr[back] = x;
		if (count == 0) front = back;
		count++;
	}
	
	public int pop_front() {
		if (count == 0) return -1;
		int temp;
		temp = arr[front];
		front++;
		front = front % maxSize;
		if (count == 1) front = back;
		count--;
		return temp;
	}
	public int pop_back() {
		if (count == 0) return -1;
		int temp;
		temp = arr[back];
		back--;
		if (back < 0) back = maxSize-1;
		if (count == 1) back = front;
		count--;
		return temp;
	}
	public int size() {
		return count;
	}
	public int empty() {
		if (count == 0) return 1;
		else return 0;
	}
	public int front() {
		if (count == 0) return -1;
		else return arr[front];
	}
	public int back() {
		if (count == 0) return -1;
		else return arr[back];
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
