package variable

fun main() {
    var number1 = 10L
    //  Java - long number1 = 10L;
    // 타입을 자동으로 추론해준다.

    var number2: Long = 10L
    // 타입을 명시적으로 지정할 수 있다.

    val number3 = 10L
    // Java - final long number3 = 10L;

    val number4: Long = 10L

    // var number5 컴파일 에러 - 타입을 추론할 수 없음
    var number5: Long // 명시적 타입 지정으로 해결
    // println(number5) 컴파일 에러 - 초기화 전 사용 불가
    number5 = 10L // 초기화 후
    println(number5) // 사용 가능

    val number6: Long
    number6 = 10L
    // val은 불변 이지만 최초 한 번은 값을 넣어줄 수 있다.
    println(number6)

    // val 컬렌셕에는 element를 추가할 수 있다
    // val은 참조를 고정하지만, 객체 내부 상태는 변경 가능
    val list = mutableListOf(1, 2, 3)
    list.add(4) // 가능 - 내부 상태 변경
    // list = mutableListOf(5, 6) 불가능 - 참조 변경

    var number7 = 10L
    var number8 = 1_000L
    var number9 = number7 + number8
    // Kotlin에서는 primitive와 wrapper 구분 없이 자동 처리

    // var number10 = 1_000L
    // number10 = null
    // null 안됨

    // var number11: Long = 1_000L
    // number11 = null
    // null 안됨

    var number12: Long? = 1_000L
    number12 = null
    // null 가능 타입은 ?를 붙여서 선언한다.
    // 사용 시에는 null 체크 필요: number12?.toString()

    val person = Person("혁")
    // 객체 인스턴스화를 위해서 new가 필요하지 않음
}