import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static final int INF = 99999999;
	static int HEIGHT, WIDTH;
	static int TARGET_R,TARGET_C;
	static int[][] MAP;
	static int[][] DIST;
	static Coord DIR[];
	public static void main(String[] args) throws IOException {
		Init();
		BFS();
		System.out.println(DIST[TARGET_R][TARGET_C]);
	}

	
	static void BFS() {
		Queue<Coord> queue = new LinkedList<Coord>();
		DIST[0][0] = 0;
		queue.add(new Coord(0,0,0));
		int nextR,nextC,r,c,d;
		while(queue.isEmpty() == false) {
			Coord parent = queue.poll();
			r = parent.r;
			c= parent.c;
			d = parent.dist;
			if(d > DIST[r][c]) continue;
			for(int i = 0 ; i < 4; i++) {
				nextR = r+DIR[i].r;
				nextC = c+DIR[i].c;
				if(nextR < 0 || nextC < 0 || nextR >= HEIGHT || nextC >= WIDTH) continue;
				
				if(DIST[nextR][nextC] > d+MAP[nextR][nextC]) {
					DIST[nextR][nextC] = d+MAP[nextR][nextC];
					queue.add(new Coord(nextR,nextC,DIST[nextR][nextC]));
				}
			}
		}
	}
	
	
	static class Coord {
		int r,c,dist;

		public Coord(int r, int c, int dist) {
			super();
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
		
	}
	
	static void Init() throws IOException{
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		WIDTH = nextInt(stk);
		HEIGHT = nextInt(stk);
		MAP = new int[HEIGHT][WIDTH];
		DIST = new int[HEIGHT][WIDTH];
		DIR = new Coord[4];
		DIR[0] = new Coord(0,-1,0);
		DIR[1] = new Coord(0,1,0);
		DIR[2] = new Coord(-1,0,0);
		DIR[3] = new Coord(1,0,0);
		
		char[] temp;
		for(int i = 0; i < HEIGHT; i++) {
			temp = io.inputStr().toCharArray();
			for(int j = 0; j < WIDTH; j++) MAP[i][j] = temp[j]-'0';
			Arrays.fill(DIST[i],INF);
		}
		TARGET_R = HEIGHT-1;
		TARGET_C = WIDTH-1;
		
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
