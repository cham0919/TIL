


  
  ## 들어가기 전에
  
  - 영속성 컨텍스트를 보기 전 알아야할 개념들이 있다.
  
    ![](https://images.velog.io/images/cham/post/aa30cb4a-9c40-4d1e-aed9-500a2548689e/image.png)
  
  
  - 위의 이미지를 보면 알다시피, 그동안 conn을 직접 관리하던 것을 EntityManager가 대신 해주는 것을 알 수 있다.
  - 이 EntityManager는 EntityManagerFactory에서 생성을 해주며, 사용자는 생성된 EntityManager를 통해 db에 접근을 할 수 있다.
  - 전반적인 흐름은 위와 같다. 
  
  <br/>
  
## 1. EntityManagerFactory
 
 <br/>
  
  - application loading 시점에 DB 당 딱 하나만 생성되어야 한다
  - WAS 가 종료되는 시점에 EntityManagerFactory 를 닫는다.
그래야 내부적으로 Connection pooling 에 대한 Resource 가 Release 된다.
 <br/><br/>
ex)
persistence.xml

``` 
<persistence-unit name="hello">
        <properties>
            <!-- 필수 속성 -->
            <property name="javax.persistence.jdbc.driver" value="org.mariadb.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.user" value="wcp_admin"/>
            <property name="javax.persistence.jdbc.password" value="1234"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/wcp?serverTimezone=UTC"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL5Dialect"/>
```

<br/>
위의 persistence-unit의 name기반으로 EntityManagerFactory 생성이 가능하다

<br/>


```
EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");
```

<br/>

## 2. EntityManager

<br/>

- EntityManagerFactory를 통해 생성이 가능하다.

```
   EntityManager entityManager = entityManagerFactory.createEntityManager();
```

<br/>

- JPA는 스레드가 하나 생성될 때 마다(Transaction 단위를 수행할 때마다) EntityManagerFactory에서 EntityManager를 생성한다.

- EntityManager는 내부적으로 DB 커넥션 풀을 사용해서 DB에 붙는다.


- Transaction 수행 후에는 내부적으로 DB Connection 을 반환하기 위해 반드시 EntityManager 를 닫는다.
<br/>

```
    entityManager.close();
```

<br/>

## 3. EntityTransaction

<br/>

- Data 를 변경하는 모든 작업은 반드시 Transaction 안에서 이루어져야 한다.

- EntityManager를 통해 EntityTransaction를 관리할 수 있다.



```
    EntityTransaction tx = entityManager.getTransaction();
```


- tx.begin(); : Transaction 시작
- tx.commit(); : Transaction 수행
- tx.rollback(); : 작업에 문제가 생겼을 시

<br/><br/>

## 4. 영속성 컨텍스트란?
<br/>

>엔티티를 영구 저장하는 환경


- 위의 EntityManager를 사용해 Entity를 변경한다해서 바로 DB에 data가 적재되는 것은 아니다. 

- commit()를 하기 전 즉, 하나의 Transaction 사이에서 실행되는 Entity 정보들은 이 영속성 컨텍스트(Persistence Context)라는 곳에 먼저 저장된다.

![](https://images.velog.io/images/cham/post/f7db3edb-0e4d-4ee0-9056-f81c3ff5c188/image.png)


- 스프링에서 EntityManager를 주입받아서 사용하면, 같은 트랜잭션의 범위 내에 있는 EntityManager는 동일 영속성 컨텍스트에 접근한다.






<br/>

##  영속성 컨텍스트를 사용하는 이유
 
 
<br/>

- 그러면 영속성 컨텍스트를 왜 사용할까? 

- 영속성 컨텍스트를 사용함으로써 얻을 수 있는 이점을 알아보자


 <br/>

###  1. 1차 캐시
 
 
 
 
![](https://images.velog.io/images/cham/post/0ca8eb9e-cfa6-445c-8e15-974b66b6dfed/image.png)


- 영속성 컨텍스트 내부에는 위와 같이 1차 캐시라는 공간이 있다.

- persist()를 한다고 해서 바로 DB에 insert문이 날아가는게 아닌, 해당 1차 캐시 공간에 저장된다.

- 이렇게 된다면, 특정 Entity에 대한 삽입과 조회가 동시에 일어나게 된다면 DB에 insert문와 select문을 실행해 DB에 접근할 필요 없이 단순히 이 1차 캐시에서 Entity를 가져오기만 하면 되는 것이다.

![](https://images.velog.io/images/cham/post/5b0ef062-c160-4f54-abf6-0009e98e7249/image.png)

- 단, 1차 캐시는 한 Transaction의 생명주기가 같으므로 눈에 띄는 큰 속도 이점은 없다고 볼 수 있다.

<br/> <br/>

 ### 2. 동일성 보장
 
<br/>

- DB에서 조회한 Data를 기반으로 새로운 Entity를 생성하는 것이 아닌, 1차 캐시에서 삽입, 조회를 하므로, JCF같이 동일성 보장이 된다.



```
   User findUser1 = em.find(User.class, "user1");
   User findUser2 = em.find(User.class, "user2");
   System.out.println(findUser1 == findUser2); // true
```
 
 -  같은 객체를 바라보고 있다.
 

<br/> <br/>


 ###  3. 쓰기 지연
 
 <br/>
 

- Entity들을 1차 캐시에 저장할 때, 저장할 뿐만 아니라 쓰기 지연 SQL 저장소라는 곳에 해당 SQL문도 함께 저장한다.


![](https://images.velog.io/images/cham/post/e35818bd-50c6-4da9-8e5d-40511de302ef/image.png)


- 미리 SQL문들을 저장하고 있다가, 트랜잭션 커밋을 실행할 때, 미리 저장해두었던 SQL문들을 한번에 실행한다.

- 즉, 버퍼링같은 기능. 한번에 모았다가 한번에 실행



<br/> <br/>


 ###  4. 변경 감지
 
 <br/>

- jpa에서 update를 할 때, 따로 update()나 modify()가 없다.

```

    Member member = entityManager.find(Member.class, 1l)
    member.setName("updateName");
```


- find()로 member를 조회한 다음 data를 변경하기만 하면 이를 감지해, commit()할 때 update문이 알아서 생성되어 날아간다.

- 이는 아래와 같은 흐름덕분에 가능하다.

![](https://images.velog.io/images/cham/post/8f780bd7-1c2e-4695-a2a1-5d52689fe6fc/image.png)


- 1차 캐시에는 스냅샷이라는 공간이 있다. 
  - snapshot : 1차(최초)로 객체가 들어왔을 시점을 저장
  - Entity : 실제 저장된 Entity
  
- flush()가 실행될 때, 먼저 Entity와 스냅샷을 비교한다.

- 위의 코드에서는 Name을 변경했기에, Entity의 Name과 스냅샷의 Name이 다른 것을 감지해 자동으로 Update문을 생성하는 것이다.


<br/><br/>

## 영속성 컨텍스트(Persistence Context) 라이프사이클


<br> 영속성 컨텍스트에는 총 4가지의 LifeCycle이 존재한다.

<br/>

### 1. 비영속 상태 (new/transient)


- 영속성 컨텍스트와 상관 없는 상태
- JPA와 상관없이 객체만 생성한 상태를 말한다.


ex)
```
  Member member = new Member();
  member.setId("1234");
  ...
```

<br/>

### 2. 영속 상태 (managed)

- 영속성 컨텍스트에 관리되고 있는 상태
- 객체 생성한다음 persist() 하는 순간 영속상태가 된다.
- commit하는 시점에 영속성 컨텍스트에 영속화되어있는 것에 대한 쿼리가 날아가게 된다

ex)
```
  Member member = new Member();
  member.setId("1234");
  ...
  em.persist(member);
```


<br/>

### 3. 준영속 상태 (detached)

- 영속성 컨텍스트에 저장되었다가 분리된 상태
- 영속성 컨텍스트에서 지운 상태

ex)
```
  Member member = new Member();
  member.setId("1234");
  ...
  em.persist(member);
  ...
  em.detach(member); // 영속성 컨텍스트에서 분리. 영속성만 삭제
  em.clear(); // 영속성 컨텍스트를 완전히 초기화
  em.close(); // 영속성 컨텍스트를 종료
```
<br/>

### 4. 삭제 상태 (removed)

- 삭제된 상태
- detached와 다르게 remove는 DB와 동기화되어 DATA까지 삭제된다.

ex)
```
  Member member = new Member();
  member.setId("1234");
  ...
  em.persist(member);
  ...
  em.remove(member); // db에서까지 삭제
```







<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>   

 출처 및 참고
 ---
 
 - https://velog.io/@seho100/JPA-%EC%98%81%EC%86%8D%EC%84%B1-%EC%BB%A8%ED%85%8D%EC%8A%A4%ED%8A%B8
 - https://gmlwjd9405.github.io/2019/08/06/persistence-context.html
 - https://www.inflearn.com/course/ORM-JPA-Basic/dashboard
 - https://www.inflearn.com/course/ORM-JPA-Basic/dashboard


