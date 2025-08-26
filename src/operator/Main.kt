package operator

fun main () {
    val money1 = JavaMoney(2_000L)
    val money2 = JavaMoney(1_000L)

    if (money1 > money2) {
        println("money1이 money2보다 금액이 큽니다")
    }

    val money3 = JavaMoney(2_000L)
    val money4 = money3
    val money5 = JavaMoney(2_000L)

    println("money3와 money4는 같은 객체: " + (money3 === money4))
    println("money3와 money5는 같은 객체: " + (money3 === money5))
    println("money3와 money5는 같은 값: " + (money3 == money5))

    if (fun1() || fun2()) {
        println("본문")
    }

    if (fun2() && fun1()) {
        println("본문")
    }

    val money6 = KotlinMoney(1_000L)
    val money7 = KotlinMoney(2_000L)
    println(money6 + money7)
}

fun fun1(): Boolean {
    println("fun1() 호출됨")
    return true
}

fun fun2(): Boolean {
    println("fun2() 호출됨")
    return false
}