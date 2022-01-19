package sumOfSection;

/*
    2022.01.19
    백준 2042번 "구간 합 구하기"

    인덱스 트리 구현 문제
    범위를 미리 분류해두고, int와 long을 쓰는 부분을 구별할 것.
    특히 input 받을 때 parseInt를 무심코 쓰는 버릇 고치기!
 */

import java.util.*;
import java.io.*;

public class SumOfSection {

    static long[] num;
    static long[] tree;
    static int S;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/sumOfSection/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st1.nextToken());
        int m = Integer.parseInt(st1.nextToken());
        int k = Integer.parseInt(st1.nextToken());

        //S 찾기
        S = 1;
        while(n > S) S *= 2;

        num = new long[n];
        for (int i = 0; i < n; i++) {
            num[i] = Long.parseLong(br.readLine());
        }

        //인덱스 트리 init
        tree = new long[S * 2];
        init();

        for(int i = 0; i < m+k; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            long c = Long.parseLong(st2.nextToken());

            if(a == 1) {
                //update
                long origin = tree[S + b - 1];
                long diff = c - origin;
                update(b, diff);
            } else {
                //구간 합
                System.out.println(query(1, S, 1, b, c));
            }
        }
    }

    static void init() {
        //bottom-up 방식
        //1. 리프노드 순회
        for (int i = 0; i < num.length; i++){
            tree[S + i] = num[i];
        }

        //2. 내부노드 순회
        for (int i = S-1; i > 0; i--) {
            long left = tree[i*2];
            long right = tree[i*2+1];

            tree[i] = left + right;
        }
    }

    static void update(int target, long diff) {
        //bottom-up 방식
        int idx = S + target - 1;

        //1. 리프의 타겟노드를 갱신
        tree[idx] += diff;

        //2. 부모로 이동 후 갱신
        while(idx != 1) {
            idx /= 2;
            tree[idx] += diff;
        }
    }

    static long query(int left, int right, int node, int queryL, long queryR) {
        //top-down 방식(재귀)
        //1. 노드의 자격 확인
        //노드가 범위 밖
        if(left < queryL && right < queryL) {
            //2. 반환한다.
            return 0;
        }
        else if(left > queryR && right > queryR ){
            return 0;
        }
        //노드가 범위 안
        else if(left >= queryL && right <= queryR) {
            //2. 가지고 있는 값을 반환한다.
            return tree[node];
        }
        //노드가 범위에 걸쳐있음
        else {
            //2. 자식 노드에 재귀호출한 값을 반환한다.
            int mid = left + ((right - left) / 2);
            long childL = query(left, mid, node*2, queryL, queryR);
            long childR = query(mid+1, right, node*2+1, queryL, queryR);
            return (childL + childR);
        }
    }
}
