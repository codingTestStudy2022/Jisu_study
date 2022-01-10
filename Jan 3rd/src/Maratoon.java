/*
    2022.01.10 프로그래머스 해시 문제 '완주하지 못한 선수'
    HashMap의 getOrDefault(키, 디폴트값)이 편리하다!
 */

import java.util.*;

public class Maratoon {

    public static void main(String[] args) {
        HashMap test = new HashMap();

        test.put("1", 0);

        System.out.println(test.get("2"));
    }

    public String soluntion(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> comp = new HashMap();

        for (String name : completion) {
            if(comp.get(name) != null) {
                int newValue = (int)comp.get(name) + 1;
                comp.put(name, newValue);
                continue;
            }
            comp.put(name, 0);
        }

        for (String name : participant) {
            if(comp.get(name) == null) {
                answer = name;
                break;
            }
            else if((int)comp.get(name) >= 0) {
                int newValue = (int)comp.get(name) - 1;
                comp.put(name, newValue);
                continue;
            }
            else {
                answer = name;
                break;
            }
        }

        return answer;
    }
}
