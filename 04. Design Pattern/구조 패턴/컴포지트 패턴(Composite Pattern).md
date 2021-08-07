# 컴포지트 패턴(Composite Pattern)


- 구조(Structural) 패턴 


- 여러 개의 객체들로 구성된 복합 객체와 단일 객체를 클라이언트에서 구별 없이 다루게 해주는 패턴



- 클라이언트는 전체와 부분을 구분하지 않고 동일한 인터페이스 를 사용할 수 있다.








<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/8f05f4e1-4a54-438e-ac04-1aa2d5a10df3/image.png)


- Component
  - 구체적인 부분
  - 즉 Leaf 클래스와 전체에 해당하는 Composite 클래스에 공통 인터페이스를 정의
- Leaf
  - 구체적인 부분 클래스
  - Composite 객체의 부품으로 설정
- Composite
  - 전체 클래스
  - 복수 개의 Component를 갖도록 정의
  - 그러므로 복수 개의 Leaf, 심지어 복수 개의 Composite 객체를 부분으로 가질 수 있음(Decorator 패턴과의 차이점)



<br/><br/>

## 구현



추가 부품 늘리기

![](https://images.velog.io/images/cham/post/91adcfba-c32f-44ce-9fff-0f278c6fde4b/image.png)

<br/>


**ComputerDevice.class**

```java
public abstract class ComputerDevice {
    public abstract int getPrice();
    public abstract int getPower();
}

```


- 가격과 전압을 얻을 수 있는 method 선언


<br/><br/>

**Keyboard.class**

```java
public class Keyboard extends ComputerDevice {
    private int price;
    private int power;
    public Keyboard(int power, int price) {
        this.power = power;
        this.price = price;
    }
    public int getPrice() { return price; }
    public int getPower() { return power; }
}
```

<br/>

**Monitor.class**

```java
public class Monitor extends ComputerDevice {
    private int price;
    private int power;
    public Monitor(int power, int price) {
        this.power = power;
        this.price = price;
    }
    public int getPrice() { return price; }
    public int getPower() { return power; }
}
```

<br/>

**Body.class**

```java
public class Body extends ComputerDevice {
    private int price;
    private int power;
    public Body(int power, int price) {
        this.power = power;
        this.price = price;
    }
    public int getPrice() { return price; }
    public int getPower() { return power; }
}
```

<br/>

- 각 부품 Class들 

<br/><br/>



**Computer.class**

```java
public class Computer extends ComputerDevice {
    private List<ComputerDevice> components = new ArrayList<ComputerDevice>();

    public void addComponent(ComputerDevice component) { components.add(component); }
    public void removeComponent(ComputerDevice component) { components.remove(component); }

    public int getPrice() {
        int price = 0;
        for(ComputerDevice component : components) {
            price += component.getPrice();
        }
        return price;
    }

    public int getPower() {
        int power = 0;
        for(ComputerDevice component : components) {
            power += component.getPower();
        }
        return power;
    }
}
```


- 실 사용될 Class 정의. ```ComputerDevice```에 의해 각종 부품을 전달받아 List를 통해 저장한다.


<br/><br/

**Main.class**

```java
   public static void main(String[] args) {
        Keyboard keyboard = new Keyboard(5, 2);
        Body body = new Body(100, 70);
        Monitor monitor = new Monitor(20, 30);

        Computer computer = new Computer();
        computer.addComponent(keyboard);
        computer.addComponent(body);
        computer.addComponent(monitor);

        int computerPrice = computer.getPrice();
        int computerPower = computer.getPower();
    }
```

<br/>


- 차후 추가되는 부품이 있다면 해당 부품을 기존 부품들과 일관되게 취급할 수 있다



<br/><br/><br/>




## 장단점


- 장점
  - 객체들이 모두 같은 타입으로 취급되기 때문에 새로운 클래스 추가가 용이하다.
  - 단일객체, 집합객체 구분하지 않고 코드 작성이 가능하다.



- 단점
  - 설계를 일반화 시켜 객체간의 구분, 제약이 힘들다.


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 컴퍼지트 패턴이란](https://gmlwjd9405.github.io/2018/08/10/composite-pattern.html)
- [[디자인 패턴 7편] 구조 패턴, 컴퍼지트(Composite)](https://dailyheumsi.tistory.com/193)
