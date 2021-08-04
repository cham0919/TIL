# 책임 연쇄 패턴(chain-of-responsibility pattern)


- 행위(Behavioral) 패턴
 


-  요청을 처리하는 동일 인터페이스 객체들을
체인 형태로 연결해놓는 패턴

-  클라이언트로부터의 요청을 처리할 수 있는 처리객체를 집합(Chain)으로 만들어 부여함으로 결합을 느슨하기 위해 만들어진 디자인 패턴

<br/>

**적용 경우**

- 요청의 발신자와 수신자를 분리하는 경우
- 요청을 처리할 수 있는 객체가 여러개일 때 그 중 하나에 요청을 보내려는 경우
- 코드에서 처리객체(handler)를 명시적으로 지정하고 싶지 않은 경우


<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/ecf1fa8b-d7b4-40dc-b707-8ce16b7e0874/image.png)

- Handler  : 요청을 수신하고 처리객체들의 집합에 전달하는 인터페이스입니다. 집합의 첫 번째 핸들러에 대한 정보만 가지고 있으며 그 이후의 핸들러에 대해서는 알지 못합니다
- Concrete handlers  : 요청을 처리하는 실제 처리객체입니다






<br/><br/>


## 구현


- 거스름돈 구현
- 100원, 10원, 1원만 가능


![](https://images.velog.io/images/cham/post/aaf5629d-9b48-4e6e-8024-5044943376e4/image.png)
<br/>


**Currency.class**

```java
public class Currency {
    private int amount;

    public Currency(int amt) {
        this.amount=amt;
    }

    public int getAmount() {
        return this.amount;
    }
}

```

- 거스름돈을 저장하는 Dto 객체 생성

<br/><br/>


**DispenseChain class**

```java
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);
    void dispense(Currency cur);
}


```

 - Handler 선언. ```setNextChain()```을 통해 다음 체인을 설정하고 ```dispense()```을 통해 기능을 동작한다



<br/><br/>


**Won1Dispenser.class**

```java
public class Won1Dispenser implements DispenseChain{
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        int num=cur.getAmount()/1;
        System.out.println("Dispensing " +num+" 1₩ note");
    }
}
```

<br/>


**Won10Dispenser.class**

```java
public class Won10Dispenser implements DispenseChain{
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount()>=10){
            int num=cur.getAmount()/10;
            int remainder=cur.getAmount()%10;

            System.out.println("Dispensing " +num+" 10₩ note");

            if(remainder!=0) this.chain.dispense(new Currency(remainder));
        }
        else this.chain.dispense(cur);
    }
}
```

<br/>

**Won100Dispenser.class**

```java
public class Won100Dispenser implements DispenseChain{
    private DispenseChain chain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount()>=100){
            int num=cur.getAmount()/100;
            int remainder=cur.getAmount()%100;

            System.out.println("Dispensing " +num+" 100₩ note");

            if(remainder!=0) this.chain.dispense(new Currency(remainder));
        }
        else this.chain.dispense(cur);
    }
}
```

<br/>

 - Handler 구현체
 - ```DispenseChain```을 통해 다음 chain을 저장하고 ```dispense()``` 끝난 뒤, 잔여 작업이 남았으면, 다음 체인으로 전달한다

<br/><br/>


**Main.class**

```java
      public static void main(String[] args) {
        DispenseChain c1 =  new Won100Dispenser();
        DispenseChain c2 = new Won10Dispenser();
        DispenseChain c3 = new Won1Dispenser();

        c1.setNextChain(c2);
        c2.setNextChain(c3);

        c1.dispense(new Currency(378));
    }
```

<br/>

```java
>Dispensing 3 100₩ note
>Dispensing 7 10₩ note
>Dispensing 8 1₩ note

```







<br/><br/>

## 장단점


- 장점
  - 결합도를 낮추며, 요청의 발신자와 수신자를 분리시킬 수 있다
  - 클라이언트는 처리객체의 집합 내부의 구조를 알 필요가 없다
  - 집합 내의 처리 순서를 변경하거나 처리객체를 추가 또는 삭제할 수 있어 유연성이 향상된다
  - 새로운 요청에 대한 처리객체 생성이 매우 편리해진다
  
  
  


- 단점
  -  충분한 디버깅을 거치지 않았을 경우 집합 내부에서 사이클이 발생할 수 있다
  - 디버깅 및 테스트가 쉽지 않다


<br/><br/><br/><br/>

---
참조:
- [[디자인 패턴] 책임 연쇄 패턴(Chain of Responsibility Pattern)](https://always-intern.tistory.com/1)
- [책임 연쇄 패턴(chain-of-responsibility pattern)](https://k0102575.github.io/articles/2020-02/chain-of-responsibility-pattern)

