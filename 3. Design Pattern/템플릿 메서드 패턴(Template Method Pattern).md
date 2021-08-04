# 템플릿 메소드 패턴


- 생성(Creational) 패턴
 


- 어떤 작업을 처리하는 일부분을 서브 클래스로 캡슐화해 전체 일을 수행하는 구조는 바꾸지 않으면서 특정 단계에서 수행하는 내역을 바꾸는 패턴


-  동일한 기능을 상위 클래스에서 정의하면서 확장/변화가 필요한 부분만 서브 클래스에서 구현할 수 있도록 한다.




<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/8746d295-bbdf-440b-93d6-e1bace4dc17c/image.png)



- 하나의 추상 클래스를 상속받아 각자 필요한 Method만 구현한 상태

<br/><br/>

## 구현


**Direction.class**

```java
public abstract class Direction {

    // 출발, 도착 세팅
    final void setStartAndDest(String start, String end) {
        System.out.println("출발지 : " + start);
        System.out.println("도착지 : " + end);
    }

    // 경유지 세팅
    final void setPassThroughs(String... points) {
        for (String point : points) {
            System.out.println("* 경유지 : " + point);
        }
    }

    // 길 찾기
    abstract void getDirection();

}
```


- 길찾는 시스템을 구현한다고 했을 때, 출발지, 목적지, 경유지를 입력받는 기능은 어떤 시스템이든 공통적으로 필요하다
- 공통적으로 필요한 기능은 추상 클래스에 구현을 하되, 각 기능에 맞춰 구현해야하는 ```getDirection()```같은 기능은 abstract로 둬 각자 상속받아 구현할 수 있게 한다.

<br/><br/>



**FastDirection.class**

```java
public class FastDirection extends Direction {

    @Override
    void getDirection() {
        System.out.println("빠른 길을 찾습니다.");
    }

}
```


- 빠른 길 찾기를 구현한다

<br/><br/>



**FreeDirection.class**

```java
public class FreeDirection extends Direction {

    @Override
    void getDirection() {
        System.out.println("무료 길을 찾습니다.");
    }

}
```


- 무료 길 찾기를 구현한다

<br/><br/>



**CloseDirection.class**

```java
public class CloseDirection extends Direction {

    @Override
    void getDirection() {
        System.out.println("가까운 길을 찾습니다.");
    }

}
```


- 가까운 길 찾기를 구현한다




<br/><br/>




## 장단점


- 장점
  - 전체적으로는 동일하면서 부분적으로는 다른 구문으로 구성된 메서드의 코드 중복을 최소화할 수 있다
  - 자식 클래스의 역할을 줄여 핵심 로직의 관리가 용이하다
  - 확장과 수정에 유연하다

  


- 단점
  - 패턴을 남발하게 될 시, 추상 메소드가 많아지면서 클래스 관리가 복잡해진다.


<br/><br/><br/><br/>

---
참조:
- [[기술 면접 질문] 기술 면접 예상 질문 대비하기 - 디자인패턴(Design Pattern)편](https://gmlwjd9405.github.io/2017/10/01/basic-concepts-of-development-designpattern.html)
- [[Design Pattern] 템플릿 메소드 패턴(Template Method Pattern)에 대하여](https://coding-factory.tistory.com/712)
- [자바 템플릿 메소드 패턴 (Java Template Method Pattern)](https://gofnrk.tistory.com/62?category=763552)