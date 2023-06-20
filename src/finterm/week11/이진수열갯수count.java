package finterm.week11;


public class 이진수열갯수count {
    public static int countNonConsecutiveZeros(String binaryString) {
        int count = 0;
        int consecutiveZeros = 0;

        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) == '0') {
                consecutiveZeros++;
            } else {
                if (consecutiveZeros > 1) {
                    count += consecutiveZeros - 1;
                }
                consecutiveZeros = 0;
            }
        }

        if (consecutiveZeros > 1) {
            count += consecutiveZeros - 1;
        }

        return count;
    }

    public static void main(String[] args) {
        String binaryString = "101010100011010";
        int nonConsecutiveZeros = countNonConsecutiveZeros(binaryString);
        System.out.println("Number of non-consecutive zeros: " + nonConsecutiveZeros);
    }
}
