# 중재자 패턴(Mediator Pattern)


- 행위(Behavioral) 패턴
 


-  모든 클래스간의 복잡한 로직(상호작용)을 캡슐화하여 하나의 클래스에 위임하여 처리하는 패턴


-  M:N의 관계에서 M:1의 관계로 복잡도를 떨어뜨려 유지 보수 및 재사용의 확장성에 유리한 패턴






<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/b2fca9b2-5f13-483f-b749-d1768d9285e4/image.png)

 - Mediator 
   - 여러 Component 중재해주는 인터페이스를 가지고 있는 추상 클래스 객체
 - ConcreteMediator 
   - Component 객체들을 가지고 있으면서 중재해주는 역할을 하는 객체
 - Component 
   - Mediator 객체에 의해서 관리 및 중재를 받을 기본 클래스 객체들







<br/><br/>


## 구현


 채팅 구현하기

<br/>

![](https://images.velog.io/images/cham/post/af5a2a5b-43b4-4864-a9bc-271346ada0a8/image.png)


**Colleague.class**

```java
public abstract class Colleague {
    private Mediator mediator;
    protected String name;
    private String message;

    public Colleague(String name){
        this.name = name;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String getName() {
        return this.name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

    public void send() {
        System.out.println(this.name + " send()");
        System.out.println();
        mediator.mediate(this);
    }

    public abstract void receive(Colleague colleague);
}
```

<br/>

- 다른 객체와 통신을 하는 요소다
- 예제의 User, Admin, System의 추상적인 요소에 해당한다. 통신에 필요한 메서드를 가지고 있다
- 다른 객체와의 통신은 직접하지 않으며, Mediator 객체를 통해 요청을 하는 방식으로 통신한다


<br/><br/>


**ConcreteColleague1.class**

```java
// User
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(String name) {
        super(name);
    }

    @Override
    public void receive(Colleague colleague) {
        System.out.println(this.name + " recevied " + colleague.getName() + "'s Message : " + colleague.getMessage());
    }
}
```

<br/>

**ConcreteColleague2.class**

```java
// System
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(String name) {
        super(name);
    }

    @Override
    public void receive(Colleague colleague) {
        System.out.println("System can't receive messages");
    }
}
```

<br/>

**ConcreteColleague3.class**

```java
// Admin
public class ConcreteColleague3 extends Colleague {

    public ConcreteColleague3(String name) {
        super(name);
    }

    @Override
    public void receive(Colleague colleague) {
        System.out.println("Admin can't receive messages");
    }
}
```

<br/>


- 예제의 User, Admin, System에 해당하는 클래스다
- Colleague클래스를 상속받아 통신에 필요한 메서드를 구현해야 한다
- User는 메시지를 받을 수 있으나, Admin/System은 보내는 것만 가능하다


<br/><br/>

**Mediator.class**

```java
public interface Mediator {
    void addColleague(Colleague colleague);
    void mediate(Colleague colleague);
}
```

<br/>

- 객체간의 통신을 중재하는 클래스다
- 통신의 대상이 되는 Colleague를 추가하는 메서드가 존재해야 한다

<br/><br/>


**ConcreteMediator.class**

```java
public class ConcreteMediator implements Mediator {
    private List<Colleague> colleagueList;

    public ConcreteMediator() {
        this.colleagueList = new ArrayList<Colleague>();
    }

    @Override
    public void addColleague(Colleague colleague) {
        this.colleagueList.add(colleague);
    }

    @Override
    public void mediate(Colleague colleague) {
        for(Colleague receiverColleague : colleagueList) {
            System.out.println("\tMediating " + colleague.getName() + " to " + receiverColleague.getName());
            receiverColleague.receive(colleague);
        }
    }
}
```

<br/>

- Mediator를 구현하는, 실질적으로 중재하는 로직을 가지는 클래스다
- 서로 통신하는 객체들을 가지고있어야 하며, 특정 Colleague로부터 요청이 들어오면, 상대 Colleague를 찾아서 해당 메시지를 전달한다
- Mediator는 Colleague에 대한 레퍼런스를 가지고 있고 Colleague는 Mediator에 대한 레퍼런스를 가지고 있다.
- 즉, 양방향 연관관계(Bi-Directional Associations)를 가진다.


<br/><br/>



**Main.class**

```java
        public static void main(String args[]) {
        Mediator mediator1 = new ConcreteMediator();
        Colleague colleague1 = new ConcreteColleague1("User1");
        Colleague colleague2 = new ConcreteColleague1("User2");
        Colleague colleague3 = new ConcreteColleague2("System");
        Colleague colleague4 = new ConcreteColleague3("Admin");

        colleague1.setMediator(mediator1);
        colleague2.setMediator(mediator1);
        colleague3.setMediator(mediator1);
        colleague4.setMediator(mediator1);

        mediator1.addColleague(colleague1);
        mediator1.addColleague(colleague2);
        mediator1.addColleague(colleague3);
        mediator1.addColleague(colleague4);

        colleague1.setMessage("I'm User1");
        colleague1.send();
    }
```


<br/>

실행결과
```java
>>>User1 send()

>>>	Mediating User1 to User1
>>>User1 recevied User1's Message : I'm User1
>>>	Mediating User1 to User2
>>>User2 recevied User1's Message : I'm User1
>>>	Mediating User1 to System
>>>System can't receive messages
>>>	Mediating User1 to Admin
>>>Admin can't receive messages
```


<br/><br/>

## 장단점


- 장점
  - 효율적인 자원 관리(리소스 풀등)를 가능하게 한다
  - 객체간의 통신을 위해 서로간에 직접 참조할 필요가 없게 한다
  - 객체들 간 수정을 하지않고 관계를 수정할 수 있다


- 단점
  - 특정 application 로직에 맞춰져있기 때문에 다른 application에 재사용하기 힘들다 
  - 중재자 패턴 사용 시 중재자 객체에 권한이 집중화되어 굉장히 크며 복잡해지므로, 설계 및 중재자 객체 수정 시 주의해야 한다


<br/><br/><br/><br/>

---
참조:
- [중재자 패턴(Mediator Pattern)](https://www.crocus.co.kr/1542)
- [[디자인패턴] Mediator(중재자) : 복잡한 의존 관계와 로직은 캡슐화](https://joycestudios.tistory.com/44)
- [중재자 패턴(Mediator Pattern) - 자바 디자인 패턴과 JDK 예제](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=2feelus&logNo=220658501378)
- [Mediator Pattern](https://defacto-standard.tistory.com/391)
- [[디자인 패턴] 중재자 패턴(Mediator Pattern)](https://always-intern.tistory.com/5)


