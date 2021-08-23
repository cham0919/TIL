# 윈도우 함수

- 윈도우 함수는 행과 행 간의 관계를 정의하기 위해서 제공되는 함수

- 윈도우 함수를 사용해서 순위, 합계, 평균, 행 위치 등을 조작할 수 있다.

- 윈도우 함수는 GROUP BY 구문과 병행하여 사용할 수 없다.
  - GROUP BY 질의 집합을 원본으로 하는 데이터를 함께 사용한다면 함께 사용하더라도 오류가 발생하지 않는다.

- 윈도우 함수로 인해 결과 건수가 줄어들지는 않는다.

- WINDOW 함수의 PARTITION 구문과 GROUP BY 구문은 둘 다 파티션을 분할한다는 의미에서는 유사하다.

- sum, max, min 등과 같은 집계 윈도우 함수를 사용할 때 윈도우 절과 함께 사용하면 집계 대상이 되는 레코드 범위를 지정할 수 있다.

<br/>

# 윈도우 함수 구조

```sql
SELECT WINDOW_FUNCTION(ARGUMENTS)
  OVER (PARTITION BY 칼럼
     ORDER BY WINDOWING절)
FROM 테이블명;
```


|구조|설명|
|---|---|
|ARGUMENTS(인수)|	윈도우 함수에 따라서 0~N개의 인수를 설정한다.|
|PARTITION BY|	전체 집합을 기준에 의해 소그룹으로 나눈다|
|ORDER BY|	어떤 항목에 대해서 정렬한다|
|WINDOWING|	- 행 기준 범위를 정한다 <br/> - ROWS는 물리적 결과의 행 수이고 RANGE는 논리적인 값에 의한 범위이다. |

<br/>

# WINDOWING

|구조|설명|
|---|---|
|ROWS|	부분집합인 윈도우 크기를 물리적 단위로 행의 집합을 지정한다.|
|RANGE|	논리적 주소에 의해 행 집합을 지정한다.|
|BETWEEN~AND|	윈도우의 시작과 끝 위치를 지정한다.|
|UNBOUNDED PRECEDING|	윈도우 시작 위치가 첫 번째 행임을 의미한다.|
|UNBOUNDED FOLLOWING|	윈도우 마지막 위치가 마지막 행임을 의미한다.|
|CURRENT ROW|	윈도우 시작 위치가 현재 행임을 의미한다. <br/> (데이터가 인출된 현재 행을 의미한다.)|

<br/>

## WINDOWING 예시

### 전체합계

```sql
SELECT EMPNO, ENAME, SAL
SUM(SAL) OVER(ORDER BY SAL
    ROWS BETWEEN UNBOUNDED PRECEDING
    AND UNBOUNDED FOLLOWING) TOTSAL
FROM EMP;
```

- UNBOUNDED PRECEDING은 처음 행을 의미하며, UNBOUNDED FOLLOWING은 마지막 행을 의미한다. 그러므로 TOTSAL의 처음부터 마지막까지의 합계(SUM(SAL))를 계산한 것이다.

<br/>

### 누적합계

```sql
SELECT EMPNO, ENAME, SAL
SUM(SAL) OVER(ORDER BY SAL
    ROWS BETWEEN UNBOUNDED PRECEDING
    AND CURRENT ROW)TOTSAL
FROM EMP;
```

- 처음부터 CURRENT ROW까지의 합계를 계산한다. 즉, 누적합계인 것이다.
- 1째 행의 값이 1이었으면 1, 2째 행의 값이 2였으면 1+2=3, 3째 행의 값이 3이었으면 1+2+3=4 이렇게 계산된다.

<br/>

# 순위 함수(RANK Function)

- 윈도우 함수는 특정 항목과 파티션에 대해서 순위를 계산할 수 있는 함수를 제공한다.

- 순위 함수는 RANK, DENSE_RANK, ROW_NUMBER 함수가 있다.

|순위 함수|설명|
|---|---|
|RANK|	- 특정항목 및 파티션에 대해서 순위를 계산한다. <br/> - 동일한 순위는 동일한 값이 부여된다.|
|DENSE_RANK|	- 동일한 순위를 하나의 건수로 계산한다.|
|ROW_NUMBER|	- 동일한 순위에 대해서 고유의 순위를 부여한다.|

<br/>

## RANK FUNCTION 예시

### 내림차순 및 파티션

```sql
SELECT ENAME, SAL
    RANK() OVER (ORDER BY SAL DESC) ALL_RANK,
    RANK() OVER (PARTITION BY JOB ORDER BY SAL DESC) JOB_RANK,
FROM EMP;
```

- RANK함수는 순위를 계산하며, 동일한 순위에는 같은 순위가 부여된다.
   - 1, 2, 2, 4, 5, 6, 7 ....
   
- ```RANK() OVER (ORDER BY SAL DESC)```는 SAL로 등수를 계산하고, 내림차순으로 조회하게 한다.

- ```RANK() OVER (PARTITION BY JOB ORDER BY SAL DESC)```는 JOB으로 파티션을 만들고, JOB별로 SAL 순위를 조회하게 한다.

<br/>

### DENSE RANK

```sql
SELECT ENAME, SAL,
  RANK() OVER (ORDER BY SAL DESC) ALL_RANK,
  DENSE_RANK() OVER(ORDER BY SAL DESC) DENSE_RANK
FROM EMP;
```

- DENSE_RANK는 동일한 순위를 하나의 건수로 인식해서 조회한다.
  - 1, 2, 2, 3, 4, 5, 6, 7...

<br/>

### ROW NUMBER

```sql
SELECT ENAME, SAL,
  RANK() OVER (ORDER BY SAL DESC) ALL_RANK,
  ROW_NUMBER() OVER (ORDER BY SAL DESC) ROW_NUM
FROM EMP;
```


- ROW_NUMBER함수는 동일한 순위에 대해서 고유의 순위를 부여한다.
  - DENSE_RANK로는 1, 2, 2, 4, 5이더라도 ROW_NUMBER로는 1, 2, 3, 4, 5 이렇게!!


<br/>


# 집계 함수(AGGREGATE Function)


|집계 함수|설명|
|---|---|
|SUM|	파티션 별로 합계를 계산한다.|
|AVG|	파티션 별로 평균을 계산한다.|
|COUNT|	파티션 별로 행 수를 계산한다.|
|MAX와 MIN|	파티션 별로 최댓값과 최솟값을 계산한다.|

<br/>

## 집계 함수 예시

### SUM

```sql
SELECT ENAME, SAL
SUM(SAL) OVER (PARTITION BY MGR) SUM_MGR
FROM EMP;
```

- 같은 관리자(MGR)에 파티션을 만들고 합계(SUM)을 계산한다.

<br/>

# 행 순서 관련 함수

- 행 순서 관련 함수는 상위 행 값을 하위에 출력하거나 하위 행 값을 상위 행에 출력할 수 있다.

- 특정 위치의 행을 출력할 수 있다.

<br/>

|행 순서|설명|
|---|---|
|FIRST_VALUE|	- 파티션에서 가장 처음에 나오는 값을 구한다. <br/> - MIN 함수를 사용해서 같은 결과를 구할 수 있다.|
|LAST_VALUE|	- 파티션에서 가장 나중에 나오는 값을 구한다.|
|LAG|	- 이전 행을 가지고 온다.|
|LEAD|	- 윈도우에서 특정 위치의 행을 가지고 온다. <br/> - 기본값은 1이다.

<br/>

## 행 순서 관련 함수 예시

### FIRST_VALUE

```sql
SELECT DEPTNO, ENAME, SAL,
FIRST VALUE(ENAME) OVER (PARTITION BY DEPTNO
  ORDER BY SAL DESC ROS UNBOUNDED PRECEDING) AS
    DEPT_A FROM EMP;
```

- 부서로 파티션을 나누고, 부서별로 급여가 가장 많은 직원의 이름을 4번째 칼럼에 배치한다.

<br/>

### LAST_VALUE

```sql
SELECT DEPTNO, ENAME, SAL
  LAST_VALUE(ENAME) OVER (PARTITION BY DEPTNO
  ORDER BY SAL DESC ROWS BETWEEN CURRENT ROW AND
  DEPT A FROM EMP;
```

- LAST_VALUE는 마지막 행을 가지고 오고

- ```BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING```은 부서 내에서 급여가 가장 적은 사람을 가지고 오는 것이다.

<br/>

### LAG

```sql
SELECT DEPTNO, ENAME, SAL, LEAD(SAL, 2)
OVER (ORDER BY SAL DESC) AS PRE_SAL FROM EMP
```

- Lag 함수는 이전 값을 가지고 오는 것이다.

- 해당 행에서 2번째 이전의 값을 가지고 온다.

<br/>

# 비율 관련 함수

- 비율 관련 함수는 누적 백분율, 순서별 백분율, 파티션을 N분으로 분할한 결과 등을 조회할 수 있다.

<br/>

|비율 함수|설명|
|---|---|
|CUME_DIST|	- 파티션 전체 건수에서 현재 행보다 작거나 같은 건수에 대한 누적 백분율을 조회한다. <br/> - 누적 분포상에 위치를 0~1사이의 값을 가진다. |
|PERCENT_RANK|	파티션에서 제일 먼저 나온 것을 0으로 제일 늦게 나온 것을 1로 하여 값이 아닌 행의 순서별 백분율을 조회한다.|
|NTILE|	파티션 별로 전체 건수를 ARGUMENT 값으로 N등분한 결과를 조회한다.|
|RATIO_TO_REPORT|	파티션 내에 전체 SUM(칼럼)에 대한 행 별 칼럼 값의 백분율을 소수점까지 조회한다.|

<br/>

## 비율 관련 함수 예시

### PERCENT_RANK

```sql
SELECT DEPTNO, ENAME, SAL
PERCENT_RANK() OVER(PARTITION BY DEPTNO ORDER BY SAL DESC) AS PERCENT_SAL
FROM EMP;
```

- PERCENT_RANK 함수는 파티션에서 등수의 퍼센트를 구하는 것이다.

- 부서 내의 등수를 백분율로 구한다.

<br/>

### NTILE

```sql
SELECT DEPTNO, ENAME, SAL, NTILE(4)
OVER (ORDER BY SAL DESC) AS N_TILE
FROM EMP;
```

- NTILE(4)는 4등분으로 분할하라는 의미로 급여가 높은 순으로 1~4등분으로 분할한다.

- 급여가 가장 높은 등급에 속하면 1, 가장 낮은 등급에 속하면 4

<br/><br/><br/><br/><br/><br/><br/>


---
Reference

- [[SQL 활용]윈도우 함수(Window Function)](https://velog.io/@yewon-july/Window-Function)