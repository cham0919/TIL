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

