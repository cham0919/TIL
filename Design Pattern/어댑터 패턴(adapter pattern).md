# 어댑터 패턴


- 구조(Structural) 패턴 


- 서로 다른 인터페이스를 가진 두 클래스를 어댑터 클래스로 인터페이스를 통일 시켜 사용하는 패턴


- 어댑터를 이용하면 인터페이스 호환성 문제 때문에 같이 쓸 수 없는 클래스들을 연결해서 쓸 수 있다.






<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/4266aae1-52fa-4f6d-ac80-b7a0ceff7202/image.png)


- WebClient
  - 요청이 들어오면 ```doWork()```를 호출한다
- WebRequester
  - WebClient가 ```doWork()```를 호출하면 작업을 위임할 Class의 Interface
- WebAdapter
  - 서드파티와 클라이언트를 연결해주는 어댑터
- FancyRequester
  - 실 작업을 수행할 Class



<br/><br/>

## 구현



<br/>


**WebRequester.class**

```java
public interface WebRequester {
    void requestHandler();
}
```


- 작업 요청 처리를 담당하는 인터페이스 


<br/><br/>

**WebClient.class**

```java
public class WebClient {
    private WebRequester webRequester;

    public WebClient(WebRequester webRequester) {
        this.webRequester = webRequester;
    }

    public void doWork() {
        webRequester.requestHandler();
    }
}
```


- 요청이 들어와 작업하면(```doWork()```)  ```requestHandler()```를 호출한다

<br/><br/>



**WebAdapter.class**

```java
public class WebAdapter implements WebRequester {
    private FancyRequester fancyRequester;

    public WebAdapter(FancyRequester fancyRequester) {
        this.fancyRequester = fancyRequester;
    }

    @Override
    public void requestHandler() {
        fancyRequester.fancyRequestHandler();
    }
}

```


- 실 작업을 수행할 Class와 연결을 담당한다


<br/><br/>


**FancyRequester.class**

```java
public class FancyRequester {
    public void fancyRequestHandler() {
        System.out.println("Yay! fancyRequestHandler is called!");
    }
}


```

- 실 작업을 구현하는 클래스

<br/><br/>

**Main.class**

```java
public class Main {

    public static void main(String[] args) {
             WebAdapter adapter = new WebAdapter(new FancyRequester());
        WebClient client = new WebClient(adapter);
        client.doWork();
    }
}
```

<br/>


```java
Yay! fancyRequestHandler is called!
```

- 정상적으로 호출되는 것을 볼 수 있다.
- 차후 다른 모듈로 변경해야하는 상황이 왔을 때, 따로 구현부를 만들어 어댑터와 연결하면 수정사항에 대해 유연하게 대처할 수 있다.

<br/><br/>



<br/><br/><br/>




## 장단점


- 장점
  - 기존 클라이언트 단의 코드 수정이 최소화된다
  - 클라이언트는 연동부분을 몰라도, 새로운 코드의 기능을 일관되게 사용가능하다



- 단점
  - 어댑터 클래스에서 통일 시켜주는 부분을 하나씩 구현해야 한다


<br/><br/><br/><br/>

---
참조:
- [디자인패턴 - 어댑터 패턴](https://yaboong.github.io/design-pattern/2018/10/15/adapter-pattern/)
- [[디자인 패턴 6편] 구조 패턴, 어댑터(Adapter)](https://dailyheumsi.tistory.com/189)
