package week12;

import java.util.Scanner;

public class Prob1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("양의 정수 k를 입력하세요: ");
        int k = scanner.nextInt();

        if (k <= 200000) {
            int min_count = count(k);
            System.out.println("서로 다른 경우의 수: " + min_count);
        } else {
            System.out.println("k는 30보다 작거나 같아야 합니다.");
        }
    }

    private static int count(int k) {
        int[] min = new int[k + 1];
        min[1] = 1; // 0을 1로 만들기 위해 필요한 최소 연산 횟수는 1번입니다.

        for (int i = 2; i <= k; i++) {
            min[i] = min[i - 1] + 1; // 1을 더하는 연산
            if (i % 2 == 0)
                min[i] = Math.min(min[i], min[i / 2] + 1); // 2를 곱하는 연산
        }

        return min[k];
    }
}
