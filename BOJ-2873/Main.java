import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE;
	static final int INF = Integer.MAX_VALUE/2;
	static final int MAX = 1001;

	static int row,col;
	static int[][] map;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		Init();
		if(row%2 == 1 || col%2 == 1) Solve();
		else Double_Even_Solve();
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		row = nextInt(stk);
		col = nextInt(stk);
		map = new int[row][col];
		for (int i = 0; i < row; i++) {
			stk = new StringTokenizer(io.inputStr());
			for (int j = 0; j < col; j++) map[i][j] = nextInt(stk);
		}

	}
	// [해결전략]
	// 1. 짝짝 크기에 대하여, 1칸의 제거할 칸(최소)을 정한다.
	// 2. 파트를 3부분으로 나눈다.  [위에서 아래] - [제거할 녀석 중간 2행에 대한 파트] - [위에서 아래(역)]
	
	static void Double_Even_Solve() throws IOException{
		int eraseR = 0, eraseC= 1 , min = map[eraseR][eraseC];
		// 제거할 최소의 칸을 찾자. (짝수 r, 홀수 c 검사)
		for (int i = 0; i < row; i++) {
			for (int j = (i+1)%2; j < col; j+=2) {
				if(map[i][j] < min) {eraseR = i; eraseC = j; min = map[i][j];}	
			}
		}

		int erase_Start_Row = (eraseR/2) * 2 ;
		int curRow = 0;
		// [위에서 아래(RDL)]
		if(curRow < erase_Start_Row) {
			while(curRow < erase_Start_Row) {
				for (int i = 1; i < col; i++) sb.append('R');
				sb.append('D');
				for (int i = 1; i < col; i++) sb.append('L');
				sb.append('D');
				curRow += 2;
			
			}

		}



		int erase_Row = erase_Start_Row,erase_Col = 0, zigzag = 1;
		// [제거할 녀석의 파트]
		while(erase_Col < col) {
			//다음 위치를 본 후, 이동하려는데 막혀있으면 오른쪽으로 그냥 간다.
			if(erase_Col == eraseC && erase_Row+zigzag == eraseR) {
				//피해 지나 가려는데 맵이 끝난 상황 (break)
				if(col <= erase_Col+1) break;
			}
			// 이상 없음 위아래 지그재그하면서 오른쪽
			else {
				sb.append((zigzag == 1) ? 'D':'U');
				erase_Row += zigzag;
				zigzag = (zigzag == 1) ? -1:1;
			}
			erase_Col++;
			if(erase_Col != col) sb.append('R');
		}
		curRow = erase_Row+1;
		// [위에서 아래(LDR)]
		// 아직 처리할 행이 남았을 경우,
		if(curRow < row) {
			while(curRow < row) {
				sb.append('D');
				for (int i = 1; i < col; i++) sb.append('L');
				sb.append('D');
				for (int i = 1; i < col; i++) sb.append('R');
				curRow += 2;
			}
		}
		//System.out.println(sb.length());
		System.out.println(sb.toString());
		
	}
	static void Solve() {
		//행이 홀수 경우 (RDL)
		boolean zigzag = true;
		if(row%2 == 1) {
			for (int i = 0; i < row; i++) {
				for (int j = 1; j < col; j++) {
					if(zigzag == true) sb.append('R');
					else sb.append('L');
				}
				if(i+1 != row) sb.append('D');
				zigzag = !zigzag;
			}
		}
		//열이 홀수 경우 (RDL)
		else if(col%2 == 1) {
			for (int i = 0; i < col; i++) {
				for (int j = 1; j < row; j++) {
					if(zigzag == true) sb.append('D');
					else sb.append('U');
				}
				if(i+1 != col) sb.append('R');
				zigzag = !zigzag;
			}
		}
		//System.out.println(sb.length());
		System.out.println(sb.toString());
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