package operator;

public class JavaMain {
    public static void main(String[] args) {
        JavaMoney money1 = new JavaMoney(2_000L);
        JavaMoney money2 = new JavaMoney(1_000L);

        if (money1.compareTo(money2) > 0) {
            System.out.println("money1이 money2보다 금액이 큽니다");
        }

        JavaMoney money3 = new JavaMoney(1_000L);
        JavaMoney money4 = money3;
        JavaMoney money5 = new JavaMoney(1_000L);

        System.out.println("money3와 money4는 같은 객체: " + (money3 == money4));
        System.out.println("money3와 money5는 같은 객체: " + (money3 == money5));
        System.out.println("money3와 money5는 같은 값: " + (money3.equals(money5)));

        JavaMoney money6 = new JavaMoney(1_000L);
        JavaMoney money7 = new JavaMoney(2_000L);
        System.out.println("money6 + money7 = " + money6.plus(money7));
    }
}
