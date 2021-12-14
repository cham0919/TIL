# SQL

- OPEN SQL 수행 결과가 성공하면 SY-SUBRC = 0을, 실패하면 그 외의 값 반환
- SY-DBCNT 는 데이터 LINㄷ 수 반환


<br/>

### SELECT


- SINGLE 옵션 추가 시, 한 라인만 선택.
- SINGLE 추가 했으나 여러 라인 선택되면 임의 라인 리턴
- INTO 구문 저장되는 곳이 필드 또는 Work Area일 때, 마지막에 ENDSELECT를 추가해야한다.
- DISTINCT는 중복제거 효과가 있다


```
DATA: gt_itab TYPE STANDARD
      TABLE OF sflight,
      gs_wa TYPE sflight.
```



- ENDSELECT은 조건에 해당하는 값을 모두 읽어올 때 까지 LOOP문을 수행한다.
- 즉, 계속 DB간 인터페이스가 수행되므로 비효율적


```
SELECT * INTO gs_wa
  FROM sflight
  WHERE carrid EQ 'AA'.
*    WRITE : / gs_wa-carrid,
*      gs_wa-connid.
  ENDSELECT.
```


- DB와 접근 한번에 모두 데이터를 가져오므로 효율적

```
  SELECT * INTO TABLE gt_itab
    FROM sflight
    WHERE carrid EQ 'AA'.

    LOOP AT gt_itab INTO gs_wa.
*        WRITE : / gs_wa-carrid,
*            gs_wa-connid.
    ENDLOOP.
```


-    AS - 컬럼명에 별명을 지정할 수 있다

```
    SELECT CARRID AS CR INTO TABLE GT_ITAB
      FROM sflight.
```


<br/>

###      동적 select 구문

```
     DATA: gs_line(72) TYPE c.

     gs_line = 'CARRID CONNID'.

     SELECT DISTINCT (gs_line) INTO TABLE gt_itab
       FROM sflight.
```


-      동적 변수는 최대 72자이나 이 이상이 필요할 때 다음과 같이 사용한다

```

       DATA gt_list LIKE TABLE OF gs_line(72).

       gs_line = 'CARRID CONNID'.
       APPEND gs_line TO gt_list.

       SELECT DISTINCT (gs_line) INTO TABLE gt_itab
       FROM sflight.
```

<br/><br/>


###     INTO 구문


-        (*) 사용은 데이터를 일정한 크기로 잘라 결과를 반환하기 때문에 비효율적
-         CORRESPONDING FIELDS OF 통해 한번에 값 할당 가능

<br/>

###  Internal Table

- 여러라인 조회할 때 사용
- APPENDING는 추가로 INSERT하고 INTO는 데이터를 삭제한 다음 INSERT한다

```
DATA: gs_wa2 TYPE sflight,
      gt_itab2 TYPE TABLE OF sflight.

SELECT carrid connid
  FROM spfli
  INTO CORRESPONDING FIELDS OF TABLE gt_itab2
  PACKAGE SIZE 5. " 몇 개의 라인을 가져와 인터널 테이블에 추가할 것인지 설정.

   LOOP AT gt_itab2 INTO gs_wa2.
      WRITE: /  gs_wa2-carrid, gs_wa2-connid.
   ENDLOOP.

   WRITE: /  '-----'.
   ENDSELECT.
```

<br/>

###   FROM 구문


#####   옵션

-   CLIENT SPECIFIED
    - 자동 Client 설정 해제

-   BYPASSING BUFFER
    - SAP Local Buffer에서 값을 읽지 않는다
    - 테이블이 Buffering이 설정되어 있어서 DB에서 SELECT문을 수행한다

-   UP TO n ROWS
    - SELECT의 ROW 개수를 제한단다
    - 사용자 실수로 대량 데이터 요청 시, 성능 저하를 예방할 수 있다

<br/>

###   동적 테이블명 사용

```
   PARAMETERS p_tname TYPE char10.
   DATA GS_WA3 TYPE SFLIGHT.

   SELECT SINGLE * INTO gs_wa3
     FROM (p_tname)
     WHERE carrid = 'AA'.
```

<br/>

###     JOIN 구문

-     명시적으로 언급안해도 기본적으로 INNSER JOIN으로 설정


- 항공기 정보를 가진 SFLIGHT와 항공사 이름은 있지만 SCARR 테이블에는 ID에 해당하는 이름이 있을 때,
- 항공사 이름 가져오는 예제

```
     TYPES: BEGIN OF t_str,
       carrid        TYPE sflight-carrid,
       carrname  TYPE scarr-carrname,
     END OF t_str.

     DATA: gs_str TYPE t_str.

     SELECT SINGLE a~carrid b~carrname
       INTO CORRESPONDING FIELDS OF gs_str " 한번에 gs_str에 데이터 삽입
       FROM sflight AS a
       INNER JOIN scarr AS b " 조인
       ON a~carrid EQ b~carrid
       WHERE a~carrid = 'AA'.

*       WRITE : / gs_str-carrid, gs_str-carrname.
```

<br/><br/>

### OUTER JOIN


- SCARR 테이블에 모든 항공사 정보의 이름이 없다는 가정 하에 항공사의 항공기 정보 누락 방지 위한
- SFLIGHT 테이블과 SCARR 테이블을 LEFT OUTER JOIN 수행 예제

```
TYPES: BEGIN OF t_str2,
  carrid TYPE sflight-carrid,
  carrname TYPE scarr-carrname,
  END OF t_str2.

  DATA: gs_str2 TYPE t_str.

  SELECT SINGLE a~carrid b~carrname
    INTO CORRESPONDING FIELDS OF gs_str2
    FROM sflight AS a
    LEFT OUTER JOIN scarr AS b
    ON a~carrid EQ b~carrid
    WHERE a~carrid = 'AA'.

*    WRITE : / gs_str2-carrid, gs_str2-carrname.
```


<br/>

### Line 수 제한

```
 SELECT ... FROM <tab> UP TO <n> ROWS ....
```


<br/>


### WHERE 구문


- 조건

```
EQ, =
NE, <>, ><
LT, <
LE, <=
GT, >
GE, >=
```

<br/>

- 문자열 비교
   -  ```WHERE COL2 LIKE 'ABC%'```


- 글자 수까지 비교
   - ```WHERE COL2 LIKE 'ABC_'```

<br/>


- 동적 조건

```
DATA gs_where(72)       TYPE c.
DATA gv_carrname        TYPE scarr-carrname.
DATA gv_carrid              TYPE scarr-carrid VALUE 'AC'.

CONCATENATE 'CARRID = ''' gv_carrid '''' INTO gs_where.

SELECT SINGLE carrname
  INTO gv_carrname
  FROM scarr
  WHERE (gs_where).
*  WRITE : / gv_carrname.
```


<br/>


###  FOR ALL ENTRIES 구문

-  인터널 테이블과 데이터베이스 테이블 JOIN과 유사.
-  LOOP 수행하며 SQL 수행

```
DATA gt_spfli       TYPE TABLE OF spfli.
DATA gt_sflight    TYPE TABLE OF sflight.
DATA gs_sflight    TYPE sflight.

SELECT * FROM spfli
  INTO TABLE gt_spfli.  "테이블 정보 가져오기

 SELECT * FROM sflight
   INTO TABLE gt_sflight
   FOR ALL ENTRIES IN gt_spfli
   WHERE carrid = gt_spfli-carrid
   AND connid = gt_spfli-connid.

*  LOOP AT gt_sflight INTO gs_sflight.
*    WRITE : / gs_sflight-carrid, gs_sflight-connid.
*   ENDLOOP.
```


<br/>

### GROUPING 구문


- Aggregate 함수

```
AVG - 평균
COUNT - 개수
MAX - 최대값
MIN - 최소값
STDDEV - 표준편차
SUM - 합계
VARIANCE - 분산
```

- 동적으로도 선언 가능하다

- 항공기 ID별 평균 예약 점유율 SELECT 예제

```
DATA: gv_carrid2        TYPE sflight-carrid,
      gv_connid          TYPE sflight-connid,
      gv_paymentsum TYPE i.

SELECT carrid connid AVG( paymentsum )
  INTO (gv_carrid2, gv_connid, gv_paymentsum)
  FROM sflight
  GROUP BY carrid connid.
*    WRITE : / gv_carrid2, gv_connid, gv_paymentsum.
 ENDSELECT.
```


<br/>

### HAVING 조건

```
SELECT carrid connid AVG( paymentsum )
  INTO (gv_carrid2, gv_connid, gv_paymentsum)
  FROM sflight
  GROUP BY carrid connid
  HAVING AVG( paymentsum) > 100000. " SELECT 조회 후 조건 설정
 ENDSELECT.
```

<br/>

### SORT 구문


-  ORDER BY PRIMARY KEY => SELECT * 구문에서만 사용 가능
- JOIN 및 VIEW에서는 사용 불가
- ASCENDING, DESCENDING


- 예약 좌석 기준 오름차순 정렬 예제

```
DATA: gv_carrid3       TYPE sflight-carrid,
      gv_connid3         TYPE sflight-connid,
      gv_paymentsum3  TYPE i.

SELECT carrid connid AVG( paymentsum ) as paymentsum3 " ORDER BY에서는 AVG(..) 사용할 수 없어 별명 설정
  INTO (gv_carrid3, gv_connid3, gv_paymentsum3)
  FROM sflight
  GROUP BY carrid connid
  ORDER BY paymentsum3.
*  WRITE: / gv_carrid3, gv_connid3, gv_paymentsum3.
 ENDSELECT.
```

<br/>

### Subquery

- 서브쿼리의 select 구문에는 컬럼 하나만 선언할 수 있다

```
 DATA: gv_carrid4       TYPE sflight-carrid,
       gv_connid4           TYPE sflight-connid,
       gv_paymentsum4  TYPE sflight-paymentsum.

 SELECT SINGLE carrid connid paymentsum
   INTO (gv_carrid4, gv_connid4, gv_paymentsum4)
   FROM sflight as ab
   WHERE carrid IN ( SELECT carrid
                              FROM spfli
                              WHERE carrid = ab~carrid
                              AND       connid = ab~connid )
   AND ab~carrid = 'AA'.

*   WRITE : / gv_carrid4, gv_connid4, gv_paymentsum4.
```

<br/>


### Non-scalar Subquery

- 서브쿼리 결과가 존재하면 TRUE, 아니면 FALSE 반환
- EXISTS 구문 사용


```
 SELECT SINGLE carrid connid paymentsum
   INTO (gv_carrid4, gv_connid4, gv_paymentsum4)
   FROM sflight AS a
   WHERE EXISTS ( SELECT *
              FROM spfli
              WHERE carrid = a~carrid
              AND connid = a~connid )
        AND a~carrid = 'AA'.

*   WRITE: / gv_carrid4, gv_connid4, gv_paymentsum4.
```

<br/>

### INSERT 구문


- 항공사 정보를 저장하고 있는 SCAR 테이블에 Air Korea 데이터 추가

```
DATA : gs_scarr TYPE scarr.

gs_scarr-carrid = 'KO'.
gs_scarr-carrname = 'Air Korea'.
gs_scarr-url = 'http://www.airkorea.co.kr/'.

INSERT INTO SCARR VALUES GS_SCARR.
* INSERT SCARR FROM GS_SCARR.
```


-  인터널 테이블 모든 값 한번에 삽입
- 같인 KEY 값 넣으면 덤프 에러 발생하기에 ACCEPTING DUPLICATE KEYS 구문을 사용한다.
-  실파할 시, SY-SUBRC = 4 를 반환한다

```
DATA: gt_spfli2        TYPE TABLE OF spfli,
      gs_spfli            TYPE spfli.

gs_spfli-carrid = 'KO'.
gs_spfli-connid = '0001'.
gs_spfli-cityfrom = 'Seoul'.
gs_spfli-cityto = ' Beijing  '.
APPEND gs_spfli TO gt_spfli2.


gs_spfli-carrid = 'KO'.
gs_spfli-connid = '0003'.
gs_spfli-cityfrom = 'Seoul'.
gs_spfli-cityto = ' Bangalore  '.
APPEND gs_spfli TO gt_spfli2.

INSERT spfli FROM TABLE gt_spfli2 ACCEPTING DUPLICATE KEYS.
```

<br/>

### UPDATE 구문


```
DATA gs_spfli3 TYPE spfli.

MOVE  'KO'          TO gs_spfli3-carrid.
MOVE '0001'         TO gs_spfli3-connid.
MOVE 'Busan'      TO gs_spfli3-cityfrom.

*UPDATE spfli FROM gs_spfli3.

*UPDATE spfli
*set CITYTO = '충청도'
*where carrid = 'KO'
*AND CONNID = '0001'.

IF SY-SUBRC eq 0.
    WRITE '성공'.
 ENDIF.
```

<br/>

### DELETE 구문

```
 DATA gs_spfli4 TYPE spfli.

 MOVE 'KO'        TO gs_spfli4-carrid.
 MOVE '0001'      TO gs_spfli4-connid.

* DELETE spfli FROM gs_spfli4.

*DELETE FROM spfli
*  WHERE carrid = 'KO'.

 IF SY-SUBRC eq 0.
   WRITE : / '삭제 성공'.
 ENDIF.
```


<br/>


### MODIFY 구문


```
 DATA: gt_spfli5        TYPE TABLE OF spfli,
      gs_spfli5            TYPE spfli.

 gs_spfli5-carrid = 'KO'.
 gs_spfli5-connid = '0001'.
 gs_spfli5-cityfrom = 'Korea'.
 MODIFY spfli FROM GS_SPFLI5.

 gs_spfli5-carrid = 'KO'.
 gs_spfli5-connid = '0001'.
 gs_spfli5-cityfrom = 'Busan'.
 APPEND GS_SPFLI5 TO GT_SPFLI5.

 gs_spfli5-carrid = 'KO'.
 gs_spfli5-connid = '0013'.
 gs_spfli5-cityfrom = 'Seoul'.
 APPEND GS_SPFLI5 TO GT_SPFLI5.

 MODIFY spfli FROM TABLE GT_SPFLI5.
```



<br/><br/><br/><br/><br/><br/><br/><br/><br/>

------------------------------------------------
Reference

- Easy ABAP 2.0 - 김성준 
 
 