package 자료구조;


import java.util.Scanner;

/*
01단계 문제 분석하기
N의 범위가 100이므로 100자리 숫자는 int나 long으로 받을 수 없음
-> 숫자형이 아닌 문자열 형태로 받은 후 문자 배열로 변환하고 그 뒤 숫자형으로 변환하여 더해줘야 된다.

02단계 손으로 풀어보기
1. string sNum = 12341234;
2. sNum을 string 형을 char []형으로 변환
3. 인덱스 0부터 끝까지 배열 탐색해여 정수형 변환하고 결과값 누적
03단계 슈도코드 작성하기
N값 입력받기
N의 숫자 입력받아 String sNum에 저장
sNum을 다시 char [] 형 cNum에 변환하여 저장
int형 변수 sum 선언
for(cNum 길이만큼 반복하기){
    배열의 각 자리값을 정수형으로 변환하여 sum에 더하여 누적하기
}
sum 출력하기
04단계 코드 구현하기
 */
public class 숫자의합_11720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        String sNum = sc.next();
        char[] cNum = sNum.toCharArray();
        int sum = 0;
        for (int i =0; i < cNum.length; i++){
            sum += cNum[i] - '0';
        }
        System.out.println(sum);
    }
}

