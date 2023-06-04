package etc_coding_test;

import java.util.Random;
import java.util.Scanner;

/**
 * 코드스쿼드(https://github.com/code-squad/test-item-pool/blob/master/level2-common/level2.md?utm_source=boottent&utm_medium=referral)
 * 요구사항
 * 기본적으로 1부터 9까지 서로 다른 수로 이루어진 3자리의 수를 맞추는 게임이다.
 * 같은 수가 같은 자리에 있으면 스트라이크, 다른 자리에 있으면 볼, 같은 수가 전혀 없으면 포볼 또는 낫싱이란 힌트를 얻고, 그 힌트를 이용해서 먼저 상대방의 수를 맞추면 승리한다.
 * [예] 상대방의 수가 425일 때, 123을 제시한 경우 : 1 스트라이크 456을 제시한 경우 : 1 스트라이크 1볼 789를 제시한 경우 : 낫싱
 * 위 숫자 야구게임에서 상대방의 역할을 컴퓨터가 한다. 컴퓨터는 1에서 9까지 서로 다른 임의의 수 3개를 선택한다. 게임 플레이어는 컴퓨터가 생각하고 있는 3개의 숫자를 입력하고, 컴퓨터는 입력한 숫자에 대한 결과를 출력한다.
 * 이 같은 과정을 반복해 컴퓨터가 선택한 3개의 숫자를 모두 맞히면 게임이 종료된다.
 *
 * 프로그래밍 구현 제약사항
 * 함수(또는 메소드) 하나의 크기가 최대 10라인을 넘지 않도록 구현한다.
 * 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
 * indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.
 * 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
 * 전역 변수를 사용하지 않는다.
 */
public class NumberBaseball {

    /**
     * 숫자야구 결과를 출력하고 게임 승리 여부를 리턴하는 메서드
     */
    public static boolean baseball(int[] answer, int[] input) {
        StringBuilder sb = new StringBuilder();
        int numStrike = getStrikeCount(answer, input);
//        int numBall = getBallCount(answer, input); // 중복 입력도 체크
        int numBall = getBallCountVer2(answer, input); // 중복 입력는 무시
        getResult(numStrike, numBall, sb);
        System.out.println(sb.toString());
        return answer.length == numStrike;
    }

    /**
     * 스트라이크 카운트를 리턴
     */
    public static int getStrikeCount(int[] answer, int[] input) {
        int numStrike = 0;
        for(int i = 0; i < answer.length; i++) {
            if(answer[i] == input[i])
                numStrike++;
        }
        return numStrike;
    }

    /**
     * 볼 카운트를 리턴
     */
    public static int getBallCount(int[] answer, int[] input) {
        int numBall = 0;
        for(int i = 0; i < answer.length; i++) {
            int includedIndex = getInclude(answer, input[i]);
            if(includedIndex != i && includedIndex != -1) {
                numBall++;
            }
        }
        return numBall;
    }

    /**
     * 중복 입력시 하나만 유효하다고 가정
     */
    public static int getBallCountVer2(int[] answer, int[] input) {
        int numBall = 0;
        for(int i = 0; i < answer.length; i++) {
            if( !isDuplicate(input, i)
                    && (getInclude(answer, input[i]) != i && getInclude(answer, input[i]) != -1)) {
                numBall++;
            }
        }
        return numBall;
    }

    /**
     * input[index]가 input[0] ~ input[index - 1] 사이에 중복되어 존재하는지 여부를 리턴
     */
    public static boolean isDuplicate(int[] input, int index) {
        for(int i = 0; i < index; i++) {
            if(input[i] == input[index])
                return true;
        }
        return false;
    }


    /**
     * 배열에 target 이 포함되어 있을 경우 해당 인덱스를 리턴하는 메서드
     * @return: 포함하지 않으면 -1을 리턴, 포함하면 해당하는 인덱스를 리턴
     */
    public static int getInclude(int[] arr, int target) {
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 결과를 StringBuilder 에 저장하는 메서드
     */
    public static void getResult(int numStrike, int numBall, StringBuilder sb) {
        if(numStrike <= 0 && numBall <= 0){
            sb.append("낫싱");
            return;
        }
        if(numStrike >= 0)
            sb.append(numStrike + " 스트라이크 ");
        if(numBall >= 0)
            sb.append(numBall + "볼 ");
    }

    public static void printArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 겹치지 않는 수로 이루어진 답안 배열을 만들어 리턴하는 함수
     */
    public static int[] createAnswer(int length) {
        int[] answer = new int[length];
        int i = 0;
        while(i < 3) {
            int numRandom = new Random().nextInt(9) + 1; // 1 ~ 9
            if(getInclude(answer, numRandom) == -1)
                answer[i++] = numRandom;
        }
        return answer;
    }

    /**
     * 사용자로부터 입력값을 받는 메서드
     */
    public static void getInput(int[] input, int length) {
        Scanner sc = new Scanner(System.in);
        String inputStr = sc.nextLine();
        for(int i = 0; i < length; i++) {
            input[i] = inputStr.charAt(i) - '0';
        }
    }

    public static void main(String[] args) {
        int length = 3;
        int[] input = new int[length];
        int[] answer = createAnswer(length); // 랜덤 답안 만들기
        getInput(input, length);
        while(!baseball(answer, input)){
            getInput(input, length);
        }
        System.out.println(length + "개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
