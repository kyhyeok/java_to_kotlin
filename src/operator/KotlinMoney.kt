package operator

data class KotlinMoney(
    val amount: Long
) : Comparable<KotlinMoney> {

    operator fun plus(other: KotlinMoney): KotlinMoney {
        return KotlinMoney(this.amount + other.amount)
    }

    override fun compareTo(other: KotlinMoney): Int {
        return this.amount.compareTo(other.amount)
    }
}