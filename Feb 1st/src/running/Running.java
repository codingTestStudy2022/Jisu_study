package running;

/*
    2022.02.03
    백준 2517번 "달리기"
    https://www.acmicpc.net/problem/2517

    인덱스 트리 유형
 */

import java.util.*;
import java.io.*;

public class Running {

    static int[] answer;
    static PriorityQueue<int[]> level = new PriorityQueue<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return Integer.compare(o1[0], o2[0]);
        }
    });
    static int[] tree;
    static int S;

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/running/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        answer = new int[N+1];

        S = 1;
        while(S < N) S *= 2;
        tree = new int[2*S];

        for(int i = 1; i <= N; i++) {
            int[] item = {Integer.parseInt(br.readLine()), i};
            level.add(item);
        }

        br.close();

        while(!level.isEmpty()){
            int[] item = level.poll();
            int pass = query(1, S, 1, 1, item[1]-1);    //이게 N이면 안됨. 첫번째 노드가 1 ~ N을 포괄하며, 나중에 좌우로 나뉠때 문제 생김
            answer[item[1]] = item[1]-pass;
            update(item[1], 1);
        }

        for(int i = 1; i <= N; i++) {
            bw.write(answer[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    static void update(int target, int diff) {
        int idx = S+target-1;
        while(idx > 1){
            tree[idx] += diff;
            idx /= 2;
        }

        tree[1] += diff;
    }

    static int query(int left, int right, int node, int queryL, int queryR) {
        int answer = 0;

        //완전히 범위 밖
        if(left > queryR || right < queryL) {
            return 0;
        }
        //리프노드
        else if(left == right) {
            return tree[node];
        }
        //완전히 범위 안
        else if(left >= queryL && right <= queryR){
            return tree[node];
        }
        //범위에 걸침
        else{
            answer += query(left, (left+right)/2, node*2, queryL, queryR);
            answer += query((left+right)/2 + 1, right, node*2+1, queryL, queryR);
        }

        return answer;
    }
}
