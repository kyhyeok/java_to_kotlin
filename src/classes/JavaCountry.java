package classes;

public enum JavaCountry {

    KOREA("KO"),
    AMERICA("US");

    private final String code;

    JavaCountry(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    private static void handleCountry(JavaCountry country) {
        if (country == JavaCountry.KOREA) {
            System.out.println("KOREA 로직");
        }

        if (country == JavaCountry.AMERICA) {
            System.out.println("AMERICA 로직");
        }

        // 새로운 enum 값 추가 시 컴파일러가 알려주지 않음
        // else 처리가 애매함 (enum이므로 다른 값이 올 가능성 없음)
    }

}
