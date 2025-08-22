package nullable

fun main() {
    // safe call
    val str: String? = "ABC"
    // println(str.length) 컴파일 에러 발생
    println(str?.length) // 3
    println()

    val str2: String? = null
    println(str2?.length) // null
    println()

    // Elvis operator
    val str3: String? = null
    println(str3?.length ?: 0) // 0
    println()

    // 다양한 null 처리 방법 비교
    val testStr: String? = "Apple"
    val nullStr: String? = null

    // Exception 방식
    try {
        startsWithException(nullStr)
    } catch (e: IllegalArgumentException) {
        println("startsWithJavaNullable(null) 에러: ${e.message}")
        // 에러: null이 들어왔습니다
    }
    println()

    // null 반환 방식
    println("startsWithJavaNullable(testStr) = ${startsWithReturnNull(testStr)}") // true
    println("startsWithJavaNullable(nullStr) = ${startsWithReturnNull(nullStr)}") // null
    println()

    // false 반환 방식
    println("startsWithBoolean(testStr) = ${startsWithBoolean(testStr)}") // true
    println("startsWithBoolean(nullStr) = ${startsWithBoolean(nullStr)}") // false
    println()

    // Safe call + Elvis 활용
    println("startsWithSafeCallElvisException(testStr) = ${startsWithSafeCallElvisException(testStr)}") // true
    try {
        startsWithSafeCallElvisException(nullStr)
    } catch (e: IllegalArgumentException) {
        println("startsWithSafeCallElvisException(nullStr) 에러: ${e.message}")
        // 에러: null이 들어왔습니다
    }
    println()

    // Safe Call + null 반환
    println("startWithSafeCallNull(testStr) = ${startWithSafeCallNull(testStr)}") // true
    println("startWithSafeCallNull(nullStr) = ${startWithSafeCallNull(nullStr)}") // null
    println()

    // Safe Call + Elvis false
    println("startWithSafeCallElvisBoolean(testStr) = ${startWithSafeCallElvisBoolean(testStr)}") // true
    println("startWithSafeCallElvisBoolean(nullStr) = ${startWithSafeCallElvisBoolean(nullStr)}") // false
    println()

    // Person 객체
    val person = Person("혁")
    println("person.name = ${person.name}") // Java @Nullable이므로 String? 타입
//    println("startsWithJavaNullable(person.name) = ${startsWithJavaNullable(person.name)}")
    // Java의 @Nullable 때문에 person.name이 String? 타입이므로 컴파일 에러 발생

    // null 체크 후 호출
    person.name?.let { name ->
        println("startsWithJavaNullable(name)?.let = ${startsWithJavaNullable(name)}")
    }
    println()

    // Not-null assertion (!!) 예시
    println("startWithNeverNull(testStr) = ${startWithNeverNull(testStr)}") // true
    try {
        startWithNeverNull(nullStr)
    } catch (e: IllegalArgumentException) {
        println("startWithNeverNull(nullStr) 에러: ${e.message}")
        // 에러: NullPointerException 발생
    }
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

// return Boolean을 반환하는 메서드
fun startsWithBoolean(str: String?): Boolean {
    if (str == null) {
        return false;
    }

    return str.startsWith("A")
}

// startsWithException 메서드와 동일한 기능을 하는 메서드
fun startsWithSafeCallElvisException(str: String?): Boolean {
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

// Java에서의 @Nullable 메서드
fun startsWithJavaNullable(str: String): Boolean {
    return str.startsWith("A")
}

// 만약 null이 들어오면 RuntimeException이 발생하는 메서드
fun startWithNeverNull(str: String?): Boolean {
    return str!!.startsWith("A")
}