import java.util.*;

class Solution {
    static int answer = 0;
    public int solution(int n, int[][] results) {
        
        //------------------ init
        int size = results.length;
        Player[] players = new Player[n+1];
        for(int i = 0; i < n+1; i++) players[i] = new Player();
        int winner,loser;
        for(int i = 0; i < size; i++){
            winner = results[i][0];
            loser = results[i][1];
            players[winner].win.add(loser);
            players[loser].lose.add(winner);
        }
        //----------------- init END
        for(int i = 1; i <= n; i++) BFS(players,players[i],n-1);
        return answer;
    }
    
    void BFS(Player[] all,Player node,int goal) {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] check = new boolean[all.length];
        int counter = 0;
        //win check
        for(int x : node.win) { check[x] = true; queue.add(x); }
        while(queue.isEmpty() == false) {
            int next = queue.poll();
            counter++;
            for(int x : all[next].win) {
                 if(check[x] == false) { check[x] = true; queue.add(x); }
            }
        }
        
        //lose check
        for(int x : node.lose) { check[x] = true; queue.add(x); }
        while(queue.isEmpty() == false) {
            int next = queue.poll();
            counter++;
            for(int x : all[next].lose) { 
                if(check[x] == false) { check[x] = true; queue.add(x); }
            }
        }
        
        if(counter == goal) answer++;
    }
    
    class Player {
        HashSet<Integer> lose = new HashSet<Integer>();
        HashSet<Integer> win = new HashSet<Integer>();
    }
}