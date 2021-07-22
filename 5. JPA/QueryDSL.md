

# 1. QueryDSL




QueryDSL을 사용하면 문법 오류를 컴파일 단계에서 잡을 수 있고 문자 기반의 JPQL보다 동적 쿼리를 안전하게 생성할 수 있는 장점이 있다. 

<br/>

## 1.1 조회

<br/>

ex)
```java
JPAQuery query = new JPAQuery(em);
QItem item = QItem.item;
List<Item> list = query.from(item)
	.where(item.name.eq("테스트").and(item.price.gt(2000)))
    .list(item);
```


<br/>


**결과 조회**

보통 ```uniqueResult()```나 ```list()```를 사용한다.

- ```uniqueResult()``` : 조회 결과가 한 건일 때 사용한다. 결과가 없을 시  null 반환, 한 건 이상일 시, NonUniqueResultException 예외가 발생한다
- ```singleResult()``` : 하나 이상이면 처음 데이터를 반환한다.
- ```list()``` : 결과가 여러개일 때 사용한다. 결과가 없으면 빈 컬렉션을 반환한다.


<br/>

## 1.2 페이징

<br/>

```java
QItem item = QItem.item;

query.from(item)
	.offset(10).limit(20)
    .listResults(item);
```

<br/>

```offset```과 ```limit```을 사용하여 페이징 처리를 한다.


- ```listResults``` : 전체 데이터 조회를 위한 count 쿼리를 한번 더 실행한다. ```result.getTotal()```을 통해 전체 데이터 수를 얻어올 수 있다.




<br/><br/>

## 1.3 조인

<br/>

```innerJoin```, ```leftJoin```, ```rightJoin```, ```fullJoin```, ```fetchJoin```이 사용 가능하다.


<br/>

**기본 Join**

```java
query.from(order)
	.join(order.member, member)
    .leftJoin(order.orderItems, orderItems)
    .list(order);
```

**on 사용**

```java
query.from(order)
    .leftJoin(order.orderItems, orderItems)
    .on(orderItem.count.gt(2))
    .list(order);
```

**fetch join 사용**

```java
query.from(order)
    .innerJoin(order.orderItems, orderItems).fetch()
    .list(order);
```






<br/><br/>

## 1.4 서브쿼리

<br/>

서브쿼리의 결과가 하나면 ```unique()```, 여러 건이면 ```list()```를 사용할 수 있다.


<br/>

**단일 서브 쿼리**

```java
query.from(item)
	.where(item.price.eq(
    	new JPASubQuery().from(itemSub).unique(itemSub.price.max())))
        .list(item);
```



**다중 서브 쿼리**

```java
query.from(item)
	.where(item.in(
    	new JPASubQuery().from(itemSub).list(itemSub)))
        .list(item);
```



<br/><br/>

## 1.5 프로젝션

<br/>

select절에서 조회 대상을 지정하는 것을 프로젝션이라 한다.
 수

<br/>

```java
List<Tuple> result = query.from(item).list(item.name, item.price);
result.get(item.name);
result.get(item.price);

```


<br/>

### 빈 생성


```java
List<Tuple> result = query.from(item)
	.list(Projections.bean(ItemDto.class, item.name.as("username"), item.price));
```

<br/>



```java
List<Tuple> result = query.from(item)
	.list(Projections.fields(ItemDto.class, item.name.as("username"));
```

<br/>

```java
List<Tuple> result = query.from(item)
	.list(Projections.constructor(ItemDto.class, item.name);
```


<br/><br/>

## 1.6 수정, 삭제 배치 쿼리

<br/>

영속성 컨텍스트를 무시하고 db에 직접 쿼리한다.


<br/>

**update**

```java
JPAUpdateClause updateClause = new JPAUpdateClause(em, item);
long count = updateClause.where(item.name.eq("JPA"))
	.set(item.price, item.price.add(100))
    .execute();
```



**delete**

```java
JPADeleteClause deleteClause = new JPADeleteClause(em, item);
long count = deleteClause.where(item.name.eq("JPA"))
    .execute();
```

<br/><br/>

## 1.7 동적 쿼리

<br/>


```java
BooleanBuilder builder = new BooleanBuilder();
if(StringUtils.hasText(param.getName())) {
	builder.and(item.name.contains(param.getName()));
}
if (param.getPrice() != null) {
	builder.and(item.price.gt(param.getPrice()));
}
query.from(item)
	.where(builder
    .list(item);
```


<br/>



<br/><br/><br/><br/>
  


---
참고 도서
 - 자바 ORM 표준 JPA 프로그래밍 - 김영한