package stealGem;

/*
    2022.01.19
    백준 1202번 "보석 도둑"
 */

import java.util.*;
import java.io.*;

public class StealGem {

    public static void main(String[] args) throws IOException{

        System.setIn(new FileInputStream("src/stealGem/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st1.nextToken());
        int K = Integer.parseInt(st1.nextToken());


       Gem[] weight = new Gem[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st2.nextToken());
            int v = Integer.parseInt(st2.nextToken());
            weight[i] = new Gem(m, v);
        }

        //1. 보석 무게 오름차순 정렬
        Arrays.sort(weight, Comparator.comparingInt(Gem::getM));

        //2. 보석 가격 내림차순 힙
        PriorityQueue<Gem> price = new PriorityQueue<>(Comparator.comparingInt(Gem::getV).reversed());

        int[] bags = new int[K];
        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        //3. 가방 무게 오름차순 정렬
        Arrays.sort(bags);

        int idx = 0;
        long result = 0;
        for (int i = 0; i < bags.length; i++) {
            //1. 남은 가방 중 가장 작은 가방 선택
            int bag = bags[i];

            while(idx < weight.length && bag >= weight[idx].m){
                price.add(weight[idx++]);
            }

            //2. 넣을 수 있는 모든 남은 보석들 중 가장 비싼 보석 선택
            if(!price.isEmpty()){
                result += price.poll().v;
            }
        }

        System.out.println(result);
    }
}

class Gem{
    int m;
    int v;

    public Gem(int m, int v){
        this.m = m;
        this.v = v;
    }

    public int getM() {
        return m;
    }

    public int getV() {
        return v;
    }
}
