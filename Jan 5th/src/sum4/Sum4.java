package sum4;

/*
    22.01.25
    백준 11659번 "구간 합 구하기 4"
    https://www.acmicpc.net/problem/11659

    동적 계획법 기초 유형(누적합 활용)
    O(M + N) <- N은 처음 누적합을 구하는 과정의 시간
 */

import java.io.*;
import java.util.*;

public class Sum4 {
    public static void main(String[] args) throws IOException {

        int[] num;
        int[] sum; //구간합 배열

        System.setIn(new FileInputStream("src/sum4/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        num = new int[N];
        sum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());

            //구간합 구하기
            if(i == 0) {
                sum[i+1] = num[i];
            } else{
                sum[i+1] = sum[i] + num[i];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < M; i++) {
           st = new StringTokenizer(br.readLine());

           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());

           bw.append((sum[b] - sum[a-1]) + "\n");
        }

        br.close();

        bw.flush();
        bw.close();
    }
}
