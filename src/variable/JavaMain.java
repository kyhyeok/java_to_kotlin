package variable;

import java.util.Arrays;
import java.util.List;

public class JavaMain {
    public static void main(String[] args) {
        long number1 = 10L;
        // 기본형

        final long number2 = 10L;

        Long number3 = 1_000L;
        // 참조형

        long number4 = number2 + number3;
        // 기본형과 참조형의 연산은 기본형으로 변환되어 처리됨, number3는 자동 언박싱됨

        final List<Integer> numbers = Arrays.asList(1, 2);
        numbers.add(3); // UnsupportedOperationException 런타임 에러 발생, final이지만 내부 요소는 변경 가능
        // 요점은 final이 객체의 참조를 변경하지 못하게 할 뿐, 객체 내부의 상태는 변경할 수 있다는 것

        /*
         * 조금 더 풀어서
         */

        Person person = new Person("혁");
        // 객체 인스턴스화를 위해서 new 키워드를 사용
    }
}