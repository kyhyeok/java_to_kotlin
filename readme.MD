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
functions calculate(number: Long?): Long {
    number ?: return 0
    
    // 다음 로직
}
```

## null 아님 단언

- nullable type이지만 아무리 생각해도 null이 될 수 없는 경우에는 !!를 사용하여 null이 아님을 단언할 수 있다
- 생성일(createdAt)은 코드 레벨이나 DB 레벨에서 입력을 하기 때문에 null이 될 수 없다고 확신할 수 있다면, !!를 사용하여 null이 아님을 단언할 수 있다
- 이럴 경우 Safe call을 매번 사용하는 것보다 코드가 더 간결해진다
- null이 들어오면 NPE(Null Pointer Exception)가 발생할 수 있으므로 주의해야 한다

```
functions startWithNeverNull(str: String?): Boolean {
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
functions fail(message: String): Nothing {
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

functions fun1(): Boolean {
    println("fun1() 호출됨")
    return true
}

functions fun2(): Boolean {
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
functions parseIntOrThrowNull(str: String): Int? {
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
- use() 함수는 확장함수로 제공되며, 자원을 사용한 후 자동으로 close() 메서드를 호출한다

<hr>

# 8. 함수 다루는 방법

## 함수 선언 문법

```
functions max(a: Int, b:Int): Int {
    if (a > b) {
       return  a
    }
    return b
}

functions max(a: Int, b:Int): Int {
    return if (a > b) {
       a
    } else {
        b
    }
}

functions max(a: Int, b:Int): Int =
    if (a > b) {
       a
    } else {
        b
    }

functions max(a: Int, b:Int) = if (a > b) a else b

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

functions repeat(
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

functions printFullName(firstName: String, lastName: String) {
    println("${firstName} ${lastName}")
}

PrintFullName().buiilder()
.firstName()
.lastName()
.build()
```

### Java에는 왜 named argument가 없을까? (AI가 설명해준 내용)

- API 계약 문제: Java에서 매개변수 이름은 현재 API의 일부가 아니다. 바이트코드에 기본적으로 포함되지 않고, 라이브러리 개발자는 이름을 자유롭게 변경할 수 있다. Named arguments를 도입하면
  매개변수 이름이 공식 API 계약이 되어 이름 변경 시 호환성이 깨진다
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
    
functions printAll(vararg strings: String) {
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

functions main() {
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
- Kotlin 친화적: 컴패니언/톱레벨/확장함수로 배치하여 발견성과 사용성이 향상된다
- 결론: 변환은 의미가 중요하므로 이름으로 의도를 드러내는 정적 팩토리가 더 적합하다

### 결론은 Kotlin에서 부 생성자를 사용할 일은 거의 없지만 서로 다른 super()호출이 꼭 필요할 때는 사용된다고 한다

## 커스텀 getter, setter, backing field

- 함수를 만들 수 있고, 함수 대신 프로퍼티로 만들 수 있다

```
functions main() {
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

    functions isAdult(): Boolean {
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
- person.name을 호출하면 name 프로퍼티의 getter가 실행되는데, getter 내부에서 다시 name을 참조하면 또다시 getter가 호출된다. 이렇게 getter가 자기 자신을 계속 호출하는 무한
  루프가 발생하게 된다
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
    abstract functions move()
}

class Cat(
    species: String, -> 1
) : Animal(species, 4) -> 3 { -> 2
    override -> 4 functions move() { 
        println("사람이 걷고 있습니다")
    }
}

class Penguin (
    species: String,
) : Animal(species, 2) {

    private val wingCount: Int = 2

    override functions move() {
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

    functions act() {
        println(swimAbility) // Penguin에서 구현했기 때문에 swimAbility 사용가능
        println("수영수영")
    }

    // functions swim() 추상 메서드 가능
}

interface Flyable {
    functions act() {
        println("날아날아")
    }
}

class Penguin (
    species: String,
) : Animal(species, 2), Swimable, Flyable {

    private val wingCount: Int = 2

    override functions move() {
        println("펭귄이 걷고 있습니다")
    }

    override val legCount: Int
            get() = super.legCount + this.wingCount

    override functions act() {
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
functions main() {
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
    - 실제 동작: 상위 클래스 Base의 init 블록이 number를 출력할 때, 아직 하위 클래스 Derived의 number 값(300)이 할당되지 않았다. 따라서 Int 타입의 기본값인 0이 출력된
      것이다.
3. 핵심 주의사항 및 해결책: 상위 클래스를 설계할 때, 생성자나 init 블록 안에서는 하위 클래스에서 override될 가능성이 있는 open 프로퍼티에 접근해서는 안 된다. 이는 예층 불가능한 동작을 유발할
   수 있기 때문이다
    - 권장 사항: 상위 클래스의 초기화 로직에 사용되는 프로퍼티는 open 키워드로 사용하지 않는 것이 안전하다

## 상속 관련 지시어 정리

- final: override를 할 수 없게 한다. default로 보이지 않게 존재한다
- open: override를 열어 준다(상속할 수 있게 해준다)
- abstract: 반드기 override 해야한다
- override: 상위 타입을 오버라이드 하고 있다

<hr>

# 11. 접근 제어를 다루는 방법

## 자바와 코틀린의 가시성 제어

- 가시성 제어: 누가 이 선언을 볼 수 있나를 정하는 규칙이며 Kotlin의 package는 namespace(이름 공간용)이며 가시성은 모듈 기반으로 제어 된다
- Kotlin에서는 패키지라는 개념을 접근 제어에 사용하지 않는다. Java의 protected나 default(아에 없음)에서 패키지 관련 부분이 제거되고 모듈에 접근 제어하는 기능이 생겼다
- Java의 기본 접근 지시어는 default, Kotlin의 기본 접근 지시어는 public

### Java에서의 네 가지 접근 제어자

- public: 모든 곳에서 접근 가능
- protected: 같은 패키지 또는 하위 클래스에서만 접근 가능
- default: 같은 패키지에서만 접근 가능
- private: 선언될 클래스 내에서만 접근 가능

### Kotlin에서의 네 가지 접근 제어자

- public: 모든 곳에서 접근 가능
- protected: 선언된 클래스 또는 하위 클래스에서만 접근 가능 (Java에서는 같은 패키지 접근 가능하지만 Kotlin에서는 불가능)
    - Kotlin에서는 패키지를 namespace를 관리하기 위한 용도로만 사용하고 가시성 제어에는 사용되지 않는다
    - namespace는 이름 충돌을 막기 위한 이름의 주소/폴더로 같은 이름이라도 어느 네임스페이스에 속했는지로 구분할 수 있다
- internal: 같은 모듈에서만 접근 가능 (default는 없어짐)
    - 모듈이란 ㅎ한 번에 함께 컴파일되는 단위로 Gradle 서브 프로젝트, Maven 아티팩트 등이 있다
    - 하위 모듈에 internal 클래스/함수가 있어도, 상위 모듈(다른 모듈)에서는 가져올 수 없다
- private: 선언될 클래스 내에서만 접근 가능

```
namespace

// 서로 다른 패키지의 같은 이름 클래스
package com.app.util
class StringUtils { ... }

package com.lib.util  
class StringUtils { ... }

// 사용 시 구분
import com.app.util.StringUtils as AppStringUtils
import com.lib.util.StringUtils as LibStringUtils

laravel의 namespace와 유사한 듯 (?)
```

## 코틀린 파일의 접근 제어

- 코틀린은 .kt 파일에 변수, 함수, 클래스 여러개를 바로 만들 수 있다
- public: 기본값으로 어디서든 접근 가능
- protected: 파일(최상단)에는 사용 불가능
    - protected는 클래스 내부 + 그 하위 클래스 에서만 보이도록 하는 가시성. 그래서 파일의 최상위(Top-level)에서는 쑬 수 없다. 최상위 선언은 어떤 클래스의 멤버가 아니기 때문이다(컴파일
      에러). 또한 인터페이스 멤버에도 protected는 허용되지 않는다
- internal: 같은 모듈에서만 접근 가능
- private: 같은 파일에서만 접근 가능

## 다양한 구성요소의 접근 제어

- 생성자에 접근 지시어를 사용하려면 constructor를 직접 작성하여야 한다

```
class Car internal constructor(
    val price: Int
)

class Car protected constructor(val price: Int)
// Warning: 'protected' visibility is effectively 'private' in a final class
```

- protected의 접근 범위는 자기 자신(같은 클래스)와 하위 클래스(상속받은 클래스)
- Final class의 제약: Kotlin 클래스는 기본적으로 final, Final 클래스는 상속 불가능하므로 하위 클래스가 존재할 수 없다
-

```
class Car protected constructor(val price: Int) {
    // protected가 실질적으로 private과 동일
    // 하위 클래스가 없으므로 자기 자신만 접근 가능
}

// 해결방법 1: open으로 상속 허용
open class Car protected constructor(val price: Int)

// 해결방법 2: private으로 명시적 변경
class Car private constructor(val price: Int)
```

### Java에서 Util 클래스 설계 패턴의 이유

- Util 클래스는 정적 메서드만 제공하는 것이 목적이므로, 인스턴스가 생성되면 안 된다(완전한 인스턴스화 방지가 목적)
- Abstract는 직접 인스턴스화를 방지해주고 private constructor는 상속을 통한 우회를 방지해준다

```
public abstract class StringUtils {
    // 추상 클래스는 직접 인스턴스화 불가능
    // new StringUtils();// 컴파일 에러
    
    private StringUtils() {} // 하위 클래스에서도 생성자 호출 차단
    // 이 부분이 없다면:
    class MyUtils extends StringUtils {} // 가능 
    
    public boolean isDirectoryPath(String path) {
        return path.endsWith("/");
    }
}
```

```
StringUtil.kt 파일
functions isDirectoryPath(path: String): Boolean {
    return path.endsWith("/")
}

// 정적 메서드 처럼 사용 가능
public class JavaMain {
    public static void main(String[] args) {
        StringUtilsKt.isDirectoryPath("/");
    }
}

```

- Java의 제약을 극복하기 위한 방어적 설계 패턴인 이유 (AI가 설명해준 내용)
    - Java: 언어 제약 떄문에 억지로 클래스를 만들고, 그 부작용을 막기 위해 복잡한 방어 코드 작성
    - Kotlin: 언어 차원에서 적절한 도구들(object, 최상위 함수)을 제공하여 자연스럽고 안전한 방식으로 해결
- 핵심: Java는 제약을 우회하기 위한 패턴이고 Kotlin은 제약 자체가 ㅇ벗어서 더 직관적인 방법 제공

### 프로퍼티 가시성 제어 방법

- 전체 프로퍼티 가시성 제어

```
class Bar(
    internal val name: String, // getter만 internal
    private var owner: String // getter, setter 모두 private
)
```

- Getter/Setter 개별 제어

```
class Bar(
    _price: Int
) {
    var price = _price
        private set // getter는 public, setter만 private
}
```

- 한 번에 설정: 프로퍼티 선언 앞에 가시성 지정자
- 개별 설정: private set, internal get 등으로 getter/setter 따로 제어

```
class User (
    (public) val id: String, // 읽기 전용, 모든 곳에서 접근
    private var password: String, // 완전 비공개
    _balance: Int
) {
    var balance = _balance
        private set              // 읽기는 공개, 수정을 클래스 내부만
        
    functions deposit(amount: Int) {
        balance += amount        // 내부에서만 수정 가능
    }
}
```

- 프로퍼티 전체와 getter/setter 개별 가기성 제어로 유연한 캡슐화 구현 가능

## Java와 Kotlin을 함께 사용할 경우 주의할 점

- Internal은 바이크 코드 상 public이기 때문에 Java 코드에서는 Kotlin 모듈의 internal 코드를 가져올 수 있다
    - 상위 모듈이 Java, 하위 모듈이 Kotlin일 경우 하위 모듈(Kotlin)의 internal field나 함수는 상위 모듈(Java)에서 사용 가능
- Kotlin의 protected와 Java의 protected는 다르다. Java는 같은 패키지의 Kotlin protected 멤버에 접근할 수 있다
    - 같은 패키지의 Java 코드는 그 멤버에 접근 가능하지만, 같은 패키지의 Kotlin 코드는 접근 불가

<hr>

# 12. Object 키워드를 다루는 방법

- Java는 Object 지시어가 없지만 Kotlin에서는 Object라는 별도의 지시어가 추가되었다

## static 함수와 변수

- Java와 달리 Kotlin은 static이 없다
- static: 클래스가 인스턴스화 될 때 새로운 값이 복제되는게 아니라 정적으로 인스턴스끼리의 값을 공유
- companion object: 클래스와 동행하는 유일한 오브젝트
    - 동반객체도 하나의 객체로 간주되기 때문에 이름을 붙일 수도 있고 interface를 구현할 수도 있다
        - 이름을 붙이지 않는 다면 Companion이라는 이름이 생략된 것 (사용할 때 Class.Companion 으로 사용)이고 하나만 존재할 수 있다
    - 유틸성 함수를 넣을 수도 있지만 유틸성 함수는 최상단 파일에 활용하는 것을 추천

```
Java
public class JavaPerson {
    private static final int MIN_AGE = 1;

    public static JavaPerson newBaby(String name) {
        return new JavaPerson(name, MIN_AGE);
    }

    private String name;

    private int age;

    public JavaPerson(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

Kotlin
class Person private constructor(
    var name: String,
    var age: Int,
) {
    companion object Factory : Log {
        private const val MIN_AGE = 1
        functions newBaby(name: String): Person {
            return Person(name, MIN_AGE)
        }

        override functions log() {
            println("companion object of Person class")
        }
    }
}

interface Log {
    functions log()
}
```

- const가 없을 경우 val MIN_AGE에 값을 런타임 시 변수 할당, const가 있을 경우 컴파일 시에 변수 할당
- const는 진짜 상수에 붙이기 위한 용도이며 기본 타입과 String에만 붙일 수 있다

## 싱글톤

- 싱글톤: 한 하나의 유일한 객체를 만들기 위한 패턴으로 생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나이고,
  최초 생성 이후에 효출된 생성자를 최초의 생성자가 생성한 객체를 리턴하며 주로 Database Connection Pool과 같은 상황에서 많이 사용된다

```
Java
public class JavaSingleton {
    // 클래스 로딩 시 즉시 인스턴스 생성(Eager Initialization)
    // Static final로 불변성과 단일성 보장
    private static final JavaSingleton INSTANCE = new JavaSingleton();

    // private 생성자로 외부에서 new 키워드를 통한 인스턴스 생성 차단
    private JavaSingleton() {}

    // 유일한 인스턴스에 접근할 수 있는 전역 접근점 제공
    // static 메서드로 인스턴스 없어도 호출 가능
    public static JavaSingleton getInstance() {
        return INSTANCE;
    }
}

Kotlin
object Singleton

object Singleton {
    var a: Int = 0
}
```

## 익명 클래스

- 익명 클래스: 특정 인터페이스나 클래스를 상속받은 구현체를 일회성으로 사용할 때 쓰는 클래스

```
interface
public interface Movable {
    void move();
    void fly();
}

Java
public static void main(String[] args) {
    moveSomething(new Movable() {
        @Override
        public void move() {
            System.out.println("move");
        }

        @Override
        public void fly() {
            System.out.println("fly");
        }
    });
}

private static void moveSomething(Movable movable) {
    movable.move();
    movable.fly();
}
    
Kotlin
functions main (){
    moveSomething(object: Movable {
        override functions move() {
            println("move")
        }
        
        override functions fly() {
            println("fly")
        }
    
    })
}

private functions moveSomething(movable: Movable) {
    movable.move()
    movable.fly()
}
```

<hr>

# 13. 중첩 클래스를 다루는 방법

## 중첩 클래스의 종류
- 중첩클래스는 Static 중첩 클래스와 Inner 클래스를 이야기하는 경우가 많다
  - Static 중첩 클래스는 독립적이고 재사용이 가능한 헬러 클래스
  - Inner 클래스는 외부 클래스와 밀접한 관계의 구현체
  - Local/Anonymous 클래스는 특수한 상황에서만 사용하고, 대부분 람다나 메서드 차조로 대체 가능한 이유로 실무에서 많이 사용되지 않는다

### Static을 사용하는 중첩 클래스 (권장되는 중첩 클래스)
- 클래스 안에 static을 붙인 클래스로 밖의 클래스 직접 참조 불가

### Static을 사용하지 않는 중첩 클래스 (권장되는 않는 중첩 클래스)
- Inner Class(내부 클래스): 밖의 클래스 직접 참조 가능 (아래는 이펙티브 자바 일부)
  - 내부 클래스는 숨겨진 외부 클래스 정보를 가지고 있어, 참조를 해지하지 못하는 경우 메모리 누수가 생길 수 있고, 이를 디버깅 하기 어렵다
  - 내부 클래스의 직렬화 형태가 명확하게 정의되자 않아 직렬화에 있어 한계가 명확하다
  - 클래스 안에 클래스를 만들때는 static 클래스를 사용하라
  - 내부 클래스가 외부 클래스를 참조함으로 인해 생기는 몇가지 문제점이 있다
- Local Class(지역 클래스): 메서드 내부의 클래스를 정의 (메서드 내부에서만 사용 가능)
- Anonymous Class(익명 클래스): 일회성 클래스 (선언 지점에서만 사용 가능 - 람다로 많이 대체하면사 많이 사용하지 않음)

- Kotlin은 Inner Class의 Guide를 충실히 따르고 있다
```
Java
public class JavaHouse {

    private String address;

    private LivingRoom livingRoom;

    public JavaHouse(String address) {
        this.address = address;
        this.livingRoom = new LivingRoom(10);
    }

    public LivingRoom getLivingRoom() {
        return livingRoom;
    }


    public static class LivingRoom { 
        private double area;

        public LivingRoom(double area) {
            this.area = area;
        }
    }
    
    // 권장되지 않는 내부 클래스
    public class LivingRoom {
        private double area;

        public LivingRoom(double area) {
            this.area = area;
        }

        public String getAddress() {
            return JavaHouse.this.address; // 바깥 클래스와 연결되어 있다
        }
    }
}

Kotlin
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
class House(
    private val address: String,
    private val livingRoom: LivingRoom,
) {

    inner class LivingRoom(
        private val area: Double
    ) {
        val address: String
        get() = this@House.address
    }
}
```
- 기본적으로 바깥 클래스를 참조하지 않는다. 바깥 클래스를 참조하고 싶다면 inner 키워드를 추가해야 한다
- Java 클래스 안의 static 클래스 / Kotlin 클래스 안의 클래스 (바깥 클래스 참조 없음, 권장되는 유형)
- Java 클래스 안의 클래스 / Kotlin 클래스 안의 inner 클래스 (바깥 클래스 참조 있음)
### 핵심: 클래스 안에 클래스를 만들 때는 static 클래스를 사용하라!! (이팩티브 자바)

<hr>

# 14. 다양한 클래스를 다루는 방법

## Data Class
```
Java
public class JavaPersonDto {
    private final String name;
    private final int age;

    public JavaPersonDto(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public record JavaPersonDto
(String name, int age) {

}
```

- 데이터(필드) 생성자, getter, equals, hashCode, toString을 IDE(Integrated Development Environment)의 도움을 받아서 작성하거나 lombok을 활용해서 작성할 수 있다
- 클래스가 장황해지거나 추가적인 처리를 해줘야 하는 단점이 있지만 Java의 record를 활용하면 완벽하게 대체 가능하다

```
Kotlin
data class PersonDto(
    val name: String,
    val age: Int
)
```
- Java의 record나 Kotlin의 data class는 getter, equals, hashCode, toString, getter를 자동으로 제공해준다
- 단 getter 메서드명이 getName()이 아닌 name()으로 바뀐다. 즉, get없이 필드명으로 대체된다
- Kotlin의 data class에서 named argument를 활용하면 builder pattern을 쓰는 것 같은 효과도 있다

### 전통적인 Builder Pattern와 Kotlin의 Data Class + Named Arguments (AI가 설명해준 내용)
```
전통적인 Builder Pattern
class User private constructor(
    val name: String,
    val age: Int,
    val email: String,
    val phone: String,
    val address: String
) {
    class Builder {
        private var name: String = ""
        private var age: Int = 0
        private var email: String = ""
        private var phone: String = ""
        private var address: String = ""
        
        functions name(name: String) = apply { this.name = name }
        functions age(age: Int) = apply { this.age = age }
        functions email(email: String) = apply { this.email = email }
        functions phone(phone: String) = apply { this.phone = phone }
        functions address(address: String) = apply { this.address = address }
        
        functions build() = User(name, age, email, phone, address)
    }
}

// 사용
val user = User.Builder()
    .name("John")
    .age(30)
    .email("john@email.com")
    .phone("010-1234-5678")
    .address("Seoul")
    .build()
    
```
- Builder Pattern의 핵심 목적
  - 가독성: 어떤 값이 어떤 필드에 들어가는지 명확
  - 선택적 매개변수: 일부 필드만 설정 가능
  - 순서 무관: 매개변수 순서에 구애받지 않음
  - lombok을 활용하면 깔끔해진다 (Java에서는 @Builder를 많이 사용)
```
Data Class + Named Arguments
data class User(
    val name: String,
    val age: Int = 0,
    val email: String = "",
    val phone: String = "",
    val address: String = ""
)

// 동일한 효과, 훨씬 간단
val user = User(
    name = "John",
    age = 30,
    email = "john@email.com",
    phone = "010-1234-5678",
    address = "Seoul"
)
```
- Named Argument의 장점
  - Builder Pattern의 이점 제공
  - 보일러플레이트 코드 제거 (Builder 클래스 불필요)
  - 컴파일 타임 안전성 (잘못된 매개변수 즉시 발견)
- Named Argument는 Builder Pattern의 장점을 언어 차원에서 제공하여 복잡한 Builder 구현 없이도 가동성이 있고 유연한 객체 생성 가능. Kotlin이 간결함과 표현력을 동시에 추구하는 철학의 좋은 예시
- Lombok을 사용하면 Builder Pattern의 보일러플레이트 코드가 어느정도 제거되지만 Named Argument는 도구 의존성 없이 언어 차원에서 Builder Pattern의 이점을 제공주므로 Lombok Builder보다 Named Argument가 선호된다
  - 더 안정적 (의존성/설정 이슈 없음), 더 투명함 (생성된 코드가 아닌 실제 코드), 더 효율적 (런타임 오버헤드 없음), 더 일관됨 (함수 호출과 동일 문법)

## Enum Class

### Enum의 특징
- 추가적인 클래스를 상속받을 수 없다
- 인터페이스는 구현할 수 있으며, 각 코드가 싱글톤이다, `KOREA("KO"), AMERICA("US");`
```
Java
public enum JavaCountry {
    
    KOREA("KO"),
    AMERICA("US");

    private final String code;

    JavaCountry(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    
    private static void handleCountry(JavaCountry country) {
        if (country == JavaCountry.KOREA) {
            System.out.println("KOREA 로직");
        }

        if (country == JavaCountry.AMERICA) {
            System.out.println("AMERICA 로직");
        }
        
        // 새로운 enum 값 추가 시 컴파일러가 알려주지 않음
        // else 처리가 애매함 (enum이므로 다른 값이 올 가능성 없음)
    }
    
}
```

### Java Enum의 문제점
- 가독성 저하: if-else 체인이 길어짐
- 누락 감지 불가: 새 enum 값 추가 시 경고 없음
- 불필요한 else: enum 특성상 다른 값이 올 수 없는데도 방어 코드 필요

```
Kotlin
functions handleCountry(country: Country) {
    when (country) {
        Country.KOREA -> println("Korea 로직")
        Country.AMERICA -> println("AMERICA 로직")
        // else 불필요 - 컴파일러가 모든 케이스 확인
    }
}

// 완전성 검사  (Exhaustive Check)
enum class Country(
    private val code: String
) {
    KOREA("KO"),
    AMERICA("US"),
    JAPAN("JP"), // 추가
}
// 컴파일르가 즉시 경고: "when expression must be exhaustive"

Return 타입에서의 강력함
functions handleCountry(country: Country) {
    return when (country) {
        Country.KOREA -> println("Korea 로직")
        Country.AMERICA -> println("AMERICA 로직")
        // JAPAN 케이스 누락 시 컴파일 에러
    }
}
``` 
- Kotlin의 when은 타입 안정성과 가도성을 동시에 제공하여 enum 분기 처리를 훨씬 안전하고 편리하게 만들어 준다

## Sealed Class, Sealed Interface
- Sealed의 뜻은 봉인, 포장
- The body was sealed in a lead coffin - 그 시신은 납으로 된 관 속에 넣어져 밀봉되었다
- The organs are kept in sealed plastic bags - 그 장기들은 밀봉된 비닐봉지 속에 보관된다

### Sealed Class 탄생 배경
- 문제: "상속 가능한 추상클래스를 만들고 싶지만 외부 라이브러리 사용자가 함부러 상속하는 것은 막고 싶다"
- 해결: 내부에서만 제어 가능한 제한된 상속 계층 구조를 만들자
- 컴파일 타임 제한
```
sealed class Person(
    val name: String,
    val age: Int
) 

class Kim: Person("Kim", 10)
class Park: Person("Park", 20)
class Lee: Person("Lee", 30)

// 외부에서 추가 상속 불가능
```

- 완전한 분기 처리
```
private functions handlePerson(person: Person) {
    when(person) {
        is Kim -> println("Kim")
        is Park -> println("Park")
        is Lee -> println("Lee")
        // else 불필요 - 컴파일러가 모든 타입을 알고 있음
    }
}
```
- 변경 감지
  - 새로운 하위 클래스 추가/제거 시 컴파일 에러로 알림
  - when 표현식이 누락된 케이스 자동 감지
  
- 실용적 활용 (Entity/DTO 추상화)
```
sealed class ApiResponse<T>

data class Success<T>(val data: T) : ApiResponse<T>()
data class Error<T>(val code: Int, val message: String) : ApiResponse<T>()
object Loading : ApiResponse<Nothing>()

// 타입 안전한 분기 처리
functions handleResponse(response: ApiResponse<User>) = when (response) {
    is Success -> showUser(response.data)
    is Error -> showError(response.message)
    is Loading -> showLoading()
}
```
- 라이브러리 개발자는 안전한 API설계가 가능
- 사용자는 예측 가능하고 완전한 분기 처리 가능
- 컴파일러는 타입 안정성을 보장
- Sealed Class는 enum의 안전성과 클래스의 유연성을 결합한 강력한 도구이다

<hr>

# 15. 배열과 컬렉션을 다루는 방법

## 배열
- 많이 사용하지 않는다. 이펙티브 자바에서도 '배열보다 리스트를 사용하라'고 말한다. 하지만 알아보자
```
val array = arrayOf(100, 200)
for(i in array.indices) {
    println(" ${i} ${array[i]}")
}

for((idx, value) in array.withIndex()) {
    println(" $idx $value")
}

val newArray = array.plus(300) // 값 추가
for((idx, value) in newArray.withIndex()) {
    println(" $idx $value")
}
```

## 코틀린에서의 Collection - List, Set, Map
- 컬렉션을 만들어 줄때는 이 컬렉션이 불변인지, 가변인지 꼭 선언해주어야 한다
  - "코드의 의도를 타입으로 표현"하면 컬렉션을 받는 함수나 변수만 봐도 수정 가능 여부를 즉시 알 수 있어 더 안전하고 예측 가능한 코드 작성 가능

### 가변 컬렉션과 불변 컬렉션의 차이
- 가변(Mutable) 컬렉션: 컬렉션에 element를 추가, 삭제할 수 있다
- 불변(Immutable) 컬렉션: 컬렉션에 element를 추가, 삭제할 수 없다
  - 불변 컬렉션이라 하더라도 참조 타입의 element 필드는 바꿀 수 있다.
  - 불변 리스트에 Money 객체 두 개가 있다 (price 1,000원, price 2,000원)
  - 새로운 Money를 추가/삭제는 불가능하지만, list.get(0)으로 첫 번째 Money에 접근해서 price를 5,000원으로 변경하는 것은 가능하다
  - 즉, 블변 컬렉션은 요소의 추가/삭제만 막을 뿐, 내부 객체의 필드 변경은 막지 않는다

### Collection - List
#### 생성
```
functions main() {
    // 불변 리스트
    // listOf를 통해 '불변 리스트'를 생성
    val numbers = listOf(100, 200) // 타입추론을 해주기 떄문에 타입 생략 가능

    val emptyList = emptyList<Int>() // 비어있기 때문에 type을 명시적으로 적어주어야 한다

    printNumbers(emptyList())
    // emptyList()이지만 printNumbers(매개변수)를 통해 타입 추론이 가능해서 type 생략 가능
    
    // 가변 리스트 - Java에서의 Collection List 기능들을 사용 가능
    val mutableNumbers = mutableListOf(100, 200)
    mutableNumbers.add(300) // 추가 가능
}

private functions printNumbers(numbers: List<Int>) { }
```

#### Java
```
final List<Integer> numbers = List.of(100, 200);

// 하나를 가져오기
System.out.println(numbers.get(0));

// For Each
for (Integer number : numbers) {
    System.out.println(number);
}

// 전통적인 For문
for (int i = 0; i < numbers.size(); i++) {
    System.out.printf("%s %s \n", i, numbers.get(i));
}
```

#### Kotlin
```
functions main() {
    val numbers = listOf(100, 200)
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
```
- 변수를 설정할 때와 마찬가지로 간단한 Tip은 먼저 불변 리스트를 만들고, 꼭 필요한 경우 가변 리스트로 변경하는 것이 코드가 깔끔해지고 불필요한 가변 리스트 사용이 줄어잔다 

### Collection - Set
- 집합은 List와 다르게 순서가 없고, 같은 element는 하나만 존재할 수 있다
- 자료구조적인 의미만 제외하면 모든 기능이 List와 비슷하다
- 
```
functions main() {
    val numbers = setOf(100, 200)

    val mutableNumbers = mutableSetOf(100, 200)

    // For Each
    for (number in numbers) {
        println(number)
    }

    // 전통적인 for문
    for ((idx, value) in numbers.withIndex()) {
        println(" $idx $value")
    }
}
```
- 기본 구현체는 LinkedHashSet이다

### Collection - Map
- Kotlin도 동일하게 MutableMap을 만들어 넣을 수도 있고, 정적 팩토리 메소드를 바로 활용할 수도 있다

```
functions main() {
    val oldMap = mutableMapOf<Int, String>() // 타입을 추론할 수 없어, 타입 지정
    oldMap.put(1, "Monday") // Java처럼 put사용
    oldMap.put(2, "Tuesday")

    oldMap[3] = "Wednesday" // map[key] = value 사용

    // 중위 호출 방식으로 mapOf는 Pair를 받는 함수이다
    // mapOf(key to value)를 사용해 불변 map 생성
    mapOf(1 to "Monday", 2 to "Tuesday", 3 to "Wednesday")

    for (key in oldMap.keys) {
        println("$key -> ${oldMap[key]}")
    }

    for ((key, value) in oldMap.entries) {
        println("$key -> $value")
    }
}
```

## 컬렉션의 null 가능성, Java와 함께 사용하기
- ? 위치에 따라 null 가능성 의미가 달라지므로 차이를 잘 이해해아 한다
- List<Int?>: 리스트에 null이 들어갈 수 있지만, 리스트는 절대 null이 아님
```
List<Int?> - 요소만 nullable

val list: List<Int?> listOf(1, 2, null, 4)
// 리스트 자체는 절대 null이 아니지만 리스트 요소는 null 가능

println(list.size) // 4 (항상 안전)
println(list[2]) // null (요소가 null일 수 있음)
println(list[0]!! + 10) // 11 (null 체크 후 사용)
```

- List<Int>?: 리스트에는 null이 들어갈 수 없지만, 리스트는 null일 수 있음
```
List<Int>? - 리스트만 nullable

val list: List<Int>? = null // 또는 listOf(1, 2, 3)
// 리스트 자체가 null일 가능성이 있지만 리스트 요소는 null 불가능

println(list?.size) // null (리스트가 null일 수 있음)
list?.let {
    println(it[0] + 10) // 요소를 확실히 Int 타입
}
```

- List<Int?>?: 리스트에 null이 들어갈 수도 있고, 리스트가 null일 수도 있음
```
List<Int?>? - 둘 다 nullable

val list: List<Int?>? = null // 또는 listOf(1, null, 3)
// 리스트 자체가 null가능하며 리스트 요소도 null 가능

list?.let { safeList ->
    safeList.forEach { element ->
        element?.let { value -> // 이중 null 체크 필요
            println(value + 10)
        }
    }
}
```

- 실용적 사용 예시 (함수 파라미터에서의 활용)
```
// 요소만 nullable - 빈 값들을 처리할 때
functions processUserNames(names: List<String?>) {
    names.filterNotNull().forEach { name ->
        println("Hello, $name")
    }
}

// 리스트만 nullable - 선택적 데이터를 받을 때
functions processOptionalData(data: List<String>?) {
    data?.forEach { item -> // item은 확실히 String 
        println(item.uppercase())
    }
}
```

- 반환 타입에서의 활용
```
// 빈 리스트 vs null로 의미 구분
functions findUsers(query: String): List<User>? {
    return if(query.isBlock()) null // 검색 안함
        else userRepository.search(qeury) // 빈 리스트 or 결과 리스트
}
```
- ?의 위치가 null 가능성의 범위를 결정
  - 타입 안에 ?: 해당 타입의 값이 null 가능
  - 타입 밖에 ?: 전체 객체가 null 가능
- 이 두 개의 차이를 이해하면 더 정확한 타입 설계와 언전한 null처리 가능


### Java는 읽기 전용 컬렉션과 변경 가능 컬렉션을 구분하지 않는다

#### Java는 nullable 타입과 non-nullable 타입을 구분하지 않는다
```
// Kotlin: 불변 리스트 생성
val immutableList = listOf("A", "B", "C")
javaMethod(immutableList) // Java로 전달

// Java: 컬렉션 가변성을 구분하지 않음
public void javaMethod(List<String> list) {
    list.add("D") // Kotlin의 불변성 가정을 위반
}
```
- Kotlin의 불변 리스트가 Java에 의해 변경되어 예상치 못한 동작 발생

#### Java는 nullable 타입과 non-nullable 타입을 구분하지 않는다
```
// Kotlin: non-null 리스트 생성
val nonNullList = mutableListOf("A", "B", "C")
javaMethod(nonNullList) // Java로 전달

// Java: null 안전성을 구분하지 않음
public void javaMethod(List<String> list) {
    list.add(null) // Kotlin의 non-null 가정을 위반
}
```
- Kotlin에서 null 체크 없이 접근 시 런탕임 에러 위험

#### 해결책 - Collections.unmodifiableXXX() 활용
```
import java.util.COllections

// Kotlin에서 Java로 전달 시 수정 방지
val protectedList = Collections.unmodifiableList(originalList)
javaMethod(protectedList)
```
- Java에서 add(), remove() 등 호출 시 UnsupportedOperationException 발생
- Kotlin 컬렉션의 불변성을 Java 레벨에서도 보장

#### 실용적 패턴
```
classs KotlinService {
    private val items = mutableListOf("A", "B", "C")
    
    // Java에 안전하게 노출
    functions getItemsForJava(): List<String> {
        return Collections.unmodifiableList(items)
    }
}
```
- Kotlin 컬렉션이 Java에서 호출되면 내용이 변할 수 있음을 감안하고, Collections.unmodifiableXXX()를 활용하여 변경 자체를 원천 차단하는 것이 안전
- 물론 다른 방어코드들도 많이 있다 (방어적 복사, 명시적 타입 검증, Java에서 @Nullable 어노테이션 사용 등)

### Kotlin에서 Java 컬렉션을 가져다 사용할 때 플랫폼 타입을 신경써야 한다

#### Java에서 Kotlin으로 컬렉션 전달
```
// Java
public List<Integer> getNumbers() {
    returhn Arrays.asList(1, 2, 3);
}

// Kotlin에서 받을 때 - 어떤 타입인지 불분명
val numbers = javaService.getNumbers() // List<Int!> (플랫폼 타입)
```
- Kotlin은 List<Int?>, List<Int>?, List<Int?>? 중 어떤 것인지 알 수 없다

#### 해결 방법
- Java 코드 분석
```
// Java 코드를 직접 확인하여 맥락 파악
public List<Integer> getValidNumbers() {
    // 구현을 보고 null 반환 여부, null 요소 포함 여부 확인
    return validNumbers;
}
```

- 적절한 Wrapping으로 영향 범위 최소화
```
class JavaCollectionWrapper {
    private val javaService = javaService()
    
    // Java 호출 지점을 래핑하여 타입 명확화
    functions getNumbers() :List<Int> {
        return javaService.getNumbers()
            ?.filterNotNull() // null 요소 제거
            ?: emptyList() // null 리스트 처리
    }
    
    functions getSafeNumbers(): List<Int?> {
        return javaService.getNumbers() ?: emptyList()
    }
}
```

- 방어적 검증
```
functions processJavaList() {
    val javaList = javaService.getNumbers()
    
    // 안전한 사용을 위한 검증
    val safeList: List<Int> = when {
        javaList == null -> emptyList()
        else -> javaList.filterNotNull()
    }
    
    // 안전하게 사용
    safeList.forEach { number ->
        println(number * 2) // null 걱정 없음
    }
}
```
- 플랫폼 타입 대응 원칙
  - Java 코드 분석: 구현체를 직접 확인하여 null 가능성 파악
  - 경계에서 래핑: Java 호출 지점을 감싸서 타입 안전성 확보
  - 영향범위 최소화: 플랫폼 타입이 Kotlin 코드 전체로 퍼지지 않도록 차단
  - 방어적 검증: null 체크와 필터링으로 안정성 확보
- Java 컬렉션을 가져올 때는 플랫폼 타입의 모호함을 인지하고, 적절한 래핑과 검증을 통해 타입 안전성을 확보하는 것이 중요

<hr>

# 16. 다양한 함수를 다루는 방법

## 확장함수

### 확장함수 탄생 배경
- 고민: Java와 100% 호환하면서, 기존 Java 코드 위에 자연스럽게 Kotlin 기능을 추가할 수 없을까?
  - Java로 만들어진 라이브러리를 수정하지 않고도 Kotlin의 장점(val/var, null-safety 등)을 활용하여 기능 확장
- 해결 아이디어: 클래스 밖에서 함수를 만들되, 마치 클래스 안에 메서드처럼 호출하도록 하자
```
fun String.lastChar(): Char {
    return this[this.length - 1] // 문자열이 존재하는 가장 마지막 문자열 반환
}

fun 확장하려는클래스.함수이름(파라미터): 리턴타입 {
    // this를 이용해 실제 클래스 안의 값에 접근
}
```
- fun: 함수
- String: String Class를 확장 (수신객체 타입)
- this: 함수 안에서는 this를 통해 인스턴스 접근 가능 (수신 객체)

### 확장함수의 캡슐화 보호 설계
#### 설계 고민
- 우려사항: 확장함수가 public이면서 클래스의 private 멤버에 접근할 수 있다면, 캡슐화 원칙이 깨지는 것 아닌가?
  - 클래스 외부에서 정의된 확장함수가 클래스 내부의 비공개 데이터에 자유롭게 접근할 수 있다면, 객체지향의 핵심 원칙인 정보 은닉과 캡슐화가 무너질 위험이 있다

#### 해결책: 접근 제한
- 설계 결정: 확장함수는 public 멤버에만 접근 가능하도록 제한
  - 확장함수에서는 클래스의 private이나 protected 멤버를 가져올 수 없게 설계되었다. 이를 통해 클래스의 내부 구현을 보호하면서도 기능 확장의 이점을 얻을 수 있다

- 확장은 가능하되, 캡슐화는 보호한다. 확장 함수의 접근 제한은 기능 확장의 편의성과 객체지향 원칙 준수 사이의 균형 갖춘 설계이다

### 멤버함수 VS 확장함수 우선순위
- 호출 우선순위 규칙: 동일한 시그니처가 있을 때는 멤버함수가 항상 우선 호출
- 잠재점 위험 시나리오
  - 처음: 확장함수만 존재 → 정상 동작
  - 나중: 라이브러리 업데이트로 동일한 시그니처의 멤버함수 추가
  - 결과: 기존 확장함수가 호출되지 않음 → 동작 변경
- 확장함수는 멤버함수와의 충돌 위험을 염두해서 신중하게 네이밍 해야 한다

### 확장함수의 정적타입 기반 호출
- 확장함수는 변수의 선언 타입(정적 타입)에 따라 어떤 함수가 호출될지 컴파일 타임에 결정된다
- 확장함수는 다형성을 지원하지 않으며, 변수의 정적 타입에 의해서 결정. 이는 확장함수가 컴파일 타임에 정적으로 결정되기 때문

## Java에서의 Kotlin 확장함수 사용 가능
```
public class JavaMain {
    public static void main(String[] args) {
        StringUtilsKt.lastChar("ABC")
    }
}
```

### 확장 프로퍼티: 확장함수의 발전형
- 확장 프로퍼티는 확장함수 + custom getter의 조합으로, 기존 클래스에 프로퍼티처럼 접근 가능한 기능으로 기존 Java 클래스를 Kotlin 스타일로 자연스럽게 사용 가능하게 해준다

## infix 함수 (중위 함수)
- infix 함수는 함수 호출을 더 자연스러운 언어처럼 표현해주는 기능

```
// 일반 확장함수
fun Int.add(other: Int): Int = this + other
3.add(4)

// Infix 확장함수
infix fun Int.add2(other: Int): Int = this + other
3.add2(4) // 일반 호출
3 add2 4 // 중위 함수 호출 (DSL 스타일)
```

- 핵심 특징은 DSL 느낌의 자연스러운 표현으로 3 add2 4 처럼 수학적 표현이나 자연어에 가까운 형태로 함수를 호출할 수 있어 코드 가독성 향상

#### DSL (Domain Specific Language)
- 특정 도메인(영역)에 특화된 언어로 해당 분야의 전문가가 이해하기 쉬운 형태로 코드를 작성할 수 있게 해주는 언어로 대표적으로 SQL이 있다
- DSL는 코드를 해당 도메인의 언어처럼 만들어 전문가도 이해할 수 있게 하는 것이 목표다
- Kotlin의 DSL: infix 함수, 람다, 확장함수 등을 조합하여 표현력 있고 읽기 쉬운 코드 작성 가능
- DSL은 프로그래밍을 특정 분야의 전문 언어로 만들어 코드의 가독성과 유지보수성을 획기적으로 개선해준다

## inline 함수
- inline 함수는 함수 호출 대신 함소 본문을 호출 지점에 직접 복사하는 기능
```
inline fun Int.add(other: Int): Int {
    return this + other
}

// 사용
3.add(4)

// 실제 컴파일 결과 (개념적)
// 3 + 4 <- 함수 본문이 직접 삽입된다
```

- 사용 목적은 성능 최적화로 스택 프레임 생성/'해제 비용을 절약하여 함수 호출 오버헤드를 제거하고 여러 함수가 연속 호출될 때 overhead를 감소하여 함수 체이닝 최적화를 해준다
- 고차 함수에서 유용
```
// 람다를 매개변수로 받는 함수들
inline fun repeat(times: Int, action: () -? Unit) {
    for (i in 0 until times) action()
}

// 함수 호출 비용 없이 반복
```

#### 주의사항
- 신중한 사용 필요
  - 코드 크기 증가: 함수 본문이 여러 곳에 복사되어 바이트코드 크기 증가
  - 컴파일 시간 증가: 인라인 처리로 컴파일 시간 증가
  - 성능 측정 필수: 실제 성능 개선 여부를 측정하고 사용
- 적절한 사용 시점
  - 작은 함수: 본문이 간단한 함수
  - 고차 함수: 람다를 매개변수로 받는 함수
  - 자주 호출되는 함수: 호출 빈도가 높은 유틸리티 함수

#### 실무 사용 예시
```
// 이미 inline으로 최적화된 함수들
list.forEach { ... } // inline 함수
list.map { ... } // inline 함수
```
- inline 함수는 함수 호출 비용을 제거하여 성능을 향상시키지만, 코드 크기 증가라는 트레이드 오프가 있어 성능 측정을 통한 신중한 사용 필요

## 지역함수
- 지역 함수는 함수 내부에 선언되는 함수로 함수 안의 중복 코드를 제거할 떄 사용

#### 중복 코드
```
fun createPerson (firstName: String, lastName: String): Person {
    if (firstName.isEmpty()) {
        throw IllegalArgumentException("firstName은 비어있을 수 없습니다. $firstName")
    }

    if (lastName.isEmpty()) {
        throw IllegalArgumentException("lastName은 비어있을 수 없습니다. $firstName")
    }

    return Person(firstName, lastName, 1)
}
```

#### 지역 함수 활용
```
fun createPerson (firstName: String, lastName: String): Person {
    fun validateName(name: String, fieldName: String) {
        if (name.isEmpty()) {
            throw IllegalArgumentException("${fieldName}은 비어있을 수 없습니다. $name")
        }
    }

    validateName(firstName, "firstName")
    validateName(lastName, "lastName")

    return Person(firstName, lastName, 1)
}
```
- 장점
  - 중복 코드 제거: 반복되는 로직을 하나로 통합
  - 스코프 제한: 해당 함수 내에서만 사용하는 헬퍼 함수
- 한계
  - 가독성 저하: 코드 depth가 깊어져 복잡해 보임
  - 테스트 어려움: 지역 함수는 직접 테스트하기 어려움
  - 재사용성 부족: 다른 곳에서 사용 불가능

#### 더 나은 대안 (책임분산)
- Person 클래스 자체가 검증 책임을 가지는 것이 더 객체지향적이다
- 지역 함수는 중복 제거의 한 방버이지만, 실무에서는 클래스나 별도 함수로 책임을 분리하는 것이 더 깔끔하기 유지보수 하기 좋다