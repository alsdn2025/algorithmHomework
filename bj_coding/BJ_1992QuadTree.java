package bj_coding;

import java.util.Scanner;

public class BJ_1992QuadTree {

    public static void quadTreeCompressRecursive(int[][] img, int x, int y, int size, StringBuilder sb) {

        if(isPossibleToCompress(img, x, y, size)) {
            sb.append(img[y][x]);
            return;
        }

        // 압축이 불가능할 경우, 네 개로 분할 재귀
        size /= 2;
        sb.append("(");
        quadTreeCompressRecursive(img, x, y, size, sb);
        quadTreeCompressRecursive(img, x + size, y, size, sb);
        quadTreeCompressRecursive(img, x, y + size, size, sb);
        quadTreeCompressRecursive(img, x + size, y + size, size, sb);
        sb.append(")");
    }


    public static boolean isPossibleToCompress(int[][] img, int x, int y, int size) {
        int value = img[y][x];
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if(img[i][j] != value) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(sc.nextLine());

        int[][] img = new int[N][N];
        for(int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for(int j = 0; j < N; j++) {
                img[i][j] = line.charAt(j) - '0';
            }
        }

        // 제대로 저장되는지 확인
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                System.out.print(img[i][j] + " ");
            }
            System.out.println();
        }

        quadTreeCompressRecursive(img, 0, 0, N, sb);
        System.out.println(sb);

//        int[][] testImg2 = {
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0},
//                {0,0,0,1,1,1,0,0},
//                {0,0,0,1,1,1,0,0},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,1,1},
//                {1,1,1,1,0,0,1,1}
//        };

    }
}
