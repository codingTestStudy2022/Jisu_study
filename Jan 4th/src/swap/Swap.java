package swap;

/*
    2022.01.20
    백준 3955번 "캔디 분배"
    https://www.acmicpc.net/problem/3955

    확장된 유클리드 호제법 유형
    미완료
 */

import java.io.*;
import java.util.*;

public class Swap {
    static int[] nums;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/swap/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[m];
        for(int i = 0; i<m; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(numbers, K));
    }

    public static int solution(int[] numbers, int K) {
        int answer = 0;

        nums = numbers.clone();

        for (int i = 0; i < nums.length-1; i++) {
            int a = nums[i];
            int b = nums[i+1];

            //두 수의 차가 K 이상일 경우
            if(Math.abs(a - b) > K) {
                boolean isSwapped = false;

                //a 기준
                int s = a;
                //전체 말고 뒤에만 해보기-------------
                for (int j = i+2; j < nums.length; j++) {
                    int t = nums[j];
                    if(s != t) {
                        if(i == 0) {
                            if(j == nums.length - 1) {
                                if(Math.abs(s-nums[j-1]) <= K) {
                                    if(Math.abs(t - nums[i+1]) <= K) {
                                        swap(i, j);
                                        answer++;
                                        isSwapped = true;
                                        break;
                                    }
                                }
                            } else {
                                if(Math.abs(s-nums[j-1]) <= K && Math.abs(s - nums[j+1]) <= K) {
                                    if(Math.abs(t - nums[i+1]) <= K) {
                                        swap(i, j);
                                        answer++;
                                        isSwapped = true;
                                        break;
                                    }
                                }
                            }
                        } else {
                            if(j == nums.length - 1){
                                if(Math.abs(s-nums[j-1]) <= K) {
                                    if(Math.abs(t-nums[i-1]) <= K && Math.abs(t - nums[i+1]) <= K) {
                                        swap(i, j);
                                        answer++;
                                        isSwapped = true;
                                        break;
                                    }
                                }
                            } else {
                                if(Math.abs(s-nums[j-1]) <= K && Math.abs(s - nums[j+1]) <= K) {
                                    if(Math.abs(t-nums[i-1]) <= K && Math.abs(t - nums[i+1]) <= K) {
                                        swap(i, j);
                                        answer++;
                                        isSwapped = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                //b 기준
                if(!isSwapped) {
                    s = b;
                    for (int j = i+2; j < nums.length; j++) {
                        int t = nums[j];
                        if(s != t) {
                            if(i + 1 == nums.length - 1) {
                                if(j == 0){
                                    if(Math.abs(s - nums[j+1]) <= K) {
                                        if(Math.abs(t-nums[i]) <= K) {
                                            swap(i+1, j);
                                            answer++;
                                            isSwapped = true;
                                            break;
                                        }
                                    }
                                }
                                else{
                                    if(Math.abs(s-nums[j-1]) <= K && Math.abs(s - nums[j+1]) <= K) {
                                        if(Math.abs(t-nums[i]) <= K) {
                                            swap(i+1, j);
                                            answer++;
                                            isSwapped = true;
                                            break;
                                        }
                                    }
                                }
                            } else {
                                if(j == 0){
                                    if(Math.abs(s - nums[j+1]) <= K) {
                                        if(Math.abs(t-nums[i]) <= K && Math.abs(t - nums[i+2]) <= K) {
                                            swap(i+1, j);
                                            answer++;
                                            isSwapped = true;
                                            break;
                                        }
                                    }
                                }else {
                                    if(Math.abs(s-nums[j-1]) <= K && Math.abs(s - nums[j+1]) <= K) {
                                        if(Math.abs(t-nums[i]) <= K && Math.abs(t - nums[i+2]) <= K) {
                                            swap(i+1, j);
                                            answer++;
                                            isSwapped = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if(!isSwapped) {
                    answer = -1;
                    break;
                }
            }

        }

        return answer;
    }

    public static void swap(int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
