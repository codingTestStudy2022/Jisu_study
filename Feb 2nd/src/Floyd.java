/*
 * 2022.02.10
 * 백준 11404번 "플로이드"
 *
 * 플로이드-워셜 유형
 */

import java.util.*;
import java.io.*;

public class Floyd {

    static int[][] table;

    public static void main(String[] args) throws IOException {
        //입력
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        table = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                table[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;
            int price = Integer.parseInt(st.nextToken());

            table[start][end] = Math.min(table[start][end], price);
        }

        br.close();

        //플로이드-워셜 알고리즘 수행
        for(int mid = 0; mid < n; mid++) {
            for(int start = 0; start < n; start++){
                for(int end = 0; end < n; end++){
                    if(start == end) continue;
                    if(table[start][mid] != Integer.MAX_VALUE && table[mid][end] != Integer.MAX_VALUE){
                        table[start][end] = Math.min(table[start][end], (table[start][mid] + table[mid][end]));
                    }
                }
            }
        }

        //출력
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(table[i][j] == Integer.MAX_VALUE) {
                    bw.append(0 + " ");
                } else {
                    bw.append(table[i][j] + " ");
                }
            }
            bw.append("\n");
        }

        bw.flush();
        bw.close();
    }
}
