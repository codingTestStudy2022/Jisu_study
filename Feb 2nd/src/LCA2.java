/*
 * 2022.02.10
 * 백준 11438번 "네트워크 연결"
 * https://www.acmicpc.net/problem/11438
 *
 * 그래프 유형
 */

import java.util.*;
import java.io.*;

public class LCA2 {

    static int n;

    static ArrayList[] tree;
    static int[][] parent;
    static int LogN;
    static int[] depth;

    public static void main(String[] args) throws IOException{

        //입력
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n+1];
        getLogN();
        parent  = new int[LogN][n+1];
        depth = new int[n+1];

        for(int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<Vertex>();
        }

        for(int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(new Vertex(a, b));
            tree[b].add(new Vertex(b, a));
        }


        //깊이 찾기 + 테이블의 첫 행 채우기
        BFS();
        //나머지 테이블 채우기
        setSparseTable();

        //결과 입력
        int m = Integer.parseInt(br.readLine());

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int answer = LCA(a, b);
            bw.append(answer + "\n");
        }

        br.close();

        bw.flush();
        bw.close();

    }

    static void getLogN() {
        LogN = 0;
        while(Math.pow(2, LogN) < n) {
            LogN += 1;
        }
    }

    static int LCA(int a, int b) {

        if(depth[a] < depth[b]){
            int tmp = a;
            a = b;
            b = tmp;
        }


        //높이 맞추기
        for(int i = 0; i < LogN; i++){
            if(((depth[a] - depth[b]) & (1<<i)) >= 1) {
                a = parent[i][a];
            }
        }

        //높이를 맞췄다면 둘이 일치하는지 검사
        if(a == b){
            return a;
        }

        //공통조상이 아닐때까지 부모를 따라 올라간다.
        //최종적으로 최소공통조상의 바로 아래까지 올라감
        for(int i = LogN-1; i >= 0; i--) {
            if(parent[i][a] != parent[i][b]){
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];
    }

    static void setSparseTable() {
        for(int i = 1; i < LogN; i++) {
            for(int j = 0; j <= n; j++){
                int parentOfNow = parent[i-1][j];
                parent[i][j] = parent[i-1][parentOfNow];
            }
        }
    }

    static void BFS(){
        Queue<Integer> dp = new LinkedList<>();

        depth[1] = 1;
        dp.add(1);

        while(!dp.isEmpty()){
            int now = dp.poll();

            List<Vertex> now_vertex = tree[now];

            for(Vertex v: now_vertex){
                int to = v.to;

                if(depth[to] == 0) {
                    depth[to] = depth[now] + 1;
                    parent[0][to] = now;
                    dp.add(to);
                }
            }
        }
    }

}

class Vertex {
    int from;
    int to;

    public Vertex(int from, int to) {
        this.from = from;
        this.to = to;
    }
}
