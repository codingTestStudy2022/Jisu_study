package cutTree;

/*
    2022.01.18
    백준 2805번 "나무 자르기"

    값에 따라 int 범위를 초과하는 경우 값이 이상해지는 경우 발생.
    이 부분을 고려해서 int, double을 섞어 쓰기
 */

import java.io.*;
import java.util.*;

public class CutTree {
    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/cutTree/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());
        int[] trees = new int[N];
        for(int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st2.nextToken());
        }

        //정렬
        Arrays.sort(trees);

        int answer = 0;
        int front = 0; int back = trees[trees.length - 1];
        while(true){

            //중앙값 찾기
            int height = front + ((back - front) / 2);

            //가져갈 수 있는 나무 양 구하기
            double sum = 0;
            for(int i = 0; i < trees.length; i++) {
                if(trees[i] - height > 0) {
                    sum += trees[i] - height;
                }
            }

            //딱 맞는 값 없이 더이상 움직일 수 없을 때
            if(height == front){

                while(sum < M) {
                    height++;

                    sum = 0;
                    for(int i = 0; i < trees.length; i++) {
                        if(trees[i] - height > 0) {
                            sum += trees[i] - height;
                        }
                    }
                }
                answer = height;
                break;
            }

            //sum == M
            if(sum == M) {
                answer = height;
                break;
            }
            //sum < M
            else if(sum < M) {
                back = height;
            }
            //sum > M
            else {
                front = height;
            }
        }

        System.out.println(answer);
    }
}
