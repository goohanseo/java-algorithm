package 자료구조;

/*
01 단계 문제 분석하기
최댓값 먼저 찾고 M
모든 점수를 점수/M*100으로 변환
N <= 1000
double이면서 N개 중 0이 아닌거 하나 나와야 됨.
N의 합 *100 / M / N

02 단계 손으로 풀어보기
1. 점수를 일차원 배열에 저장
2. 배열의 최고 점수와 총합을 구함
3. 다시 계산한 점수의 평균값을 출력

03 단계 슈도코드 작성하기
변수 N에 과목의 수 입력받기
길이가 N인 1차원 배열 A[] 선언하기
for(A[] 길이만큼 반복하기){
    A[i]에 각 점수 저장하기
}
for(A[] 길이만큼 반복하기){
    최고점은 변수 max에, 총점은 변수 sum에 저장하기
}
sum * 100 / max / N 출력하기
04단계 코드 구현하기
 */

import java.util.Scanner;

public class 평균구하기_1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int A[] = new int[N];
        for (int i = 0; i < N; i++){
            A[i] = sc.nextInt();
        }
        long sum = 0;
        long max = 0;
        for (int i = 0; i < N; i++){
            if (A[i] > max){
                max = A[i];
            }
            sum = sum + A[i];
        }
        System.out.println(sum * 100.0 / max / N);
    }
}
