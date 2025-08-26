package collectionfunctions

fun main() {
    val fruits = listOf(
        Fruit(1L, "사과", 1000, 1000),
        Fruit(2L, "사과", 1200, 1200),
        Fruit(3L, "사과", 6200, 6200),
        Fruit(4L, "바나나", 3200, 3200),
        Fruit(5L, "바나나", 2500, 2500),
        Fruit(6L, "수박", 10000, 10000)
    )

    println("filterFruits")
    println(filterFruits(fruits))
    println()

    println("filterFruitsLambda")
    println(filterFruitsLambda(fruits, { fruit -> fruit.name == "사과" }))
    println()

    println("filterIndexedFruits")
    println(filterIndexedFruits(fruits))
    println()

    println("filterIndexedFruitsLambda")
    println(
        filterIndexedFruitsLambda(
            fruits, { idx, fruit ->
                print("${idx} ")
                fruit.name == "사과"
            })
    )
    println()


    println("filterMapFruits")
    println(filterMapFruits(fruits))
    println()

    println("filterMapFruitsLambda")
    println(
        filterMapFruitsLambda(fruits, { fruit -> fruit.name == "사과" }, { fruit -> fruit.currentPrice })
    )
    println()

    println("filterMapIndexedFruits")
    println(filterMapIndexedFruits(fruits))
    println()

    println("filterMapIndexedFruitsLambda")
    println(
        filterMapIndexedFruitsLambda(fruits, { fruit -> fruit.name == "사과" }, { idx, fruit ->
            print("${idx} ")
            fruit.currentPrice
        })
    )
    println()

    println("filterMapNotNullFruits")
    println(filterMapNotNullFruits(fruits))
    println()

    println("filterMapNotNullFruitsLambda")
    println(
        filterMapNotNullFruitsLambda(
            fruits,
            { fruit -> fruit.name == "사과" },
            { fruit -> fruit.nullOrValue() },
        )
    )
    println()
}

private fun filterFruits(
    fruits: List<Fruit>
): List<Fruit> {
    return fruits.filter { fruit -> fruit.name == "사과" } // 사과만 filter
}

private fun filterFruitsLambda(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean
): List<Fruit> {
    return fruits.filter(filter) // 사과만 filter
}

// filter를 하는 중 index가 필요할 경우
private fun filterIndexedFruits(
    fruits: List<Fruit>
): List<Fruit> {
    return fruits.filterIndexed { idx, fruit ->
        print("${idx} ")
        fruit.name == "사과"
    }
}

private fun filterIndexedFruitsLambda(
    fruits: List<Fruit>, filterIndexed: (Int, Fruit) -> Boolean
): List<Fruit> {
    return fruits.filterIndexed(filterIndexed)
}

private fun filterMapFruits(
    fruits: List<Fruit>,
): List<Long> {
    return fruits.filter { fruit -> fruit.name == "사과" } // 사과만 filter
        .map { fruit -> fruit.currentPrice } // map을 통해 currentPrice만 반환
}

private fun filterMapFruitsLambda(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean, map: (Fruit) -> Long
): List<Long> {
    return fruits.filter(filter) // 사과만 filter
        .map(map) // map을 통해 currentPrice만 반환
}

// filter과 Map을 하는 중 index가 필요할 경우
private fun filterMapIndexedFruits(
    fruits: List<Fruit>
): List<Long> {
    return fruits.filter { fruit -> fruit.name == "사과" }.mapIndexed { idx, fruit ->
        print("${idx} ")
        fruit.currentPrice
    }
}

private fun filterMapIndexedFruitsLambda(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean, mapIndexed: (Int, Fruit) -> Long
): List<Long> {
    return fruits.filter(filter).mapIndexed(mapIndexed)
}

// null일 수도 아닐수도 있는데 null이 아닌 것만 골라서 반환 (Fruit에서 mapNotNull()는 멤버 함수를 만듬)
private fun filterMapNotNullFruits(
    fruits: List<Fruit>
): List<Fruit> {
    return fruits.filter { fruit -> fruit.name == "사과" }.mapNotNull { fruit -> fruit.nullOrValue() }
}

private fun filterMapNotNullFruitsLambda(
    fruits: List<Fruit>, filter: (Fruit) -> Boolean, mapNotNull: (Fruit) -> Fruit?
): List<Fruit> {
    return fruits.filter(filter).mapNotNull(mapNotNull)
}