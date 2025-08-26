package collectionfunctions

data class Fruit(
    val id: Long,
    val name: String,
    val factoryPrice: Long,
    val currentPrice: Long,
) {
    fun nullOrValue(): Fruit? {
        return if (currentPrice >= 5_000) {
            null // 비싼 과일은 제외
        } else {
            this // 적정 가격 과일만 반환
        }
    }

    val isSamePrice: Boolean
        get() = factoryPrice == currentPrice

}

val List<Fruit>.samePriceFilter: List<Fruit>
    get() = this.filter(Fruit::isSamePrice)