package etc_coding_test;

/**
 * 문자열이 주어졌을 때, 문자열이 회문(palindrome)의 치환(permutation)인지 확인하는 프로그램을 작성하라.
 */
public class StringPalindromePermutation {

    /**
     * 방법 1. 문자가 ASCII 라고 가정. 128 사이즈 배열에 각 문자의 중복 회수를 카운팅
     */
    public static boolean isPalindromePermutation1(String str) {
        int[] check = new int[128];
        for (char c : str.toCharArray()) {
            check[c]++;
        }

        boolean foundOdd = false; // 홀수인 문자를 찾으면 true
        for (int i : check) {
            if (i % 2 != 0) {
                if (foundOdd) {
                    return false; // 두 번째로 홀수개인 문자가 나오면 false
                }
                foundOdd = true;
            }
        }

        return true;
    }


    /**
     * 방법 2. 문자가 a~z 의 알파벳 소문자라고 가정. 비트 연산자 사용
     */
    public static boolean isPalindromePermutation2(String str) {
        int bitVector = createBitVector(str);

        // 비트 플래그가 모두 off (모두 짝수개) 거나, 하나만 on (하나만 홀수개)라면 true
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    private static boolean checkExactlyOneBitSet(int bitVector) {
        // 딱 하나의 비트만 1일 경우, -1한 뒤 AND 연산을 하면 무조건 0이 나온다
        return (bitVector & (bitVector - 1)) == 0;
    }

    private static int createBitVector(String s) {
        int bitVector = 0;
        for (char c : s.toCharArray()) {
            int x = c - 'a';
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }

    /**
     * index 에 해당하는 숫자가 현재 짝수개라면 bit flag 를 켜고, 홀수개라면 끈다.
     */
    private static int toggle(int bitVector, int index) {
        if (index < 0) {
            return bitVector;
        }

        int mask = 1 << index;
        if ((bitVector & mask) == 0) { // 해당 index 가 현재 짝수개라면( 플래그가 off 라면 )
            bitVector |= mask; // Turn on the bit flag
        }else { // 해당 index 가 현재 홀수개라면 ( 플래그가 on 이라면 )
            bitVector &= ~mask; // Turn off the bit flag
        }

        return bitVector;
    }


    public static void main(String[] args) {
        String str1 = "aabcdedcbaa"; // 회문
        String str2 = "cbaaabcdaed"; // 회문의 치환형
        String str3 = "cbaaabcdaeda"; // 회문의 치환형 X
        System.out.println(isPalindromePermutation1(str2));
        System.out.println(isPalindromePermutation1(str3));

        System.out.println(isPalindromePermutation2(str2));
        System.out.println(isPalindromePermutation2(str3));
    }
}
