package Programmers;

public class Printing {
    public static void main(String[] args) {
        String str = "aBcDeFg";

        // 문자를 하나씩 확인할 수밖에 없나?
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < chars.length; ++i) {
            if(Character.isUpperCase(chars[i])) {
                sb.append(Character.toLowerCase(chars[i]));
            } else {
                sb.append(Character.toUpperCase(chars[i]));
            }
        }

        str = sb.toString();
        System.out.println(str);
    }
}
