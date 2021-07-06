# 프로토타입 패턴(Prototype Pattern)


- 생성(Creational) 패턴
 


- 원형이 되는 인스턴스로 새로운 인스턴스를 만드는 방식, 즉 객체에 의해 생성될 객체의 타입이 결정되는 생성 디자인 패턴


-  Original 객체를 새로운 객체에 복사하여 우리의 필요에 따라 수정하는 메커니즘을 제공한다




<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/4ed31652-73c8-44f5-b2fd-7a9ef9f2ccf0/image.png)



- Prototype : 인스턴스를 복사하여 새로운 인스턴스를 만들기 위한 메소드를 결정
- ConcretePrototype : 인스턴스를 복사해서 새로운 인스턴스를 만드는 메소드를 실제로 구현

<br/><br/>

## 구현



![](https://images.velog.io/images/cham/post/ac58b483-2116-4d3f-88cc-b5895a72e9d3/image.png)

**Type.class**

```java
public enum Type {

    TRIANGLE, CIRCLE, RECTANGLE
}
```


- 각 Type에 대한 Enum 생성

<br/><br/>



**Shape.class**

```java
public abstract class Shape implements Cloneable {
    protected Type type;

    abstract void draw();

    @Override
    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

```


- clone() 메소드를 사용하기 위해 Cloneable 인터페이스를 구현하며, 공통 메소드인 draw()는 추상 메소드로 정의하고,
하위 클래스에서 사용할 clone()는 공통으로 동작하는 메소드를 정의

<br/><br/>



**Rectangle.class**

```java
public class Rectangle extends Shape {
    public Rectangle() {
        this.type = Type.RECTANGLE;
    }

    @Override
    void draw() {
        System.out.println("[Rectangle]입니다.");
    }
}
```

<br/>

**Triangle.class**

```java
public class Triangle extends Shape {
    public Triangle() {
        this.type = Type.TRIANGLE;
    }

    @Override
    void draw() {
        System.out.println("[Triangle]입니다.");
    }
}
```

<br/>

**Circle.class**

```java
public class Circle extends Shape {
    public Circle() {
        this.type = Type.CIRCLE;
    }

    @Override
    void draw() {
        System.out.println("[Circle]입니다.");
    }
}
```

<br/>

- 각각 Shape 클래스를 상속 받으며 draw()메소드를 재정의

<br/><br/>



**ShapeStroe.class**

```java
public class ShapeStore {
    private static Map<Type, Shape> shapeMap = new HashMap<Type, Shape>();

    public void registerShape() {
        Rectangle rec = new Rectangle();
        Circle cir = new Circle();
        Triangle tri = new Triangle();

        shapeMap.put(rec.type, rec);
        shapeMap.put(cir.type, cir);
        shapeMap.put(tri.type, tri);
    }

    public Shape getShape(Type type)  {
        return (Shape) shapeMap.get(type).clone();
    }
}
```


- 저장소를 담당하며, 최초 registerShap() 메소드 호출 시 복제에 사용할 객체를 인스턴스화 해서 shapeMap에 저장하는 동적을 하며, getShape()메소드를 통해 객체의 복사본을 반환해주는 역할

<br/><br/>



**Main.class**

```java
    public static void main(String[] args) {
        ShapeStore manager = new ShapeStore();
        manager.registerShape();

        Circle cir1 = (Circle)manager.getShape(Type.CIRCLE);
        cir1.draw();
        Circle cir2 = (Circle)manager.getShape(Type.CIRCLE);
        cir2.draw();

        Rectangle rec1 = (Rectangle)manager.getShape(Type.RECTANGLE);
        rec1.draw();

        Triangle tri1 = (Triangle)manager.getShape(Type.TRIANGLE);
        tri1.draw();

    }
```

<br/>

실행결과
```java
>>>[Circle]입니다.
>>>[Circle]입니다.
>>>[Rectangle]입니다.
>>>[Triangle]입니다.
```



<br/><br/>




## 장단점


- 장점
  - 객체의 생성이 값비싼 경우 ( DB를 참조하는 등 ) 객체 생성의 비용을 줄일 수 있다
  - 시스템이 어떤 Concrete Class를 사용하는지에 대한 정보를 캡슐화
  - 객체를 생성해 주기 위한 별도의 객체 생성 클래스가 불필요
  - 객체의 각 부분을 조합해서 생성되는 형태에도 적용 가능




  


- 단점
  - clone()메소드는 Object 클래스의 메소드로, 얕은 복사로 동작하기에 깊은 복제를 사용해야 할 경우 clone()메소드 재정의 항상 고려
  - 생성될 객체들의 자료형인 클래스들이 모두 clone() 메서드를 구현해야 한다
  




<br/><br/><br/><br/>

---
참조:
- [[생성 패턴] 프로토타입 패턴(Prototype Pattern) 이해 및 예제](https://readystory.tistory.com/122)
- [[디자인패턴/Design Patter] Prototype 패턴 프로토타입 패턴](https://lee1535.tistory.com/76)
- [원형(프로토타입) 패턴(prototype pattern)](https://leetaehoon.tistory.com/55)
