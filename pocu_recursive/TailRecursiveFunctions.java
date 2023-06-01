package pocu_recursive;

public class TailRecursiveFunctions {

    ////////////////////////////////////
    // Tail Call Function Example
    public static int tailCallEx(int data) {
        data = blahblah(data);

        return do_that(data); // tail call
    }
    public static int blahblah(int data) {
        return data;
    }
    public static int do_that(int data) {
        return data;
    }


    ////////////////////////////////////
    // Fibonacci tail recursive
    public static int fiboTailCall(int index) {
        if(index <= 1)
            return index;

        return fiboRecursive(index - 1, 1, 1);
    }

    public static int fiboRecursive(int index, int prev, int pres) {
        if(index <= 1)
            return pres;

        return fiboRecursive(index - 1, pres, prev + pres);
    }

    ////////////////////////////////////
    // Factorial tail recursive
    public static int factorialTailCall(int n) {
        if(n <= 1)
            return 1;

        return factorialRecursive(n, 1);
    }

    public static int factorialRecursive(int i, int acc) {
        if(i == 0)
            return acc;

        return factorialRecursive(i - 1, acc*i);
    }

    ////////////////////////////////////
    // Accumulate tail recursive
    public static int accTailCall(int n) {
        return accRecursive(n, 0);
    }

    public static int accRecursive(int n, int acc) {
        if(n == 0)
            return acc;

        return accRecursive(n - 1, acc + n);
    }
}