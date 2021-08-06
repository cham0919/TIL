
  

## ORM


> ORM은 Object-Relational Mapping으로 객체와 관계형 데이터베이스의 데이터를 자동으로 매핑(연결)해주는 프로그래밍 기법이다

 - ORM은 객체와 관계형 DB를 매핑해주는 프로그래밍 기법으로 전체를 아우르는 개념이었다.
 
 <br/>
 
### ORM은 알겠는데 왜 쓰는거지

<br/>
장단점이 있는데, 간단하게만 정리하자면 다음과 같다

- 장점
  - 객체 지향적인 코드로 인해 더 직관적이고 비즈니스 로직에 더 집중할 수 있게 도와준다
  - 재사용 및 유지보수의 편리성이 증가한다
  
 <br> 
 - 단점
   - 프로젝트의 복잡성이 크면 구현하는 난이도가 상승
   - 잘못 구현한 경우 성능이 저하
     - 이 부분은, 그렇게 큰 차이가 없다고 하는 의견도 많다
     
     
 <br/><br/>
  
개인적으로 관계형에 종속되지 않고 객체 지향적으로 설계를 할 수 있다는 것이 가장 큰 장점이라고 생각한다.
객체 지향와 관계형은 상속, 연관관계, 데이터 타입부터가 다른데, 중심이 관계형에 얽메이지 않고 객체 지향적으로 설계를 할 수 있다는 것은 아무도 무시할 수 없을 장점일 것이다.


  
 
 <br/> <br/>
 
## 🍻 JPA는 뭔데




>Java Persistence API의 약어로 자바 플랫폼 SE와 자바 플랫폼 EE를 사용하는 응용프로그램에서 관계형 데이터베이스의 관리를 표현하는 자바 API이다.



<br/>

### JPA는 표준 인터페이스다

<br/>

- JPA는 ORM을 사용하기 위한 표준 인터페이스를 모아둔 것이다.
- JPA는 특정 기능을 하는 라이브러리가 아니다.
- javax.persistance 로서 구현체가 없다는 것이 중요하다


 <br/> <br/>
 
## 🥴 Hibernate는 뭔데
 
 
 
 >Hibernate는 JPA 명세의 구현체이다. 
 
 - javax.persistence.EntityManager와 같은 JPA의 인터페이스를 직접 구현한 라이브러리이다.
 - JPA와 Hibernate는 마치 자바의 interface와 해당 interface를 구현한 class와 같은 관계이다
 - 즉, JPA를 사용한다고해서 굳이 Hibernate를 사용할 필요는 없다고 생각할 수도 있다
 
 <br/>
 
재밌는건 사실 Hibernate가 JPA보다 먼저 나왔다는 것이다. EJB를 사용하던 시절, 한 개발자가 자기가 만들어도 이것보단 잘 만들겠다며 뚝딱하고 나온게 Hibernate.
Hibernate가 각광받자 Java진영에서 표준을 만들고 Hibernate 개발자를 모셔와 나온게 JPA.
 EJB는 얼마나 거지같았던걸까


 
 
 <br/><br/>
 
## 😵 번외 Spring Data JPA
 
 
 >JPA를 쓰기 편하게 만들어놓은 모듈이다
 
 
 
 - 이름에서부터 알 수 있듯이 Spring에서 제공해주는 모듈이다
 - JPA를 한 단계 추상화시킨 Repository라는 인터페이스를 제공한다
 - 이 Repository를 사용함으로써, 우리는 굳이 EntityManager를 직접 다룰 필요가 없다
 
 
  <br/> <br/>
 
 
 ### 요약
 
 ![](https://images.velog.io/images/cham/post/8ddb5937-ce34-4a38-b27e-19a928481de0/image.png)
 
 
 
 앞으로 누가 ORM, Hibernate, JPA 차이를 물어본다면 자신있게 대답하고 술 한잔 해야겠다.
 
 
 
 
 
 
  <br/> <br/> <br/> <br/><br/> <br/> <br/> <br/>
  출저 및 참고
 https://gmlwjd9405.github.io/2018/12/25/difference-jdbc-jpa-mybatis.html
 https://cornswrold.tistory.com/317
 https://gmlwjd9405.github.io/2019/02/01/orm.html 
 https://github.com/WeareSoft/tech-interview/blob/master/contents/db.md#%EC%9E%A5%EC%A0%90---%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80
 https://suhwan.dev/2019/02/24/jpa-vs-hibernate-vs-spring-data-jpa/