package etc_coding_test;

/**
 * 문제: 2차원 배열이 주어졌을 때, 0이 있는 행과 열의 원소를 모두 0으로 바꾸는 프로그램을 작성하라.
 * 제한사항: 입력 외의 추가적인 캐시 데이터 ( 배열, 리스트, 해쉬 테이블 등 )의 사용을 금한다.
 */
public class SetZeroToAllZero {

    public static void setZeroToAllZero(int[][] matrix) {
        int fRow = -1; // 처음으로 만난 0의 행
        int fColumn = -1; // 처음으로 만난 0의 열

        // 정보 행/열에 0의 인덱스 정보를 기록한다.
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] == 0) {
                    // 처음으로 만난 0이라면 fRow, fColumn 입력
                    if (fRow == -1) {
                        fRow = i;
                        fColumn = j;
                        continue;
                    }
                    // 이후 만난 0은 정보 행열(fRow/fColumn)에 정보를 입력
                    matrix[fRow][j] = 0;
                    matrix[i][fColumn] = 0;
                }
            }
        }

        // 정보 행에 기록된 인덱스에 해당하는 열들을 모두 0으로 바꾼다
        // 단, 정보 열은 제외한다
        for (int j = fColumn + 1; j < matrix[fRow].length; j++) {
            if (matrix[fRow][j] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 정보 열에 기록된 인덱스에 해당하는 행들을 모두 0으로 바꾼다
        for (int i = fRow; i < matrix.length; i++) {
            if (matrix[i][fColumn] == 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 정보 열을 모두 0으로 바꾼다
        for (int i = 0; i < matrix.length; ++i) {
            matrix[i][fRow] = 0;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                System.out.printf("[%d]", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1,1,1,1},
                {1,0,1,1},
                {1,1,1,1},
                {1,1,0,1}
        };

        int[][] matrix2 = new int[][]{
                {1,1,1,1,1},
                {1,0,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,0,1}
        };

        System.out.println("입력 1:");
        printMatrix(matrix1);
        setZeroToAllZero(matrix1);
        System.out.println("출력 1:");
        printMatrix(matrix1);

        System.out.println("입력 2:");
        printMatrix(matrix2);
        setZeroToAllZero(matrix2);
        System.out.println("출력 2:");
        printMatrix(matrix2);
    }
}
