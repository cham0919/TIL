
Write





*WRITE 구문
**OUTPUT 리스트에 데이터를 쓰는 기능을 주로 한다
**값을 할당하는 기능도 수행
**리스트용으로 사용될 때 데이터를 LIST BUFFER에 저장한다
**OUTPUT 리스트에 사용될 수 있는 항목
***DATA 구문으로 선언된 필드
***TABLES 구문으로 선언된 구조체의 항목
***FIELD-SYMBOL로 선언된 필드 심볼
***언어에 종속적이지 않은 TEXT문장


*AT pl
**필드의 위치와 길이를 지정하여 준다
**슬래시(/) 기호는 new Line을 의미하며 숫자 앞에 선언되어야 한다

*DATA : gt_data TYPE TABLE OF SFLIGHT WITH HEADER LINE.
*
*TOP-OF-PAGE.
*  WRITE : /50 'REPORT FORMAT' CENTERED.
*  WRITE : /48 '-----------------' CENTERED.
*  SKIP.
*
* START-OF-SELECTION.
*  WRITE :/(51) SY-ULINE, 53(68) SY-ULINE,
*             /       SY-VLINE NO-GAP, 51 SY-VLINE NO-GAP, 53 SY-VLINE NO-GAP,
*             120 SY-VLINE NO-GAP. " 윗 라인
*
*
*  WRITE: 2(9) 'Report No' NO-GAP CENTERED, SY-VLINE NO-GAP.   " SY-VLINE NO-GAP 세로 줄
*  WRITE: 12(5) '00001' NO-GAP, 51 SY-VLINE NO-GAP.
*
*  WRITE: 54(11) 'Report Date' NO-GAP CENTERED, SY-VLINE NO-GAP.
*  WRITE: 66(10) SY-DATUM NO-GAP CENTERED, 120 SY-VLINE NO-GAP.
*  WRITE:/(51) SY-ULINE, 53(68) SY-ULINE. " 밑 라인
*  SKIP.
*
*  WRITE:/(120) SY-ULINE.
*
*  SELECT * INTO CORRESPONDING FIELDS OF TABLE GT_DATA
*    FROM SFLIGHT UP TO 10 ROWS.
*
*    LOOP AT GT_DATA.
*
*      WRITE :/ SY-VLINE NO-GAP, 2(10) 'Carrier ID', SY-VLINE NO-GAP.
*      WRITE: 14(4) GT_DATA-CARRID, SY-VLINE NO-GAP.
*      WRITE: 20(18) 'Connection Number', SY-VLINE NO-GAP.
*      WRITE: 40(4) GT_DATA-CONNID, SY-VLINE NO-GAP.
*      WRITE: 46(12) 'FLIGHT date', SY-VLINE NO-GAP.
*      WRITE: 60(12) GT_DATA-FLDATE, SY-VLINE NO-GAP.
*      WRITE: 74(10) 'Air Fare', SY-VLINE NO-GAP.
*      WRITE: 86(11) GT_DATA-PRICE, SY-VLINE NO-GAP.
*      WRITE: 100(15) 'Local currency', SY-VLINE NO-GAP.
*      WRITE: 116(3) GT_DATA-CURRENCY, SY-VLINE NO-GAP.
*      WRITE:/(120) SY-ULINE.
*
*
*    ENDLOOP.




*WRITE OPTION

*DATA : l_1 TYPE p DECIMALS 3 VALUE '0.000'.
*DATA : l_2 TYPE p DECIMALS 3 VALUE '-1.234'.
*DATA : l_3 TYPE p VALUE '20151231'.
*DATA : l_4 TYPE p VALUE '1000'. "CURR 15, DECIMAL 3.
*DATA : l_5 TYPE p DECIMALS 3 VALUE '1.234'.
*DATA : l_6 TYPE p DECIMALS 3 VALUE '1.678'.
*DATA : l_7 TYPE p DECIMALS 3 VALUE '1000'.
*DATA : l_8 TYPE f  VALUE '123456789E2'.
*DATA : l_9 TYPE d VALUE '20151231'.
*DATA : l_10 TYPE d VALUE '20151231'.
*DATA : l_11(8) TYPE c VALUE 'YYYYMMDD'.
*DATA : l_12(10) TYPE c VALUE 'align'.
*
*WRITE : 'L_1', L_1, L_1 NO-ZERO.            " 0을 출력하지 않음. TYPE C인 경우 SPACE
*WRITE : / 'L_2', L_2, L_2 NO-SIGN.           "부호를 조회하지 않음
*WRITE : / 'L_3', L_3, L_3 DD/MM/YY, L_3 DDMMYY. " TYPE D, 날짜 포맷을 변경
*WRITE : / 'L_4', L_4 CURRENCY 'USA', L_4 CURRENCY 'KRM'. " w에 정의된 통화 형식으로 금액 필드 값을 나타낸다.
*WRITE : / 'L_5', L_5, L_5 DECIMALS 2.       " 소수점 자리를 조절한다(type i, p, f)
*WRITE : / 'L_6', L_6, L_6 ROUND 2, L_6 ROUND -2.  " r 수만큼 10진수는 10의 r승 이동함
*WRITE : / 'L_7', L_7 UNIT 'STD', L_7 UNIT 'KM'.  " u에 정의된 단위에 따라 출력값 포맷 결정. 출력 변수 f는 수량으로 취급, u에 정의된 단위로 출력 시 소수점 자리를 결정.
*WRITE : / 'L_8', L_8, L_8 EXPONENT 2.  " e만큼 지수를 설정해 보여준다(TYPE f)
*WRITE : / 'L_9', L_9 USING EDIT MASK '__:__:__', L_9 USING NO EDIT MASK. "사용자가 정의한 포맷으로 화면에 보여준다
*WRITE : /10 'L_9',   20 'L_9'.
*WRITE : / L_10 UNDER 'L_10', L_9 UNDER 'L_9'. " g 헤더라인을 선언하고 아래 각 필드의 값을 보일 경우 사용
*WRITE : / L_10 NO-GAP, L_11.  " 필드 사이의 공백을 없애준다
*WRITE : / L_12 LEFT-JUSTIFIED,  " 왼쪽으로 정렬
*           / L_12 CENTERED,  " 중앙 정렬
*           / L_12 RIGHT-JUSTIFIED. " 오른쪽 정렬




*WRITE OUTPUT 포맷

*DATA : l_color TYPE char25 VALUE 'Color Test'.
*DATA : l_intens TYPE char25 VALUE 'Intensified test'.
*DATA : l_inverse TYPE char25 VALUE 'Inverse test'.
*DATA : l_hotspot TYPE char25 VALUE 'Hotspot test'.
*DATA : l_input TYPE char25 VALUE 'Input Test'.
*DATA : l_frame TYPE char25 VALUE 'Frame Test'.
*
*FORMAT COLOR COL_HEADING ON.
*    WRITE : / l_color.
*FORMAT COLOR OFF.
*
*FORMAT INTENSIFIED ON. " 색상 강조
*WRITE : / L_INTENS.
*FORMAT INTENSIFIED OFF.
*
*FORMAT INVERSE ON. " 색상 반전
*WRITE : / L_INVERSE.
*FORMAT INVERSE OFF.
*
*FORMAT HOTSPOT ON. " 핫스팟 기능 활성화
*WRITE : / L_HOTSPOT.
*FORMAT HOTSPOT OFF.
*
*FORMAT INPUT ON. " 입력필드
*WRITE : / L_INPUT.
*FORMAT INPUT OFF.
*
*FORMAT FRAMES ON.
*WRITE : / L_FRAME.
*FORMAT FRAMES OFF.
*
*
**다음 구문과 동일
**FORMAT COLOR OFF  INTENSIFIED OFF INVERSE OFF HOTSPOT OFF INPUT OFF
*FORMAT RESET.



*WRITE AS CHECKBOX

*DATA : gv_box(1) TYPE c,
*          gv_lines TYPE i,
*          gv_num(1) TYPE c.
*
*DO 5 TIMES.
*      gv_num = sy-index.
*      WRITE : / gv_box AS CHECKBOX, 'Line NO: ', gv_num.
*      HIDE: gv_box, gv_num.
* ENDDO.
*
* gv_lines = sy-linno.
*
* TOP-OF-PAGE.
*    WRITE 'After selecting a checkbox, double click.'.
*    ULINE.
*
*  AT LINE-SELECTION.
*    DO gv_lines TIMES.
*      READ LINE sy-index FIELD VALUE gv_box.
*      IF gv_box = 'X'.
*        MODIFY LINE sy-index
*          FIELD VALUE gv_box
*          FIELD FORMAT gv_box INPUT OFF
*                              gv_num COLOR 7 INVERSE ON.
*      ENDIF.
*    ENDDO.


*WRITE AS SYMBOL
**LIST의 필드를 심볼로 보여준다

*INCLUDE <SYMBOL>.
*
*WRITE : SYM_RIGHT_HAND AS SYMBOL,
*            'Enjoy ABAP',
*            SYM_LEFT_TRIANGLE AS SYMBOL.



*WRITE AS ICON

*INCLUDE <icon>.
*
*WRITE : / icon_graphics AS ICON,
*             icon_alarm AS ICON.


*WRITE AS LINE

*INCLUDE <line>.
*
*TOP-OF-PAGE.
*    WRITE : '라인 테스트'.
*    ULINE.
*
* START-OF-SELECTION.
*    ULINE /1(50).
*    WRITE : / sy-vline NO-GAP.
*    WRITE : / line_top_left_corner AS LINE.


*WRITE AS QUICKINFO
**마우스 커서 놓으면 정보 보임

*INCLUDE <list>.
*DATA : gv_info(20) VALUE 'This is Quick Info'.
*
*TOP-OF-PAGE.
*  WRITE : 'Quickinfo Test'.
*  ULINE.
*
*
*  START-OF-SELECTION.
*      WRITE : / icon_information AS icon
*                QUICKINFO gv_info.
*      WRITE : / 'Take a mouse on the Quickinfo Icon.'.


*추가 구문
**NEW-PAGE : 새로운 페이지 생성
**NEW-LINE : 줄바꿈
*SKIP <n> : Blank Line을 <n>번 출력.
*SKIP TO LINE <n> : n번째 Line으로 Cursor의 위치를 옮긴다
*RESERVE <n> LINES : 현재 페이지에 최소 n만큼 여유가 없다면 자동으로 Page Feed가 생성된다
*BACK: RESERVE 다음에 사용했을 때 커서 위치를 RESERVE 문장 전의 위치로 이동. 그냥 사용하면 페이지 처음으로 이동
*POSITION <n>: Column 상의 커서 위치를 지정
*SET BLANK LINES ON(OFF) : Blank Line을 출력(삭제). 기본은 on이다.

