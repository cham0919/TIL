# 데코레이터 패턴


- 구조(Structural) 패턴 


- 객체의 결합 을 통해 기능을 동적으로 유연하게 확장 할 수 있게 해주는 패턴


- 기본 기능에 추가할 수 있는 기능의 종류가 많은 경우에 각 추가 기능을 Decorator 클래스로 정의 한 후 필요한 Decorator 객체를 조합함으로써 추가 기능의 조합을 설계 하는 방식







<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/61c2a129-f571-4318-a3db-8519beaa5be8/image.png)


- Component
  - 기본 기능을 뜻하는 ConcreteComponent와 추가 기능을 뜻하는 Decorator의 공통 기능을 정의
  - 클라이언트는 Component를 통해 실제 객체를 사용함
- ConcreteComponent
  - 기본 기능을 구현하는 클래스
- Decorator
   - 많은 수가 존재하는 구체적인 Decorator의 공통 기능을 제공
 - ConcreteDecoratorA, ConcreteDecoratorB
   - Decorator의 하위 클래스로 기본 기능에 추가되는 개별적인 기능을 뜻함
   - ConcreteDecorator 클래스는 ConcreteComponent 객체에 대한 참조가 필요한데, 이는 Decorator 클래스에서 Component 클래스로의 ‘합성(composition) 관계’를 통해 표현됨


- 합성 관계
  - 생성자에서 필드에 대한 객체를 생성하는 경우
  - 전체 객체의 라이프타임과 부분 객체의 라이프 타임은 의존적이다.
  - 즉, 전체 객체가 없어지면 부분 객체도 없어진다.


<br/><br/>

## 구현



도로 표시 방법에 대해 구현해보자

<br/>


**Display.class**

```java
public abstract class Display {

    public abstract void draw();

}
```


- 도로 표시 구현에 관한 Method를 정의한다


<br/><br/>

**RoadDisplay.class**

```java
public class RoadDisplay extends Display{
    public void draw() { System.out.println("기본 도로 표시"); }
}
```


- 기본 도로 표시 방법에 대한 기능 클래스를 구현한다

<br/><br/>



**DisplayDecorator.class**

```java
public abstract class DisplayDecorator extends Display {
    private Display decoratedDisplay;

    public DisplayDecorator(Display decoratedDisplay) {
        this.decoratedDisplay = decoratedDisplay;
    }

    @Override
    public void draw() { decoratedDisplay.draw(); }
}

```


- 기본 도로 표시 기능이 아닌 추가 표시 기능이 필요할 때 클래스 조합에 사용할 Decorator 클래스를 정의한다


<br/><br/>


**LaneDecorator.class**

```java
public class LaneDecorator extends DisplayDecorator {

    public LaneDecorator(Display decoratedDisplay) { super(decoratedDisplay); }

    @Override
    public void draw() {
        super.draw(); // 설정된 기존 표시 기능을 수행
        drawLane(); // 추가적으로 차선을 표시
    }

    // 차선 표시 기능만 직접 제공
    private void drawLane() { System.out.println("\t차선 표시"); }
}


```

<br/>

**TrafficDecorator.class**

```java

public class TrafficDecorator extends DisplayDecorator {

    public TrafficDecorator(Display decoratedDisplay) { super(decoratedDisplay); }

    @Override
    public void draw() {
        super.draw(); // 설정된 기존 표시 기능을 수행
        drawTraffic(); // 추가적으로 교통량을 표시
    }

    // 교통량 표시 기능만 직접 제공
    private void drawTraffic() { System.out.println("\t교통량 표시"); }
}

```

- 클래스 합성 조합에 사용할 교통량과 차선 표시 클래스를 정의한다

<br/><br/>

**Main.class**

```java
    public static void main(String[] args) {
        Display roadWithLaneAndTraffic =
                new TrafficDecorator(
                        new LaneDecorator(
                                new RoadDisplay()));
        roadWithLaneAndTraffic.draw();
    }
```

<br/>


```java
>>> 기본 도로 표시
	차선 표시
	교통량 표시
```

- Main에서 클래스 조합을 이용하여 표시 순서를 정의할 수 있게 된다.
  - 가장 먼저 생성된 RoadDisplay 객체의 draw 메서드가 실행
  - 첫 번째 추가 기능인 TrafficDecorator 클래스의 drawTraffic 메서드가 실행
  - 두 번째 추가 기능인 LaneDecorator 클래스의 drawLane 메서드가 실행

<br/><br/>

![](https://images.velog.io/images/cham/post/099ec944-03b9-4d9a-9e12-9a389b261e77/image.png)


<br/>

Client 클래스는 동일한 Display 클래스만을 통해 일관성 있는 방식으로 도로 정보를 표시할 수 있다.




<br/><br/><br/>




## 장단점


- 장점
  - 추가 기능 조합별로 별도의 클래스를 구현하는 대신 각 추가 기능에 해당하는 클래스의 객체를 조합해 구현할 수 있게 된다.
  -  객체에 동적으로 기능 추가가 간단하게 가능하다.



- 단점
  - 클래스들이 계속 추가되어 클래스가 많아질 수 있다.
  - 객체의 정체를 알기 힘들고 복잡해질 수 있다.


<br/><br/><br/><br/>

---
참조:
- [[디자인 패턴 8편] 구조 패턴, 데코레이터(Decorator)](https://dailyheumsi.tistory.com/198)
- [[Design Pattern] 데코레이터 패턴이란](https://gmlwjd9405.github.io/2018/07/09/decorator-pattern.html)
