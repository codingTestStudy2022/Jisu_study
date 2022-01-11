/*
    2022.10.11 프로그래머스 해시 문제 "위장" Level 2

    조합 공식을 쓰면 아주 간단해지는 문제.
    최악의 경우 O(n^3)이라는 끔찍한 효율을 보임...
    답은 대충 다 맞는 듯.
    공식을 사용하지 않아보려 노력함.

 */

import java.util.*;
import java.io.*;

public class Camouflage {

    static Map<String, Integer> board = new HashMap<>();
    static Map<String, Integer> memory = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());

        String[][] clothes = new String[count][2];


        for(int i = 0; i <count; i++) {
            String[] item = br.readLine().split(", ");
            clothes[i] = item;
        }

        int answer = solution(clothes);

        System.out.println(answer);

    }

    static public int solution(String[][] clothes) {
        int answer = 0;


        //board 해시맵 만들기
        for(String[] item : clothes){

            int num = 0;

            if(board.get(item[1]) != null) {
                num += board.get(item[1]);
            }
            board.put(item[1], num + 1);
        }

        //키 배열 생성
        String[] keys = board.keySet().toArray(new String[board.size()]);

        //그룹핑할 사이즈 결정(1~최대까지)
        for(int i = 1; i <= board.size(); i++) {
            //기준 idx 설정
            for(int j = 0; j < board.size(); j++) {
                answer += count(keys, i, j);
            }
        }

        return answer;
    }


    public static int count(String[] keys, int num, int idx) {
        //그룹핑 사이즈가 1이면 키에 저장된 값만 반환
        if(num == 1) {
            return board.get(keys[idx]);
        }
        //기준 idx 뒤로 num개의 item이 존재하지 않을 때(즉, 더이상 따져볼 수 없음)
        else if(idx + num > keys.length) return 0;
        else {
            int check = 0;
            for (int i = idx+1; i < keys.length; i++) {
                check += board.get(keys[idx]) * count(keys, num-1, i);
            }

            return check;
        }
    }
}
