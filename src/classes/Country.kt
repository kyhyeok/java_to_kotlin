package classes

fun handleCountry(country: Country) {
    when (country) {
        Country.KOREA -> println("Korea 로직")
        Country.AMERICA -> println("AMERICA 로직")
    }
}

enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US"),
}