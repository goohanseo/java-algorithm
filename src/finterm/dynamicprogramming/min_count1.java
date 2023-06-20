package finterm.dynamicprogramming;

import java.util.Scanner;

public class min_count1 {
    //앞서 계산한 결과를 저장하기 위한 DP 테이블 초기화
    public static int[] d = new int [30001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        //dynamic bottom-up
        for(int i = 2; i <= x; i++){
            //현재의 수에서 1을 빼는 경우
            d[i] = d[i-1] + 1;
            //현재의 수가 2로 나누어 떨어지는 경우
            if(i%2 == 0)
                d[i] = Math.min(d[i], d[i/2]+1);
            //현재의 수가 3로 나누어 떨어지는 경우
            if(i%3 == 0)
                d[i] = Math.min(d[i], d[i/2]+1);
            //현재의 수가 5로 나누어 떨어지는 경우
            if(i%5 == 0)
                d[i] = Math.min(d[i], d[i/2]+1);
        }
        System.out.println(d[x]);
    }
}
