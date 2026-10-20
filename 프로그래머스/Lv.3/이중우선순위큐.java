import java.util.*;

class 이중우선순위큐 {
    public int[] solution(String[] operations) {

        // 최솟값과 최댓값을 모두 빠르게 찾아야 하므로 TreeMap 사용
        // TreeMap: 키 오름차순 정렬, map.firstKey(): 최솟값/ map.lastKey(): 최댓값
        TreeMap<Integer,Integer> map = new TreeMap<>();

        for (String operation : operations) {
            char command =  operation.charAt(0);
            int num = Integer.parseInt(operation.substring(2));

            if (command == 'I') {
                // 키가 있으면 해당 값을 반환하여 +1로 덮어씀, 없으면 기본값 0으로 추가됨
                // map.merge(num, 1, Integer::sum)으로도 사용 가능
                // 위는 키가 없으면 값 새로 지정, 키가 있으면 기존 값과 새로운 값 결합
                map.put(num, map.getOrDefault(num, 0) + 1);
                continue;
            }

            if (map.isEmpty()) {
                continue;
            }

            int target;
            if (num == 1) {
                target = map.lastKey(); // 최댓값
            } else {
                target = map.firstKey(); // 최솟값
            }

            int count = map.get(target);
            if (count == 1) {
                map.remove(target);
            } else {
                map.put(target, count - 1);
            }
        }

        if (map.isEmpty()) {
            return new int[]{0, 0};
        }

        return new int[]{map.lastKey(), map.firstKey()};
    }
}