# WHERE 1=1

- WHERE는 조회하려는 데이터들의 조건을 거는 문법이다.

- 1 = 1은 말그대로 참을 의미한다.

- 즉, 1=1이란 조건은 있으나마나 한 조건이라는 의미다.

<br/>

## 장점

### 1. 쿼리 디버깅 시, 주석처리가 편하다.

```sql
select *
from CUSTOMERS
where CUSTOMERID = '3'
AND COMPANYNAME LIKE 'L%'
```

위와 같은 쿼리가 있을 때,  COMPANYNAME LIKE 'L%'인 데이터가 잘 조회되는지 확인하기 위해 CUSTOMERID = '3'인 조건행을 잠시 주석처리한다 가정하자.


```sql
select *
from CUSTOMERS
where --CUSTOMERID = '3' 
--AND
COMPANYNAME LIKE 'L%'
```

하지만 WHERE 1=1 이 있다면 아래와 같이 간편하게 주석 및 디버깅을 할 수 있다.

```sql
select *
from CUSTOMERS
where 1=1
--AND CUSTOMERID = '3'
AND COMPANYNAME LIKE 'L%'
```            
            
            
                 
<br/>

## 2. 동적쿼리에서 특정상황마다 WHERE절을 다르게 작성해줘야 할때 편리하다.

사용자가 CUSTOMERID를 조회조건으로 선택한 후 조회를 할 때는 CUSTOMERID로, COMPANYNAME을 조회조건으로 선택한 경우에는 COMPANYID로 WHERE절의 조건을 걸어줘야하는 경우를 가정하자.

```java
query1 = "SELECT * FROM CUSTOMER "

if(!cusotmerID.equals("") {
	query2 = "WHERE CUSTOMERID = '" + customerID + "'"
}
if(!companyName.equals("") {
	if(!customerId.equals("") {
    	query3 = "AND"
    } else {
    	query3 = "WHERE"
    }
	query4 = "COMPANAYNAME = '" + companayname + "'"
}
```

첫 조건에 WHERE가 붙어야하기 때문에 소스가 복잡하다.

이때, WHERE 1=1이 있다면 간편하게 변경가능하다.

```java
query1 = "SELECT * FROM CUSTOMER WHERE 1=1 "

if(!cusotmerID.equals("") {
	query2 = "AND CUSTOMERID = '" + customerID + "'"
}
if(!companyName.equals("") {
	query2 = "AND COMPANYNAME = '" + companyName + "'"
}
```

<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [[MSSQL] WHERE 1=1이 뭐야? 왜 쓰는 거야?](https://hyjykelly.tistory.com/5)




