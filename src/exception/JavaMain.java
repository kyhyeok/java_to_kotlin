package exception;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JavaMain {
    public static void main(String[] args) throws IOException {
        JavaFilterPrinter printer = new JavaFilterPrinter();
        printer.readFile();
    }

    private int parseIntOrThrow(@NotNull String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("주어진 %s는 숫자가 아닙니다", str));
        }
    }

    private Integer parseIntOrThrowNull(@NotNull String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // 예외를 던지지 않고 null을 반환
            return null;
        }
    }
}
