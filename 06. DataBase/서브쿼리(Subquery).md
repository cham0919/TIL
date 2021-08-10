# Main Query와 Subquery

- Subquery는 SELECT문 내에 다시 SELECT문을 사용하는 SQL 문이다.

- Subquery의 형태는 FROM 구에 SELECT문을 사용하는 인라인 뷰(View)와 SELECT문에 Subquery를 사용하는 스칼라 서브쿼리(Scala Subquery) 등이 있다.

  - ```SELECT * FROM (SELECT ROWNUM NUM, SNAME FROM STUDENT) a WHERE NUM < 5```
  - FROM 구에 SELECT문을 사용하여 가상의 테이블을 만드는 효과를 얻을 수 있다.
  
- WHERE 구에 SELECT문을 사용하면 서브쿼리(Subquery)라고 한다.
   - ```SELECT * FROM STUDENT WHERE MAJORNO = (SELECT MAJORNO FROM MAJOR WHERE MAJORNO=10);```
   - 서브쿼리 밖에 있는 SELECT문은 메인쿼리(Main Query)이다.
   
<br/>

# 단일 행 서브쿼리와 다중 행 서브쿼리

- 서브쿼리(Subquery)는 반환하는 행 수가 한 개인 것과 여러 개인 것에 따라서 단일 행 서브쿼리와 멀티 행 서브쿼리로 분류된다.

- 단일 행 서브쿼리는 단 하나의 행만 반환하는 서브쿼리로 비교 연산자(=, <, <=, >=, <>)를 사용한다.

- 다중 행 서브쿼리는 여러 개의 행을 반환하는 것으로 IN, ANY, ALL, EXISTS를 사용해야 한다.

<br/>

|서브쿼리 종류|설명|
|---|---|
|단일 행 서브쿼리|	- 서브쿼리를 실행하면 그 결과는 반드시 한 행만 조회된다.<br/> - 비교 연산자인 =, <, <=, >, >=, <>를 사용한다.|
|다중 행 서브쿼리|	- 서브쿼리를 실행하면 그 결과는 여러 개의 행이 조회된다. <br/> - 다중 행 비교 연산자인 IN, ANY, ALL, EXISTS를 사용한다.|

<br/>


# 단일 행 Subquery

|단일 행 연산| 설명|
|---|---|
|스칼라(Scala) Subquery|	- 스칼라 서브쿼리는 반드시 한 행과 한 컬럼만 반환하는 서브쿼리이다. <br/> - 만약 여러 행이 반환되면 오류가 발생한다.|

<br/>

# 다중 행(Multi Row) Subquery

- 다중 행 서브쿼리는 서브쿼리 결과가 여러 개의 행을 반환하는 것으로, 다중 행 연산자를 사용해야 한다.

|다중 행 연산|설명|
|---|---|
|IN(Subquery)|	Main Query의 비교조건이 Subquery의 결과 중 하나만 동일하면 참이 된다.|
|ALL(Subquery)|	- Main Query와 Subquery의 결과가 모두 동일하면 참이 된다. <br/> - < ALL: 최솟값을 반환한다. <br/> - > ALL: 최댓값을 반환한다. |
|ANY(Subquery)|	- Main Query의 비교 조건이 Subquery의 결과 중 하나 이상 동일하면 참이 된다. <br/> - < ANY: 하나라도 크게 되면 참이된다. <br/> - > ANY: 하나라도 작게 되면 참이 된다.|
|EXISTS(Subquery)|	- Main Query와 Sub Query의 결과가 하나라도 존재하면 참이 된다. <br/> - EXISTS의 결과는 참과 거짓이 반환된다. |
|연관(Correlated) Subquery|	- 연관 Subquery는 Subquery 내에서 Main Query 내의 칼럼을 사용하는 것을 의미한다.|

<br/>

### IN

- IN은 반환되는 여러 개의 행 중에서 하나만 참이 되어도 참인 연산이다.

```sql
SELECT ENAME, DNAME, SAL
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
AND EMP.EMPNO
IN(SELECT EMPNO FROM EMP WHERE SAL>20000);
```

<br/>

### ALL

- 메인쿼리와 서브쿼리의 결과가 모두 동일하면 참이 된다.

- DEPTNO가 20, 30보다 작거나 같은 것을 조회한다.

```sql
SELECT * FROM EMP
WHERE DEPTNO <= ALL(20, 30);
```

<br/>

### EXISTS

- EXISTS는 SUBQUERY로 어떤 데이터 존재 여부를 확인하는 것이다.

- 즉, EXISTS의 결과는 참과 거짓이 반환된다.

- 보통 where절에서 사용되며, ```where (not) exists (select 'X' from ~ )``` 과 같이 쓰인다.
  - 'X'는 null이 아닌 다른 알파벳으로 사용해도 무방하다.

- 급여가 2000이상이 있으면 참이 반환되고, 없으면 거짓이 반환된다.

```sql
SELECT ENAME, DNAME, SAL
FROM EMP, DEPT
WHERE EMP.DEPTNO = DEPT.DEPTNO
AND EXISTS(SELECT 1 FROM EMP WHERE SAL>2000);
```

<br/>

### 스칼라 서브쿼리

- 스칼라 Subquery는 반드시 한 행과 한 칼럼만 반환하는 서브쿼리이다.

- 만약 여러 행이 반횐되면 오류가 발생한다.

```sql
SELECT ENAME AS "name", SAL AS "SALARY",
(SELECT AVG(SAL) FROME MP) AS "AVERAGE"
FROM EMP
WHERE EMPNO = 1000;
```

<br/>

### 연관(Correlated) Subquery

- 연관 Subquery는 Subquery 내에서 Main Query 내의 칼럼을 사용하는 것을 의미한다.

```sql
FROM EMP a
FROM a.DEPTNO =
(SELECT DEPTNO FROM DEPT b
WHERE b.DEPTNO  = a.DEPTNO);
```


<br/><br/><br/><br/><br/><br/><br/>


---
Reference

- [[SQL 활용]서브쿼리(Subquery)](https://velog.io/@yewon-july/Subquery)
