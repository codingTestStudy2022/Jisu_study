package Candy;

/*
    2022.01.20
    백준 3955번 "캔디 분배"
    https://www.acmicpc.net/problem/3955

    확장된 유클리드 호제법 유형
    미완료
 */

import java.io.*;
import java.util.*;

public class Candy {
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/Candy/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder answer = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            //sa+tb = 1의 s t 구하기
            GCD str = ETgcd(a, b);

            //1. 해 검증
            if(str.r != 0) {
                answer.append("IMPOSSIBLE");
                answer.append("\n");
                continue;
            }

            //2. 초기 해 구하기
            long x0 = str.s;
            long y0 = str.t;

            //3. 일반 해 구하기

            //4. k의 범위
        }

        //k(-x) + cy = 1
    }

    static GCD ETgcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long tmp, q;
        while(r1 != 0) {
            //gcd
            q = r0 / r1;

            tmp = r0 % r1;
            r0 = r1;
            r1 = tmp;

            tmp = s0 - (s1 * q);
            s0 = s1;
            s1 = tmp;

            tmp = t0 - (t1 * q);
            t0 = t1;
            t1 = tmp;
        }

        GCD result = new GCD(s0, t0, r0);
        return result;
    }

    static long gcd(long a, long b) {
        long r = 0;
        while(b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return a;
    }

}

class GCD {
    long s;
    long t;
    long r;

    public GCD(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
