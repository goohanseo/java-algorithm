package finterm.week11;


public class 그리드순환식 {
    public static long countPaths(int n) {
        long[][] dp = new long[n][n];

        // Initialize the first row and first column with 1
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }

        // Calculate the number of paths for each cell
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (j <= i) {
                    // If the cell is below or on the diagonal
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else {
                    // If the cell is above the diagonal
                    dp[i][j] = dp[i][i];
                }
            }
        }

        return dp[n-1][n-1];
    }

    public static void main(String[] args) {
        int n = 5; // Size of the grid
        long numPaths = countPaths(n);
        System.out.println("Number of different paths: " + numPaths);
    }
}
