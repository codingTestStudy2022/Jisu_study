package road;

/*
    22.01.25
    백준 3176번 "도로 네트워크"
    https://www.acmicpc.net/problem/3176

    LCA(최소 조상 노드) 응용 유형
 */

import java.io.*;
import java.util.*;

public class Road {

    static int N;
    static ArrayList[] roads;
    static int maxRoad, minRoad;

    static int[] depth;
    static int[][] parent, max, min;
    static int K;

    public static void main(String[] args) throws IOException {

        //입력
        System.setIn(new FileInputStream("src/road/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        roads = new ArrayList[N];

        for(int i = 0; i<N; i++) {
            roads[i] = new ArrayList<Info>();
        }

        for(int i = 0; i < N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int dst = Integer.parseInt(st.nextToken());

            roads[a].add(new Info(a, b, dst));
            roads[b].add(new Info(b, a, dst));
        }

        K = 0;
        while(N > Math.pow(2, K)) {
            K += 1;
        }

        depth = new int[N];
        parent = new int[K][N];
        max = new int[K][N];
        min = new int[K][N];

        //트리 만들기
        bfs(0);

        //테이블 완성하기
        setTable();

        int Q = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            maxRoad = 0;
            minRoad = 1000001;

            //높이 맞추기
            if(depth[a] > depth[b]) {
                a = setHeight(a, b);
            } else{
                b = setHeight(b, a);
            }

            //서로 같은지 검사
            if(a == b) {
                bw.append(minRoad + " " + maxRoad + "\n");
                continue;
            }

            //이진탐색으로 최소 부모 찾기
            binarySearch(a, b);

            bw.append(minRoad + " " + maxRoad + "\n");
        }

        br.close();

        bw.flush();
        bw.close();
    }

    static void bfs(int start) {
        Queue<Integer> stack = new LinkedList<>();
        depth[start] = 1;

        stack.add(start);

        while(!stack.isEmpty()) {
            int now = stack.poll();

            List<Info> list = roads[now];
            for(Info i : list) {
                int to = i.to;
                int dst = i.dst;

                if(depth[to] == 0) {
                    depth[to] = depth[now] + 1;
                    parent[0][to] = now;
                    max[0][to] = dst;
                    min[0][to] = dst;

                    stack.add(to);
                }
            }
        }


    }

    static void setTable() {
        for(int i = 1; i < K; i++) {
            for(int j = 0; j < N; j++) {
                int myP = parent[i-1][j];
                parent[i][j] = parent[i-1][myP];
                max[i][j] = Integer.max(max[i-1][j], max[i-1][myP]);
                min[i][j] = Integer.min(min[i-1][j], min[i-1][myP]);
            }
        }
    }

    static int setHeight(int a, int b) {
        int gap = depth[a] - depth[b];

        for(int i = 0; i < K; i++) {
            if((gap & (1<<i)) >= 1) {
                maxRoad = Integer.max(maxRoad, max[i][a]);
                minRoad = Integer.min(minRoad, min[i][a]);
                a = parent[i][a];
            }
        }

        return a;
    }

    static void binarySearch(int a, int b) {
        //공통 조상이 아닐때까지 부모를 타고 올라가기
        //최종적으로는 최소공통조상 바로 밑까지 올라감.
        for (int i = K-1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                maxRoad = Integer.max(maxRoad, Integer.max(max[i][a], max[i][b]));
                minRoad = Integer.min(minRoad, Integer.min(min[i][a], min[i][b]));
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        maxRoad = Integer.max(maxRoad, Integer.max(max[0][a], max[0][b]));
        minRoad = Integer.min(minRoad, Integer.min(min[0][a], min[0][b]));
    }
}

class Info {
    int from;
    int to;
    int dst;

    public Info(int from, int to, int dst) {
        this.from = from;
        this.to = to;
        this.dst = dst;
    }
}
