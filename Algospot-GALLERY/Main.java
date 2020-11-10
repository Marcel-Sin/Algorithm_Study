import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	static final int MAX_LETTER = 26; 
	
	static int TOTAL,G,H;
	static boolean[] CAM = new boolean[1000];
	static boolean[] CAM_AREA = new boolean[1000];
	static boolean[] VISITED = new boolean[1000];
	
	static ArrayList<Integer>[] GRAPH = new ArrayList[1000];
	
	public static void main(String[] args) throws IOException {	
		TOTAL = io.inputInt();
		for(int i = 0; i < 1000; i++) GRAPH[i] = new ArrayList<Integer>();
		
		for(int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(Solve());
		}
		
	}
	
	static void Init() throws IOException{
		for(int i = 0; i < 1000; i++) GRAPH[i].clear();
		Arrays.fill(VISITED, false);
		Arrays.fill(CAM, false);
		Arrays.fill(CAM_AREA, false);
		
		
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		G = nextInt(stk);
		H = nextInt(stk);
		for(int i = 0; i < H; i++) {
			stk = new StringTokenizer(io.inputStr());
			Connect(nextInt(stk),nextInt(stk));
		}
	}
	
	static int Solve() {
		int ans = 0;
		for(int i = 0; i < G; i++) {
			if(CAM_AREA[i] == false) Cam_Need(i,true);
		}
		for(int i = 0; i < G; i++) {
			if(CAM[i] == true) ans++;
		}
		return ans;
	}
	
	
	// 카메라 설치가 필요하면 설치한다.
	static boolean Cam_Need(int here, boolean isRoot) {
		VISITED[here] = true;
		// 부모에게 말단 노드가 있을 경우, 무조건 부모에게 설치 요청
		if(!isRoot && GRAPH[here].size() == 1) return true;
		
		// 캠 설치를 요청하는 자식이 있으면 설치
		for(int there : GRAPH[here]) {
			if ( !VISITED[there] && Cam_Need(there,false) == true) {
				Cam_Setup(here);
				return false;
			}
		}
		
		// 루트인데, 캠 영역에 못들어갔으면 자신이 설치해야 함.
		if(isRoot && CAM_AREA[here] == false) {
			Cam_Setup(here);
			return false;
		}
		
		// 자신이 캠 영역에 못들어가는 경우, 설치 요청
		if(CAM_AREA[here] == false) return true;
		return false;
	}
	static void Cam_Setup(int pos) {
		CAM[pos] = true;
		CAM_AREA[pos] = true;
		for(int there : GRAPH[pos]) CAM_AREA[there] = true;
	}
	
	
	
	
	static void Connect(int a, int b) {
		GRAPH[a].add(b);
		GRAPH[b].add(a);
	}


	// ===================== functions for PS =====================
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
	static void Display(int[] arr, int limit) {
		//System.out.println("요소갯수 : " + arr.length);
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		System.out.println("요소갯수 : " + (arr.length * arr[0].length));
		for (int i = 0; i < limit; i++) {
			System.out.print("["+i+"] : ");
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
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