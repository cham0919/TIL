# Optional

Optional은 null이 올 수 있는 값을 감싸는 Wrapper 클래스로 선택형값을 캡슐호하는 클래스다.

<br/>

## null 때문에 발생하는 문제

- 에러의 근원
  - ```NullPointerException```은 자바에서 가장 흔히 발생하는 에러다
- 코드를 어지럽힌다.
  - 중첩된 null 확인 코드를 추가해야 하므로 null 때문에 코드 가독성이 떨어진다.
- 아무 의미가 없다.
  - null은 아무 의미도 표현하지 않는다. 정적 형식 언어에서 값이 없음을 표현하는 방법으로는 적절하지 않다.
- 자바 철학에 위배된다.
  - 자바는 개발자로부터 모든 포인터를 숨겼다. null을 제외하고서.
- 형식 시스템에 구멍을 만든다. 
  - null은 무형식이며 정보를 포함하고 있지 않으므로 모든 참조 형식에 null을 할당할 수 있다. 이런 식으로 null이 할당 및 다른 부분으로 전차되었을 때, 애초에 null이 어떤 의미로 사용되었는지 알 수 없다.
  
  
<br/>

## Optional 객체 만들기

### 빈 Optional

정적 팩토리 메서드 ```Optional.empty```로 빈 Optional 객체를 얻을 수 있다.

```java
Optional<String> optStr = Optional.empty();
```

### null이 아닌 값으로 Optional 만들기

정적 팩토리 메서드 ```Optional.of```로 null이 아닌 값을 포함하는 Optional을 만들 수 있다.

```java
Optional<String> optStr = Optional.of("test");
```

안에 null을 참조하게 되면 그 즉시 ```NullPointerException```가 발생하게 된다.

### null값으로 Optional 만들기

정적 팩토리 메서드 ```Optional.ofNullable```로 null값을 저장할 수 있는 Optional을 만들 수 있다.

```java
Optional<String> optStr = Optional.ofNullable("test");
```

<br/>

## Optional 값 추출 및 변환

![image](https://user-images.githubusercontent.com/61372486/128610775-a00b2b1e-bb1d-406c-8a7b-197a03d99f1f.png)


값을 추출하고 변환하는 기능을 위해 Optional은 ```map```을 지원한다.

```java
Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
Optional<String> name = optInsurance.map(Insurance::getName);
```
Optional 값이 있다면 map 인수로 제공된 함수가 값을 변경하고 비어있으면 아무 일도 일어나지 않는다.



<br/>

## Optional 객체 연결

![image](https://user-images.githubusercontent.com/61372486/128610791-e0260438-481e-4744-a696-f7c5cf64dc96.png)

map을 통해 파이프라인을 형성한다면 문제가 발생한다.

```java
Optional<Person> optPerson = Optional.of(person);
Optional<String> name = 
        optPerson.map(Person::getCar)
                 .map(Car::getInsurance)
                 .map(Insurance::getName);
```

map은 Optional의 형식을 반환한다. 여러번의 map을 사용한다면 아래와 같이 중첩된 Optional 구조를 반환하게 된다.

![image](https://user-images.githubusercontent.com/61372486/128610864-54b53e45-b22b-4f80-a0fd-23b7b2b65d43.png)

따라서 다음과 같이 사용해야한다.


```java
Optional<Person> optPerson = Optional.of(person);
Optional<String> name = 
        optPerson.flapMap(Person::getCar)
                 .flapMap(Car::getInsurance)
                 .flapMap(Insurance::getName);
```

<br/>

## Optional 스트림 조작

자바 9에서는 Optional을 포함하는 스트림을 쉽게 처리할 수 있도록 Optional에 ```stream()```을 추가했다.

Optional 스트림을 값을 가진 스트림으로 변환할 때 유용하다.

```java
Stream<Optional<String>> stream = ...;
        Set<String> result = stream.filter(Optional::isPresent)
                                    .map(Optional::get)
                                    .collect(toSet());
```

<br/>

## Default 액션과 Optional 언랩

Optional 클래스는 인스턴스에 포함된 값을 읽는 다양한 방법을 제공한다.

- get()
  - 값이 있으면 반환하고 없으면 ```NoSuchElementException```을 발생시킨다.
  
- orElse(T other)
  - Optional이 값을 포함하지 않을 때 기본 값을 제공할 수 있다.
  
- orElseGet(Supplier\<? extends T> other)
  - Optional 값이 없을 때만 Supplier가 실행된다.
  - default 메서드를 만드는데 시간이 걸리거나 Optional이 비어있을 때만 기본값을 생성하고 싶을 때, 사용한다.

- orElseThrow(Supplier\<? extends X> exceptionSupplier)
  - Optional이 비어있을 때 예외를 발생시킨다.
  
- ifPresent(Consumer\<? super T> consumer)
  - 값이 존재할 때 인수로 넘겨준 동작을 실행할 수 있다.  
  
- ifPresentOrElse(Consumer\<? super T> action, Runnable emptyAction)
  - Optional이 비어있을 때 실행할 수 있는 Runnable을 인수로 받는다.
  
<br/>

## 필터 사용

특정 조건에 부합하는 값을 얻고 싶을 때 사용한다.

```java
Optional<Insurance> optInsurance = ...
optInsurance.filter(insurance -> "test".equals(insurance.getName()))
                .ifPresent(x -> System.out.println("ok"));
```

조건과 일치하면 값을 반환하고 아니면 빈 Optional 객체를 반환한다.

<br/>

## Optional 클래스의 메서드

|메서드|설명|
|------|---|
|empty|빈 Optional 인스턴스 반환|
|filter|값이 존재하며 조건과 일치하면 값을 포함하는 Optional을 반환, 값이 없거나 조건과 불일치시 빈 Optional 반환|
|flatMap|값이 존재하면 인수로 제공된 함수 결과 Optional을 반환, 값이 없으면 빈 Optional 반환|
|get|값이 존재하면 Optional이 감싸고 있는 값을 반환, 값이 없으면 ```NoSuchElementException``` 발생|
|ifPresent|값이 존재하면 지정된 Consumer 실행, 없으면 아무 일도 일어나지 않음|
|ifPresentOrElse|값이 존재하면 지정된 Consumer 실행, 없으면 아무 일도 일어나지 않음|
|isPresent|값이 존재하면 true 반환, 없으면 false 반환|
|map|값이 존재하면 제공된 매핑 함수 적용|
|of|값이 존재하면 값을 감싸는 Optional 반환, null이면 ```NullPointerException``` 발생|
|ofNullable|값이 존재하면 값을 감싸는 Optional 반환, 값이 null이면 빈 Optional 반환|
|or|값이 존재하면 같은 Optional 반환, 값이 없으면 Supplier에서 만든 Optional 반환|
|orElse|값이 존재하면 값을 반환, 값이 없으면 기본값을 반환|
|orElseGet|값이 존재하면 값을 반환, 값이 없으면 Supplier에서 제공하는 값 반환|
|orElseThrow|값이 존재하면 값을 반환, 값이 없으면 Supplier에서 생성한 예외 발생|
|stream|값이 존재하면 존재하는 값만 포함하는 스트림 반환, 없으면 빈 스트림 반환|




<br/><br/><br/><br/><br/><br/><br/>


Reference

---

- 라울-게이브리얼 우르마, 『모던 자바 인 액션』, 우정은, 한빛미디어(2018)
