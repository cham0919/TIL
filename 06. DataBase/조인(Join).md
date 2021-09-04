# EQUI(등가) 조인(교집합)

- 조인은 여러 개의 릴레이션을 사용해서 새로운 릴레이션을 만드는 과정이다.

- 조인의 가장 기본은 교집합을 만드는 것이다.

- ``` SELECT * FROM STUDENT, MAJOR WHERE STUDENT.MAJORNO = MAJOR.MAJORNO; ```
  - STUDENT 테이블과 MAJOR 테이블에서 MAJORNO 칼럼을 사용하여 같은 것을 조인한다.
  
- EQUI JOIN에서만 HASH JOIN이 가능하다.
  - 해시 조인은 먼저 선행 테이블을 결정하고, 선행 테이블에서 주어진 조건(WHERE)에 해당하는 행을 선택한다.
  - 해당 행이 선택되면, 조인 키(Join Key)를 기준으로, 해시 함수를 사용해서 해시 테이블을 메인 메모리(Main Memory)에 생성하고, 후행 테이블에서 주어진 조건을 만족하는 행을 찾는다.
  - 후행 테이블의 조인 키를 사용해서, 해시 함수를 적용하여 해당 버킷을 찾는다.
  - 대량의 데이터를 처리할 때 주로 사용된다.

<br/>

# INNER JOIN

- INNER JOIN은 ON 문을 사용해서 테이블을 연결한다.

-  ```SELECT * FROM STUDENT INNER JOIN MAJOR ON STUDENT.MAJORNO = MAJOR.MAJORNO;```

<br/>

# INTERSECT 연산

- 두 개의 테이블에서 교집합을 조회한다.

- 즉, 두 개의 테이블에서 공통된 값을 조회한다.

- ```SELECT MAJORNO FROM STUDENT INTERSECT SELECT MAJORNO FROM MAJOR;```

<br/>

#  NON-EQUI(비등가) 조인

- Non-Equi는 두 개의 테이블 간에 조인하는 경우, "="을 사용하지 않고, >, <, >=, <= 등을 사용한다.

<br/>

# Outer JOIN

- OUTER JOIN은 두 개의 테이블 간에 교집합(EQUI JOIN)을 조회하고, 한쪽 테이블에만 있는 데이터도 포함시켜 조회한다.

- 이 때, 왼쪽 테이블에만 있는 행도 포함하면 LEFT OUTER JOIN이라고 하고, 오른쪽 테이블에만 있는 행만 포함시키면 RIGHT OUTER JOIN이라고 한다.

- FULL OUTER JOIN은 LEFT OUTER JOIN과 RIGHT OUTER JOIN 모두를 하는 것이다.

- Oracle Database에서는 OUTER JOIN을 할 때, "(+)" 기호를 사용해서 할 수 있다.

-  ```SELECT * FROM STUDENT, MAJOR WHERE STUDENT.MAJORNO (+)= MAJOR.MAJORNO;```

- MAJOR 테이블에는 있지만, STUDENT 테이블에는 없는 정보도 조회된다.

<br/>

# CROSS JOIN

- CROSS JOIN은 조인 조건구 없이 2개의 테이블을 하나로 조인한다.

- 조인구가 없기 때문에 카테시안 곱이 발생한다.

- 예를 들어, 행이 12개 있는 테이블과 행이 5개 있는 테이블을 조인하면 60(12*5)개의 행이 조회된다.

- CROSS JOIN은 FROM절에 "CROSS JOIN"구를 사용하면 된다.

- ```SELECT * FROM STUDENT CROSS JOIN MAJOR;```
-  ```SELECT * FROM STUDENT, MAJOR;```

<br/>

# NATURAL JOIN

- Natural Join을 사용하면 공통된 속성을 자동으로 찾아 같은 값을 갖는 항목끼리 결합시켜준다.
  
- 만약 같은 값을 갖는 항목이 없다면 해당 항목은 제외한다.




<br/>

<br/>

# USING Join

- 자연 조인에서 사용하는 테이블간에 동일한 이름과 형식의 컬럼을 모두 조인한다

- 이럴 경우 USING 절을 이용한 조인문을 이용하면 특정 컬럼을 지정할 수 있다.

- USING절 안에 포함된 컬럼에는 알리야스를 사용할 수 없다.

```sql
SELECT 컬럼, 컬럼, …
FROM 테이블1
JOIN 테이블2 USING(조인 컬럼)
[JOIN 테이블3 USING(조인 컬럼)] …
WHERE 검색 조건;
```

- USING 절은 조인에 사용될 컬럼을 지정한다.
- NATURAL 절과 USING 절은 함께 사용할 수 없다.
- 조인에 이용되지 않은 동일 이름을 가진 컬럼은 컬럼명 앞에 테이블명을 기술한다.
- 조인 컬럼은 괄호로 묶어서 기술해야 한다.

<br/>

# UNION을 사용한 합집합 구현

### UNION

- UNION 연산은 두 개의 테이블을 하나로 만드는 연산이다.

- 단, 두 개의 테이블의 칼럼 수, 칼럼의 데이터 형식 모두가 일치해야 한다. 만약 둘 중 하나라도 다르다면, 오류가 발생한다.

- UNION 연산은 두 개의 테이블을 하나로 합치면서 중복된 데이터를 제거한다.

- 그래서 UNION은 정렬(SORT) 과정을 발생시킨다.

- ```SELECT MAJORNO FROM STUDENT UNION SELECT MAJORNO FROM MAJOR;```

<br/>

### UNION ALL

- UNION과 달리 중복을 제거하거나 정렬을 유발하지 않는다.

- ```SELECT MAJORNO FROM STUDENT UNION ALL SELECT MAJORNO FROM MAJOR;```

<br/>

# 차집합을 만드는 MINUS

- MINUS 연산은 두 개의 테이블에서 차집합을 조회한다.

- 즉, 선행하는 SELECT문에는 있고, 후행하는 SELECT문에는 없는 집합을 조회하는 것이다.

- MS-SQL에서 MINUS와 동일한 연산은 EXCEPT다.



<br/><br/><br/><br/><br/><br/><br/>


---
Reference

- [[SQL 활용]조인(Join)](https://velog.io/@yewon-july/Join)