package escape;

import java.io.*;
import java.util.*;
/*
    2022.01.17
    백준 3055번 "탈출"
 */

public class Escape {
    static int[] setup;
    static String [][] map;
    static int[][] go;

    static Queue<escape.Point> qu = new LinkedList<>();;

    public static void main(String[] args) throws IOException {

        //input txt 설정
        System.setIn(new FileInputStream("src/escape/input.txt"));

        //첫 setup 가져오기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] setup_string = br.readLine().split(" ");
        setup = new int[]{Integer.parseInt(setup_string[0]), Integer.parseInt(setup_string[1])};

        //map 가져오기
        map = new String[setup[0]][setup[1]];
        go = new int[setup[0]][setup[1]];
        Point start = null;

        for(int i = 0; i < setup[0]; i++){
            String[] row = br.readLine().split("");
            for(int j = 0; j < setup[1]; j++) {
                if(row[j].equals("S")) start = new Point(i, j, 'a');
                if(row[j].equals("*")) qu.add(new Point(i, j, '*'));
                map[i][j] = row[j];
            }
        }

        qu.add(start);

        //실행
        run();
    }

    static void run() {
        int count = 0;
        boolean arrive = false;

        int[] moveX = {0, 0, -1, 1};
        int[] moveY = {-1, 1, 0, 0};

        //1. 큐에서 꺼내기
        while(!qu.isEmpty()) {
            Point loc = qu.poll();

            //2. 목적지인가? -> (고슴도치) D인가?
            if(loc.type == 'a' && map[loc.x][loc.y].equals("D")) {
                arrive = true;
                count = go[loc.x][loc.y];
                break;
            }

            //3. 연결된 곳을 순회 -> 좌우위아래
            for(int i = 0; i < 4; i++) {
                int xp = loc.x + moveX[i];
                int yp = loc.y + moveY[i];

                if(xp < 0 || xp >= setup[0] || yp < 0 || yp >= setup[1])
                    continue;

                if(loc.type == 'a'){
                    //4. 갈 수 있는가? -> (고슴도치) . or D, 방문하지 않은 곳
                    if((map[xp][yp].equals(".") || map[xp][yp].equals("D")) && go[xp][yp] == 0){
                        //5. 체크인
                        go[xp][yp] = go[loc.x][loc.y] + 1;
                        //6. 큐에 넣음
                        qu.add(new Point(xp, yp, 'a'));
                    }
                } else {
                    //4. 갈 수 있는가? -> (물) . or S
                    if(map[xp][yp].equals(".") || map[xp][yp].equals("S")){
                        //5. 체크인
                        map[xp][yp] = "*";
                        //6. 큐에 넣음
                        qu.add(new Point(xp, yp, '*'));
                    }
                }
            }
        }

        if(arrive) System.out.println(count);
        else System.out.println("KAKTUS");

    }

}

class Point {
    int x;
    int y;
    char type;

    public Point(int x, int y, char type) {
        super();
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
