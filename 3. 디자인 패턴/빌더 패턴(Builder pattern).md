# 빌더 패턴


- 생성(Creational) 패턴
 


- 별도의 Builder 클래스를 만들어 값을 입력받은 후 하나의 인스턴스를 리턴하는 방식

-  매개 변수를 각각 받아들이고 모든 매개 변수를 받은 뒤에 이 변수들을 통합해서 한번에 사용하는 패턴



<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/d0dcac25-ed89-447e-8245-2b1c21ad1a26/image.png)





<br/><br/>

## 구현




```java
public class Member {
    private final int id;
    private final String password;
    private final String name;
    private final int age;
    private final String email;
    private final String phone;

    public static class Builder {
        // Required parameters(필수 인자)
        private final int id;

        // Optional parameters - initialized to default values(선택적 인자는 기본값으로 초기화)
        private String password = "";
        private String name = "";
        private int age = 0;
        private String email = "";
        private String phone = "";

        public Builder(int id) {
            this.id = id;
        }

        public Builder password(String password) {
            password = password;
            return this;    // 이렇게 하면 . 으로 체인을 이어갈 수 있다.
        }
        public Builder name(String name) {
            name = name;
            return this;
        }
        public Builder age(int age) {
            age = age;
            return this;
        }
        public Builder email(String email) {
            email = email;
            return this;
        }
        public Builder phone(String phone) {
            phone = phone;
            return this;
        }
        public Member build() {
            return new Member(this);
        }
    }

    private Member(Builder builder) {
        id = builder.id;
        password = builder.password;
        name = builder.name;
        age = builder.age;
        email = builder.email;
        phone = builder.phone;
    }
}
```


- 외부 혹은 내부로 Builder Class를 구현하여, 해당 Builder를 통해 인자값을 전달받은 후, 마지막 builder() 메서드를 호출하여 인스턴스를 반환한다.
- 위와 같이 구현하면 다음과 같이 호출할 수 있다.


```java
  Member member = new Member.Builder(1)
                .name("name")
                .age(27)
                .build();
```


<br/><br/><br/>

#### 참조

```java
@Getter
@Builder
public class Member {
    private final int id;
    private final String password;
    private final String name;
    private final int age;
    private final String email;
    private final String phone;
    ...
```


- lombok을 사용하면 ```@Builder```를 사용하는 것만으로 패턴구현이 가능하다...!

<br/><br/>

## 장단점


- 장점
  - 각 인자가 어떤 의미인지 알기 쉽다.
  - 필요에 따라 setter 메소드를 구현하지 않아 변경 불가능 객체를 만들 수 있다.
  - 한 번에 객체를 생성하므로 객체 일관성이 깨지지 않는다.
  - build() 함수가 잘못된 값이 입력되었는지 검증하게 할 수도 있다.
  


- 단점
  - API는 시간이 지날수록 매개변수가 많아지는 경향이 있으므로 코드가 더 커질 가능성이 있다.
  - 무분별하게 사용할 경우 성능저하의 요인이 될 가능성이 있다.
  
  
<br/><br/><br/>

&nbsp;빌더 패턴에 관련하여 GoF 디자인 패턴의 빌더 패턴과 Effective Java의 빌더 패턴과의 개념 충돌로 여러 가지 의견들이 나오는 것을 볼 수 있다.
 &nbsp;업무에서는 좀 더 Effective Java에서 설명하는 개념에 근거하여 사용을 했지만 다른 개념도 있다하니 이는 차후에 각자 공부하여 내용을 추가하는 걸로,,







<br/><br/><br/><br/>

---
참조:
- [빌더 패턴(Builder Pattern)](https://jdm.kr/blog/217)
- [자바 빌더 패턴 (Java Builder Pattern) 장단점](https://gofnrk.tistory.com/60)
- [빌더 패턴(Builder Pattern)](https://gmlwjd9405.github.io/2017/10/01/basic-concepts-of-development-designpattern.html)
