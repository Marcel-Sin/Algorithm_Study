import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static IO_Manager io = new IO_Manager();
	static final int NINF = Integer.MIN_VALUE / 2;
	static final int INF = Integer.MAX_VALUE / 2;
	static final int MAX = 1000;
	
	public Solution solve = new Solution();
	static String[] genres;
	static int[] plays;

	public static void main(String[] args) throws IOException {
		Execute();
	}
	
	
	static void Execute() {
		Main mainObj = new Main();
		int[] ans = mainObj.solve.solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
		Display(ans, ans.length);
	}
	
	class Solution {
		public int[] solution(String[] genres, int[] plays) {
			int[] answer = {};
			HashMap<String, PriorityQueue<Album>> map = new HashMap<String, PriorityQueue<Album>>();
			HashMap<String, Integer> keyOrder = new HashMap<String, Integer>();
			// 키와 함께 초기화
			for (int i = 0; i < genres.length; i++) {
				if (map.containsKey(genres[i]) == false) {
					map.put(genres[i], new PriorityQueue<Album>());
					keyOrder.put(genres[i], 0);
				}
			}
			// 데이터 생성
			for (int i = 0; i < genres.length; i++) {
				map.get(genres[i]).add(new Album(i, genres[i], plays[i]));
				keyOrder.put(genres[i], keyOrder.get(genres[i]) + plays[i]);
			}
			ArrayList<Pair> keyList = new ArrayList<Pair>();
			for (String str : keyOrder.keySet()) {
				keyList.add(new Pair(str, keyOrder.get(str)));
			}
			Collections.sort(keyList);
			ArrayList<Integer> ansList = new ArrayList<Integer>();
			for (Pair keyPair : keyList) {
				PriorityQueue<Album> pq = map.get(keyPair.key);
				if (pq.size() == 1)
					ansList.add(pq.poll().number);
				else {
					ansList.add(pq.poll().number);
					ansList.add(pq.poll().number);
				}
			}
			answer = new int[ansList.size()];
			for (int i = 0; i < answer.length; i++)
				answer[i] = ansList.get(i);
			return answer;
		}
	   class Album implements Comparable<Album> {
	   	int number,play;
	   	String genre;

			public Album(int number, String genre,int play) {
				super();
				this.number = number;
				this.play = play;
				this.genre = genre;
			}

			@Override
			public int compareTo(Album o) {
				if(this.play == o.play) return (this.number < o.number) ? -1:1;
				else return (this.play > o.play) ? -1:1;
			}
	   }
	   class Pair implements Comparable<Pair>{
	   	public String key;
	   	public int value;
			public Pair(String key, int value) {
				super();
				this.key = key;
				this.value = value;
			}
			@Override
			public int compareTo(Pair o) {
				if(this.value == o.value) return 0;
				else return (this.value > o.value) ? -1:1;
			}
	   }
	}


//	===================== ETC functions for PS =====================
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
		for (int i = 0; i < limit; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
	static void Display(int[][] arr, int limit) {
		for (int i = 0; i < limit; i++) {
			for (int j = 0; j < limit; j++) {
				System.out.printf(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}


//-------------IO_Manager--------------
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