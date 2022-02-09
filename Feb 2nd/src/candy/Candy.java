package candy;

/*
    2022.02.09
    백준 3955번 "캔디 분배"
    https://www.acmicpc.net/problem/3955

	확장된 유클리드 호제법 유형
	도저희 이해가 안됨. 미완료
 */

import java.util.*;
import java.io.*;

public class Candy {

    public static void main(String[] args) throws IOException{
        System.setIn(new FileInputStream("src/candy/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	int k = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	Result result = EEA(k, c);
        	
        	long x0, y0;
        	if(result.r != 1) {
        		System.out.println("IMPOSSIBLE");
        	} else {
        		x0 = result.s;
        		y0 = result.t;
        		
        		y0 %= k;
        		
        		if(y0 < 0) y0 += k;
        		
        		y0 = Math.max(y0, (k+c)/c);
        		
        		if(y0 <= 1000000000) {
        			System.out.println(y0);
        		} else {
        			System.out.println("IMPOSSIBLE");
        		}        		
        	}
        }
    }
    
    //확장된 유클리드 호제법
    static Result EEA(int a, int b) {
    	long s0 = 1, t0 = 0, r0 = a;
    	long s1 = 0, t1 = 1, r1 = b;
    	
    	long tmp;
    	while(r1 != 0) {
    		long q = r0/r1;
    		
    		tmp = r0 - q * r1;
    		r0 = r1;
    		r1 = tmp;
    		
    		tmp = s0 - q * s1;
    		s0 = s1;
    		s1 = tmp;
    		
    		tmp = t0 - q * t1;
    		t0 = t1;
    		t1 = tmp;
    	}
    	
    	return new Result(s0, t0, r0);
    	
    }
}

class Result {
	long s, t, r;
	
	public Result(long s, long t, long r) {
		this.s = s;
		this.t = t;
		this.r = r;
	}
}
