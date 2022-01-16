/*
    2022.01.15 프로그래머스 스택/큐 문제 "기능개발" Level2


 */

import java.util.*;

public class Develop {

    public static void main(String[] args) {

        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};

        int[] answer = solution(progresses, speeds);
        System.out.println(answer);
    }

    static public int[] solution(int[] progresses, int[] speeds) {
        int[] answer;
        List<Integer> answer_list = new LinkedList<>();

        int dist_num = 0;

        int date = 1;
        int i = 0;
        while(true) {
            if(i >= progresses.length) break;

            //해당 인덱스의 기능이 배포 가능한지 확인
            if(progresses[i] + (speeds[i] * date) >= 100) {
                //배포 개수 증가
                dist_num++;

                //뒤순서 기능들 중에 배포 가능한 기능이 있는지 확인
                while(true) {
                    i += 1;

                    if(i >= progresses.length) break;


                    //뒤순서 기능을 더이상 배포할 수 없으면 중지
                    if(progresses[i] + (speeds[i] * date) < 100) break;
                    else dist_num++;
                }

                //배포 기록
                answer_list.add(dist_num);

                //초기화
                dist_num = 0;
            }

            //다음날로
            date++;
        }

        answer = answer_list.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }
}
