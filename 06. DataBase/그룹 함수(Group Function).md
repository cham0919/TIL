# GROUP BY

- GROUP BY 절을 통해 소그룹별 기준을 정한 후, SELECT 절에 집계함수를 사용한다.
- 집계 함수의 통계 정보는 NULL 값을 가진 행을 제외하고 수행한다.
- SELECT절과 달리 ALIAS 명을 사용할 수 없다.
- 집계 함수는 WHERE 절에는 올 수 없다. 
  - 집계 함수를 사용할 수 있는 GROUP BY절보다 WHERE 절이 먼저 수행된다.
- HAVING절은 GROUP BY절의 기준 항목이나 소그룹의 집계 함수를 이용한 조건을 표시할 수 있다.
- 중첩된 그룹함수의 경우 최종 결과값은 1건이 될 수 밖에 없기 때문에 GROUP BY에 기술된 컬럼은 SELECT절에 기술될 수 없다.

ex) 
```sql
-- 에러발생
SELECT 메뉴ID, 사용유형코드, AVG(COUNT(*))
FROM 시스템사용이력
GROUP BY 메뉴ID, 사용유형코드
```


<br/>


# 예시

부서명과 업무명을 기준으로 사원수와 급여 합을 집계한 일반적인 GROUP BY SQL 문장을 수행한다.

![image](https://user-images.githubusercontent.com/61372486/128892782-4e9881d7-d8fa-41b4-a904-1b162c5b364c.png)


# ROLLUP

- ROLLUP은 GROUPBY 칼럼에 대하여 Subtotal을 만들어진다.
- ROLLUP을 할 때 GROUP BY구에 칼럼이 두 개 이상 오면 순서에 따라서 결과가 달라진다.
- ROLLUP에 의해서 생성되는 Subtotal은 Grouping Columns의 수를 N개 라고 했을 때, N+1 Level의 Subtotal이 생성된다. 
- 잊지 말아야 할 것은, ROLLUP의 인수는 계층 구조이므로 인수 순서가 바뀌면 수행 결과도 바뀌게 되므로 인수의 순서에도 주의해야 한다.


ex)

```sql
GROUP BY ROLLUP(DNAME,JOB)
= GROUP BY DNAME,JOB
UNION ALL
GROUP BY DNAME
UNION ALL
모든 집합 그룹 결과
```


<br/>




## 예시

- 2개 컬럼을 그룹핑 했으므로, 3개의 소계 행이 생길 것이다.
  - Subtotal은 Grouping Columns의 수를 N개 라고 했을 때, N+1 Level의 Subtotal이 생성됩니다.
  - 따로 정렬을 하지 않아도 자동 정렬되서 나옵니다.
  - ROLLUP의 경우 계층 간 집게에 대해서는 LEVEL 별 순서(L1->L2->L3)를 정렬하지만, 계층 내 GROUP BY 수행시 생성되는 표준 집계에는 별도의 정렬을 지원하지 않습니다.
  
  
L1 - GROUP BY 수행시 생성되는 표준 집계 (9건)
L2 - DNAME 별 모든 JOB의 SUBTOTAL (3건)  
L3 - GRAND TOTAL (마지막 행, 1건)
  
![image](https://user-images.githubusercontent.com/61372486/128894113-2125d2ea-7778-4f16-b564-96430d64206f.png)
  


<br/>


# GROUPING

- GROUPING 함수는 ROLLUP, CUBE, GROUPING SETS에서 생성되는 합계값을 구분하기 위해서 만들어진 함수이다.
- ROLLUP 이나 CUBE에 의한 소계가 계산된 결과에는 GROUPING(EXPR) = 1 이 표시되고, 그 외의 결과에는 GROUPING(EXPR) = 0 이 표시된다.

```sql
SELECT DEPTNO, 
DECODE(GROUPING(DEPTNO), 1, '전체 합계') TOT, JOB, 
DECODE(GROUPING(JOB), 1, '부서합계) T_DEPT, SUM(SAL)
FROM EMP
GROUP BY ROLLUP(DEPTNO, JOB);
```

<br/>

## 예시

- ROLLUP 함수를 추가한 집계 보고서에서 집계 레코드를 구분할 수 있는 GROUPING 함수가 추가된 SQL 문장을 작성하시오.

- ROLLUP에 의해 소계된 컬럼은 GROUPING 함수에 의해 1로 표시됩니다. 아닌 경우는 0

- 이제 1과 0의 값에 따라 DECODE 조건을 걸어 소계가 걸리면 빈 공란으로 두지 않고 명칭을 부여합니다.


![image](https://user-images.githubusercontent.com/61372486/128894433-7c574a0a-90b1-4847-9e0a-84cc502be340.png)

<br/>

- ROLLUP 함수를 추가한 집계 보고서에서 집계 레코드를 구분할 수 있는 GROUPING 함수가 추가된 SQL 문장을 작성하시오.
   -  JOB만 ROLLUP, DNAME은 GROUP BY 기존 방식

- JOB에 대해서만 소계가 출력되고, DNAME은 따로 소계가 없다. 즉 L3 - GRAND TOTAL (마지막 행, 1건) 가 없습니다.

![image](https://user-images.githubusercontent.com/61372486/128894603-682ae092-f011-4ba2-91f4-71f2ee79062f.png)




# GROUPING SETS

- GROUPING SETS 함수는 GROUP BY에 나오는 칼럼 순서와 관계없이 다양한 소계를 만들 수 있다.
- GROUPING SETS 함수는 GROUP BY에 나오는 칼럼 순서와 관계없이 개별적으로 모두 처리한다.
-  아래 예시에는 DEPTNO 합계와 JOB 합계가 개별적으로 조회된다.

- GROUPING SETS 함수는 쉽게 GROUP BY 한 SQL 들을 UNION ALL 해서 보여주는 결과와 같다.

```sql
SELECT DEPTNO, JOB, SUM(SAL)
FROM EMP
GROUP BY GROUPING SETS(DEPTNO, JOB);
```

<br/>


## 예제

- 일반 GROUP BY SQL를 이용하여 부서별, 직업별 인원수와 연봉 합계를 구하여라.
  - DNAME 그룹핑 한 결과와 JOB을 그룹핑 한 결과를 합친 결과가 나온다  

**UNION ALL 사용**

![image](https://user-images.githubusercontent.com/61372486/128894729-7a100e3c-6728-4a23-8286-325ea9e88f31.png)

<br/>

**GROUPING SETS 사용**

![image](https://user-images.githubusercontent.com/61372486/128894772-5024e0df-2718-4129-9f66-7c12411df272.png)

-  GROUPING SETS(인수1, 인수2) 즉, 인수1 그룹핑 결과  + 인수2 그룹핑 결과를 합친 결과
-  참고로!! GROUPING SETS(인수1, 인수2) 와 GROUPING SETS(인수2, 인수1)의 결과를 서로 같다
- 즉, GROUPING SETS 인수들은 평등한 관계이므로 인수의 순서가 바뀌어도 결과는 같다.

ex)

```sql
GROUP BY GROUPING SET(DNAME,JOB)
= GROUP BY DNAME
UNION ALL
GROUP BY JOB
```

<br/>

- 부서-직업-매니저 별, 부서-직업 별, 직업-매니저 별 집계를 GROUPING SETS 함수를 이용해서 구해라.
  - 괄호를 기준으로, 부서-직업-매니저 별, 부서-직업 별, 직업-매니저 별 집계결과를 얻을 수 있다
  
  ![image](https://user-images.githubusercontent.com/61372486/128895557-140faead-c4f4-46c2-8dbc-ddd0e031d20e.png)
  ![image](https://user-images.githubusercontent.com/61372486/128894833-cc041119-38ff-42d7-8fb5-e843d6655904.png)
  
<br/>  

# CUBE 함수

- CUBE는 CUBE 함수에 제시한 칼럼에 대해서 결합 가능한 모든 집계를 계산한다.
- 다차원 집계를 제공하여 다양하게 데이터를 분석할 수 있게 한다.
- 즉, 조합될 수 있는 경우의 수가 모두 조합되는 것이다.

ex)

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

## 예시

- CUBE 함수를 추가한 집계 보고서에서 집계 레코드를 구분할 수 있는 GROUPING 함수가 추가된 SQL 문장을 작성하시오.
  -  부서명, 직무별 전체직원수와 전체 연봉을 구하시오.
  
-  CUBE는 모든 경우의 Subtotal 집계를 하며, GROUPING COLUMNS 의 수가 N개라고 하면, 2의 N승 LEVEL의 Subtotal을 생성하게 된다.

![image](https://user-images.githubusercontent.com/61372486/128894664-2cd56aab-ee2f-4b3e-bc9a-694c6eab6134.png)

<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [[SQL 활용]그룹 함수(Group Function)](https://velog.io/@yewon-july/Group-Function-6gjqtwdv)
- [[SQLD] 제2장 - 그룹함수(CUBE, ROLLUP, GROUPING SETS)](https://limkydev.tistory.com/151?category=974039)