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
	static int A,B,ANSWER;
	static boolean[] notPrime = new boolean[10001];
	static int[] check = new int[10001];
	public static void main(String[] args) throws IOException {
		int caseCount = io.inputInt();
		Eratos();
		for(int i = 0; i < caseCount; i++) {
			Arrays.fill(check, -1);
			StringTokenizer stk = new StringTokenizer(io.inputStr());
			A = nextInt(stk);
			B = nextInt(stk);
			BFS(A);
			if(check[B] == -1) System.out.println("Impossible");
			else System.out.println(check[B]);
		}
		
	}	

	static void BFS(int startNumber) {
		Queue<Integer> queue = new LinkedList<Integer>();
		check[startNumber] = 0;
		queue.add(startNumber);
		int counter = 0;
		while(queue.isEmpty() == false) {
			int loopCount = queue.size();
			for(int loop = 0 ; loop < loopCount; loop++) {
				int parent = queue.poll();
				for(int i = 1; i <= 4; i++) {
					for(int j = 0; j < 10; j++) {
						int nextNum = ReplacePos(parent, i, j);
						if(check[nextNum] == -1 &&  isPrime(nextNum) && nextNum >= 1000) {
							check[nextNum] = counter+1;
							queue.add(nextNum);
						}
					}
				}
			}
			counter++;
		}
	}
	
	static void Eratos() {
		notPrime[1] = true;
		for(int i = 2; i <= 100; i++) {
			for(int j = i+i; j <= 10000; j+=i) notPrime[j] = true;
		}
	}
	
	static int ReplacePos(int num,int pos,int replacer) {
		int value = 0, rest = 0;
		if(pos == 1) {
			value = num % 1000;
			replacer *= 1000;
			return value+replacer;
		}
		else if(pos == 2) {
			value = num / 1000;
			value *= 1000;
			rest = num % 100;
			replacer *= 100;
			return value+rest+replacer;
		}
		else if(pos == 3) {
			value = num / 100;
			value *= 100;
			rest = num % 10;
			replacer *= 10;
			return value+rest+replacer;
		}
		else if(pos == 4) {
			value = num / 10;
			value *= 10;
			return value+replacer;
		}
		return -1;
	}
	
	static boolean isPrime(int n) {
	  return (notPrime[n] == true)? false:true;
	}
	
	static int nextInt(StringTokenizer stk) {
		return Integer.parseInt(stk.nextToken());
	}
	static void Swap(int[] a, int i , int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] =temp;
	}	
	static void PartialReverse(int[] a, int start, int end) {
		int temp;
		while(end > start) {
			temp = a[start];
			a[start++] = a[end];
			a[end--] = temp;
		}
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
