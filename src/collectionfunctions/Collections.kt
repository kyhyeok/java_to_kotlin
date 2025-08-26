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

    val allApples = listOf(
        Fruit(1L, "사과", 1000, 1000),
        Fruit(2L, "사과", 1200, 1200),
    )

    // all: 조건을 모두 만족하면 true 그렇지 않으면 false
    // 모든 과일이 사과인가 아닌가
    val isAllApple = fruits.all { fruit -> fruit.name == "사과" }
    println(isAllApple) // false

    val isAllAppleTest = allApples.all { fruit -> fruit.name == "사과" }
    println(isAllAppleTest) // true

    // none: 조건을 모두 불만족하면 true 그렇지 않으면 false
    // 모든 과일이 사과가 아닌가
    val isNoneApple = fruits.none { fruit -> fruit.name == "사과" }
    println(isNoneApple) // false

    // any: 조건을 하나라도 만족하면 true 그렇지 않으면 false
    // factoryPrice가 10,000원 이상의 과일이 하나라도 있나?
    val hasExpensiveFruit = fruits.any { fruit -> fruit.factoryPrice >= 10000 }
    println(hasExpensiveFruit)// true

    // count: 개수를 센다
    // 총 과일의 수는 몇개?
    val totalFruitCount = fruits.count()
    println(totalFruitCount) // 8

    // sortedBy: (오름차순) 정렬을 한다
    // 낮은 가격 순 - currentPrice
    val sortedByLowPrice = fruits.sortedBy { fruit -> fruit.currentPrice }
    println(sortedByLowPrice)

    // sortedByDescending: (내림차순) 정렬을 한다
    // 높은 가격 순 - currentPrice
    val sortedByHighPrice = fruits.sortedByDescending { fruit -> fruit.currentPrice }
    println(sortedByHighPrice)

    // distinctBy: 변형된 값을 기준으로 중복을 제거한다
    // 중복 제거 후 이름만 반환
    val distinctFruitNames = fruits.distinctBy { fruit -> fruit.name }
        .map { fruit -> fruit.name }
    println(distinctFruitNames) // [사과, 바나나, 딸기, 수박]

    // first: 첫번째 값을 가져온다 (무조건 null이 아니여야함)
    // 첫 번재 과일만
    val firstFruit = fruits.first()
    println(firstFruit) // Fruit(id=1, name=사과, factoryPrice=1000, currentPrice=1000)

    // firstOrNull: 첫번째 값 또는 null을 가져온다
    val firstFruitOrNull = fruits.firstOrNull()
    println(firstFruitOrNull) // Fruit(id=1, name=사과, factoryPrice=1000, currentPrice=1000)

    // last: 마지막 값을 가져온다 (무조건 null이 아니여야함)
    // 마지막 과일만
    val lastFruit = fruits.last()
    println(lastFruit) // Fruit(id=8, name=딸기, factoryPrice=8000, currentPrice=9000)

    // lastOrNull: 마지막 값 또는 null을 가져온다
    val lastFruitOrNull = fruits.lastOrNull()
    println(lastFruitOrNull) // Fruit(id=8, name=딸기, factoryPrice=8000, currentPrice=9000)

    // 조건에 맞는 첫 번째 / 마지막 과일
    val firstApple = fruits.first { fruit -> fruit.name == "사과" }
    println(firstApple) // Fruit(id=1, name=사과, factoryPrice=1000, currentPrice=1000)

    val lastApple = fruits.last { fruit -> fruit.name == "사과" }
    println(lastApple) // Fruit(id=7, name=사과, factoryPrice=15000, currentPrice=15000)

    // 안전한 버전
    val firstExpensiveFruit = fruits.firstOrNull { fruit -> fruit.currentPrice >= 10000 }
    println(firstExpensiveFruit) // Fruit(id=6, name=수박, factoryPrice=10000, currentPrice=10000)

    // 특정 조건의 개수
    val appleCount = fruits.count { fruit -> fruit.name == "사과" }
    println(appleCount) // 4
}