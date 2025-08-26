package operator;

public record JavaMoney(long amount) implements Comparable<JavaMoney> {

    public JavaMoney plus(JavaMoney other) {
        return new JavaMoney(this.amount + other.amount);
    }

    @Override
    public int compareTo(JavaMoney o) {
        return Long.compare(this.amount, o.amount);
    }
}
