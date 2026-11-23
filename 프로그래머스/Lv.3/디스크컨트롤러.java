import java.util.*;

class Solution {
    private static class Job {
        final int number;
        final int requestTime;
        final int duration;

        public Job(int number, int requestTime, int duration) {
            this.number = number;
            this.requestTime = requestTime;
            this.duration = duration;
        }
    }
    public int solution(int[][] jobs) {
        int count = jobs.length;
        Job[] sortedJobs = new Job[count];

        // 우선순위 정렬 전 원래 인덱스를 작업번호로 저장 후 요청 시각순 정렬
        for (int i = 0; i < count; i++) {
            sortedJobs[i] = new Job(i, jobs[i][0], jobs[i][1]);
        }

        Arrays.sort(sortedJobs,
                Comparator.comparingInt((Job job) -> job.requestTime)
                        .thenComparingInt(job -> job.number)
        );

        // 소요 시간 -> 요청 시각 -> 작업 번호순으로 우선순위 결정
        PriorityQueue<Job> waiting = new PriorityQueue<>(
                Comparator.comparingInt((Job job) -> job.duration)
                        .thenComparingInt(job -> job.requestTime)
                        .thenComparingInt(job -> job.number)
        );

        int time = 0;
        int next = 0;
        int completed = 0;
        long totalTurnAround = 0;

        while (completed < count) {
            // 대기 작업이 없다면 다음 요청 시각으로 이동
            if (waiting.isEmpty() && next < count) {
                time = Math.max(time, sortedJobs[next].requestTime);
            }

            // 현재 시각까지 요청된 작업을 모두 추가
            // 작업이 끝나는 시각에 들어온 요청도 포함
            while (next < count && sortedJobs[next].requestTime <= time) {
                waiting.offer(sortedJobs[next]);
                next++;
            }

            // 우선순위가 가장 높은 작업을 끝까지 실행
            Job cur =  waiting.poll();
            time += cur.duration;

            // 반환 시간 = 종료 시각 - 요청 시각
            totalTurnAround += time - cur.requestTime;
            completed++;
        }

        return (int) (totalTurnAround / count);
    }
}