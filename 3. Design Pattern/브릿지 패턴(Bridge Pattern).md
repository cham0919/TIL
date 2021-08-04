# 브릿지 패턴(Bridge Pattern)


- 구조(Structural) 패턴 


- 구현부에서 추상층을 분리하여 각자 독립적으로 변형 및 확장이 가능하도록 만드는 패턴


- 기능과 구현에 대해 두 개의 별도 클래스로 구현한다

<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/3d78b750-1cd2-46ba-bfff-62feaa7ae158/image.png)


- Abstraction : 기능 계층의 최상위 클래스로 추상 인터페이스
- RefindAbstraction : 기능 계층에서 새로운 부분을 확장한 클래스
- Implementor : Abstraction의 기능을 구현하기 위한 인터페이스 정의
- ConcreteImplementor : 실제 기능을 구현하는 클래스


<br/><br/>

## 구현



사냥법 출력하기

![](https://images.velog.io/images/cham/post/20d9a039-df07-4981-bfce-22958c2e608b/image.png)

<br/>


**Hunting_Handler.class**

```java
public interface Hunting_Handler {
    void Find_Quarry();
    void Detected_Quarry();
    void attack();
}
```


- 사냥법 Method에 대한 인터페이스를 정의한다


<br/><br/>

**Hunting_Method1.class**

```java
public class Hunting_Method1 implements Hunting_Handler {
    public void Find_Quarry()
    {
        System.out.println("물 위에서 찾는다");
    }
    public void Detected_Quarry()
    {
        System.out.println("물고기 발견!");
    }
    public void attack()
    {
        System.out.println("낚아챈다.");
    }
}
```

<br/>

**Hunting_Method2.class**

```java
public class Hunting_Method2 implements Hunting_Handler {
    public void Find_Quarry()
    {
        System.out.println("지상에서 찾는다");
    }
    public void Detected_Quarry()
    {
        System.out.println("노루 발견");
    }
    public void attack()
    {
        System.out.println("물어뜯는다.");
    }
}
```

<br/>

- 사냥법에 대한 실제 출력 기능들을 정의한다

<br/><br/>



**Animal.class**

```java
public class Animal {

    private Hunting_Handler hunting;

    public Animal(Hunting_Handler hunting)
    {
        this.hunting=hunting;
    }
    public void Find_Quarry()
    {
        hunting.Find_Quarry();
    }
    public void Detected_Quarry()
    {
        hunting.Detected_Quarry();
    }
    public void attack()
    {
        hunting.attack();
    }
    public void hunt()
    {
        Find_Quarry();
        Detected_Quarry();
        attack();
    }
}
```


- 객체 생성과 기능에 대한 Method를 정의한다


<br/><br/>

**Bird.class**

```java
public class Bird extends Animal
{
    public Bird(Hunting_Handler hunting)
    {
        super(hunting);
    }
    public void hunt()
    {
        System.out.println("새의 사냥방식");
        Find_Quarry();
        Detected_Quarry();
        attack();
    }
}
```
<br/>

**Tiger.class**

```java
public class Tiger extends Animal
{
    public Tiger(Hunting_Handler hunting)
    {
        super(hunting);
    }
    public void hunt()
    {
        System.out.println("호랑이의 사냥방식");
        Find_Quarry();
        Detected_Quarry();
        attack();
    }
}
```
<br/>


- 각 기능들을 조합하여 구현한다


<br/><br/>


**Main.class**

```java
      public static void main(String argsp[])
    {
        Animal tiger = new Tiger(new Hunting_Method2());
        Animal bird = new Bird(new Hunting_Method1());

        tiger.hunt();
        System.out.println("-----------");
        bird.hunt();
    }
```

<br/>


```java
호랑이의 사냥방식
지상에서 찾는다
노루 발견
물어뜯는다.
--------------
새의 사냥방식
물 위에서 찾는다
물고기 발견!
낚아챈다.

```

<br/>



- 두 개의 클래스 계층을 분리해 두면 각각의 클래스 계층을 독립적으로 확장할 수 있다



<br/><br/><br/>




## 장단점


- 장점
  - 추상화된 부분과 실제 구현 부분을 독립적으로 확장할 수 있다.
  - 구현을 인터페이스에 완전히 결합시키지 않았기 때문에 구현과 추상화된 부분을 분리시킬 수 있다
  - 추상화된 부분을 구현한 구상 클래스를 바꿔도 클라이언트 쪽에는 영향을 끼치지 않는다.



- 단점
  - 디자인이 복잡해진다


<br/><br/><br/><br/>

---
참조:
- [07 브릿지 패턴 (Bridge Pattern)](https://lktprogrammer.tistory.com/35)
- [[디자인 패턴] Bridge 패턴 (기능계층과 구현계층 분리하기)](https://m.blog.naver.com/tradlinx0522/220928963011)
- [기타 여러가지 패턴들 정리](https://thefif19wlsvy.tistory.com/50)
