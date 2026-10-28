import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int height = triangle.length;

        // dp[col]: 현재 행의 col 위치에서 바닥까지의 최대 합
        // 밑에서부터 계산하므로 마지막 행 복사
        int[] dp = triangle[height - 1].clone();

        // 밑에서 두 번째 행부터 꼭대기까지 계산
        for (int row = height - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                // 아래쪽 두 경로 중 합이 더 큰 경로 선택
                // dp[col]: 왼쪽 아래 경로, dp[col + 1]: 오른쪽 아래 경로
                dp[col] = triangle[row][col] + Math.max(dp[col], dp[col + 1]);
            }
        }

        return dp[0];
    }
}