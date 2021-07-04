# 플라이웨이트 패턴(Flyweight Pattern)


- 구조(Structural) 패턴
 


- 어떤 클래스의 인스턴스 한 개만 가지고 여러 개의 "가상 인스턴스"를 제공하고 싶을 때 사용하는 패턴

-  인스턴스를 가능한 대로 공유시켜 new연산자를 통한 메모리 낭비를 줄이는 방식



<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/2634ced9-d9ac-4628-b6f7-86541cfe77e3/image.png)

- Flyweight : 공유에 사용할 클래스들의 인터페이스(API)를 선언
- ConcreteFlyweight : Flyweight의 내용을 정의. 실제 공유될 객체
- FlyweightFactory : 해당 공장을 사용해서 Flyweight의 인스턴스를 생성 또는 공유해주는 역할



<br/><br/>


## 구현




<br/>


**Shape.class**

```java
public interface Shape {
    public void draw();
}
```



<br/><br/>


**Circle .class**

```java
public class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle [color=" + color + ", x=" + x + ", y=" + y + ", radius=" + radius + "]");
    }
}

```

 - ```ConcreteFlyweight``` :  인터페이스(API)의 내용을 정의하고, 필요한 속성을 가질 수 있다.



<br/><br/>


**ShapeFactory .class**

```java
public class ShapeFactory {
    private static final HashMap<String, Circle> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if(circle == null) {
            circle = new Circle(color);
            circleMap.put(color,circle);
            System.out.println("==== 새로운 객체 생성 : " + color + "색 원 ====" );
        } 

        return circle;
    }
}
```

 - ```getCircle()``` :  객체의 생성 또는 공유의 역할을 담당하며 클라이언트에게 응답한다.

<br/><br/>


**Main.class**

```java
  public static void main(String[] args) {
        String[] colors = {"Red","Green","Blue","Yellow"};

        for (int i = 0; i < 10; i++) {
            Circle circle = (Circle)ShapeFactory.getCircle(colors[(int) (Math.random()*4)]);
        }
    }
```


- Proxy객체
- 물류창고에 주문을 이행하기 전, 재고가 있는지 검사 후, ```Warehouse```에게 요청한다




<br/><br/>

## 장단점


- 장점
  - 많은 객체를 만들때 성능을 향상된다
  - 여러 "가상" 객체의 상태를 한 곳에 집중시켜놓을 수 있다
  
  
  


- 단점
  - 특정 인스턴스의 공유 컴포넌트를 다르게 행동하게 하는 것이 불가능하다
  - 객체의 값을 변경하면 공유받은 "가상" 객체를 사용하는 곳에 영향을 줄 수 있다


<br/><br/><br/><br/>

---
참조:
- [[디자인패턴/Design Pattern] Flyweight Pattern / 플라이웨이트 패턴](https://lee1535.tistory.com/106)

