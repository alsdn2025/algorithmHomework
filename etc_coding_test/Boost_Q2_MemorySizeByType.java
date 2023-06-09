package etc_coding_test;
import java.util.*;

/**
 * 출제의도:
 * 모든 언어에서 공통적으로 사용하는 함수와 배열 정도를 다룰 수 있으면서도 요구사항을 만족하기 위한
 * 흐름 제어와 예외적인 조건을 처리할 수 있어야 합니다.
 *
 * 여러분들은 새로운 컴퓨터를 만들고 8바이트 단위로 관리하는 타입별 메모리 관리 방식을 시뮬레이션 하려고 합니다.
 * 지원하는 타입별 크기는 BOOL = 1, SHORT = 2, FLOAT = 4, INT = 8, LONG = 16 입니다.
 * 매개변수 param() 에 타입들을 입력한 순서대로 메모리를 할당한 결과를 8바이트 단위로 구분해서 return 해주는
 * solution 함수를 작성하세요.
 * 단, BOOL 을 제외한 8바이트보다 작은 타입들이 연속될 경우에는 사이에 패딩(.)을 붙여야 합니다. SHORT 은 2배수,
 * FLOAT 는 4배수가 되도록 메모리를 할당해야 합니다.
 */
public class Boost_Q2_MemorySizeByType {
    final static int MAX_MEMORY_LIMIT = 128;

    public static int getByteSize(String str) {
        switch(str) {
            case "BOOL" :
                return 1;
            case "SHORT" :
                return 2;
            case "FLOAT" :
                return 4;
            case "INT" :
                return 8;
            case "LONG" :
                return 16;
            default :
                System.out.println("can not get byte size of " + str );
                return -1;
        }
    }

    public static String solution(String[] param) {
        StringBuilder sbResult = new StringBuilder(); // 결과를 담을 sb
        StringBuilder sb = new StringBuilder(); // 8바이트씩 문자열을 만들 sb

        for (String str : param) {
            int byteSize = getByteSize(str); // 이번 str 의 사이즈

            // SHORT: 현재 바이트 주소가 2의 배수가 될때까지 '.'을 추가한다.
            if (byteSize == 2) {
                while(sb.length() % 2 != 0) {
                    sb.append(".");
                }
            }

            // LONG: 현재 바이트 주소가 4의 배수가 될때까지 '.'을 추가한다.
            if (byteSize == 4) {
                while(sb.length() % 4 != 0) {
                    sb.append(".");
                }
            }

            // 현재 단위공간의 바이트가 8바이트를 넘어갈 경우
            if (sb.length() + byteSize > 8) {
                // 우선 나머지를 .으로 채우고 result 에 넣는다.
                for (int i = sb.length(); i < 8; ++i) {
                    sb.append(".");
                }
                sbResult.append(sb).append(",");
                sb.setLength(0);

                // 그 뒤 넣어야 하는 바이트만큼 현재 단위에 채워주고 다음 루프로 넘어간다.
                // 현재 단위의 바이트가 8바이트를 넘어가면 result 에 넣는다.
                while (byteSize > 0) {
                    sb.append("#");
                    byteSize--;
                    if (sb.length() == 8) {
                        sbResult.append(sb).append(",");
                        sb.setLength(0);
                    }
                }
                continue;
            }

            // 남은 공간에 바이트수만큼 넣을 수 있으므로, 바이트수만큼 넣어준다.
            for (int i = 0; i < byteSize; ++i) {
                sb.append("#");
                if (sb.length() == 8) {
                    sbResult.append(sb).append(",");
                    sb.setLength(0);
                }
            }
        }

        // 마지막 행을 .으로 채우고 result 에 넣는다.
        if (sb.length() != 0) {
            for (int i = sb.length(); i < 8; ++i) {
                sb.append(".");
            }
            sbResult.append(sb).append(",");
        }

        // result 의 바이트수를 검사한다.
        String str = sbResult.toString().replace(",", "");
        if (str.length() > MAX_MEMORY_LIMIT) {
            return "HALT";
        }

        // result 의 마지막 문자 ','을 제거한다.
        sbResult.deleteCharAt(sbResult.length() - 1);

        return sbResult.toString();
    }

    public static void main(String[] args) {
        String[] arr1 = {"INT", "INT", "BOOL", "SHORT", "LONG"};
        String[] arr2 = {"INT", "SHORT", "FLOAT", "INT", "BOOL"};
        String[] arr3 = {"FLOAT", "SHORT", "BOOL", "BOOL", "BOOL", "INT"};
        String[] arr4 = {"BOOL", "LONG", "SHORT", "LONG", "BOOL", "LONG", "BOOL", "LONG", "SHORT", "LONG", "LONG"};

        System.out.println(Arrays.toString(arr1));
        System.out.println(solution(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(solution(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(solution(arr3));
        System.out.println(Arrays.toString(arr4));
        System.out.println(solution(arr4));
    }
}