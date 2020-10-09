import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NegINF = Integer.MIN_VALUE; 
	static int TOTAL;
	static RangedTree_MaxFrequency rtree;
	
	public static void main(String[] args) throws IOException {
		int[] arr = {0,0,1,1,1,3,3,4,5,6,7};
		rtree = new RangedTree_MaxFrequency(arr);
		for(int i = 0; i < 16; i++) System.out.println(i+"]  "+rtree.info[i].freqNum + " (Freq : " + rtree.info[i].freqCount + " )");
	}
	
	//[i,j]구간 내에서 최대빈도 수를 찾아주는 구간 트리
	
	static class RangedTree_MaxFrequency {
		public MaxFrequent_Info[] info;
		public int data_Size;
		
		
		public RangedTree_MaxFrequency(int[] array) {
			data_Size = array.length;
			info = new MaxFrequent_Info[data_Size*4];
			for(int i = 0; i < info.length; i++) info[i] = new MaxFrequent_Info();
			Init(array,0,0,data_Size-1);
		}
		
		public void Init(int[] array, int node, int nodeLeft, int nodeRight) {
			
			//Case 1 : Leaf Node
			if(nodeLeft == nodeRight) {
				info[node] = new MaxFrequent_Info(1,array[nodeLeft],1,array[nodeLeft],1,array[nodeLeft],1);
				return;
			}
			//Case 2 : Else Nodes (Core)
			int mid = (nodeLeft+nodeRight)/2, leftNode = node*2+1, rightNode = node*2+2;
			Init(array,leftNode,nodeLeft,mid);
			Init(array,rightNode,mid+1,nodeRight);
			
			info[node] = MaxFrequency_Merge(leftNode, rightNode);
			return;
			
		}
		
		// a가 왼쪽, b가 오른쪽
		public MaxFrequent_Info MaxFrequency_Merge(int a, int b) {
			MaxFrequent_Info ret = new MaxFrequent_Info();
			ret.size = info[a].size + info[b].size;
			
			ret.leftNum = info[a].leftNum;
			ret.leftFreq = info[a].leftFreq;
			if(info[a].leftFreq == info[a].size && info[a].rightNum == info[b].leftNum) ret.leftFreq +=  info[b].leftFreq;
			
			ret.rightNum = info[b].rightNum;
			ret.rightFreq = info[b].rightFreq;
			if(info[b].rightFreq == info[b].size && info[a].rightNum == info[b].leftNum ) ret.rightFreq += info[a].rightFreq;
			
			ret.freqNum = info[a].freqNum;
			ret.freqCount = info[a].freqCount;
			
			if(ret.freqCount <= info[b].freqCount) {
				ret.freqNum = info[b].freqNum;
				ret.freqCount = info[b].freqCount;
			}
			
			if(ret.freqCount < ret.leftFreq) {
				ret.freqNum = ret.leftNum;
				ret.freqCount = ret.leftFreq;
			}
			
			if(ret.freqCount <= ret.rightFreq ) {
				ret.freqNum = ret.rightNum;
				ret.freqCount = ret.rightFreq;
			}
			
			if(info[a].rightNum == info[b].leftNum && ret.freqCount < info[a].rightFreq+info[b].leftFreq) {
				ret.freqNum = info[a].rightNum;
				ret.freqCount = info[a].rightFreq+info[b].leftFreq;
			}
			return ret;
		}
		
	}
	
	static class MaxFrequent_Info {
		
		public int size;
		public int freqNum,freqCount;
		public int leftNum,leftFreq;
		public int rightNum,rightFreq;
		
		
		public MaxFrequent_Info() {
			super();
		}


		public MaxFrequent_Info(int size, int freqNum, int freqCount, int leftNum, int leftFreq, int rightNum,
				int rightFreq) {
			super();
			this.size = size;
			this.freqNum = freqNum;
			this.freqCount = freqCount;
			this.leftNum = leftNum;
			this.leftFreq = leftFreq;
			this.rightNum = rightNum;
			this.rightFreq = rightFreq;
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