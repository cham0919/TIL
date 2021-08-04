

# 1. Enum


<br/>

Enum을 '열거형' 또는 Enumeration 또는 상수집합 이라고도 부른다.

enum(열거형)은 서로 관련된 상수를 편리하게 선언하기 위한 것으로 상수를 여러 개 정의할 때 사용한다. enum은 여러 상수를 정의한 후, 정의된 것 이외의 값은 허용하지 않는다.


```java
enum 열거형이름 {상수명1 , 상수명2, 상수명3, ...}
```

  
<br/><br/>


## 1.2 특징

<br/>

1. enum에 정의된 상수들은 해당 enum type의 객체이다.

  C 등의 다른 언어에도 열거형이 존재한다. 하지만 다른 언어들과 달리 Java의 enum은 단순한 정수 값이 아닌 해당 enum type의 객체이다.
  
<br/>
  
2.  생성자와 메서드를 추가할 수 있다.
 
```java
enum Fruit {
    APPLE, PEACH, BANANA;    // 열거할 상수의 이름 선언, 마지막에 ; 을 꼭 붙여야한다.

    Fruit() {
        System.out.println("생성자 호출 " + this.name());
    }
}

public class EnumDemo {

    public static void main(String[] args) {
        Fruit apple = Fruit.APPLE;
    // Fruit grape = new Fruit();
    // 에러 발생. 열거형의 생성자의 접근제어자는 항상 private이다.
    }
}
```
```java
>>>생성자 호출 APPLE
>>>생성자 호출 PEACH
>>>생성자 호출 BANANA
```

생성자를 정의할 수 있는데, enum의 생성자의 접근제어자는 private이기 때문에 외부에서 상수를 추가할 수 없다. 열거형의 멤버 중 하나를 호출하면, 열거된 모든 상수의 객체가 생성된다. 

상수 하나당 각각의 인스턴스가 만들어지며 모두 public static final이다.

추상 메서드를 선언해서 각 상수 별로 다르게 동작하는 코드 구현도 가능하다.

```java
enum Transport {
    BUS(1200) {
        @Override
        double fare(int distance) {
            return distance * BASIC_FARE * 1.5;
        }
    },

    TAXI(3900) {
        @Override
        double fare(int distance) {
            return distance * BASIC_FARE * 2.0;
        }
    },

    SUBWAY(1200) {
        @Override
        double fare(int distance) {
            return distance * BASIC_FARE * 0.5;
        }
    };

    protected final int BASIC_FARE;  // 기본요금, protected로 선언해야 상수에서 접근 가능

    Transport(int basicFare) {
        BASIC_FARE = basicFare;
    }

    abstract double fare(int distance);   // 거리에 따른 요금 계산
}
```

<br/>

3. 상수 간의 비교가 가능하다.

enum 상수 간의 비교에는 ==를 사용할 수 있다. 단 >, < 같은 비교연산자는 사용할 수 없고 compareTo()를 사용할 수 있다.



<br/>


## 1.3 사용 시기

<br/>

>필요한 원소를 컴파일 타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자.  태양계 행성, 한 주의 요일, 체스 말처럼 본질적으로 열거 타입인 타입은 당연히 포함된다. 그리고 메뉴 아이템, 연산 코드, 명령줄 플래그 등 허용하는 값 모두를 컴파일타임에 이미 알고 있을 때도 쓸 수 있다. 열거 타입에 정의된 상수 개수가 영원히 고정 불변일 필요는 없다. 열거 타입은 나중에 상수가 추가돼도 바이너리 수준에서 호환되도록 설계되었다
- 이펙티브 자바 3/E. Item 34. 219쪽

<br/><br/>



## 1.4 Method

<br/>

- values()
  - 해당 enum 타입에 정의된 상수 배열을 반환한다.
- getDeclaringClass()
  - 열거형의 객체를 반환한다.
- name()
  - 열거형 상수의 이름을 문자열로 반환한다.
- ordinal()
  - 열거형 상수가 정의된 순서를 반환한다.(0부터 시작)
- valueOf(enumType, name)
  - 지정된 열거형에서 name과 일치하는 열거형 상수를 반환한다.
  

<br/>

values()를 제외한 나머지는 java.lang.Enum 클래스에 정의된 메서드이다.

  
<br/><br/>
  
  
## 1.5 Enum 상속

<br/>

java.lang 에 포함된 Enum 클래스는 모든 자바 열거형의 조상이다. 모든 열거형은 Enum 클래스를 상속받기 때문에 enum type은 별도의 상속을 받을 수 없다.



<br/><br/>

# 2. EnumSet

<br/>

EnumSet 열거형을 위해 고안된 특별한 Set 인터페이스 구현체이다.HashSet과 비교했을 때, 성능 상의 이점이 많기 때문에 열거형 데이터를 위한 Set이 필요한 경우 EnumSet을 사용하는 것이 좋다.

<br/>

## 2.1 상속 구조

![](https://images.velog.io/images/cham/post/5a80d373-9d38-4f75-b832-6bc8834ca1ad/image.png)


<br/>

## 2.2 특징

- EnumSet은 AbstractSet 클래스를 상속하고 Set 인터페이스를 구현한다.
- 오직 열거형 상수만을 값으로 가질 수 있다. 또한 모든 값은 같은 enum type이어야 한다.
- null value를 추가하는 것을 허용하지 않는다. NullPointerException을 던지는 것도 허용하지 않는다.
- ordinal 값의 순서대로 요소가 저장된다.
- tread-safe하지 않다. 동기식으로 사용하려면 Collections.synchronizedMap을 사용하거나, 외부에서 동기화를 구현해야한다.
- 모든 메서드는 arithmetic bitwise operation을 사용하기 때문에 모든 기본 연산의 시간 복잡도가 O(1)이다.

<br/>

## 2.3 사용법

```java
enum Color {
    RED, YELLOW, GREEN, BLUE, BLACK, WHITE
    
}

public class EnumDemo {

    public static void main(String[] args) {
        EnumSet<Color> set1, set2, set3, set4, set5;

        set1 = EnumSet.allOf(Color.class);
        set2 = EnumSet.of(Color.RED, Color.GREEN, Color.BLUE);
        set3 = EnumSet.complementOf(set2);
        set4 = EnumSet.range(Color.YELLOW, Color.BLACK);

        set5 = EnumSet.noneOf(Color.class);
        set5.add(Color.BLACK);
        set5.add(Color.BLUE);
        set5.remove(Color.BLUE);

        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set3 = " + set3);
        System.out.println("set4 = " + set4);
        System.out.println("set5 = " + set5);
        System.out.println(set5.contains(Color.BLACK));
    }
}
```

```java
set1 = [RED, YELLOW, GREEN, BLUE, BLACK, WHITE]
set2 = [RED, GREEN, BLUE]
set3 = [YELLOW, BLACK, WHITE]
set4 = [YELLOW, GREEN, BLUE, BLACK]
set5 = [BLACK]
true
```

<br/><br/>

# 2. EnumMap

<br/>


enum type 키와 함께 사용하기 위한 특수한 Map 구현체다.
enum map의 모든 키는 map이 생성될 때 명시적으로 또는 암시적으로 지정된 단일 열거형 타입에서 가져와야한다다.

enum map은 내부적으로 배열로 표시되며 매우 간결하고 효율적입니다.

Null 요소는 허용되지 않는다.

여러 쓰레드가 동시에 enum map에 접근하고 쓰레드 중 하나 이상이 map을 수정하는 경우 외부에서 동기화해야한다.

일반적으로 enum map을 자연스럽게 캡슐화하는 일부 객체에서 동기화하여 수행된다.

이러한 객체가 없으면 Collections.synchronizedMap(Map<K, V>) 메서드를 사용하여 맵을 "래핑" 해야한다.

```Map<EnumKey, V> m = Collections.synchronizedMap<new EnumMap<EnumKey, V>(...));```


<br/>

## 2.1 생성자

- EnumMap(Class<K> keyType)
  
  - 매개변수로 받은 keyType을 사용하는 EnumMap을 만든다.
  
- EnumMap(EnumMap<K, ? extends V> m)
  
  - 매개변수로 받은 요소와 동일한 매핑을 가지는 EnumMap을 만든다.
  
- EnumMap(Map<K, ?extends V> m)
  
  - 매개변수로 받은 Map 요소에서 초기화 된 enum map을 만든다.
  - 지정된 Map이 EnumMap의 인스턴스인 경우 생성자는 EnumMap(EnumMap<K, extends V> m)과 동일하게 작동한다.
  - 그렇지 않으면 지정된 맵에 하나 이상의 매핑이 포함되어 있어야한다. (새 enum map의 키 타입을 확인하기 위해)
  - 매개변수가 EnumMap이 아니고 매핑이 하나도 없다면 IllegalArgumentException을 발생시킵니다.

  
<br/>

## 2.2 Method
  
- size()
  - 이 EnumMap의 키-값 매핑 사이즈를 반환한다. 

- containsValue(Object value)
  - EnumMap이 value 값을 매핑한 키가 존재한다면(하나 이상이라면) true를 반환한다.

- containsKey(Object key)
  - EnumMap에 매개변수의 key가 존재한다면 true를 반환한다.
  
- get(Object key)
  - key에 매핑되는 값을 반환하거나 Map에 key가 없을 경우 null을 반환한다.
  - 즉 이 Map에 키 k에서 값 v로의 매핑이 포함되어 있으면(key == k), 이 메서드는 v를 반환합니다. 그렇지 않으면 null을 반환한다.
  - 반환값이 null이라고해서 반드시 Map에 key에 대한 매핑이 포함되어 있지 않음을 나타내는 것은 아닙니다. 명시적으로 key를 null로 매핑할 수도 있다.
  - containsKey 연산을 사용하여 이 두 경우를 구분할 수 있다.

- put(K key, V value)
  - key와 value를 매핑하여 EnumMap에 넣는다. 만약 이미 동일한 key값을 가지고있다면 해당 value 값으로 덮어쓴다.
  
- remove(Object key)
  - key가 존재하는 경우 Map에서 key를 삭제하고 key에 매핑되어 있던 value를 반환한다.

- putAll(Map<? extends K, ? extends V> m)
  - 매개변수 m 에 있는 모든 요소들을 이 Map에 삽입합니다. 이미 존재하는 key라면 새로운 값으로 대체된다.

- clear()
  - 이 Map에 모든 매핑을 삭제한다.

- keySet()
  - 이 Map에 포함된 키의 Set 뷰를 반환합니다. Set의 interator는 자연순서(열거형 상수가 선언된 순서)로 키를 반환한다.

- values()
  - 이 Map에 포함된 값의 Collection 뷰를 반환한다.
  - Collection의 iterator는 해당 key가 map에 나타나는 순서대로 값을 반환한다. 


- entrySet()
  - 이 Map에 포함된 매핑의 Set 뷰를 반환한다.
  
  
<br/><br/><br/><br/><br/><br/><br/>

참조

---


 - [11주차. Enum](https://parkadd.tistory.com/50)
 - [[Java Study 11주차] Enum](https://wisdom-and-record.tistory.com/52)
 - [Enum](https://www.notion.so/Enum-6ffa87530c424d8ab7a1b585bfb26fa2)
 
  