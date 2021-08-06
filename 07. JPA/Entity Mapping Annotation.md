

# 1. 객체와 테이블 매핑



## @Entity



- 테이블과 매핑할 클래스 상단에 필요
- JPA에서 관리할 엔티티라는 것을 명시

<br/>

 주의사항
 
1. 기본 생성자는 필수
2. final class, enum, interface, inner class에는 사용 불가
3. 저장할 필드에 final 사용 불가


ex)
```java
@Entity
public class Member{
...
```




## @Table


- 엔티티와 매핑할 테이블 지정
- 생략 시, class 이름을 테이블 이름으로 정의


<br/>

주요 속성 정리

1. name : 매핑할 테이블 이름 설정
2. catalog : catalog 기능이 있는 데이터베이스에서 catalog 매핑
3. schema : schema 기능이 있는 데이터베이스에서 schema 생성
3. uniqueConstraints : DDL 생성 시, 유니크 제약조건을 생성한다. 2개 이상 복합 유니크 제약조건도 생성가능하다. 이 기능은 스키마 자동 생성 기능을 사용해서 DDL을 만들 때만 사용된다.


<br/>

ex)

```java
@Entity
@Table(name = "Velog_Member")
public class Member{
...
```

<br/><br/>


# 2. 기본 키 매핑


## @Id


- 테이블의 PK 값을 설정
- 기본 키 할당에는 직접할당과 자동 생성이 있다.


<br/>

 ###  1.1 직접할당
 
 - 직접 @Id로 매핑한다.
 - DB에 삽입하기 전, 직접 Id 값을 할당해야한다.
 
 
 ex)
 

```java
      @Entity
      @Table(name = "Velog_Member")
      public class Member{
      
        @Id
        private Long id;
        ...
```

<br/>


###  1.2 자동 생성
 
 
 - 대리키 사용 방식

- DB 벤더마다 지원하는 방식이 다르기 때문에 다음과 같은 속성들이 있다.
 
 <br/>
 
####  1.2.1 IDENTITY : 기본 키 생성을 데이터베이스에 위임한다. 

  - 주로 MySQL, SQL Server, DB2에서 사용한다. 
  
  
  ex)
```java  
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
...
```

  
  
 ####  1.2.2  SEQUENCE : 데이터베이스 시퀸스를 사용해 기본 키를 할당한다.
 - 시퀸스를 지원하는 오라클, DB2, H2 등에서 사용한다.

 ex)
```java  

@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", // 매핑할 데이터베이스 시퀸즈 이름
        initialValue = 1, allocationSize = 1)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

     ...
```

 
  
 ####  1.2.3. TABLE : 키 생성 테이블을 사용한다.
 - 키 생성 전용 테이블을 하나 만들어 시퀸스 전략을 흉내낸다.
 - 모든 데이터베이스에서 사용가능하다.
 
 
 ex)
 

```java

@Entity
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "TABLE_SEQUENCES",
        pkColumnName = "MEMBER_SEQ", allocationSize = 1
)
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
...
```


 
 ####  1.2.4 AUTO : DB Vender에 따라 자동 선택한다
 
- @GeneratedValue.strategy의 default값이다.
 
 
 ex)
 

```java

@Entity
public class Member {
    
    @Id
    @GeneratedValue
    private Long id;
...
```

<br/><br/>


# 3. 컬럼 매핑


## @Column


- 객체 필드를 테이블 컬럼에 매핑한다.

<br/>

주요 속성 정리

1. name : 매핑할 컬럼 이름 설정
2. insertable : 저장 시, 이 필드도 함께 저장. false 설정 시, 이 필드는 db에 저장하지 않는다. 읽기 전용일 때 사용한다.
3. updatable : 수정 시, 이 필드도 함께 수정한다. false로 설정 시, db에 수정하지 않는다. 읽기 전용일 때 사용한다.
3. table : 하나의 엔티티를 두 개 이상의 테이블에 매핑할 때 사용한다. 지정한 필드를 다른 테이블에 매핑할 수 있다.
4. nullable(DDL) : null 허용 여부를 설정한다. false 설정 시, DDL 생성 시에 not null 제약조건이 붙는다.
5. unique(DDL) : 유니크 제약조건을 걸 때 사용한다. 두 컬럼 이상 사용할 시, class 레벨에서 @Table.uniqueConstraints를 사용해야한다.
6. columnDefinition(DDL) : 데이터베이스 컬럼 정보를 직접 줄 수 있다.
7. length(DDL) : 문자 길이 제약조건을 생성한다. String 타입에만 사용한다.
8. precision, scale(DDL) : BigDecimal 타입에서 사용한다.(BigInteger에도 사용 가능). precision는 소수점을 포함한 전체 자릿수를, scale는 소수의 자릿수다. double, float 타입에는 적용 불가. 


<br/>

ex)
```java

@Entity
public class Member {
    
...

    @Column(name = "mem_email", length = 255)
    private String email;

...

```


<br/>



## @Enumerated


- 자바의 enum 타입을 매핑할 때 사용한다.

<br/>
주요 속성 정리

1. EnumType.ORDINAL : enum 순서를 DB에 저장
2. EnumType.STRING : enum 이름을 DB에 저장

<br/>

ex)

```java

@Entity
public class Member {
    
...

    @Column(name = MemberTable.ROLE)
    @Enumerated(EnumType.STRING)
    private Role role;

...

```


<br/>



## @Temporal


- 날짜 타입을 매핑할 때 사용한다.



<br/>
주요 속성 정리

1. TemporalType.DATE : 날짜, 데이터베이스 date 타입과 매핑(ex: 2021-05-09)
2. TemporalType.TIME : 시간, 데이터베이스 time 타입과 매핑(ex: 11:08:15)
3. TemporalType.TIMESTAMP : 날짜와 시간, 데이터베이스 timestamp 타입과 매핑
(ex: 2021-05-09 11:08:15)


ex)

```java

@Entity
public class Member {
    
...

    @Temporal(TemporalType.DATE)
    @Column(name = BoardTable.UPLOAD_DATETIME)
    private Date uploadDatetime;

...

```



<br/>



## @Lob


- 데이터베이스의 BLOB, CLOB 타입과 매핑한다.
-  @Lob에는 지정할 수 있는 속성이 없다. 매핑하는 필드 타입이 문자면 CLOB, 나머지는 BLOB으로 매핑한다.



<br/>

ex)
```java

@Entity
public class Member {
    
...


    @Lob
    private String lobString; // clob
    @Lob
    private byte[] lobByte; // blob

...

```
<br/>



## @Transient


- 이 필드는 매핑하지 않는다. 따라서 DB에 저장하지 않고 조회하지도 않는다. 객체에 임시로 어떤 값을 보관하고 싶을 때 사용한다.


<br/>

ex)
```java

@Entity
public class Member {
    
...

    @Transient
    private String tempId;

...

```
<br/>



## @Access


- JPA가 엔티티 데이터에 접근하는 방식을 지정한다.


<br/>

주요 속성 정리

1. 필드 접근 : AccessType.FIELD. 필드에 직접 접근. private라도 접근가능하다. @Id가 필드에 있다면 생략 가능하다


ex)
```java

@Entity
@Access(AccessType.FIELD) // 생략 가능
public class Member {
    
...

    @id
    private String id;

...

```
<br/>

2. 프로퍼티 접근 : AccessType.PROPERTY. 접근자 Getter를 사용한다.



ex)
```java

@Entity
@Access(AccessType.PROPERTY) // 생략 가능
public class Member {
    
...

    private String id;
    
    @Id
    public String getId(){
     return this.id; 
    }
...

```
<br/>


3. 혼용 가능


ex)
```java

@Entity
public class Member {
    
...
    @Id
    private String id;
    private String pw;
    
    @Access(AccessType.PROPERTY)
    public String getPw(){
     return encrypt(this.pw); 
    }
...

```
<br/>


<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>   
 참고 도서
 - https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=62681446
 


  
  