package pocu_recursive;

class GenericReverse<T> {
    public <T> T[] reverse(T[] input) {
        int len = input.length;
        for (int i = 0; i < len/2; i++) {
            T temp = input[i];
            input[i] = input[len-1-i];
            input[len-1-i] = temp;
        }
        return input;
    }
}

public class BruteForce {

    public static void main(String[] args) {
        GenericReverse<Character> charReverser = new GenericReverse<>();
        GenericReverse<Integer> intReverser = new GenericReverse<>();
        Character[] characters = new Character[]{'a','b','c','d','e'};
        Integer[] integers = new Integer[]{123,456,789,101112};

        String str = "abcdefghijk";
        Character[] result = charReverser.reverse(characters);
        Integer[] resultInteger = intReverser.reverse(integers);

        for (Character c : result) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (Integer integer : resultInteger) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
