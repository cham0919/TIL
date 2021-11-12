*Subroutine

*PERFORM write_data.
*
*FORM write_data.
*  WRITE 'Subroutine Test.'.
* ENDFORM.



*파라미터 전달

*Call by Value(값 복사)
*USING 키워드를 사용한다


*DATA : gv_val TYPE c LENGTH 20 VALUE 'Call by value test.'.
*
*
*PERFORM call_byvalue USING gv_val.
*
*PERFORM write_data.
*
*FORM write_data.
*  WRITE 'Subroutine Test.'.
* ENDFORM.
*
*FORM call_byvalue USING VALUE(p_val).
*       WRITE p_val.
*ENDFORM.



*Call by Reference

*CHANGING 키워드를 사용한다

*DATA: gv_val TYPE c LENGTH 30 VALUE 'CALL BY REFERENCE TEST.'.
*
*WRITE : /  gv_val.
*
*PERFORM call_byref CHANGING gv_val.
*
*WRITE : /  gv_val.
*
*FORM call_byref CHANGING p_val.
*
*  p_val = 'Value is Changed.'.
*
*ENDFORM.


 " VALUE 구문 사용하지 않으면 USING과 CHANGING 구문 모두 Call by Reference를 이용하게 된다
* USING, CHANGING 섞어 사용 가능하다


*Call by Value and Result

*서브루틴 내에서는 변경이 안되나 결과가 생성되면 변경된다

*DATA : gv_val1      TYPE i VALUE 2.
*DATA : gv_val2      TYPE i VALUE 3.
*DATA : gv_sum     TYPE i.
*
*PERFORM sum_data USING gv_val1 gv_val2
*                               CHANGING gv_sum.
*
*WRITE : / '결과 : ', gv_sum.
*
*FORM sum_data USING   value(p_val1)
*                                     value(p_val2)
*                    CHANGING value(p_sum).
*
*  p_sum = p_val1 + p_val2.
*
*  ENDFORM.



*파라미터 타입 정의

*명시적 타입 지정 안할시 Generic으로 정의. 호출하는 쪽의 타입 상속받게 됨

*FORM 구문에서 파라미터 타입 지정 예제

*DATA : gv_val               TYPE c.
*
*PERFORM call_byvref CHANGING gv_val. " 구문 에러. c 타입 -> i 타입 캐스팅 x
*
*FORM call_byvref CHANGING p_val TYPE i.
*  p_val = '값 변경 완료'.
*
*ENDFORM.



*파라미터와 구조체

*DATA: BEGIN OF gs_str,
*              col1 VALUE 'A',
*              col2 VALUE 'B',
*          END OF gs_str.
*
*
*PERFORM write_data USING gs_str.
*
*FORM write_data USING ps_str STRUCTURE gs_str.
*      WRITE: / ps_str.
*ENDFORM.




*파라미터와 인터널 테이블

*USING, CHANGING 구문

*인터널 테이블을 서브루틴 파라미터로 사용할 때도 가능

*TYPES: BEGIN OF t_str,
*        col1 TYPE C,
*        col2 TYPE I,
*  END OF t_str.
*
*TYPES : t_itab  TYPE TABLE OF t_str.
*
* DATA: gs_str   TYPE t_str,
*           gt_itab  TYPE t_itab.
*
*gs_str-col1 = 'A'.
*gs_str-col2 = 1.
*APPEND gs_str TO gt_itab.
*
*gs_str-col1 = 'B'.
*gs_str-col2 = 2.
*APPEND gs_str TO gt_itab.
*
*
*PERFORM test_itab USING gt_itab.
*
*FORM test_itab USING pt_itab TYPE t_itab.
*
*    READ TABLE pt_itab WITH KEY col1 = 'A' INTO gs_str.
*    IF sy-subrc EQ 0.
*      WRITE : / gs_str-col1, gs_str-col2.
*    ENDIF.
* ENDFORM.




*TABLES 구문

*3.0 이전에서 사용되는 것으로 USING, CHANGING 대신 사용가능하다
*Call by value 지원 x

*TABLES 구문 이용해 인터널 테이블을 서브루틴으로 전달하는 예제

*TYPES: BEGIN OF t_str,
*            col1    TYPE c,
*            col2    TYPE i,
*          END OF t_str.
*
*TYPES: t_itab TYPE TABLE OF t_str.
*
*DATA: gt_itab TYPE t_itab.
*
*PERFORM test_itab TABLES gt_itab.
*PERFORM write_data TABLES gt_itab.
*
*FORM test_itab TABLES pt_itab TYPE t_itab.
*    DATA ls_str TYPE t_str.
*
*    ls_str-col1 = 'A'.
*    ls_str-col2 = 1.
*    APPEND ls_str TO pt_itab.
*
*    ls_str-col1 = 'B'.
*    ls_str-col2 = 2.
*    APPEND ls_str TO pt_itab.
*
*ENDFORM.
*
*FORM write_data TABLES pt_itab LIKE gt_itab.
*    DATA ls_str TYPE t_str.
*
*    LOOP AT pt_itab INTO ls_str.
*        WRITE : / ls_str-col1, ls_str-col2.
*     ENDLOOP.
* ENDFORM.



*Subroutine 호출

*Internal, External 두가지 존재



*외부 서브루틴 호출

* IF FOUND 구문 사용

*아래 구현한 루틴 호출 예제
*
*DATA: gv_first(10)        TYPE c    VALUE 'External',
*          gv_second(10)  TYPE c    VALUE 'CALL',
*          gv_result(20)     TYPE c.
*
*PERFORM concate_string(YCHS_07) IF FOUND
*                                                          USING gv_first gv_second
*                                                    CHANGING GV_RESULT.


*내부 서브루틴 호출

*문자열 2개를 서브루틴에 전달해 하나의 변수에 연결하는 예제

*DATA: gv_val1(10) TYPE c VALUE 'Enjoy',
*          gv_val2(10) TYPE c VALUE 'ABAP',
*          gv_val3(10) TYPE c.
*
*PERFORM concate_string USING gv_val1 gv_val2
*                                CHANGING gv_val3.
*
*FORM concate_string USING value(p_val1) value(p_val2)
*                          CHANGING value(p_val3).
*
*  CONCATENATE P_VAL1 P_VAL2 INTO P_VAL3 SEPARATED BY space.
*
*  PERFORM write_data USING P_VAL3.
*
*ENDFORM.
*
*
*FORM write_data USING VALUE(p_val).
*    WRITE : / p_val.
* ENDFORM.
*
*



* 서브루틴 동적 호출

*DATA: gv_first(10)        TYPE c    VALUE 'Dynamic',
*          gv_second(10)  TYPE c    VALUE 'CALL',
*          gv_result(20)     TYPE c.
*
* DATA: gv_pname(8)    TYPE c    VALUE 'YCHS_07',
*           gv_subname(20) TYPE c VALUE 'CONCATE_STRING'. " 프로그램 이름과 서브루틴명은 반드시 대문자
*
* TRANSLATE GV_PNAME TO UPPER CASE.
*
* PERFORM (gv_subname) IN PROGRAM (gv_pname) IF FOUND
*                                                            USING gv_first gv_second
*                                                            CHANGING GV_RESULT.
*
*
* FORM concate_string USING value(p_val1) value(p_val2)
*                          CHANGING value(p_val3).
*
*  CONCATENATE P_VAL1 P_VAL2 INTO P_VAL3 SEPARATED BY space.
*
*  WRITE : / P_VAL3 .
*
*ENDFORM.





*LIST를 이용한 서브루틴 호출

*리스트를 이용해 서브루틴을 차례대로 호출 가능하다
*idx 순서에 따라 호출하며 오직 내부 서브루틴에서만 사용 가능하다
*파라미터 사용이 불가하다

*DO 2 TIMES.
*  PERFORM SY-INDEX OF subr1 subr2.
*ENDDO.
*
*FORM subr1.
*    WRITE: / '서브루틴1 실행'.
*ENDFORM.
*
*FORM subr2.
*    WRITE: / '서브루틴2 실행'.
*ENDFORM.




*Subroutine 종료
*END FORM - 자동 종료
*CHECK - 구문 확인 후 종료
*EXIT - 강제 종료

*PARAMETERS : P_VAL TYPE CHAR10.
*
*PERFORM END_SUBR USING P_VAL.
*
* FORM END_SUBR USING VALUE(P_VAL).
*
*   CASE P_VAL.
*      WHEN 'EXIT'.
*        WRITE '서브루틴 EXIT'.
*        EXIT.
*      WHEN 'CHECK'.
*         WRITE '값 CHECK'.
*         CHECK P_VAL NE 'CHECK'.
*       WHEN OTHERS.
*          WRITE '이상한 값'.
*
*    ENDCASE.
*
*     WRITE : / '서브루틴 끝.'.
*
*ENDFORM.




*Temporary Subroutine
*메인 메모리에서 동작하는 동적 서브루틴
*실행 중인 프로그램 메인 메모리에 Subroutine Pool 생성해 소스코드를 <itab> 인터널 테이블에 삽입하며
*8자리 Type C의 <prog> 서브루틴 프로그램을 생성한다.
*하나의 프로그램에 최대 36개까지 서브루틴 풀 생성 가능하다.
*에러 발생 시, SY-SUBRC = 8 이 반환된다

*DATA: gt_code(72) OCCURS 10,
*      gv_prog(8),
*      gv_msg(120).
*
*APPEND 'PROGRAM SUBPOOL.' TO gt_code.
*APPEND 'FORM dynamic_subr.' TO gt_code.
*APPEND 'WRITE: / ''Dynamic Subroutine is called''.' TO gt_code.
*APPEND 'ENDFORM.' TO gt_code.
*
*GENERATE SUBROUTINE POOL gt_code NAME gv_prog
*MESSAGE gv_msg.
*
*IF SY-SUBRC NE 0.
*  WRITE: / '서브루틴 풀 실패'.
*  ELSE.
*    WRITE: / '서브루틴 이름 : ', gv_prog.
*    SKIP 1.
*    PERFORM dynamic_subr IN PROGRAM (gv_prog).
*ENDIF.


*PERFORM ON COMMIT
*서브루틴 호출 시 ON COMMIT/ROLLBACK 옵션 사용이 가능하다

*- Using PERFORM ON COMMIT : COMMIT WORK 만나면 서브루틴 호출
*- Using PERFORM ON ROLLBACK : ROLLBACK WORK 만나면 서브루틴 호출

*DATA : gs_scarr     LIKE scarr,
*           gs_flg         TYPE c.
*
*SELECT SINGLE *
*    FROM scarr INTO gs_scarr
*    WHERE carrid = 'AA'.
*
*PERFORM delete_data USING gs_scarr.
*
*PERFORM insert_data ON COMMIT.
*
*IF gv_flg = 'X'. " 삭제가 성공적으로 변경됐으면
*  COMMIT WORK.
*ENDIF.
*
*FORM delete_data USING value(ps_scarr) TYPE scarr.
*
*  DELETE scarr FROM ps_scarr.
*  IF sy-subrc EQ 0.   " 삭제가 성공하면
*    gv_flg = 'X'.  " 값 변경
*  ENDIF.
*  ENDFORM.
*
*  FORM insert_data.
*    INSERT scarr FROM gs_scarr.
*  ENDFORM.




* MACRO


**LOCAL MACRO
**DEFINE ~ END0OF-DEFINITION 사용

*3개 파라미터 전달받아 CONCATENATE 명령 처리하는 con 매크로 예제
*'&' 는 매크로에서 변수값 받는 파라미터, 최대 9개까지 설정 가능.
*자기자신 호출 x
*매크로 con 내부에 또 다른 매크로 dis 호출해 결과값 화면 출력
*PERFORM은 로직에 포함된 재사용 모듈 / MACRO는 단순 코딩 반복 감소 위한 것

*DATA: gv_val1 TYPE c VALUE 'A',
*          gv_val2 TYPE c VALUE 'B',
*          gv_val3 TYPE char3.
*
*DEFINE con.
*  CONCATENATE &1 &2 INTO &3 SEPARATED BY space.
*  dis &3.
*END-OF-DEFINITION.
*
*DEFINE dis.
*  WRITE &1.
*END-OF-DEFINITION.
*
*con gv_val1 gv_val2 gv_val3.



*Global MACRO
* 전역적으로 사용 가능
*TRMAC 테이블 유지보수하면 사용 가능

