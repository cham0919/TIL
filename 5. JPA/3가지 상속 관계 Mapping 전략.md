






# 1. 상속 관계 매핑



 관계형 데이터베이스에서는 객체 지향에서 사용되는 상속이라는 개념이 없다. 대신 슈퍼타입 서브타입 관계라는 모델링 기업이 존재하는데 이 기법이 그나마 상속 개념과 흡사하다.
 이를 이용하여 객체의 상속 구조와 데이터베이스의 슈퍼타입 서브타입 관계를 매핑하는 것을 상속관계 매핑이라 하는데, 여기서 간단히 정리해보려 한다.
  
<br/>

### 1.1 조인 전략


- 엔티티 각각을 테이블로 만들고 자식 테이블이 부모 테이블의 기본 키를 받아 기본 키 + 외래 키로 사용하는 전략
- 자식 테이블 중 어느 테이블을 조회해야하는지 구분하기 위해 DTYPE이란 구분 컬럼을 사용한다

![](https://images.velog.io/images/cham/post/53e6d766-f2d8-4595-aeb7-ccd4cbdd9bb6/image.png)


구현 예제

ex)
```java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @GeneratedValue @Id
    private Long id;
...

```

```java
@Entity
@DiscriminatorValue("TEST")
@PrimaryKeyJoinColumn(name = "Album_ID")
public class Album extends Item{

    private String artist;

}
...

```



- ```@Inheritance(strategy = InheritanceType.JOINED) ``` 
  - 부모 클래스에 지정. 조인 전략이므로 InheritanceType.JOINED 설정
- ```@DiscriminatorColumn(name = "DTYPE")``` 
  - 구분 컬럼 지정. Default값이 DTYPE이므로 name 속성은 생략 가능
- ```@DiscriminatorValue("TEST")```
  - 구분 컬럼에 입력할 값 지정. Default값으로 엔티티 이름 사용
- ```@PrimaryKeyJoinColumn(name = "Album_ID")```
  - Default로 자식 테이블은 부토 테이블 id 컬럼명을 그대로 사용하나, 변경시 해당 설정값 추가 


- 장점
   - 테이블의 정규화
   - 외래 키 참조 무결성 제약조건 활용 가능
   - 저장공간을 효율적으로 사용 가능


- 단점
   - 조회시 잦은 조인으로 인해 성능 저하 가능성
   - 복잡한 조회 쿼리
   - 데이터 등록 시, 두번 실행되는 INSERT문



<br/>

### 1.2 단일 테이블 전략


- 하나의 테이블을 사용하며 구분 컬럼(DTYPE)을 활용해 데이터를 활용하는 전략


![](https://images.velog.io/images/cham/post/55a73a0e-dc74-41dd-bbe2-3dfe39f64414/image.png)


구현 예제

ex)
```java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @GeneratedValue @Id
    private Long id;
...

```

```java
@Entity
@DiscriminatorValue("TEST")
public class Album extends Item{

    private String artist;

}
...

```



- ```@Inheritance(strategy = InheritanceType.SINGLE_TABLE) ``` 
  - 부모 클래스에 지정. 단일 테이블 전략이므로 InheritanceType.SINGLE_TABLE 설정



- 장점
   - 조인이 사용되지 않아 빠른 조회 성능
   - 단순한 조회 쿼리
   


- 단점
   - 자식 엔티티가 매핑한 컬럼은 모두 NULL 허용
   - 높은 테이블이 커질 가능성으로 인해 오히려 조회 성능이 안좋아질 수 있음
  



<br/>

### 1.3 구현 클래스마다 테이블 전략


- 자식 엔티티마다 테이블 생성하는 전략
- 추천하지 않는 전략

![](https://images.velog.io/images/cham/post/4c1e96fb-3ca2-4353-87c4-1a2538b2f65a/image.png)

구현 예제

ex)
```java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @GeneratedValue @Id
    private Long id;
...

```

```java
@Entity
@DiscriminatorValue("TEST")
public class Album extends Item{

    private String artist;

}
...

```



- ```@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) ``` 
  - 부모 클래스에 지정. 구현 클래스마다 테이블 전략이므로 InheritanceType.TABLE_PER_CLASS 설정



- 장점
   - 서브 타입을 구분해서 처리할 때 효과적
   - not null 제약조건 사용 가능
   


- 단점
   - 여러 자식 테이블 함께 조회시 성능 문제(UNION을 사용함)
   - 자식 테이블을 통합해 쿼리가 어려움
  


<br/>

<br/>
<br/><br/><br/><br/><br/><br/><br/><br/>
 
 
 참고 도서

 - https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=62681446
 
