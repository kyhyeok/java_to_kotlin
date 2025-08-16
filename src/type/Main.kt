package type

fun main() {
    val number1 = 3 // Int 타입
    val number2 = 3.14 // Double 타입
    val number3 = 3.14f // Float 타입
    val number4 = 3L // Long 타입

    val number5 = 4;
    // val number6: Long = number5; Type mismatch 에러 발생
    val number6: Long = number5.toLong(); // Type mismatch 에러 발생

    val number7: Int? = 3 // Int 타입, null을 허용하지 않음
    // val number8: Long = number7.toLong() number7이 null일 수 있으므로 NPE가 발생할 수 있음
    val number8: Long = number7?.toLong() ?: 0L

    val person2 = Person("혁 개발자", 30)
    val log2 = "사람의 이름은 ${person2.name}이고 나이는 ${person2.age}세 입니다"

    val name = "혁 개발자"
    val str ="""
        가나다라마바사
        아자차카타파하
        ${name}
        """.trimIndent()

    println(str) // 여러 줄 문자열

    val strIndexing = "ABCDE"
    strIndexing[0] // A
    strIndexing[2] // C
}


fun printAGeIfPerson(obj: Any) {
    // is는 Java instanceof와 동일한 기능을 함
    if (obj is Person) {
        // as Person은 Java의 강 제 형변환과 동일한 기능을 함
        val person = obj as Person // Smart cast
        println(person.age)
    }

    if (obj is Person) {
        val person = obj
        println(person.age)
    }

    if (obj is Person) {
        println(obj.age)
    }
}

fun printAGeIfPersonReverse(obj: Any) {
    // if (!(obj instanceof Person))
    if (obj !is Person) {
        return
    }
}

fun printAGeIfPersonNull(obj: Any?) {
    // obj가 null이 아니라면 Person 타입으로 안전하게 캐스팅 아닐경우 null을 반환
    val person = obj as? Person // Safe cast
    println(person?.age) // person이 null이 아니라면 age를 출력, null이라면 아무것도 출력하지 않음
}