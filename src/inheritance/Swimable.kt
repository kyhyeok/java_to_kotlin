package inheritance

interface Swimable {
    val swimAbility: Int // 인터페이스는 필드가 없고, getter 구현을 상속받는 클래스에게 위임한다
    // get() = 3 기본 구현 제공 시, 구현 클래스에서 오버라이드하지 않아도 된다

    fun act() {
        println(swimAbility) // Penguin에서 구현했기 때문에 swimAbility 사용가능
        println("수영수영")
    }

    // fun swim() 추상 메서드 가능
}