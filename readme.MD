# 1. 변수 선언 키워드 - var과 val의 차이점
- var은 가변으로 변경이 가능하다
- val을 불변으로 변경이 불가능하다(read-only)

## 간단한 TIP
- 모든 변수는 우선 val로 선언하고, 변경이 필요한 경우에만 var로 선언(변경)하면 코드들이 더 깔끔해지고 디버깅도 쉬워 진다

## primitive type과 reference type
- Java에서는 primitive type과 reference type이 구분되지만, Kotlin에서는 primitive type과 reference type이 구분되지 않는다
- Java에서는 연산을 할 경우 reference type을 primitive type으로 변환(boxing, unboxing)해야 하지만, Kotlin에서는 내부적으로 자동으로 변환된다
- Kotlin에서는 연산을 할 경우 내부적으로 primitive type으로 변환되어 연산이 수행된다
- Kotlin에서는 boxing, unboxing이 필요하지 않다 (Kotlin이 알아서 처리해준다)

## nullable type
- Kotlin에서는 null을 허용하는 타입과 허용하지 않는 타입이 구분된다
- null을 허용하는 타입은 ?를 붙여서 선언한다
- Java에서는 null을 허용하는 타입과 허용하지 않는 타입이 구분되지 않지만, Kotlin에서는 null을 허용하는 타입과 허용하지 않는 타입이 구분된다

## 객체 인스턴스화
- Java에서는 new 키워드를 사용하지만, Kotlin에서는 new 키워드를 사용하지 않고 객체를 인스턴스화한다