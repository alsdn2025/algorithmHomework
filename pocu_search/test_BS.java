package pocu_search;

public class test_BS {

    public static int bs(int[] data, int l, int r, int target) {
        if (l > r)
            return -1;
        int p = (l + r)/2;

        if(data[p] == target)
            return p;
        if(data[p] < target)
            return bs(data, p + 1, r, target);
        else
            return bs(data, l, p - 1, target);
    }

    public static int located_bs(int[] data, int l, int r, int target) {

        if (l > r)
            return -1;
        int p = (l + r) / 2;
        if(data[p] == target)
            return p;

        // 왼쪽이 정렬되어 있다면
        if(data[l] <= data[p]) {
            if(data[l] <= target && target < data[p])
                return located_bs(data, l, p - 1, target);
            return located_bs(data, p + 1, r, target);
        }else { // 오른쪽이 정렬되어 있다면
            if(data[p] < target && target <= data[r])
                return located_bs(data, p + 1, r, target);
            return located_bs(data, l, p - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,5,7,8,10,15,21,32,35,39,43,44,47,99};
        int target = 10;
        int index = bs(data, 0, data.length - 1, target);

        for(int i: data) {
            System.out.print(i + " ");
        }

        System.out.println(String.format("\n%d은 %d번 인덱스에 있습니다.\n", target, index));


        // located array
        int[] locatedData = {10,15,21,32,35,39,43,44,47,99,1,2,3,5,7,8};
        target = 35;
        index = located_bs(locatedData, 0, locatedData.length - 1 , target);

        for(int i: locatedData) {
            System.out.print(i + " ");
        }

        System.out.println(String.format("\n%d은 %d번 인덱스에 있습니다.", target, index));
    }

}
