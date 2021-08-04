# 방문자 패턴 (Visitor pattern)


- 행위(Behavioral) 패턴
 


-  방문자와 방문 공간을 분리하여, 방문 공간이 방문자를 맞이할 때, 이후에 대한 행동을 방문자에게 위임하는 패턴


- 즉, 로직과 구조를 분리하는 패턴이라고 볼 수 있다. 로직과 구조가 분리되면 구조를 수정하지 않고도 새로운 동작을 기존 객체 구조에 추가할 수 있다.








<br/><br/>

## 구조


![](https://images.velog.io/images/cham/post/c27fa040-7995-4756-bc05-0da3d87a1ffc/image.png)

- Visitor
  - 방문자 클래스의 인터페이스
  - visit(Element) 을 공용 인터페이스로 쓴다. Element 는 방문 공간이다.
- Element
  - 방문 공간 클래스의 인터페이스
  - accept(Vistor) 를 공용 인터페이스로 쓴다. Visitor 는 방문자다.
  - 내부적으로 Vistor.visit(this) 를 호출한다.
- ConcreteVisitor
  - Visitor 를 구체적으로 구현한 클래스
- ConcreteElement
  - Element 를 구체적으로 구현한 클래스







<br/><br/>


## 구현


등급별 고객 혜택을 제공하는 쇼핑몰 시스템 만들기

<br/>

![](https://images.velog.io/images/cham/post/8987ccf5-f1f1-472d-ab74-480a4d9da518/image.png)


**Benefit.class**

```java
public interface Benefit {
    void getBenefit(GoldMember member);
    void getBenefit(VipMember member);
}
```

<br/>


- 혜택을 받을 Member 별로 실행 가능한 메소드를 정의한다

<br/><br/>


**DiscountBenefit.class**

```java
public class DiscountBenefit implements Benefit {
    @Override
    public void getBenefit(GoldMember member) {
        System.out.println("Discount for Gold Member");
    }

    @Override
    public void getBenefit(VipMember member) {
        System.out.println("Discount for Vip Member");
    }
}
```

<br/>


**PointBenefit.class**

```java
public class PointBenefit implements Benefit {
    @Override
    public void getBenefit(GoldMember member) {
        System.out.println("Point for Gold Member");
    }

    @Override
    public void getBenefit(VipMember member) {
        System.out.println("Point for Vip Member");
    }
}
```

<br/>


- 각 멤버 등급별 혜택에 대해 기능을 구현한다



<br/><br/>

**Member.class**

```java
public interface Member {
    void getBenefit(Benefit benefit);
}
```

<br/>

- 등급별 멤버가 혜택을 받을 수 있는 메소드를 Member 인터페이스에 선언한다.

<br/><br/>

**GoldMember.class**

```java
public class GoldMember implements Member {
    @Override
    public void getBenefit(Benefit benefit) {
        benefit.getBenefit(this);
    }
}
```

<br/>


**VipMember.class**

```java
public class VipMember implements Member {
    @Override
    public void getBenefit(Benefit benefit) {
        benefit.getBenefit(this);
    }
}
```

<br/>


- 혜택받는 method를 구현한다.
-  다른 Member 가 추가되더라도 구현 부분은 ```benefit.getBenefit(this);```만 추가하면 된다.
- 혹은 혜택이 추가되더라도 Member는 수정할 필요가 없어진다


<br/><br/>


**Main.class**

```java
      public static void main(String[] args) {
        Member goldMember = new GoldMember();
        Member vipMember = new VipMember();
        Benefit pointBenefit = new PointBenefit();
        Benefit discountBenefit = new DiscountBenefit();

        goldMember.getBenefit(pointBenefit);
        vipMember.getBenefit(pointBenefit);
        goldMember.getBenefit(discountBenefit);
        vipMember.getBenefit(discountBenefit);
    }
```


<br/>

실행결과
```java
>>>Point for Gold Member
>>>Point for Vip Member
>>>Discount for Gold Member
>>>Discount for Vip Member
```


<br/><br/>

## 장단점


- 장점
  - 작업 대상(방문 공간) 과 작업 항목(방문 공간을 가지고 하는 일)을 분리시킨다.
  - 데이터와 알고리즘이 분리되어, 데이터의 독립성을 높여준다.
  - 작업 대상의 입장에서는 인터페이스를 통일시켜, 사용자에게 동일한 인터페이스를 제공한다.


- 단점
  - 새로운 작업 대상(방문 공간)이 추가될 때마다 작업 주체(방문자) 도 이에 대한 로직을 추가해야 한다.
  - 두 객체 (방문자와 방문 공간)의 결합도가 높아진다.


<br/><br/><br/><br/>

---
참조:
- [[디자인 패턴 15편] 행동 패턴, 방문자 (Visitor)](https://dailyheumsi.tistory.com/216)
- [방문자 패턴 - Visitor pattern](https://thecodinglog.github.io/design/2019/10/29/visitor-pattern.html)


