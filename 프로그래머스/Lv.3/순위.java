import java.util.*;

class 순위 {
    public int solution(int n, int[][] results) {

        List<List<Integer>> winGraph = new ArrayList<>();
        List<List<Integer>> loseGraph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            winGraph.add(new ArrayList<>());
            loseGraph.add(new ArrayList<>());
        }

        for (int[] result : results) {
            int winner = result[0];
            int loser =  result[1];

            winGraph.get(winner).add(loser);
            loseGraph.get(loser).add(winner);
        }

        int answer = 0;

        for (int player = 0; player <= n; player++) {
            int winCount = bfs(player, winGraph);
            int loseCount = bfs(player, loseGraph);

            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }
        return answer;
    }

    private int bfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (visited[next]) {
                    continue;
                }

                queue.offer(next);
                visited[next] = true;
                count++;
            }
        }
        return count;
    }
}