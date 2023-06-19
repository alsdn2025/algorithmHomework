package Programmers;

/**
 * 문제 설명
 * x축과 y축으로 이루어진 2차원 직교 좌표계에 중심이 원점인 서로 다른 크기의 원이 두 개 주어집니다.
 * 반지름을 나타내는 두 정수 r1, r2가 매개변수로 주어질 때,
 * 두 원 사이의 공간에 x 좌표와 y 좌표가 모두 정수인 점의 개수를 return 하도록 solution 함수를 완성해주세요.
 * ※ 각 원 위의 점도 포함하여 셉니다.
 */
public class L2_IntegerCoordinatesBetweenTwoCircles {

    // (y축의 점을 제외한 1사분면의 정수 개수) * 4 = 정답
    public long solution(int r1, int r2) {
        long cnt = 0;

        for (int x = 1; x <= r2; ++x) {
            int yMax = getYMax(r2, x);
            int yMin = getYMin(r1, x);

            cnt += (yMax - yMin) + 1;

            // 아래처럼 for 문으로 구할시 시간 제한 걸림
            // for (int y = yMax; y >= yMin; --y) {
            //     cnt++;
            // }
        }

        cnt *= 4;
        return cnt;
    }

    public static int getYMax(int r, int x) {
        return (int) Math.floor(Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2)));
    }

    public static int getYMin(int r, int x) {
        return (int) Math.ceil(Math.sqrt(Math.pow(r, 2) - Math.pow(x, 2)));
    }
}
