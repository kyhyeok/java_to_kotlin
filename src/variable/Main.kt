package variable

fun main() {
    var number1 = 10L
    //  Java - long number1 = 10L;
    // 타비을 자동으로 추론해준다.

    var number2: Long = 10L
    // 타입을 명시적으로 지정할 수 있다.

    val number3 = 10L
    // Java - final long number2 = 10L;

    val number4: Long = 10L

    // var number5는 컴파일 에러 - 타입을 추론할 수 없을 때는 타입을 명시적으로 지정해야 한다.
    var number5: Long
    // println(number5) 초기화 되지 않은 변수는 사용할 수 없다.
    number5 = 10L
    println(number5)

    val number6: Long
    number6 = 10L
    // val은 불변 이지만 최초 한 번은 값을 넣어줄 수 있다.
    println(number6)

    // val 컬렌셕에는 element를 추가할 수 있다

    var number7 = 10L
    var number8 = 1_000L
    var number9 = number7 + number8
    // boxing과 unboxing이 자동으로 이루어진다.

    // var number10 = 1_000L
    // number10 = null
    // null 안됨

    // var number11: Long = 1_000L
    // number11 = null
    // null 안됨

    var number12: Long? = 1_000L
    number12 = null
    // null 가능 타입은 ?를 붙여서 선언한다.

    var person = Person("혁")
    // 객체 인스턴스화를 위해서 new가 필요하지 않음
}