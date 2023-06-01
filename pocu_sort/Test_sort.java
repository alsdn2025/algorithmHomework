package pocu_sort;

import java.util.*;

public class Test_sort {

    public static void swap(int[] data, int a, int b) {
        if(a == b) {
            return;
        }
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    public static int[] generateData(int length) {
        Random random = new Random();
        int data[] = new int[length];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt(20);
        }
        return data;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] arr) {
        for(int i = arr.length - 1; i > 0; --i) {
            for(int j = 0; j < i; ++j) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length - 1; ++i) {
            for(int j = i + 1; j < arr.length; ++j) {
                if(arr[i] > arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    public static void insertionSort(int[] arr) {

        for(int i = 1; i < arr.length; ++i) {
            int j = i;
            while(j > 0 && arr[j] < arr[j - 1]) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
                j--;
            }
        }

    }

    public static void quickSort(int[] arr) {
        quickSortRecursive(arr, 0, arr.length - 1);
    }

    public static void quickSortRecursive(int[] arr, int l, int r) {
        if(l > r)
            return;

        int lp = l; // left pointer
        for(int i = l; i < r; i++) {
            if(arr[i] < arr[r]) {
                swap(arr, lp, i);
                lp++;
            }
        }

        swap(arr, lp, r);
        quickSortRecursive(arr, l, lp - 1);
        quickSortRecursive(arr, lp + 1, r);
    }

    public static void mergeSort(int[] arr) {
        mergeSortRecursive(arr, 0, arr.length - 1);
    }

    public static void mergeSortRecursive(int[] arr, int l, int r) {
        if(l >= r)
            return;

        // 쪼갠 뒤 병합
        int p = (l + r) / 2;
        mergeSortRecursive(arr, l, p);
        mergeSortRecursive(arr, p + 1, r);

        int[] temp = new int[arr.length]; // 임시 메모리

        int index = l;
        int lp = l, rp = p + 1;
        while(lp <= p && rp <= r) {
            if(arr[lp] <= arr[rp]) {
                temp[index++] = arr[lp++];
            }else {
                temp[index++] = arr[rp++];
            }
        }

        // 오른쪽 원소들이 남은 경우

        while(rp <= r) {
            temp[index++] = arr[rp++];
        }
        // 왼쪽 원소들이 남은 경우
        while(lp <= p) {
            temp[index++] = arr[lp++];
        }

        for(int i = l; i <= r; i++) {
            arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {

        System.out.println("Bubble sort");
        int[] data = generateData(10);
        printArray(data);
        bubbleSort(data);
        printArray(data);

        System.out.println("Selection sort");
        data = generateData(15);
        printArray(data);
        selectionSort(data);
        printArray(data);

        System.out.println("Insertion sort");
        data = generateData(15);
        printArray(data);
        insertionSort(data);
        printArray(data);

        data = generateData(30);
        System.out.println("Quick sort");
        printArray(data);
        quickSort(data);
        printArray(data);

        data = generateData(30);
        System.out.println("Merge sort");
        printArray(data);
        mergeSort(data);
        printArray(data);

    }
}
