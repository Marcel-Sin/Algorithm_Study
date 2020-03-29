import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 이 풀이는 https://doublik.tistory.com/6 사이트가 참조 됐습니다.

public class Main {
	
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		Cord mr_Minho,mr_Gangho;
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		mr_Minho = new Cord(nextDouble(stk),nextDouble(stk),nextDouble(stk),nextDouble(stk));
		mr_Gangho = new Cord(nextDouble(stk),nextDouble(stk),nextDouble(stk),nextDouble(stk));

		double p,q,low,high,min,distP,distQ,minimum=3e9;
		low = 0d;
		high = 100d;
		while (high-low >= 1e-10) {
			p = (2*low+1*high)/3;
			q = (1*low+2*high)/3;
			PercentageCord(mr_Minho, p);
			PercentageCord(mr_Gangho, p);
			distP = Distance(mr_Minho,mr_Gangho);
			
			PercentageCord(mr_Minho, q);
			PercentageCord(mr_Gangho, q);		
			distQ = Distance(mr_Minho,mr_Gangho);
			
			minimum = Math.min(minimum, Math.min(distP,distQ));
			if(distP >= distQ) low = p;
			else high = q;
		}
		System.out.printf("%.8f\n",minimum);
	}

	
	static double nextDouble(StringTokenizer stk) {
		return Double.parseDouble(stk.nextToken());
	}
	
	static double Distance(Cord a, Cord b) {
		double x = (b.curX-a.curX);
		double y = (b.curY-a.curY);
		double dist = Math.sqrt((x*x)+(y*y));
		return dist;
	}
	
	static void PercentageCord(Cord t, double percent) {
		t.curX = t.startX+t.distX*(percent/100);
		t.curY = t.startY+t.distY*(percent/100);
	}
	

	static class Cord {
		double curX,curY;
		double startX,startY;	
		double endX,endY;
		double distX,distY;
		public Cord(double startX, double startY, double endX, double endY) {
			super();
			this.curX = startX;
			this.curY = startY;
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
			distX = endX-curX;
			distY = endY-curY;
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
