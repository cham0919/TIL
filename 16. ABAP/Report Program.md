# Report Program


```
DATA : gt_sflight TYPE TABLE OF sflight,
           gs_sflight TYPE sflight.
```

-  SELECT-OPTIONS 또는 PARAMETERS 선언하고 리포트 프로그램 실행하면 자동으로 값 입력받을 수 있는 화면 생성
- 생성되는 화면을 SELECTION SCREEN이라 정의.
- SELECT-OPTIONS : s_carrid FOR gs_sflight-carrid.

```
INITIALIZATION.
  s_carrid-sign = 'I'.
  s_carrid-option = 'EQ'.
  s_carrid-low = 'AA'.
  APPEND s_carrid.

  START-OF-SELECTION.
      SELECT carrid connid
        FROM sflight
        INTO CORRESPONDING FIELDS OF TABLE gt_sflight
        WHERE carrid IN s_carrid.

    END-OF-SELECTION.
        LOOP AT gt_sflight INTO gs_sflight.
          WRITE : / gs_sflight-carrid, gs_sflight-connid.
        ENDLOOP.
```


<br/>

### 프로그램 구조(선언)

- Report 프로그램은 3가지 구조로 구분 가능
    -  데이터 선언부 조회 선택 화면
    -  실행 시점까지의 Event
    -  데이터 뿌려주는 List Event


<br/>


# 프로그램 선언문


- REPORT  YCHS_17 NO STANDARD PAGE HEADING - 기본 헤딩 제거

- REPORT  YCHS_17 LINE-SIZE 30. - 헤더 라인 사이즈 넓히기

- REPORT  YCHS_17 MESSAGE-ID <message-id> - 메세지 id 설정

- REPORT  YCHS_17.
- INCLUDE pgm_idTOP - 프로그램에서 사용할 테이블과 데이터 선언



<br/>

### SELECT SCREEN

- 프로그램 조회 조건을 입력할 수 있는 SELECTION SCREEN 생성 부분
- 리포트 프로그램에서 INCLUDE 프로그램명SEL(또는 TOP)에 포함하는 것이 좋다

```
TABLES : sflight.

SELECT-OPTIONS: sel_carr FOR sflight-CARRID.
PARAMETERS : p_carr LIKE sflight-carrid.
SELECTION-SCREEN BEGIN OF BLOCK bl1
  WITH FRAME TITLE TEXT-010.
  SELECTION-SCREEN END OF BLOCK bl1.
```

<br/>

### PARAMETERS

- 인풋 필드를 정의한다
- Data Type F는 SELECTION SCREEN에서 지원되지 않으므로 파라미터로 선언될 수 없다
- PARAMETERS <p> [(<length>)] <type> [<decimals>].

<br/>

##### 옵션들

```
DATA : l_fname(20)      TYPE c.
PARAMETERS:   p_1                                   DEFAULT 'A',                                            " 기본 값 세팅
                        p_2                                   TYPE char10,                                             " DATA TYPE 정의
                        p_3                                   TYPE c LENGTH 3 DEFAULT '123',               " TYPE c, n, x, p에만 정의되며 길이 정의
                        p_4                                   TYPE p DECIMALS 2 DEFAULT '123.456789', " 소수점 자리 지정
                        p_5                                   LIKE sflight-carrid,                                      " 오브젝트와 같은 데이터 타입 선언
                        p_6                                   MEMORY ID scl,                                         " 메모리 파라미터를 할당
                        p_7                                   MATCHCODE OBJECT zcarrid,                     " mobj에 Search Help 명을 입력하면 Possible Entry가 생성
                        p_8                                   MODIF ID mid,                                             " screen-group을 지정해 그룹별로 화면 속성을 제어하기 위함
                        p_9                                   NO-DISPLAY,                                             "화면에 보이지 않음
                        p_10                                 DEFAULT 'a' LOWER CASE,                         " 대소문자 구별
                        p_11                                 OBLIGATORY,                                             " 필수 필드로 지정. 화면 필드에는 물음표가 표시됨
                        p_12                                 AS CHECKBOX,                                          "  CHECK BOX로 표현
                        p_13                                 RADIOBUTTON GROUP radi,                         " 라디오 버튼으로 표현함. 두개 이상 필드를 그룹으로 선언해야함
                        p_13_2                              RADIOBUTTON GROUP radi,
                        p_14(10)                           VISIBLE LENGTH 3 DEFAULT '1234567890',     " 필드의 일부 길이까지만 화면에 보이게 설정함
                        p_15                                 LIKE sflight-carrid VALUE CHECK,                 " 테이블 필드 속성을 상속받아 Check Table 값을 체크할 수 있음(외부 키)
                        p_16                                 LIKE (l_fname),                                            " 파라미터를 동적으로 선언할 수 있음. 실행 시에 g는 아밥 딕셔너리에 존재하는 오브젝트가 할당되어야 함
                        p_17                                 LIKE sflight-carrid AS LISTBOX VISIBLE LENGTH 3, " 아밥 딕셔너리 필드의 인풋 헬프와 연결되면 LIST BOX로 보임
                        p_18                                 AS CHECKBOX USER-COMMAND abc.           " 체크 박스와 라디오 버튼에만 작용. 라디오 버튼을 클릭하면 USER COMMAND를 수행함
                        " 다음은 Logical Datagbase에서 사용됨,
*                  p_19                           for table sflight,
*                  p_20                           FOR NODE sflight,
*                  p_21                           AS SEARCH PATTERN,                          " LDB를 사용하며 SEARCH HELP의 킷값으로 인터널 테이블 구성
*                  p_22                           VALUE-REQUEST,                                " LDB에서 F4 VALUE HELP를 추가할 수 있도록 함
*                  p_22                           HELP-REQUEST.                                  " VALUE-REQUEST와 유사하며 필드 HELP를 생성함
```



<br/>

### SELECT-OPTIONS

- 2개의 인풋 필드를 통해 다양한 조건 값을 입력받을 수 있다
- FOR 구문과 항상 병행.


##### 옵션

```
TABLES : sflight.
DATA : gs_scarr TYPE scarr.
DATA : gv_val    TYPE char20.
SELECT-OPTIONS : s_1 FOR sflight-carrid           DEFAULT 'AC',                      " 기본 값 세팅
                              s_2 FOR gs_scarr-carrid       DEFAULT 'AA*'                      " option과 sign 지정.
                                                                         OPTION EQ SIGN I,                 " option - EQ, BT, NB, GE, LE, GT, LT, NE / sign - I(Include) / E(Exclude)
                              s_3 FOR gv_val                    DEFAULT '1111' TO '9999',      "  SELECT-OPTION의 LOW 값에서 HIGH 값을 지정. 구간 값을 지정함
                              s_4 FOR gv_val                    DEFAULT 'AAAA' TO 'ZZZZ'    " 앞의 두 구문을 조합한 것으로 OPTION은 BT와 NB만 가능함
                                                                         OPTION BT SIGN E,
                              s_5 FOR sflight-carrid           MEMORY ID scl,                      " MEMORY 파라미터 지정
                              s_6 FOR gv_val                    MATCHCODE OBJECT zxarrid,  " zxarrid에 Search Help 명을 입력하면 Possible Entry가 할당됨
                              s_7 FOR sflight-carrid           MODIF ID car,                           " 수정
                              s_8 FOR sflight-carrid           NO-DISPLAY,                            " 화면에 보이지 않음
                              s_9 FOR sflight-carrid           LOWER CASE,                          " 대소문자 구별
                              s_10 FOR sflight-carrid         OBLIGATORY,                            " 필수 필드로 지정. 화면 필드에는 ?가 표시
                              s_11 FOR sflight-carrid         NO-EXTENSION,                     " 버튼 제거
                              s_12 FOR sflight-carrid         NO INTERVALS,                        " HIGH 값 제거
                              s_13 FOR sflight-carrid         VISIBLE LENGTH 1.                   " 필드 일부 길이까지만 화면에 보이게 설정
```



<br/>

### SELECTION-SCREEN

- 시스템이 생성하는 화면을 사용자 입맛에 맞게 화면 구성 요소들 배치 가능

```
TABLES : scarr, sscrfields.

SELECTION-SCREEN BEGIN OF LINE.     " 파라미터를 여러 개 묶어 한 라인으로 생성. 라인에서 SELECT-OPTIONS, SELECTION-SCREEN SKIP n 구문 사용 x
SELECTION-SCREEN COMMENT 1(10) text-001 FOR FIELD p_1.    " 파라미터에 대한 내역을 지정. 1(10)은 /pos(len), pos(l3n), (len) 의미.
PARAMETERS : p_1 LIKE scarr-carrid.

" SELECTION-SCREEN BEGIC OF LINE 블록 안에서 파라미터 위치를 지정함
SELECTION-SCREEN POSITION POS_LOW.
PARAMETERS : p_2 LIKE scarr-carrname.

SELECTION-SCREEN END OF LINE.

SELECTION-SCREEN SKIP 2.                 " 빈 라인을 n개 삽입함
SELECTION-SCREEN ULINE.                  " Under Line을 추가함.
SELECTION-SCREEN ULINE /1(10).         " / 는 New 라인, 위치
SELECTION-SCREEN ULINE pos_low(10).  " pos_low는 파라미터 위치에서 시작
SELECTION-SCREEN ULINE pos_high(10).  " pos_high는 리포트 라인 길이 끝에서 시작

" 화면에 버튼 추가해 클릭하면 AT SELECTION-SCREEN에서 SSCRFIELDS-UCOMM에 저장됨
SELECTION-SCREEN PUSHBUTTON /pos_low(10) text-002 USER-COMMAND BTN01.

" 파라미터, 옵션 등 블록 형성
" WITH FRAME - 프레임 추가
" TITLE - 타이틀 추가
" NO INTERVALS - 블록 안 SELECT-OPTIONS LOW값만 보임
SELECTION-SCREEN BEGIN OF BLOCK block WITH FRAME TITLE text-003.
 PARAMETERS : p_3 TYPE c.
 SELECT-OPTIONS : s_1 FOR scarr-carrid.
 SELECTION-SCREEN END OF BLOCK block.

 SELECTION-SCREEN BEGIN OF BLOCK block2 WITH FRAME TITLE text-004
   NO INTERVALS.
 PARAMETERS : p_4 TYPE c.
 SELECT-OPTIONS : s_2 FOR scarr-carrid.
 SELECTION-SCREEN END OF BLOCK block2.
 " 펑션 키를 추가.
 " TABLES : SSCRFIELDS 구문이 선언되어야 함
 SELECTION-SCREEN FUNCTION KEY 1.

 INITIALIZATION.
    MOVE 'Function key 1' TO sscrfields-functxt_01.

 AT SELECTION-SCREEN.
    IF sscrfields-ucomm = 'FC01'.
        MESSAGE 'You Clicked Function Key 1' TYPE 'I'.
    ENDIF.

    IF sscrfields-ucomm = 'BTN01'.
         MESSAGE 'You Clicked Function Key Button' TYPE 'I'.
    ENDIF.
```


<br/>

### MESSAGE id

- 화면 하단에 메세지 보여주려면 리포트 선언 첫 문장에 기술해야 한다
-  REPORT <Reportname> MESSAGE-ID <message-id>
- MESSAGE Ennn WITH <field1> ... <field4>.

- E - 메세지 바. 사용자 입력 값에 대해 체크했을 때 뿌려주는 에러 메세지
- W - 메세지 바. 엔터누르면 다음 프로세스 넘어감. 화면 로직 멈추고 경고 메세지 형태로 출력
- I - 팝업 윈도우. 엔터 누르면 다음 프로세스 수행
- S - 성공 메세지
- A - 팝업 윈도우. STOP 이라는 버튼이 윈도우 창안에 있음. 누르면 세션 종료
- X - 덤프 화면과 함께 프로그램 종료


```
*MESSAGE E001 WITH 'ERROR'.
*MESSAGE W001 WITH 'WARNING'.
*MESSAGE I001 WITH 'INFORMATION'.
*MESSAGE S001 WITH 'SUCCESS'.
*MESSAGE A001 WITH 'ABEND'.
*MESSAGE X001 WITH 'DUMP'.
```


<br/>


# 프로그램 구조(이벤트)


- SELECTION SCREEN 화면이 열리기 전에 화면 필드 값을 초기화하는데 주로 사용된다.
- 필드 초기화, DEFAULT 값 세팅
- GUI STATUS 및 TITLEBAR 세팅
- SET TITLEBAR...
- SET PF-STATUS...
- INITIALIZATION.

- 사용자가 SELECTION SCREEN에 값을 입력하기 전/후에 작동한다
- 추가로 여러가지 기능이 있다
- ```AT SELECTION-SCREEN. " (OUTPUT, ON VALUE REQUEST.)```

-  사용자가 실행버튼(F8)을 클릭하면 데이터베이스(LDB)에서 값을 읽어온다
-   일반적으로 SELET 구문이 사용되는 블록이다
-  LDB를 사용한 REPORT에서는 GET <Table> 구문이 사용된다
- START-OF-SELECTION.

-  (SELECT * FROM ~ 또는 GET <TABLE> .....)

-  데이터를 읽은 후의 작업을 수행하는 블록이다
-  END-OF-SELECTION.

<br/>

### INITIALIZATION

- SELECTION-SCREEN 조회되기 전에 작동하므로 변수 초깃값 지정할 때 흔히 사용된다

```
TABLES : sflight.

PARAMETERS : p_carrid         LIKE sflight-carrid,
                       p_connid        LIKE sflight-connid.

SELECT-OPTIONS : s_fldate   FOR sflight-fldate.

INITIALIZATION. " 초기값 세팅할 필요 없을때는 생략해도 괜찮음
*SET TITLEBAR 'T1000'.
*SET PF-STATUS 'TEST'.
p_carrid = 'AA'.
p_connid = '17'.

s_fldate-low = '20150101'.
s_fldate-high = '20151231'.
APPEND s_fldate. " SELECT-OPTIONS은 인터널 테이블 형태기 때문에 반드시 APPEND 구문으로 데이터를 추가해야함
```

<br/>

### AT SELECTION-SCREEN

- 인풋 필드값이 변동되었을 때 실행되는 이벤트
- INITIALIZATION과 START-OF-SELECTION 사이에 수행되어 사용자 액션에 반응하고 화면 필드를 조정한다

- 예를 들어 SELECTION-SCREEN에 사업부 필드가 존재하고, 사용자는 다른 사업부의 데이터 조회를 막아야할 경우
- 사용자가 사업부 코드 변경하면 AT SELECTION-SCREEN이 자동 실행되기에 권한 체크 로직을 추가해야한다

```
AT SELECTION-SCREEN.
  AUTHORITY-CHECK OBJECT 'Z_TEST'
  ID 'CARRID' FIELD pa_carr
  ID ' ACTVT' FIELD '03'.

  IF SY-SUBRC = 4.
    MESSAGE E000 WITH '권한 없음'.
  ENDIF.
```

### 옵션들


- ON psel
   - SELECTION SCREEN에서 전달되는 특정 필드에 대해 수행
   - 오류 메세지 발생 시 해당 필드는 다시 입력상태가 되도록 설정

- 항공사 ID에 AA 이외의 값 입력되면 에러메세지 출력

```
TABLES : scarr.

SELECT-OPTIONS : s_carrid  FOR scarr-carrid.

AT SELECTION-SCREEN ON s_carrid.

    IF s_carrid-low NE 'AA'.
        MESSAGE 'It is required to Input AA' TYPE 'E'.
    ENDIF.
```

<br/>

- ON END OF sel
    - SELECTION SCREEN에서 멀티로 선택하면 전체 SELECTION TABLE의 입력 값을 제어할 수 있다
    -하한 / 상한값, 미 입력값 체크 등에 사용 가능

```
TABLES : scarr.
SELECT-OPTIONS : s_carrid FOR scarr-carrid.

AT SELECTION-SCREEN ON END OF s_carrid.
  LOOP AT s_carrid.
    IF s_carrid-low IS INITIAL.
      MESSAGE '낮은 값이 요구됩니다' TYPE 'E'..
    ENDIF.
  ENDLOOP.
```

<br/>

- OUTPUT
   - AT SELECTION-SCREEN OUTPUT 구문을 이용해 SELECTION-SCREEN 화면의 LAYOUT을 설정할 수 있다

```
TABLES : scarr.

SELECT-OPTIONS : s_carrid FOR scarr-carrid MODIF ID sc1.
PARAMETERS : p_test(10)   TYPE c MODIF ID sc2.

AT SELECTION-SCREEN OUTPUT.
  LOOP AT SCREEN.
    IF screen-group1 = 'SC1'.
      screen-intensified = '1'.
    ELSEIF screen-group1 = 'SC2'.
      screen-input = '0'.
    ENDIF.
     MODIFY SCREEN.
  ENDLOOP.
```

<br/>

- ON VALUE-REQUEST FOR psel_low-higj
   - ABAP Dictionary에서 제공해주는 Possible Entry 대신 사용자가 정의해준 Entry가 보이게 설정할 수 있다

<br/>

- ON HELP-REQUEST FOR psel_low_high
   - ABAP Dictionary에 해당 필드에 대한 도움말이 없거나 기존의 도움말을 대신해서 표현하고자 할 때 사용된다
   - SELECTION SCREEN의 필드를 선택하고 F1 눌렀을 때 수행된다

<br/>

- ON RADIOBUTTON GROUP radi
   - RADIOBUTTON GROUP <radi> 내에 선언된 PARAMETER들을 제어할 수 있다
   - RADIOBUTTON 그룹내의 필드는 <FIELD> 옵션으로 제어할 수 없다

<br/>

- ON BLOCK block
   - SELECTION SCREEN의 BLOCK 내 입력 값을 제어할 수 있다
   - SELECTION-SCREEN BEGIN OF BLOCK ~ SELECTION-SCREEN END OF BLOCK 내에 선언된 필드들이 특정 규칙에 맞지 않을 때 제어할 수 있따

<br/>

### START-OF-SELECTION

```
DATA : g_total    TYPE i,
           g_cnt      TYPE i,
           g_index   TYPE i.

DATA : gt_sflight TYPE TABLE OF sflight WITH HEADER LINE.

START-OF-SELECTION.
  SELECT *
    INTO CORRESPONDING FIELDS OF TABLE gt_sflight
    FROM sflight.

    DESCRIBE TABLE gt_sflight LINES g_total.

    LOOP AT gt_sflight.
      g_cnt = g_cnt + 1.
      PERFORM progress_indicator USING g_cnt g_total ' Progressing...  '.
    ENDLOOP.

    WRITE : / '성공'.

    FORM progress_indicator USING value(p_cur)
                                                    value(p_total)
                                                    value(p_text).

     DATA : lv_text(50) TYPE c,
               lv_idx(3) TYPE n.

      lv_idx = ( p_cur / p_total ) * 100.

      CONCATENATE lv_idx ' % : ' p_text INTO lv_text.

      CALL FUNCTION
         'SAPGUI_PROGRESS_INDICATOR'
         EXPORTING
           percentage = lv_idx
           text           = lv_text
         EXCEPTIONS
            OTHERS   = 0.
      ENDFORM.
```

<br/>

### END-OF-SELECTION

- 마지막 실행되는 이벤트

```
DATA : gs_str TYPE scarr,
           gt_itab TYPE TABLE OF scarr.

START-OF-SELECTION.
      SELECT *
        INTO CORRESPONDING FIELDS OF TABLE gt_itab
        FROM scarr.

  END-OF-SELECTION.
        LOOP AT gt_itab INTO gs_str.
          WRITE : / gs_str-carrid,  gs_str-carrname.
        ENDLOOP.
```

<br/>

# 프로그램 구조(List Process 이벤트)

- 조회조건 입력 후 실행버튼 혹은 F8 이벤트시 화면에 뿌려주는 List Process 이벤트

<br/>

### TOP-OF-PAGE

- 새로운 페이지가 시작될 때 수행되는 이벤트
- START OF SELECTION에서 첫번째 WRITE 만나면 수행, 새로운 페이지마다 수행
- NO STANDARD PAGE HEADING 옵션으로 생성된 프로그램에서 직접 헤더 입력 시 사용
- NEW-PAGE 구문에서는 이벤트 수행 X

<br/>

### TOP-OF-PAGE.

```
    WRITE: sy-title, 60 'Page no :', sy-pagno.
    ULINE.
    WRITE : / 'Enjoy ABAP', sy-datum.
    ULINE.

 START-OF-SELECTION.
    DO 50 TIMES.
      WRITE : / sy-index.
    ENDDO.
```


<br/>

### END-OF-PAGE

- 페이지 끝에서 수행되는 블록
- 현재 페이지의 남은 공간이 부족할 때 수행되는 이벤트(Footer)
-프로그램 내 NEW-PAGE 이벤트 추가했다면 END-OF-PAGE는 수행되지 않는다

<br/>

- 10 - 한 페이지의 총 라인 수 / (2) - 푸터에 뿌려줄 라인 수

```
REPORT  YCHS_17 LINE-COUNT 10(2).


TOP-OF-PAGE.
    WRITE: / 'Top of Page'.
    ULINE AT /(60).

 END-OF-PAGE.
    ULINE.
    WRITE : /30 'Page no :', sy-pagno.

  START-OF-SELECTION.
    DO 20 TIMES.
      WRITE : / sy-index.
    ENDDO.
```

<br/>

### AT LINE-SELECTION

 - LIST에서 라인을 더블클릭하거나 F2 눌렀을 때 수행
 - 발생하는 SY-UCOMM 시스템 변수에는 'PICK'이 할당된다

```
START-OF-SELECTION.
      WRITE: / 'First List.'.

      FORMAT HOTSPOT ON COLOR 7.
      WRITE : / 'Click this line'.
      FORMAT HOTSPOT OFF COLOR OFF.

  AT LINE-SELECTION.
       WRITE : / 'Secondary List'.
       WRITE : / 'sy-ucomn =' , sy-ucomm.


DATA : GS_SCARR         TYPE SCARR,
           GT_SCARR         TYPE TABLE OF SCARR,
           GV_FNAME(20)   TYPE C,
           GV_VALUE(20)    TYPE C,
           GV_CARRID         LIKE SCARR-CARRID,
           GV_CARRNAME   LIKE SCARR-CARRNAME.

AT LINE-SELECTION.
  GET CURSOR FIELD GV_FNAME VALUE GV_VALUE.

WRITE : / '->' , GV_FNAME, GV_VALUE.

  CASE GV_FNAME.
    WHEN 'GS_SCARR-CARRID'.
      SPLIT SY-LISEL AT ' ' INTO GV_CARRID GV_CARRNAME.
      WRITE : / GV_CARRID, GV_CARRNAME.

      WHEN OTHERS.
   ENDCASE.

   START-OF-SELECTION.
        SELECT *
          INTO CORRESPONDING FIELDS OF TABLE GT_SCARR
          FROM SCARR.

        LOOP AT GT_SCARR INTO GS_SCARR.
          WRITE : / GS_SCARR-CARRID,
                        GS_SCARR-CARRNAME.
        ENDLOOP.
```

<br/>

# LIST 시스템 필드


- SY-TITLE - 프로그램의 타이틀
- SY-LINCT - Report Statement에서 지정한 한 페이지 라인 수
- SY-LINSZ - Report Statement에서 지정한 라인 길이
- SY-SROWS - 현재 윈도우 라인 수
- SY-SCOLS - 현재 윈도우 컬럼 수
- SY-PAGNO - 현재 페이지 넘버
- SY-LILLI - 선택된 라인이 몇번째 라인인지
- SY-LINNO - 각 페이지 라인 넘버
- SY-COLNO - 현재 컬럼 번호
- SY-LISEL - 선택한 라인의 모든 값
- SY-CPAGE - 현재 페이지의 페이지 넘버
- SY-LSIND - 리스트의 순번. Secondary List

<br/>

### AT PF<NN>

- PF <nn>으로 선언된 Function을 수행

```
START-OF-SELECTION.
    WRITE : / 'Function Key Test PF5, PF6, PF7.'.


AT PF5.
  PERFORM wlist.


AT PF6.
  PERFORM wlist.

AT PF7.
  PERFORM wlist.

FORM wlist.
  WRITE :
  / 'You selected below Function Key',
  / 'SY-UCOM =', sy-ucomm.
ENDFORM.
```

<br/>

### AT USER-COMMAND

- 프로그램에서 Function으로 선언된 기능 수행

```
START-OF-SELECTION.
    SET PF-STATUS 'TEST'.
    WRITE : / 'Click the button'.

AT USER-COMMAND.
  CASE SY-UCOMM.
    WHEN 'FC1'.
      LEAVE PROGRAM.
  ENDCASE.
```

<br/>

### TOP-OF-PAGE DURING LINE-SELECTION

- Secondary List에서 Header를 컨트롤할 때 사용하는 이벤트

```
TOP-OF-PAGE.
    WRITE 'First List TOP-OF-PAGE.'.
    ULINE.

  TOP-OF-PAGE DURING LINE-SELECTION.
      WRITE 'Secondary List TOP-OF-PAGE.'.
      ULINE.

   AT LINE-SELECTION.
     PERFORM write_list.

    START-OF-SELECTION.
     WRITE : 'Double-click this line.'.

    FORM write_list.
      WRITE : 'Secondary list',
      / 'sy-pfkey:', sy-pfkey.
    ENDFORM.
```

<br/>

### HIDE AREA

- LIST에서 선택하면 HIDE WORK AREA로 데이터가 저장된다

```
DATA : lt_scarr TYPE STANDARD TABLE OF scarr WITH HEADER LINE.
DATA : ls_scarr TYPE scarr,
           lv_chk   TYPE c,
           lv_fldname(30),
           lv_fldval(50).

START-OF-SELECTION.
    SELECT * INTO TABLE lt_scarr FROM scarr.


END-OF-SELECTION.
      LOOP AT lt_scarr.
          WRITE : / lv_chk AS CHECKBOX,
          sy-vline, lt_scarr-carrid, lt_scarr-carrname.
          HIDE : lt_scarr-carrid, lt_scarr-carrname.
      ENDLOOP.

AT LINE-SELECTION.
  CHECK sy-lsind = 1.
  WINDOW STARTING AT 5 5 ENDING AT 55 10.
  WRITE : / 'You selected below data.'.
  WRITE : / lt_scarr-carrid, lt_scarr-carrname.
```


<br/><br/><br/><br/><br/><br/><br/><br/><br/>

------------------------------------------------
Reference

- Easy ABAP 2.0 - 김성준 
 
 
