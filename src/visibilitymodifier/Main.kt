package visibilitymodifier

val a = 3

fun add(a: Int, b: Int): Int {
    return a + b
}

class Cat()

class Bus internal constructor(
    val price: Int
)

class Car protected constructor(
    val price: Int
)



class Bar(
    internal val name: String,
    private var owner: String,
    _price: Int
) {
    var price = _price
        private set
}