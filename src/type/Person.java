package type;

import org.jetbrains.annotations.Nullable;

public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static void printAGeIfPerson(Object obj) {
        // instanceof 연산자를 사용하여 obj가 Person 타입인지 확인
        if (obj instanceof Person) {
            // obj를 Person 타입으로 캐스팅
            Person person = (Person) obj;
            System.out.println(person.getAge());

            // obj.getAge() 불가능
        }

        // if (obj instanceof Person)의 반대 경우
        if (!(obj instanceof Person)) {

        }
    }
}
