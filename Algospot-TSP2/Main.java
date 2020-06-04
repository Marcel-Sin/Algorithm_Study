import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static int SIZE;
	static double[][] DIST;
	static double ANS;
	static int ALL_VISIT;
	static double[][] DP;
	
	
	//DP[i][j] 정의 : i번째 위치로부터, j의 비트 0이 된 모든 곳을 탐방하고 오는 최소 비용 값
	
	public static void main(String[] args) throws IOException {
		
		TOTAL = io.inputInt();
		for(int i = 0; i < TOTAL; i++) {
			Init();
			double temp = Double.MAX_VALUE;
			for(int x = 0; x < SIZE; x++) {
				temp = Math.min(temp,Find_Path(x,BitSet(0, x)));		
			}
			System.out.printf("%.10f\n",temp);
		}

		
	}
	
	
	static double Find_Path(int curPos,int visited) {
		if(visited == ALL_VISIT) { return 0.0; }
		
		int nextVisited = BitSet(visited, curPos);
		if(DP[curPos][nextVisited] != 0) return DP[curPos][nextVisited];
		
		double temp = Double.MAX_VALUE;
		for(int i = 0; i < SIZE; i++) {
			if(BitCheck(nextVisited, i) == false) {
				temp = Math.min(temp, DIST[curPos][i]+Find_Path(i,BitSet(nextVisited,i)));
			}
		}
		DP[curPos][nextVisited] = temp;
		return temp;
	}
	
	static void Init() throws IOException{
		SIZE = io.inputInt();
		DIST = new double[SIZE][SIZE];
		DP = new double[SIZE][1<<SIZE];
		ALL_VISIT = (1<<SIZE)-1;
		StringTokenizer stk = null;
		for(int a = 0; a < SIZE; a++) {
			stk = new StringTokenizer(io.inputStr());
			for(int b = 0; b < SIZE; b++) DIST[a][b] = Double.parseDouble((stk.nextToken()));	
		}
	}
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}

	static int BitSet(int value, int idx) {
		return value|(1 << idx);
	}
	static int BitUnset(int value, int idx) {
		return value&(~(1 << idx));
	}
	static boolean BitCheck(int value, int idx) {
		int temp = value&(1 << idx);
		if(temp != 0) return true;
		else return false;
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
	}}
