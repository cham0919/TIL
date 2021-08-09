# ROWNUM

- ROWNUM은 ORACLE 데이터베이스의 SELECT문 결과에 대해서 논리적인 일렬번호를 부여한다.

- 조회되는 행 수를 제한할 때 많이 사용된다.

- 화면에 데이터를 출력할 때 부여되는 __논리적 순번))이다.

- 만약 ROWNUM을 사용해서 페이지 단위 출력을 하기 위해서는 인라인 뷰(Inline View)를 사용해야 한다.

-  ```SELECT * FROM EMPLOYEE WHERE ROWNUM<=3```
   - 3 행 이하까지 조회한다.
   
   
### Inline View
- 인라인뷰는 SELECT문에서 FROM절에 사용되는 서브쿼리(Sub Query)를 의미한다.


### ROWNUM, TOP, LIMIT
- Oracle: ROWNUM
- SQL Server: TOP
  - ```SELECT TOP(3) FROM EMPLOYEE;```
- MySQL: Limit
  - ```SELECT * FROM EMPLOYEE LIMIT 3;```
  
<br/>

# ROWID

- ROWID는 ORACLE 데이터베이스 내에서 데이터를 구분할 수 있는 유일한 값이다.

- ROWID는 ```SELECT ROWID, EMPNO FROM EMPLOYEE```와 같은 SELECT문으로 확인할 수 있다.

- ROWID를 통해서 데이터가 어떤 데이터 파일, 어느 블록에 저장되어 있는지 알 수 있다.

- ROWID는 테이블에 데이터를 입력하면 자동으로 생성되는 값이다.

<br/>

## ROWID 구조

|구조|길이|설명|
|---|---|---|
|오브젝트 번호|1~6|오브젝트(Object) 별로 유일한 값을 가지고 있으며, 해당 오브젝트가 속해 있는 값이다.|
|상대 파일 번호|7~9|테이블스페이스(Tablespace)에 속해 있는 데이터 파일에 대한 상대 파일번호이다.|
|블록 번호|10~15|데이터 파일 내부에서 어느 블록에 데이터가 있는지 알려준다.|
|데이터 번호|16~18|데이터 블록에 데이터가 저장되어 있는 순서를 의미한다.|



<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [[SQL 기본]ROWNUM과 ROWID](https://velog.io/@yewon-july/ROWNUM-ROWID)