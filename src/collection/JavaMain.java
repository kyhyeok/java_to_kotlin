package collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaMain {
    public static void main(String[] args) {
        final List<Integer> numbers = List.of(100, 200);

        // 하나를 가져오기
        System.out.println(numbers.get(0));

        // For Each
        for (Integer number : numbers) {
            System.out.println(number);
        }

        // 전통적인 For문
        for (int i = 0; i < numbers.size(); i++) {
            System.out.printf("%s %s \n", i, numbers.get(i));
        }

        // JDK 8 까지
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Monday");
        map.put(2, "Tuesday");

        // JDK 9
        Map.of(1, "Monday", 2, "Tuesday");

        for (int key: map.keySet()) {
            System.out.println(key + " " + map.get(key));
        }

        for(Map.Entry<Integer, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
