/*
    2022.10.11 프로그래머스 해시 문제 "위장" Level 2

    22:38 ~

 */

import java.util.*;

public class Camouflage {
    class Solution {
        public int solution(String[][] clothes) {
            int answer = 0;

            Map<String, List<String>> board = new HashMap<>();

            for(String[] item : clothes){

                List<String> newList;

                if(board.get(item[1]) != null) {  newList = board.get(item[1]); }
                else { newList = new ArrayList<>(); }

                newList.add(item[0]);
                board.put(item[1], newList);
            }

            return answer;
        }
    }
}
