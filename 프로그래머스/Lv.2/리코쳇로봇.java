import java.util.*;

class 리코쳇로봇 {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public int solution(String[] board) {

        int rows = board.length;
        int cols = board[0].length();

        int startX = 0;
        int startY = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row].charAt(col) == 'R') {
                    startX = row;
                    startY = col;
                }
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startX, startY});
        int[][] dist =  new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[startX][startY] = 0;

        while (!queue.isEmpty()) {
            int[] cur =  queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (board[x].charAt(y) == 'G') {
                return dist[x][y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = x;
                int ny = y;

                while (canMove(nx + dx[i], ny + dy[i], board, rows, cols)) {
                    nx += dx[i];
                    ny += dy[i];
                }

                if (dist[nx][ny] != -1) {
                    continue;
                }

                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[]{nx, ny});
            }
        }

        return -1;
    }

    private boolean canMove(int x, int y, String[] board, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols
                && board[x].charAt(y) != 'D';
    }
}