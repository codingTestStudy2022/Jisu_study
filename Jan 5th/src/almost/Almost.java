package almost;

/*
    22.01.25
    백준 3830번 "교수님은 기다리지 않는다"
    https://www.acmicpc.net/problem/3830

    단일 시작, 단일 도착 다익스트라 응용문제
    미완료
 */

import java.io.*;
import java.util.*;

public class Almost {

    static int N;   // 장소의 수
    static int M;   // 도로의 수
    static int start; //시작점
    static int dst; //도착점

    static ArrayList<Road>[] roads;
    static ArrayList<Integer>[] tracking;
    static boolean[][] isShortest;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/almost/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) {
                break;
            }

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            dst = Integer.parseInt(st.nextToken());

            roads = new ArrayList[N];
            tracking = new ArrayList[N];
            isShortest = new boolean[N][N];
            distance = new int[N];

            for(int i = 0; i < N; i++){
                roads[i] = new ArrayList<>();
                tracking[i] = new ArrayList<>();
            }

            //간선 받기
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());

                roads[from].add(new Road(from, to, length));
            }

            //최단경로 찾기
            findShortest();

            //역트래킹
            backTracking(dst);

            //다시 최단경로 찾기
            findShortest();

            //초기화
            reset();

            if(distance[dst] == Integer.MAX_VALUE) {
                bw.append("-1\n");
            } else {
                bw.append(distance[dst] + "\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();
   }

    static void findShortest() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        //distance 초기화
        distance[start] = 0;
        for(int i = 1; i < N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        int[] start_node = {start, 0};
        pq.add(start_node);

        while(!pq.isEmpty()){
            int[] now = pq.poll();

            if(distance[now[0]] != now[1]) {
                continue;
            }

            List<Road> vertexes = roads[now[0]];
            for(Road v : vertexes) {
                //최단경로에 쓰인 길일 경우
                if(isShortest[v.from][v.to]) {
                    continue;
                }

                if(distance[v.to] > distance[v.from] + v.length) {
                    distance[v.to] = distance[v.from] + v.length;
                    tracking[v.to].clear();
                    tracking[v.to].add(v.from);
                }
                else if(distance[v.to] == distance[v.from] + v.length) {
                    tracking[v.to].add(v.from);
                }
            }
        }
    }

    static void backTracking(int now) {
        //출발점에 도착하면 stop
        if(now == start) {
            return;
        }

        for(int next : tracking[now]) {
            if(isShortest[next][now] == false) {
                isShortest[next][now] = true;
            }
            backTracking(next);
        }
    }

    static void reset() {

        //flag 초기화
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                isShortest[i][j] = false;
            }
        }

        //트레킹 초기화
    }
}

class Road {
    int from;
    int to;
    int length;

    public Road(int from, int to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }
}
