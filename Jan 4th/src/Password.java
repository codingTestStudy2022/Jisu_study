/*
    2022.01.17
 */

import java.io.*;
import java.util.*;

public class Password {
    static int[] condition;
    static String[] alpha;
    static StringBuilder answer = new StringBuilder("");

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        condition = new int[]{Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])};
        alpha = br.readLine().split(" ");

        //정렬
        Arrays.sort(alpha);

        //1. 체크인
        String[] text = new String[condition[0]];
        for(int i = 0; i < alpha.length; i++) {
            makePasswd(i, text, 0);
        }

        System.out.println(answer);
    }

    static void makePasswd(int idx, String[] text, int text_idx) {
        text[text_idx] = alpha[idx];

        //2. 목적지인지 확인
        if(text_idx == (condition[0] - 1)) {
            String[] vowel = {"a", "e", "o", "i", "u"};

            int count = 0;
            for(String vo : vowel) {
                if(Arrays.asList(text).contains(vo)) count++;
            }

            if(count > 0) {
                if(text.length - count > 1) {
                    for(String ch : text) {
                        answer.append(ch);
                    }
                    answer.append("\n");
                }
            }


            return;
        }

        //3. 연결된 곳 순회
        for(int i = idx + 1; i < alpha.length; i++) {
            //4. 간다
            makePasswd(i, text, text_idx + 1);
        }

        //체크아웃
        return;
    }
}
