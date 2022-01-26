package LCA2;

/*
    22.01.25
    백준 11438번 "LCA2"
    https://www.acmicpc.net/problem/11438

    최소 공통 조상 유형
    미완료
 */

import java.io.*;
import java.util.*;

public class LCA2 {

    static int N;

    static List[] vertex;
    static int[] depth;
    static int[][] parent;
    static int height;
    static int K;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/LCA2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        vertex = new List[N];
        for(int i = 0; i < N; i++) {
            vertex[i] = new ArrayList<Integer>();
        }

        visited = new boolean[N];

        //간선들 받기
        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            vertex[a].add(b);
            vertex[b].add(a);
        }

        K = 0;

        while(Math.pow(2, K) < N) {
            K++;
        }

        parent = new int[K][N];
        depth = new int[N];

        //각 노드의 depth 기록
        dfs();

        //쿼리들 받기
        int query = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < query; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            //a가 더 깊이 있음을 가정한 로직
            if(depth[a] > depth[b]) {
                bw.append(lca(b, a) + "\n");
            }
            else {
                bw.append(lca(a, b) + "\n");
            }
        }

        br.close();

        bw.flush();
        bw.close();
    }

    static void dfs() {

        //재귀 방식으로 하면 최대 10만번 함수를 호출해서 stack overflow가 발생할 수 있음.
        //stack방식으로 해야함.

        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        parent[0][0] = 0;

        while(!stack.isEmpty()) {
            int now = stack.pop();
            depth[now] = depth[parent[0][now]] + 1;
            visited[now] = true;

            List<Integer> connect = vertex[now];

            for (int next : connect) {
                if(!visited[next]) {
                    stack.add(next);
                    parent[0][next] = now;
                }
            }
        }
    }

    static int lca(int a, int b) {
        int answer = 0;

        makeDP();

        //높이 맟주기(비트연산자 사용)
        for(int i = 0; i < K; i++) {
            if(((depth[a] - depth[b]) & (1 << i)) >= 1) {
                a = parent[i][a];
            }
        }

        //

        return answer;
    }

    static void makeDP() {
        for(int k = 1; k < K; k++) {
            for (int v = 0; v < N; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }
}
