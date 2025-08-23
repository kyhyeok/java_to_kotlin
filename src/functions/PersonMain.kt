package functions

fun main() {
    val person = Person("혁", "B", 100)
    person.nextYearAge() // 멤버 함수 출력
}

fun Person.nextYearAge(): Int {
    println("확장함수")
    return this.age + 1
}