package inheritance;

public interface JavaSwimable {
    default void act() {
        System.out.println("수영수영");
    }

    // void swim(); 추상 메서드
}
