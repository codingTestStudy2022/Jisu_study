package dictionary;

/*
    2022.01.20
    백준 1256번 "사전"
    https://www.acmicpc.net/problem/1256

    파스칼의 삼각형 유형
    조합문제에 굉장히 약함. 많이 풀어볼 것.
 */

import java.io.*;
import java.util.*;

public class Dictionary {

    static int[][] tri;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/dictionary/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        br.close();

        //파스칼의 삼각형 만들기
        makeTri(a, z);

        if(tri[a+z][a] < k) {
            System.out.print("-1");
        } else {
            //첫번째 글자부터 k가 a일지 z일지 비교하고 넘어가기
            StringBuilder sb = new StringBuilder();
            int looplen = a + z;
            int len = a + z;
            for(int i = 0; i < looplen; i++){
                //문자열 개수가 k보다 모자랄 경우
                if(a == 0 && z == 0) {
                    sb.setLength(0);
                    sb.append("-1");
                    break;
                }

                //a만 남은 경우
                if(z == 0) {
                    sb.append("a");
                    a -= 1;
                }
                //z만 남은 경우
                else if (a == 0) {
                    sb.append("z");
                    z -= 1;
                }
                else {
                    int aSize;
                    if(a == 1) {
                        aSize = 1;
                    } else {
                        aSize = tri[len - 1][a - 1];
                    }

                    if(aSize < k) {
                        k -= aSize;
                        z -= 1;
                        sb.append("z");
                    } else {
                        a -= 1;
                        sb.append("a");
                    }
                }
                len -= 1;
            }

            System.out.println(sb);
        }


    }

    static void makeTri(int n, int m) {
        int len = n + m;
        tri = new int[len+1][len+1];

        //대각선 초기화
        for (int i = 0; i < tri.length; i++) {
            tri[i][0] = 1;
            tri[i][i] = 1;
        }

        //나머지 숫자 계산해서 넣기
        for (int i = 2; i < tri.length; i++) {
            for (int j = 1; j < tri.length; j++) {
                //삼각형 끝에 도달하면 다름 라인으로
                if(tri[i][j] == 1) {
                    break;
                }
                else {
                    if(tri[i-1][j-1] + tri[i-1][j] > 1000000000) {
                        tri[i][j] = 1000000000;
                    } else {
                        tri[i][j] = tri[i - 1][j - 1] + tri[i - 1][j];
                    }
                }
            }
        }
    }
}
