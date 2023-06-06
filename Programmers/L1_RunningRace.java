package Programmers;


import java.util.*;

/**
 * Description
 * 얀에서는 매년 달리기 경주가 열립니다.
 * 해설진들은 선수들이 자기 바로 앞의 선수를 추월할 때 추월한 선수의 이름을 부릅니다.
 * 예를 들어 1등부터 3등까지 "mumu", "soe", "poe" 선수들이 순서대로 달리고 있을 때,
 * 해설진이 "soe"선수를 불렀다면 2등인 "soe" 선수가 1등인 "mumu" 선수를 추월했다는 것입니다.
 * 즉 "soe" 선수가 1등, "mumu" 선수가 2등으로 바뀝니다.
 *
 * 선수들의 이름이 1등부터 현재 등수 순서대로 담긴 문자열 배열 players와
 * 해설진이 부른 이름을 담은 문자열 배열 callings가 매개변수로 주어질 때,
 * 경주가 끝났을 때 선수들의 이름을 1등부터 등수 순서대로 배열에 담아 return 하는 solution 함수를 완성해주세요.
 */
public class L1_RunningRace {

    /**
     * case 1) LinkedList 와 HashMap 이용 - 시간복잡도 O(N^2)
     */
    public static String[] solution1(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        HashMap<String, Integer> map = new HashMap<>(); // 이름, 현재 등수
        LinkedList<String> list = new LinkedList<>(); // 현재 등수대로 이름 저장

        // map, list 초기화
        for (int i = 0; i < players.length; ++i) {
            map.put(players[i], i);
            list.add(players[i]);
        }

        // 이동 카운팅
        for (String name : callings) {
            int callRanking = map.get(name); // 호명된 선수의 추월 전 등수
            int frontRanking = callRanking - 1; // 호명된 선수의 앞 선수의 등수
            String frontName = list.get(frontRanking);

            list.set(frontRanking, name);
            list.set(callRanking, frontName);
            map.replace(name, frontRanking);
            map.replace(frontName, callRanking);
        }

        return list.toArray(new String[0]);
    }

    /**
     * case 2) HashMap 두 개 이용 - 시간복잡도 O(N)
     */
    public static String[] solution2(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        HashMap<String, Integer> mappedByName = new HashMap<>(); // 이름, 순위
        HashMap<Integer, String> mappedByOrder = new HashMap<>(); // 순위, 이름

        // map 초기화
        for (int i = 0; i < players.length; ++i) {
            mappedByName.put(players[i], i);
            mappedByOrder.put(i, players[i]);
        }

        // 이동 카운팅
        for (String name : callings) {
            int callRanking = mappedByName.get(name); // 호명된 선수의 추월 전 등수
            int frontRanking = callRanking - 1; // 호명된 선수의 앞 선수의 등수
            String frontName = mappedByOrder.get(frontRanking); // 앞 선수의 이름

            mappedByName.replace(name, frontRanking);
            mappedByName.replace(frontName, callRanking);
            mappedByOrder.replace(frontRanking, name);
            mappedByOrder.replace(callRanking, frontName);
        }

        // 순위에 따라 array 로 만들기
        LinkedList<Map.Entry<Integer, String>> entryList = new LinkedList<>(mappedByOrder.entrySet());
        entryList.sort(Map.Entry.comparingByKey());
        for (int i = 0; i < entryList.size(); ++i) {
            answer[i] = entryList.get(i).getValue();
        }
        return answer;
    }

    /**
     * case 3) HashMap 1개와 파라미터로 주어진 players 배열을 저장소로 활용
     * 시간복잡도 O(N)
     */
    public static String[] solution3(String[] players, String[] callings) {

        int n = players.length;
        HashMap<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.put(players[i], i);
        }

        for (String calling : callings) {
            int idx = indexMap.get(calling);
            if (idx > 0) {
                String temp = players[idx - 1];
                players[idx - 1] = players[idx];
                players[idx] = temp;
                indexMap.put(players[idx - 1], idx - 1);
                indexMap.put(players[idx], idx);
            }
        }

        return players;
    }

    public static void main(String[] args) {

        String[] players = {"mumu", "soe", "poe", "kai", "mine"};
        String[] callings = {"kai", "kai", "mine", "mine"};
        // 예상 결과 = ["mumu", "kai", "mine", "soe", "poe"]
        String[] result1 = solution1(players, callings);
        String[] result2 = solution2(players, callings);
        String[] result3 = solution3(players, callings);

        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.toString(result3));
    }
}
