import java.util.*;

class 단어변환 {
    public int solution(String begin, String target, String[] words) {
        // 단어를 저장하지 않고 words 배열의 인덱스로 저장
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        int[] dist = new int[words.length];

        // begin에서 알파벳 한 개만 바꿔서 변환할 수 있는 단어를 큐에 넣고 bfs 시작
        for (int i = 0; i < words.length; i++) {
            if (canConvert(begin, words[i])) {
                queue.offer(i);
                visited[i] = true;
                dist[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if(words[cur].equals(target)) {
                return dist[cur];
            }

            for (int next = 0; next < words.length; next++) {
                if (!visited[next] && canConvert(words[cur], words[next])) {
                    queue.offer(next);
                    visited[next] = true;
                    dist[next] = dist[cur] + 1;
                }
            }
        }

        return 0;
    }

    private boolean canConvert(String from, String to) {
        int diff = 0;
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) != to.charAt(i)) {
                diff++;

                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}