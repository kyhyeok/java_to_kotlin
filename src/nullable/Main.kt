package nullable

fun main() {
    val str: String? = "ABC"
    // println(str.length) 컴파일 에러 발생
    println(str?.length) // 3

    val str2: String? = null
    println(str2?.length) // null

    val str3: String? = null
    println(str3?.length ?: 0) // 0

    println(startWithNeverNull("ABC"))

    val person = Person("혁 개발자")
    // startsWithJavaNullable(person.name) Person 클래스의 name은 null이 될 수 없으므로 컴파일 에러 발생
}

fun startsWithJavaNullable(str: String): Boolean {
    return str.startsWith("A")
}

// return null을 허용하지 않는 메서드 Boolean
fun startsWithException(str: String?): Boolean {
    if (str == null) {
        throw IllegalArgumentException("null이 들어왔습니다")
    }

    return str.startsWith("A")
}

// return null을 허용하는 메서드 Boolean?
fun startsWithReturnNull(str: String?): Boolean? {
    if (str == null) {
        return null
    }

    return str.startsWith("A")
}

fun startsWithBoolean(str: String?): Boolean {
    if (str == null) {
        return false;
    }

    return str.startsWith("A")
}

// startsWithException 메서드와 동일한 기능을 하는 메서드
fun startWithSafeCallElvisException(str: String?): Boolean {
    return str?.startsWith("A")
        ?: throw IllegalArgumentException("null이 들어왔습니다")
}

// startsWithReturnNull 메서드와 동일한 기능을 하는 메서드
fun startWithSafeCallNull(str: String?): Boolean? {
    return str?.startsWith("A")
}

// startsWithBoolean 메서드와 동일한 기능을 하는 메서드
fun startWithSafeCallElvisBoolean(str: String?): Boolean {
    return str?.startsWith("A") ?: false
}

// 만약 null이 들어오면 RuntimeException이 발생하는 메서드
fun startWithNeverNull(str: String?): Boolean {
    return str!!.startsWith("A")
}