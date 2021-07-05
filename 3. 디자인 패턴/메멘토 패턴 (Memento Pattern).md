# 메멘토 패턴(Memento Pattern)


- 행위(Behavioral) 패턴
 


-  객체의 상태 정보를 가지는 클래스를 따로 생성하여, 객체의 상태를 저장하거나 이전 상태로 복원할 수 있게 해주는 패턴

-  원하는 시점의 상태 복원이 가능하다



<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/389b43d4-85ca-4333-970f-30a8970ede03/image.png)







<br/><br/>


## 구현




<br/>


**Originator.class**

```java
public class Originator {
    private String state;

    public void setState(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public Memento saveStateToMemento(){
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento){
        state = memento.getState();
    }
}
```

- Originator : 현재 State를 가지고, Memento 객체와 Memento 객체 상태를 얻게 한다

<br/><br/>


**Memento class**

```java
public class Memento {
    private String state;

    public Memento(String state){
        this.state = state;
    }

    public String getState(){
        return state;
    }
}


```

- Memento : State를 가지고 있는 인스턴스



<br/><br/>


**CareTaker.class**

```java
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
```


- CareTaker  : Memento를 순서대로 저장한다

<br/><br/>


**Main.class**

```java
 public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State #4");

        System.out.println("Current State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState());
    }
```

<br/>

```java
>>>Current State: State #4
>>>First saved State: State #2
>>>Second saved State: State #3
```


 - 저장한 status를 불러올 수 있다




<br/><br/>

## 장단점


- 장점
  - 저장된 상태를 핵심 객체와는 다른 별도의 객체에 보관하기 때문에 안전하다
  - 핵심 객체의 데이터를 계속해서 캡슐화된 상태로 유지할 수 있다
  - 복구 기능을 구현하기 쉽다

 

- 단점
  - 이전 상태의 객체를 저장하기 위한 Originator가 클 경우 많은 메모리가 필요하다
  - 상태를 저장하고 복구하는 데 시간이 오래 걸리 수 있다
     - 자바 시스템에서는 시스템의 상태를 저장할 때 직렬화를 사용하는 것이 좋다
  


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] Memento Pattern](https://beomseok95.tistory.com/283)
- [[디자인 패턴] 메멘토 패턴(Memento Pattern)](https://always-intern.tistory.com/6?category=1036393)
- [[행위 패턴] Memento pattern (메멘토 패턴)](https://kunoo.tistory.com/entry/%ED%96%89%EC%9C%84-%ED%8C%A8%ED%84%B4-Memento-pattern-%EB%A9%94%EB%A9%98%ED%86%A0-%ED%8C%A8%ED%84%B4)
- [기타 여러가지 패턴들 정리](https://thefif19wlsvy.tistory.com/50)


