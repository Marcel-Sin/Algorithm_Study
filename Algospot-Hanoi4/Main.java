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
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE; 
	static final int INF = Integer.MAX_VALUE; 
	
	static int TOTAL;
	
	// checkMap <Hanoi4Data, Distance>
	static HashMap<Integer, Integer> checkMap = new HashMap<Integer, Integer>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int[] TWO_BIT_ACCESS = new int[16];
	static int problemSize,problemData, finishData;
	
	//Temporary Memory for Method
	static int[] mem = new int[20];
	static int[] firstPlates = new int[4];
	
	public static void main(String[] args) throws IOException {	
		TOTAL = io.inputInt();
		for (int i = 0; i < TOTAL; i++) {
			Init();
			System.out.println(BFS());
		}
	}
	static void Init() throws IOException{
		queue.clear();
		checkMap.clear();
		//Initializing for 2-bits indexing
		int num = 0b11;
		for (int i = TWO_BIT_ACCESS.length-1; i >= 0; i--) {
			TWO_BIT_ACCESS[i] = num;
			num = num << 2;
		}
		problemSize = io.inputInt();
		
		// Creating binary-datas for PS 
		problemData = 0;
		for(int pos = 0; pos < 4; pos++) {
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			stk.nextToken();
			while(stk.hasMoreTokens()) {
				problemData = Set_2Bits(problemData,nextInt(stk)-1, pos);
			}
		}
		finishData = 0;
		for(int i = 0; i < problemSize; i++) {
			finishData = Set_2Bits(finishData, i, 3);
		}
		
	}
	static int BFS() {
		queue.add(problemData);
		checkMap.put(problemData, 0);
		
		while(!queue.isEmpty()) {
			int here = queue.poll();
			
			int[] adj = GetAdj(here);
			for (int i = 0; i < adj.length; i++) {
				if(adj[i] == -1) break;
				if(checkMap.containsKey(adj[i]) == false) {
					if(adj[i] == finishData) return checkMap.get(here)+1;
					checkMap.put(adj[i],checkMap.get(here)+1);
					queue.add(adj[i]);
				}
			}
		}
		
		return -1;
	}
	
	static int[] GetAdj(int data){
		Arrays.fill(mem, -1);
		Arrays.fill(firstPlates, -1);
		
		
		//각 기둥의 맨앞 원판을 찾는다.
		int plate = 0, pos = 0, loop=0;
		while(plate < problemSize && loop < firstPlates.length) {
			pos = Get_2Bits(data, plate);
			if(firstPlates[pos] == -1) {
				firstPlates[pos] = plate;
				loop++;
			}
			plate++;
		}
		
		// 각 기둥의 맨앞 원판이 다른 기둥보다 크면 해당 자리로 옮겨본다.
		int adjCount = 0;
		for(int p1 = 0; p1 < firstPlates.length; p1++) {
			if(firstPlates[p1] == -1) continue;
			for(int p2 = 0; p2 < firstPlates.length; p2++) {
				if(p1 == p2) continue;
				if(firstPlates[p1] < firstPlates[p2] || firstPlates[p2] == -1) {
					mem[adjCount++] = Set_2Bits(data, firstPlates[p1], p2);
				}
			}	
		}
		
		return mem;
	}
	
	// 2비트 단위로 데이터를 가져온다.
	static int Get_2Bits(int data, int idx) {
		return (data & TWO_BIT_ACCESS[idx]) >>> (30-2*idx);
	}
	// 2비트 단위로 데이터를 저장한다.
	static int Set_2Bits(int data, int idx, int value) {
		
		// 해당 Data의 자리를 0으로 만든다.
		data = data & (~TWO_BIT_ACCESS[idx]);
		// 넣을 데이터의 자리를 맞춘다.
		value = value << (30-2*idx);
		
		// 넣고 반환
		return data | value;
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