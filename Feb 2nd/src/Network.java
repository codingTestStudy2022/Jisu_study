/*
 * 2022.02.10
 * 백준 1922번 "네트워크 연결"
 * https://www.acmicpc.net/problem/1922
 *
 * 최소신장트리 유형
 */

import java.util.*;
import java.io.*;

public class Network {

    //static ArrayList<V> vertex = new ArrayList<>();
    static int[] parent;
    static ArrayList<V> answer_v = new ArrayList<>();
    static ArrayList[] vertex;

    public static void main(String[] args) throws IOException{

        //입력
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        vertex = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            vertex[i] = new ArrayList<V>();
        }

        for(int i = 0; i < m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int price = Integer.parseInt(st.nextToken());

            //vertex.add(new V(a, b, price));

            vertex[a].add(new V(a, b, price));
            vertex[b].add(new V(b, a, price));
        }

        //크루스칼 - 간선 정렬
        //Collections.sort(vertex);

        //크루스칼 알고리즘 수행
        //int answer = kruskal();

        //프림 알고리즘 수행
        int answer = prim(n);

        System.out.println(answer);
    }

//    static int kruskal() {
//        int answer = 0;
//
//        for(int i = 0; i < vertex.size(); i++) {
//            V now = vertex.get(i);
//
//            int parentA = find(now.a);
//            int parentB = find(now.b);
//
//            if(parentA == parentB) continue;
//            else {
//                answer += now.price;
//                union(now.a, now.b);
//
//                answer_v.add(now);
//            }
//        }
//
//        return answer;
//    }

    static int prim(int n) {
        int answer = 0;
        PriorityQueue<V> v_queue = new PriorityQueue<>(new Comparator<V>() {
            @Override
            public int compare(V o1, V o2) {
                return Integer.compare(o1.price, o2.price);
            }
        });
        boolean[] visited = new boolean[n];

        List<V> start = vertex[0];
        for(V v: start){
            v_queue.add(v);
        }
        visited[0] = true;

        while(!v_queue.isEmpty()){
            V min_v = v_queue.poll();

            if(visited[min_v.b]) continue;
            else {
                answer += min_v.price;
                List<V> next_v = vertex[min_v.b];
                for(V v:next_v) {
                    v_queue.add(v);
                }
                visited[min_v.b] = true;
            }
        }

        return answer;

    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        parent[parentB] = parentA;
    }

    static int find(int a){
        if(parent[a] == a) return a;
        else return find(parent[a]);
    }
}

class V implements Comparable<V>{
    int a;
    int b;
    int price;

    public V(int a, int b, int price) {
        this.a = a;
        this.b = b;
        this.price = price;
    }

    public int compareTo(V v) {
        return Integer.compare(this.price, v.price);
    }
}
