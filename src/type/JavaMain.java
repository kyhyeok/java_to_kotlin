package type;

public class JavaMain {
    public static void main(String[] args) {
        int number1 = 4;
        long number2 = number1;

        // System.out.println(number1 + number2);
        // integer 타입을 long 타입으로 암시적으로 변환되었다. Java는 작은 타입에서 큰 타입으로의 변환을 자동으로 수행한다.
        // 하지만, 큰 타입에서 작은 타입으로의 변환은 명시적으로 해야 한다

        Person person = new Person("혁", 5);
        String log = String.format("사람의 이름은 %s이고 나이는 %s세 입니다", person.getName(), person.getAge());

        StringBuilder builder = new StringBuilder();
        builder.append("사람의 이름은 ");
        builder.append(person.getName());
        builder.append("이고 나이는 ");
        builder.append(person.getAge());
        builder.append("세 입니다");

        String str = "ABCDE";
        char ch = str.charAt(1); // B
    }
}
