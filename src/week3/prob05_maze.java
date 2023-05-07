//package week3;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Scanner;
//
//public class prob05_maze {
//    public static void main(String[] args) throws IOException {
//        readFile();
//    }
//
//
//
//    static int N;
//    static int board[][];
//    static int visited[][];
//    static void readFile() throws IOException{
//        //경로를 수정해야 합니다.
//        String filePath = "C:\\Users\\PKNU\\IdeaProjects\\java-algorithm\\src\\week3\\input(5번).txt"; //경로
//        Scanner sc = new Scanner(new File(filePath));
//
//        N = sc.nextInt();
//        board = new int[N][N];
//        visited = new int[N][N];
//
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                board[i][j] = sc.nextInt();
//            }
//        }
//
//        x = sc.nextInt();
//        y = sc.nextInt();
//        x_ = sc.nextInt();
//        y_ = sc.nextInt();
//    }
//}
////inorder 이진탐색트리
////RECURSION
