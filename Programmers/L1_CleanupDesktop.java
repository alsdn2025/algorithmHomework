package Programmers;

/**
 * Description
 * 드래그는 바탕화면의 격자점 S(lux, luy)를 마우스 왼쪽 버튼으로 클릭한 상태로
 * 격자점 E(rdx, rdy)로 이동한 뒤 마우스 왼쪽 버튼을 떼는 행동입니다.
 * 이때, "점 S에서 점 E로 드래그한다"고 표현하고 점 S와 점 E를 각각 드래그의 시작점, 끝점이라고 표현합니다.
 *
 * 점 S(lux, luy)에서 점 E(rdx, rdy)로 드래그를 할 때,
 * "드래그 한 거리"는 |rdx - lux| + |rdy - luy|로 정의합니다.
 *
 * 점 S에서 점 E로 드래그를 하면 바탕화면에서 두 격자점을 각각 왼쪽 위,
 * 오른쪽 아래로 하는 직사각형 내부에 있는 모든 파일이 선택됩니다.
 *
 * 머쓱이의 컴퓨터 바탕화면의 상태를 나타내는 문자열 배열 wallpaper가 매개변수로 주어질 때,
 * 바탕화면의 파일들을 한 번에 삭제하기 위해 최소한의 이동거리를 갖는
 * 드래그의 시작점과 끝점을 담은 정수 배열을 return하는 solution 함수를 작성해 주세요.
 * 드래그의 시작점이 (lux, luy), 끝점이 (rdx, rdy)라면 정수 배열 [lux, luy, rdx, rdy]를 return하면 됩니다.
 */
public class L1_CleanupDesktop {
    public static int[] solution(String[] wallpaper) {

        int leftFileIndex = wallpaper[0].length(); // 가장 왼쪽
        int rightFileIndex = -1; // 가장 오른쪽
        int topFileIndex = wallpaper.length; // 가장 위
        int downFileIndex = -1; // 가장 아래

        // 파일이 만드는 사각형의 가장자리에 속하는 index 들을 찾는다.
        for (int i = 0; i < wallpaper.length; ++i) {
            for (int j = 0; j < wallpaper[i].length(); ++j) {
                if (wallpaper[i].charAt(j) == '#') {
                    leftFileIndex = Math.min(leftFileIndex, j);
                    rightFileIndex = Math.max(rightFileIndex, j);
                    topFileIndex = Math.min(topFileIndex, i);
                    downFileIndex = Math.max(downFileIndex, i);
                }
            }
        }

        // 찾은 파일 index 를 기반으로 드래그 범위를 리턴한다
        return new int[]{topFileIndex, leftFileIndex, downFileIndex + 1, rightFileIndex + 1};
    }
}
