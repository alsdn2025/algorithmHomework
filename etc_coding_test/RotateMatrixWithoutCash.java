package etc_coding_test;

import java.util.Random;

/**
 * 문제: int 형 2차원 행렬로 이루어진 이미지 데이터가 주어졌을 때, 이미지를 오른쪽으로 90도 회전시키는 프로그램을 작성하라.
 * 제한 사항: 추가적인 캐시 메모리 ( 배열, 리스트, 해시 테이블 등 자료구조 ) 의 사용을 금한다.
 */
public class RotateMatrixWithoutCash {

    public static void rotateMatrix(int[][] img) {
        // 행렬의 바깥 테두리부터 한 라인씩 접근한다.
        int s = 0;
        int e = img.length - 1;
        while(e - s > 0) {
            rotate(img, s, e);
            s++;
            e--;
        }
    }

    public static void rotate(int[][] img, int s, int e) {
        for (int i = s, j = e; i < e; ++i, --j) {
                int temp = img[s][i];
                img[s][i] = img[i][e];
                img[i][e] = img[e][j];
                img[e][j] = img[j][s];
                img[j][s] = temp;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int n = 4;
        int[][] img = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                img[i][j] = random.nextInt(256);
            }
        }

        System.out.println("회전 이전:");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("[%3d]", img[i][j]);
            }
            System.out.println();
        }


        System.out.println("회전 이후:");
        rotateMatrix(img);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                System.out.printf("[%3d]", img[i][j]);
            }
            System.out.println();
        }
    }
}
