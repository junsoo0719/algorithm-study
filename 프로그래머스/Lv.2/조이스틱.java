import java.util.*;

class Solution {
    public int solution(String name) {

        int length = name.length();

        int verticalMoves = 0;
        // 수평이동 횟수: 오른쪽으로만 이동해서 모든 위치 방문 하는 경우
        // 이후에 다시 왼쪽으로 돌아가는 방법도 고려
        int horizontalMoves = length - 1;

        for (int i = 0; i < length; i++) {
            char target = name.charAt(i);

            // 위로 변경하거나 A->Z 아래로 변경하는 경우
            verticalMoves += Math.min(target - 'A', 'Z' - target + 1);

            // i 다음에 연속된 A 구간 찾아서 건너뛰기
            int next = i + 1;
            while (next < length && name.charAt(next) == 'A') {
                next++;
            }

            // 시작 위치에서 왼쪽으로 돌아 next 위치까지 가는 이동 횟수
            int remaining = length - next;

            // 시작 위치 -> 오른쪽 i 위치 -> 시작점 -> 왼쪽 next 위치
            horizontalMoves = Math.min(horizontalMoves, i * 2 + remaining);
            // 시작위치 -> 왼쪽 next 위치 -> 시작점 -> 오른쪽 i 위치
            horizontalMoves = Math.min(horizontalMoves, remaining * 2 + i);
        }

        return horizontalMoves + verticalMoves;
    }
}