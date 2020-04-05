import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
	static long swapCounter = 0;
	static int[] sorted = new int[500000];
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		ArrayList<Point> parr = new ArrayList<Point>();
	
		for(int i = 0; i < size; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			parr.add(new Point(nextInt(stk),nextInt(stk)));
		}
		
		CloestPair(parr);
		
	}
	
	
	
	
	
	static void CloestPair(ArrayList<Point> pairList) {
		Collections.sort(pairList,new Comparator_X());
		SortedSet<Point> candidates = new TreeSet<Point>(new Comparator_Y());
		candidates.add(pairList.get(0));
		candidates.add(pairList.get(1));
		int answer = Distance(pairList.get(0),pairList.get(1));
		int start = 0;
		for (int i = 2; i < pairList.size(); i++) {
			Point now = pairList.get(i);
			while (start < i) {
				Point p = pairList.get(start);
				int difX = now.x - p.x;
				if(difX*difX > answer) {
					candidates.remove(p);
					start++;
				}
				else {
					break;
				}
			}
			int d = (int) Math.sqrt((double) answer) + 1;
			Point lower = new Point(-100000, now.y - d);
			Point upper = new Point(100000, now.y + d);
			SortedSet<Point> ySearch = candidates.subSet(lower, upper);
			Iterator<Point> iter = ySearch.iterator();
			while (iter.hasNext()) {
				int dist = Distance(now, iter.next());
				if (answer > dist)
					answer = dist;
			}
			candidates.add(pairList.get(i));
		}
		System.out.println(answer);
	}

	static int Distance(Point A, Point B) {
		int x = (B.x-A.x);
		int y = (B.y-A.y);
		return x*x + y*y;
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static class Comparator_X implements Comparator<Point> {
		@Override
		public int compare(Point o1, Point o) {
			if(o1.x < o.x) return -1;
			else if(o1.x == o.x) return 0;
			else return 1;
		}
	}
	static class Comparator_Y implements Comparator<Point> {
		@Override
		public int compare(Point o1, Point o) {
			if(o1.y == o.y) {
				if(o1.x == o.x) return 0;
				else return o1.x < o.x? -1:1;
			}
			else return o1.y < o.y ? -1:1;
		}
	}

	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "("+ this.x + ", "+this.y+")";
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
