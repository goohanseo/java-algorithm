package week12;

import java.util.Scanner;

public class Prob3 {
    public static int getMaxPrice(int[] prices, int n) {
        int[] max = new int[n + 1];
        max[0] = 0;

        for (int i = 1; i <= n; i++) {
            int maxPrice = 0;
            for (int j = 1; j <= i; j++) {
                maxPrice = Math.max(maxPrice, prices[j] + max[i - j]);
            }
            max[i] = maxPrice;
        }

        return max[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("막대기의 길이 N을 입력하세요: ");
        int n = scanner.nextInt();

        int[] prices = new int[n + 1];
        System.out.println("막대기 조각의 가격을 입력하세요:");
        for (int i = 1; i <= n; i++) {
            prices[i] = scanner.nextInt();
        }

        int maxPrice = getMaxPrice(prices, n);
        System.out.println("max: " + maxPrice);
    }
}
