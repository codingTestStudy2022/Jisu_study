/*
    2022.01.12 해시 문제 "베스트앨범" Level 3

    ::(더블콜론)은 람다식처럼 함수를 간결하게 사용할 수 있도록 하는 것.
 */

import java.util.*;
import java.util.Map.*;

public class BestAlbum {

    public static void main(String[] args) {
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};

        int[] answer = solution(genres, plays);
        int[] correct = {4, 1, 3, 0};

        for(int ans : answer){
            System.out.print(ans + " ");
        }
        System.out.println("");
        System.out.println(Arrays.equals(answer, correct));
    }

    static public int[] solution(String[] genres, int[] plays) {
        int[] answer;

        int totalLen = genres.length;

        Map<Integer, String[]> music = new HashMap<>();      //고유번호,  [장르, 재생수]
        Map<String, Integer> gen_num = new HashMap<>();    //장르, 재생수

        for (int i = 0; i < totalLen; i++) {
            String[] item = {genres[i], Integer.toString(plays[i])};
            music.put(i, item);
        }

        //장르별 전체 재생수 수집
        for (Integer key: music.keySet() ) {
            String[] item = music.get(key); //[장르, 재생수]

            gen_num.put(item[0], gen_num.getOrDefault(item[0], 0) + Integer.parseInt(item[1]));

        }

        //장르별 재생수 내림차순 정렬
        List<Entry<String, Integer>> list_gen_num = new ArrayList<>(gen_num.entrySet());

        Collections.sort(list_gen_num, new Comparator<Entry<String, Integer>>(){
            public int compare(Entry<String, Integer> first, Entry<String, Integer> second) {
                return second.getValue().compareTo(first.getValue());
            }
        });

        //각 장르별 가장 높은 값 찾기
        List<Integer> answer_list = new LinkedList<>();

        for(Entry<String, Integer> entry : list_gen_num) {
            String genre = entry.getKey();

            int[] first = {-1, -1};   //고유번호, 재생수
            int[] second = {-1, -1};

            for(Entry<Integer, String[]> music_entry : music.entrySet()) {
                Integer number = music_entry.getKey();  //고유번호
                String[] value = music_entry.getValue();    //[장르, 재생수]

                if(value[0].equals(genre)) {
                    int play = Integer.parseInt(value[1]);
                    if( play > first[1] ) {
                        second[0] = first[0];
                        second[1] = first[1];

                        first[0] = number;
                        first[1] = play;
                    } else if (play > second[1]) {
                        second[0] = number;
                        second[1] = play;
                    }
                }
            }

            //정답 작성
            answer_list.add(first[0]);
            if(second[0] != -1) answer_list.add(second[0]);
        }

        answer = answer_list.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }

}
