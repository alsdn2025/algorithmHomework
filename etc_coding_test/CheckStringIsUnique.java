package etc_coding_test;

import java.util.HashSet;

/**
 * 문자열이 주어졌을 때, 해당 문자열을 이루는 문자들이 유일한지(isUnique)를 판단한다.
 */
public class CheckStringIsUnique {
    public static void main(String[] args) {
        String str1 = "ABCDE";
        String str2 = "ABCDEA";
        String str3 = "가나다라마바사가";
        String str4 = "abcde";
        String str5 = "abcdee";

        System.out.println(isUniqueASCII(str1));
        System.out.println(isUniqueASCII(str2));
        System.out.println(isUniqueUNICODE(str3));
        System.out.println(isUniqueLowerCase(str4));
        System.out.println(isUniqueLowerCase(str5));
    }

    /**
     * 1. 문자가 ASCII 만을 사용한다고 가정, boolean 배열 사용
      */
    public static boolean isUniqueASCII (String str) {
        if (str.length() > 128) {
            // ASCII 의 개수는 128개이므로 문자열의 길이가 128보다 길다면 중복값이 있다
            return false;
        }

        boolean[] char_set = new boolean[128]; // 128개의 ASCII 사용 여부를 나타낼 배열

        // 중복되는 ASCII 가 있는지 검사
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if(char_set[c]) {
                return false;
            }
            char_set[c] = true;
        }
        return true;
    }

    /**
     * 2. 문자가 UNICODE 를 사용한다고 가정, set 사용
     */
    public static boolean isUniqueUNICODE(String str) {
        HashSet<Character> set = new HashSet();

        for (char c : str.toCharArray()) {
            if (set.contains(c)) {
                return false;
            }
            set.add(c);
        }

        return true;
    }

    /**
     * 3. 문자가 a~z, 알파벳 소문자만 사용한다고 가정, bit operator 사용
     */
    public static boolean isUniqueLowerCase(String str) {
        int checker = 0; // bit 플래그로 사용할 integer

        for (int i = 0; i < str.length(); ++i) {
            int val = str.charAt(i) - 'a'; // 0 ~ 27
            if ((checker & (1 << val)) > 0 ) {
                return false;
            }
            checker |= (1 << val); // 플래그를 올려준다.
        }

        return true;
    }
}
