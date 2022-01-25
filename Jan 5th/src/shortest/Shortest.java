package shortest;

/*
    22.01.25
    백준 1753번 "최단경로"
    https://www.acmicpc.net/problem/1753

    다익스트라 알고리즘 유형
 */

import java.io.*;
import java.util.*;

public class Shortest {

    static int[] distance;
    static List[] v_list;
    static PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
        public int compare(int[] v1, int[] v2) {
            return Integer.compare(v1[1], v2[1]);
        }
    });
    static int max = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/shortest/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        distance = new int[V];
        v_list = new List[V];

        for(int i = 0; i < V; i++) {
            v_list[i] = new ArrayList<Vertex>();
        }

        int K = Integer.parseInt(br.readLine()) - 1;

        //간선 저장
        for(int i = 0; i < E; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st2.nextToken()) - 1;
            int v = Integer.parseInt(st2.nextToken()) - 1;
            int w = Integer.parseInt(st2.nextToken());

            v_list[u].add(new Vertex(u, v, w));
        }

        br.close();

        //시작점 외 초기화
        for(int i = 0; i<V; i++){
            if(i != K) {
                distance[i] = max;
            }
        }

        //경로 돌리기
        int[] start = {K, 0};
        pq.add(start);
        while(!pq.isEmpty()) {
            int[] node = pq.poll();

            //값이 실제 배열의 값보다 크다면 넘어감
            if(distance[node[0]] < node[1]) {
                continue;
            }

            List<Vertex> one = v_list[node[0]];
            //연결된 간선 순회
            for(Vertex v : one) {
                if(distance[v.to] > distance[v.from] + v.degree) {
                    distance[v.to] = distance[v.from] + v.degree;
                    int[] add = {v.to, distance[v.to]};
                    pq.add(add);
                }
            }
        }

        //출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < V; i++) {
            if(i == K) {
                bw.append("0\n");
            }
            else if(distance[i] == max) {
                bw.append("INF\n");
            } else {
                bw.append(distance[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }
}

class Vertex {
    int from;
    int to;
    int degree;

    public Vertex(int from, int to, int degree) {
        this.from = from;
        this.to = to;
        this.degree = degree;
    }
}
