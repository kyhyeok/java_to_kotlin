package inheritance

fun main() {
    Derived(300) // Base Class → 0 → Derived Class
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)  // 0 출력! (예상: 300)
    }
}


class Derived(
    override val number: Int,
) : Base(number) {
    init {
        println("Derived Class")
    }
}

/*
open class Base(val number: Int = 100) {  // final로 설계
    init {
        println(number)  // 안전하게 예상 값 출력
    }
}
*/