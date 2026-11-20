import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        // 프로세스 순서를 인덱스로 저장한 큐
        Queue<Integer> queue = new ArrayDeque<>();

        // count[p]: 아직 실행하지 않은 우선순위 p의 프로세스 수
        int[] count = new int[10];
        int highest = 0;

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(i);
            count[priorities[i]]++;
            highest = Math.max(highest, priorities[i]);
        }

        int executed = 0;

        while(!queue.isEmpty()){
            int index = queue.poll();
            int priority = priorities[index];

            // 더 높은 우선순위의 프로세스가 남아 있다면 큐의 맨 뒤로 이동
            if (priority < highest) {
                queue.offer(index);
                continue;
            }

            // 현재 프로세스가 최고 우선순위라면 실행
            executed++;
            count[priority]--;

            if (index == location) {
                return executed;
            }

            // 남아 있는 프로세스의 최고 우선순위 갱신
            while (highest > 0 && count[highest] == 0) {
                highest--;
            }
        }

        return 0;
    }
}