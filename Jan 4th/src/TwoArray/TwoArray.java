package TwoArray;

import java.io.*;
import java.util.*;

public class TwoArray {

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/TwoArray/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        Long[] first = new Long[n+1];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            first[i] = Long.parseLong(st1.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        Long[] second = new Long[m+1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            second[i] = Long.parseLong(st2.nextToken());
        }

        List<Long> onesub = makeSub(first, n);
        List<Long> twosub = makeSub(second, m);

        find(onesub, twosub, T);


    }

    static List<Long> makeSub(Long[] ori, int size) {
        List<Long> sub = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            Long sum = ori[i];
            sub.add(sum);
            for(int j = i + 1; j < size; j++) {
                sum += ori[j];
                sub.add(sum);
            }
        }

        //정렬
        Collections.sort(sub);

        return sub;
    }

    static void find(List<Long> fst, List<Long> sec, Long T) {

        Long answer = Long.valueOf(0);
        int pt1 = 0; int pt2 = sec.size() - 1;
        Long count1 =  Long.valueOf(0); Long count2 =  Long.valueOf(0);
        long sum = 0;

        while(pt1 < fst.size() && pt2 > -1) {
            sum = fst.get(pt1) + sec.get(pt2);

            //sum == T
            if(sum == T) {
                //동률 체크
                Long check = fst.get(pt1);
                while(pt1 < fst.size() && check == fst.get(pt1)) {
                    count1++;
                    pt1++;
                }
                check = sec.get(pt2);
                while(pt2 > -1 && check == sec.get(pt2)){
                    count2++;
                    pt2--;
                }

                answer += (Long)(count1 * count2);
            }

            //sum > T
            else if(sum > T) {
                pt2--;
            }

            //sum < T
            else {
                pt1++;
            }
        }
        System.out.println(answer);
    }
}
