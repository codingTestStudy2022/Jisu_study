package factory;

/*
    22.01.25
    백준 7578번 "공장"
    https://www.acmicpc.net/problem/7578

    인덱스 트리 응용 유형 : Inverse Counting
 */

import java.io.*;
import java.util.*;

public class Factory {

    static int[] a;
    static HashMap<Integer, Integer> b = new HashMap<>();
    static long[] tree;
    static int S = 1;

    public static void main(String[] args) throws IOException{

        long answer = 0;

        System.setIn(new FileInputStream("src/factory/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        a = new int[N];
        while(N > S){
            S *= 2;
        }
        tree = new long[2*S];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){

            a[i] = Integer.parseInt(st1.nextToken());
            b.put(Integer.parseInt(st2.nextToken()), i);
        }

        //케이블 쌍 구하기
        for(int i = 0; i < N; i++){
            int idx = b.get(a[i]);

            answer += query(1, S, 1, idx+2, S);
            update(idx, 1);
        }

        System.out.println(answer);
    }
    static void update(int target, long diff){
        int idx = S + target;
        while(idx > 1) {
            tree[idx] += diff;
            idx /= 2;
        }
        tree[1] += diff;
    }

    static long query(int left, int right, int node, int queryleft, int queryright){
        if(right < queryleft || left > queryright) {
            return 0;
        }
        else if(left == right) {
            return tree[node];
        }
        else if(left >= queryleft && right <= queryright) {
            return tree[node];
        }
        else {
            long answer = 0;
            answer += query(left, left + (right-left)/2, node*2, queryleft, queryright);
            answer += query(left + (right-left)/2+1, right, node*2 + 1, queryleft, queryright);

            return answer;
        }
    }
}
