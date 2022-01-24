package network;

/*
    2022.01.24
    백준 1922번 "네트워크 연결"
    https://www.acmicpc.net/problem/1922

    그래프 유형
    최소신장트리 유형(크루스칼, 프림 알고리즘 둘 중 하나 사용)
    union부분 로직 주의. 여기를 엄청 헷갈렸음.
 */

import java.util.*;
import java.io.*;

public class Network {

    static int[] graph;
    static List<Vertex> v;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/network/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new int[n];
        for(int i = 0;  i< n; i++) {
            graph[i] = i;
        }
        v = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            v.add(new Vertex(a - 1, b - 1, c));
        }

        //1. 간선 리스트 정렬하기(우선순위큐를 사용해도 좋음)
        Collections.sort(v);

        //2. 간선 순서대로 연결하기
        int sum = 0;
        for(Vertex one : v) {
            if(union(one.a, one.b)) {
                sum += one.degree;
            }
        }

        System.out.println(sum);
    }

    public static boolean union(int a, int b) {

        int Aparent = find(a);
        int Bparent = find(b);

        if(Aparent == Bparent) {
            return false;
        } else{
            graph[Aparent] = Bparent;
            return true;
        }
    }

    public static int find(int a) {
        if(a == graph[a]) {
            return a;
        } else{
            return graph[a] = find(graph[a]);
        }
    }
}

class Vertex implements Comparable<Vertex> {
    int a;
    int b;
    int degree;

    public Vertex(int a , int b, int degree) {
        this.a = a;
        this.b = b;
        this.degree = degree;
    }

    @Override
    public int compareTo(Vertex v) {
        return Integer.compare(this.degree, v.degree);
    }
}
