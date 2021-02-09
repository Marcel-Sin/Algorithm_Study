import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE;
	
	static double[] problem = new double[8];
	static Pair Minho,Kangho;
	
	public static void main(String[] args) throws IOException {	
		Init();
		System.out.printf("%.10f",Solve());
	}
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < problem.length; i++) {
			problem[i] = Double.parseDouble(stk.nextToken());
		}
		Minho = new Pair(problem[0],problem[1],problem[2],problem[3]);
		Kangho = new Pair(problem[4],problem[5],problem[6],problem[7]);
		
	}

	static double Solve() throws IOException {
		double low = 0, p, q , high = 1.0d;
		double distP,distQ;
		double distLow = Distance_Pair_WalkUnit(Minho,Kangho,low);
		double distHigh = Distance_Pair_WalkUnit(Minho,Kangho,high);
		double ret = Min(distLow,distHigh);
		while(high - low > 1.0e-10) {
			p = (low*2+high*1)/3;
			q = (low*1+high*2)/3;
			distP = Distance_Pair_WalkUnit(Minho,Kangho,p);
			distQ = Distance_Pair_WalkUnit(Minho,Kangho,q);
			ret = Min(distQ,ret);
			ret = Min(distP,ret);
			
			if(distP >= distQ) low = p;
			else high = q;
		}
		return ret;
	}
	
	static double Distance_Pair_WalkUnit(Pair a, Pair b, double walkUnit) {
		//두 좌표 쌍에 목적지까지 walkUnit 적용
		double ax = a.x+a.destX_Add*walkUnit;
		double ay = a.y+a.destY_Add*walkUnit;
		
		double bx = b.x+b.destX_Add*walkUnit;
		double by = b.y+b.destY_Add*walkUnit;
		
		
		//실제 거리 계산
		double ret = Math.pow((bx - ax),2);
		ret += Math.pow((by - ay),2);
		return Math.sqrt(ret);
	}
	

	static class Pair {
		public double x,y;
		public double destX_Add,destY_Add;
		
		public Pair(double x, double y, double destX, double destY) {
			super();
			this.x = x;
			this.y = y;
			this.destX_Add = destX-x;  
			this.destY_Add = destY-y;
		}		
	}
	
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ===================== functions for PS =====================
	// ============================================================
	// ============================================================
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static long Min(long a, long b) {
		return (a > b) ? b : a;
	}
	static long Max(long a, long b) {
		return (a > b) ? a : b;
	}
	static int Min(int a, int b) {
		return (a > b) ? b : a;
	}
	static int Max(int a, int b) {
		return (a > b) ? a : b;
	}
	static double Min(double a, double b) {
		return (a > b) ? b : a;
	}
	static double Max(double a, double b) {
		return (a > b) ? a : b;
	}
	static void Display(int[] arr, int limit) {
		// System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf("%2d ",arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
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