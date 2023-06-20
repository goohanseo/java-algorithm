package finterm.dynamicprogramming;

import java.util.Scanner;

public class nm_column {
    static int testCase, n, m;
    static int[][] arr = new int[20][20];
    static int[][] dp = new int[20][20];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //testCase 입력
        testCase = sc.nextInt();
        for(int tc = 0; tc < testCase; tc++){
            //금광 정보 입력
            n = sc.nextInt();
            m = sc.nextInt();
            for (int i = 0; i < n; i++){
                for (int j = 0; j < m; j++){
                    arr[i][j] = sc.nextInt();
                }
            }
            //다이나믹 프로그래밍을 위한 2차원 DP  테이블 초기화
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; i++){
                    dp[i][j] = arr[i][j];
                }
            }
            //다이나믹 프로그래밍 진행
            for(int j = 1; j < m; j++){
                for(int i = 1; i < n; n++){
                    int leftUp, leftDown, left;
                    if(i == 0)
                        leftUp = 0;
                    else
                        leftUp = dp[i-1][j-1];
                    if (i == n -1)
                        leftDown = 0;
                    else
                        leftDown = dp[i + 1][j - 1];
                    left = dp[i][j-1];
                    dp[i][j] = dp[i][j] + Math.max(left,Math.max(leftDown,leftUp));
                }
            }
            int result = 0;
            for (int i = 0; i < n; i++){
                result = Math.max(result, dp[i][m -1]);
            }
            System.out.println(result);
        }
    }
}
