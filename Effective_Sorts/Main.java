import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	static IO_Manager io = new IO_Manager();
	static int[] MEM = new int[10];
	public static void main(String[] args) throws IOException {
		int[] t = {8,3,2,7,9,1,2,0,0,0};
		//InsertSort(t,7);
		//QuickSort(t,0,6);
		//MergeSort(t,0,6,MEM);
		for(int n : t) System.out.print(n + " ");
	}

	static int Partition(int[] a, int first, int last, int pivot) {
		int SI = first,P = a[pivot];
		// Pivot to Last
		Swap(a,pivot,last);
		
		// Make Groups
		for(int i = first; i < last; i++) {
			if(a[i] < P) Swap(a,i,SI++);	
		}
		
		// Pivot Positioning
		Swap(a,SI,last);
		return SI;
	}
	
	static void QuickSort(int[] a, int first, int last) {
		//Basic Condition
		if(last <= first) return;
		
		//Divide
		int part = Partition(a, first, last, (first+last)/2);
		
		//Conquer
		QuickSort(a,first,part);
		QuickSort(a,part+1,last);
	}
	static void Swap(int[] a,int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	static void Insertion(int[] a, int size, int num) {
		int i = 0,temp = num;
		// Find
		while(i < size && a[i] < num) i++;
		
		// Push
		for(int j = size; j > i; j--) a[j] = a[j-1];
		
		// Input
		a[i] = num;
	}
	static void InsertSort(int[] a, int size) {
		if(size == 1) return;
		for(int i = 0; i < size; i++) {
			Insertion(a,i,a[i]);
		}
	}
	
	static void MergeSort(int[] a, int low, int high, int[] temp) {
		//Basic Condition
		if(high <= low) return;
		
		//Divide
		int mid = (low + high)/2;
		
		//Conquer
		MergeSort(a,low,mid,temp);
		MergeSort(a,mid+1,high,temp);
		
		//Combine
		int i = low, j = mid+1;
		for(int k = low; k <= high; k++) {
			if(mid < i) temp[k] = a[j++];
			else if(high < j) temp[k] = a[i++];
			else if(a[i] > a[j]) temp[k] = a[j++];
			else temp[k] = a[i++];
		}
		
		//Copy
		for(i = low; i <= high; i++) a[i] = temp[i];	
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
