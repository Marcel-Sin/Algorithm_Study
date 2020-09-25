import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static int TOTAL;
	static HeapTree tree = new HeapTree(50000);
	public static void main(String[] args) throws IOException {
		tree.Insert(5);
		tree.Insert(9);
		tree.Insert(2);
		tree.Insert(33);
		tree.Insert(99);
		tree.Insert(1);
		tree.Insert(7);
		tree.Insert(105);
		
		int loop = tree.size;
		for(int i=0; i < loop; i++) {
			System.out.println(tree.Extract());
		}
		
		
		Display(tree.arr,8);
	}
	
	static class HeapTree {
		int[] arr;
		int size;
		
		public HeapTree(int arrLen) {
			arr = new int[arrLen];
			size = 0;
		}
		
		public void Insert(int value) {
			int place = size, parent = (size-1)/2;
			arr[place] = value;
			
			while(true) {
				if(place == 0 || arr[parent] >= arr[place]) break;
				else {
					Swap(place,parent);
					place = parent;
					parent = (place-1)/2;
				}
			}
			
			size++;
		}
		public int Extract() {
			if(size == 0) return -1;
			int ret = arr[0];
			size--;
			arr[0] = arr[size];
			
			int curPos = 0, left_Child, right_Child;
			
			while(true) {
				left_Child = curPos*2+1;
				right_Child = curPos*2+2;
				
				//★★★ 코드 처리를 줄이는 핵심,  초기 값을 현재 위치로 잡는 것.
				int nextPos = curPos;

				
				//자식이 없을 때,
				if(size <= left_Child) break;
				
				//왼쪽만의 경우
				if(arr[curPos] < arr[left_Child]) nextPos = left_Child;
				if(right_Child < size && arr[nextPos] < arr[right_Child]) nextPos = right_Child;
				
				
				if(nextPos == curPos) break;
				Swap(curPos,nextPos);
				curPos = nextPos;
			}
			
			return ret;
		}
		
		
		public void Swap(int a, int b) {
			int temp = arr[a];
			arr[a] = arr[b];
			arr[b] = temp;
		}
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