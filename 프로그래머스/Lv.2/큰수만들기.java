import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int n = number.length();
        int resultLength = n - k;
        int remaining = k;

        // char 배열은 스택처럼 사용, size는 현재 저장된 숫자 개수
        char[] stack = new char[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            char digit = number.charAt(i);

            // 현재 숫자를 더 앞에 두면 큰 수가 되는 경우
            while (remaining > 0 && size > 0 && stack[size - 1] < digit) {
                size--;
                remaining--;
            }

            stack[size++] = digit;
        }

        // 삭제 횟수가 남았다면 뒤에서 제거, 앞에서 resultLength개만 반환
        // new String(문자 배열, 시작 인덱스, 문자 개수)
        return new String(stack, 0, resultLength);
    }
}