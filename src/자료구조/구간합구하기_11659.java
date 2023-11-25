package 자료구조;

    /*
01단계 문제 분석하기
최대 100,000번의 연산

02단계 손으로 풀어보기
배열을 받아오면서 구간합 계산해놓기

03단계 슈도코드 작성하기
수의 개수 N과 횟수 M 받아오기
A[N] 배열과 함께 S[N]값도 채우기
i, j 받아와서 S[j] - S[i-1] 구해서 출력하기
04단계 코드 구현하기
 */

/*
받아올 데이터가 많을 때는 버퍼로 받아오고 S[0] 주의해서 합배열 저장해야된다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기_11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer =
                new StringTokenizer(bufferedReader.readLine());
        int suNo = Integer.parseInt(stringTokenizer.nextToken());
        int quizNo = Integer.parseInt(stringTokenizer.nextToken());
        long[] S = new long[suNo + 1];
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= suNo; i++) {
            S[i] = S[i - 1] + Integer.parseInt(stringTokenizer.nextToken());
        }
        for (int q = 0; q < quizNo; q++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int i = Integer.parseInt(stringTokenizer.nextToken());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            System.out.println(S[j] - S[i - 1]);
        }
    }
}
