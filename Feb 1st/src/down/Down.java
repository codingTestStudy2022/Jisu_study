package down;

/*
    2022.02.03
    백준 2096번 "내려가기"
    https://www.acmicpc.net/problem/2096

    동적 프로그래밍 유형
    256MB 기준 생성 가능한 int 개수 : 67,108,864개
 */

import java.util.*;
import java.io.*;

public class Down {

    public static void main(String[] args) throws IOException {

        int max = 0;
        int min = Integer.MAX_VALUE;
        int[][] maxDp, minDp;

        //입력
        System.setIn(new FileInputStream("src/down/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        maxDp = new int[2][3];
        minDp = new int[2][3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 3; i++) {
            int num = Integer.parseInt(st.nextToken());
            maxDp[0][i] = num;
            minDp[0][i] = num;
        }

        for(int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(j == 0) {
                    maxDp[i%2][j] = Math.max((maxDp[(i+1)%2][j] + num), (maxDp[(i+1)%2][j+1] + num));
                    minDp[i%2][j] = Math.min((minDp[(i+1)%2][j] + num), (minDp[(i+1)%2][j+1] + num));
                } else if(j == 2) {
                    maxDp[i%2][j] = Math.max((maxDp[(i+1)%2][j-1] + num), (maxDp[(i+1)%2][j] + num));
                    minDp[i%2][j] = Math.min((minDp[(i+1)%2][j-1] + num), (minDp[(i+1)%2][j] + num));
                } else {
                    maxDp[i%2][j] = Math.max((maxDp[(i+1)%2][j-1] + num), Math.max(maxDp[(i+1)%2][j] + num, maxDp[(i+1)%2][j+1] + num));
                    minDp[i%2][j] = Math.min((minDp[(i+1)%2][j-1] + num), Math.min(minDp[(i+1)%2][j] + num, minDp[(i+1)%2][j+1] + num));
                }
            }
        }

        for(int i = 0; i < 3; i++) {
            max = Math.max(max, maxDp[(N-1)%2][i]);
            min = Math.min(min, minDp[(N-1)%2][i]);
        }

        System.out.println(max + " " + min);
    }
}
