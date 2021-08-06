
# 0. 들어가기 전에

<br/>

연관관계에 들어가기 전에 간단한 키워드들이 있어 정리하고 들어가보려 한다.

- 방향 : 단방향/양방향이 있다. 연관관계가 형성되어있을 때, 한쪽만 참조하는 것을 단방향, 양쪽 모두 서로 참조하는 것을 양방향이라 칭한다.
- 다중성 : 다대일/일대다/일대일/다대다 다중성이 있다.
- 연관관계 주인 : 객체를 양방향 연관관계로 형성했을 시, 연관관계의 주인을 정해야한다.

<br/>

객체 연관관계와 테이블 연관관계 차이
  - 객체의 참조를 통한 연관관계는 언제나 단방향이다.
  - 객체에서 양방향을 구현하기 위해서는 서로의 필드에 참조하여 보관해야한다.
  - 즉, 정확히 양방향의 관계가 아닌, 서로 다른 단방향 관계를 중첩하여 양방향처럼 만드는 것이다.
  - 테이블 연관관계는 외래 키 하나로 양방향의 연관관계를 맺는다.


<br/>



# 1. 단방향 연관관계




```java
public class Student {

...

@ManyToOne
@JoinColumn(name = "SCHOOL_ID")
private School school;
```


<br/>

### 1.1 매핑 관련 어노테이션 정보

- @ManyToOne : 다대일 관계 매핑 정보. 
  - optional : false로 설정 시, 연관된 엔티티가 항상 있어야한다
  - fetch : 글로벌 페치 전략을 설정한다
  - cascade : 영속성 전이 기능을 사용한다.
  - targetEntity : 연관된 엔티티의 타입 정보를 설정한다. 
- @JoinColumn : 외래 키 매핑할 때 사용. 생략 가능. 생략시 필드명 + _ + 테이블 기본 키 컬럼명으로 설정
  -  name : 매핑할 외래키 이름
  - referencedColumnName : 외래 키가 참조하는 대상 테이블의 컬럼명
  - foreignKey : 외래키 제약조건을 직접 지정 가능. 테이블 생성할 때만 사용
  - unique, nullable, insertable, updateable, columnDefinition, table : @Column의 속성과 동일

<br/>

Tip. 단방향 관계를 매핑할 때 다대일/일대다 선택 기준은 반대편 관계에 달려 있다. 반대편이 일대다면 다대일, 반대편이 다대일이면 일대다를 사용하면 된다.

<br/>



# 2. 양방향 연관관계



<br/>


```java
@Entity
public class School {

...

@OneToMany(mappedBy = "school")
List<Student> students = new ArrayList<Student>();

...

```


### 2.1 연관관계 주인

양방향 연관관계 매핑 시, 둘 중 하나를 주인으로 정해야한다. 이 주인만이 db 연관관계와 매핑되고 외래 키를 관리할 수 있다. 반대편은 오직 읽기만 할 수 있다.

따라서 읽기만 가능한 곳은 mappedBy 속성을 사용하여 주인 필드를 지정한 뒤 본인 필드는 읽기 전용이라고 명시해주어햐 한다. 


<br><br>



# 3. 연관관계 정리




## 3.1 다대일(N:1)

<br/>

###  3.1.1 다대일(N:1) 단방향



- 다대일로 Student쪽에 School 연관관계 매핑을 시켜주면 된다

```java
public class Student {

...

@ManyToOne
@JoinColumn(name = "SCHOOL_ID")
private School school;
```

<br/>

###  3.1.1 다대일(N:1, 1:N) 양방향

School과 Student간의 다대일 관계 매핑 예시

- 양방향으로 School쪽에 조회만 가능한 연관관계 매핑을 시켜주면 된다

```java
public class Student {

...

@ManyToOne
@JoinColumn(name = "SCHOOL_ID")
private School school;

...

public void setSchool(School school){
 this.school = school;
 
 if(!school.getStudents().contains(this)){
  school.getStudents().add(this);
  }
}
```


```java
@Entity
public class School {

...

@OneToMany(mappedBy = "school")
private List<Student> students = new ArrayList<Student>();

...

public void addStudent(Student student){
 this.students.add(student);
 if (student.getSchool() != this){
  student.setSchool(this);
 }
}
...

```


- 양방향은 항상 서로 참조해야한다. 이를 위한게 setSchool(), addStudent()인데 양쪽 다 작성하면 무한루프에 빠지므로 주의해야한다.

<br/>

## 3.2 일대다(1:N)

<br/>

###  3.2.1 일대다(1:N) 단방향


- 일대다로 School쪽에 연관관계 매핑을 시켜주면 된다


```java
@Entity
public class School {

...

@OneToMany
@JoinColumn(name = "SCHOOL_ID") 
private List<Student> students = new ArrayList<Student>();

...

```

- 일대다 단방향 매핑의 단점은 매핑한 객체가 관리하는 외래 키가 다른 테이블이 있다는 것이다
- 본인 테이블에 외래키가 있다면 저장 및 처리를 INSERT 한번으로 처리가 가능하지만, 다른 테이블에 있다면 UPDATE문을 추가 실행해야한다는 단점이 있다.


<br/>

###  3.2.2 일대다(1:N, N:1) 양방향

School과 Student간의 일대다 관계 매핑 예시

- 일대다 양방향 매핑은 존재하지 않는다. 다대일 양방향 매핑을 사용하여 매핑해야한다.




```java
@Entity
public class School {

...

@OneToMany
@JoinColumn(name = "SCHOOL_ID") 
private List<Student> students = new ArrayList<Student>();

...

```


```java
public class Student {

...

@ManyToOne
@JoinColumn(name = "SCHOOL_ID", insertable = false, updatable = false)
private School school;

...

```

- insertable = false, updatable = false 로 읽기 전용 설정을 한다


<br/>

## 3.3 일대일(1:1)

<br/>

###  3.3.1 일대일(1:1) 단방향

School과 Student간의 일대일 관계 매핑 예시



```java
public class Student {

...

@OneToOne
@JoinColumn(name = "SCHOOL_ID")
private School school;

...

```
<br/>

###  3.3.2 일대일(1:1) 양방향


```java
@Entity
public class School {

...

@OneToOne(mappedBy = "school")
private Student student;

...

```
<br/>

## 3.4 다대다(N:N)

<br/>

###  3.4.1 다대다(N:N) 단방향

School과 Student간의 다대다 관계 매핑 예시



```java
public class Student {

...

@ManyToMany
@JoinTable(name = "STUDENT_SCHOOL",
    joinColumns = @JoinColumn(name = "STUDENT_ID"),
    inverseJoinColumns = @JoinColumn(name = "SCHOOL_ID"))
private List<School> schools = new ArrayList<School>();

...

```



- @JoinTable.name : 연결테이블 지정
- @JoinTable.joinColumns : 현재 방향인 학생과 매핑할 조인 컬럼 정보를 지정한다
- @JoinTable.inverseJoinColumns : 반대 방향인 학교와 매핑할 조인 컬럼 정보를 지정한다

<br/>

###  3.4.2 다대다(N:N) 양방향



```java
@Entity
public class School {

...

@ManyToMany(mappedBy = "schools")
private List<Student> students;

...

```

<br/>
<br/><br/><br/><br/><br/><br/><br/><br/>
 
 
 참고 도서

 - https://www.aladin.co.kr/shop/wproduct.aspx?ItemId=62681446