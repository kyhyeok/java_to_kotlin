package nesting

fun main() {

}

class House(
    private val address: String,
    private val livingRoom: LivingRoom,
) {

    // 코틀린은 기본적으로 바깥 클래스에 대한 연결이 없는 중첩 클래스를 만들어 준다
    class LivingRoom(
        private val area: Double
    )
}


// 권장되지 않는 내부 클래스
class House2(
    private val address: String,
    private val livingRoom: LivingRoom2,
) {

    inner class LivingRoom2(
        private val area: Double
    ) {
        val address: String
        get() = this@House2.address
    }
}