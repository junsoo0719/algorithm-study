import java.util.*;

class Solution {

    private static class Truck {
        final int weight; // 트럭 무게
        final int existTime; // 다리에서 나가는 시각

        Truck(int weight, int existTime) {
            this.weight = weight;
            this.existTime = existTime;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Truck> bridge = new ArrayDeque<>();
        int curWeight = 0;
        int time = 0;

        for (int truckWeight : truck_weights) {
            // 첫 트럭은 1초에 진입, 이후에도 한 초에 한 대만 진입
            time++;

            while (true) {
                // 같은 시각에 나가는 트럭을 먼저 처리
                while (!bridge.isEmpty() && bridge.peek().existTime <= time) {
                    curWeight -= bridge.poll().weight;
                }

                if (bridge.size() < bridge_length && curWeight + truckWeight <= weight) {
                    break;
                }

                // 다음 트럭이 나가기 전까지는 다리의 상태가 바뀌지 않음
                time = bridge.peek().existTime;
            }

            bridge.offer(new Truck(truckWeight, time + bridge_length));
            curWeight += truckWeight;
        }

        // time은 마지막 트럭이 진입한 시각
        return time + bridge_length;
    }
}