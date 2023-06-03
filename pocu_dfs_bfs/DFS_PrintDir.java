package pocu_dfs_bfs;

import java.io.File;

/**
 * dfs 를 이용해 디렉토리 구조를 콘솔에 출력해보자
 */
public class DFS_PrintDir {

    public static final int INDENT_LENGTH = 2; // 들여쓰기용 스페이스 길이

    /**
     * dfs 를 이용해 파일 이름을 콘솔에 출력하는 재귀 함수
     * @param file 방문하려는 파일
     * @param depth 현재 방문 중인 파일의 깊이
     */
    public static void printDirRecursive(File file, int depth) {
        if(!file.exists())
            return;

        // 파일이름에 깊이만큼의 들여쓰기를 적용한 뒤 출력
        String msg = padLeft(file.getName(), depth * INDENT_LENGTH);
        System.out.println(msg);

        // 파일이 디렉토리라면 하위 파일에 대해서 재귀 호출
        if(file.isDirectory()) {
            File[] lowerFiles = file.listFiles();
            for (File f : lowerFiles) {
                printDirRecursive(f, depth + 1);
            }
        }
    }

    // 문자열 왼쪽에 패딩을 추가해서 리턴해주는 메서드
    public static String padLeft(String str, int padSize) {
        for (int i = 0; i < padSize; i++) {
            str = " " + str;
        }
        return str;
    }

    public static void main(String[] args) {
        File file = new File("C:\\MyJava\\IdeaProject\\mw_algorithm\\out");

        printDirRecursive(file, 0);
    }
}
