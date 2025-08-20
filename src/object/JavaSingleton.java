package object;

public class JavaSingleton {
    // 클래스 로딩 시 즉시 인스턴스 생성(Eager Initialization)
    // Static final로 불변성과 단일성 보장
    private static final JavaSingleton INSTANCE = new JavaSingleton();

    // private 생성자로 외부에서 new 키워드를 통한 인스턴스 생성 차단
    private JavaSingleton() {}

    // 유일한 인스턴스에 접근할 수 있는 전역 접근점 제공
    // static 메서드로 인스턴스 없어도 호출 가능
    public static JavaSingleton getInstance() {
        return INSTANCE;
    }
}
