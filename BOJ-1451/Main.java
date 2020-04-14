import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[][] arr;
	static long[][] sum;
	static int H,W;
	static long ans = -1;
	public static void main(String[] args) throws IOException {
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		H = nextInt(stk);
		W = nextInt(stk);
		arr = new int[H+1][W+1];
		sum = new long[H+1][W+1];
		for(int i = 1; i <= H; i++) {
			char[] temp = io.inputStr().toCharArray();
			for(int j = 0; j < W; j++) arr[i][j+1] = temp[j]-48;
		}
		/*
      for (int i=1; i<=W; i++) {
         for (int j=1; j<=W; j++) {
             sum[i][j] = sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1]+(long)a[i][j];
         }
     }
     */
		CreateSum();
		Case01();
		Case02();
		Case03();
		Case04();
		Case05();
		Case06();
		System.out.println(ans);
	}	
	static void CreateSum() {
		
		int rowAdder = 0;
			for(int i = 1; i <= W; i++) {
				rowAdder += arr[1][i];
				sum[1][i] = rowAdder;
			}
			if(H == 1) return;
			
			for(int y = 2; y <= H; y++) {
				rowAdder = 0;
				for(int x = 1; x <= W; x++) {
					rowAdder += arr[y][x];
					sum[y][x] = sum[y-1][x] + rowAdder;
				}
			}
	}

	static long RectSum(int sRow,int sCol,int eRow, int eCol) {
		return sum[eRow][eCol] - sum[eRow][sCol-1] - sum[sRow-1][eCol] + sum[sRow-1][sCol-1];
	}

	static void Case01() {
		for(int i = 1; i <= H - 2; i++) {
			for(int j = i+1; j <= H - 1; j++) {
				long r1 = RectSum(1,1,i,W);
				long r2 = RectSum(i+1,1,j,W);
				long r3 = RectSum(j+1,1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}
	
	static void Case02() {
		for(int i = 1; i <= W-2; i++) {
			for(int j = i+1; j <= W-1; j++) {
				long r1 = RectSum(1,1,H,i);
				long r2 = RectSum(1,i+1,H,j);
				long r3 = RectSum(1,j+1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}
	
	static void Case03() {
		for(int row = 1; row <= H-1; row++) {
			for(int col = 1; col <= W-1; col++) {
				long r1 = RectSum(1,1,row,W);
				long r2 = RectSum(row+1,1,H,col);
				long r3 = RectSum(row+1,col+1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}

	static void Case04() {
		for(int row = 1; row <= H-1; row++) {
			for(int col = 1; col <= W-1; col++) {
				long r1 = RectSum(1,1,row,col);
				long r2 = RectSum(row+1,1,H,col);
				long r3 = RectSum(1,col+1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}
	
	static void Case05() {
		for(int row = 1; row <= H-1; row++) {
			for(int col = 1; col <= W-1; col++) {
				long r1 = RectSum(1,1,row,col);
				long r2 = RectSum(1,col+1,row,W);
				long r3 = RectSum(row+1,1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}
	
	static void Case06() {
		for(int row = 1; row <= H-1; row++) {
			for(int col = 1; col <= W-1; col++) {
				long r1 = RectSum(1,1,H,col);
				long r2 = RectSum(1,col+1,row,W);
				long r3 = RectSum(row+1,col+1,H,W);
				long temp = r1*r2*r3;
				if(ans < temp) ans = temp;
			}
		}
	}
	
	
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
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
