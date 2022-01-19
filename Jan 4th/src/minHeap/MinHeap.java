package minHeap;

/*
    2022.01.19
    백준 1927번 "최소 힙"

    힙의 삭제 과정을 헷갈리지 말 것.
    단계를 글로 써보고 코드를 작성할 것.
 */

import java.io.*;
import java.util.*;

public class MinHeap {

    static List<Integer> heap = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/minHeap/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        heap.add(0);

        for(int i = 0; i < n ; i++) {
            int num = Integer.parseInt(br.readLine());

            if(num > 0) {
                insert(num);
            } else {
                System.out.println(delete());
            }
        }
    }

    //insert
    static void insert(int num) {
        //1. 배열 끝에 삽입
        heap.add(num);
        int idx = heap.size() - 1;
        int parent = heap.get(idx / 2);

        //2. 만약 조건에 위배된다면 부모노드와 swap(반복)
        while(parent != 0 && parent > num) {
            heap.set(idx/2, num);
            heap.set(idx, parent);

            idx = idx/2;
            parent = heap.get(idx/2);
        }
    }

    //delete
    static int delete() {

        //배열이 비어있을 경우
        if(heap.size() == 1) {
            return 0;
        }

        //1. 루트 꺼내기
        int root = heap.get(1);
        int idx = 1;

        //2. 자식이 존재하지 않으면 루트노드를 제거하고 리턴
        if(heap.size() < 3) {
            heap.remove(1);
            return root;
        }

        //2. 자식이 존재하면, 마지막 값을 루트로 가져오기
        heap.set(1, heap.remove(heap.size() - 1));
        int parent = heap.get(1);

        //3. 자식노드가 존재하는 경우, 부모보다 자식이 더 큰지 확인
        while((idx * 2) <= (heap.size() - 1)) {
            int left = heap.get(idx*2);
            //오른쪽 노드 존재
            if((idx * 2 + 1) <= (heap.size() - 1)) {
                int right = heap.get(idx*2+1);
                if(parent > left || parent > right) {
                    heap.set(idx, left < right? left : right);
                    idx = left < right ? idx*2: idx*2+1;
                    heap.set(idx, parent);
                    parent = heap.get(idx);
                } else {
                    break;
                }
            } else {
                if(parent > left) {
                    heap.set(idx, left);
                    idx = idx*2;
                    heap.set(idx, parent);
                    parent = heap.get(idx);
                } else {
                    break;
                }
            }
        }
        return root;
    }
}
