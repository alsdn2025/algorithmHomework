package pocu_sort;

import java.util.Random;

public class BubbleSelectInsert {
    
    public static void bubbleSort(int[] data) {
        for (int j = data.length - 1; j > 0; --j) {
            System.out.println(j + 1 + "번째 값을 찾는 단계----");
            printArray(data);
            for (int i = 0; i < j; ++i) {
                if(data[i] > data[i + 1]){
                    int temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                }
                printArray(data);
            }
        }
    }
    
    public static void selectionSort(int[] data) {
        for (int i = 0; i < data.length -1; ++i) {
            for (int j = i+1; j < data.length; ++j) {
                if(data[i] > data[j]){
                    int temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
            System.out.println();
        }
    }

    public static void insertionSort(int[] data) {
        for (int i = 1; i < data.length; ++i) {
            System.out.println("i = " + i);
            int j = i;
            while(j > 0 && data[j-1] > data[j]) {
                int temp = data[j-1];
                data[j-1] = data[j];
                data[j] = temp;
                j--;
                printArray(data);
            }
        }
    }


    public static void _sort(int[] data) {

    }


    public static void _sortRecursive(int[] data, int l, int r) {
        if(l >= r)
            return;

        int pivot = (l + r) / 2;


    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] generateData(int length) {
        Random random = new Random();
        int data[] = new int[length];
        for (int i = 0; i < data.length; ++i) {
            data[i] = random.nextInt(20);
        }
        return data;
    }
    
    public static void main(String[] args) {

//        System.out.println("Bubble sort");
//        int[] data = generateData(10);
////        printArray(data);
//        bubbleSort(data);
//        printArray(data);

//        System.out.println("Selection sort");
//        int[] data = generateData(15);
//        selectionSort(data);
//        printArray(data);

        System.out.println("Insertion sort");
        int[] data = generateData(15);
        printArray(data);
        insertionSort(data);
        printArray(data);

    }
}
