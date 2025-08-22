package collection

fun main() {
    val numbers = setOf(100, 200)

    val mutableNumbers = mutableSetOf(100, 200)

    // For Each
    for (number in numbers) {
        println(number)
    }

    // 전통적인 for문
    for ((idx, value) in numbers.withIndex()) {
        println(" $idx $value")
    }
}