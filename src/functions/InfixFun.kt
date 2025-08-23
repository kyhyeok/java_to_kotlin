package functions

fun main() {
    3.add(4)

    3.add2(4)

    3 add2 4
}

inline fun Int.add(other: Int): Int {
    return this + other
}

infix fun Int.add2(other: Int): Int {
    return this + other
}