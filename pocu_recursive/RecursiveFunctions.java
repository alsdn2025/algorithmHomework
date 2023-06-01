package pocu_recursive;

import java.util.Random;

import static pocu_recursive.TailRecursiveFunctions.*;

public class RecursiveFunctions {

    // fibonacci
    public static int fibo(int index) {
        if(index <= 1) {
            return index;
        }

        // Fi = Fi-1 + Fi-2 ( if i >= 2 )
        return fibo(index-1) + fibo(index -2);
    }

    // factorial
    public static int factorial(int n) {
        if(n <= 1) {
            return 1;
        }

        return n * factorial(n-1);
    }

    public static int factorialWithLoop(int n) {
        if(n <= 1)
            return 1;

        int acc = 1;
        for (int i = n; i > 0; --i) {
            acc *= i;
        }

        return acc;
    }

    // accumulate
    public static int accumulate(int n) {
        if(n <= 1)
            return n;

        return n + accumulate(n);
    }

    public static int accWithLoop(int n) {
        if(n <= 1)
            return n;

        int acc = 0;
        for (int i = n; i > 0; --i) {
            acc += i;
        }

        return acc;
    }



    public static void main(String[] args) {
        // factorial
        System.out.println(factorial(10));
        System.out.println(factorialTailCall(10));
        System.out.println(factorialWithLoop(10));

        // fibonacci
        System.out.println(fiboTailCall(7));
        System.out.println(fibo(7));

        // accumulate
        System.out.println(accTailCall(100));
        System.out.println(accWithLoop(100));

    }

}
