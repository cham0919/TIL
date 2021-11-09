

*NATIVE SQL

*EXEC ~ ENDEXEC 사이 구문 점검 수행 X. Dump Error 발생 가능성 있음
*다른 db에 적용x
*Client를 구분하는 MANDT 필드를 SQL 구문 내 필수 포함


*기본 구문
* EXEC SQL ~ ENDEXEC
*변수명 앞에 콜론(:) 표기
*클라이언트 구분자인 MANDT 필드 반드시 기술

DATA : gv_carrid        LIKE sflight-carrid VALUE 'AA',
          gv_connid       LIKE sflight-connid.

*   EXEC SQL.
*    SELECT A.CONNID
*      INTO :GV_CONNID
*      FROM SFLIGHT A
*      WHERE A.MANDT = :SY-MANDT
*      AND A.CARRID = :GV_CARRID
*    ENDEXEC.

*    WRITE gv_connid.



*Native SQL과 인터널 테이블
*    PERFORMING 이용해 한번에 여러 데이터를 인터널 테이블에 삽입 가능
*    SELECT구문이 한 라인 읽을 때마다 Subroutine 실행

    TYPES: BEGIN OF t_str,
                carrid LIKE sflight-carrid,
                connid LIKE sflight-connid,
    END OF t_str.

    DATA  gt_itab   TYPE TABLE OF t_str.
    DATA  gs_wa    TYPE t_str.


    EXEC SQL PERFORMING APPEND_ITAB.
      SELECT A.CARRID, A.CONNID
        INTO :GS_WA-CARRID, :GS_WA-CONNID
        FROM SFLIGHT A
       WHERE A.MANDT = :SY-MANDT
       AND A.CARRID = :GV_CARRID
     ENDEXEC.


     FORM append_itab.
*      WRITE : / gs_wa-carrid, gs_wa-connid.
      APPEND gs_wa TO gt_itab.
      CLEAR gt_itab.
      ENDFORM.      "APPEND_ITAB




*    Native SQL과 JOIN

*      오라클 NATIVE SQL에서의  JOIN 구문


TYPES: BEGIN OF t_join_str,
        carrid      LIKE sflight-carrid,
        connid    LIKE sflight-connid,
        carrname  LIKE scarr-carrname,
  END OF t_join_str.

  DATA gs_join_wa   TYPE t_join_str.
  DATA : gv_join_carrid LIKE sflight-carrid VALUE 'AA'.

" Outer Join 사용 시 A.MANDT (+) = B.MANDT
      " AND A.CARRID (+) = B.CARRID 사용
*     EXEC SQL PERFORMING WRITE_DATA.
*      SELECT A.CARRID, B.CARRNAME
*        INTO :GS_JOIN_WA-CARRID, :GS_JOIN_WA-CARRNAME
*        FROM SFLIGHT A, SCARR B
*       WHERE A.MANDT = B.MANDT
*       AND A.CARRID = B.CARRID
*       AND A.MANDT = :SY-MANDT
*       AND A.CARRID = :GV_JOIN_CARRID
*     ENDEXEC.
*
*     FORM write_data.
*       WRITE : / gs_join_wa-carrid, gs_join_wa-carrname.
*      ENDFORM.




*NATIVE SQL과 SUBSTRING

*3번째 자리에 있는 문자 'CD' 추출 예제
*DATA: LV_CHAR TYPE C(10).
*LV_CHAR = ABCDE.

*항공기 이름의 앞 8자리 가져오는 로직 예제

* EXEC SQL PERFORMING  WRITE_DATA .
*    SELECT A.CARRID, SUBSTR( B.CARRNAME, 1, 8 )
*      INTO :GS_WA-CARRID, :GS_WA-CARRNAME
*      FROM SCARR B, SFLIGHT A
*      WHERE A.MANDT = B.MANDT
*          AND A.CARRID = B.CARRID
*          AND A.MANDT = :SY-MANDT
*          AND A.CARRID = :GV_CARRID
*   ENDEXEC.
*
*   FORM write_data.
*       WRITE : / gw_wa-carrid, gs_wa-carrname.
*      ENDFORM.

