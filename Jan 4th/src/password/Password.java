package password;

/*
    2022.01.20
    백준 1827번 "암호제작"
    https://www.acmicpc.net/problem/1837

    소수 찾기, BigInteger 유형 문제.
    BigInteger를 풀어서 쓸 수 있는가를 확인함.

    k를 포함하는 소수가 아닌 k보다 작은 소수들을 대상으로 해야하는데
    이런 사소한 것 놓치지 말기
 */

import java.io.*;
import java.util.*;

public class Password {

    static String p;

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/password/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        p = st.nextToken();
        int k = Integer.parseInt(st.nextToken());
        br.close();

        //2~k 범위 내 소수 모두 찾기
        boolean[] isNotPrime = new boolean[k];
        List<Integer> prime = new LinkedList<>();

        for (int i = 2; i < k; i++) {
            if(!isNotPrime[i]) {
                prime.add(i);

                int num = i + i;
                while(num < k) {
                    isNotPrime[num] = true;
                    num += i;
                }
            }
        }

        //찾은 소수들을 하나씩 나눠보기
        boolean isBad = false;
        StringBuilder sb = new StringBuilder();
        for(int num : prime){
            if(divide(num)) {
                isBad = true;
                sb.append("BAD ");
                sb.append(num);
                break;
            }
        }

        if(isBad) {
            System.out.println(sb);
        } else {
            System.out.println("GOOD");
        }
    }

    static boolean divide(int num) {
        int rest = 0;
        String rest_s = "";
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            rest = Integer.parseInt(rest_s + ch);
            rest %= num;
            rest_s = Integer.toString(rest);

        }

        return rest == 0? true: false;
     }
}
