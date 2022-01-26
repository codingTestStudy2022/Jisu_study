package triangle;

/*
    22.01.25
    백준 1932번 "정수 삼각형"
    https://www.acmicpc.net/problem/1932

    동적 계획법 기초 문제
    입력을 제대로 받을 것.
    문제를 제대로 읽고 그 조건을 잘 맞춰줄 것
    최소의 엣지케이스를 확인해볼 것.(삼각형 크기가 1이라던지)
 */

import java.io.*;
import java.util.*;

public class triangle {

    static int[][] tri;
    static int[][] max;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/triangle/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        tri = new int[n][n];
        max = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j <= i; j++) {
                tri[i][j] = Integer.parseInt(st.nextToken());
           }
        }

        //점화식 = max[i][j] = tri[i][j] + Max(max[i-1][j-1] + max[i-1][j])
        max[0][0] = tri[0][0];
        int answer = max[0][0];
        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(j == 0) {
                    max[i][j] = tri[i][j] + max[i-1][j];
                }
                else if(j == i){
                    max[i][j] = tri[i][j] + max[i-1][j-1];
                }
                else {
                    max[i][j] = tri[i][j] + Integer.max(max[i-1][j-1], max[i-1][j]);
                }

                if(answer < max[i][j]) {
                    answer = max[i][j];
                }
            }
        }

        System.out.println(answer);


    }
}
