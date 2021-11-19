

*Internal Table
*프로그램 내 정의하여 사용할 수 있는 로컬 테이블
*동적인 구조체 배열이다
*INITIAL SIZE 구문은 실제 메모리 공간을 할당하는 것이 아닌 예약한다


*Local Table Type이용 인터널 테이블 생성
*
*TYPES: BEGIN OF s_type,
*            no(6)              TYPE c,
*            name(10)        TYPE c,
*            part(16)          TYPE c,
*  END OF s_type.
*
*DATA gt_itab TYPE STANDARD TABLE OF s_type WITH NON-UNIQUE KEY no WITH HEADER LINE.
*
*  gt_itab-no = '0001'.
*  gt_itab-name = 'Easy ABAP'.
*  gt_itab-part = 'SAP Team.'.
*  APPEND gt_itab.
*
*  LOOP AT gt_itab.
*        WRITE : gt_itab-no, gt_itab-name, gt_itab-part.
*   ENDLOOP.


*구조체 선언 후 참고하여 인터널 테이블 선언도 가능

*DATA: BEGIN OF gs_type,
*            no(6)              TYPE c,
*            name(10)        TYPE c,
*            part(16)          TYPE c,
*  END OF gs_type.
*
*DATA gt_itab LIKE STANDARD TABLE OF gs_type WITH NON-UNIQUE KEY no WITH HEADER LINE. " LIKE를 사용




*Global ABAP Dictionary Type 이용 인터널 테이블 생성

*DATA gt_itab TYPE SORTED TABLE OF scarr WITH UNIQUE KEY carrid.
*DATA gs_str LIKE LINE OF gt_itab.
*
*SELECT * INTO TABLE gt_itab
*  FROM scarr.
*
*  LOOP AT gt_itab INTO gs_str.
*    WRITE: / gs_str-carrid, gs_str-carrname.
*   ENDLOOP.




*헤더라인
**LOOP AT 시, 헤더라인이 없는 경우 INTO 필수, 있는 경우 생략 가능
**MODIFY 시, 헤더라인이 없는 경우 FROM 필수, 있는 경우 생략 가능

*
*TYPES: BEGIN OF t_str,
*  col1 TYPE i,
*  col2 TYPE i,
* END OF t_str.
*
* DATA gt_itab TYPE TABLE OF t_str WITH HEADER LINE.
*
* DO 3 TIMES.
*   gt_itab-col1 = sy-index.
*   gt_itab-col2 = sy-index ** 2.
*   APPEND gt_itab.  " 헤더라인이 있기 때문에 TO 생략
* ENDDO.
*
* LOOP AT gt_itab.  " 헤더라인이 있기 때문에 INTO 생략
*   WRITE: / gt_itab-col1, gt_itab-col2.
* ENDLOOP.



*Work Area 선언해 인터널 테이블 만들기

*TYPES: BEGIN OF t_str,
*  col1 TYPE i,
*  col2 TYPE i,
* END OF t_str.
*
* DATA gt_itab TYPE TABLE OF t_str.
* DATA gs_str LIKE LINE OF gt_itab.
*
* DO 3 TIMES.
*   gs_str-col1 = sy-index.
*   gs_str-col2 = sy-index * 2.
*   APPEND gs_str TO gt_itab.  " 헤더라인이 없기 때문에 TO
* ENDDO.
*
* LOOP AT gt_itab INTO gs_str.  " 헤더라인이 없기 때문에 INTO
*   WRITE: / gs_str-col1, gs_str-col2.
* ENDLOOP.




*Standard Table
* 순차적인 인덱스 갖는 테이블, Tree 구조
* 인덱스 이용해 엔트리 찾을 때 유용
* 키는 항상 Non-unique로 선언해야한다.

*TYPES: BEGIN OF t_line,
*      field1 TYPE c LENGTH 5,
*      field2 TYPE c LENGTH 4,
*      field3 TYPE i,
*  END OF t_line.
*
*  TYPES t_tab TYPE STANDARD TABLE OF t_line WITH NON-UNIQUE DEFAULT KEY. " DEFAULT KEY는 char타입 선언된 컬럼을 모두 키 컬럼으로 정의. 생략 가능
*
*  DATA gt_itab TYPE t_tab WITH HEADER LINE.
*
*  gt_itab-field1 = 'Enjoy'.
*  gt_itab-field2 = 'ABAP'.
*  gt_itab-field3 = 1.
*  APPEND gt_itab.
*
*  READ TABLE gt_itab INDEX 1.
*
*  WRITE : / gt_itab-field1,  gt_itab-field2, gt_itab-field3.





*Sorted Table
*키 값으로 항상 정렬된 테이블
*Unique Key 설정 가능

*TYPES: BEGIN OF t_line,
*        col    TYPE c,
*        seq   TYPE i,
*  END OF t_line.
*
*  TYPES t_tab TYPE SORTED TABLE OF t_line WITH UNIQUE KEY col.
*
*  DATA gt_itab TYPE t_tab WITH HEADER LINE.
*
*  gt_itab-col = 'B'.
*  gt_itab-seq = '1'.
*  INSERT TABLE gt_itab.
*
*gt_itab-col = 'A'.
*  gt_itab-seq = '2'.
*  INSERT TABLE gt_itab. " 정렬 테이블에서는 append 사용 불가
*
*  CLEAR gt_itab.
*  READ TABLE gt_itab INDEX 2.
*
*  WRITE : / gt_itab-col, gt_itab-seq.




*Hashed Table

*TYPES: BEGIN OF t_line,
*        col    TYPE c,
*        seq   TYPE i,
*  END OF t_line.
*
*  TYPES t_tab TYPE HASHED TABLE OF t_line WITH UNIQUE KEY col.
*
*  DATA gt_itab TYPE t_tab WITH HEADER LINE.
*
*  gt_itab-col = 'B'.
*  gt_itab-seq = '1'.
*  INSERT TABLE gt_itab.
*
*gt_itab-col = 'A'.
*  gt_itab-seq = '2'.
*  INSERT TABLE gt_itab. " 정렬 테이블에서는 append 사용 불가
*
*  CLEAR gt_itab.
*  READ TABLE gt_itab WITH TABLE KEY col  = 'A'.
*
*  WRITE : / gt_itab-col, gt_itab-seq.



*인터널 테이블 구식 선언법

*OCCURS
** 헤더라인을 가지는 인터널 테이블
** 클래스 기반 프로그램에서는 지원 X
** 인터널 테이블 메모리 할당을 의미하며, 0은 제한을 두지 않아 메모리 할당 최소화.
** Standard 테이블이 기본이며 다른 테이블 사용 불가.


*TYPES: BEGIN OF t_line,
*        col    TYPE c,
*        seq   TYPE i,
*  END OF t_line.
*
*  DATA gt_itab TYPE t_line OCCURS 0 WITH HEADER LINE.
*
*  gt_itab-col = 'A'.
*  gt_itab-seq = '1'.
*  INSERT TABLE gt_itab. " 정렬 테이블에서는 append 사용 불가
*
*  CLEAR gt_itab.
*  READ TABLE gt_itab INDEX 1.
*
*  WRITE : / gt_itab-col, gt_itab-seq.



---




*인터널 테이블 값 할당
*MOVE 통해 가능. 헤더 라인 값만 복사
* [] 사용하면 BODY도 가능
*타입이 같아야 한다. 다르면 칼럼 순서대로 값 할당한ㄷ.
*타입이 서로 다른 경우 속성에 Unicode check active 설정되어 있으면 문자 - 숫자 사이 과정에서 에러 발생할 수 있다

*TYPES: BEGIN OF t_line,
*      col1  TYPE i,
*      col2  TYPE i,
* END OF t_line.
*
* DATA: gt_itab1 TYPE STANDARD TABLE OF t_line WITH HEADER LINE,
*           gt_itab2 TYPE STANDARD TABLE OF t_line,
*           gs_wa   LIKE LINE OF gt_itab2.
*
*DO 5 TIMES.
*    gt_itab1-col1 = sy-index.
*    gt_itab1-col2 = sy-index * 2.
*    INSERT table gt_itab1.
*ENDDO.
*
*MOVE gt_itab1[] TO gt_itab2. " BODY까지 복사
*
*LOOP AT gt_itab2 INTO gs_wa.
*    WRITE: /  gs_wa-col1, gs_wa-col2.
* ENDLOOP.




*인터널 테이블 초기화

*CLEAR - 헤더라인이 있으면 헤더라인만 삭제, 없으면 BODY 전체 삭제. 헤더라인이 있는데 BODY 삭제할 때는 [] 사용
*REFRESH, FREE - BODY 삭제. 메모리 공간은 유지
*FREE - 메모리 공간까지 지움



*DATA: BEGIN OF gs_line,
*      col1  TYPE i,
*      col2  TYPE c,
* END OF gs_line.
*
* DATA: gt_itab LIKE STANDARD TABLE OF gs_line.
*
*gs_line-col1 = 1.
*gs_line-col2 = 'A'.
*INSERT gs_line INTO TABLE gt_itab.
*
*REFRESH gt_itab.
**FREE gt_itab.
**CLEAR gt_itab.
*
*IF gt_itab IS INITIAL.
*    WRITE : / '초기화'.
*    FREE gt_itab.
*ENDIF.



*인터널 테이블 정렬
*KEY가 있으면 KEY 기준 정렬, 선언 안되어 있으면 문자 타입 컬럼 구성하여 정렬
*Sorted Table에서 sort 사용하면 구문 에러

*Stable SORT
*정렬 계속 사용하면 Sort 시퀸스가 계속 변경된다
* 이를 해결하기 위한 정렬, 하지만 정렬 시간 더 걸림
*같은 데이터에도 위치 유지


*DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE i,
* END OF gs_line.
*
* DATA: gt_itab LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1.
*
*gs_line-col1 = 'B'.
*gs_line-col2 = 3.
*APPEND gs_line TO gt_itab.
*
*gs_line-col1 = 'C'.
*gs_line-col2 = 4.
*APPEND gs_line TO gt_itab.
*
*gs_line-col1 = 'A'.
*gs_line-col2 = 2.
*APPEND gs_line TO gt_itab.
*
*gs_line-col1 = 'A'.
*gs_line-col2 = 1.
*APPEND gs_line TO gt_itab.
*
*SORT gt_itab.
*PERFORM write_data.
*
*SORT gt_itab BY col1 col2.
*PERFORM write_data.
*
*SORT gt_itab BY col1 DESCENDING col2 ASCENDING.
*PERFORM write_data.
*
*FORM write_data.
*      LOOP AT gt_itab INTO gs_line.
*            WRITE: / gs_line-col1, gs_line-col2.
*       ENDLOOP.
*     ULINE.
* ENDFORM.




*인터널 테이블 속성 알아내기
*DESCRIBE 구문 사용

* DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE i,
* END OF gs_line.
*
* DATA: gt_itab LIKE STANDARD TABLE OF gs_line INITIAL SIZE 10.
*
* DATA: gv_line      TYPE i.
*
* DO 20 TIMES.
*   gs_line-col1 = sy-index.
*   gs_line-col2 = sy-index * 2.
*   INSERT gs_line INTO TABLE gt_itab.
*  ENDDO.
*
*  DESCRIBE TABLE gt_itab LINES gv_line.
*  WRITE : / gv_line.




*인터널 테이블 데이터 추가

*INSERT 구문
*Key 값을 이용해 인터널 테이블 라인을 추가한다
*성공하면 SY-SUBRC에 0이 저장된다
*UNIQUE KEY 값을 가진 상태에서 키가 중복되면 SY-SUBRC 4를 반환하고 덤프 에러는 발생하지 않는다
*여러 라인을 삽입할 때 같은 라인 타입이어야 한다.


*테이블 키 이용해 여러 라인 추가 예제

* DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE i,
* END OF gs_line.
*
* DATA: gt_itab1 LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1.
* DATA: gt_itab2 LIKE SORTED TABLE OF gs_line WITH NON-UNIQUE KEY col1.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 1.
* INSERT gs_line INTO TABLE gt_itab1.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 2.
* INSERT gs_line INTO TABLE gt_itab1.
*
* gs_line-col1 = 'C'.
* gs_line-col2 = 3.
* INSERT gs_line INTO TABLE gt_itab1.
*
* INSERT LINES OF gt_itab1 INTO TABLE gt_itab2.
** INSERT LINES OF gt_itab1 FROM 1 TO 2 INTO TABLE gt_itab2.
*
* LOOP AT gt_itab2 INTO gs_line.
*    WRITE : / gs_line-col1, gs_line-col2.
*  ENDLOOP.





* 인덱스 이용한 라인 추가
*해시 테이블은 사용 불가
*성공하면 SY-SUBRC 0 , SY-TABIX는 INDEX값을 반환
*여러 라인 추가할 때는 INSERT LINES OF itab1 INTO itab2 [index idx].

*Standard Table - 마지막 위치에 추가. append 구문과 같은 효과
*Sorted Table - 인터널 테이블 순서에 따라 추가. Non-Unique key 타입이면 중복된 라인은 같은 key 위에 추가된다
*
*
* DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE i,
* END OF gs_line.
*
* DATA: gt_itab1 LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1.
** DATA: gt_itab LIKE SORTED TABLE OF gs_line WITH UNIQUE KEY col1.
** DATA: gt_itab LIKE HASHED TABLE OF gs_line WITH UNIQUE KEY col1.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 1.
* INSERT gs_line INTO TABLE gt_itab.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 2.
* INSERT gs_line INTO TABLE gt_itab.
*
* gs_line-col1 = 'C'.
* gs_line-col2 = 3.
* INSERT gs_line INTO TABLE gt_itab.
*
*BREAK-POINT.



*APPEND 구문 이용한 라인 추가
* key와 index를 이용할 수 있는 insert와 달리 이는 index만 사용가능.
* 해시 테이블에서는 사용 불가.
* 여러라인 사용시 APPEND LINES OF itab1 TO itab2.
* 특정 라인 사용시 APPEND LINES OF itab1 [FROM n1] [TO n2] TO itab2.

*Standard Table - 마지막 위치에 추가. SORTED BY 옵션 이용하여 KEY 값 기준으로 DESCENDING 정렬하면서 추가 가능
*Sorted Table - 데이터가 정렬된 상태로 추가되도록 로직 구성해야한다. 안그러면 덤프 에러 발생

* DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE n,
* END OF gs_line.
*
* DATA: gt_itab1 LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1.
** DATA: gt_itab LIKE SORTED TABLE OF gs_line WITH UNIQUE KEY col1.
** DATA: gt_itab LIKE HASHED TABLE OF gs_line WITH UNIQUE KEY col1.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 1.
* APPEND gs_line TO gt_itab.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 2.
* APPEND gs_line TO gt_itab.
*
* gs_line-col1 = 'C'.
* gs_line-col2 = 3.
* APPEND gs_line TO gt_itab.


*APPEND INITIAL LINE
*인터널 테이블을 빈 공간에서 미리 생성 후 라인 추가 가능
*SORTED BY 구문 사용 시, 컬럼 기준으로 DESCENDING 정렬 수행하며 추가한다. - STANDARD 타입 테이블에만 효력 있고, INITIAL SIZE 지정해야한다


* DATA: BEGIN OF gs_line,
*      col1  TYPE c,
*      col2  TYPE i,
* END OF gs_line.
*
* DATA: gt_itab LIKE TABLE OF gs_line INITIAL SIZE 2.
*
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 1.
* APPEND gs_line TO gt_itab SORTED BY col1.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 2.
* APPEND gs_line TO gt_itab SORTED BY col1.
*
* gs_line-col1 = 'C'.
* gs_line-col2 = 3.
* APPEND gs_line TO gt_itab SORTED BY col1.
*
* LOOP AT gt_itab INTO gs_line.
*    WRITE : / gs_line-col1, gs_line-col2.
*  ENDLOOP. " descending하며 삽입되고 초기 사이즈가 2기 때문에 A,2 는 제거
*




*COLLECT 구문
*인터널 테이블의 숫자 타입 칼럼을 합산하는 기능
* Key 값을 제외한 칼럼들은 Numeric으로 선언되어야 한다.(f, i, p)
*같은 Key 값이 있을 때는 숫자 타입 칼럼을 합산, 없을 때는 APPEND문을 수행.
*KEY값이 없는 테이블은 CHAR 타입 칼럼 기준으로 수행


* DATA: BEGIN OF gs_line,
*      col1(3)  TYPE c,
*      col2(2)  TYPE n,
*      col3      TYPE i,
* END OF gs_line.
*
* DATA: gt_itab LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1 col2.
*
* gs_line-col1 = 'AA'.
* gs_line-col2 = 17.
* gs_line-col3 = 660.
* COLLECT gs_line INTO gt_itab.
*
* gs_line-col1 = 'AL'.
* gs_line-col2 = 34.
* gs_line-col3 = 220.
* COLLECT gs_line INTO gt_itab.
*
* gs_line-col1 = 'AA'.
* gs_line-col2 = 17.
* gs_line-col3 = 280.
* COLLECT gs_line INTO gt_itab.
*
*
* LOOP AT gt_itab INTO gs_line.
*    WRITE : / gs_line-col1, gs_line-col2, gs_line-col3.
*  ENDLOOP




*인터널 테이블 데이터 변경

*테이블 키를 이용해 한 라인 변경
*키 값 기준으로 라인을 변경한다
*Non-Unique Key고, 중복된 값이 존재할 때 MODIFY 구문을 수행할 때는 첫 번째 라인이 변경된다

* DATA: BEGIN OF gs_line,
*      col1(2)  TYPE c,
*      col2      TYPE i,
*      col3      TYPE sy-datum,
* END OF gs_line.
*
* DATA: gt_itab LIKE STANDARD TABLE OF gs_line WITH NON-UNIQUE KEY col1 col2.
*
* gs_line-col1 = 'AA'.
* gs_line-col2 = 50.
* INSERT gs_line INTO TABLE gt_itab.
*
* gs_line-col1 = 'AA'.
* gs_line-col2 = 26.
* INSERT gs_line INTO TABLE gt_itab.
*
* gs_line-col1 = 'AA'.
* gs_line-col2 = 50.
* gs_line-col3 = '20201029'.
*
*MODIFY TABLE gt_itab FROM gs_line.
*
* LOOP AT gt_itab INTO gs_line.
*    WRITE : / gs_line-col1, gs_line-col2, gs_line-col3.
*  ENDLOOP



*WHERE 조건 이용한 여러 라인 변경

*DATA: BEGIN OF gs_line,
*      carrid          TYPE sflight-carrid,
*      carrname    TYPE scarr-carrname,
*      fldate          TYPE sflight-fldate,
*END OF gs_line.
*
*DATA gt_itab LIKE TABLE OF gs_line.
*
*SELECT carrid connid
*  INTO CORRESPONDING FIELDS OF TABLE gt_itab
*  FROM sflight.
*
* LOOP AT gt_itab INTO gs_line.
*    AT NEW carrid.
*      SELECT SINGLE carrname INTO gs_line-carrname
*        FROM scarr
*        WHERE carrid = gs_line-carrid.
*
*     MODIFY gt_itab FROM gs_line TRANSPORTING carrname
*      WHERE carrid = gs_line-carrid.
*     ENDAT.
*
*    WRITE : / gs_line-carrid, gs_line-carrname.
*
*  ENDLOOP.
.


*인덱스틑 이용해 한 라인 변경
*해시 테이블에서는 사용 불가
*루프 구문 내에서는 인덱스 옵션 생략 가능하며 이 때 현재 인터널 테이블의 라인 인덱스값을 변경함

*DATA: BEGIN OF gs_line,
*      carrid          TYPE sflight-carrid,
*      carrname    TYPE scarr-carrname,
*      fldate          TYPE sflight-fldate,
*END OF gs_line.
*
*DATA gt_itab LIKE TABLE OF gs_line.
*
*SELECT carrid fldate
*  INTO CORRESPONDING FIELDS OF TABLE gt_itab
*  FROM sflight.
*
*  LOOP AT gt_itab INTO gs_line.
*      SELECT SINGLE carrname
*        INTO gs_line-carrname
*        FROM scarr
*        WHERE carrid = gs_line-carrid.
*
*        WRITE : / gs_line-carrid, gs_line-carrname.
*
*    ENDLOOP.







*인덱스 테이블 데이터 삭제

*테이블 키를 이용한 한 라인 삭제
*Non-Unique Key로 설정된 스탠다드 타입 경우 WITH TABLE KEY 구문은 중복된 Key 데이터 중 한 건만 삭제

*DELETE TABLE itab WITH TABLE KEY k1 = f1 ... kn = fn.


*WHERE 조건을 이용해 여러 라인 삭제

*DATA: BEGIN OF gs_line,
*      carrid    TYPE sflight-carrid,
*      connid  TYPE sflight-connid,
*END OF gs_line.
*
*DATA gt_itab  LIKE TABLE OF gs_line WITH NON-UNIQUE KEY carrid.
*
*SELECT carrid connid fldate
*  INTO CORRESPONDING FIELDS OF TABLE gt_itab
*  FROM sflight.
*
*DELETE gt_itab
*WHERE carrid = 'AA'
*AND connid = '0017'.
*
*  LOOP AT gt_itab INTO gs_line.
*
*    WRITE : / gs_line-carrid, gs_line-connid.
*
*  ENDLOOP.





*인덱스를 이용한 삭제

*DATA: BEGIN OF gs_line,
*      col1    TYPE i,
*      col2  TYPE i,
*END OF gs_line.
*
*DATA gt_itab  LIKE TABLE OF gs_line.
*
*DO 3 TIMES.
*  gs_line-col1 = sy-index.
*  gs_line-col2 = sy-index * 2.
*  APPEND gs_line TO gt_itab.
*ENDDO.
*
*DELETE gt_itab INDEX 2.
*
*  LOOP AT gt_itab INTO gs_line.
*
*    WRITE : / gs_line-col1, gs_line-col2.
*
*  ENDLOOP.



*ADJACENT DUPLICATE 구문 이용한 중복 라인 삭제
*구문 수행 전에 SORT 구문으로 정렬해야 원하는 결과 취득 가능

*DATA: BEGIN OF gs_line,
*      carrid    TYPE sflight-carrid,
*      connid  TYPE sflight-connid,
*END OF gs_line.
*
*DATA gt_itab  LIKE TABLE OF gs_line WITH HEADER LINE.
*
*SELECT carrid connid
*   INTO CORRESPONDING FIELDS OF TABLE gt_itab
*  FROM sflight.
*
*DELETE ADJACENT DUPLICATES FROM gt_itab.
*
*  LOOP AT gt_itab.
*
*    WRITE : / gt_itab-carrid, gt_itab-connid.
*
*  ENDLOOP.




*인터널 테이블 읽기

*테이블 키 이용
**RESULT는 Read 결과를 저장하게 되는 Work Area다.
**헤더 라인이 존재하면 Into 이하는 생략하고 인터널 테이블 이름 자체를 Work Area로 사용해도 된다
**성공하면 sy-subrc 0을, 실패하면 4를 반환한다
**sy-tabix 변수는 라인의 인덱스를 반환한다

*키 값 이용해 READ하는 예제

*DATA: BEGIN OF gs_line,
*     carrid         TYPE scarr-carrid,
*     carrname   TYPE scarr-carrname,
* END OF gs_line.
*
* DATA gt_itab LIKE TABLE OF gs_line WITH NON-UNIQUE KEY carrid.
*
* SELECT carrid carrname
*   INTO CORRESPONDING FIELDS OF TABLE gt_itab
*   FROM scarr.
*
*   gs_line-carrid = 'AA'.
*   READ TABLE gt_itab
*   FROM gs_line
*   INTO gs_line.
*
*   WRITE : / gs_line-carrid, gs_line-carrname.
*
*   CLEAR : gs_line.
*
*   READ TABLE gt_itab
*   WITH TABLE KEY carrid = 'AB'
*   INTO gs_line.
*
*   WRITE : / gs_line-carrid, gs_line-carrname.



*Work Area 할당
** READ 구문 결과를 Work Area에 할당하는 구문


**COMPARING 옵션
***READ 구문 결과값에 비교 조건 추가
***구문 필드가 Work Area 값과 인터널 테이블에 존재하는 값이 같으면 sy-subrc = 0, 다르면 2를 반환

*DATA: BEGIN OF gs_line,
*     col1      TYPE c,
*     col2      TYPE i,
* END OF gs_line.
*
* DATA gt_itab LIKE SORTED TABLE OF gs_line WITH UNIQUE KEY col1.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 1.
* INSERT gs_line INTO TABLE  gt_itab.
*
* CLEAR gs_line.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 2.
* INSERT gs_line INTO TABLE  gt_itab.
*
* CLEAR gs_line.
*
* gs_line-col1 = 'A'.
*
* READ TABLE gt_itab
* FROM gs_line INTO gs_line
* COMPARING col2.
*
* WRITE : / 'SY-SUBRC :', SY-SUBRC.
* WRITE : / 'RESULT :', gs_line-col1, gs_line-col2.



*READ 구문의 TRANSPORTING
**READ한 결과를 해당 컬럼만 타겟에 저장하는 기능

*DATA: BEGIN OF gs_line,
*     col1      TYPE c,
*     col2(7)      TYPE C,
* END OF gs_line.
*
* DATA gt_itab LIKE SORTED TABLE OF gs_line WITH UNIQUE KEY col1.
* DATA gs_wa LIKE LINE OF gt_itab.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 'First'.
* INSERT gs_line INTO TABLE gt_itab.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 'Second'.
* INSERT gs_line INTO TABLE gt_itab.
*
* CLEAR gs_line.
*
* gs_line-col1 = 'A'.
*
* READ TABLE gt_itab WITH TABLE KEY col1 = 'A' INTO gs_wa
* TRANSPORTING col2.   " 결과값을 col2에만 삽입
*
* WRITE : / gs_wa-col1, gs_wa-col2.



*인덱스를 이용해 READ 구문 수행
*성공시 SY-SURBC 0, 실패 시 4 반환
*SY-TABIX에는 인터널 테이블의 인덱스 순번이 저장


*DATA: BEGIN OF gs_line,
*     col1      TYPE c,
*     col2(7)      TYPE c,
* END OF gs_line.
*
* DATA gt_itab LIKE SORTED TABLE OF gs_line WITH UNIQUE KEY col1.
*
* gs_line-col1 = 'A'.
* gs_line-col2 = 'First'.
* INSERT gs_line INTO TABLE gt_itab.
*
*CLEAR gs_line.
*
* gs_line-col1 = 'B'.
* gs_line-col2 = 'Second'.
* INSERT gs_line INTO TABLE gt_itab.
*
* CLEAR gs_line.
*
* READ TABLE gt_itab INTO gs_line INDEX 1.
*
* WRITE : / gs_line-col1, gs_line-col2.




*BINARY 서치
**대상 컬럼을 기준으로 정렬 후 사용하며 속도가 빠르다.

*DATA: BEGIN OF gs_line,
*     carrid       TYPE sflight-carrid,
*     carrname  TYPE scarr-carrname,
* END OF gs_line.
*
* DATA gt_itab LIKE TABLE OF gs_line .
* DATA gt_scarr LIKE TABLE OF scarr WITH HEADER LINE.
*
* SELECT carrid connid
*   INTO CORRESPONDING FIELDS OF TABLE gt_itab
*   FROM sflight.
*
*SELECT carrid carrname
*   INTO CORRESPONDING FIELDS OF TABLE gt_scarr
*   FROM scarr.
*
*LOOP AT gt_itab INTO gs_line.
*
*    READ TABLE gt_scarr WITH KEY carrid = gs_line-carrid BINARY SEARCH. " 데이터 비교 및 가져오는게 db가 아닌 어플리케이션단이기 때문에 속도가 훨씬 빠름

*
*    gs_line-carrname = gt_scarr-carrname.
*
*    MODIFY gt_itab FROM gs_line.
*
*    WRITE : / gs_line-carrid, gs_line-carrname.
*
* ENDLOOP.