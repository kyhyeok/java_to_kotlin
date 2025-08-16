package operator

data class KotlinMoney (
    val amount: Long
) {
    operator fun plus(another: KotlinMoney): KotlinMoney {
        return KotlinMoney(this.amount + another.amount)
    }
}