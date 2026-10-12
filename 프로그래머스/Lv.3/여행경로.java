import java.util.*;

class 여행경로 {
    public String[] solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();

        // map -> 출발 공항별로 도착 공항 저장
        // PriorityQueue -> 도착 공항 알파벳순 정리
        for (String[] ticket : tickets) {
            // 키가 Map에 없을 경우 새 PriorityQueue 생성
            graph.computeIfAbsent(ticket[0], k -> new PriorityQueue<>())
                    .offer(ticket[1]);
        }

        // DFS 이동 경로
        Deque<String> stack = new ArrayDeque<>();
        // 완성된 최종 경로
        Deque<String> route = new ArrayDeque<>();

        stack.push("ICN");

        while (!stack.isEmpty()) {
            // 현재 공항에서 사용할 수 있는 항공권 남아 있는지 확인해야 하므로 공항 제거 X
            String cur = stack.peek();
            PriorityQueue<String> dest = graph.get(cur);

            if (dest != null && !dest.isEmpty()) {
                // 항공권 중 알파벳순 공항 선택
                stack.push(dest.poll());
            } else {
                // 항공권 더 없는 경우 최종 경로 앞에 추가, 이 때 stack에서 제거
                route.addFirst(stack.pop());
            }
        }

        // toArray()의 반환 타입은 Object[]이므로 String[]으로 타입 알려주기 위해 String[0] 전달
        String[] result = route.toArray(new String[0]);
        return result;
    }
}