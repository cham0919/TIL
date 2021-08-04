# 커맨드 패턴 (Command Pattern)


- 행위(Behavioral) 패턴
 


-  실행될 기능을 캡슐화함으로써 주어진 여러 기능을 실행할 수 있는 재사용성이 높은 클래스를 설계하는 패턴


-  이벤트가 발생했을 때 실행될 기능이 다양하면서도 변경이 필요한 경우에 이벤트를 발생시키는 클래스를 변경하지 않고 재사용하고자 할 때 유용하다





<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/db5b302d-aa11-4479-9ced-d1e937567dcf/image.png)

 - Command
   - 실행될 기능에 대한 인터페이스
   - 실행될 기능을 execute 메서드로 선언함
 - ConcreteCommand
   - 실제로 실행되는 기능을 구현
   - 즉, Command라는 인터페이스를 구현함
 - Invoker
   - 기능의 실행을 요청하는 호출자 클래스
 - Receiver
   - ConcreteCommand에서 execute 메서드를 구현할 때 필요한 클래스
   - 즉, ConcreteCommand의 기능을 실행하기 위해 사용하는 수신자 클래스







<br/><br/>


## 구현


 만능 버튼 만들기

<br/>

![](https://images.velog.io/images/cham/post/2d10885c-92ff-4a2e-ae28-41c70255b010/image.png)


**Command.class**

```java
public interface Command {
    void execute();
}
```

- execute() 선언


<br/><br/>


**Button.class**

```java
public class Button {

    private Command theCommand;

    public Button(Command theCommand) {
        setCommand(theCommand);
    }

    public void setCommand(Command newCommand) {
        this.theCommand = newCommand;
    }

    public void pressed() {
        theCommand.execute();
    }
}
```

- 생성자를 통해 필요한 커맨드를 받아 ```pressed()```를 통해 실행한다

<br/><br/>

**Lamp.class**

```java
public class Lamp {

    public void turnOn() {
        System.out.println("Lamp On");
    }
}
```

<br/>


**LampOnCommand.class**

```java
public class LampOnCommand implements Command {
    private Lamp theLamp;

    public LampOnCommand(Lamp theLamp) {
        this.theLamp = theLamp;
    }

    public void execute() {
        theLamp.turnOn();
    }
}
```

- Lamp를 켜는 클래스


<br/>


**Alarm.class**

```java
public class Alarm {
    public void start(){ System.out.println("Alarming"); }
}
```

<br/>


**AlarmStartCommand.class**

```java
public class AlarmStartCommand implements Command {

    private Alarm theAlarm;

    public AlarmStartCommand(Alarm theAlarm) { this.theAlarm = theAlarm; }

    public void execute() { theAlarm.start(); }
}
```

- 알람을 켜는 클래스

<br/><br/>


**Main.class**

```java
    public static void main(String[] args) {
        Lamp lamp = new Lamp();
        Command lampOnCommand = new LampOnCommand(lamp);
        Alarm alarm = new Alarm();
        Command alarmStartCommand = new AlarmStartCommand(alarm);

        // 각 상황에 맞춰 커맨드 입력
        Button button1 = new Button(lampOnCommand);
        button1.pressed();

        Button button2 = new Button(alarmStartCommand);
        button2.pressed();
        button2.setCommand(lampOnCommand);
        button2.pressed();
    }

```


<br/>

실행결과
```java
>>>Lamp On
>>>Alarming
>>>Lamp On
```

- 각 필요한 커맨드를 입력해 사용한다






<br/><br/>

## 장단점


- 장점
  -  작업을 수행하는 객체(리시버)와 작업을 요청하는 객체를 분리하기 때문에 SRP 원칙을 잘 지켜낸다.
  - 기존 코드 수정 없이 새로운 리시버, 명령어 추가가 가능하기 때문에 OCP 원칙을 잘 지켜낸다.
  - 커맨드 단위의 별도의 액션(undo, redo) 등이 가능하고, 커맨드 상속 및 조합을 통해 더 정교한 커맨드를 구현할 수 있다.


- 단점
  - 전체적으로 이해가 필요하고 복잡한 설계구조를 가진다.


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 커맨드 패턴이란](https://gmlwjd9405.github.io/2018/07/07/command-pattern.html)
- [[디자인 패턴 16편] 행동 패턴, 커맨드 (Command)](https://dailyheumsi.tistory.com/217)


