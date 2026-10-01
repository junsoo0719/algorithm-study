import java.util.*;

class 모음사전 {
    public int solution(String word) {

        // 각 자리 가중치(자리 별 모음 하나 건너뛸 때 건너뛰는 단어 수)
        // 5번째 자리: 5^0 = 1
        // 4번째 자리: 1 + 5^1 = 6
        // 3번째 자리: 6 + 5^2 = 31
        // 2번째 자리: 31 + 5^3 = 156
        // 1번째 자리: 156 + 5^4 = 781
        int[] weights = {781, 156, 31, 6, 1};

        // 모음 순서 - 인덱스로 지정
        String vowels = "AEIOU";

        int answer = 0;

        for (int i = 0; i < word.length(); i++) {
            int vowelIndex = vowels.indexOf(word.charAt(i));
            // 현재 글자까지 만들어진 단어 자체 포함하기 위해 +1
            answer += vowelIndex * weights[i] + 1;
        }

        return answer;
    }
}