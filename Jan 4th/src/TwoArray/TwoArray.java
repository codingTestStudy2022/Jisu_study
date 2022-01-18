package TwoArray;

/*
    2022.01.18
    백준 2143번 "두 배열의 합"

    Long과 long은 다르다. 진짜 짜증나
 */

import java.io.*;
import java.util.*;

public class TwoArray {

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/TwoArray/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        long[] first = new long[n+1];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            first[i] = Long.parseLong(st1.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        long[] second = new long[m+1];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            second[i] = Long.parseLong(st2.nextToken());
        }

        long[] onesub = makeSub(first, n);
        long[] twosub = makeSub(second, m);

        find(onesub, twosub, T);


    }

    static long[] makeSub(long[] ori, int size) {
        List<Long> sub = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            long sum = ori[i];
            sub.add(sum);
            for(int j = i + 1; j < size; j++) {
                sum += ori[j];
                sub.add(sum);
            }
        }

        //정렬
        Collections.sort(sub);
        long[] sub_array = sub.stream().mapToLong(Long::longValue).toArray();

        return sub_array;
    }

    static void find(long[] fst, long[] sec, long T) {

        Long answer = Long.valueOf(0);
        int pt1 = 0; int pt2 = sec.length - 1;

        long sum = 0;

        while(pt1 < fst.length && pt2 > -1) {
            sum = fst[pt1] + sec[pt2];

            //sum == T
            if(sum == T) {
                //동률 체크
                long count1 = 0; long count2 = 0;
                long check = fst[pt1];
                while(pt1 < fst.length && fst[pt1] == check) {
                    count1++;
                    pt1++;
                }
                check = sec[pt2];
                while(pt2 > -1 && sec[pt2] == check){
                    count2++;
                    pt2--;
                }

                answer += (long)(count1 * count2);
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
