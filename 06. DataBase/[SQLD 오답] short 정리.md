
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

# 옵티마이저 

- 쿼리를 수행하는데 소요되는 일량 또는 시간을 기반으로 최적화를 수행하는 옵티마이저는 **비용 기반 옵티마이져**

<br/>

# SQL 문장 종류

- DML (데이터 조작어)
  - ```SELECT, INSERT, UPDATE, DELETE```
  
- DDL (데이터 정의어)
  - ```CREATE, ALTER, DROP, RENAME```
  
- DCL (데이터 제어어)
  - ```GRANT, REVOKE```
  
- TCL (트랜잭션 제어어)
  - ```COMMIT, ROLLBACK```
  
  
 <br/>
 
# Column 수정

- SQL Server에서는 여러 컬럼을 동시에 수정하는 구문을 지원하지 않는다.
- 또한 괄호를 사용하지 않는다.

ex)
 ```sql
-- 에러 발생
ALTER TABLE 테이블명 ALTER COLUMN (컬럼1 VARCHAR(30), 컬럼2 VARCHAR(30));

-- 정상 동작
ALTER TABLE 테이블명 ALTER COLUMN 컬럼1 VARCHAR(30);
```

<br/>

# UNIQUE

- UNIQUE는 테이블 내에서 중복되는 값이 없지만, ```NULL``` 입력은 가능하다.

<br/>

# DELETE, DROP, TRUNCATE

- ```DROP, TRUNCATE```은 로그를 남기지 않지만 ```DELETE```는 로그를 남긴다
- ```TRUNCATE```는 테이블 자체가 삭제되는 것이 아닌, 테이블에 들어있던 모든 행들이 제거되고 저장 공간을 재사용 가능하도록 해제한다.
- ```DROP```는 테이블 구조를 완전히 삭제한다.
- ```DELETE```는 모든 데이터를 제거한다.

<br/>

# ROLLBACK

- ```ROLLBACK```은 커밋되지 않은 상위의 모든 트랜잭션을 되돌린다.

ex)
```sql
BEGIN TRANSACTION 
...
BEGIN TRANSACTION
...
BEGIN TRANSACTION
...
ROLLBACK -- 제일 처음 트랜잭션까지 롤백
-- SAVEPOINT를 지정하면 해당 라인까지만 롤백한다.
```

<br/>

# NULL 연산

- NULL 값이 포함된 4칙 연산의 결과는 모두 NULL이다.
- NULL 값과의 비교연산은 모두 FALSE를 리턴한다.

<br/>

# ORACLE '' 삽입

- ORACLE에서 ```INSERT```문 중 ```''```   값을 입력하면 공백문자가 아닌 NULL이 입력된다.

<br/>

# ORACLE 날짜 연산

- ORACLE에서 날짜 연산은 숫자 연산과 같다
- ```1/24/60```은 1분을 의미한다.
- ```1/24/(60/10)```은 10분을 의미한다.

<br/>

# DUAL 테이블 

- 사용자 SYS가 소유하며 모든 사용자가 액세스 가능하다.
- 일종의 DUMMY 테이블이다.
- DUMMY라는 문자열 유형의 칼럼에는 'X'라는 값이 들어있는 행을 1건 포함하고 있다.

<br/>

# CASE 문

- CASE문은 두가지 유형으로 나뉜다.

```sql
-- SEARCHED_CASE_EXPRESSION
CASE WHEN LOC = 'NEW YOCK' THEN 'EAST'

-- SIMPLE_CASE_EXPRESSION
CASE LOC WHEN 'NEW YOCK' THEN 'EAST'
```

<br/>

# 0 연산

- 분모가 0이 들어가는 경우 연산 자체가 에러를 발생한다.
- 0/300 = 0
- 300/0 = 에러발생
- 300/NULL = NULL

<br/>

# NULL 우선순위

- ORACLE에서는 NULL 값을 가장 큰 값으로 간주하여 오름차순으로 정렬했을 경우 가장 마지막에 위치한다.
- SQL SERVER에서는 NULL값을 가장 작은 값으로 간주하며 오름차순으로 정렬했을 경우 가장 위에 위치한다.

<br/>

# LIKE 조인

- LIKE 연산을 통한 조인이 가능하다.

[EMP_TBL]

|EMPNO|ENAME|
|---|---|
|1000|SMITH|
|1050|ALLEN|
|1100|SCOTT|

<br/>

[RULE_TBL]

|RULE_NO|RULE|
|---|---|
|1|S%|
|2|%T%|


```sql
SELECT COUNT(*) CNT
FROM EMP_TBL A, RULE_TBL B
WHERE A.ENAME LIKE B.RULE
```

- LIKE 조인으로 인해 총 4건의 결과가 도출된다.

1. SMITH - S%
2. SCOTT - S%
3. SMITH - %T%
4. ALLEN - %T%

<br/>

# UNION, UNION ALL

- UNION
  - 중복 제거
- UNION ALL
  - 중복 제거 X
  
<br/>

# PRIOR

- CONNECT BY, SELECT, WHERE절에 사용되며, 현재 읽은 칼럼을 지정한다.
- PRIOR 자식 = 부모
  - 계층구조에서 부모 데이터 -> 자식 데이터로 순방향 전개한다.
- PRIOR 부모 = 자식
 - 자식 데이터 -> 부모 데이터로 역방향 전개를 한다.
 
<br/>

# LEVEL

- Oracle 계층형 질의에서 루트 노드의 LEVEL 값은 1이다.

<br/>

# START WITH

- START WITH절은 계층 구조 전개의 시작 위치를 지정하는 구문이다.
- 즉, 루트 데이터를 지정한다.

<br/>

# ORDER SIBLINGS BY

- 형제 노드(동일 LEVEL) 사이에서 정렬을 수행한다.

<br/>

# CONNECT BY

- WHERE절과 다르게 START WITH 절에서 필터링된 시작 데이터는 결과목록에 포함되어지며, 이후 CONNECT BY 절에 의해 필터링 된다.  


<br/>

# SQL Server 계층형 질의문

- SQL Server에서의 계층형 질의문은 CTE를 재귀 호출함으로써 계층 구조를 전개한다.
- SQL Server에서의 계층형 질의문은 앵커 멤버를 실행하여 기본 결과 집합을 만들고 이후 재귀 멤버를 지속적으로 실행한다.


<br/>

# 서브 쿼리

- 단일 행 서브쿼리
  - 결과가 항상 1건 이하인 서브쿼리
- 다중 행 서브쿼리
  - 결과가 여러 건인 서브쿼리
- 다중 컬럼 서브쿼리
  - 결과로 여러 컬럼을 반환한다. 메인쿼리의 조건절에 여러 컬럼을 동시에 비교할 수 있다.
  - 서브쿼리와 메인쿼리에서 비교하고자 하는 컬럼 개수와 컬럼 위치가 동일해야한다.
  - SQL Server에서는 현재 지원하지 않는 기능이다.
 
 <br/>
 
- 주의사항
  - 괄호로 감싸서 사용한다
  - 단일 행, 복수 행 비교 연산자와 함께 사용가능하다
  - ORDER BY 사용하지 못한다.
  
  
<br/>

# HAVING절

- GROUP BY 및 집계함수를 사용하지 않고 HAVING 절을 사용하였다고 해서 오류가 발생하진 않는다.

<br/>

# 비 연관 서브 쿼리

- 비 연관 서브 쿼리는 주로 메인쿼리에 값을 제공하기 위한 목적으로 사용된다.

<br/>

# 인라인 뷰

- from절에서 사용되는 서브쿼리를 인라인 뷰라고 한다.
- 서브쿼리 결과가 마치 실행 시에 동적으로 생성된 테이블인 것처럼 사용할 수 있다.
- 인라인 뷰는 sql문이 실행될 때만 임시적으로 생성되는 동적 뷰이기때문에 데이터베이스에 해당 정보가 저장되지 않는다.

<br/>

# 뷰 사용 장점

- 독립성
  - 테이블 구조가 변경되어도 뷰를 사용하는 응용 프로그램은 변경하지 않아도 된다.
- 편리성
  - 복잡한 질의를 뷰로 생성함으로써 관련 질의를 단순하게 작성할 수 있다.
  - 해당 형태의 sql문을 자주 사용할 때 뷰를 이용하면 편리하게 사용할 수 있다.
- 보안성
  - 직원의 급여정보와 같이 숨기고 싶은 정보가 존재한다면, 뷰를 생성할 때 해당 컬럼을 빼고 생성함으로써 정보를 감출 수 있다.
  
  
<br/>

# LAG, LEAD

- LAG는 현재 읽혀진 데이터의 이전 값을, LEAD는 이후 값을 알아내는 함수이다.

ex)
```sql
SELECT EMPLOYEE_ID,
       DEPARTMENT_ID,
       LAST_NAME,
       SALARY,
       LAG(SALARY,2) OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY ) AS BEFORE_SALARY
FROM HR.EMPLOYEES
WHERE EMPLOYEE_ID < 110;

->

EMPLOYEE_ID DEPARTMENT_ID LAST_NAME SALARY BEFORE_SALARY
107 60 Lorentz 4200.00 
106 60 Pataballa 4800.00 
105 60 Austin 4800.00 4200
104 60 Ernst 6000.00 4800
103 60 Hunold 9000.00 4800
102 90 De Haan 17000.00 
101 90 Kochhar 17000.00 
100 90 King 24000.00       17000
109 100 Faviet 9000.00 
108 100 Greenberg 12000.00 
```



<br/>

# ROLE

- ROLE는 많은 DBMS 사용자에게 개별적으로 많은권한을 부여하는 번거로움과 어려움을 해소하기 위해 다양한 권한을 하나의 그룹으로 묶어놓은 논리적인 권한의 그룹이다.

<br/>

# PL/SQL 특징

- Block 구조로 되어있어 각 기능별로 모듈화가 가능하다.
- 변수, 상수 등을 선언하여 SQL 문장 간 값을 교환한다.
- IF, LOOP 등 절차형 언어를 사용하여 절차형 프로그래밍이 가능하다
- DBMS 정의 에러나 사용자 정의 에러를 정의하여 사용할 수 있다.
- Oracle에 내장되어 있어 Oracle과 PL/SQL을 지원하는 어떤 서버로도 프로그램을 옮길 수 있다.
- 응용 프로그램의 성능을 향상시킨다.
- 여러 SQL문장을 Block으로 묶고 한 번에 Block 전부를 서버로 보내기 때문에 통신량을 줄일 수 있다.
- 동적 SQL 또는 DDL 문장을 실행할 때 ```EXECUTE IMMEDIATE```를 사용해야 한다.

<br/>

# 트리거

- 데이터의 무결성과 일관성을 위해 사용자 정의 함수를 사용하는 것은 트리거의 용도이다.
- 트리거는 프로시저와 달리 커밋이나 롤백같은 TCL을 사용할 수 없다.
- 트리거는 테이블과 뷰, 데이터베이스 작업을 대상으로 정의할 수 있으며, 전체 트랜잭션 작업에 대해 발생되는 트리거와 각 행에 대해서 발생되는 트리거가 있다.

<br/>

# 저장 모듈(Stored Module)

- SQL 문장을 데이터베이스 서버에 저장하여 사용자와 애플리케이션 사이에서 공유할 수 있도록 만든 일종의 SQL 컴포넌트 프로그램이다.
- 독립적으로 실행되거나 다른 프로그램으로부터 실행될 수 있는 완전한 실행 프로그램이다.
- Oracle의 저장 모듈에는 프로시져, 사용자 정의 함수, 트리거가 있다.

<br/>

# 트리거와 프로시저 차이점

|프로시저|트리거
|---|---
| CREATE 프로시저 문법 사용 | CREATE 트리거 문법 사용
| EXECUTE 명령어로 실행 | 생성 후 자동 실행
| 커밋, 롤백 실행 가능 | 커밋, 롤백 실행 불가능
 
 
<br/>

# 엔터티 특징

- 반드시 해당 업무에서 필요하고 관리하고자하는 정보여야 한다
- 유일한 식별자에 의해 식별이 가능해야한다
- 영속적으로 존재하는 인스턴스의 집합이여야 한다
- 엔터티는 업무 프로세스에 의해 이용돼야한다
- 엔터티는 반드시 속성이 있어야한다
- 엔터티는 다른 엔터티와 최소 한 개 이상의 관계가 있어야 한다


<br/>

# 엔터티, 인스턴스, 속성, 속성값의 관계

- 한 개의 엔터티는 두 개 이상의 인스턴스의 집합이어야 한다
- 한 개의 엔터티는 두 개이상의 속성을 갖는다
- 한 개의 속성은 한 개의 속성값을 갖는다.

<br/>

# 도메인

- 각 속성은 가질 수 있는 값의 범위가 있는데 이를 그 속성의 도메인이라 한다.
- 엔터티 내에서 속성에 대한 데이터 타입과 크기 그리고 제약사항을 지정하는 것이다.

<br/>

# 관계의 표기법

- 관계명
  - 관계의 이름
- 관계차수
  - 1:1 1:M, M:N
- 관계선택사양
  - 필수관계, 선택관계
 
 
<br/>

# 주식별자의 특징

- 유일성
  - 주식별자에 의해 엔터티내에 모든 인스턴스들을 유일하게 구분함
- 최소성
  - 주식별자를 구성하는 속성의 수는 유일성을 만족하는 최소의 수가 되어야 함
- 불변성 
  - 주식별자가 한 번 특정 엔터티에 지정되면 그 식별자의 값은 변하지 않아야 함
- 존재성
  - 주식별자가 지정되면 반드시 데이터 값이 존재(NULL 안됨)
  
  
# 식별자와 비식별자관계 비교

|항목|식별자관계|비식별자관계|
|---|---|---|
|목적|강한 연결관계 표현 | 약한 연결관계 표현|
|자식 주식별자 영향|자식 주식별자의 구성에 포함됨|자식 일반 속성에 포함됨|
| 표기법 | 실선 표현| 점선 표현|
|연결 고려사항|- 반드시 부모엔터티 종속 <br/> - 자식 주식별자구성에 부모 주식별자포함 필요 <br/> - 상속받은 주식별자속성을 타 엔터티에 이전 필요 | - 약한 연결관계 표현 <br/> - 자식 주식별자구성을 독립적으로 구성 <br/> - 자식 주식별자구성에 부모 주식별자 부분 필요 <br/> - 상속받은 주식별자속성을 타 엔터티에 차단 필요 <br/> - 부모쪽의 관계참여가 선택관계 |

<br/>

# 반정규화 절차

- 반정규화 대상조사
  - 범위처리빈도수 조사
  - 대량의 범위 처리 조사
  - 통계성 프로세스 조사
  - 테이블 조인 개수

- 다른 방법유도 검토
  - 뷰 테이블
  - 클러스터링 적용
  - 인덱스의 조정
  - 응용어플리케이션
  
- 반정규화 적용
  - 테이블 반정규화
  - 속성의 반정규화
  - 관계의 반정규화
  
<br/>

# 슈퍼/서브 타입 데이터 모델의 변환 기술

- 개별로 발생되는 트랜잭션에 대해서는 개별 테이블로 구성
- 슈퍼타입 + 서브타입에 대해 발생되는 트랜잭션에 대해서 슈퍼타입 + 서브타입 테이블로 구성
- 전체를 하나로 묶어 트랜잭션이 발생할 때는 하나의 테이블로 구성

<br/>

# 성능 데이터 모델링 수행 절차

- 데이터모델링할 때 정규화를 정확히 수행
- 데이터베이스 용량산정 수행
- 데이터베이스에 발생되는 트랜잭션의 유형을 파악
- 용량과 트랜잭션의 유형에 따라 반정규화 진행
- 이력모델의 조정, PK/FK 조정, 슈퍼타입/서브타입 조정 등 수행
- 성능관점에서 데이터모델 검증

<br/>

# GIS(Global Single Instance)

- GIS는 통합된 한 개의 인스턴스 즉, 통합 데이터베이스 구조를 의미하므로, 분산데이터베이스와는 대치되는 개념
  
<br/>

# VARCHAR 비교

- 길이가 다르다면 짧은 것이 끝날 때까지만 비교한 후에 길이가 긴 것이 크다고 판단한다
- CHAR형은 길이가 다르다면 작은 쪽에 SPACE 를 추가하여 길이를 같게 한 후에 비교한다

<br/>

# OR, AND

- SQL 에서 소괄호 없이 AND OR 을 사용하면 AND -> OR 의 순서로 처리된다


<br/>

# LPAD, RPAD

- PAD 함수는 지정한 길이 만큼 왼쪽, 오른쪽부터 특정문자로 채워준다.

```sql
select RPAD('A', 10, 0)  
from dual;

-- A000000000

select LPAD('A', 10, 0)  
from dual;

-- 000000000A

select LPAD('A', 0, 0)  
from dual;

-- NULL
```


<br/>

# 문자열 SUM

- SUM 함수에서는 NULL 합산이 적용되지 않음

```sql
COL1 COL2
A	100
B	200
C	300
C	400


select SUM(case COL1 when 'A' then 1 END) 
from TAB1;

-- 1
```

- 다른 B, C, C 값들은 CASE문에 의해 NULL로 치환되어 1 + NULL + NULL + NULL = NULL 이 될 것 같지만 NULL은 제외하고 합산하여 답은 1이 된다. 


<br/>

# IN, NOT IN 과 NULL

- IN절과 NOT IN절 안의 NULL을 조심하자

```sql
-- NULL은 제외하고 '낚시'인 ROW만 조회
hobby in ( null, '낚시')
```

- ```hobby in ( null, '낚시')``` => ```where hobby = null or hobby = '낚시'```로 변환
  - null은 is null 이나 is not null로 비교되어야 하지만  hobby = null로 비교되기 때문에 비교대상에서 SKIP된다
  
```sql
-- 아무 ROW도 선택되지 않는다
hobby not in(낚시, null)
```

- ```hobby not in(낚시, null)``` => ```where hobby <> null and hobby <> '낚시'```로 변환
  - null은 비교될 때 is null 이나 is not null로 비교되어야 하므로 hobby <> null 은 항상 false를 반환한다
  - and 조건문 때문에 0개의 ROW가 선택된다.
  
  
<br/>

# 상호연관서브쿼리

- 상호연관서브쿼리는 서브쿼리와 메인쿼리간 알리야스를 공유하는 쿼리를 의미한다.
- 이 쿼리는 from절에서 사용하면 에러가 발생한다.
- where절에서 사용가능


<br/>

# COUNT(*)와 HAVING

- COUNT(*)은 대부분 결과값이 정수형이다
- HAVING절에서 반환된 레코드가 없을 시, 공집합이 반환된다.

ex)필수관계, 선택관계

```sql
SELECT COUNT(*) 
FROM dual
HAVING 4>4;

-- 공집합 반환

SELECT COUNT(*) 
FROM dual
WHERE 4>4;

-- 0 반환
```