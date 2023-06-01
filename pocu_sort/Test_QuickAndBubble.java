package pocu_sort;

import java.util.Random;

public class Test_QuickAndBubble {

    public static void bubbleSort(int[] data) {
        System.out.println("Bubble Sort ------");
        printArray(data);

        for (int i = data.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if(data[j] > data[j+1]) {
                    swap(data, j, j+1);
                }
            }
        }
    }

    public static void quickSort(int[] data) {
        System.out.println("Quick Sort ------");
        printArray(data);
        quickRecursive(data, 0, data.length - 1);
    }

    public static void quickRecursive(int[] data, int l, int r) {
        if(l > r) {
            return;
        }

        int start = l;
        int pivot = data[r];
        for (int i = l; i < r; ++i) {
            if(data[i] < pivot) {
                swap(data, i, l++);
            }
        }

        // 최종확정된 left 에 pivot 을 놓기
        swap(data, l, r);

        quickRecursive(data, start, l - 1);
        quickRecursive(data, l + 1, r);
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
    }
}
