package lineUP;

/*
    2022.01.24
    백준 2252번 "줄 세우기"
    https://www.acmicpc.net/problem/2252

    그래프 유형
    위상 정렬로 풀어야함. 위상 정렬에 대해 숙지할 것.
 */

import java.util.*;
import java.io.*;

public class LineUp {

    static int[] v;
    static List[] line;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("src/lineUP/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        v = new int[n];
        line = new List[n];
        for(int i = 0; i < n; i++) {
            line[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st2.nextToken()) - 1;
            int b = Integer.parseInt(st2.nextToken()) - 1;

            v[a]++;
            line[b].add(a);
        }

        br.close();

        Stack<Integer> answer = sort();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(answer.pop());
            sb.append(" ");
        }
        System.out.println(sb);
    }

    static Stack<Integer> sort() {
        Queue<Integer> queue = new LinkedList<>();
        Stack<Integer> answer = new Stack<>();

        for(int i = 0; i < v.length; i++) {
            if(v[i] == 0) {
                queue.add(i);
            }
        }

        while(!queue.isEmpty()) {
            int pop = queue.poll();
            answer.add(pop + 1);

            List<Integer> backofpop = line[pop];
            for(int num : backofpop) {
                v[num]--;
                if(v[num] == 0) {
                    queue.add(num);
                }
            }
        }

        return answer;
    }
}
