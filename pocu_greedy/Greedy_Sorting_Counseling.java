package pocu_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 학생 상담 테이블 문제. 
 * 랜덤으로 생성된 학생 상담 신청 데이터를 보고, 최대한 많은 수의 학생을 상담하는 방법을 도출한다.
 *
 * Input:
 * The first line gives the number of students N (1 ≤ N ≤ 100,000).
 * From the second line to the N+1 line, the information of each counseling is given,
 * and the start time and end time of the counseling are given with a space between them.
 * The start time and end time are natural numbers less than or equal to 231-1 or 0.
 *
 * Output:
 * The first line prints the maximum number of possible students for counseling.
 * The second line print the start and end times of the included counseling.
 */
public class Greedy_Sorting_Counseling {
    static final int STUDENT_NUMBER = 100000; // 상담을 원하는 학생 수

    // 정렬 인터페이스
    public interface ISorting{
        // 각 정렬 클래스는 students를 오름차순 정렬하는 메서드를 가진다.
        void sort(int[][] students);
    }

    // 기수정렬 클래스
    public static class Radix implements ISorting {
        // 기수정렬
        @Override
        public void sort(int[][] students){
            // 학생 배열에서 가장 큰 값을 가지는 end time을 저장한다.
            int max = getLatestEndTime(students);

            // 가장 큰 end time의 자리수만큼 작은 자리수부터(LSD) countSort한다.
            for(int exp = 1; max/exp > 0; exp *= 10){
                radixCountSort(students,exp);
            }
        }

        // 모든 학생의 end time에 대해 해당 exp 자리수에 해당하는 값 순서대로 학생배열을 정렬하는 메서드
        public static void radixCountSort(int[][] students, int exp){
            int[][] temp = new int[students.length][2]; // 정렬 결과를 임시저장할 배열
            int[] count = new int[10]; // 해당 자리수에 해당하는 값에 대해 0부터 9까지의 값들을 카운트하고 누적합을 만들기 위해 사용하는 배열
            Arrays.fill(count,0); // count배열을 0으로 초기화한다.

            // 모든 학생배열의 end time에 대해, 해당 자리수(exp)값중 0~9까지의 값들이 몇 개씩 있는지를 카운트한다.
            for (int[] student : students) {
                int expValue = (student[1] / exp) % 10; // 학생의 end time에 대해 exp자리수 값을 저장한다.
                count[expValue]++; // 해당 값의 카운트를 하나 늘린다.
            }

            // count배열을 누적합으로 만들어서, temp 배열에 학생이 들어갈 위치를 저장한다.
            for (int i = 1; i < 10; i++) {
                count[i] += count[i-1];
            }

            // 누적합을 이용해, 모든 학생들을 해당 자리수의 end time이 작은 순서대로 임시배열에 정렬한다.
            for(int i = students.length-1; i>=0; i-- ){
                int expValue = (students[i][1]/exp)%10; // 학생의 end time에 대해 exp자리수 값을 저장한다.
                temp[count[expValue]-1] = students[i]; // count 배열이 저장한 위치에 학생을 차곡차곡 넣는다.
                // 배열의 인덱스는 0부터 시작하므로 count[expValue]-1 위치에 넣는다.
                count[expValue]--; // 학생을 배치했으므로 카운트를 하나 줄인다.
            }

            // temp 임시배열에 저장된 정렬 결과를 학생 배열에 매핑한다.
            for (int i = 0; i < students.length; i++) {
                students[i] = temp[i];
            }
        }

    }

    // 퀵정렬 클래스
    public static class Quick implements ISorting {
        // 학생 배열에 대해 퀵정렬을 수행하는 메서드
        @Override
        public void sort(int[][] students){
            quickSortRecursive(students, 0, students.length-1); // 첫 번째 학생부터 마지막 학생까지 퀵정렬 재귀함수를 호출한다.
        }

        /*
         * 퀵정렬 재귀함수.
         * 재귀적으로 호출되어, 학생 배열의 left번째 학생부터 right번째 학생까지를 end time에 따라 오름차순으로 정렬한다.
         * */
        public static void quickSortRecursive(int[][] students, int left, int right){
            if(left >= right){
                return; // 배열의 위치를 나타내는 left가 right보다 크거나 같다면 중단한다.
            }

            int pos = quickSortPartition(students, left, right); // 각 회차의 정렬 수행 후, 최종 고정 위치를 리턴받는다.
            // 해당 위치의 학생은 그 위치에 최종 고정된다.

            quickSortRecursive(students, left, pos-1); // 고정된 위치의 왼쪽 부분과
            quickSortRecursive(students,pos+1, right); // 오른쪽 부분을 재귀함수를 통해 정렬한다.
        }

        /*
         * 퀵정렬 파티션 함수.
         * 가장 오른쪽(right) 학생의 상담종료시간을 기준값으로 하여 기준값보다 작은 end time을 가진 학생을 왼쪽으로
         * 기준값보다 큰 end time을 가진 학생을 오른쪽으로 위치시킨다.
         * 이후 기준값이 된 학생을 최종위치시키고 이 위치를 반환한다.
         */
        public static int quickSortPartition(int[][] students, int left, int right){
            int pivot = students[right][1]; // 가장 오른쪽 학생의 end time을 pivot값으로 사용한다.

            int i = (left - 1);
            // left부터 right까지 학생의 end time을 한명씩 비교해서, pivot보다 작다면 i 이하의 위치로 이동시킨다.
            for(int j = left; j<right; j++){
                if(students[j][1] < pivot){
                    i++;
                    swap(students,i,j);
                }
            }// for문이 다 끝나면, i이하는 pivot보다 작은 값이 되고, i 초과는 pivot보다 같거나 큰 값이 된다.

            // i+1번째 학생을 가장 오른쪽 학생(pivot)과 바꾼다.
            // 원래 피봇이었던 해당 학생을 바꾼 자리에 최종 고정시키고, 최종 고정 위치를 리턴한다.
            int pos = i+1;
            swap(students,pos,right);

            return pos;
        }

    }

    // 병합 정렬 클래스
    public static class Merge implements ISorting {
        // 병합 정렬
        @Override
        public void sort(int[][] students){
            mergeSortRecursive(students,0,students.length-1); // 첫 번째 학생부터 마지막 학생까지 재귀함수 호출
        }

        // 배열을 left부터 right까지 분할하고 병합하여 정렬하는 재귀함수
        public static void mergeSortRecursive(int[][] students, int left, int right){
            if(left >= right){
                return; // left가 right보다 크거나 같다면 중단한다.
            }

            int mid = (left + right) / 2; // 중간 인덱스를 만든다.

            mergeSortRecursive(students, left,mid);  // 중간 인덱스를 기준으로 왼쪽 파티션에 대해 다시 재귀함수를 호출한다.
            mergeSortRecursive(students,mid+1,right); // 중간 인덱스를 기준으로 오른쪽 파티션에 대해 다시 재귀함수를 호출한다.
            mergeSortMerge(students, left, mid,mid+1, right); // 왼쪽 파티션과 오른쪽 파티션의 값을 비교하여 오름차순으로 정렬하면서 병합한다.
        }

        /*
         * 두 파티션의 값을 비교하고 정렬하여 한 파티션으로 병합하는 메서드
         * leftStart번째 학생부터 leftEnd까지의 학생을 왼쪽 파티션, rightStart학생부터 rightEnd까지의 학생을 오른쪽 파티션으로 취급한다.
         * 왼쪽 파티션의 각 학생과 오른쪽 파티션의 각 학생의 end time을 비교하여 모든 학생에 대해 오름차순으로 정렬한다.
         * */
        public static void mergeSortMerge(int[][] students, int leftStart, int leftEnd, int rightStart, int rightEnd){
            int p = leftStart; // 왼쪽 파티션의 시작 인덱스
            int q = rightStart; // 오른쪽 파티션의 시작 인덱스
            int[][] temp = new int[rightEnd-leftStart+1][2]; // leftStart부터 rightEnd까지의 정렬 결과 배열을 저장하기 위한 임시 배열
            int temp_index = 0; // 임시저장 배열의 인덱스

            while(p <= leftEnd && q <= rightEnd){         // p와 q가 각 파티션의 범위를 벗어날때까지,
                if(students[p][1] <= students[q][1]){       // 두 파티션의 각 첫 번째 값부터 비교하여,
                    temp[temp_index++] = students[p++];     // 둘 중 더 작은 값을 temp 배열에 차곡차곡 넣고 p나 q를 증가시킨다.
                }else {
                    temp[temp_index++] = students[q++];
                }
            }

            // 만약 p가 왼쪽 파티션 범위를 벗어났다면, 아직 남아있는 오른쪽 파티션의 값들을 temp에 넣어준다.
            if(p>leftEnd){
                for(int i = q; i <= rightEnd; i++){
                    temp[temp_index++] = students[i];
                }
            }else{ // 만약 왼쪽 파티션 값이 남아있다면, 남은 왼쪽 파티션 값들을 temp에 넣어준다.
                for(int i = p; i<=leftEnd; i++){
                    temp[temp_index++] = students[i];
                }
            }

            // 만들어진 temp 배열을 students 배열에 매핑한다.
            for(int i = leftStart; i <= rightEnd; i++){
                students[i] = temp[i-leftStart];
            }
        }
    }

    // 힙 정렬 클래스
    public static class Heap implements ISorting {
        // 힙 정렬. 최대 힙을 이용한다.
        @Override
        public void sort(int[][] students) {
            int child; // 자식 노드 위치
            int parent; // 부모 노드 위치

            /*
             * 학생 배열을 최대 힙 구조로 만든다.
             * 제일 첫 번째 학생(0번째)를 처음 루트 노드로 지정한 뒤,
             * 두 번째 학생부터 마지막 학생까지, 해당 학생을 자식노드로 하여 부모 노드와 값을 비교하고 스왑하여 힙 구조를 만든다.
             */
            for (int i = 1; i < STUDENT_NUMBER; i++) { // 0번째 노드가 처음 루트노드가 되므로 1번째 노드부터 비교한다.
                child = i; // 자식 노드 위치
                do {
                    parent = (child - 1) / 2; // 부모 노드의 위치는 (자식 노드의 위치 -1) / 2다.
                    if (students[parent][1] < students[child][1]) { // 만약 부모노드의 end time보다 자식 노드의 end time이 더 클 경우
                        swap(students, child, parent); // 두 노드의 위치를 바꾼다.
                    }
                    child = parent; // 부모 노드를 새로운 자식노드로 하여 위 과정을 반복한다.
                } while (child != 0); // 0번째(루트노드)가 아닌 모든 자식 노드에 대해 이를 수행한다.
            }


            /*
             * 힙의 사이즈를 하나씩 줄여가며 배열을 정렬한다.
             * 루트 학생의 위치와 마지막(i번째) 학생의 위치를 바꾼 뒤 바뀐 배열의 i-1번 이하 노드들에 대하여 다시 힙 구조를 만든다.
             * 힙의 사이즈가 0이 될때까지 이를 반복하면 배열의 정렬이 완료된다.
             * */
            for (int i = STUDENT_NUMBER - 1; i >= 0; i--) {
                swap(students, 0, i); // 먼저 i번째 학생과 루트 학생(0번 인덱스의 학생)을 바꾼다.
                // 루트 학생은 해당 루프에서 가장 큰 end time을 가자고 있으므로, 맨 뒤인 i번째 위치에 고정된다.
                parent = 0;  // 루트 노드의 위치 0을 부모 노드의 위치로 저장한다.

                // 자식과 부모노드와 값을 비교하고자식 노드의 값이 더 크다면 부모노드와 바꾼다.
                // 이미 고정된 i번째 미만의 모든 노드가 힙의 속성을 만족하도록, i번째 미만의 모든 자식노드에 대해 이를 반복한다.
                do {
                    child = 2 * parent + 1; // 부모 노드의 자식 노드.

                    // 부모 노드의 자식노드 둘 중 더 큰 자식노드를 찾는다.
                    // 자식 노드는
                    if (child < i - 1 && students[child][1] < students[child + 1][1]) {
                        child++;
                    }
                    // 자식 노드가 부모 노드보다 더 크다면 바꾼다.
                    if (child < i && students[parent][1] < students[child][1]) {
                        swap(students, child, parent);
                    }
                    parent = child; // 이제 바뀐 노드를 새로운 루트 노드로 하여 이 과정을 반복한다.
                } while (child < i); // while문이 끝나면 i번째 미만의 모든 노드에 대해 힙 구조가 완성된다.
            }
        }

    }

    // 학생 배열 중 a번째 학생과 b번째 학생의 자리를 바꾸기 위한 메서드.
    public static void swap(int[][] students, int a, int b){
        int[] temp;
        temp = students[a];
        students[a] = students[b];
        students[b] = temp;
    }

    // 모든 학생의 end time 중, 가장 큰 값(늦은 시간)을 리턴하는 메서드.
    public static int getLatestEndTime(int[][] students){
        int max = students[0][1]; // 가장 큰 값을 저장하기 위한 로컬변수.

        // for 문을 통해 모든 학생의 end time중 가장 큰 값을 저장한다.
        for(int i = 1; i<students.length; i++){
            if(students[i][1] > max){
                max = students[i][1];
            }
        }

        return max; // 도출해낸 가장 큰 값을 리턴한다.
    }

    public static void main(String[] args) {
        long programStartTime = System.currentTimeMillis(); // 프로그램의 시작 시각 기록
        Random rand = new Random(programStartTime);  // 난수 생성을 위한 랜덤 객체 생성

        /*
        * 상담신청학생목록을 나타내는 배열을 만든다.
        * 첫번째 인덱스는 학생 번호를
        * 두번째 인덱스는 학생이 신청한 시작시간(start time)과 종료시간(end time)를 저장한다.
        * 시작시간은 종료시간보다 작아야 한다.
        * ex) student[0][0] : 0번 학생의 상담 시작 시각, student[0][1] : 0번 학생의 상담 종료 시각
        * */
        int[][] students = new int[STUDENT_NUMBER][2];


        /*
        * 완성된 상담시간표를 나타내는 배열을 만든다.
        * 각 학생의 학생번호를 상담 순서대로 저장한다.
        * */
        ArrayList<Integer> timeTable = new ArrayList<>();


        /*
         * 상담신청학생목록을 생성할 방식을 선택한다.
         * RANDOM : 무작위, ASCENDING: 상담종료시간을 기준으로 오름차순 생성
         * */
        creationType type = creationType.RANDOM;          //  생성방식 중 하나를 선택한다.
//        creationType type = creationType.ASCENDING;

        /*
        * 선택한 방식대로 상담신청학생목록의 내용을 만든다.
        * */
        switch (type) {
            // 상담종료시간에 대해 무작위로 생성
            case RANDOM:
                for (int i = 0; i < STUDENT_NUMBER; i++) {
                    students[i][1] = rand.nextInt(Integer.MAX_VALUE - 1) + 1; // 상담 종료 시간은 최소 1부터 최대 (2^31-1).
                    students[i][0] = rand.nextInt(students[i][1] - 1); // 상담 시작 시간은 최소 0부터 최대 (상담종료시간-1).
                }
                break;

            // 상담신청학생목록을 처음부터 상담종료시간에 대해 오름차순으로 정렬되어있도록 생성.
            case ASCENDING:
                students[0][1] = 1;
                students[0][0] = 0;
                for (int i = 1; i < STUDENT_NUMBER; i++) {
                    int prevEndTime = students[i - 1][1]; // 앞 학생의 상담종료시간.
                    students[i][1] = prevEndTime + 1; // 상담 종료 시간은 앞 학생의 상담종료시간 + 1.
                    students[i][0] = rand.nextInt(students[i][1] - 1); // 상담 시간 시간은 최소 0부터 최대 (상담종료시간-1)
                }
        }

        /*
         * 상담신청학생목록을 각 학생의 상담종료시간(end time)기준으로 오름차순 정렬한다.
         * 정렬 종류를 선택한다.
         */
        long sortingStartTime = System.currentTimeMillis();
        new Heap().sort(students);
//        new Radix().sort(students);
//        new Merge().sort(students);
//        new Quick().sort(students);
        long sortingEndTime = System.currentTimeMillis();
        long sortingTime = sortingEndTime - sortingStartTime;

        /*
        * 정렬된 상담신청목록을 이용하여 최적의 상담시간표를 만든다.
        * 상담신청목록은 이미 각 학생의 상담종료시간을 기준으로 오름차순 정렬되어있기 때문에
        * 모든 학생에 대해 마지막 상담종료시간보다 상담시작시간이 더 뒤인 학생을 골라 상담시간표에 넣으면 최적의 시간표가 만들어진다.
        */
        int lastCounselingEndTime = 0; // 마지막 상담종료 시간
        for(int i = 0; i < STUDENT_NUMBER; i++){ // 신청 목록에 있는 모든 학생에 대해
            if(lastCounselingEndTime <= students[i][0]){ // 상담시작시간이 마지막 상담종료시간보다 뒤일 경우
                timeTable.add(i); // 해당 학생의 번호를 상담시간표에 넣는다.
                lastCounselingEndTime = students[i][1];  // 마지막 상담종료시간을 갱신한다.
            }
        }

        /*
        * 결과 상담시간표를 출력한다.
        * */
        System.out.println("\nResult Time Table");
        System.out.println("Number of Counseling Students : " + timeTable.size());
        System.out.println("Student Number | Start time ~   End time");
        System.out.println("------------------------------------------");
        for (int index: timeTable) {
            System.out.printf("%-14d : %10d ~ %10d\n",index+1, students[index][0], students[index][1]);
        }

        /*
        * 전체 시간과 정렬시간의 비율을 구한다.
        */
        long programEndTime = System.currentTimeMillis(); // 프로그램 종료 시간을 저장한다.
        long entireTime = programEndTime-programStartTime; // 전체 시간을 구한다.
        double sortingPartRatio = (double)sortingTime / (double)entireTime; // 전체 시간 중 정렬 시간의 비율을 구한다.
        double remainingPartRatio = 1.0d - sortingPartRatio; // 정렬 시간을 뺀 나머지 시간의 비율을 구한다.


        /*
        * 정렬 시간, 전체 시간,
        * 정렬 시간의 비율, 나머지의 비율을 출력한다.
        * */
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator()).append("Sorting Time : ").append(sortingTime).append("ms").append(System.lineSeparator())
                .append("Entire Time : ").append(entireTime).append("ms").append(System.lineSeparator())
                .append(String.format("Sorting Part Ratio : %.5f",sortingPartRatio)).append(System.lineSeparator())
                .append(String.format("Remaining Part Ratio : %.5f",remainingPartRatio)).append(System.lineSeparator());
        System.out.println(sb);

    }
}
