# 팩토리 메서드 패턴


- 생성(Creational) 패턴
 
- 객체 생성 처리를 서브 클래스로 분리 해 처리하도록 캡슐화하는 패턴

-  객체를 생성하기 위해 인터페이스를 정의하지만, 어떤 클래스의 인스턴스를 생성할지에 대한 결정은 서브클래스가 내리도록 한다








<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/679e0dcf-7303-4b7f-b458-53935c00e84b/image.png)


- Product
  - 팩토리 메서드로 생성될 객체의 공통 인터페이스
- ConcreteProduct
  - 구체적으로 객체가 생성되는 클래스
- Creator
  - 팩토리 메서드를 갖는 클래스
- ConcreteCreator
  - 팩토리 메서드를 구현하는 클래스로 ConcreteProduct 객체를 생성


<br/><br/>

## 구현




### 1. 기본 구현

<br/>

**Product.class**

```java
public abstract class Product {

    public abstract void use();

}
```


- 우선 생성될 객체에 대한 추상 클래스를 구현한다.

<br/><br/>



**IDCard.class**

```java
public class IDCard extends Product{

    private String owner;

    public IDCard(String owner) {
        System.out.println(owner + "의 카드를 만듭니다.");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println(owner + "의 카드를 사용합니다.");
    }

    public String getOwner() {
        return owner;
    }
}
```


- IDCard라는 객체를 생성할 것이고, 생성과 등록, 소유자의 변수가 있다

<br/><br/>



**Factory.class**

```java
public abstract class Factory {

    public final Product create(String owner) {
        Product p = createProduct(owner);
        registerProduct(p);
        return p;
    }
    protected abstract Product createProduct(String owner);
    protected abstract void registerProduct(Product p);
}
```


- Product 객체의 생성을 관리할 Factory 추상 클래스를 구현한다
- 소유자 변수를 받아 Product를 생성하여 return할 ```create(String owner)```와 ```createProduct(String owner)```, Product를 등록할 ```registerProduct(Product p)```를 선언한다

<br/><br/>



**IDCardFactory.class**

```java
public class IDCardFactory extends Factory {

    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String owner) {
        return new IDCard(owner);
    }

    @Override
    protected void registerProduct(Product p) {
        owners.add(((IDCard) p).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
```


- Factory를 상속받아 필요한 부분을 구현한다




<br/><br/>



**Main.class**

```java
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("홍길동");
        Product card2 = factory.create("김정태");
        Product card3 = factory.create("손수영");
        card1.use();
        card2.use();
        card3.use();
    }
```

<br/>

```java
홍길동의 카드를 만듭니다.
김정태의 카드를 만듭니다.
손수영의 카드를 만듭니다.
홍길동의 카드를 사용합니다.
김정태의 카드를 사용합니다.
손수영의 카드를 사용합니다.
```

- 다음과 같이 사용한다


<br/><br/>

### 2. 응용 구현


<br/>

현재 Factory는 Product의 IDCard 하나만 담당하고 있다. 하지만 차후 IDCard가 여러개 생간다면 그에 맞춰 Factory는 어떻게 구현해야할까?
S사, N사, K사 종류의 IDCard가 생겼다고 가정하자.

![](https://images.velog.io/images/cham/post/2bc29b0d-ac30-465b-9a87-d34ebd34dfb0/image.png)

**K_IDCard.class**

```java
public class K_IDCard extends Product{

    private String owner;

    public K_IDCard(String owner) {
        System.out.println("K사의 " + owner + "의 카드를 만듭니다.");
        this.owner = owner;
    }

    @Override
    public void use() {
        System.out.println("K사의 " + owner + "의 카드를 사용합니다.");
    }

    public String getOwner() {
        return owner;
    }
}
```


- 다음과 같이 K사, N사, S사의 IDCard로 나뉘었다

<br/><br/>



<br/><br/>



**Factory.class**

```java
public abstract class Factory {

    public final Product create(String company, String owner) throws ClassNotFoundException {
        Product p = createProduct(company, owner); // 변경. company 구분자 추가
        registerProduct(p);
        return p;
    }
    protected abstract Product createProduct(String company, String owner) throws ClassNotFoundException; // // 변경. company 구분자 추가
    
    
}
```


- Product 생성시, 구분자가 필요하기에 인자값을 추가했다
- 현재로 간단한 예제를 위해 ```registerProduct```는 제거했다

<br/><br/>



**IDCardFactory.class**

```java
public class IDCardFactory extends Factory {

    private List<String> owners = new ArrayList<>();

    @Override
    protected Product createProduct(String company, String owner) throws ClassNotFoundException { 
        if ("S".equals(company)) { // 변경 - 구분자에 맞는 Class 반환
            return new S_IDCard(owner);
        } else if ("N".equals(company)) {
            return new N_IDCard(owner);
        } else if ("K".equals(company)) {
            return new K_IDCard(owner);
        } else {
            throw new ClassNotFoundException("No Search IDCard. Please Check Company");
        }
    }

    @Override
    protected void registerProduct(Product p) {
        owners.add(((K_IDCard) p).getOwner());
    }

    public List<String> getOwners() {
        return owners;
    }
}
```


- 구분자를 받아 Class를 생성하여 반환한다



<br/><br/>



**Main.class**

```java
    public static void main(String[] args) throws ClassNotFoundException {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("N", "홍길동");
        Product card2 = factory.create("S", "김정태");
        Product card3 = factory.create("K", "손수영");
        card1.use();
        card2.use();
        card3.use();
    }
```

<br/>

```java
N사의 홍길동의 카드를 만듭니다.
S사의 김정태의 카드를 만듭니다.
K사의 손수영의 카드를 만듭니다.
N사의 홍길동의 카드를 사용합니다.
S사의 김정태의 카드를 사용합니다.
K사의 손수영의 카드를 사용합니다.

```


- 다음과 같이 사용한다



<br/><br/><br/>




## 장단점


- 장점
  - 객체의 생성 코드를 별도의 클래스/메서드로 분리함으로써 객체 생성의 변화에 대비하는 데 유용하다
  - 


  


- 단점
  - 패턴을 남발하게 될 시, 구현해야하는 클래스의 개수가 늘어나게 된다


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 팩토리 메서드 패턴이란](https://gmlwjd9405.github.io/2018/08/07/factory-method-pattern.html)
- [팩토리 메소드 패턴 (Factory Method Pattern)](https://johngrib.github.io/wiki/factory-method-pattern/#fn:head-example)
