import java.util.*;

class 주차요금계산 {
    public int[] solution(int[] fees, String[] records) {

        // 차량 번호 순 결과 반환 -> TreeMap
        // 정렬 필요 없이 입차 시각만 저장, 조회 -> HashMap
        Map<String, Integer> totalTimes = new TreeMap<>();
        Map<String, Integer> enterTimes = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");

            int time = toMinutes(parts[0]);
            String carNumber = parts[1];
            String type =  parts[2];

            totalTimes.putIfAbsent(carNumber, 0);

            if (type.equals("IN")) {
                enterTimes.put(carNumber, time);
            } else {
                int enterTime = enterTimes.remove(carNumber);
                int parkingTime = time - enterTime;

                totalTimes.put(
                        carNumber,
                        totalTimes.get(carNumber) + parkingTime
                );
            }
        }

        // 출차 기록이 없는 차량은 23:59에 출차된 것으로 간주
        int endOfDay = toMinutes("23:59");

        for (Map.Entry<String, Integer> entry : enterTimes.entrySet()) {
            String carNumber = entry.getKey();
            int enterTime = entry.getValue();
            int parkingTime = endOfDay - enterTime;

            totalTimes.put(
                    carNumber,
                    totalTimes.get(carNumber) + parkingTime
            );
        }

        int[] answer = new int[totalTimes.size()];
        int index = 0;

        for (int totalTime : totalTimes.values()) {
            answer[index++] = calculateFee(totalTime, fees);
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");

        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }

    private int calculateFee(int totalTime, int[] fees) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (totalTime <= basicTime) {
            return basicFee;
        }

        int extraTime = totalTime - basicTime;

        // Math.ceil 대신 정수 연산으로 올림
        int unitCount = (extraTime + unitTime - 1) / unitTime;

        return basicFee + unitCount * unitFee;
    }
}