package functions

fun main() {
    val train: Train = Train()
    train.isExpensive() // Train 확장함수

    val srt1: Train = Srt() // Train이 더 상위의 타입이라 가능
    srt1.isExpensive() // Train 확장함수 여기 결과가 특이하다

    val srt2: Srt = Srt()
    srt2.isExpensive() // Srt 확장함수
}

open class Train (
    val name: String = "새마을기차",
    val price: Int = 5_000,
)

fun Train.isExpensive(): Boolean {
    println("Train 확장함수")
    return  this.price >= 10_000
}

class Srt: Train("SRT", 40_000)

fun Srt.isExpensive(): Boolean {
    println("Srt의 확장함수")
    return this.price >= 10_000
}


