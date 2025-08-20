package object;

public class JavaMain {
    public static void main(String[] args) {
        // companion object에 이름이 없는 경우
        // Person.Companion.newBaby("Kotlin Baby");

        // companion object에 이름이 있ㄲ는 경우
        Person.Factory.newBaby("Kotlin Baby");

        // @JvmStatic
        Person.newBaby("Kotlin New Baby");


        moveSomething(new Movable() {
            @Override
            public void move() {
                System.out.println("move");
            }

            @Override
            public void fly() {
                System.out.println("fly");
            }
        });
    }

    private static void moveSomething(Movable movable) {
        movable.move();
        movable.fly();
    }
}

