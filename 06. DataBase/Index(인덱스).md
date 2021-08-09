# Index(인덱스)

## 목적

- RDBMS에서 검색 속도를 높이기 위한 기술

- 테이블의 칼럼을 색인화한다.

- 데이터베이스 안의 레코드를 처음부터 풀스캔하지 않고, B+ Tree로 구성된 구조에서 Index 파일 검색으로 속도를 향상시키는 기술이다.

- 인덱스의 구조는 Root Block, Branch Block, Leaf Block으로 구성되고, Root Block은 인덱스의 트리에서 가장 상위에 있는 노드를 의미하며, Branch Block은 다음 단계의 주소를 가지고 있는 포인터로 되어 있다

- 오름차순(Ascending), 내림차순(Descending) 탐색이 가능하다.

<br/>

* 색인화 : 특정 내용이 들어 있는 정보를 쉽게 찾아볼 수 있도록 표지 따위를 넣거나 일정한 순서에 따라 배열함

<br/>

## 인덱스 스캔(Index Scan)

- 인덱스 유일 스캔(Index Unique Scan)
  - 인덱스의 키 값이 중복되지 않는 경우, 해당 인덱스를 사용할 때 발생한다.

- 인덱스 범위 스캔(Index Range Scan)
  - SELECT문에서 특정 범위를 조회하는 WHERE문을 사용할 경우 발생한다.
  - Like, Between이 대표적인 예이다. 단, 데이터 양이 적은 경우에는 인덱스 자체를 실행시키지 않고 TABLE FULL SCAN이 될 수 있다.
  - Index Range Scan은 인덱스의 Leaf Block의 특정 범위를 스캔한 것이다.

- 인덱스 전체 스캔(Index Full Scan)
  - 인덱스에서 검색되는 키가 많은 경우에, Leaf Block의 처음부터 끝까지 전체를 읽어 들인다.
  - Table Full Scan 시에 High Watermark란?
    - Table Full Scan은 테이블의 데이터를 모두 읽은 것을 의미한다.
    - 테이블을 읽을 때, High Watermark 이하까지만 Table을 Full Scan한다.
    - High Watermark는 테이블에 데이터가 저장된 블록에서 최상위 위치를 의미하고, 데이터가 삭제되면 High Watermark가 변경된다.

<br/>


## 파일 구성

테이블 생성 시, 3가지 파일이 생성된다.

- FRM : 테이블 구조 저장 파일
- MYD : 실제 데이터 파일
- MYI : Index 정보 파일 (Index 사용 시 생성)

사용자가 쿼리를 통해 Index를 사용하는 칼럼을 검색하게 되면, 이때 MYI 파일의 내용을 활용한다.

<br/>

## 단점

- Index 생성시, .mdb 파일 크기가 증가한다.
- 한 페이지를 동시에 수정할 수 있는 병행성이 줄어든다.
- 인덱스 된 Field에서 Data를 업데이트하거나, Record를 추가 또는 삭제시 성능이 떨어진다.
- 데이터 변경 작업이 자주 일어나는 경우, Index를 재작성해야 하므로 성능에 영향을 미친다.

<br/>

## 상황 분석

**사용하면 좋은 경우**

(1) Where 절에서 자주 사용되는 Column

(2) 외래키가 사용되는 Column

(3) Join에 자주 사용되는 Column

<br/>

**Index 사용을 피해야 하는 경우**

(1) Data 중복도가 높은 Column

(2) DML이 자주 일어나는 Column

<br/>

## DML이 일어났을 때의 상황

- INSERT
  - 기존 Block에 여유가 없을 때, 새로운 Data가 입력된다.
  - → 새로운 Block을 할당 받은 후, Key를 옮기는 작업을 수행한다.
  - → Index split 작업 동안, 해당 Block의 Key 값에 대해서 DML이 블로킹 된다. (대기 이벤트 발생)
  
<br/>
  
- DELETE

<Table과 Index 상황 비교>

- Table에서 data가 delete 되는 경우 : Data가 지워지고, 다른 Data가 그 공간을 사용 가능하다.

- Index에서 Data가 delete 되는 경우 : Data가 지워지지 않고, 사용 안 됨 표시만 해둔다.

→ Table의 Data 수와 Index의 Data 수가 다를 수 있음


<br/>

- UPDATE
  - Table에서 update가 발생하면 → Index는 Update 할 수 없다.
  - Index에서는 Delete가 발생한 후, 새로운 작업의 Insert 작업 / 2배의 작업이 소요되어 힘들다.
  
  
  

<br/><br/><br/><br/>


참조

---

- [DB Index 란?](https://lalwr.blogspot.com/2016/02/db-index.html)
- [Index(인덱스)](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Database/%5BDB%5D%20Index.md)
- [[SQL 최적화의 원리]인덱스(Index)](https://velog.io/@yewon-july/Index)



 