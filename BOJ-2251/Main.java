import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int A = 0, B = 1, C = 2;
	static int[] FULL = new int[3];
	static boolean[][][] check;
	static SortedSet<Integer> answer = new TreeSet<Integer>();
	public static void main(String[] args) throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		FULL[0] = nextInt(stk);
		FULL[1] = nextInt(stk);
		FULL[2] = nextInt(stk);
		check = new boolean[201][201][201];
		Bottle tempBottle = new Bottle(0, 0, FULL[2]);
		BFS(tempBottle);
		for(int n : answer) System.out.print(n + " ");
		
		
	}	

	static void BottleVisit(Bottle set) {
		check[set.value[0]][set.value[1]][set.value[2]] = true;
	}
	
	static boolean BottleCheck(Bottle set) {
		if(check[set.value[0]][set.value[1]][set.value[2]] == false) return false;
		return true;
	}
	
	static void ExtractAnswer(Bottle set) {
		if(set.value[0] == 0) answer.add(set.value[2]);
	}
	
	
	static void BFS(Bottle startSet) {
		Queue<Bottle> queue = new LinkedList<Bottle>();
		BottleVisit(startSet);
		queue.add(startSet);
		
		while(queue.isEmpty() == false) {
			Bottle parent = queue.poll();
			ExtractAnswer(parent);
			Bottle[] nexts = new Bottle[6];
			for(int i = 0; i < 6; i++) {
				nexts[i] = new Bottle(parent.value[0],parent.value[1],parent.value[2]);
			}
			nexts[0].Pour(0,1);
			nexts[1].Pour(0,2);
			nexts[2].Pour(1,2);
			nexts[3].Pour(1,0);
			nexts[4].Pour(2,0);
			nexts[5].Pour(2,1);
			for(int i = 0; i < 6; i++) {
				if(BottleCheck(nexts[i]) == false) {
					BottleVisit(nexts[i]);
					queue.add(nexts[i]);
				}
			}
			
		}
		
		
	}
	
	static class Bottle {
		int[] value;
		public Bottle(int a, int b, int c) {
			value = new int[3];
			value[0] = a;
			value[1] = b;
			value[2] = c;
		}
		public void Pour(int from, int to) {
			if(value[from] == 0) return;
			int toRestSpace = FULL[to]-value[to];
			value[to] += (toRestSpace >= value[from])? value[from]:toRestSpace;
			value[from] = (toRestSpace >= value[from]) ? 0:value[from]-toRestSpace;
		}
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
