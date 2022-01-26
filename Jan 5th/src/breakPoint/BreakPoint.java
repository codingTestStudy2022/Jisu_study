package breakPoint;

/*
    22.01.25
    백준 11266번 "단절점"
    https://www.acmicpc.net/problem/11266

    단절점 알고리즘 유형
    미완료
 */

import java.io.*;
import java.util.*;

public class BreakPoint {

    static int V, E;

    static List[] vertex;
    static int[] order;
    static boolean[] visited;
    static int low;
    static int Order;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/breakPoint/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        vertex = new List[V];
        order = new int[V];
        visited = new boolean[V];

        for(int i = 0; i < V; i++) {
            vertex[i] = new ArrayList<int[]>();
        }

        //간선 받기
        for (int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());


            int a = Integer.parseInt(st2.nextToken()) - 1;
            int b = Integer.parseInt(st2.nextToken()) - 1;
            int[] v1 = {a, b};
            int[] v2 = {b, a};

            vertex[a].add(v1);
            vertex[b].add(v2);
        }

        br.close();

        //각 정점이 단절점인지 확인
        int answer = 0;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < V; i++) {

        }

        System.out.println(answer);
        bw.flush();
        bw.close();
    }

    static void dfs(int now, int breakP) {

    }
}
