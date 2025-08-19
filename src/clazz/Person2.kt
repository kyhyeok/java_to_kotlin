package clazz

fun main() {
    val person2 = Person2("hyeok")
    println(person2.name)
}

class Person2(
    name: String = "혁",
    var age: Int = 1,
) {

//    var name = name
//        set(value) {
//            field = value.uppercase()
//        }

    var name = name
        get() = field.uppercase()

    val upperCaseName: String
        get() = this.name.uppercase()

    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
        println("초기화 블록")
    }

    fun isAdult(): Boolean {
        return this.age >= 20
    }

    val isAdultBrace: Boolean
        get() {
            return this.age >= 20
        }

    val isAdult2: Boolean
        get() = this.age >= 20
}