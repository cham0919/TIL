# 스테이트 패턴(State Pattern)


- 행위(Behavioral) 패턴
 


-  일련의 규칙에 따라 객체의 상태(State)를 변화시켜, 객체가 할 수 있는 행위를 바꾸는 패턴

- 객체의 특정 상태를 클래스로 선언하고, 클래스에서는 해당 상태에서 할 수 있는 행위들을 메서드로 정의한다







<br/><br/>

## 구조


![](https://images.velog.io/images/cham/post/da92d255-7d10-4fbb-82b2-56fe449933af/image.png)

- State
  - 시스템의 모든 상태에 공통의 인터페이스
  - 이 인터페이스를 실체화한 어떤 상태 클래스도 기존 상태 클래스를 대신해 교체해서 사용할 수 있다
- State1, State2, State3
  - Context 객체가 요청한 작업을 자신의 방식으로 실제 실행한다
  - 대부분의 경우 다음 상태를 결정해 상태 변경을 Context 객체에 요청하는 역할도 수행한다
- Context
  - State를 이용하는 역할을 수행한다
  - 현재 시스템의 상태를 나타내는 상태 변수 (state)와 실제 시스템의 상태를 구성하는 여러 가지 변수가 있다
  - 각 상태 클래스에서 상태 변경을 요청해 상태를 바꿀 수 있도록 하는 메서드(setState)가 제공된다. 
  - Context 요소를 구현한 클래스의 request 메서드는 실제 행위를 실행하는 대신 해당 상태 객체에 행위 실행을 위임한다.






<br/><br/>


## 구현


 형광등 만들기

<br/>

![](https://images.velog.io/images/cham/post/e8c1de30-3531-4e0f-ad25-9b01d4623e08/image.png)


**State.class**

```java
public interface State{
    void on_button_pushed(Light light);
    void off_button_pushed(Light light);
}
```



<br/>

- 형광등 on/off 기능 선언


<br/><br/>


**ON.class**

```java
public class ON implements State{
    private static ON on = new ON(); // ON 클래스의 인스턴스로 초기화됨
    private ON() { }

    public static ON getInstance() { // 초기화된 ON 클래스의 인스턴스를 반환함
        return on;
    }

    public void on_button_pushed(Light light){ // ON 상태일 때 On 버튼을 눌러도 변화 없음
        System.out.println("반응 없음");
    }

    public void off_button_pushed(Light light){
        light.setState(OFF.getInstance());
        System.out.println("Light Off!");
    }
}
```

<br/>

 
 
**OFF.class**

```java
public class OFF implements State{
    private static OFF off = new OFF();
    private OFF() { }

    public static OFF getInstance() { 
        return off;
    }

    public void on_button_pushed(Light light){ 
        light.setState(ON.getInstance());
        System.out.println("Light On!");
    }

    public void off_button_pushed(Light light){ 
        System.out.println("반응 없음");
    }
}
```

<br/>
 
 - 형광등 상태에 대한 특정 기능들 구현

<br/><br/>

**Light.class**

```java
public class Light{
    private State state;

    public Light(){
        state = OFF.getInstance();
    }

    public void setState(State state){
        this.state = state;
    }

    public void on_button_pushed(){
        state.on_button_pushed(this);
    }

    public void off_button_pushed(){
        state.off_button_pushed(this);
    }
}
```

<br/>

- 원하는 기능들 호출
- 초기값은 OFF로 설정


<br/><br/>


**Main.class**

```java
      public static void main(String[] args) {
        Light light = new Light();
        light.on_button_pushed();
        light.on_button_pushed();
        light.off_button_pushed();
        light.off_button_pushed();
    }
```


<br/>

실행결과
```java
>>>Light On!
>>>반응 없음
>>>Light Off!
>>>반응 없음
```


<br/><br/>

## 장단점


- 장점
  - 하나의 객체에 대한 여러 동작을 구현해야할 때 상태 객체만 수정하므로 동작의 추가, 삭제 및 수정이 간단해진다
  - State 패턴을 사용하면 객체의 상태에 따른 조건문(if/else, switch)이 줄어들어 코드가 간결해지고 가독성이 올라간다
  


- 단점
  -  상태에 따른 조건문을 대신한 상태 객체가 증가하여 관리해야할 클래스의 수가 증가한다


<br/><br/><br/><br/>

---
참조:
- [State Pattern (스테이트 패턴)](https://dev-momo.tistory.com/entry/State-Pattern-%EC%8A%A4%ED%85%8C%EC%9D%B4%ED%8A%B8-%ED%8C%A8%ED%84%B4)
- [[디자인 패턴] 상태 패턴(State Pattern)](https://velog.io/@y_dragonrise/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4-%EC%83%81%ED%83%9C-%ED%8C%A8%ED%84%B4State-Pattern)
- [[디자인 패턴] 상태 패턴(State Pattern)](https://always-intern.tistory.com/9)

