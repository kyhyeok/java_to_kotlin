package functions

fun main() {
    val str = "ABCDEFG"
    str.lastChar() // String에 존재했던 멤버 함수처럼 사용할 수 있다
    println(str.lastChar()) // G
}

fun String.lastChar(): Char {
    return this[this.length - 1] // 문자열이 존재하는 가장 마지막 문자열 반환
}

val String.lastChar: Char
    get() = this[length - 1]