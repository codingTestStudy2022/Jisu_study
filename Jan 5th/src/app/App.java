package app;

/*
    22.01.25
    백준 7579번 "앱"
    https://www.acmicpc.net/problem/7579

    DP유형
 */

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {

        int[] memory;
        int[] cost;
        int[][] dp;
        int totalCost = 0;
        int answer = 0;

        System.setIn(new FileInputStream("src/app/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        br.close();
        for(int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
            cost[i] = Integer.parseInt(st2.nextToken());
        }

        for(int c : cost) {
            totalCost += c;
        }

        dp = new int[N+1][totalCost+1];

        //dp 누적합 구하기
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j <= totalCost; j++) {
                //기존 값 가져오기
                dp[i][j] = dp[i-1][j];

                //i번째 앱의 cost이상부터 갱신
                if(j - cost[i] >= 0) {
                    dp[i][j] = Integer.max(dp[i][j], dp[i-1][j - cost[i]] + memory[i]);
                }
            }
        }

        //확보 가능한 메모리가 N 이상인 것을 탐색
        for(int i = 0; i <= totalCost; i++) {
            if(dp[N][i] >= M) {
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
