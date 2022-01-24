package bracket;

/*
    2022.01.23
    쏘카 코딩테스트

 */

import java.util.*;
import java.io.*;

public class Bracket {

    static Stack<Character> open = new Stack<Character>();
    static Stack<Integer> openIdx = new Stack<Integer>();
    static char wrong = 0; static int idx = -1; static char lost;

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/bracket/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        br.close();

        int answer = solution(s);

        System.out.println(answer);
    }

    public static int solution(String s) {
        int answer = 0;

        int small = 0; int middle = 0; int big = 0;

        //잘못된 부분 찾기
        int finish = 0; //닫힘 괄호가 빠진 경우만 사용
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == '(' || ch == '{' || ch == '[') {
                open.push(ch);
                openIdx.push(i);
            } else {
                //열림이 빠진 경우
                if(open.isEmpty()) {
                    wrong = ch;
                    idx = i;
                    break;
                }

                char check = open.peek();

                if(check == '(' && ch == ')'){
                    open.pop();
                    openIdx.pop();
                } else if(check == '{' && ch == '}') {
                    open.pop();
                    openIdx.pop();
                } else if(check == '[' && ch == ']') {
                    open.pop();
                    openIdx.pop();
                } else {
                    //char opentmp = open.pop();
                    //열림이 빠진 경우
                    if(open.peek() == findOther(s.charAt(i+1)))  {
                        wrong = ch;
                        idx = i;
                    }
                    //닫힘이 빠진 경우
                    else {
                        wrong = open.pop();
                        idx = openIdx.pop();
                        finish = i;
                    }

                    open.clear();
                    openIdx.clear();
                    break;
                }
            }
        }

        //닫힘이 빠진 경우
        if(openIdx.size() != 0) {
            idx = openIdx.pop();
            wrong = open.pop();
            finish = s.length();
        }

        lost = findOther(wrong);

        //1. 빠진 괄호가 열린 괄호인지 아닌지 확인
        //열린 괄호가 빠진 경우
        if(lost == '(' || lost == '[' || lost == '{') {
            //스타트 지점 찾기
            for (int i = 0; i < idx; i++) {
                char ch = s.charAt(i);

                if(ch == '(' || ch == '{' || ch == '[') {
                    open.push(ch);
                    openIdx.push(i);
                } else {
                    open.pop();
                    openIdx.pop();
                }
            }

            int start = openIdx.isEmpty()? 0 : openIdx.pop() + 1;
            open.clear();
            openIdx.clear();

            //수 세기
            answer = count(start, idx, s);
        }
        //닫힌 괄호가 빠진 경우
        else {
            //수 세기
            answer = count(idx + 1, finish, s);
        }

        return answer;
    }

    public static char findOther(char wrong) {
        char lost = 0;
        switch (wrong) {
            case '(' : lost = ')';
                       break;
            case '{' : lost = '}';
                       break;
            case '[' : lost = ']';
                        break;
            case ')' : lost = '(';
                    break;
            case '}' : lost = '{';
                    break;
            case ']' : lost = '[';
                    break;
        }

        return lost;
    }

    public static int count(int start, int end, String s) {
        int answer = 0;
        char[] openBracket = {'(', '{', '['};
        boolean isLostOpen = false;



        for(int i = start; i < end; i++) {
            char ch = s.charAt(i);

            if(ch != wrong && ch!= lost && open.isEmpty()) {
                answer++;
            }

            if(ch != lost && ch != wrong) {
                if(ch == '(' || ch == '{' || ch == '[') {
                    open.push(ch);
                } else {
                    open.pop();
                }
            }
        }
        return answer+1;
    }
}

