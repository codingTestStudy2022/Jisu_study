package game;

/*
    22.02.01
    백준 1103번 '게임'
    https://www.acmicpc.net/problem/1103

    여기의 DP는 다시 사용X 중복되는 계산을 막기 위함

    DP의 의미 : 현재 위치에 오기까지 거쳐온 count의 수.
    만약 4번을 거쳐 도착한 포인트 dp에 4가 저장되어있다면 이미 다른 루트로 4번 거쳐 도착한 적이 있고,
    이미 이 이후 루트는 확인된 루트라는 것을 보장하는 것.
    이후 루트를 가능한 경우의 수 대로 전부 거쳐서 최댓값을 반환했을 것이기 때문에 다시 거치는 것이 의미가 없다.
 */

import java.util.*;
import java.io.*;

public class Game{

    static String[][] board;
    static boolean[][] visited;
    static int[][] dp;
    static int rows, cols;

    static int[] xmove = {0, 0, -1, 1};
    static int[] ymove = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {

        //입력
        System.setIn(new FileInputStream("src/game/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        board = new String[rows][cols];
        visited = new boolean[rows][cols];
        dp = new int[rows][cols];

        for(int i = 0;  i< rows; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0; j < cols; j++) {
                board[i][j] = line[j];
            }
        }

        //dfs
        int answer = dfs(0, 0, 1);
        System.out.println(answer);
    }

    static int dfs(int xp, int yp, int count) {

        int answer = count;

        //순환을 만든 경우
        if(visited[xp][yp]) {
            return -1;
        }

        visited[xp][yp] = true;

        dp[xp][yp] = count;

        //구멍일 경우
        if(board[xp][yp].equals("H")) {
            visited[xp][yp] = false;
            return count - 1;
        }

        int num = Integer.parseInt(board[xp][yp]);

        for(int i = 0; i < 4; i++) {
            int nx = xp + (xmove[i] * num);
            int ny = yp + (ymove[i] * num);

            if(nx < 0 || ny < 0 || nx >= rows || ny >= cols || dp[nx][ny] > count) {
                continue;
            }

            int tmp = dfs(nx, ny, count+1);
            if(tmp == -1) {
                answer = -1;
                break;
            }
            answer = Math.max(answer, tmp);
        }

        visited[xp][yp] = false;

        return answer;
    }
}
