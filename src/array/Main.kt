package array

fun main() {
    val array = arrayOf(100, 200)

    // index를 활용하는 방법
    for(i in array.indices) {
        println(" ${i} ${array[i]}")
        // 0 100
        // 1 200
    }
    println()

    // array.withIndex() 인덱스와 value를 한 번에 받는다
    for((idx, value) in array.withIndex()) {
        println(" $idx $value")
        // 0 100
        // 1 200
    }
    println()

    // 배열에 값 추가
    val newArray = array.plus(300)
    for((idx, value) in newArray.withIndex()) {
        println(" $idx $value")
        // 0 100
        // 1 200
        // 2 300
    }

}