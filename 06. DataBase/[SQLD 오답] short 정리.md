# 계층형 sql

- ```CONNECT_BY_ISLEAF``` : 전개 과정에서 해당 데이터가 리프 데이터면 1,아니면 0
- ```CONNECT_BY_ISCYCLE``` : 전개 과정에서 자식을 갖는데, 해당 데이터가 조상으로 존재하면 1, 그렇지 않으면 0
  - 여기서 조상이란 자신으로부터 루트까지의 경록에 존재하는 데이터를 말함
- ```SYS_CONNECT_BY_PATH``` : 하위 레벨의 컬럼까지 모두 표시해줌 (구분자 지정 가능)
- ```CONNECT_BY_ROOT``` : Root 노드의 정보를 표시

<br/>

# 행 제한
 
SQL SERVER - TOP (Expression) [PERCENT] [WITH TIES]

- ```WITH TIES``` : ORDER BY 조건 기준으로 TOP N 의 마지막 행으로 표시되는 추가 행의 데이터가 같을 경우 N+
  동일 정렬 순서 데이터를 추가 반환하도록 지정하는 옵션 (마지막 기준 공통일 경우 모두 출력)
 
<br/>

# 결합함수

- Oracle 의 결합 함수 : CONCAT / ||
- SQL Server : +

<br/>

# IDENTITY

SQL SERVER : IDENTITY [ ( seed , increment ) ]
- SEED : 첫번째 행이 입력될 때의 시작값
- 증가되는 값
- 해당 컬럼에 값을 넣을 경우 Error 발생 (Error 발생을 막기 위해서는
IDENTITY_INSERT 를 OFF 로 하면 되나 이런 경우 IDENTITY 를 쓴 의미가 없어짐)

<br/>

# CHECK

- ORACLE의 CHECK 조건을 만족하지 못할 경우 에러가 나나 NULL 은 무시됨

<br/>

# ANSI JOIN

- ANSI SQL 에서 조인 조건절 (ON 절) 에 사용된 조건절은 조인 전 조건으로 작용한다

- ON 절 이후 WHERE 절에서 쓰인 조건절은 조인후 조건절로 사용된다

<br/>

# GROUPING 함수

```sql
GROUP BY ROLLUP(DNAME,JOB)
= GROUP BY DNAME,JOB 
UNION ALL 
GROUP BY DNAME 
UNION ALL
모든 집합 그룹 결과
```

```sql
GROUP BY GROUPING SET(DNAME,JOB)
= GROUP BY DNAME
UNION ALL
GROUP BY JOB
```

```sql
GROUP BY CUBE(DNAME,JOB)
= GROUP BY DNAME,JOB
UNION ALL
GROUP BY DNAME
UNION ALL
GROUP BY JOB
UNION ALL
모든 집합 그룹 결과
```

<br/>

# MAX(LEVEL)

- 계층형 쿼리에서 최대 계층의 수를 구하기 위해 select 문에 사용한다.

- MAX (LEVEL) 을 사용하여 최대 계층 수를 구한다.


<br/>

# NOT EXISTS 와 OUTER JOIN 변형

- NOT EXISTS 는 OUTER JOIN 으로 변경 시, NOT NULL COLUMN 에 대한 IS NULL 체크로 NOT EXISTS 를 구현가능하다

ex)

```sql
SELECT ...
FROM 급여이력 S
WHERE NOT EXISTS (SELECT 'X'
FROM 사원 P
WHERE P.사원번호 = S.사원번호)


SELECT ....
FROM 급여이력 S LEFT OUTER JOIN 사원 P
ON (S.사원번호 = P.사원번호)
WHERE P.사원번호 IS NULL
```

<br/>

# Oracle, SQL Server CHAR형 차이

- CHAR 형의 경우 고정형으로 사이즈에 비해 작은 값이 들어올 경우 나머지 사이즈를 ' '(스페이스) 로 채운다

- Oracle 의 경우 length 함수는 ' ' (스페이스) 를 1로 인식하고 SQL Server 의 len 함수는 문자 뒤의 ' ' (스페이스)를 0으로 인식한다.

<br/>

# Oracle, SQL Server COMMIT

- Oracle 의 경우 DML 후 자동 COMMIT 아니나, DDL 발생하면 D이 은 암묵적인 COMMIT 이 자동으로 발생되어 전체 트랜잭션이 COMMIT 된다.

- SQL Server 의 경우 자동 COMMIT이 된다.

<br/>

# WHERE

- WHERE 절에 조건절이 쓰이게 되면 암묵적으로 해당 컬럼에 대한 IS NOT NULL 조건이 생성된다

<br/>


# Oracle NVL, NVL2

- 해당 컬럼의 값이 null 값인 경우 특정값으로 출력하고 싶으면 NVL 함수를 사용하고, null 값이 아닐경우 특정값으로 출력하고 싶으면 NVL2 함수를 사용하면 된다.

- NVL 함수는 값이 null인 경우 지정값을 출력한다.
  - 함수  :  NVL("값", "지정값")
  
- NVL2 함수는 null이 아닌경우 지정값1을  출력하고, null인 경우 지정값2을 출력 한다.
  - 함수 :  NVL2("값", "지정값1", "지정값2")

<br/>

# ESCAPE

- LIKE 연산으로 '%'나 '_'가 들어간 문자를 검색하기 위해서는 ESCAPE를 사용해야 한다. '_'나 '%'앞에 ESCAPE로 특수 문자를 지정하면 검색할 수 있다.

```sql
SQL> SELECT   loc
     FROM   dept
     WHERE   loc like '%#_%' ESCAPE '#';

LOC
-------------
NEW_YORK        
 

-- 아래의 결과들을 한번 비교해 보시기 바랍니다.
SQL> SELECT   loc
     FROM   dept
     WHERE   loc like '%N%@_%' ESCAPE '@'
        
LOC
-------------
NEW_YORK        
 
 
SQL> SELECT loc
     FROM dept
     WHERE loc like '%_%';
        
LOC
-----------
NEW_YORK
DALLAS
CHICAGO
BOSTON     
```

<br/>

# COALESCE

- 다수의 컬럼을 합칠 때, 사용한다.

- 예를 들어 A테이블에 phone이라는 컬럼과 B테이블에 tel이라는 컬럼이 있을 때, ```COALESCE(phone, tel)```하면 처음으로 null이 아닌 데이터로 출력

<br/>

# NULLIF

- 지정된 두 식이 같으면 Null 값을 반환한다.
 
- 예를 들어 ```SELECT NULLIF(4,4) AS Same, NULLIF(5,7) AS Different;``` 는 두 입력 값이 동일하기 때문에 첫 번째 열(4 및 4)에 대해 NULL을 반환한다.
 
 - 두 번째 열은 두 입력 값이 다르기 때문에 첫 번째 값(5)을 반환한다.

<br/>

# 문자열 자르기

- SUBSTR(str, pos)
  - ```SELECT SUBSTR('동해물과백두산이',5);```
  - 결과) 백두산이
  - 해석) 5번째 문자열부터 읽으시오.
  
<br/>

- SUBSTR(str FROM pos)
  - ```SELECT SUBSTRING('동해물과백두산이' FROM 5);```
  - 결과) 백두산이
  - 해석) 5번째 문자열부터 읽으시오.
  
<br/>

- SUBSTR(str,pos,len)
  - ```SELECT SUBSTRING('동해물과백두산이',3,4);```
  - 결과) 물과백두
  - 해석) 3번째 문자열부터 읽으시고, 4글자만 가져오시오.
  
<br/>

- SUBSTR(str FROM pos FOR len)
  - ```SELECT SUBSTRING('동해물과백두산이' FROM 3 FOR 4);```
  - 결과) 물과백두
차집합을 만드는 MINUS  - 해석) 3번째 문자열부터 읽으시고, 4글자만 가져오시오.

- pos에 음수가 들어오면 뒤에서부터 카운트

<br/>

# DISTINCT

- 조회할 때, 조회 ROW보다 적게 중복제거 되면 조회 데이터 수만큼은 출력된다.

ex)

```sql
-- TABLE 
1 A 
1 A 
1 A 
1 B 

SELECT COUNT(COL1), COUNT(COL2) 
FROM (
     SELECT DISTINCT COL1, COL2 
     FROM T1 
);

>1 2
>1 3
```


<br/>

# 이론 

- 쿼리를 수행하는데 소요되는 일량 또는 시간을 기반으로 최적화를 수행하는 옵티마이저는 **비용 기반 옵티마이져**