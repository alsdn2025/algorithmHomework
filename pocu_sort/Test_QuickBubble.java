package pocu_sort;

import java.util.Random;

public class Test_QuickBubble {

    public static void quickSort(int[] data) {
        System.out.println("Test_QuickBubble.quickSort");
        quickSortRecursive(data, 0, data.length-1);
    }

    public static void quickSortRecursive(int[] data, int l, int pivot){

        if(l >= pivot)
            return;

        int lp = l; int progress = l;
        while(progress < pivot) {
            if(data[progress] < data[pivot]) {
                swap(data, progress, lp);
                lp++;
            }
            progress++;
        }

        swap(data, lp, pivot);

        quickSortRecursive(data, 0, lp - 1);
        quickSortRecursive(data, lp + 1, pivot);
    }

    public static void bubbleSort(int[] data) {

        System.out.println("Test_QuickBubble.bubbleSort");
        for (int i = data.length-1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if(data[j] > data[j+1]) {
                    swap(data, j, j+1);
                }
            }

        }

    }



    public static void swap(int[] data, int a, int b) {
        if(a == b) {
            return;
        }
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    public static int[] generateData(int length) {
        Random random = new Random();
        int data[] = new int[length];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt(32);
        }
        return data;
    }



    public static void main(String[] args) {
        int[] data = generateData(20);
        bubbleSort(data);
        System.out.println("버블 정렬 결과-----");
        printArray(data);

        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
        data = generateData(20);
        quickSort(data);
        System.out.println("퀵 정렬 결과-----");
        printArray(data);
    }
}
