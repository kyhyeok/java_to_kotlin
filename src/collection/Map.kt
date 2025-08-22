package collection

fun main() {
    val oldMap = mutableMapOf<Int, String>() // 타입을 추론할 수 없어, 타입 지정
    oldMap.put(1, "Monday") // Java처럼 put사용
    oldMap.put(2, "Tuesday")

    oldMap[3] = "Wednesday" // map[key] = value 사용

    // 중위 호출 방식으로 mapOf는 Pair를 받는 함수이다
    // mapOf(key to value)를 사용해 불변 map 생성
    mapOf(1 to "Monday", 2 to "Tuesday", 3 to "Wednesday")

    for (key in oldMap.keys) {
        println("$key -> ${oldMap[key]}")
    }

    for ((key, value) in oldMap.entries) {
        println("$key -> $value")
    }
}