package clazz

fun main() {
    val person = Person("혁", 5);

    println(person.name)
    person.age = 10;
    println(person.age)

    val person2 = Person("홍길동");
    println(person2.name)

    val javaPerson = JavaPerson("혁", 5);

    println(javaPerson.name)
    javaPerson.age = 10;
    println(javaPerson.age)

    val person3 = Person();
    println(person3.name)
}

class Person (
    val name: String,
    var age: Int
) {
    init {
        println("초기화 블록")
    }

    constructor(name: String): this(name, 1) {
        println("첫 번째 부 생성자")
    }

    constructor(): this("임꺽정") {
        println("두 번째 부 생성자")
    }
}