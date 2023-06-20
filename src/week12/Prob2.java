package week12;

import java.util.Scanner;

public class Prob2 {
    public static int countSumCombinations(int k) {
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= 3; i++) {
            for (int j = i; j <= k; j++) {
                dp[j] += dp[j - i];
            }
        }

        return dp[k];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("양의 정수 k를 입력하세요: ");
        int k = scanner.nextInt();

        if (k <= 30) {
            int combinations = countSumCombinations(k);
            System.out.println("서로 다른 경우의 수: " + combinations);
        } else {
            System.out.println("k는 30보다 작거나 같아야 합니다.");
        }
    }
}