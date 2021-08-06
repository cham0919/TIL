# 스트래티지 패턴(Strategy Pattern)


- 행위(Behavioral) 패턴
 


-  행위를 클래스로 캡슐화해 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 패턴


- 같은 문제를 해결하는 여러 알고리즘이 클래스별로 캡슐화되어 있고 이들이 필요할 때 교체할 수 있도록 함으로써 동일한 문제를 다른 알고리즘으로 해결할 수 있게 하는 디자인 패턴








<br/><br/>

## 구조


![](https://images.velog.io/images/cham/post/58dda679-740a-4cf1-9213-82a9d359a9df/image.png)

- Strategy
  - 인터페이스나 추상 클래스로 외부에서 동일한 방식으로 알고리즘을 호출하는 방법을 명시
- ConcreteStrategy
  - 스트래티지 패턴에서 명시한 알고리즘을 실제로 구현한 클래스
- Context
  - 스트래티지 패턴을 이용하는 역할을 수행한다.
필요에 따라 동적으로 구체적인 전략을 바꿀 수 있도록 setter 메서드(‘집약 관계’)를 제공한다.








<br/><br/>


## 구현


로봇 만들기

<br/>

![](https://images.velog.io/images/cham/post/1662b221-78bb-4ed9-b8a9-835fdfcb9bed/image.png)


**AttackStrategy.class**

```java
public interface AttackStrategy { void attack(); }
```

<br/>


**MissileStrategy.class**

```java
public class MissileStrategy implements AttackStrategy {
    public void attack() { System.out.println("I have Missile."); }
}
```

<br/>


**PunchStrategy.class**

```java
public class PunchStrategy implements AttackStrategy {
    public void attack() { System.out.println("I have strong punch."); }
}
```

<br/>


- 공격 방법에 대한 전략을 구현한다.



<br/><br/>

**MovingStrategy.class**

```java
public interface MovingStrategy { void move(); }
```

<br/>


**FlyingStrategy.class**

```java
public class FlyingStrategy implements MovingStrategy {
    public void move() { System.out.println("I can fly."); }
}
```

<br/>


**WalkingStrategy.class**

```java
public class WalkingStrategy implements MovingStrategy {
    public void move() { System.out.println("I can only walk."); }
}
```

<br/>


- 이동 방법에 대한 전략을 구현한다.
- 각 전략들을 캡슐화하여 차후 추가 및 변경 시, Robot에 대한 변겅은 발생하지 않도록 한다


<br/><br/>

**Robot.class**

```java
public abstract class Robot {
    private String name;
    private AttackStrategy attackStrategy;
    private MovingStrategy movingStrategy;

    public Robot(String name) { this.name = name; }
    public String getName() { return name; }
    public void attack() { attackStrategy.attack(); }
    public void move() { movingStrategy.move(); }

    // 집약 관계, 전체 객체가 메모리에서 사라진다 해도 부분 객체는 사라지지 않는다.
    // setter 메서드
    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy; }
    public void setMovingStrategy(MovingStrategy movingStrategy) {
        this.movingStrategy = movingStrategy; }
}
```

<br/>

**TaekwonV.class**

```java
public class TaekwonV extends Robot {
    public TaekwonV(String name) { super(name); }
}
```

<br/>

**Atom.class**

```java
public class Atom extends Robot {
    public Atom(String name) { super(name); }
}
```

<br/>

- 각 상황에 맞는 Strategy를 받아 수행한다

<br/><br/>


**Main.class**

```java
      public static void main(String[] args) {
        Robot taekwonV = new TaekwonV("TaekwonV");
        Robot atom = new Atom("Atom");

        /* 수정된 부분: 전략 변경 방법 */
        taekwonV.setMovingStrategy(new WalkingStrategy());
        taekwonV.setAttackStrategy(new MissileStrategy());
        atom.setMovingStrategy(new FlyingStrategy());
        atom.setAttackStrategy(new PunchStrategy());

        /* 아래부터는 동일 */
        System.out.println("My name is " + taekwonV.getName());
        taekwonV.move();
        taekwonV.attack();

        System.out.println();
        System.out.println("My name is " + atom.getName());
        atom.move();
        atom.attack();
    }
```


<br/>

실행결과
```java
>>>My name is TaekwonV
>>>I can only walk.
>>>I have Missile.

>>>My name is Atom
>>>I can fly.
>>>I have strong punch.
```


<br/><br/>

## 장단점


- 장점
  - 컨텍스트 코드의 변경 없이 새로운 전략을 추가할 수 있다.
  - 즉, 컨텍스트에 대한 OCP 원칙을 만족하게 된다.


- 단점
  - 사용되는 전략이 적다면, 복잡성만 늘어난다


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 스트래티지 패턴이란](https://gmlwjd9405.github.io/2018/07/06/strategy-pattern.html)
- [[우아한테크코스] 4주차 후기 - 전략패턴의 적용](https://pjh3749.tistory.com/249)


