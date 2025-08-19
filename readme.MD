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
- Safe call은 ?.을 사용하여 null을 체크하고, null이 아닐 경우에만 메서드를 호출하는 방법이다

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
- Any 타입은 equals(), hashCode(), toString() 메서드를 가지고 있다

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
- Java에서는 객체를 비교할 떄 CompareTo 메서드를 사용하지만, Kotlin에서는 비교 연산자를 사용하면 자동으로 compareTo 메서드가 호출된다
-

### 동등성

- 두 객체의 값이 같은가
- Java에서는 equals() 메서드를 사용하지만, Kotlin에서는 == 연산자를 사용한다. ==을 사용하면 간접적으로 equals() 메서드가 호출된다

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
- use() 함수는 확장 함수로 제공되며, 자원을 사용한 후 자동으로 close() 메서드를 호출한다

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

<hr>

# 9. 클래스를 다루는 방법

## 클래스와 프로퍼티, 생성자와 init
```
Java
public class JavaPerson {
    private final String name; // 블변이기 때문에 setter가 불가능하다

    private int age;
    
    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

```
Kotlin
class Person constructor(name: String, age: Int){
    if (age <= 0) {
        throw new IllegalArgumentException(String.format("나이는 %s일 수 없습니다.", age));
    }
    
    val name: String = name
    var age: Int = age
}

class Person(name: String, age: Int){
    val name: String = name
    var age: Int = age
}

class Person(
    val name: String,
    var age: Int
) {
}

class Person(
    val name: String,
    var age: Int
)

package clazz

fun main() {
    val person = Person("혁", 5); // 초기화 블록

    println(person.name) // 혁
    person.age = 10;
    println(person.age) 

    // 초기화 블록
    // 첫 번째 부 생성자
    val person2 = Person("홍길동");
    println(person2.name) // 홍길동
    
    // 초기화 블록
    // 첫 번째 부 생성자
    // 두 번째 부 생성자
    val person3 = Person();
    println(person3.name) // 임꺽정
}

class Person (
    val name: String,
    var age: Int
) {
    init {
        println("초기화 블록")
    }

    constructor(name: String): this(name, 1) {
        println("첫 번째 부 생성자")
    }

    constructor(): this("임꺽정") {
        println("두 번째 부 생성자")
    }

    // constructor(): this("임꺽정")가 constructor(name: String): this(name, 1)를 호출
    // constructor(name: String): this(name, 1)가 주 생성자를 호출
}
```
- Kotlin은 필드만 만들면 getter, setter를 자동으로 만들어준다
  - 프로퍼티 = 필드 + getter+ setter
  - Java record와 비슷한 느낌이다.
  - 정확히는 Kotlin의 data class가 Java의 record와 매우 흡사하다 (불변)
- init {}은 생성자가 호출되는 시점에 호출된다
- 초기 생성자(주 생성자 - primary constructor)를 Class 옆에서 자동으로(생략가능하기 떄문에) 생성되지만 새로운 생성자(부 생성자)는 {}안에 constructor()를 사용해 주어야 한다
- 주 생성자에 파라미터가 하나도 없을 경우에는 생략 가능하다(자동으로 생성 해주기 때문)
- 부 생성자는 body를 가질 수 있다. 부 생성자 호출 시 가장 최상단인 주 생성자부터 호출된다
  - body안에 코드를 추가해 줄 수 있다

### 결과론적으로 부 생성자보다 default parameter 사용을 권장한다 (아래는 AI가 설명해준 내용)
- 기본 인자가 "주 생성자 + 이름 있는 인자"와 맞물려, 보일러플레이트 없이 명확하고 안전하게 객체를 만들 수 있다
- 조합 폭발 방지: 선택 인자 조합마다 부 생성자를 늘리지 않아도 된다("텔레스코핑 생성자" 제거)
  - Telescoping Constructor는 필수 매개변수를 가지는 생성자를 필두로 선택 매개변수를 가지는 생성자를 추가하는 것
  - 클래스에 OverLoading이 많은 것으로 판단된다
- 가독성과 의도 표현: 이름 있는 인자와 함께 쓰면 호출부가 읽기 쉽다
- 단일 초기화 경로: 주 생성자를 하나로 불변식을 한 곳에서 보장
- 진화 용이성: 파라미터를 추가하되 기본값을 주면 기존 호출부 소스 호환을 지키기 쉬워진다
- 결론: 기본 인자 + 이름 있는 인자 조합이 가장 Kotlin스러운 방식이다

### Converting과 같은 경우 부 생성자를 사용할 수 있지만 정적 팩토리 메서드를 권장한다 (아래는 AI가 설명해준 내용)
- 의미 전달: from(), of(), parse(), fromJson() 등 이름으로 변환 의도를 명확히 표현 가능하다. 생성자는 의미 전달이 제한적이다
- 초기화 집중화: 주 생성자를 private으로 감추고 검증/정규화 로직을 한 곳에 집중. 부 생성자는 로직 분산과 중복 위험이 있다
- 생성 전략 유연성
  - 캐시/풀링/싱글턴 재사용
  - 서브타입 변환
  - 입력에 따른 구현 선택
- 실패 처리: null, Result<T>등으로 실패를 값으로 표현이 가능하다. 생성자는 예외만 가능하다
- API 명료성: 오버로드된 생성자는 호출 시 모호하지만, 팩토리는 이름으로 구분되어 명확하다
- Kotlin 친화적: 컴패니언/톱레벨/확장 함수로 배치하여 발견성과 사용성이 향상된다
- 결론: 변환은 의미가 중요하므로 이름으로 의도를 드러내는 정적 팩토리가 더 적합하다

### 결론은 Kotlin에서 부 생성자를 사용할 일은 거의 없지만 서로 다른 super()호출이 꼭 필요할 때는 사용된다고 한다

## 커스텀 getter, setter, backing field
- 함수를 만들 수 있고, 함수 대신 프로퍼티로 만들 수 있다
```
fun main() {
    val person = Person("hyeok")
    println(person.name) // HYEOK
}

class Person(
    name: String = "혁",
    var age: Int = 1,
) {

    val name = name
        get() = field.uppercase()
        
    init {
        if (age <= 0) {
            throw IllegalArgumentException("나이는 ${age}일 수 없습니다")
        }
        println("초기화 블록")
    }

    fun isAdult(): Boolean {
        return this.age >= 20
    }

    val isAdultBrace: Boolean
        get() {
            return this.age >= 20
        }

    val isAdultCumtom: Boolean
        get() = this.age >= 20
}
```
### 커스텀 getter
```
class Person(
    name: String = "혁",
    var age: Int = 1,
) {
    val isAdultCumtom: Boolean
        get() { return this.age >= 20 }
        
    val isAdultCumtom: Boolean 
        get() = this.age >= 20
}
```

### backing field
```
class Person(
    name: String = "혁",
    var age: Int = 1,
) {
    var name = name 
    get() = name.uppercase() // ❌ 무한 루프!
    
    var name = name
        get() = field.uppercase() // field 사용
            
    var name = name
            set(value) {
                field = value.uppercase()
            } // set 
            
            
    val upperCaseName: String
            get() = this.name.uppercase() // custom getter
}
```
- Kotlin에서 프로퍼티에 custom getter를 정의할 때 무한 루프 문제가 발생할 수 있다
- person.name을 호출하면 name 프로퍼티의 getter가 실행되는데, getter 내부에서 다시 name을 참조하면 또다시 getter가 호출된다. 이렇게 getter가 자기 자신을 계속 호출하는 무한 루프가 발생하게 된다
- 이 문제를 해결하기 위해 Kotlin은 field라는 예약어를 제공한다. field는 프로퍼티의 실제 저장된 값을 직접 참조하므로 getter를 다시 호출하지 않는다
- 이렇게 getter/setter에서 사용되는 숨겨진 필드를 backing field라고 부른다

### Kotlin: Custom Getter vs 함수 선택 기준
- 객체의 속성이라면 Custom Getter를 사용, 객체의 행동이라면 함수를 사용한다
- 판단 기준
  - 속성: 객체의 상태나 특성을 나타낸다(이름, 나이, 크기 등)
  - 행동: 객체가 수행하는 작업이나 동작을 나타낸다(계산, 변환, 출력 등)
- 사용성
  - Custom Getter: object.property - 사연스러운 속성 접근
  - 함수: object.method() - 명시적인 동작 실행
- 핵심: 외부에서 속성처럼 보이면 custom getter, 동작처럼 보이면 함수를 사용한다

### Kotlin에서 backing field보다 custom getter를 더 많이 사용하는 이유
- Backing field 방식: 기존 프로퍼티의 저장된 값을 변형, Custom getter 방식: 새로운 프로퍼티처럼 보이는 계산된 값 제공
- 동일한 결과가 나오지만 더 간단하게 구현할 수 있다
- API 설계의 자연스러움: Custom getter는 마치 실제 프로퍼티가 하나 더 있는 것처럼 보여주므로 외부에서 사용하기 자연스럽다
- 사용 빈도의 차이: 대부분의 경우 기존 값의 변형보다는 새로운 계산된 값을 제공하는 것이 일반적이어서, backing field를 사용할 일이 상대적으로 적다
- 명확한 책임 분리: 원본 데이터는 name, 파생된 데이터는 upperCaseName
- 결론: 같은 결과를 더 직관적이고 간단하게 달성할 수 있어서 Kotlin에서 custom getter를 선호한다

### custom setter
- Java에서도 그렇지만 Kotlin에서 역시 setter를 사용하는 것을 지양한다
- 함수를 만들어서 해당 인스턴스를 변경하는 것을 지향한다

<hr>

# 10. 상속을 다루는 방법
## 추상 클래스
- 상위 클래스에 접근하는 키워드는 super로 Java와 동일하다
- Java와 Kotlin 모두 추상 클래스는 인스턴스화 할 수 없다
  - 하나 이상의 추상 메서드(미구현)를 가질 수 있어 완전한 객체를 만들 정보가 부족하기 떄문이다. 모든 추상 멤버를 구현한 구체 서브클래스가 있어야만 인스턴스화가 가능하다
- Kotlin에서는 Java와 다르게 프로퍼티를 override할 경우 Open을 붙여 줘야한다
- legCount에 대한 상속받은 다른 클래스에서 getter를 override할 때 (custom getter를 활용한 프로퍼티를 만들 때)

### Kotlin에서 open을 붙여아 되는 이유를 무엇일까? (아래는 AI가 설명해준 내용)
- Final-by-Default
- Kotlin은 모든 클래스/함수/프로퍼티가 기본적으로 final이다. 오버라이드하려면 명시적으로 open을 선언해야 한다
- Kotlin 프로퍼티는 getter/setter 메서드로 컴파일되므로, 프로퍼티 오버라이드 = 메서드 오버라이드이다
- Java 메서드는 기본적으로 오버라이드가 가능(non-final), Kotlin 메서드/프로퍼티는 기본적으로 final. 따라서 open 명시 필요
- 장점
  - API 안정성: Fragile base class 문제 방지 (나중에 알아본다)
  - 우발적 오버라이딩 방지: 의도하지 않은 상속 차단
  - 성능최적화: 컴파일러가 디버추얼라이제이션 등 최적화 적용 가능 (나중에 알아본다)
  - 캡슐화 강화: 명시적 설계로 더 안전한 상속
- 핵심: Kotlin은 안전한 상속을 위해 명시적 open 선언을 요구한다
```
abstract class Animal(
    protected val species: String,
    protected open val legCount: Int
) {
    abstract fun move()
}

class Cat(
    species: String, -> 1
) : Animal(species, 4) -> 3 { -> 2
    override -> 4 fun move() { 
        println("사람이 걷고 있습니다")
    }
}

class Penguin (
    species: String,
) : Animal(species, 2) {

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 걷고 있습니다")
    }

    override val legCount: Int
            get() = super.legCount + this.wingCount
}
```
1. convention 차이점은 타입을 슬 때는 변수명에서 한 칸 뛰지 않고 :을 붙인다
2. 상속받을 때는 한 칸 뛰고 :을 붙인다
3. 상위 클래스의 생성자를 호출해준다. Cat을 만들 떄 사용한 species field를 바로 넣어준다
4. @Override 어노테이션이 아닌 override 지시어를 사용한다 (필수)

## 인터페이스
- Java와 Kotlin 모두 인터페이스를 인스턴스화 할 수 없다
  - 구현(메서드 본문)과 인스턴스 상태를 갖지 않는 계약이기 때문에 그 자체로는 불완전하다. 따라서 직접 인스턴스화 할 수 없고, 구현 클래스를 통해서만 객체를 만들 수 있다
```
interface Swimable {
    val swimAbility: Int // 인터페이스는 필드가 없고, getter 구현을 상속받는 클래스에게 위임한다
        // get() = 3 기본 구현 제공 시, 구현 클래스에서 오버라이드하지 않아도 된다

    fun act() {
        println(swimAbility) // Penguin에서 구현했기 때문에 swimAbility 사용가능
        println("수영수영")
    }

    // fun swim() 추상 메서드 가능
}

interface Flyable {
    fun act() {
        println("날아날아")
    }
}

class Penguin (
    species: String,
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("펭귄이 걷고 있습니다")
    }

    override val legCount: Int
            get() = super.legCount + this.wingCount

    override fun act() {
        super<Swimable>.act()
        super<Flyable>.act()
    }
}
```
- Java에서 상속은 extends (확장)과 implements(구현)으로 나누지만 Kotlin에서는 : 하나로 통일한다 
- 중복되는 인터페이스를 특정할 떄 `super<타입>.함수` 사용
- Kotlin에서는 backing field가 없는 프로퍼티를 interface에 만들 수 있다

## 클래스를 상속할 떄 주의할 점
```
fun main() {
    Derived(300) // Base Class → 0 → Derived Class
}

open class Base(
    open val number: Int = 100
) {
    init {
        println("Base Class")
        println(number)  // 0 출력! (예상: 300)
    }
}


class Derived(
    override val number: Int,
) : Base(number) {
    init {
        println("Derived Class")
    }
}

/*
open class Base(val number: Int = 100) {  // final로 설계
    init {
        println(number)  // 안전하게 예상 값 출력
    }
}
*/
```
1. 초기화 순서 - Kotlin에서 하위 클래스의 인스턴스를 생성하면, 상위 클래스의 생성자와 init 블록이 먼저 실행된 후, 하위 클래스의 생성자와 init 블록이 실행된다.
   - 실행 순서: 상위 클래스 init ⮕ 하위 클래스 init
2. open 프로퍼티 접근 문제: 상위 클래스의 init 블록이나 생성자에서 open으로 선언된 프로퍼티에 접근할 때 문제가 발생할 수 있다
   - 문제의 원인: 상위 클래스가 초기화되는 시점에는 하위 클래스에서 override할 프로퍼티가 아직 초기화되지 않은 상태이다
   - 실제 동작: 상위 클래스 Base의 init 블록이 number를 출력할 때, 아직 하위 클래스 Derived의 number 값(300)이 할당되지 않았다. 따라서 Int 타입의 기본값인 0이 출력된 것이다.
3. 핵심 주의사항 및 해결책: 상위 클래스를 설계할 때, 생성자나 init 블록 안에서는 하위 클래스에서 override될 가능성이 있는 open 프로퍼티에 접근해서는 안 된다. 이는 예층 불가능한 동작을 유발할 수 있기 때문이다
    - 권장 사항: 상위 클래스의 초기화 로직에 사용되는 프로퍼티는 open 키워드로 사용하지 않는 것이 안전하다

## 상속 관련 지시어 정리
- final: override를 할 수 없게 한다. default로 보이지 않게 존재한다
- open: override를 열어 준다(상속할 수 있게 해준다)
- abstract: 반드기 override 해야한다
- override: 상위 타입을 오버라이드 하고 있다