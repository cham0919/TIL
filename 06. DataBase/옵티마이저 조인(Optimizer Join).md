# Nested Loop Join

- Nested Loop Join은 하나의 테이블에서 데이터를 먼저 찾고, 그 다음 테이블을 조인하는 형식으로 실행된다.

- Nested Loop Join에서 먼저 조회되는 테이블을 **Outer Table**, 그 다음에 조회되는 테이블을 **Inner Table**이라 칭한다.
- 그래서 Nested Loop Join에서는 외부 테이블(선행 테이블, Outer Table)의 크기가 작은 것을 먼저 찾는 것이 중요하다. 데이터 스캔의 범위를 줄일 수 있기 때문이다.
- Nested Loop Join에서는 RANDOM ACCESS가 발생하는데, RANDOM ACCESS가 많이 발생하면 성능 지연이 발생한다. 그러므로, RANDOM ACCESS의 양을 줄여야 성능이 향상된다.
- Nested Loop Join은 조인 칼럼의 인덱스가 존재해야한다.
- SELECT 뒤에 ``` ordered use_nl(TABLE NAME) ```의 힌트를 주어 사용한다.
  - ordered 힌트는 FROM 절에 나오는 테이블 순서대로 조인을 하게 하는 것이다. ordered는 혼자 사용되지 않고, use_nl, use_merge, use_hash 힌트와 함께 사용된다.
- 프로그래밍에서 사용되는 중첩된 반복문과 유사한 방식으로 조인을 수행하는 방식이다.


<br/>

# Sort Merge Join

- Sort Merge Join은 두 개의 테이블을 SORT_AREA라는 메모리 공간에 모두 로딩(Loading)하고 정렬(Sort)을 수행한다.

- 두 개의 테이블에서 Sort가 완료되면, 두 개의 테이블을 Merge한다.
- 데이터 양이 많아지면 성능이 저하된다.(Sort를 수행하기 때문).
- 정렬 데이터양이 너무 많으면, 정렬은 임시 영역에서 수행된다.
- 임시 영역은 디스크에 있기 때문에 성능이 급격히 떨어진다.
- SELECT 뒤에 ``` ordered use_merge(TABLE NAME) ``` 힌트를 사용하여 Sort Merge 조인을 할 수 있따.

<br/>

# Hash Join

- Hash Join은 두 개의 테이블에서 작은 테이블을 Hash 메모리에 로딩(Loading)하고 두 개의 테이블의 조인 키를 사용해서 테이블을 생성한다.

- Hash 함수를 사용해서 주소를 계산하고, 해당 주소를 사용해서 테이블을 조인하기 때문에, CPU 연산을 많이 한다.
- 특히, Hash Join 시에는 선행 테이블이 충분히 메모리에 로딩되는 크기여야만 한다.



<br/><br/><br/><br/><br/><br/><br/>


---
Reference

- [[SQL 최적화의 원리]옵티마이저 조인(Optimizer Join)](https://velog.io/@yewon-july/Optimizer-Join)