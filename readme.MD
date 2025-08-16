# 1.변수를 다루는 방법

## 변수 선언 키워드 - var과 val의 차이점
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

<hr>

# 2. null을 다루는 방법

## null checking
- Kotlin에서는 null을 허용하는 타입과 허용하지 않는 타입이 구분되기 때문에, null을 체크하는 방법이 다르다
- null을 허용하는 타입은 ?를 붙여서 선언하고, null을 체크하는 방법은 if문을 사용한다
- null을 허용하지 않는 타입은 ?를 붙이지 않고 선언하고, null을 체크하는 방법은 !!를 사용한다

## Safe call, Elvis operator

### safe call
```
val str: String? = "ABC"
str.length // 컴파일 에러 발생
str?.length // null이 아닐 경우에만 length를 호출
```
- ?가 safe call이며 ?.에서 ?는 null 체크를 의미한다
- Safe call은 ?.을 사용하여 null을 체크하고, null이 아닐 경우에만 메소드를 호출하는 방법이다

### Elvis operator
- 앞의 연산자가 null일 경우 뒤의 값을 반환하는 연산자이다
- Java에서는 삼항 연산자(?:)를 사용하지만, Kotlin에서는 Elvis operator(?:)를 사용한다
- 90도 회전하면 엘비스 프레슬리를 닮았다고 해서 Elvis operator라고 불린다 (서양유머???? 이모티콘을 많이 사용해서 그런가보다)
```
Kotlin
val str: String? = "ABC"
str?.length ?: 0 // str이 null이면 0을 반환, null이 아니면 length를 반환

Java
String str = "ABC";
int length = (str != null) ? str.length() : 0; // str이 null이면 0을 반환, null이 아니면 length를 반환
```
- Elvis operator은 Java에서 early return을 사용하는 것과 유사하다
```
Java 
public long calculate(Long number) {
    if (number == null) return ;
    
    // 다음 로직
}

Kotlin
fun calculate(number: Long?): Long {
    number ?: return 0
    
    // 다음 로직
}
```

## null 아님 단언
- nullable type이지만 아무리 생각해도 null이 될 수 없는 경우 에는 !!를 사용하여 null이 아님을 단언할 수 있다
- 생성일(createdAt)은 코드 레벨이나 DB 레벨에서 입력을 하기 때문에 null이 될 수 없다고 확신할 수 있다면, !!를 사용하여 null이 아님을 단언할 수 있다
- 이럴 경우 Safe call을 매번 사용하는 것보다 코드가 더 간결해진다
- null이 들어오면 NPE(Null Pointer Exception)가 발생할 수 있으므로 주의해야 한다
```
fun startWithNeverNull(str: String?): Boolean {
    return str!!.startsWith("A")
}
```

## 플랫폼 타입
- Kotlin에서는 Java와의 호환성을 위해 플랫폼 타입을 제공한다
- 플랫폼 타입(String!)은 Java에서 넘어온 null 가능성을 Kotlin이 확신할 수 없을 때 생기며, null 안정성을 호출하는 개발자가 직접 보장해야 한다
- @Nullable 또는 @NotNull 애너테이션을 붙이면 Kotlin이 명확하게 String? 또는 String으로 매핑해준다

<hr>

# 3. TYPE을 다루는 방법

## 기본 타입
- Kotlin의 기본 타입은 Java의 기본 타입과 동일하다
- Kotlin의 기본 타입은 Java의 기본 타입과 동일하지만, Kotlin에서는 기본 타입과 참조 타입이 구분되지 않는다
- Java 기본 타입간의 변환은 암시적으로 이루어지지만, Kotlin 기본 타입간의 변환은 명시적으로 이루어져야 한다
- Java
  - integer 타입을 long 타입으로 암시적으로 변환되었다. Java는 작은 타입에서 큰 타입으로의 변환을 자동으로 수행한다
  - 하지만, 큰 타입에서 작은 타입으로의 변환은 명시적으로 해야 한다
- Kotlin
  - integer 타입을 long 타입으로 변환할 때는 toLong()을 사용해야 한다. 그렇지 않으면 type mismatch 에러가 발생한다
- 변수가 nullable 타입인 경우 적절한 처리가 필요하다

## 타입 캐스팅
- Kotlin에서는 타입 캐스팅을 할 때 as 키워드를 사용한다
- Kotlin에서는 smart cast를 지원한다
- smart cast는 컴파일러가 변수의 타입을 자동으로 추론하여 캐스팅하는 기능하다
- 예를 들어, if문에서 변수가 특정 타입으로 체크되면, 이후 코드에서 해당 변수를 해당 타입으로 자동으로 캐스팅해준다
### value is type
  - value가 Type이면 true를 반환하고, 아니면 false를 반환한다
### value !is type
  - value가 Type이면 false를 반환하고, 아니면 true를 반환한다
### value as type
  - value가 type이면 type으로 캐스팅하고, 아니면 ClassCastException을 발생시킨다
### value as? type
  - value가 type이면 type으로 캐스팅하고, value가 null이거나 value가 type이 아니면 null을 반환한다

## Kotlin의 3가지 특이한 타입
### Any
- Kotlin의 모든 타입은 Any 타입을 상속받는다. Java의 Object와 유사하다 (모든 객체의 최상위 타입)
- 모든 Primitive Type의 최상위 타입이기도 하다. 단, Java의 Primitive 타입은 Object를 최상위 타입으로 두지 않는다 
- Any는 null을 포함할 수 없기 때문에 null을 포함하고 싶으면 Any?로 선언해야 한다. null을 포함할 수 있는 타입은 Any?이다
- Any 타입은 equals(), hashCode(), toString() 메소드를 가지고 있다

### Unit
- Java의 void와 유사하지만 void와 다르게 Unit은 타입 인자로 사용할 수 있다
- Java에서는 void말고 Void class가 따로 있지만 Kotlin은 Unit이 직접 사용 가능하다
  - `<void>` 불가능, `<Void>`가능
- 함수형 프로그래밍에서 Unit은 단 하나의 인스턴스만 가지는 타입을 의미한다. 즉, Kotlin의 Unit은 실제로 존재하는 타입임을 나타낸다

### Nothing
- Kotlin의 Nothing 타입은 어떤 값도 가질 수 없는 타입이다
- Nothing 타입은 함수가 절대 반환하지 않는다는 것을 나타낸다
  - 함수가 정상적으로 끝나지 않았다는 사실을 표현하는 역할이다.
- 무조건 예외를 반환하는 암수 / 무한 루프 함수 등에서 사용된다
```
fun fail(message: String): Nothing {
    throw IllegalArgumentException(message)
}
```

## String Interpolation, String Indexing
- Kotlin에서는 String Interpolation을 지원한다
- String Interpolation은 ${}를 사용하여 변수나 표현식을 문자열에 삽입하는 방법이다
- String Interpolation은 가독성이 좋고, 코드가 간결해진다. 문자열을 생성할 때 StringBuilder를 사용하지 않고, 문자열을 생성할 수 있다. 또한 문자열을 생성할 때 성능이 더 좋다
```
Java
Person person = new Person("혁", 5);
String log = String.format("사람의 이름은 %s이고 나이는 %s세 입니다", person.getName(), person.getAge());

StringBuilder builder = new StringBuilder();
builder.append("사람의 이름은 ");
builder.append(person.getName());
builder.append("이고 나이는 ");
builder.append(person.getAge());
builder.append("세 입니다");

Kotlin
val person2 = Person("혁 개발자", 30)
val log2 = "사람의 이름은 ${person2.name}이고 나이는 ${person2.age}세 입니다"

val name = "혁 개발자"
val log3 = "사람의 이름은 $name이다."
```
- JavaScript나 PHP, Python 등에서 사용하는 String Interpolation과 유사하다
- 변수에서 바로 사용할 때는 `${}`를 사용할 필요가 없고, 변수명만 사용하면 되지만 `${}`을 사용해서 통일해 주는 것이 가독성, 일괄 변환, 정규식 활용 측면에서 좋을 수 있다
```
val name = "혁 개발자"
val str ="""
    가나다라마바사
    아자차카타파하
    ${name}
""".trimIndent()
println(str)
```
- String Indexing은 문자열의 특정 위치에 있는 문자를 가져오는 방법이다
- Kotlin에서는 문자열의 특정 위치에 있는 문자를 가져올 때, []를 사용한다
```
Java
String str = "ABCDE";
char ch = str.charAt(1); // B

Kotlin
val strIndexing = "ABCDE"
strIndexing[0] // A
strIndexing[2] // C
```

<hr>