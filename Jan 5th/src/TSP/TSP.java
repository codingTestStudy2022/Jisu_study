package TSP;

/*
    22.01.25
    백준 2098번 "외판원 순회"
    https://www.acmicpc.net/problem/2098

    DP유형 : Traveling Salesman Problem(TSP)
    굉장히 유명한 NP-Hard 문제

    dfs + DP 메모라이제이션 + 비트마스크를 사용한 풀이법

    메모라이제이션과 for문 안의 continue는 생각보다 시간 성능에 많은 영향을 미친다.
 */

import java.io.*;
import java.util.*;

public class TSP {

    static int city;
    static int[][] road;
    static int[][] dp;
    static int visitAll;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/TSP/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        city = Integer.parseInt(br.readLine());
        road = new int[city][city];
        visitAll = (1<<city) - 1;
        dp = new int[city][visitAll+1];

        for (int i = 0; i < city; i++) {
            for (int j = 0; j < visitAll+1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < city; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < city; j++){
                road[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1] = 0;
        findShortest(0, 1);

        System.out.println(answer);
    }

    static void findShortest(int now, int visited) {
        //모든 곳을 방문했다면 출발점으로 돌아가기
        //단, 돌아가는 길이 있어야함
        if(visited == visitAll && road[now][0] != 0) {
            answer = Integer.min(answer, dp[now][visited] + road[now][0]);
        }

        //모든 노드를 순회화며 갈 수 있는 곳 탐색하기
        for(int i = 0; i < city; i++) {
            int next = (1<<i); //다음 방문할 곳
            int nextVisited = visited | next;

            //--------이 부분이 시간 성능에 큰 영향을 미침
            if(nextVisited == visited) continue;

            if(road[now][i] == 0) continue;
            //--------

            if(dp[i][nextVisited] > dp[now][visited] + road[now][i]) {
                dp[i][nextVisited] = dp[now][visited] + road[now][i];
                findShortest(i, nextVisited);
            }
        }
    }

}

