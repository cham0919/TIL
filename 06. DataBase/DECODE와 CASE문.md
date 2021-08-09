# DECODE

- DECODE문으로 IF문을 구현할 수 있다.

- 특정 구문이 참이면 A, 거짓이면 B로 응답한다.

- ```DECODE(EMPNO, 1000, 'TRUE', FALSE)```
   - ```if (EMPNO==1000){return "TRUE";}else{return "FALSE";}```와 같다고 보면 된다.
   
<br/>

# CASE

- CASE문은 IF~THEN ~ELSE-END의 프로그래밍 언어처럼 조건문을 사용할 수 있다.

- 조건을 WHEN구에 사용하고 THEN은 해당 조건이 참이면 실행되고 거짓이면 ELSE구가 실행된다.

- 구조

```sql
CASE [expression]
  WHEN condition_1 THEN result_1
  WHEN condition_2 THEN result_2
  ...
  WHEN condition_n THEN result_n
  ELSE result
END
```

<br/>

### CASE문 예시

```sql
SELECT CASE
  WHEN EMPNO = 1000 THEN 'A'
  WHEN EMPNO = 1001 THEN 'B'
  ELSE 'C'
  END
FROM EMP;
```

- EMPNO가 1000이면 A를, 1001이면 B를, 둘 다 아니면 C를 출력하는 구문이다.


<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [[SQL 기본]DECODE와 CASE문](https://velog.io/@yewon-july/DECODE-CASE)