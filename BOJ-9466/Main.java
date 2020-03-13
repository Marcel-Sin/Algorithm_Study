import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static final int UNVISIT = 0;
	static final int VISIT = 1;
	static final int UNCYCLE = 2;
	static final int CYCLE = 3;

	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int[] dirs = new int[100001];
		int[] check = new int[100001]; // automatically All 0, 0 = no visit, 1 = visit, 2 = Uncycled ,3 = Cycled
		Stack<Integer> tracer = new Stack<Integer>();
		
		int testCount = io.inputInt();
		for (int test = 0; test < testCount; test++) {
			int total = io.inputInt();
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			
			for (int i = 1; i <= total; i++)
				dirs[i] = Integer.parseInt(stk.nextToken());
			
			// -------------------------- Efficiency -----------------------------
			for(int i=1;i<= total; i++) {
				DFS(dirs, i, check, tracer, total);
			}
			int resultSum = 0;
			for (int i = 1; i <= total; i++) {
				if (check[i] == UNCYCLE)
					resultSum++;
			}
			System.out.println(resultSum);
			ClearCheck(check, total);
		}
		
	}
	static public void ClearCheck(int[] arr,int size) {
		for (int i = 1; i <= size; i++) arr[i] = 0;
	}
	static public void DFS(int[] links, int pos, int[] check, Stack<Integer> tracer,int maxSize) {
		if(check[pos] >= UNCYCLE) return;
		int curPos = pos;
		while (true) {
			check[curPos] = VISIT;
			tracer.push(curPos);
			int nextVisit = links[curPos];
			if (check[nextVisit] == VISIT) {
				Cycles(check,tracer,nextVisit);
				return;
			} else if (check[nextVisit] >= UNCYCLE) {
				NoCycles(check,tracer);
				return;
			}
			else {
				curPos = nextVisit;
			}
		}
	}
	static public void Cycles(int[] check, Stack<Integer> tracer, int pos) {
		int next;
		check[pos] = CYCLE;
		while((next = tracer.pop()) != pos) {
			check[next] = CYCLE;
		}
		NoCycles(check,tracer);
	}
	
	static public void NoCycles(int[] check, Stack<Integer> tracer) {
		int next = -1;
		while(tracer.isEmpty() == false) {
			next = tracer.pop();
			check[next] = UNCYCLE;
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
