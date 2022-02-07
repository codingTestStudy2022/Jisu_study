package rain;

/*
    2022.02.03
    백준 2094번 "강수량"
    https://www.acmicpc.net/problem/2094

    인덱스 트리 응용 유형

    세그먼트 트리는 구간의 합 뿐만 아니라 곱, 최댓값, 최솟값까지 가능하다..!!

    진짜 질문 게시판에 올라온 온갖 재채점 사례들을 다 확인한 뒤에야 간신히 통과함
    진짜 천재는 다르다
 */

import java.util.*;
import java.io.*;

public class Rain {

    static int N;
    static int[][] list;    //{연도, 강수량}

    //해당 연도의 순번 기록. idx = n일 때, 내 앞에 n개의 기록이 있음을 의미.
    //key : 연도, value : [순번, 기록]
    static HashMap<Integer, int[]> records = new HashMap<>();

    //인덱스 트리(최댓값을 저장)
    static int[] tree;
    static int S;

    public static void main(String[] args) throws IOException {

        //입력
        System.setIn(new FileInputStream("src/rain/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        list = new int[N][2];

        S = 1;
        while(S < N) S *= 2;
        tree = new int[2*S];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] tmp = new int[2];
            tmp[0] = Integer.parseInt(st.nextToken());
            tmp[1] = Integer.parseInt(st.nextToken());

            list[i][0] = tmp[0];
            list[i][1] = tmp[1];

            int[] tmp2 = {i, tmp[1]};
            records.put(tmp[0], tmp2);

            //인덱스 트리에 삽입
            update(i, tmp[1]);
        }

        //쿼리 처리
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            int max = 0;

            //y, x의 기록을 둘 다 모를 경우 : 정하기 나름이므로 max값이 최댓값만 아니면 무조건 maybe
            if(!records.containsKey(x) && !records.containsKey(y)){

                int backOfY = binerySearch(y, false);
                int frontOfX = binerySearch(x, true);

                max = query(0, S-1, 1, backOfY, frontOfX);

                bw.write("maybe\n");
                continue;
            }

            //x 기록만 알 경우 : y 뒤에 있는 값부터 x 앞까지의 최댓값이 x 강수량 미만인 경우만 maybe(이진탐색)
            if(records.containsKey(x) && !records.containsKey(y)){

                int backOfY = binerySearch(y, false);

                //y 바로 뒤에 있는 기록이 x일 경우
                if(backOfY == records.get(x)[0]) {
                    bw.write("maybe\n");
                    continue;
                }

                max = query(0, S-1, 1, backOfY, records.get(x)[0]-1);

                if(max < records.get(x)[1]) {
                    bw.write("maybe\n");
                } else {
                    bw.write("false\n");
                }
                continue;
            }
            //y 기록만 알 경우
            if(!records.containsKey(x) && records.containsKey(y)){

                int frontOfX = binerySearch(x, true);

                //x 바로 앞에 있는 기록이 y일 경우
                if(frontOfX == records.get(y)[0]) {
                    bw.write("maybe\n");
                    continue;
                }

                max = query(0, S-1, 1, records.get(y)[0]+1, frontOfX);

                //y가 max 이상일 때
                if(records.get(y)[1] > max){
                    bw.write("maybe\n");
                    continue;
                }else  {
                    bw.write("false\n");
                    continue;
                }
            }

            //y, x 기록을 모두 알 경우
            //x가 y 강수량 이상일 경우
            if(records.get(x)[1] > records.get(y)[1]) {
                bw.write("false\n");
                continue;
            }

            //y와 x가 연달아 있을 경우
            if(y == x-1) {
                if(records.get(x)[1] <= records.get(y)[1]){
                    bw.write("true\n");
                } else{
                    bw.write("false\n");
                }
                continue;
            }

            //x, y 사이의 모든 값을 알고 있을 경우
            if(records.get(x)[0] - records.get(y)[0] == (x - y)) {
                max = query(0, S-1, 1, records.get(y)[0] + 1, records.get(x)[0] - 1);

                if(max < records.get(x)[1]) {
                    bw.write("true\n");
                } else{
                    bw.write("false\n");
                }
            }
            //x, y 값을 듬성듬성 알고 있을 경우
            else {
                max = query(0, S-1, 1, records.get(y)[0] + 1, records.get(x)[0] - 1);

                if(max < records.get(x)[1]) {
                    bw.write("maybe\n");
                } else{
                    bw.write("false\n");
                }
            }

        }

        br.close();
        bw.flush();
        bw.close();

    }

    static void update(int target, int value){
        int idx = S + target;

        while(idx > 1) {
            if(Math.max(tree[idx], value) == value){
                tree[idx] = value;
                idx /= 2;
            } else {
                break;
            }
        }

        tree[1] = Math.max(tree[1], value);
    }

    static int query(int left, int right, int node, int queryL, int queryR) {
        int answer = 0;

        if(left > queryR || right < queryL) {
            return 0;
        }
        else if(left == right || (left >= queryL && right <= queryR)) {
            return tree[node];
        }
        else{
            int maxLeft = query(left, (left+right)/2 , node*2, queryL, queryR);
            int maxRight = query((left+right)/2+1, right, node*2+1, queryL, queryR);
            answer = Math.max(maxLeft, maxRight);
        }

        return answer;
    }

    static int binerySearch(int year, boolean isCeil) {
        int start = 0;
        int end = N-1;

        while(start <= end) {
            int idx = (start + end) / 2;

            if(year < list[idx][0]) {
                end = idx - 1;
            } else if(year > list[idx][0]) {
                start = idx + 1;
            }
        }

        return isCeil? end : start;
    }
}
