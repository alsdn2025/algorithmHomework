package pocu_search;

public class binarySearch {

    public static int binarySearchRecursive(int[] data, int l, int r, int target) {
        if(l > r)
            return -1;

        int pivot = (l + r) / 2;
        if(data[pivot] == target)
            return pivot;

        if(data[pivot] < target){
            return binarySearchRecursive(data, pivot + 1, r,target);
        }else
            return binarySearchRecursive(data, l, pivot - 1, target);
    }

    public static int locatedArrayBinarySearch(int data[], int l, int r, int target) {
        if(l > r)
            return -1;
        int pivot = (l + r)/2;
        if(data[pivot] == target)
            return pivot;

        if(data[l] <= data[pivot]) {
            if(target >= data[l] && target < data[pivot])
                return locatedArrayBinarySearch(data, l, pivot - 1 , target);
            return locatedArrayBinarySearch(data, pivot + 1, r, target);
        }else {
            if(target > data[pivot] && target <= data[l]) {
                return locatedArrayBinarySearch(data, pivot + 1, r, target);
            }
            return locatedArrayBinarySearch(data, l, pivot - 1, target);
        }
    }

    public static void main(String[] args) {

        int data[] = new int[100];
        for (int i = 0; i < 100; i++) {
            data[i] = i;
        }
        System.out.println(binarySearchRecursive(data, 0, data.length-1, 11));
        System.out.println();

        int locatedData[] = new int[100];
        for (int i = 0; i < 25; i++) {
            locatedData[i] = 75+i;
        }
        for (int i = 25; i < 100; i++) {
            locatedData[i] = i-25;
        }
        System.out.println(locatedArrayBinarySearch(locatedData, 0, locatedData.length-1, 11));

    }
}
