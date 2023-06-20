package finterm.dynamic2017;

import java.io.*;

public class SCSDynamic {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input the two strings
        String str1 = br.readLine().trim();
        String str2 = br.readLine().trim();

        // Calculate the length of the Shortest Common Supersequence (SCS)
        int length = SCSLength(str1, str2);

        // Print the Shortest Common Supersequence (SCS) and its length
        System.out.println("The Shortest Common Supersequence is: " + printSCS(str1, str2));
        System.out.println("The length of the Shortest Common Supersequence is: " + length);
    }

    // Function to find the length of the Shortest Common Supersequence (SCS)
    public static int SCSLength(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[m][n];
    }

    // Function to print the Shortest Common Supersequence (SCS)
    public static String printSCS(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (str1.charAt(i-1) == str2.charAt(j-1)) dp[i][j] = 1 + dp[i-1][j-1];
                else dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            if (str1.charAt(i-1) == str2.charAt(j-1)) {
                sb.append(str1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i][j-1] < dp[i-1][j]) {
                sb.append(str2.charAt(j-1));
                j--;
            } else {
                sb.append(str1.charAt(i-1));
                i--;
            }
        }

        while (i > 0) {
            sb.append(str1.charAt(i-1));
            i--;
        }

        while (j > 0) {
            sb.append(str2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }
}
