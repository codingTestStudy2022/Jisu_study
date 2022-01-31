package han;

/*
    22.01.31
    백준 1065번 '한수'
    https://www.acmicpc.net/problem/1065

    미해결
    뭐야?
 */

import java.util.*;
import java.io.*;

public class Han {

    public static void main(String[] args) throws IOException {

        //입력
        System.setIn(new FileInputStream("src/han/input.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.close();

        int answer = 0;

        if(n < 99) answer = n;
        else answer = 99;

        for(int i = 100; i <= n; i++) {
            String[] num = Integer.toString(i).split("");

            int gap = Integer.parseInt(num[1]) - Integer.parseInt(num[0]);
            boolean isHan = true;
            for(int j = 2; j < num.length; j++) {
                int fst = Integer.parseInt(num[j-1]);
                int scd = Integer.parseInt(num[j]);

                if(scd - fst != gap) {
                    isHan = false;
                    break;
                }
            }

            if(isHan) answer++;
        }

        System.out.println(answer);

    }

}
