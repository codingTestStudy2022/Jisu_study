package boggle;

/*
    2022.01.20
    백준 9202번 "Boggle"
    https://www.acmicpc.net/problem/9202

    trie 유형 문제
    가이드와 답코드를 보면서 간신히 풀었음..
 */

import java.io.*;
import java.util.*;

public class Boggle {

    static Node tree = new Node('a');
    static char[][] board = new char[4][4];
    static Answer answer = new Answer();
    static boolean[][] visited = new boolean[4][4];

    static int[] moveX = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] moveY = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] add = {0, 0, 1, 1, 2, 3, 5, 11};

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/boggle/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            String word = br.readLine();
            //단어 추가
            insert(word);
        }
        br.readLine();

        StringBuilder st = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < 4; j++) {
                String line = br.readLine();
                for (int k = 0; k < 4; k++) {
                    board[j][k] = line.charAt(k);
                }
            }

            //DFS 탐색
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(tree.hasChild(board[j][k])) {
                        find(j, k, tree.getChild(board[j][k]), 1);
                    }
                }

            }
            //결과 기록
            st.append(answer.score);
            st.append(" ");
            st.append(answer.longest);
            st.append(" ");
            st.append(answer.count);
            st.append("\n");

            //초기화
            answer.reset();
            resetHit(tree);
            br.readLine();
        }

        System.out.println(st);
    }

    static void find(int xp, int yp, Node node, int len){
        //1. 체크인
        visited[xp][yp] = true;
        //2. 목적지인지 확인
        if(node.isEnd && !node.isHit) {

            answer.count += 1;
            answer.score += add[len-1];
            node.isHit = true;

            //찾은 단어가 더 긴 단어인지 확인
            if(compare(answer.longest, node.word)) {
                answer.longest = node.word;
            }

        }

        //3. 연결된 곳을 순회
        for (int i = 0; i < 8; i++) {
            int nextX = xp + moveX[i];
            int nextY = yp + moveY[i];

            if(nextX >= 4 || nextY >= 4 || nextX < 0 || nextY < 0) {
                continue;
            }

            if(!visited[nextX][nextY]) {
                int idx = board[nextX][nextY] - 65;

                //4. 갈 수 있나?
                if(node.child[idx] != null) {
                    //5. 간다
                    find(nextX, nextY, node.child[idx], len+1);
                }
            }
        }
        //6. 체크아웃
        visited[xp][yp] = false;
    }

    static boolean compare(String a, String b) {
        int result = Integer.compare(b.length(), a.length());
        if(result == 0){
            if(a.compareTo(b) > 0){
                return true;
            } else {
                return false;
            }
        }
        else if(result < 0){
            return false;
        }
        else {
            return true;
        }
    }

    static void insert(String word) {
        Node current = tree;
        for (int i = 0; i < word.length(); i++) {
            //child 배열에 해당 알파벳 노드가 만들어져있는지 확인
            int idx = word.charAt(i) - 65;

            //없다면
            if(current.child[idx] == null){
                current.child[idx] = new Node(word.charAt(i));
            }
            current = current.child[idx];
        }

        current.isEnd = true;
        current.word = word;
    }

    static void resetHit(Node root) {
        Node current = root;
        root.isHit = false;
        for (int i = 0; i < 26; i++) {
            if(current.child[i] != null) {
                resetHit(root.child[i]);
            }
        }
    }
}



class Node {
    char ch;
    String word;
    Node[] child = new Node[26];
    boolean isEnd;
    boolean isHit;

    public Node(char ch) {
        this.ch = ch;
        word = null;
        this.isEnd = false;
        isHit = false;
    }

    Node getChild(char ch) {
        int idx = ch - 'A';

        return this.child[idx];
    }

    boolean hasChild(char ch) {
        int idx = ch - 65;
        if(this.child[idx] == null) {
            return false;
        } else {
            return true;
        }
    }
}

class Answer {
    int count;
    String longest;
    int score;

    public Answer(){
        count = 0;
        longest = "";
        score = 0;
    }

    public void reset() {
        count = 0;
        longest = "";
        score = 0;
    }
}