package SumOfPart;

/*
    2022.02.03
    백준 1806번 "부분합"
    https://www.acmicpc.net/problem/1806

    투포인트 유형
 */

import java.util.*;
import java.io.*;

public class SumOfPart {


    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/SumOfPart/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int low = 0; int high = 0;
        long sum = num[0];
        int answer = 100001;
        while(high < N) {
            //S보다 작은 경우
            if(sum < S) {
                //끝에 도달했는데도 작은 경우
                if(high == N-1) {
                    break;
                }
                high++;
                sum += num[high];
            }
            //S랑 같은 경우
            else if(sum == S){
                answer = Math.min(answer, (high-low)+1);
                if(high == N-1) {
                    break;
                }
                high++;
                sum += num[high];
            }
            //S보다 큰 경우
            else{
                answer = Math.min(answer, (high-low)+1);
                sum -= num[low];
                low++;
            }
        }

        if(answer == 100001){
            System.out.println(0);
        } else{
            System.out.println(answer);
        }
    }
}
