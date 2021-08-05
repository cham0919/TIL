# 스트림(Stream)

스트림이란 데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소라고 정의할 수 있다.

스트림은 자바 8 api에 새로 추가된 기능으로 이를 ㅇ용하면 선언형(데이터를 처리하는 임시 구현 코드 대신 질의로 표현할 수 있다)으로 컬렉션 데이터를 처리할 수 있다.

또한 멀티스레드 코드를 구현하지 않아도 데이터를 투명하게 병렬로 처리할 수 있다.

<br/>

## 특징

- 선언형
  - 루프 및 if 조건문 없이 선언형 코드와 동작 파라미터화를 활용하면 변하는 요구사항에 쉽게 대응 가능하다.
- 조립 가능
  - 특정 기능들에 대해 조합하여 파이프라인을 만들 수 있어 유연성이 좋아진다.
- 병렬화
  - 손쉽게 병렬처리가 가능해져 성능이 좋아진다.
  
<br/>

## 스트림과 컬렉션

자바 기존 컬렉션과 새로운 스트림 모두 연속된 요소 형식의 값을 저장하는 자료구조의 인터페이스를 제공한다.

하지만 차이점들은 아래와 같다.

**데이터를 계산하는 시기**
 - 컬렉션은 현재 자료구조가 포함하는 **모든** 값을 메모리에 저장하는 자료구조다.
    - 즉, 컬렉션의 모든 요소는 컬렉션에 추가하기 전에 계산되어야 한다.
 - 스트림은 요청할 때만 요소를 계산하는 고정된 자료구조다.
    - 스트림에 요소를 추가하거나 제거할 수 없다.
    - 즉, 사용자가 데이터를 요청할 때만 값을 계산한다.
  
<br/>

**반복 탐색**
 
 - 컬렉션은 미리 계산되어진 값을 메모리에 갖고 있기 때문에 여러번 탐색이 가능하다
 - 반면 스트림은 반복자처럼 한 번만 탐색할 수 있다. 즉, 탐색된 스트림의 요소는 소비된다.
 
<br/>

**데이터 반복 처리 방법**

- 컬렉션을 사용하려면 사용자가 직접 요소를 반복해야한다. 이를 **외부 반복**이라 한다.
- 스트림은 반복을 알아서 처리하고 결과 스트림값을 어딘가 저장해주는 **내부 반복**을 사용한다.

<br/>

## 스트림 연산

```java
List<String> names = menu.stream()
                                .filter(dish -> dish.getCalories() > 300)
                                .map(Dish::getName)
                                .limit(3)
                                .collect(toList());
``` 

위는 두 그룹으로 구분할 수 있다.

- filter, map, limit은 서로 연결되어 파이프라인을 형성한다
- collect로 파이프라인을 실행한 다음 닫는다

연결할 수 있는 스트림 연산을 **중간 연산**이라 하며 스트림을 닫는 연산을 **최종 연산**이라 한다.

<br/>

### 중간 연산

중간 연산의 특징은 단말 연산을 스트림 파이프라인에 실행하기 전까지는 아무 연산도 수행하지 않는 즉, lazy하다는 것이다.

합쳐진 중간 연산을 최종 연산으로 한 번에 처리한다.

<br/>

### 최종 연산

최종 연산은 스트림 파이프라인에서 결과를 도출한다.

<br/>

### 종류

**중간 연산**

|연산|형식|반환 형식|연산의 인수|함수 디스크립터|
|---|---|---|---|---|
|filter|중간 연산|Stream\<T>|Predicate\<T>|T -> boolean|
|map|중간 연산|Stream\<T>|Function\<T>|T -> R|
|limit|중간 연산|Stream\<T>|||
|sorted|중간 연산|Stream\<T>|Comparator\<T>|(T, T) -> int|
|distinct|중간 연산|Stream\<T>|||

<br/>

**최종 연산**

|연산|형식|반환 형식|목적|
|---|---|---|---|
|forEach|최종 연산|void|스트림의 각 요소를 소비하면서 람다를 적용한다|
|count|최종 연산|long|스트림의 요소 개수를 반환한다|
|collect|최종 연산||스트림을 소비해서 리스트, 맵, 정수 형식의 컬렉션을 단든다.|


<br/>

## 활용

### 필터링

- filter는 Predicate를 인수로 받아 일치하는 모든 요소를 포함하는 스트림을 반환한다.

- 또한 고유 요소로 이루어진 스트림을 반환하는 distinct 메서드도 지원한다.
  - 고유 여부는 스트림에서 만든 객체의 hashCode, equals로 결정된다.

<br/>

### 스트림 슬라이싱

#### Predicate를 이용한 슬라이싱

자바 9에서 takeWhile, dropWhile 두 가지 새로운 메서드를 지원한다.

##### TAKEWHILE

```java
Stream.of(1,2,3,4,5,6,7,8,9)
                .filter(n -> n%2 == 0)
                .forEach(System.out::println);

        Stream.of(2,4,3,4,5,6,7,8,9)
                .takeWhile(n -> n%2 == 0)
                .forEach(System.out::println);
```

```
2
4
6
8

2
4
```

특정 조건의 데이터를 추출할 때, filter를 사용하게 되면 전체를 다 순회하게 된다. 

정렬이 된 배열이라는 기준 하에, 모든 데이터를 탐색하는 것은 상항한 성능 이슈가 발생할 수 있다.

takeWhile를 이용하면 무한 스트림을 포함한 모든 스트림에 Predicate를 적용하여 스트림을 슬라이스 할 수 있다.


##### DROPWHILE

dropWhile은 정반대의 작업을 수행한다. Predicate가 처음으로 거짓이 되는 지점까지의 발견된 요소를 버린다.

#### 스트림 축소

limit() 사용하여 주어진 값 이하 크리를 갖는 새로은 스트림을 가질 수 있다.



#### 요소 건너뛰기

skip() 사용하여 처음 n개 요소를 제외한 스트림을 얻을 수 있다.


<br/>

### 매핑

#### 각 요소에 함수 적용하기

스트림은 함수를 인수로 받는 map을 사용하여 함수를 적용한 결과가 새로운 요소로 매핑되게 할 수 있다.

```java
List<String> names = menu.stream()
                         .map(Dish::getName)
                         .collect(toList());
```

위와 같이 Dish라는 객체의 스트림에서 Name을 추출하여 String 컬렉션으로 변환하는 용도로 사용 가능하다.

#### 스트림 평면화

flatMap을 사용하여 스트림에 대해 평면화를 사용할 수 있다.

예를 들어 ["Hello","World"] 를 ["H", "e", "l", "o", "W", "r", "d"] 로 반환하고 싶다고 가정하자.

1. map을 적용할 경우

![image](https://user-images.githubusercontent.com/61372486/128373219-66ebe0a0-0e45-441f-bad0-509293012bbf.png)

- Hello가 split("")에 의해 ["H", "e", "l", "l", "o"] 로 분리되고, World가 split("")에 의해 ["W", "o", "r", "l", "d"]로 분리된다.
- map에 의해 Stream의 소스가 ["H", "e", "l", "l", "o"] 와 ["W", "o", "r", "l", "d"] 로 변환된다.
- distinct()에 의해 중복된 소스가 제거된다. (해당 사항 없음)
- 2개의  ["H", "e", "l", "l", "o"] 와 ["W", "o", "r", "l", "d"]가 collect(toList())에 의해 수집된다.

2. flatMap을 적용할 경우

![image](https://user-images.githubusercontent.com/61372486/128373357-1e174510-6719-4fa2-a575-36bc448cab01.png)


- Hello가 split("")에 의해 ["H", "e", "l", "l", "o"] 로 분리되고, World가 split("")에 의해 ["W", "o", "r", "l", "d"]로 분리된다.
- Arrays.stream(T[] array)를 사용해 ["H", "e", "l", "l", "o"] 와 ["W", "o", "r", "l", "d"]를 각각 Stream<String>으로 만든다.
- flatMap()을 사용해 여러 개의 Stream<String>을 1개의 Stream<String>으로 평평하게 합치고, Stream의 소스는 ["H", "e", "l", "l", "o", W", "o", "r", "l", "d"] 가 된다.
- distinct()에 의해 중복된 소스(l, o)가 제거된다.
- 중복이 제거된 ["H", "e", "l", "o", "W", "r", "d"]가 collect(toList())에 의해 수집된다.

로 평면화 사용이 가능하다.

<br/>

### 검색과 매칭

특정 속성이 데이터 집합에 있는지 여부를 검색하기 위해 스트림 api는 allMatch, anyMatch, noneMatch, findFirst, findAny 등을 지원한다.

#### anyMatch

Predicate가 주어진 스트림에서 적어도 한 요소가 일치하는지 확인할 때 anyMatch를 사용한다.

return값은 boolean으로 반환하고 최종 연산이 속한다.

#### allMatch

스트림의 모든 요소가 주어진 Predicate와 일치하는지 검사한다.

return값은 boolean으로 반환하고 최종 연산이 속한다.

#### noneMatch

주어진 Predicate와 일치하는 요소가 없는지 확인한다.

return값은 boolean으로 반환하고 최종 연산이 속한다.
 
위의 세 메서드는 스트림 쇼트서킷 기법, 즉 자바의 &&, || 과 같은 연산을 활용한다.

* 쇼트서킷 : 표현식에서 하나라도 거짓이라는 결과가 나오면 나머지 표현식의 결과와 상과없이 전체 결과도 거짓이 되는 것

#### findAny

현재 스트림에서 임의의 요소를 반환한다. 

#### findFirst

처음의 요소를 Optional형태로 반환한다.

논리적인 데이터 순서가 정해져있는 스트림에서 처음 요소를 찾을 때 사용한다.

<br/>

### 리듀싱

모든 스트림 요소를 처리해서 값으로 도출하는 질의를 리듀싱 연산이라고 한다.

함수형 프로그래밍 언어 용어로는 이 과정이 마치 종이를 작은 조각이 될 때까지 반복해서 접는 것 같다해서 폴드라고 부른다.

reduce는 두 개의 인수를 갖는다.

- 초깃값 0
- 두 요소를 조합해서 새로운 값을 만드는 BinaryOperator<T>

#### 요소의 합

```java
int sum = numbers.stream().reduce(0, (a, b) -> a + b);
```

#### 초깃값 없음

초깃값을 전달하지 않는다면 Optional 객체를 반환한다.

```java
Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
```


### 연산 정리


 |연산|형식|반환 형식|사용된 함수형 인터페이스 형식|함수 디스크립터|
 |---|---|---|---|---|
 |filter|중간 연산|Stream\<T>|Predicate\<T>|T -> boolean|
 |distinct|중간 연산|Stream\<T>|||
 |takeWhile|중간 연산|Stream\<T>|Predicate\<T>|T -> boolean|
 |dropWhile|중간 연산|Stream\<T>|Predicate\<T>|T -> boolean|
 |skip|중간 연산|Stream\<T>|long||
 |limit|중간 연산|Stream\<T>|||
 |map|중간 연산|Stream\<T>|Function\<T>|T -> R|
 |flatMap|중간 연산|Stream\<R>|Function<T, Stream\<R>>|T -> Stream<R>|
 |sorted|중간 연산|Stream\<T>|Comparator\<T>|(T, T) -> int|
 |anyMatch|최종 연산|boolean|Predicate\<T>|T -> boolean|
 |noneMatch|최종 연산|boolean|Predicate\<T>|T -> boolean|
 |allMatch|최종 연산|boolean|Predicate\<T>|T -> boolean|
 |findAny|최종 연산|Optional\<T>|||
 |findFirst|최종 연산|Optional\<T>|||
 |forEach|최종 연산|void|Consumer\<T>|T -> void|
 |reduce|최종 연산|Optional\<T>|BinaryOperator\<T>|(T, T) -> T| 
 |count|최종 연산|long|| 


<br/>

## 기본형 특화 스트림

자바8에서는 박싱 비용을 줄이기 위한 기본형 특화 스트림을 제공한다.

특화 스트림은 오직 박싱 과정에서 일어나는 효율성과 관련있으며 스트림에 추가 기능을 제공하지 않는다.

### 숫자 스트림으로 매핑

```mapToInt```, ```mapToDouble```, ```mapToLong```가 가장 많이 사용된다. 

이들은 특화된 스트림을 반환한다.

```java
int calories = menu.stream()
                   .mapToInt(Dish::getCalories) // IntStream 반환
                   .sum();
```

### 객체 스트림으로 복원

IntStream의 amp 연산은 int를 인수로 받아 int를 반환하는 람다를 인수로 받는다.

따라서 정수가 아닌 다른 객체로 반환하고 싶을 때, ```boxed``` 메서드를 사용하여 일반 스트림으로 변환할 수 있다.

```java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
```

### 기본값 Optional

stream의 값이 없으면 0이 도출된다. 

하지만 최대값, 최소값을 계산할 때, 결과가 0인지 stream의 요소가 없어 나온 0인지 구별해야할 때 Optional을 사용한다.

또한 ```OptionalInt```, ```OptionalLong```, ```OptionalDouble``` 세가지의 기본형 특화 스트림 버전도 제공한다.

```java
OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
int max = maxCalories.orElse(1); // 값이 없을 때 기본 값을 설정
```

### 숫자 범위

자바 8의 ```IntStream```, ```LongStream```에서는 ```range```, ```rangeClosed``` 두가지 메서드를 제공한다.

첫번째 인수로 시작값, 두번째 인수로 종료값을 갖는다.

 ```range```는 시작값과 종료값이 결과에 포함되지 않고, ```rangeClosed```는 포함된다는 점이 다르다.
 
 ```java
// 1부터 100 사이 짝수 수 구하기
long evenNumCnt = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0)
                .count();
```

<br/>

## 스트림 생성

### 값으로 스트림 생성

정적 메서드 ```Stream.of```를 사용해 스트림을 생성할 수 있다.

```java
Stream<String> stream = Stream.of("TEST1", "TEST2", "TEST3");
```

### null이 될 수 있는 객체로 스트림 생성

자바 9에서는 null이 될 수 있는 객체를 스트림으로 만들 수 있는 새로운 메서드가 추가됐다.

```java
// 기존에는 null 체크를 해줬어야 했다.
String homeValue = System.getProperty("home");
Stream<String> homeValueStream =
homeValue == null ? Stream.empty() : Stream.of(homeValue);

// 좀 더 간한하게 구현가해졌다.
Stream<String> homeValueStream =
Stream.ofNullable(System.getProperty("home"));
```
 
### 배열로 스트림 생성

```Arrays.stream```으로 스트림을 생성할 수 있다.

```java
int[] numbers = {2, 3, 5 ,7};
int sum = Arrays.stream(numbers).sum();
```

### 파일로 스트림 만들기

자바의 NIO API도 스트림 API를 활용할 수 있다.

java.nio.file.Files의 많은 정적 메서드가 스트림을 반환한다.

예를 들어 Files.lines는 주어진 파일의 행 스트림을 문자열로 반환한다.

```java
//파일에서 고유한 단어 수를 찾는 프로그램
long uniqueWords = 0;
        try(Stream<String> lines =
                    Files.lines(Paths.get("data,txt"))){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))) // 고유 단어 수 계산
                    .distinct() // 중복 제거
                    .count(); // 단어 스트림 생성
        } catch (IOException e){
            e.printStackTrace();
        }
```

Stream 인터페이스는 AutoCloseable 인터페이스를 구현하고 있기 때문에 try 블록 내의 자원은 자동으로 관리된다.

### 함수로 무한 스트림 생성

스트림 api는 함수에서 스트림을 만들 수 있는 정적 메서드 ```Stream.iterate```, ```Stream.generate```를 제공한다.

이를 이용해 무한 스트림, 즉 고정된 컬렉션에서 고정된 크기의 스트림이 아닌 고정되지 않은 스트림을 만들 수 있다.

#### iterate

```java
Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
```

기본적으로 iterate는 기존 결과에 의존해서 순차적 연산을 수행한다.

iterate는 요청할 때마다 값을 생산할 수 있으며 끝이 없으므로 무한 스트림을 만든다.

이를 **언바운드 스트림**이라 표현한다.

자바 9에서는 Predicate를 지원한다.

```java
Stream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);
```

작업의 수행을 쉽게 제한할 수 있다.

#### generate

generate는 생성된 각 값을 연속적으로 계산하지 않는다.

Supplier\<T>를 인수로 받아 새로운 값을 생성한다.

```java
Stream.generate(Math::random)
                .forEach(System.out::println);
```

<br/>

## Collector

Collector 인터페이스 구현은 스트림의 요소를 어떤 식으로 도출할지 지정한다.

Collectors에서 제공하는 메서드의 기능은 크게 세 가지로 구분할 수 있다.

- 스트림 요소를 하나의 값으로 redue하고 요약
- 요소 그룹화
- 요소 분할

### reducing과 요약

#### 최댓값과 최솟값 검색

```Collectors.maxBy```, ```Collectors.minBy``` 를 사용해 계산할 수 있다.

```java
// 메뉴에서 칼로리가 가장 높은 음식 찾기
Comparator<Dish> dishCompator = 
                Comparator.comparingInt(Dish::getCalories);
        
Optional<Dish> dish = menu.stream()
                .collect(maxBy(dishCompator));
```

#### 요약 연산

Collectors에서는 ```Collectors.summingInt``` 메서드를 제공한다.

이는 객체를 int로 매핑하는 함수를 인수로 받는다. 
 
```java
// 메뉴의; 칼로리 총합 계산
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
```

이 외에도 ```Collectors.averagingInt```, ```Collectors.averagingLong```, ```Collectors.averagingDouble``` 등을 제공한다.

##### 다중 연산

합계나 평군 등 두 개 이상의 연한을 한 번에 수행할 때에는 ```summarizingInt```를 사용할 수 있다.

```java
IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
System.out.println(menuStatistics);

>>>IntSummaryStatistics{count=5, sum=762, min=2, average=152.400000, max=600}
```

이 역시 long, double을 지원하는 메서드도 존재한다.


#### 문자열 연결

```joining``` 메서드를 이용하면 스트림의 각 객체에 ```toString```을 호출하여 연결 가능하다.

```java
String shortMenu = menu.stream().map(Dish::getName).collect(joining(", ")); // 안의 param은 구분자로 들어가게 된다.  
System.out.println(shortMenu);
```

내부적으로 StringBuilder를 이용하여 문자열을 연결한다.

```toString```을 내부적으로 구현하고 있다면 map은 생략가능하다.

<br/>

### 그룹화

```Collectors.groupingBy```를 통해 쉽게 그룹화가 가능하다.

```java
// 타입에 맞춰 그룹화
Map<Dish.Type, List<Dish>> typeListMap = menu.stream().collect(groupingBy(Dish::getType));
```

함수를 기준으로 스트림이 그룹화되므로 이를 분류 함수라 한다.

#### 그룹화된 요소 조작

```java
Map<Dish.Type, List<Dish>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        filtering(dish -> dish.getCalories() > 500, toList())));
```

만약 ```filter```를 사용하게 된다면 존재하지 않는 Type에 대해서는 결과 Map에서 해당 키 자체가 사라진다.

```filtering```은 ```Collectors``` 클래스의 또 다른 정적 팩토리 메서드로 Predicate를 인수로 받는다.

조건만이 아닌 ```mapping```을 사용하여 요소를 변환하는 작업도 가능하다.

```java
// 메뉴 이름으로 그룹화
Map<Dish.Type, List<String>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
```

```flatMapping```을 사용하면 간단한 태그 추출이 가능하다.

```java
// Type 기준으로 메뉴 이름을 dishTags의 값들 기준으로 Set을 만든다.
Map<String, List<String>> dishTags = new HashMap<>();
dishTags.put("pork", asList("greasy", "salty"));
dishTags.put("beef", asList("salty", "roasted"));
...
        
Map<Dish.Type, Set<String>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
```


#### 다수준 그룹화

항목을 다수준으로 그룹화가 가능하다.

바깥쪽 ```groupingBy```의 메서드에 스트림의 항목을 분류할 두 번째 기준을 정의하는 내부```groupingBy```를 통해 다수준으로 그룹화를 정의할 수 있다.  

```java
Map<Dish.Type, Map<CaloricLevel, List<Dish>>> typeListMap = menu.stream()
                .collect(groupingBy(Dish::getType, // 첫 분류 함수
                        groupingBy(dish -> {       // 두번째 분류 함수
                            if (dish.getCalories() <= 400)
                                return CaloricLevel.DIET;
                            else if (dish.getCalories() <=  700)
                                return CaloricLevel.NORMAL;
                            else 
                                return CaloricLevel.FAT;
                        })
                ));
```

#### 서브그룹으로 데이터 수집

groupingBy로 넘겨주는 컬렉터의 형식은 제한이 없다.

분류 함수 한 개의 인수를 갖는 ```groupingBy(f)```는 ```groupingBy(f, toList())```의 축약형이다.

다음과 같은 형식도 가능하다.

```java
Map<Dish.Type, Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType, counting();
```  

#### 결과를 다른 형식에 적용하기

```Collectors.collectingAndThen```으로 결과를 다른 형식으로 활용 가능하다.

collectingAndThen는 적용할 컬렉터와 반환 함수를 인수로 받아 다른 컬렉터를 반환한다.

```java
// 서브 그룹에서 가장 칼로리가 높은 요리 찾기
Map<Dish.Type, Dish> mostCaloricByType = 
                menu.stream().collect(groupingBy(Dish::getType, // 분류 함수
                collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)), // 감싸인 컬렉터
                        Optional::get)));
```


<br/>

## 분할

분할은 분할 함수라 불리는 Predicate를 분류 함수로 사용하는 특후나 그룹화 기능이다.

맵의 키 형싣은 Boolean이다. 즉, 결과적으로 맵은 최대 두 개의 그룹(true or false)으로 분류된다.

```java
Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
``` 
두번째 인수를 통해 다중 그룹화도 가능하다.

```java
Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = 
                menu.stream().collect(
                partitioningBy(Dish::isVegetarian, // 분할 함수
                        groupingBy(Dish::getType))); // 두번째 컬렉터
```

<br/><br/><br/><br/>

---
참조:
- [Stream.takeWhile 과 Stream.filter 차이](https://vesselsdiary.tistory.com/156)
- [[Java] Stream API의 활용 및 사용법 - 고급 (4/5)](https://mangkyu.tistory.com/115)
- 모던 자바 인 액션


