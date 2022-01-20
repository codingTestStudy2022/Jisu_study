package candybox;

/*
    2022.01.20
    백준 2243번 "사탕상자"

    처음으로 한방에 맞힘
    물론 가이드가 잡혀있었지만...
 */

import java.util.*;
import java.io.*;

public class Candybox {

    static int S;
    static int[] tree;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/candybox/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = 1;
        while(S < 1000000) {
            S *= 2;
        }
        tree = new int[2*S];

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 1) {
                int level = Integer.parseInt(st.nextToken());
                int[] answer = query(level, 1);
                sb.append(answer[0]);
                sb.append("\n");
            } else {
                int target = Integer.parseInt(st.nextToken());
                int diff = Integer.parseInt(st.nextToken());

                //update
                update(target, diff);
            }
        }

        System.out.println(sb);
        br.close();
    }

    //[찾은 맛 번호, 남은 레벨]
    static int[] query(int level, int node){
        int[] answer = {-1, 0};
        int value = tree[node];

        //노드가 리프인지 확인
        //(내부노드일 경우)
        if(node < S) {
            //1. 해당 노드의 값이 레벨보다 큰지, 작은지 확인
            if(value >= level) {
                //크거나 같을 경우 -> 2. 왼쪽 순회
                int[] check1 = query(level, node*2);
                //왼쪽에서 레벨을 못찾을 경우 -> 3. 오른쪽 순회
                if(check1[0] == -1) {
                    int[] check2 = query(check1[1], node*2+1);
                    answer[0] = check2[0];
                } else {
                    answer[0] = check1[0];
                }
                //리턴 전에 해당 노드를 1 줄이고 리턴
                tree[node] -= 1;

                return answer;
            } else {
                //작을 경우 -> 레벨을 줄이고 리턴
                level -= value;
                answer[1] = level;
                return answer;
            }
        } else {
            //(리프일 경우)
            //1. 해당 노드 값이 레벨보다 큰지, 작은지 확인
            if(value >= level){
                //크거나 같을 경우 -> 레벨을 0, 해당 값을 1 줄이고 리턴
                answer[0] = node-S+1;
                answer[1] = 0;
                tree[node] -= 1;
                return answer;
            } else {
                //작을 경우 -> 레벨을 줄이고 리턴
                level -= value;
                answer[1] = level;
                return answer;
            }
        }
    }

    static void update(int target, int diff) {
        //타겟 노드 변경
        int idx = S + target - 1;
        tree[idx] += diff;

        //부모 노드로 이동해 변경 -> 반복
        while(idx > 1) {
            idx /= 2;
            tree[idx] += diff;
        }
    }
}
