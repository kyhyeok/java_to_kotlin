package nesting;

public class JavaMain {
    public static void main(String[] args) {
        JavaHouse house = new JavaHouse("서울");
        System.out.println(house.getLivingRoom().getAddress());
    }
}
