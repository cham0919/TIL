# 추상 팩토리 패턴


- 생성(Creational) 패턴 

- 구체적인 클래스에 의존하지 않고 서로 연관되거나 의존적인 객체들의 조합을 만드는 인터페이스를 제공하는 패턴


- 상세화된 서브클래스를 정의하지 않고도 서로 관련성이 있거나 독립적인 여러 객체의 군을 생성하기 위한 인터페이스를 제공한다








<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/cc021b1e-1c7a-40a1-93d8-f62a44fb1939/image.png)


- AbstractFactory
  - 실제 팩토리 클래스의 공통 인터페이스
- ConcreteFactory
  - 구체적인 팩토리 클래스로 AbstractFactory 클래스의 추상 메서드를 오버라이드함으로써 구체적인 제품을 생성한다.
- AbstractProduct
  - 제품의 공통 인터페이스
- ConcreteProduct
  - 구체적인 팩토리 클래스에서 생성되는 구체적인 제품



<br/><br/>

## 구현




### 1. 팩토리 메소드 패턴 적용

<br/>

부품 조달을 위한 Factory가 필요한데 현재 S사와 K사가 있다고 하자.

<br/>

**MotorFactory.class**

```java
public class MotorFactory{

    public static Motor createMotor(String vendorID) {
        Motor motor = null;

        switch (vendorID) {
            case "S":
                motor = new S_Motor();
                break;
            case "K":
                motor = new K_Motor();
                break;
        }
        return motor;
    }
}
```


- 벤더에 따라 Motor를 생성하여 리턴한다

<br/><br/>


**DoorFactory.class**

```java
public class DoorFactory {

    public static Door createDoor(String vendorID) {
        Door door = null;

        switch (vendorID) {
            case "S":
                door = new S_Door();
                break;
            case "K":
                door = new K_Door();
                break;
        }
        return door;
    }
}
```


- 벤더에 따라 Door를 생성하여 리턴한다

<br/><br/>

**Main.class**

```java
public class Main {

    public static void main(String[] args) {
        Door S_Door = DoorFactory.createDoor("S");
        Motor S_Motor = MotorFactory.createMotor("S");

        S_Door.open();
        S_Motor.move();
    }
}
```

<br/>


```java
S_Door Open
S_Motor Move
```

- 다음와 같이 사용한다

<br/><br/>


### 문제점

-  다른 제조업체의 부품으로 변경해야하는 경우
    - S사가 아닌 K사로 변경되었을 경우
      - Factory를 호출하는 곳에 가서 Door와 Motor의 인자값을 모두 K사로 변경해야한다
      - 만약 부품의 종류가 20개였으면 20군데의 인자값을 모두 변경해줘야한다
      - 부품의 수가 많아지면 특정 업체별 부품을 생성하는 코드의 길이가 길어지고 복잡해진다.


- 새로운 제조업체의 부품이 추가된 경우
  - DoorFactory 클래스뿐만 아니라 나머지 부품과 연관된 Factory 클래스에서도 마찬가지로 삼성의 부품을 생성하도록 변경해야 한다.
  - 또한, 위와 마찬가지로 특정 업체별 부품을 생성하는 코드에서 삼성의 부품을 생성하도록 모두 변경해야 한다.


- 결과적으로 기존의 팩토리 메서드 패턴을 이용한 객체 생성은 관련 있는 여러 개의 객체를 일관성 있는 방식으로 생성하는 경우에 많은 코드 변경이 발생 하게 된다.


<br/><br/>


### 2. 추상 팩토리 패턴 적용

<br/>


**Factory.class**

```java
public abstract class Factory {

    abstract Motor createMotor();
    abstract Door createDoor();
}
```


- Motor와 Door를 생산하는 Factory 추상 클래스를 생성한다

<br/><br/>



**S_Factory.class**

```java
public class S_Factory extends Factory{

    @Override
    Motor createMotor() { return new S_Motor(); }

    @Override
    Door createDoor() { return new S_Door(); }
}
```

<br/>

**K_Factory.class**

```java
public class K_Factory extends Factory {

    @Override
    Motor createMotor() { return new K_Motor(); }

    @Override
    Door createDoor() { return new K_Door(); }
}
```


- 부품별 Factory가 아닌 제조업체별 Factory로 구현한다




<br/><br/>

![](https://images.velog.io/images/cham/post/70b39270-f2be-4524-97a1-99d319e76411/image.png)

**Main.class**

```java
public class Main {

    public static void main(String[] args) {

        Factory factory = new S_Factory();
        Motor motor = factory.createMotor();
        Door door = factory.createDoor();

        door.open();
        motor.move();
    }
}
```

<br/>

```java
S_Door Open
S_Motor Move
```

- 제조 업체별로 Factory 클래스를 정의했으므로 제조 업체별 부품 객체를 간단히 생성할 수 있다.
- 새로운 제조 업체의 부품을 지원하는 경우에도 해당 제조 업체의 부품을 생성하는 Factory 클래스만 새롭게 만들면 된다



<br/><br/>


## 장단점


- 장점
  - 구체 팩토리의 클래스는 응용프로그램에서 한 번만 나타나기 때문에 응용프로그램이 사용할 구체 팩토리를 변경하기는 쉽다. 또한, 구체 팩토리를 변경함으로써 응용프로그램은 서로 다른 제품을 사용할 수 있게 변경된다. 
  - 추상 팩토리 패턴을 쓰면 응용 프로그램이 생성할 객체의 클래스를 제어할 수 있다. 
  - 즉, 관련성 있는 여러 종류의 객체를 일관된 방식으로 생성하는 경우에 유용하다.



- 단점
  - 새롭게 생성되는 제품은 추상 팩토리가 생성할 수 있는 제품 집합에만 고정되어 있기 때문에 새로운 종류의 제품을 제공하기 어렵다. 


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 추상 팩토리 패턴이란](https://gmlwjd9405.github.io/2018/08/08/abstract-factory-pattern.html)
- [[디자인 패턴] 추상 팩토리 패턴 (Abstract Factory Pattern)](https://devowen.com/326)
