# 1. Entity & DTO 구분

  
<br/>

### 1.1 용도

용도부터가 다르다. Dto는 데이터 전달을 위한 View Layer에 속한 클래스로 변경 가능성이 다분하나, Entity는 DB Layer로 변경 가능성이 적은편에 속합니다. 이 둘을 구분짓지 않고 사용한다면, 전체 Layer에서 Entity에 대한 의존성이 높아져, 차후 수정사항에 대해 민접하게 대처하기 힘들어질 것이다.

### 1.2 잠재적 이슈

Controller에서 데이터를 전달할 때, Json 직렬화를 주로 사용한다. 

이때, Entity에 무분별한 Json 직렬화를 사용한다면 무한 참조 루프가 발생할 가능성이 높다.
 
또한 직렬화를 하며 Fetch Lazy한 필드까지 모두 참조한다는 것인데 이렇게 되면 Lazy Loading의 이점을 살릴 수가 없기도 하다.
 

<br/><br/>

# 2. 구분법

<br/>

### 1.1 Controller와 Service


View Layer에서 데이터 전달을 담당하는 Dto는 Controller, DB Layer에  속해 비즈니스적 로직에 필요한 Entity는 Service에서 사용하는 것을 권장한다. 


<br/>

### 1.2 Mapping 계층


>A Service Layer defines an application’s boundary [Cockburn PloP] and its set of available operations from the perspective of interfacing client layers. It encapsulates the application’s business logic, controlling transactions and coor-dinating responses in the implementation of its operations. - Martin Fowler


마틴 파울러는 Service Layer란 어플리케이션의 경계를 정의하고 비즈니스 로직 등 도메인을 캡슐화하는 역할이라고 정의한다. 

도메인 Model 즉, Entity를 표현 계층(Controller)에서 사용하는 경우 결합도가 증가하여, 도메인의 변경이 Controller의 변경을 촉발하는 유지보수의 문제로 이어질 수 있다.
 
 
이런 관점으로 볼 때, Layer간 데이터 전달 목적으로 DTO를 엄격하게 고수한다면 Entity와 Dto간의 Mapping이 이뤄지는 계층은 Service 레이어에서 정의되어야 한다는 의견이 존재한다.
 
요청에 대한 응답 역시 Service 레이어의 일부분이기 때문이다.
  
<br/>

#### 1.2.1 Controller Dto를 사용하는 경우

- View에 반환할 필요가 없는 데이터까지 포함되어 Controller(표현 계층)까지 넘어온다.

- Controller가 여러 Entity들의 정보를 조합해서 DTO를 생성해야 하는 경우, 결국 Service(응용 계층) 로직이 Controller에 포함되게 된다.

- 여러 Entity들을 조회해야 하기 때문에 하나의 Controller가 의존하는 Service의 개수가 비대해진다.


 위와 같은 단점들이 Service에서 사용하게 되면 해결될 수 있다.
 
 



<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [Entity, DTO, VO 바로 알기](https://velog.io/@gillog/Entity-DTO-VO-%EB%B0%94%EB%A1%9C-%EC%95%8C%EA%B8%B0)
- [2) 스프링부트로 웹 서비스 출시하기 - 2. SpringBoot & JPA로 간단 API 만들기](https://jojoldu.tistory.com/251)
