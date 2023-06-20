package midterm;

;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
        static int N, x, y, x_, y_;
        static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        static int[] dy = {0, 0, -1, 1};
        static int[][] board;
        static boolean[][] visited;

        public static void main(String[] args) throws IOException {
                readFile();
                if(move(x,y)){
                        System.out.println("Yes");
                }else{
                        System.out.println("No");
                }
        }

        static void readFile() throws IOException{
                //경로를 수정해야 합니다.
                String filePath = "/Users/guhanseo/study/algorithm/src/input.txt (2주차 미로)"; //경로
                Scanner sc = new Scanner(new File(filePath));

                N = sc.nextInt();
                board = new int[N][N];
                visited = new boolean[N][N];

                for(int i = 0; i < N; i++){
                        for(int j = 0; j < N; j++){
                                board[i][j] = sc.nextInt();
                        }
                }

                x = sc.nextInt();
                y = sc.nextInt();
                x_ = sc.nextInt();
                y_ = sc.nextInt();
        }

        static boolean move(int sx, int sy) {
//        System.out.printf("(%d, %d)\n", sx, sy);
                //목표지점 도달시 정지
                if (sx == x_ && sy == y_) {
                        return true;
                }
                for (int i = 0; i < 4; i++) {
                        int nx = sx;
                        int ny = sy;
                        int cnt = 0; // 현재 방향에 한칸짜리 말이 존재하는가 ?
                        while(true){
                                nx += dx[i];
                                ny += dy[i];
                                if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 보드를 넘어가지 않는지 체크
                                        break; // while 문 빠져 나가고 다른 방향에 대해서 다시 한칸씩 검사
                                }
                                if (board[nx][ny] == 1) {
                                        cnt++;
                                        continue;
                                }
                                if (cnt == 1 && !visited[nx][ny]) {
                                        visited[nx][ny] = true; //이 cell이 있는 방향으론 되돌아가면 안되므로 방문 표시
                                        if(move(nx, ny)) return true;
                                }
                        }
                }
                return false;
        }
}