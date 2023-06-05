package etc_coding_test;
import java.util.*;

/**
 * 입력으로 N이 주어지면 N 이하의 정수 중 소수를 출력하는 소수 출력 프로그램을 작성하라.
 */
public class printPrimeNumber {

    public static void printPrimeNumber(int N) {
        ArrayList<Integer> primeList = new ArrayList<>();

        for(int i = 2; i <= N; i++) {
            if(isPrime(i))
                primeList.add(i);
        }
        for(int p : primeList) {
            System.out.print(p + " ");
        }
    }

    // n이 소수인지를 판별하는 메서드
    // 1보다 크고 입력 N의 제곱근보다 작은 숫자 i 중, n % i 인 i가 존재한다면 n은 소수가 아니다
    public static boolean isPrime(int n) {
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print("어떤 수보다 작은 소수들을 출력할까요? \n>");
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        printPrimeNumber(N);
    }
}
