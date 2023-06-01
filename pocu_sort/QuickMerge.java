package pocu_sort;

import java.util.Arrays;
import java.util.Random;

public class QuickMerge {

    public static void quickSort(int[] data) {
        System.out.println("Quick Sort---");
        printArray(data);
        quickSortRecursive(data, 0, data.length-1);
    }

    public static void quickSortRecursive(int[] data, int l, int r) {
        if(l >= r) {
            return;
        }

        // r을 pivot 으로 사용
        int start = l;

//        System.out.println("pivot index = " + r);
//        printArrayWithPivotAndLeft(data, r, l);

        for (int i = l; i < r; ++i) {
            if(data[i] < data[r]) {
                swap(data, l, i);
                l++;
            }
//            printArrayWithPivotAndLeft(data, r, l);
        }
        swap(data, l, r); // 전진이 끝난 left 위치에 pivot 이 최종 고정됨
        printArrayWithPivot(data, l);

        quickSortRecursive(data, start, l-1);
        quickSortRecursive(data, l+1, r);
    }

    public static void mergeSort(int[] data) {
        System.out.println("Merge Sort -----");
        mergeSortRecursive(data, 0, data.length - 1);
    }

    public static void mergeSortRecursive(int[] data, int l, int r) {
        if(l >= r) {
            return;
        }

        int mid = (l + r) / 2;
        mergeSortRecursive(data, l, mid);
        mergeSortRecursive(data, mid + 1, r);

//        System.out.println("l = " + l + " r = " + r);

        int[] temp = new int[data.length];
        int lp = l;
        int rp = mid + 1;
        int i = l;
        while(lp <= mid && rp <= r) {
            if(data[lp] < data[rp]) {
                temp[i] = data[lp++];
            }else if (data[lp] >= data[rp]) {
                temp[i] = data[rp++];
            }
            i++;
//            printArray(temp);
        }

        // 남은 요소들 채워넣기
        while(rp <= r) {
            temp[i++] = data[rp++];
//            printArray(temp);
        }

        while(lp <= mid) {
            temp[i++] = data[lp++];
//            printArray(temp);
        }

        // 임시 저장소 -> 결과로 복사
        for (int j = l; j <= r; j++) {
            data[j] = temp[j];
        }
        printArray(data);
    }


    public static void dualPivotQuicksort(int[] arr, int left, int right) {
        if (right - left < 1) return;
        if (arr[left] > arr[right]) swap(arr, left, right);
        int pivot1 = arr[left], pivot2 = arr[right];
        int less = left + 1, great = right - 1;
        int i;
        for (i = less; i <= great; i++) {
            if (arr[i] < pivot1) swap(arr, i, less++);
            else if (arr[i] > pivot2) {
                while (arr[great] > pivot2 && i < great) great--;
                swap(arr, i, great--);
                if (arr[i] < pivot1) swap(arr, i, less++);
            }
        }

        swap(arr, left, --less);
        swap(arr, right, ++great);

        dualPivotQuicksort(arr, left, less - 1);
        dualPivotQuicksort(arr, great + 1, right);

        if (less < i && great > i) {
            dualPivotQuicksort(arr, less, great);
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

    public static void printArrayWithPivot(int[] arr, int pivot) {
        for (int i = 0; i < arr.length; i++) {
            if(i == pivot)
                System.out.print(arr[i] +"* ");
            else
                System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    public static void printArrayWithPivotAndLeft(int[] arr, int pivot, int left) {
        for (int i = 0; i < arr.length; i++) {
            if(i == left)
                System.out.print(arr[i] +"l ");
            else if(i == pivot){
                System.out.print(arr[i] +"* ");
            }else
                System.out.print(arr[i] + "  ");
        }
        System.out.println();
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
//        quickSort(data);
//        System.out.println("결과-----");
//        printArray(data);

        data = generateData(44);
        mergeSort(data);
        System.out.println("결과-----");
        printArray(data);




    }
}
