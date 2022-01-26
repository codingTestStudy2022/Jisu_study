package sum5;

/*
    22.01.25
    백준 11660번 "구간합 구하기 5"
    https://www.acmicpc.net/problem/11660

    동적 프로그래밍 기초 문제
 */

import java.io.*;
import java.util.*;

public class Sum5 {

    static int N;
    static int M;

    static int[][] num;
    static int[][] sum;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/sum5/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N + 1][N + 1];
        sum = new int[N + 1][N + 1];

        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //구간합 만들기
        setSum();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());

            bw.append(getSum(a1-1, a2-1, b1, b2) + "\n");
        }

        br.close();

        bw.flush();
        bw.close();
    }

    static void setSum() {
        for(int i = 1; i < N + 1; i++) {
            for(int j = 1; j < N + 1; j++) {
                sum[i][j] = num[i][j] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
            }
        }
    }

    static int getSum(int a1, int a2, int b1, int b2) {
        int answer = sum[b1][b2] - sum[a1][b2] - sum[b1][a2] + sum[a1][a2];

        return answer;
    }

}
