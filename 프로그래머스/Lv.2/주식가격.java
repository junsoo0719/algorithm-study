import java.util.*;

class Solution {
    public int[] solution(int[] prices) {

        int n = prices.length;
        int[] answer = new int[n];

        // 기간이 확정되지 않은 인덱스 스택
        int[] stack = new int[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            // 현재 가격이 더 낮으면 이전 가격의 유지 기간 확정
            while (size > 0 && prices[i] < prices[stack[size - 1]]) {
                int index = stack[--size];
                answer[index] = i - index;
            }

            // 현재 가격은 이후에 떨어지는지 확인해야 하므로 스택에 저장
            stack[size++] = i;
        }

        // 스택에 남은 가격은 마지막까지 떨어지지 않은 경우
        while (size > 0) {
            int index = stack[--size];
            answer[index] = n - index - 1;
        }

        return answer;
    }
}