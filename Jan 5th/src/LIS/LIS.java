package LIS;

/*
    22.01.28
    백준 14003번 "가장 긴 증가하는 부분 수열 5"
    https://www.acmicpc.net/problem/14003

    최장 증가 수열(LIS) 유형
 */

import java.io.*;
import java.util.*;

public class LIS {

    static int[] num;
    static int[] indexOrder;
    static int[] dp;


    public static void main(String[] args) throws IOException {

        //입력받기
        System.setIn(new FileInputStream("src/LIS/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        num = new int[N];
        indexOrder = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        //dp 만들기
        indexOrder[0] = 0;
        dp[0] = num[0];

        int idx = 0;
        for (int i = 1; i < N; i++) {
            if(dp[idx] > num[i]) {
                int mid = binarySearch(idx, num[i]);
                dp[mid] = num[i];
                indexOrder[i] = mid;
            } else if (dp[idx] < num[i]) {
                idx += 1;
                dp[idx] = num[i];
                indexOrder[i] = idx;
            } else {
                indexOrder[i] = idx;
            }
        }

        //길이 저장
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int answer = idx + 1;

        //LIS의 길이에서 내려오면서 실제 LIS 길이를 찾는다.
        for(int i = N-1; i >= 0; i--) {
            if(idx == indexOrder[i]) {
                dp[idx] = num[i];
                idx--;
            }
        }

        //완성된 LIS 저장
        bw.append(answer + "\n");
        for(int i = 0; i< answer; i++){
            bw.append(dp[i] + " ");
        }

        bw.flush();
        bw.close();

    }

    static int binarySearch(int idx, int number) {
        int start = 0;
        int end = idx;
        while(start <= end) {

            int mid = (start + end) / 2;

            if(dp[mid] < number) {
                start = mid + 1;
            } else if(dp[mid] > number){
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return start;
    }

}

