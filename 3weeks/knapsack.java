//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//public class knapsack {
//    private static int[] w;
//    private static int[] v;
//    private static int N, W, MAX, weight,price;
//    private static boolean[] include;
//    private static int num;
//
//    public static void main(String[] args) throws IOException {
//        readFile();
//        num = N;
//        include = new boolean[num];
//        powerSet(0);
//        System.out.println(MAX);
//
//    }
//    public static void powerSet(int k){
//        weight =0;
//        price = 0;
//        if (k==num){
//            for (int i=0; i<num; i++){
//                if(include[i] == true) {
//                    weight += w[i];
//                    price += v[i];
//
//                }
//                if (weight > W){
//                    return;
//                }
//                if (price > MAX){
//                    MAX = price;
//                }
//            }
//            return;
//        }
//        include[k] = false;
//        powerSet(k+1);
//        include[k] = true;
//        powerSet(k+1);
//    }
//     static void readFile() throws IOException {
//        String filename = "/Users/guhanseo/study/algorithm/src/input(4번).txt";
//
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            // 첫 두 줄을 읽어 정수로 변환합니다.
//            N = Integer.parseInt(reader.readLine().trim());
//            W = Integer.parseInt(reader.readLine().trim());
//
//            // 나머지 줄을 읽어 배열로 변환합니다.
//            w = new int[N];
//            String[] tokens1 = reader.readLine().trim().split("\\s+");
//            for (int i = 0; i < N; i++) {
//                w[i] = Integer.parseInt(tokens1[i]);
//            }
//
//            v = new int[N];
//            String[] tokens2 = reader.readLine().trim().split("\\s+");
//            for (int i = 0; i < N; i++) {
//                v[i] = Integer.parseInt(tokens2[i]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        // n, w 배열을 사용하여 작업을 수행합니다.
//    }
//
//
//    // arr1, arr2 배열을 사용하여 작업을 수행합니다.
//}
//
//
//
//
//
//
//
//
