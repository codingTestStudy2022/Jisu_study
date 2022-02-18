/*
 * 2022.02.18
 * 백준 1102번 "발전소"
 * https://www.acmicpc.net/problem/1102
 *
 * 동적프로그래밍 유형
 * 외판원 순회 응용
 *
 * dp를 정의하는 것이 어려웠음.
 * 비트마스킹을 활용할 때 헷갈리는 부분 주의
 */

import java.util.*;
import java.io.*;

public class Factory {

    static int n;
    static int[][] restart;
    static boolean[] turnOn;
    static int min;

    static int[] dp;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        //입력
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        restart = new int[n][n];
        dp = new int[(int)(Math.pow(2, n))];
        Arrays.fill(dp, Integer.MAX_VALUE);

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                restart[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        turnOn = new boolean[n];

        String yn = br.readLine();
        int running = 0;
        int runningcount = 0;
        for(int i = 0; i<n; i++){
            if(yn.charAt(i) == 'Y') {
                turnOn[i] = true;
                running += (1<<i);
                runningcount++;
            } else {
                turnOn[i] = false;
            }
        }

        min = Integer.parseInt(br.readLine());
        br.close();

        if(min <= runningcount) {
            System.out.println(0);
            return;
        }

        count(running, runningcount, 0);

        if(answer == Integer.MAX_VALUE) System.out.println("-1");
        else System.out.println(answer);
    }

    static void count(int running, int runningCount, int totalDegree) {
        //p개의 발전소가 켜져있을 경우
        if(runningCount == min) {
            answer = Math.min(answer, totalDegree);
            return;
        }

        for(int i = 0; i < n; i++) {
            //이용하려고 하는 발전소가 켜져있지 않은 경우
            if(((1<<i) & running) < 1) continue;

            for(int j = 0; j < n; j++) {

                //키려고 하는 발전소가 이미 켜져있는 경우
                int nextRunning = running | (1<<j);
                if(running == nextRunning) continue;

                int nextTotal = totalDegree + restart[i][j];
                if(dp[nextRunning]<= nextTotal) continue;

                dp[nextRunning] = nextTotal;
                count(nextRunning, runningCount+1, nextTotal);
            }
        }
    }
}
