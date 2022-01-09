import java.io.*;

/*
    2022.01.10 백준 1003번
    (실패)
    기존에 재귀함수만 알고 있었으나 동적 계획법에 대해 새로 공부하게 됨.

    풀이 참고 : https://st-lab.tistory.com/124
 */

public class Fibonacci {

    static Integer[][] table = new Integer[41][2];

    public static void main(String[] args) throws IOException{

        table[0][0] = 1;
        table[0][1] = 0;
        table[1][0] = 0;
        table[1][1] = 1;


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(br.readLine());

        for(int i = 1; i<=caseNum; i++) {
            int testcase = Integer.parseInt(br.readLine());

            fibo(testcase);

            System.out.println(table[testcase][0] + " " + table[testcase][1]);
        }

        long after = System.currentTimeMillis();

    }

    static Integer[] fibo(int num) {
        if(table[num][0] == null || table[num][1] == null){
            table[num][0] = fibo(num-1)[0] + fibo(num-2)[0];
            table[num][1] = fibo(num-1)[1] + fibo(num-2)[1];
        }
        return table[num];
    }
}
