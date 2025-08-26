package collectionfunctions


fun main() {
    val fruits = listOf(
        Fruit(1L, "사과", 1000, 1000),
        Fruit(2L, "사과", 1200, 1200),
        Fruit(3L, "사과", 6200, 6200),
        Fruit(4L, "바나나", 3200, 3200),
        Fruit(5L, "바나나", 2500, 2500),
        Fruit(6L, "수박", 10000, 10000),
        Fruit(7L, "사과", 15000, 15000),
        Fruit(8L, "딸기", 8000, 9000),
    )

    // 과일이름 -> List<Fruit> Map이 필요
    val listToMapName: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
    println(listToMapName)
    // {사과=[Fruit(id=1, name=사과, factoryPrice=1000, currentPrice=1000), Fruit(id=2, name=사과, factoryPrice=1200, currentPrice=1200), Fruit(id=3, name=사과, factoryPrice=6200, currentPrice=6200), Fruit(id=7, name=사과, factoryPrice=15000, currentPrice=15000)], 바나나=[Fruit(id=4, name=바나나, factoryPrice=3200, currentPrice=3200), Fruit(id=5, name=바나나, factoryPrice=2500, currentPrice=2500)], 수박=[Fruit(id=6, name=수박, factoryPrice=10000, currentPrice=10000)], 딸기=[Fruit(id=8, name=딸기, factoryPrice=8000, currentPrice=9000)]}

    // id -> 과일 Map이 필요
    // id를 통해서 맵핑(중복되지 않는 키) map을 만들 때 사용한다
    val itToMap: Map<Long, Fruit> = fruits.associateBy { fruit -> fruit.id }
    println(itToMap)

    val nameToFactoryPriceMap: Map<String, List<Long>> = fruits
        .groupBy({ fruit -> fruit.name }, { fruit -> fruit.factoryPrice })
    println(nameToFactoryPriceMap)

    val idToFactoryPriceMap: Map<Long, Long> = fruits
        .associateBy({ fruit -> fruit.id }, { fruit -> fruit.factoryPrice })
    println(idToFactoryPriceMap)

    val mapGroupByFilter: Map<String, List<Fruit>> = fruits.groupBy { fruit -> fruit.name }
        .filter { (key, value) -> key == "사과" }
    println(mapGroupByFilter)
}