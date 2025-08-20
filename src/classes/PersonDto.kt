package classes

fun main() {
    val dto1 = PersonDto("혁", 100)
    val dto2 = PersonDto("혁", 200)
    val dto3 = PersonDto("혁", 100)
    println(dto1 == dto2) // false
    println(dto1 == dto3) // true
    println(dto1) // PersonDto(name=혁, age=100)

}

data class PersonDto(
    val name: String,
    val age: Int
)