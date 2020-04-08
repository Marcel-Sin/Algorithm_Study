import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		int size = io.inputInt();
		Discuss[] times = new Discuss[size];
		
		for(int i = 0; i < size; i++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			times[i] = new Discuss(nextInt(stk),nextInt(stk));
		}
		Arrays.sort(times);
		int finishTime = 0;
		int count = 0;
		for(int i = 0; i < size; i++) {
			if(times[i].start >= finishTime) {
				finishTime = times[i].end;
				count++;
			}
		}
		System.out.println(count);
		
		
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}


	static class Discuss implements Comparable<Discuss>{
		int start;
		int end;
		public Discuss(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Discuss o) {
			if(this.end == o.end) {
				if(this.start == o.start) return 0;
				else return (this.start < o.start)? -1:1;
			}
			else return (this.end < o.end)? -1:1;
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
