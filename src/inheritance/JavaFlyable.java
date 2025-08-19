package inheritance;

public interface JavaFlyable {
    default void act() {
        System.out.println("날아날아");
    }

    // void fly(); 추상 메서드
}
