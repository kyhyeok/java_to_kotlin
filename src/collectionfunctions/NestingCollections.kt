package collectionfunctions

fun main() {
    val fruitsInList: List<List<Fruit>> = listOf(
        listOf(
            Fruit(1L, "사과", 1000, 1000),
            Fruit(2L, "사과", 1200, 1250),
            Fruit(3L, "사과", 6200, 6200),
            Fruit(4L, "사과", 15000, 15500),
        ),
        listOf(
            Fruit(5L, "바나나", 3200, 3200),
            Fruit(6L, "바나나", 2500, 2800),
            Fruit(7L, "바나나", 4500, 5000),
        ),
        listOf(
            Fruit(8L, "수박", 10000, 10000),
        )
    )

    // 출고가와 현재가가 동일한 과일 고르기
    val samePriceFruits = fruitsInList.flatMap { list ->
        list.filter { fruit -> fruit.factoryPrice == fruit.currentPrice }
    }
    println(samePriceFruits)

    val samePriceFruitsExtensionFunction = fruitsInList.flatMap { list -> list.samePriceFilter }
    println(samePriceFruitsExtensionFunction)

    // List<List<Fruit>> -> List<Fruit>로 변경 (평탄화 작업)
    fruitsInList.flatten()
}