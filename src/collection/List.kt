package collection

fun main() {
    // 불변 리스트
    // listOf를 통해 '불변 리스트'를 생성
    val numbers = listOf(100, 200) // 타입추론을 해주기 떄문에 타입 생략 가능

    val emptyList = emptyList<Int>() // 비어있기 때문에 type을 명시적으로 적어주어야 한다

    printNumbers(emptyList())
    // emptyList()이지만 printNumbers(매개변수)를 통해 타입 추론이 가능해서 type 생략 가능

    // 가변 리스트 - Java에서의 Collection List 기능들을 사용 가능
    val mutableNumbers = mutableListOf(100, 200)
    mutableNumbers.add(300) // 추가 가능

    // 하나를 가져오기
    numbers.get(0) // 가능은 함
    numbers[0] // 배열처럼도 가능

    for (number in numbers) {
        println(number)
    }

    for ((idx, value) in numbers.withIndex()) {
        println(" $idx $value")
    }
}

private fun printNumbers(numbers: List<Int>) {

}