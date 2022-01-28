package LCS2;

/*
    22.01.25
    백준 9252번 "LCS2"
    https://www.acmicpc.net/problem/9252

    최장 공통 부분 수열 유형
 */

import java.io.*;
import java.util.*;

public class LCS2 {

    static String[] a, b;
    static int[][] count;
    static int[][] direction;   //1 : 왼쪽, 2: 위쪽, 3: 대각선

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/LCS2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine().split("");
        b = br.readLine().split("");

        count = new int[a.length+1][b.length+1];
        direction = new int[a.length+1][b.length+1];

        //테이블 만들기
        setTable();

        //역추적
        String answer = backTracking();
        System.out.println(answer.length());
        System.out.println(answer);
    }

    static void setTable() {
        for(int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                if(a[i-1].equals(b[j-1])){
                    count[i][j] = Math.max(count[i-1][j-1]+1, Math.max(count[i-1][j], count[i-1][j]));
                    if(count[i][j] == count[i-1][j-1]+1) {
                        direction[i][j] = 3;
                    } else if(count[i][j] == count[i-1][j]) {
                        direction[i][j] = 2;
                    } else{
                        direction[i][j] = 1;
                    }
                } else{
                    if(count[i-1][j] > count[i][j-1]) {
                        count[i][j] = count[i-1][j];
                        direction[i][j] = 2;
                    }else{
                        count[i][j] = count[i][j-1];
                        direction[i][j] = 1;
                    }
                }
            }
        }
    }

    static String backTracking() {
        int x = a.length;
        int y = b.length;
        StringBuilder sb = new StringBuilder();

        while(x != 0 && y != 0) {
            if(direction[x][y] == 1){
                y -= 1;
            }
            else if(direction[x][y] == 2) {
                x -= 1;
            } else {
                sb.append(a[x-1]);
                x -= 1;
                y -= 1;
            }
        }

        return sb.reverse().toString();
    }
}
