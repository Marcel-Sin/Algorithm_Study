import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {
	
	public static void main(String[] args) throws IOException {
		IO_Manager io = new IO_Manager();
		StringTokenizer stk = new StringTokenizer(io.inputStr());
		int sizeA = nextInt(stk);
		int[] arrA = new int[sizeA];
		int sizeB = nextInt(stk);
		int[] arrB = new int[sizeB];
		int[] arrC = new int[sizeA+sizeB];
		
		
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < sizeA; i++) arrA[i] = nextInt(stk);
		stk = new StringTokenizer(io.inputStr());
		for (int i = 0; i < sizeB; i++) arrB[i] = nextInt(stk);

		MergeSort(arrC,arrA,arrB);
		for (int n : arrC) io.write(n+" ");
		io.close();
	}

	static void MergeSort(int[] output, int[] arrA, int[] arrB) {
		int complete = 0;
		int a=0,b=0;
		while(complete < output.length) {
			if (a < arrA.length && b < arrB.length) {
				if (arrA[a] < arrB[b]) {
					output[complete] = arrA[a];
					a++;
				} else if (arrA[a] > arrB[b]) {
					output[complete] = arrB[b];
					b++;
				}
				else {
					output[complete] = arrA[a];
					a++;
				}
				complete++;
			}
			else {
				if(a == arrA.length) {
					for(int i = b; i < arrB.length; i++) {
						output[complete] = arrB[i];
						complete++;
					}
				}
				else if(b == arrB.length) {
					for(int i = a; i < arrA.length; i++) {
						output[complete] = arrA[i];
						complete++;
					}
				}
				else break;
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
