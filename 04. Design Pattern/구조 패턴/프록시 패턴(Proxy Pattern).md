# 프록시 패턴(Proxy Pattern)


- 구조(Structural) 패턴
 


- 어떤 다른 객체로 접근하는 것을 통제하기 위해서 그 객체의 대리자(surrogate)나 자리표시자(placeholder)의 역할을 하는 객체를 제공하는 패턴

-  중요한 것은 흐름제어만 할 뿐 결과값을 조작하거나 변경시키지 않는다



<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/e0c5c322-e19f-4d7a-aab5-74476e677244/image.png)

- Proxy Object는 메서드 수행시 실제 객체(Real Object)의 메서드에 위임합니다.


<br/><br/>

## 사용되는 세가지 방법



1. Virtual Proxy
 - 주체 클래스가 리소스 집약적인 경우
   - 예를들어, 주체 클래스가 해상도가 아주 높은 이미지를 처리해야 하는 경우 인스턴스화 할때 많은 메모리를 사용하게 되는데, 프록시 클래스에서 자잘한 작업들을 처리하고 리소스가 많이 요구되는 작업들이 필요할 때에만 주체 클래스를 사용하도록 구현할 수 있다.

2. Protection Proxy

- 주체 클래스에 대한 접근을 제어하기 위한 경우
  - 프록시 클래스에서 클라이언트가 주체 클래스에 대한 접근을 허용할지 말지 결정하도록 할 수 있다. 

3. Remote Proxy
 - 프록시 클래스는 로컬에 두고, 주체 클래스는 Remote 로 존재하는 경우
   - Google Docs 같은 것이 대표적인 예시이다. 브라우저는 브라우저대로 필요한 자원을 로컬에 가지고 있고, 또다른 일부 자원은 Google 서버에 있는 형태이다.


<br/><br/>

## 구현


창고에서 주문처리하기 전에 재고가 있는지 확인하는 시스템 구현

![](https://images.velog.io/images/cham/post/7d65a279-9e78-4bf9-8e81-42cf517ecb88/image.png)


<br/>


**IOrder.class**

```java
public interface IOrder {

    void fulfillOrder(Order order);
}

```


- 주문을 이행할 Method 선언

<br/><br/>


**Warehouse.class**

```java
public class Warehouse implements IOrder {
    private Hashtable<Integer, Integer> stock;
    private String address;

    @Override
    public void fulfillOrder(Order order) {
        for (Item item: order.getItemList()) {
            Integer sku = item.getSku();
            this.stock.replace(sku, stock.get(sku) - 1);

            /* 포장, 배송 등 기타 작업들이 추가적으로 이루어질 수 있음 */

        }
    }

    public int currentInventory(Item item) {
        return stock.getOrDefault(stock.get(item.getSku()), 0);
    }
}

```


- 물류창고에서 주문을 이행할 실 Method를 구현한다(RealSubject)


<br/><br/>


**OrderFulfillment.class**

```java
public class OrderFulfillment implements IOrder {
    private List<Warehouse> warehouses;

    @Override
    public void fulfillOrder(Order order) {
        for (Item item: order.getItemList()) {
            for (Warehouse warehouse: warehouses) {
                if (warehouse.currentInventory(item) != 0) {
                    warehouse.fulfillOrder(order);
                }
            }
        }
    }
}
```


- Proxy객체
- 물류창고에 주문을 이행하기 전, 재고가 있는지 검사 후, ```Warehouse```에게 요청한다




<br/><br/>

## 장단점


- 장점
  - Polymorphism(다형성)을 가지도록 디자인함으로써 클라이언트가 하나의 인터페이스 접근할 수 있다
  - 리소스 집약적인 객체가 실제로 필요해질 때까지 라이트한 버전의 프록시 클래스로 전처리 등 필요한 작업을 진행할 수 있고, –Virtual Proxy
  - 클라이언트가 주체 클래스에 접근하는 것에 대한 제한이나 어떤 클라이언트인지에 따라 서로 다른 방식으로 요청이 처리되도록 할 수 있다. –Protection Proxy
  - 또한, 동일한 물리적 또는 가상 공간에 있지 않은 시스템을 로컬에 있는 것 처럼 표현할 수 있다. –Remote Proxy
  
  


- 단점
  - 객체를 생성할때 한단계를 거치게 되므로, 빈번한 객체 생성이 필요한 경우 성능이 저하될 수 있다.
  - 프록시 내부에서 객체 생성을 위해 스레드가 생성, 동기화가 구현되야 하는 경우 성능이 저하될 수 있다.
  - 로직이 난해해져 가독성이 떨어질 수 있다.


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 프록시 패턴(Proxy Pattern)에 대하여](https://coding-factory.tistory.com/711)
- [디자인패턴 - 프록시 패턴](https://yaboong.github.io/design-pattern/2018/10/17/proxy-pattern/)
- [[디자인 패턴 9편] 구조 패턴,프록시(Proxy)](https://dailyheumsi.tistory.com/201)
- [[구조 패턴] 프록시 패턴(Proxy Pattern) 이해 및 예제](https://readystory.tistory.com/132?category=822867)
