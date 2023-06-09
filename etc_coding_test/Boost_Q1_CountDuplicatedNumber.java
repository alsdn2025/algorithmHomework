package etc_coding_test;/* Online Java Compiler and Editor */

import java.util.*;

/**
 * Q. 자연수가 들어있는 배열 arr가 매개변수로 주어집니다. 배열 arr 안의 숫자들 중에서 앞에 있는 숫자들부터
 * 뒤에 중복되어 나타나는 숫자들 중복 횟수를 계산해서 배열로 return 하도록 solution 함수를 완성해주세요.
 * 만약 중복되는 숫자가 없다면 배열에 -1을 채워서 return 하세요.
 *
 * 입출력 예 #1
 * arr = {1, 2, 3, 3, 3, 3, 4, 4,} 에서 3은 4번, 4는 2번 나타나므로 [4, 2]를 반환합니다.
 */
public class Boost_Q1_CountDuplicatedNumber {

    /**
     * v1: 입력된 숫자들 중, 중복되는 숫자의 개수를 int 형 변수에 저장
     * ex: input={1, 3, 5, 1, 1, 4, 3} -> cnt = 2개( 1, 3 )
     */
	public static int[] solution_v1(int[] arr) {
        // <숫자, 중복 횟수> 저장용 map
		HashMap<Integer, Integer> map = new HashMap<>(); 
		// 순서 저장용 list
		LinkedList<Integer> list = new LinkedList<>();
        int cnt = 0; // 중복수의 개수를 세는 변수

        for (int num : arr) {
            // list는 등장 순서만 저장, 중복은 저장 x 
            if (!list.contains(num)) {
                list.add(num);
            }
            
            // map은 숫자의 중복 횟수를 저장
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.replace(num, map.get(num) + 1);

                if (map.get(num) == 2) {
                    cnt++; // 중복수 카운트
                }
            }
        }
        
        // 중복수가 없을 경우 -1 반환
        if (cnt == 0) {
            return new int[]{-1};
        }
        
        int index = 0; 
        int[] result = new int[cnt];
        for (int n = 0; n < list.size(); n++) {
            if (map.get(list.get(n)) > 1) {
                result[index++] = map.get(list.get(n)); 
            }
        }
        
		return result;
	}

    /**
     * v2 : 중복수의 갯수를 저장하기 위해 Set 자료형을 사용
     */
    public static int[] solution_v2(int[] arr) {
        // <숫자, 중복 횟수> 저장용 map
        HashMap<Integer, Integer> map = new HashMap<>();
        // 순서 저장용 list
        LinkedList<Integer> list = new LinkedList<>();
        // 중복수의 갯수를 저장하기 위한 Set
        HashSet<Integer> set = new HashSet<>();

        for (int num : arr) {
            // list는 등장 순서만 저장, 중복은 저장 x
            if (!list.contains(num)) {
                list.add(num);
            }

            // map은 숫자의 중복 횟수를 저장
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.replace(num, map.get(num) + 1);
                set.add(num);
            }
        }

        // 중복수가 없을 경우 -1 반환
        if (set.isEmpty()) {
            return new int[]{-1};
        }

        int index = 0;
        int[] result = new int[set.size()];
        for (int n = 0; n < list.size(); n++) {
            if (map.get(list.get(n)) > 1) {
                result[index++] = map.get(list.get(n));
            }
        }

        return result;
    }

	public static void main(String[] args) {
		int[] arr1 = new int[] {1, 2, 3, 3, 3, 3, 4, 4};
		int[] arr2 = new int[] {3, 2, 4, 4, 2, 5, 2, 5, 5};
		int[] arr3 = new int[] {3, 5, 7, 9, 1};
		int[] arr4 = new int[] {3, 2, 4, 4, 2, 3, 3, 3, 4, 4};
		 
		
        System.out.println("print results:");
        int[] result1 = solution_v2(arr1);
        int[] result2 = solution_v2(arr2);
        int[] result3 = solution_v2(arr3);
        int[] result4 = solution_v2(arr4);
        
        for (int i = 0; i < result1.length; ++i) {
            System.out.print(result1[i] + " ");
        }
		System.out.println();
        
        for (int i = 0; i < result2.length; ++i) {
            System.out.print(result2[i] + " ");
        }
		System.out.println();
		
        for (int i = 0; i < result3.length; ++i) {
            System.out.print(result3[i] + " ");
        }
		System.out.println();
		
        for (int i = 0; i < result4.length; ++i) {
            System.out.print(result4[i] + " ");
        }
		System.out.println();
		
	}
}