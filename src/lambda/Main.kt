package lambda

fun main() {
    val fruits = listOf(
        Fruit("사과", 1000),
        Fruit("사과", 1200),
        Fruit("사과", 1200),
        Fruit("사과", 1500),
        Fruit("바나나", 3000),
        Fruit("바나나", 3200),
        Fruit("바나나", 2500),
        Fruit("수박", 10000)
    )

    // 람다를 만드는 첫 번째 방법: fun 키워드 (익명 함수)
    val isApple = fun(fruit: Fruit): Boolean {
        return fruit.name == "사과"
    }

    // 람다를 만드는 두 번째 방법: 중괄호 + 화살표 (일반적)
    val isApple2 = { fruit: Fruit -> fruit.name == "사과"}

    // 람다를 직업 호출하는 첫 번째 방법: 직접 호출
    isApple(fruits[0])
    isApple(Fruit("사과", 1000))

    // 람다를 직업 호출하는 두 번째 방법: invoke() 사용 (명시적)
    isApple.invoke(fruits[0])
    isApple.invoke(Fruit("사과", 1000))

    filterFruits(fruits, isApple)

    filterFruits(fruits, isApple2)

    filterFruits(fruits, { fruit: Fruit -> fruit.name == "사과"})

    filterFruits(fruits) { fruit: Fruit -> fruit.name == "사과"}

    filterFruits(fruits) { fruit -> fruit.name == "사과"}

    filterFruits(fruits) { a -> a.name == "사과"} // 이름 변경 가능
    filterFruits(fruits) { it.name == "사과"} // 파라미터가 한개인 경우 it 사용 가능

    var targetFruitName = "바나나"
    targetFruitName = "수박"
    filterFruits(fruits) { it.name == targetFruitName }
}


private fun filterFruits(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    val results = mutableListOf<Fruit>()
    for (fruit in fruits) {
        if (filter(fruit)) {
            results.add(fruit)
        }
    }
    return results
}