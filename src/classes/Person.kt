package classes

private fun handlePerson(person: Person) {
    when(person) {
        is Kim -> println("Kim")
        is Park -> println("Park")
        is Lee -> println("Lee")
        // else 불필요 - 컴파일러가 모든 타입 알고 있음
    }
}

sealed class Person(
    val name: String,
    val age: Int
)

class Kim: Person("Kim", 10)
class Park: Person("Park", 20)
class Lee: Person("Lee", 30)
// 외부에서 추가 상속 불가능