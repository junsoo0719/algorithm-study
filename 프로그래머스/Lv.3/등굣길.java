import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        // m x n 이지만 행 수: n, 열 수: m
        boolean[][] blocked = new boolean[n + 1][m + 1];

        for (int[] puddle : puddles) {
            blocked[puddle[1]][puddle[0]] = true;
        }

        // 해당 칸까지의 경로 개수
        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                // 집 위치는 이미 초기화했으므로 패스
                if (row == 1 && col == 1) {
                    continue;
                }

                // 물 웅덩이도 지나갈 수 없으므로 경로 수 0으로 패스
                if (blocked[row][col]) {
                    continue;
                }

                // 위에서 내려오거나 왼쪽에서 들어오는 두 가지 경로가 있음
                dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % MOD;
            }
        }
        return dp[n][m];
    }
}