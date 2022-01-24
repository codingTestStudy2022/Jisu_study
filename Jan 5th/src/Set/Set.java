package Set;

/*
    2022.01.24
    백준 1717번 "집합의 표현"
    https://www.acmicpc.net/problem/1717

    그래프 유형
 */

import java.util.*;
import java.io.*;

public class Set {

    static int[] graph;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/Set/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new int[n + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int c = Integer.parseInt(st2.nextToken());

            if(a == 0) {
                union(b, c);
            } else {
                if (find(b) == find(c)) {
                    sb.append("YES");
                    sb.append("\n");
                } else {
                    sb.append("NO");
                    sb.append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        int aGroup = find(a);
        int bGroup = find(b);
        graph[bGroup] = aGroup;
    }

    static int find(int a) {
        if(a == graph[a]) {
            return a;
        } else {
            int aGroup = graph[a];
            return find(aGroup);
        }
    }


}
