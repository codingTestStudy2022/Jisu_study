package timeMachine;

/*
    2022.01.24
    백준 11657번 "타임머신"
    https://www.acmicpc.net/problem/11657

    그래프 유형
    벨만-포드 알고리즘 유형
    벨만-포드 알고리즘에 대한 정확한 이해가 필요했음.
 */

import java.util.*;
import java.io.*;

public class TimeMachine {

    static Vertex[] v;
    static List[] n_list;
    static long [] distance;
    static long max = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/timeMachine/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        v = new Vertex[m];
        n_list = new List[n];
        distance = new long[n];

        for (int i = 0; i < n; i++) {
            n_list[i] = new ArrayList<Integer>();
            if(i != 0) {
                distance[i] = max;
            }
        }

        //모든 간선 저장
        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken());
            int b = Integer.parseInt(st2.nextToken());
            int c = Integer.parseInt(st2.nextToken());

            v[i] = new Vertex(a, b, c);
            n_list[a - 1].add(i);
        }

        br.close();

        int idx = 0;
        for(int i = 0;  i< n-1; i++) {
            //해당 정점에 연결된 간선들 순회
           iter(false);
        }


        //재 순회
        boolean timeLoop = false;
        timeLoop = iter(true);

        if(timeLoop) {
            System.out.println("-1");
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            for (int i = 1; i < distance.length; i++) {
                if(distance[i] == max){
                    bw.append("-1\n");
                } else{
                    bw.append(distance[i] + "\n");
                }
            }

            bw.flush();
            bw.close();

        }

    }

    public static boolean iter(boolean again) {

        //모든 정점 돌기
        for(int i = 0; i < distance.length; i++) {
            List<Integer> one = n_list[i];
            long value = distance[i];

            if(value == max) continue;

            if(one == null) continue;

            for(int idx : one) {
                int to = v[idx].to;
                int degree = v[idx].degree;

                if(distance[to] > value + degree) {
                    if(again) {
                        return true;
                    }
                    distance[to] = value + degree;
                }
            }
        }
        return false;
    }
}

class Vertex {
    int from;
    int to;
    int degree;

    public Vertex(int from, int to, int degree) {
        this.from = from - 1;
        this.to = to - 1;
        this.degree = degree;
    }
}
