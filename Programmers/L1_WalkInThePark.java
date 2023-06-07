package Programmers;

import java.util.*;

/*
 * Description
지나다니는 길을 'O', 장애물을 'X'로 나타낸 직사각형 격자 모양의 공원에서 로봇 강아지가 산책을 하려합니다. 산책은 로봇 강아지에 미리 입력된 명령에 따라 진행하며, 명령은 다음과 같은 형식으로 주어집니다.

["방향 거리", "방향 거리" … ]
예를 들어 "E 5"는 로봇 강아지가 현재 위치에서 동쪽으로 5칸 이동했다는 의미입니다. 로봇 강아지는 명령을 수행하기 전에 다음 두 가지를 먼저 확인합니다.

주어진 방향으로 이동할 때 공원을 벗어나는지 확인합니다.
주어진 방향으로 이동 중 장애물을 만나는지 확인합니다.
위 두 가지중 어느 하나라도 해당된다면, 로봇 강아지는 해당 명령을 무시하고 다음 명령을 수행합니다.
공원의 가로 길이가 W, 세로 길이가 H라고 할 때, 공원의 좌측 상단의 좌표는 (0, 0), 우측 하단의 좌표는 (H - 1, W - 1) 입니다.

공원을 나타내는 문자열 배열 park, 로봇 강아지가 수행할 명령이 담긴 문자열 배열 routes가 매개변수로 주어질 때,
로봇 강아지가 모든 명령을 수행 후 놓인 위치를 [세로 방향 좌표, 가로 방향 좌표] 순으로 배열에 담아 return 하도록
solution 함수를 완성해주세요.
 */
public class L1_WalkInThePark {

    public static int[] solution(String[] park, String[] routes) {

        char[][] map = new char[park.length][park[0].length()];
        int rowIndex = -1;
        int columnIndex = -1;

        // char[][] 배열에 공원 정보 입력
        for (int i = 0; i < park.length; ++i) {
            for (int j = 0; j < park[i].length(); ++j) {
                map[i][j] = park[i].charAt(j);

                if (park[i].charAt(j) == 'S') {
                    rowIndex = i;
                    columnIndex = j;
                    // System.out.printf("초기: [%d][%d]\n", i, j);
                }
            }
        }

        // 이동 시작
        for (String route : routes) {
            boolean isPossible = true; // 이동 가능 여부를 판단하는 플래그
            char direction = route.charAt(0); // 방향
            int dist = Integer.parseInt(route.split(" ")[1]); // 이동하려는 거리

            if (direction == 'E') {
                if (columnIndex + dist >= map[rowIndex].length) {
                    continue;
                }
                for (int i = columnIndex; i <= columnIndex + dist; ++i) {
                    if (map[rowIndex][i] == 'X') {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    columnIndex += dist;
                    // System.out.printf("이동 성공, [%d][%d]", rowIndex, columnIndex);
                }
            }

            if (direction == 'W') {
                if (columnIndex - dist < 0) {
                    continue;
                }
                for (int i = columnIndex; i >= columnIndex - dist; --i) {
                    if (map[rowIndex][i] == 'X') {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    columnIndex -= dist;
                    // System.out.printf("이동 성공, [%d][%d]\n", rowIndex, columnIndex);
                }
            }

            if (direction == 'S') {
                if (rowIndex + dist >= map.length) {
                    continue;
                }
                for (int i = rowIndex; i <= rowIndex + dist; ++i) {
                    if (map[i][columnIndex] == 'X') {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    rowIndex += dist;
                    // System.out.printf("이동 성공, [%d][%d]", rowIndex, columnIndex);
                }
            }

            if (direction == 'N') {
                if (rowIndex - dist < 0) {
                    continue;
                }
                for (int i = rowIndex; i >= rowIndex - dist; --i) {
                    if (map[i][columnIndex] == 'X') {
                        isPossible = false;
                        break;
                    }
                }
                if (isPossible) {
                    rowIndex -= dist;
                    // System.out.printf("이동 성공, [%d][%d]", rowIndex, columnIndex);
                }
            }
        }

        return new int[]{rowIndex, columnIndex};
    }


    public static void main(String[] args) {
        String[] park1 = {"SOO", "OOO", "OOO"};
        String[] park2 = {"SOO", "OXX", "OOO"};
        String[] park3 = {"OSO", "OOO", "OXO", "OOO"};

        String[] routes1 = {"E 2", "S 2", "W 1"};
        String[] routes2 = {"E 2", "S 2", "W 1"};
        String[] routes3 = {"E 2", "S 3", "W 1"};

        System.out.println("결과 1: " + Arrays.toString(solution(park1, routes1)));
        System.out.println("결과 2: " + Arrays.toString(solution(park2, routes2)));
        System.out.println("결과 3: " + Arrays.toString(solution(park3, routes3)));
    }
}
