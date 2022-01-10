/*
    2022.01.10 프로그래머스 해시 문제 '전화번호 목록'

    약 1시간 소요

    초기 O(n^2) 형태로는 효율성을 통과하지 못해
    O(n)형태로 변경
 */

import java.util.*;

public class PhoneNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        String[] book = new String[num];

        sc.nextLine();
        for(int i = 0; i < num ; i++) {
            book[i] = sc.nextLine();
        }

        boolean answer = solution(book);
        System.out.println(answer);
    }

    static public boolean solution(String[] phone_book) {
        boolean answer = true;

        Map<String, Integer> book = new HashMap<>();

        for(String num: phone_book) {
            book.put(num, 0);
        }

        for(String num : phone_book) {
            for(int i = 1; i < num.length(); i++) {
                if(book.get(num.substring(0, i)) != null) {
                    answer = false;
                    break;
                }
            }
            if(!answer) break;
        }

        return answer;
    }
}
