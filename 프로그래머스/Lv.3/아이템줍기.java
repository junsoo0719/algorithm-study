import java.util.*;

class 아이템줍기 {
    private static final int SIZE = 102;

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[SIZE][SIZE];

        // 좌표를 2배로 확대한 후 모든 직사각형을 1로 채움
        // 이유: 좌표를 그대로 배열에 표시하면 높이가 1인 직사각형 양쪽 테두리가 바로 붙을 수 있는데
        // 캐릭터는 직사각형 내부를 통과할 수 없기 때문
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    map[x][y] = 1;
                }
            }
        }

        // 직사각형 내부를 0으로 제거하여 테두리만 남김
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2;
            int y1 = rect[1] * 2;
            int x2 = rect[2] * 2;
            int y2 = rect[3] * 2;

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = 0;
                }
            }
        }

        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;

        int[][] dist = new int[SIZE][SIZE];
        // dist배열 -1로 초기화해서 아직 방문하지 않은 위치 표시
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{characterX, characterY});
        dist[characterX][characterY] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            if (x == itemX && y == itemY) {
                // 좌표를 2배 확대했으므로 거리도 2로 나눔
                return dist[x][y] / 2;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nextX = x + dx[dir];
                int nextY = y + dy[dir];

                if (nextX < 0 || nextX >= SIZE || nextY < 0 || nextY >= SIZE) {
                    continue;
                }
                if (map[nextX][nextY] != 1) {
                    continue;
                }
                if (dist[nextX][nextY] != -1) {
                    continue;
                }

                queue.offer(new int[]{nextX, nextY});
                dist[nextX][nextY] = dist[x][y] + 1;
            }
        }

        return 0;
    }
}