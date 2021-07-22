# 0. 객체지향 쿼리



JPA는 공식적으로 지원하는 객체지향 쿼리 기능이 존재한다.

- JPQL
- Criteria 쿼리 : JPQL을 편하게 작성하도록 도와주는 API, 빌더 클래스 모음
- 네이티브 SQL : JPA에서 JPQL 대신 직접 SQL 사용 가능

공식적이진 않지만 알아둬야할 기능

 - QueryDSL : Criteria 쿼리처럼 JPQL을 편하게 작성하도록 도와주는 빌더 클래스 모음, 비표준 오픈소스 프레임워크
 - JDBC 직접 사용, MyBatis 같은 SQL 매퍼 프레임워크 사용 : 필요하면 JDBC를 직접 사용할 수 있다.



<br/><br/>



# 1. JPQL



 - JPQL은 엔티티 객체를 조회하는 객체지향 쿼리다.
   - 테이블 대상이 아니라 객체를 대상으로 쿼리한다.

 - JPQL은 SQL을 추상화해 특정 데이터베이스에 의존하지 않는다.
   - 즉, DB Vender 설정만 변경하면 모두 호환가능하다. 

- JPQL은 SQL보다 간결하다.
  - 엔티티 직접 조회, 묵시적 조인, 다형성 지원으로 SQL보다 간결하다.
  
<br/>

## 1.1 기본 문법

<br/>

```java
select_문 :: = 
	select_절
    from_절
    [where_절]
    [groupby_절]
    [having_절]
    [orderby_절]
    
update_문 ::= update_절 [where_절]
delete_문 ::= delete_절 [where_절]
```
<br/>

### 1.1.1 특징

<br/>

- 대소문자 구분
  - 엔티티와 속성은 대소문자를 구분한다
  - ```select```, ```from```같은 JPQL 키워드는 구분하지 않는다
  
- 엔티티 이름
  - JPQL에서 사용하는 테이블명은 클래스 명이 아니라 엔티티 명으로 사용한다
 
- 별칭은 필수
  - JPQL은 필수로 별칭을 사용해야한다

<br/>

## 1.2 TypeQuery, Query

<br/>

JPQL을 실행하기 위한 쿼리 객체이다. 쿼리 객체는 ```TypeQuery```, ```Query```가 있으나 반환 타입을 명확히 지정할 수 있다면 ```TypeQuery```를 사용, 없으면 ```Query```를 사용한다.

<br/>

ex)

```java
TypeQuery<Member> query = 
	em.createQuery("SELECT m FROM Member m", Member.class);
   
List<Member> resultList = query.getResultList();


Query query = 
	em.createQuery("SELECT m FROM Member m");
   
List resultList = query.getResultList();
```

- ```query.getResultList()``` 
  - 결과를 예제로 반환한다. 결과가 없을 시 빈 컬렉션을 반환한다
- ```query.getSingleResult()``` 
  - 결과가 없으면 ```NoResultException```발생한다
  - 결과가 1개보다 많을 시 ```NoUniqueResultException``` 발생한다.



<br/><br/>

## 1.3 파라미터 바인딩

<br/>


### 1.3.1 이름 기준 파라미터

<br/>

쿼리 변수 앞에  **:** 를 넣어 사용한다

```java
String usernameParam = "User"

TypeQuery<Member> query = 
	em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class);
```

<br/>


### 1.3.2 위치 기준 파라미터

<br/>

쿼리 변수 앞에  **?** 를 넣어 사용한다. 위치 값은 1부터 시작한다.

```java
List<Member> members = 
	em.createQuery("SELECT m FROM Member m where m.username 
    = ?1", Member.class)
    .setParameter(1, usernameParam)
    .getResultList();
```



<br/><br/>

## 1.4 프로젝션

<br/>

SELECT 절에 조회할 대상을 지정하는 것을 프로젝션이라 칭한다. 
프로젝션 대상은 엔티티, 엠비디드 타입, 스칼라 타입이 있다. 


<br/>


### 1.4.1 엔티티 프로젝션

<br/>

```java
SELECT m FROM Member m      // 회원
SELECT m.team FROM Member m // 팀
```

컬럼을 나열해서 조회해야하는 것이 아닌 원하는 객체를 바로 조회하는 것이 엔티티 프로젝션이다.
이 조회한 엔티티는 영속성 컨텍스트에서 관리된다.

<br/>


### 1.4.2 임베디드 타입 프로젝션

<br/>

```java
String query = "SELECT a FROM Order o";
List<Address> address = em.createQuery(query, Address.class)
			.getResultList();
```

임베디드 타입은 조회의 시작점이 될 수 없다. 

임베디드 타입은 엔티티 타입이 아닌 값 타입이다. 따라서 이렇게 직접 조회한 임베디드 타입은 영속성 컨텍스트에서 관리되지 않는다. 

<br/>


### 1.4.3 스칼라 타입 프로젝션

<br/>

```java
String query = "SELECT a FROM Order o";
List<String> username = em.createQuery("SELECT username FROM Member m", String.class)
			.getResultList();
```

숫자, 문자, 날짜같은 기본 데이터 타입들을 스칼라 타입이라 한다.


<br/>


### 1.4.5 여러 값 조회

<br/>

```java
Query query = 
	em.createQuery("SELECT m.username, m.age FROM Member m");
List resultList = query.getResultList();
// or
List<Object[]> resultList = query.getResultList();
```

꼭 필요한 데이터들만 선택해 조회할 경우에 사용한다. 프로젝션에 여러 값을 선택하면 TypeQuery를 사용하지 못하고 Query를 사용해야한다.



<br/>

```java
Query query = 
	em.createQuery("SELECT o.member. o.product, o.orderAmount FROM Order o");

List<Object[]> resultList = query.getResultList();

for (Object[] row : resultList) {
	Member member = (Member) row[o];
    Product product = (product) row[1];
    int orderAmount = (Integer)row[2];	
}
```

이 때 조회한 엔티티는 영속성 컨텍스트에서 관리된다.


<br/>



### 1.4.6 NEW 명령어

<br/>

```java
TypeQuery<UserDTO> query = 
	em.createQuery("SELECT new jpabook.jpql.UserDTO(m.username, m.age) FROM Member m", UserDTO.class);
List resultList = query.getResultList();
// or
List<UserDTO> resultList = query.getResultList();
```

NEW 명령어를 사용해반환 클래스를 지정받을 수 있다.

주의점
- 패키지 명을 포함한 전체 클래스 명을 입력해야한다
- 순서와 타입이 일치하는 생성자가 필요하다.



<br/><br/>

## 1.5 페이징 API 

<br/>

JPA는 페이징을 두 API로 추상화했다.

- ```setFirstResult(int startPosition)```: 조회 시작 위치(0부터 시작한다)
- ```setMaxResults(int maxResult)```: 조회할 데이터 수

<br/>

```java
TypeQuery<UserDTO> query = 
	em.createQuery("SELECT m FROM Member m ORDER BY m.username DESC", Member.class);
    
query.setFirstResult(10);
query.setMaxResults(20);
query.getResultList();
```


<br/><br/>

## 1.6 집합과 정렬

<br/>

집합은 집합함수와 함께 통계 정보를 구할 때 사용한다.


<br/>

### 1.6.1 집합 함수

<br/>

|함수|설명|
|------|---|
|COUNT|결과 수를 구한다. 반환 타입 : Long|
|MAX, MIN|최대, 최소 값을 구한다. 문자, 숫자, 날짜 등에 사용한다.|
|AVG|평균값을 구한다. 숫자타입만 사용할 수 있다. 반환 타입: Double|
|SUM|합을 구한다. 숫자타입만 사용할 수 있다. 반환 타입: 정수합 Long, 소수합: Double, BigInteger합 : BigInteger, BigDecimal합 : BigDecimal|


<br/>

### 1.6.2 참고사항

<br/>

- NULL 값은 무시하고 통계에 잡히지 않는다.
- 값이 없으나 사용하면 NULL 값이 된다. 
  - COUNT는 0이 된다.
- DISTINCT를 집합 함수 안에 사용해서 중복된 값을 제거하고 나서 집합을 구할 수 있다.
- DISTINCT를 COUNT에서 사용할 때 임베디드 타입은 지원하지 않는다.




<br/><br/>

## 1.7 JPQL 조인

<br/>


### 1.7.1 내부 조인

내부조인은 inner join을 사용한다.

<br/>

```java
String teamName = "팀A";
String query = "SELECT m FROM Member m (INNER) JOIN m.team t WHERE t.name = :teamName";

List<Member> members = em.createQuery(query, Member.class)
	.setParameter("teamName", teamName)
    	.getResultList();
```


<br/>


### 1.7.2 외부 조인

외부 조인은 left (outer) join을 사용한다.

<br/>

```java
SELECT m
FROM Member m LEFT [OUTER] JOIN m.team t
```



<br/>


### 1.7.3 컬렉션 조인

일대다 관계나 다대다 관계처럼 컬렉션을 사용하는 곳에 조인하는 것을 컬렉션 조인이라 한다.

<br/>

```java
SELECT t, m
FROM Team t LEFT JOIN t.member m
```

- ```t LEFT JOIN t.member m```은 팀과 멤버를 컬렉션 값 연관 필드로 외부조인했다.




<br/>


### 1.7.4 세타 조인

WHERE절을 사용해 세타 조인이 가능하다. 세타 조인은 내부 조인만 지원한다.

<br/>

```java
//JPQL
select count(m) from Member m, Team t
where m.user = t.name
```



<br/>


### 1.7.5 JOIN ON절

<br/>

JPA 2.1부터 조인 시 ON 절을 지원한다. ON절로 인해 조인 대상을 필터링 조인할 수 있다. 
내부조인의 ON절은 WHERE절과 결과가 같아 보통 외부 조인에서만 사용한다.



```java
//JPQL
select m, t from Member m left join m.team t on t.name = 'A'
where m.user = t.name
```




<br/><br/>




## 1.8 페치 조인

<br/>

연관된 엔티티나 컬렉션을 한 번에 같이 조회하는 기능으로 join fetch로 사용할 수 있다.


<br/>


### 1.8.1 엔티티 페치 조인


```java
select m from Member m join fetch m.team
```

페치 조인을 통해 지연 로딩되어있는 연관 엔티티들도 한번에 가져올 수 있다.


<br/>


### 1.8.2 컬렉션 페치 조인


```java
select t from Team t join fetch t.members
where t.name "팀A"
```

페치 조인을 통해 지연 로딩되어있는 멤버 엔티티들도 함께 조회된다.

<br/>


### 1.8.3 페치 조인과 DISTINCT

<br/>

JPQL의 DISTINCT 명령어는 SQL에 추가하고 또 애플리케이션에서 한 번 더 중복을 제거한다.


<br/>


### 1.8.4 페치 조인 한계

<br/>

- 페치 조인 대상에는 별칭을 줄 수 없다
  - 따라서 SELECT, WHERE, 서브쿼리에 페치 조인 대상을 사용할 수 없다
  - 하이버네이트를 포함한 몇몇 구현체들에서는 지원한다.
- 둘 이상의 컬렉션을 페치할 수 없다
  - 구현체에 따라 가능하긴하나 컬렉션 * 컬렉션의 카테시안 곱이 만들어지니 주의해야한다.
- 컬렉션을 페치 조인하면 페이징 API를 사용할 수 없다
  - 단일 값 연관 필드(일대일, 다대일)들은 페치 조인을 사용해도 페이징 API를 사용할 수 있다.







<br/><br/>




## 1.9 경로 표현식

<br/>

경로 표현식이란 .(점)을 찍어 객체 그래프를 탐색하는 것이다.


<br/>

### 1.9.1 용어 정리

- 상태 필드 : 단순히 값을 저장하기 위한 필드
- 연관 필드 : 연관관계를 위한 필드, 임베디드 타입 포함
  - 단일 값 연관 필드 : @ManyToOne, @OneToOne, 대상이 엔티티
  - 컬렉션 값 연관 필드 : @OneToMany, @ManyToMany, 대상이 컬렉션


<br/>

### 1.9.2 특징

- 상대 필드 경로 : 경로 탐색의 끝이다. 더는 탐색할 수 없다
- 단일 값 연관 경로 : 묵시적으로 내부 조인이 발생한다. 단일 값 연관 경로는 계속 탐색 가능하다
- 컬렉션 값 연관 경로: 묵시적으로 내부 조인이 발생한다. 더는 탐색할 수 없다. FORM 절에서 조인을 통해 별칭을 얻으면 별칭으로 탐색가능하다.
   - ```select m.username from Team t join t.members m```
   
   
- 컬렉션은 size라는 기능을 제공한다.
  - ```select t.members.size from Team t```


<br/>

### 1.9.3 주의사항

- 항상 내부 조인이다
- 컬렉션은 경로 탐색의 끝이다.
- 경로 탐색은 주로 SELECT, WHERE 절에서 사용하지만 묵시적 조인으로 인해 SQL의 FROM 절에 영향을 준다






<br/><br/>




## 1.10 서브 쿼리

<br/>


서브쿼리는 WHERE, HAVING 절에서만 사용가능하다.

```java
select m from Member m 
where (select count(o) from Order o where m = o.member) > 0
```

<br/>

### 1.10.1 서브 쿼리 함수

- EXISTS
  - 문법 : [NOT] EXISTS (서브쿼리)
  - 서브쿼리에 결과가 존재하면 참
 
- ALL, ANY, SOME
  - 문법 : ALL, ANY, SOME (서브쿼리)
  - 비교 연산자와 같이 사용한다
  
 - IN
   - 문법 : [NOT] IN (서브쿼리)
   - 서브쿼리 결과 중 하나라도 같은 것이 있으면 참이다.




<br/><br/>




## 1.11 조건식

<br/>


### 1.11.1 타입 표현

- 문자
  - 작은 따옴표 사용 ('hello')
- 숫자
  - L(Long 타입) (10L)
  - D(Double 타입) (10D)
  - F(Float 타입) (10F)
- 날짜
  - DATE {d 'yyyy-mm-dd'} 
  - TIME {t 'hh-mm-ss'}
  - DATETIME {ts 'yyyy-mm-dd-hh:mm:ss.f'}
- enum
  - 패키지명 포함한 전체 이름 (com.min.Member.ADMIN)
- 엔티티 타입
  - 엔티티 타입을 표현. 주로 상속과 관련해 사용 (TYPE(m) = Member)



<br/>


### 1.11.2 컬렉션 식

컬렉션 식은 컬렉션에서만 사용할 수 있고 컬렉션은 컬렉션 식밖에 사용할 수 없다.

- { 컬렉션 값 연관 경로 }IS [NOT] EMPTY
  - 컬렉션에 값이 비었으면 참
- { 엔티티나 값 } [NOT] MEMBER [OF] {컬렉션 값 연관 경로}
  - 엔티티나 값이 컬렉션에 포함되어 있으면 참


   - 서브쿼리 결과 중 하나라도 같은 것이 있으면 참이다.




<br/><br/>




## 1.12 다형성 쿼리

<br/>


JQPL로 부모 엔티티를 조회하면 자식 엔티티도 함께 조회된다. 
이를 선택하려면 TYPE을 사용한다

<br/>

```sql
select i from Item i
where type(i) IN (BOOK, MOVIE)
```


<br/>

### 1.12.1 TREAT

JPA 2.1부터 자바의 타입 캐스팅과 비슷한 기능이 생겼다. 상속 구조에서 부모 타입을 특정 자식 타입으로 다룰 때 사용한다. 
JPA 표준은 FROM, WHERE절에서 사용 가능하지만 하이버네이트에서는 SELECT 절에서도 사용가능하다.

```sql
select i from Item i where treat(i as Book).author = 'kim'
```



<br/><br/>


<br/><br/><br/><br/>
  


---
참고 도서
 - 자바 ORM 표준 JPA 프로그래밍 - 김영한