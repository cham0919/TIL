# Collection

자바 9에서는 작은 컬렉션 객체를 쉽게 만드는 방법을 제공한다.

이외에도 8에서 추가된 메서드도 살펴본다.


<br/>

## Set


### 팩토리 

```Set.of```로 간단한 Set을 만들 수 있다.

```java
Set<String> friends = Set.of("TEST1", "TEST2", "TEST3");
```

- 중복된 요소를 제공해 생성하면 ```java.lang.IllegalArgumentException``` 예외가 발생한다.

### 메서드

- ```removeIf``` : Predicate를 만족하는 요소를 제한다. 
- ```replaceAll``` : 리스트에서 이용할 수 있다. UnaryOperator를 이용해 요소를 변경한다.

위의 메서드는 호출한 컬렉션 자체를 변경한다.

<br/>

## List

### 팩토리 


```List.of``` 메소드를 이용하여 간단하게 리스트를 만들 수 있다.

```java
List<String> friends = List.of("TEST1", "TEST2", "TEST3");
```

- 요소를 변경하면 ```java.lang.UnsupportedOperationException``` 예외가 발생한다.

- null 요소는 금지된다


### 메서드 

- ```removeIf``` : Predicate를 만족하는 요소를 제한다. 
- ```replaceAll``` : 리스트에서 이용할 수 있다. UnaryOperator를 이용해 요소를 변경한다.
- ```sort``` : List에서 제공하는 기능으로 리스트를 정렬한다.

위의 메서드는 호출한 컬렉션 자체를 변경한다.

<br/>

## Map

### 팩토리

#### Map.of


```java
Map<String, Integer> ageOfFriends
                = Map.of("A", 30, "B", 20, "C", 27);
```

요소가 적은 맵을 만들 때 유용하다.

#### Map.Entry<K, V>

```java
Map<String, Integer> ageOfFriends
                = Map.ofEntries(
                        entry("A", 30),
                        entry("B", 20),
                        entry("C", 27));
```

<br/>


### 메서드

자바 8에서는 Map에 몇 가지 메서드를 추가했다.

- ```forEach``` : BiConsumer를 인수로 받아 반복문을 수행한다.
- ```Entry.comparingByValue``` : 값을 기준으로 정렬한다.
-  ```Entry.comparingByKey``` : 키를 기준으로 정렬한다.
- ```getOrDefault``` : 키가 없을 시, 반환값을 default로 지정할 수 있다.
- ```computeIfAbsent``` : 제공된 키에 해당하는 값이 없으면, 키를 이용해 새 값을 계산하고 맵에 추가한다.
- ```computeIfPresent``` : 제공된 키가 존재하면 새 값을 계산하고 맵에 추가한다.
- ```coompute``` : 제공된 키로 새 값을 계산하고 맵에 저장한다
- ```remove(k, v)``` : 키에 특정 값이 연관되어 있을 때, 항목을 제거한다.
- ```replaceAll``` : BiFunction을 적용한 결과로 각 항목의 값을 교체한다.
- ```Replace``` : 키가 존재하면 맵의 값을 바꾼다. 키가 특정 값으로 매핑되었을 때만 값을 교체하는 오버로드 버전도 있다.
- ```putAll``` : 두 개의 map을 합친다. 중복된 키가 있다면 덮어씌워진다.
- ```merge``` : 두 개의 map을 합친다. 중복된 키에 대한 결정인 BiFunction을 인수로 받는다

<br/>

### HashMap 

자바 8에서는 HashMap 내부 구조가 일부 변경됐다.

기존에는 키로 생성한 해시코드로 접근할 수 있는 버킷에 저장했다. 하지만 이는 많은 키가 같은 해시코드를 반환하는 상황이 되면 O(n)의 시간이 걸리는 LinkedList로 버킷을 반환해야하므로 성능이 저하되었었다.

버킷이 너무 커질 경우 이를 O(long(n))의 시간이 소요되는 정렬된 트리를 이용해 동적으로 치환해 충돌이 일어나는 요소 반환 성능을 개선했다.

하지만 키가 String, Number 클래스같은 Comparable의 형태여야만 지원된다.

<br/>

## ConcurrentHashMap

```ConcurrentHashMap```은 내부 자료구조의 특정 부분만 잠궈 동시 추가, 갱신 작업을 허용한다. 

```ConcurrentHashMap```은 세 가지 새로운 연산을 지원한다.

- ```forEach``` : 각 키, 값에 주어진 액션을 실행
- ```reduce``` : 모든 키, 값을 제공된 리듀스 함수를 이용해 결과로 합친다
- ```search``` : null이 아닌 값을 반환할 때까지 각 키, 값에 함수를 적용한다.
- ```mappingCount``` : 맵의 매핑 개수를 반환한다. 기존 ```size```와 다르게 long Type을 반환하며 int 범위를 넘어서는 이후의 상황에 대처가 가능하다.
- ```keySet``` : ```ConcurrentHashMap```를 집합 뷰로 반환환다. 집합 뷰와 맵은 서로 영향을 받는다. ```newKeySet```이라는 메서드를 이용해 맵으로 유지되는 집합을 만들수도 있다.




<br/><br/><br/><br/><br/><br/><br/>


Reference

---

- 라울-게이브리얼 우르마, 『모던 자바 인 액션』, 우정은, 한빛미디어(2018)