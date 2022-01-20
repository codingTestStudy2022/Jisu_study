package gcd;

/*
    2022.01.20
    백준 14476번 "최대공약수 하나 빼기"

    유클리드 호제법 유형 문제
    누적 방식이 O(n^2)을 O(n)으로 줄일 수 있는 방법임.
    사칙연산에도 쓸 수 있고, 대체로 합쳐서 쓸 수 있는것은 다 됨.
 */

import java.io.*;
import java.util.*;

public class Gcd {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/gcd/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] num_s = br.readLine().split(" ");
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(num_s[i]);
        }

        int[] LtoR = new int[n];
        LtoR[0] = num[0];
        for (int i = 1; i < n; i++) {
            LtoR[i] = gcd(LtoR[i-1], num[i]);
        }
        int[] RtoL = new int[n];
        RtoL[n-1] = num[n-1];
        for (int i = n-2; i >= 0; i--) {
            RtoL[i] = gcd(RtoL[i+1], num[i]);
        }
        
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            int gcd = 0;
            if(i == 0) {
                gcd = RtoL[1];
            } else if(i == n - 1) {
                gcd = LtoR[n-2];
            } else {
                gcd = gcd(LtoR[i-1], RtoL[i+1]);
            }

            if(max < gcd && num[i] % gcd != 0) {
                max = gcd;
                maxIndex = num[i];
            }
        }

        if(max != 0) {
            System.out.println(max + " " + maxIndex);
        } else {
            System.out.println(-1);
        }
    }
    
    static int gcd(int a, int b) {
        
        while(b != 0) {
            int r = a % b; 
            a = b;
            b = r;
        }
        
        return a;
    }
}
