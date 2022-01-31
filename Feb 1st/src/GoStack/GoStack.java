package GoStack;

/*
    22.01.31
    백준 3425번 '고스택'
    https://www.acmicpc.net/problem/3425

    스택 구현 문제
    경우의 수가 드럽게 어렵다.
 */

import java.util.*;
import java.io.*;

public class GoStack {

    static ArrayList<String[]> command = new ArrayList<>();
    static ArrayList<Integer> stack = new ArrayList<>();

    static int max = 1000000000;

    public static void main(String[] args) throws IOException{

        //입력
        System.setIn(new FileInputStream("src/GoStack/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            command.clear();

            //명령어 입력
            while(true){
                StringTokenizer st = new StringTokenizer(br.readLine());

                String[] line = new String[2];

                line[0] = st.nextToken();
                if(line[0].equals("NUM")) {
                    line[1] = st.nextToken();
                }

                command.add(line);

                if(line[0].equals("END") || line[0].equals("QUIT")) break;
            }

            //quit이면 종료
            if(command.get(0)[0].equals("QUIT")) break;

            //각 스택 초기값 받아 수행
            int num = Integer.parseInt(br.readLine());

            for (int i = 0; i < num; i++) {
                stack.clear();
                stack.add(Integer.parseInt(br.readLine()));
                boolean isError = false;

                for (String[] line : command) {
                    int size = stack.size();
                    if(!line[0].equals("NUM") && size == 0) {
                        bw.write("ERROR\n");
                        break;
                    }

                    switch(line[0]) {
                        case "NUM":
                            isError = NUM(Integer.parseInt(line[1]));
                            break;
                        case "POP":
                            isError = POP();
                            break;
                        case "INV":
                            isError = INV();
                            break;
                        case "DUP":
                            isError = DUP();
                            break;
                        case "SWP":
                            isError = SWP();
                            break;
                        case "ADD":
                            isError = ADD();
                            break;
                        case "SUB":
                            isError = SUB();
                            break;
                        case "MUL":
                            isError = MUL();
                            break;
                        case "DIV":
                            isError = DIV();
                            break;
                        case "MOD":
                            isError = MOD();
                            break;
                        case "END":
                            if(size != 1) {
                                bw.write("ERROR\n");
                                break;
                            }
                            for(int j = size-1; j >= 0; j--) {
                                bw.write(stack.get(j) + "\n");
                            }
                            break;
                    }
                    if(isError) {
                        bw.write("ERROR\n");
                        break;
                    }
                }
            }

            bw.write("\n");
            br.readLine();
        }

        br.close();
        bw.flush();
        bw.close();
    }

    static boolean NUM(int num) {
        stack.add(num);
        return false;
    }

    static boolean POP() {
        stack.remove(stack.size()-1);
        return false;
    }

    static boolean INV() {
        int pop = stack.get(stack.size()-1);
        stack.set(stack.size()-1, pop-(2 * pop));

        return false;
    }

    static boolean DUP() {
        stack.add(stack.get(stack.size()-1));
        return false;
    }

    static boolean SWP() {
        if(stack.size() < 2) {
            return true;
        }
        int first = stack.get(stack.size()-1);
        int second = stack.get(stack.size()-2);
        stack.set(stack.size()-1, second);
        stack.set(stack.size()-2, first);

        return false;
    }

    static boolean ADD() {
        if(stack.size() < 2) {
            return true;
        }

        int first = stack.remove(stack.size()-1);
        int second = stack.get(stack.size()-1);
        if(Math.abs((long)first+(long)second) > max){
            return true;
        }
        stack.set(stack.size()-1, first+second);

        return false;
    }

    static boolean SUB() {
        if(stack.size() < 2) {
            return true;
        }
        int f2 = stack.remove(stack.size()-1);
        int s2 = stack.get(stack.size()-1);
        if(Math.abs((long)s2-(long)f2) > max){
            return true;
        }
        stack.set(stack.size()-1, s2-f2);
        return false;
    }

    static boolean MUL() {
        if(stack.size() < 2) {
            return true;
        }
        int f3 = stack.remove(stack.size()-1);
        int s3 = stack.get(stack.size()-1);

        if(Math.abs((long)f3 * (long)s3) > max){
            return true;
        }
        stack.set(stack.size()-1, f3*s3);
        return false;
    }

    static boolean DIV() {
        if(stack.size() < 2) {
            return true;
        }

        int f4 = stack.remove(stack.size()-1);
        int s4 = stack.get(stack.size()-1);
        if(f4 == 0) {
            return true;
        }

        int result = Math.abs(s4) / Math.abs(f4);

        if((f4 > 0 && s4 < 0) || (f4 < 0 && s4 > 0)) {
            result -= 2 * result;
        }

        stack.set(stack.size()-1, result);
        return false;
    }

    static boolean MOD() {
        if(stack.size() < 2) {
            return true;
        }

        int f5 = stack.remove(stack.size()-1);
        int s5 = stack.get(stack.size()-1);
        if(f5 == 0) {
            return true;
        }

        int result = Math.abs(s5) % Math.abs(f5);

        if(s5 < 0) {
            result -= 2 * result;
        }

        stack.set(stack.size()-1, result);

        return false;
    }
}
