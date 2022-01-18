package sumOfNum;

/*
    2021.01.18
    백준 2003번 "수들의 합 2"
 */

import java.io.*;
import java.util.*;

public class SumOfNum {

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/sumOfNum/input.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] num = new int[N+1];
        for(int i = 0; i < N; i++){
            num[i] = sc.nextInt();
        }

        int low = 0; int high = 0;
        int sum = num[low];
        int count = 0;

        while(high < N) {

            if(low == high) {
                if (sum == M)
                    count++;
                sum += num[++high];
            }
            //sum == M
            else if(sum == M){
                count++;
                sum -= num[low++];
            }
            //sum > M
            else if (sum > M)
                sum -= num[low++];
            //sum < M
            else
                sum += num[++high];
        }

        System.out.println(count);

    }

}
