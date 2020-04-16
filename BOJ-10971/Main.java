import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int ans = Integer.MAX_VALUE;
	static int count;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		count = io.inputInt();
		StringTokenizer stk;
		map = new int[count][count];
		for(int i = 0; i < count; i++) {
			stk = new StringTokenizer(io.inputStr());
			for(int j = 0; j < count; j++) map[i][j] = nextInt(stk);
		}
		boolean[] newVisit = new boolean[count];
		for(int i = 0; i < count; i++) {
			boolean[] temp = new boolean[count];
			temp[i] = true;
			Salesman newMan = new Salesman(0,0,i,i,temp);
			DFS(newMan);
		}

		System.out.println(ans);
	}	


	
	static void DFS(Salesman man) {
		if(man.visitCount == count-1) {
			if(map[man.where][man.start] == 0) return;
			man.moveDist += map[man.where][man.start];
			if(man.moveDist < ans) ans = man.moveDist;
			return;
		}
		boolean[] check = man.visit;
		for(int i = 0; i < count; i++) {
			if(check[i] == false && map[man.where][i] != 0) {
				boolean[] newVisit = check.clone();
				newVisit[i] = true;
				Salesman newMan = new Salesman(man.moveDist+map[man.where][i],man.visitCount+1, man.start, i, newVisit);
				DFS(newMan);
			}
		}
	}
	
	static class Salesman {
		int moveDist;
		int visitCount;
		int start;
		int where;
		boolean[] visit;
		public Salesman(int moveDist, int visitCount, int start, int where, boolean[] visit) {
			super();
			this.moveDist = moveDist;
			this.visitCount = visitCount;
			this.start = start;
			this.where = where;
			this.visit = visit;
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
