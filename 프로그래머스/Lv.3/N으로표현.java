import java.util.*;

class N으로표현 {
    public int solution(int N, int number) {
        // dp[i]: N을 정확히 i번 사용해서 만들 수 잇는 수
        // 같은 결과가 여러 수식에서 나올 수 있으므로 HashSet 사용
        List<Set<Integer>> dp = new ArrayList<>();

        // 사용 횟수는 최대 8
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        // N을 이어 붙인 수 저장
        int repeatedNumber = 0;

        // N을 적게 사용하는 경우부터 차례대로 확인
        for (int count = 1; count <= 8; count++) {
            // 기존 숫자를 한 자리 왼족으로 이동한 후 N을 붙임
            // 이어 붙인 수는 사칙연산으로 조합하는 경우와 별개로 추가
            repeatedNumber = repeatedNumber * 10 + N;
            dp.get(count).add(repeatedNumber);

            // N을 count번 사용하는 수식을 마지막 사칙연산을 기준으로 왼쪽/오른쪽 수식으로 나눔
            // count = 4라면 사용 횟수 조합은
            // 왼쪽 1번 + 오른쪽 3번/ 왼쪽 2번 + 오른쪽 2번/ 왼쪽 3번 + 오른쪽 1번
            for (int leftCount = 1; leftCount < count; leftCount++) {
                int rightCount = count - leftCount;

                // leftCount와 rightCount는 모두 count보다 작으므로
                // 필요한 집합은 이전 반복에서 이미 완성되어 있음
                for (int left : dp.get(leftCount)) {
                    for (int right : dp.get(rightCount)) {
                        // 두 수식을 연산하면 N을 사용한 총 횟수는 leftCount + rightCount
                        // 0으로 나눌 수 없으므로 나누는 수 확인
                        dp.get(count).add(left + right);
                        dp.get(count).add(left - right);
                        dp.get(count).add(left * right);
                        if (right != 0) {
                            dp.get(count).add(left / right);
                        }
                        // 각 집합의 모든 값 조합을 확인하므로 반대 순서 연산도 모두 처리됨
                    }
                }
            }

            // count번 사용해서 목표 숫자를 만들 수 있는지 확인
            // 사용 횟수를 1부터 증가시켰으므로 처음 목표 숫자를 발견한 count가 최소 사용 횟수
            if (dp.get(count).contains(number)) {
                return count;
            }
        }
        return -1;
    }
}