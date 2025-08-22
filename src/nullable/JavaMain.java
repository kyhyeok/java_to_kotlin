package nullable;

public class JavaMain {
    public static void main(String[] args) {
//        startsWithA(null); NullPointerException 발생
    }

    public static boolean startsWithA(String str) {
        return str.startsWith("A");
    }

    // 예외를 던지는 메서드 & return null을 허용하지 않는 메서드
    public static boolean startsWithException(String str) {
        if (str == null) {
            throw new IllegalArgumentException("null이 들어왔습니다");
        }
        return str.startsWith("A");
    }


    // return null을 허용하는 메서드
    public static Boolean startsWithReturnNull(String str) {
        if (str == null) {
            return null;
        }
        return str.startsWith("A");
    }


    public static boolean startsWithBoolean(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("A");
    }
}
