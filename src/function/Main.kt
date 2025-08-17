package function

fun main() {
    repeat("Hello World")
    repeat("Hello World", useNewLine = false)

    printFullName("톰", "크루즈")
    printFullName("크루즈", "톰")

    printFullName(lastName = "크루즈", firstName = "톰")

    printAll("Hello", "World", "Java")

    val array = arrayOf("Hello", "World", "Kotlin")
    printAll(*array) // spread operator
}

fun max(a: Int, b:Int): Int {
    if (a > b) {
       return  a
    }
    return b
}

fun repeat(
    str: String,
    num: Int = 3,
    useNewLine: Boolean = true
) {
    for (i in 1..num) {
        if (useNewLine) {
            println(str)
        } else {
            print(str)
        }
    }
}

fun printFullName(firstName: String, lastName: String) {
    println("${firstName} ${lastName}")
}

fun printAll(vararg strings: String) {
    for(str in strings) {
        println(str)
    }
}