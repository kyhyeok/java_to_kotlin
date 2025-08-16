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

# 4. 연산자 다루는 방법

## 단항 연산자 / 산술 연살자

### 단한 연상자

- ++, --

### 산술 연산자

- +, -, *, /, %

### 산술대입 연산자

- +=, -=, *=, /=, %=

## 비교 연산자와 동등성, 동일성

### 비교 연산자

-  > , <, >=, <=
- Java에서는 객체를 비교할 떄 CompareTo 메소드를 사용하지만, Kotlin에서는 비교 연산자를 사용하면 자동으로 compareTo 메소드가 호출된다
-

### 동등성

- 두 객체의 값이 같은가
- Java에서는 equals() 메소드를 사용하지만, Kotlin에서는 == 연산자를 사용한다. ==을 사용하면 간접적으로 equals() 메소드가 호출된다

### 동일성

- 완전히 동일한 객체인가 (즉 주소가 같은가)
- Java에서는 == 연산자를 사용하지만, Kotlin에서는 === 연산자를 사용한다

## 논리 연산자 / 코틀린에 있는 특이한 연산자

### 논리 연산자

- &&, ||, !
- Java와 동일하게 동작하고 Java와 같이 Lazy 연산을 수행한다

```
if (fun1() || fun2() {
    println("본문입니다")
}

if (fun2() && fun1() {
    println("본문입니다")
}

fun fun1(): Boolean {
    println("fun1() 호출됨")
    return true
}

fun fun2(): Boolean {
    println("fun2() 호출됨")
    return false
}
```

- fun1()이 true를 반환하면 fun2()는 호출되지 않는다. 즉, fun2()는 실행되지 않는다.
- fun1()에서 조건문이 참이므로 fun2()와 관계없이 결과는 참이다

### 코틀린에 있는 특이한 연산자

- in, !in
    - in 연산자는 특정 값이 컬렉션에 포함되어 있는지 확인하는 연산자이다
      `println(1 in numbers)`
- a..b
    - a부터 b까지의 범위를 나타내는 연산자이다
- a[i], a[i] = b
    - a에서 특정 Index i로 값을 가져온다
    - a에서 특정 Index i에 값을 할당한다

```
val str = "ABC"
println(str[0]) // A
```

## 연산자 오버로딩

- Kotlin에서는 연산자 오버로딩을 지원한다. 객체마자 연산자를 직접 정의할 수 있다

<hr>

### 5. 제어문을 다루는 방법

## if문

- if문 Java와 동일하게 동작한다
- if-else
  - Java에서 if-else는 Statement이지만 Kotlin에서는 Expression이다
  - 즉, Kotlin에서는 if-else문이 값을 반환할 수 있다
  - Kotlin은 표현식 지향 언어이기 때문에, 많은 것들이 Expression으로 평가될 수 있다
    `val max = if (a > b) a else b // a와 b` 중 큰 값을 반환하는 if-else문
- if-else if-else도 같다

## Expression과 Statement

### Expression
- Expression(표현식)은 값을 반환하는 코드 블록이다. 하나의 값으로 도출되는 문장이다
- 값을 만들어내는 코드, 하나의 값으로 평가될 수 있다
  ```
      1 + 2 // => 3
      "Hello" // => "Hello"
      x + 10 // => x가 5라면 50
      val y = 3 // 3은 expression, 전체는 statement이다
  ```
### Statement 
- Statement(문장)는 값을 반환하지 않는 코드 블록이다. 프로그램의 무장, 하나의 값으로 도출되지 않는다
  - 어떤 작업을 수행하지만, 값을 반환하지 않으며, 프로그램 흐름을 제어하거나 변수 선언, 할당 등을 포함한다
  ```
      val x = 5 // 변수 선언 + 할당 => 전체는 Statement
      if (x > 3) { // 조건문 => Statement
          println(x) // 출력 -> Statement
      }
  ```
  
## switch와 when
- Kotlin의 when 문은 Java의 switch 문을 대체하며, 더 강력하고 유연한 분기문이다.
- 값 유무와 관계없이 사용할 수 있으며, Expression으로 평가되어 값을 반환할 수도 있다.여러 조건을 동시에 처리할 수 있다.
  - 예: in, !in, is, !is, 콤마(,)를 이용한 다중 조건 등
- break 없이 자동으로 분기가 종료된다.
- when은 Enum Class 혹은 Sealed Class와 함께 사용하면 더욱 유용하다.

<hr>

# 6. 반복문 다루는 방법

## for-each문
- Java나 Kotlin이나 for-each문은 거의 동일하게 동작한다
- Java는 :을 사용하고 Kotlin에서는 in 키워드를 사용한다

## 전통적인 for문
```
for (i in 1..3) {
    println(i) 1, 2, 3
}
for (i in 3 downTo 1) {
    println(i) 3, 2, 1
}
for (i in 1..5 step 2) {
    println(i) 1, 3, 5
}
```
## Progression과 Range
- Progression은 등차수열이다. 시작 값, 끝 값, 공차 (증가 또는 감소 값)으로 구성된다
- 1..3은 Range를 나타내며, 1부터 3까지의 숫자를 포함하는 범위를 의미한다
- 1..3의 의미는 "1에서 시작하고 3으로 끝나는 등차수열을 만들어 줘"라는 의미이다
- 3 downTo 1은 3부터 1까지의 숫자를 포함하는 범위를 의미하며, 1씩 감소하는 등차수열을 만들어 준다
- 1..5 step 2는 1부터 5까지의 숫자 중 2씩 증가하는 등차수열을 만들어 준다
- downTo, Step도 함수이다 (중위 호출 함수)
- Kotlin에서 전통적인 for문은 등차수열을 이용한다

## while문
- Kotlin의 while문은 Java와 동일하게 동작한다
