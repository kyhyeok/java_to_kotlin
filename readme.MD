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

<hr>

# 7. 예의를 다루는 방법
- Java와 문법 자체는 동일하지만 Java와 Kotlin의 차이로 인한 포맷팅이 간결하다
- null을 허용하는 타입과 허용하지 않는 타입이 구분되기 때문에, null을 체크하는 방법이 다르다
  - Kotlin에서는 null을 허용하는 타입은 ?를 붙여서 선언하고, null을 체크하는 방법은 if문과 동일하게 Expression으로 간주되어서 return을 한 번만 사용해도 된다
```
fun parseIntOrThrowNull(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}
```

## try catch finally 구문
- Java와 동일하게 동작한다

## Checked Exception과 Unchecked Exception
- Kotlin에서는 Checked Exception이 없다. 모든 예외는 Unchecked Exception이다
- Java에서는 Checked Exception과 Unchecked Exception이 구분되지만, Kotlin에서는 모든 예외가 Unchecked Exception이다
- Java는 복구 가능한 실패를 강제 처리시키려는 의도로 Checked Exception을 도입했고, 버그/계약 위반처럼 복구 불가능한 경우엔 Unchecked Exception을 사용
- Checked Exception은 보일러플레이트 증가, throws 연쇄로 인한 추상화 누수와 API 진화 저해, 함수형/비동기와의 궁합 불량, 의미 없는 빈 catch 유도 등의 단점이 있다
- Kotlin은 Checked Exception을 제거하여 이러한 단점을 해결하고, 예외 처리의 복잡성을 줄였다.
  - 모든 예외를 Unchecked Exception처럼 취급하며, 복구 가능한 실패는 Result/sealed class 등 타입으로 모델링하는 방식을 선호한다
- Kotlin도 필요 시 try-catch가 가능하고, Java 쪽에 throws가 보이게 하려면 @throws로 노추할 수 있다

### Java는 복구 강제(Checked) + 버그 (Unchecked) 이원화, Kotlin은 예외 강제 없음 + 복구는 타입으로 모델링

## try with resources
- Kotlin에서는 try with resources 구문이 없다
- 대신, Kotlin에서는 use() 함수를 사용하여 자원을 자동으로 해제할 수 있다
- use() 함수는 AutoCloseable 인터페이스를 구현한 객체에서 사용되며, 자원을 자동으로 해제해준다
- use() 함수는 확장 함수로 제공되며, 자원을 사용한 후 자동으로 close() 메소드를 호출한다

<hr>

# 8. 함수 다루는 방법

## 함수 선언 문법
```
fun max(a: Int, b:Int): Int {
    if (a > b) {
       return  a
    }
    return b
}

fun max(a: Int, b:Int): Int {
    return if (a > b) {
       a
    } else {
        b
    }
}

fun max(a: Int, b:Int): Int =
    if (a > b) {
       a
    } else {
        b
    }

fun max(a: Int, b:Int) = if (a > b) a else b

Java 람다에서 단일 표현식    
static int max(int a, int b) = (a, b) -> (a > b) ? a : b;
```
- 함수가 하나의 결과값이라면 {}대신 =이 오고 return을 제거해도 된다
- 분기처리 시 모든 경우가 Int를 반환하기 때문에 return type이 생략 가능하다(추론해준다)
- =이 아닌 {}을 사용하는 경우에는 반환 타입이 Unit이 아닌 경우 명시적으로 타입을 작성해주어야 한다
- Java 람다의 단일 표헌식과 느낌상 비슷하다
- Java에서는 Class안에 Method로 작성되지만 Kotlin은 클래스 안에 있을 수도 있고, 파일 최상단에 있을 수도 있다. 또한, 한 파일 안에 여러 함수들이 있을 수도 있다
- JavaScript, PHP, Python 등의 문법과 동일하다

## default parameter
- Java에서는 OverLoading을 활용해서 문제를 해결하지만 같은 메서드를 N개를 만드는 것이 중복되는 느낌이 나고 귀찮을 수 있다.
- Kotlin에서는 default parameter를 넣어주면 함수를 사용할 때 값은 넣어주지 않는다면 해당 값을 기본값으로 사용할 수 있다
- Kotlin에서도 물론 OverLoading 기능이 있다
- JavaScript, PHP, Python 등의 문법과 동일하다

```
repeat("Hello Kotlin")
repeat("Hello Kotlin", 3, true)
repeat("Hello Kotlin", 5, false)

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
```

### Java에는 왜 default parameter가 없을까? (AI가 설명해준 내용)
- 언어 설계 철학: Java는 단순성과 명시성을 중시한다. 기본 매개변수는 오버로딩, 제네릭, varargs와 함께 사용될 때 메서드 해석 규칙을 복잡하게 만들어 언어 명세(JLS)의 복잡도를 크게 증가시킨다.
- 바이너리 호환성: 기본값을 컴파일 시점에 인라이닝하면 라이브러리 업데이트 시 재컴파일이 필요하고, 런타임에 처리하려면 JVM 호출 규약의 대대적인 변경이 필요해진다
- 가독성 문제: Named argument가 없는 Java에서는 중간 매개변수를 건너뛰기 어렵고, 기본값이 호출부에서 보이지 않아 코드 이해가 어려워진다
- 대안 패턴: Java는 대신 메서드 오버로딩, 빌더 패턴, 매개변수 객체 등의 명시적인 패턴을 권장한다. 이는 더 장황하지만 의도가 명확하고 타입 안전성을 보장한다
- Java는 편의성보다는 예측 가능성과 명시성을 선택한 것이다 


## named argument (parameter)
- 매개변수의 이름을 통해 직접 지정하는 방식으로 지저오디지 않은 매개변수는 기본값을 사용한다
- builder를 직접 만들지 않고 builder의 장점을 가질 수 있다
- Kotlin에서 Java 함수를 가져다 사용할 때는 named argument를 사용할 수 없다
- JavaScript, PHP, Python 등의 문법과 동일하다
```
repeat("Hello World", useNewLine = false)

printFullName("톰", "크루즈") // 톰 크루즈 
printFullName("크루즈", "톰") // 크루즈 톰
printFullName(lastName = "크루즈", firstName = "톰") // 톰 크루즈

fun printFullName(firstName: String, lastName: String) {
    println("${firstName} ${lastName}")
}

PrintFullName().buiilder()
.firstName()
.lastName()
.build()
```

### Java에는 왜 named argument가 없을까? (AI가 설명해준 내용)
- API 계약 문제: Java에서 매개변수 이름은 현재 API의 일부가 아니다. 바이트코드에 기본적으로 포함되지 않고, 라이브러리 개발자는 이름을 자유롭게 변경할 수 있다. Named arguments를 도입하면 매개변수 이름이 공식 API 계약이 되어 이름 변경 시 호환성이 깨진다
- 메서드 해석 복잡성: Java는 이미 오버로딩, 제네릭, varargs 등으로 복잡한 메서드 해석 규칙을 가지고 있다. Named arguments를 추가하면 모호성 해결 규칙이 기하급수적으로 복잡해진다
```
void draw(int width, int height) {}
void draw(int height, int width) {}
// draw(height = 10, width = 20); // 어느 메서드를 선택할지?
```
- 기본 매개변수와의 시너지 부족: Named arguments의 진가는 기본 매개변수와 함께 사용될 때 발휘된다. Java는 기본 매개변수도 지원하지 않아 named arguments만으로는 효용이 제한적이다
- 생태계 영향: 리플렉션, 바이트코드 조작, 난독화 도구들이 "이름은 계약이 아니다"라는 전제로 설계되어 있어, 변경 시 광범위한 생태계 업데이트가 필요하다
- Java는 편의성보다 호환성과 단순성을 선택하여 기존 방식을 유지하고 있다

## 같은 타입의 여러 파라미터 받기(가변인자)
- Java에서는 가변인자를 ...을 사용하여 처리하지만, Kotlin에서는 vararg 키워드를 사용하여 처리한다
- 가변인자 함수를 사용하기 위해서는 배열 앞에 스프레드 연산자 (*)를 붙여주어야 한다. Comma 구분을 Java와 동일하다

```
printAll("Hello", "World", "Java")

val array = arrayOf("Hello", "World", "Kotlin")
printAll(*array) // spread operator
    
fun printAll(vararg strings: String) {
    for(str in strings) {
        println(str)
}
```

### 가변인자(Varargs)
- 기본 개념: 가변인자는 메서드의 매개변수를 동적으로 처리할 수 있도록 해준다. 이를 사용하면 메서드 호출 시 전달되는 인자의 개수를 동적으로 변경할 수 있다
- `public void exampleMethod(String... strings) { // strings는 배열로 처리됨 }`
- 인수 개수가 가변적이고 유연성이 필요할 때 사용하되, 성능이 중요한 상황에서는 신중히 고려해야 한다
