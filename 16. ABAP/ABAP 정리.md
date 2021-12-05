## ABAP ( Advanced Business Application Programming )

<br/>

### HANA 장점

 - CDS뷰 사용가능.
 - eclipse 연동 쉬움.
 - ECC는 row기반이지만 HANA는 row, column store
 - 필드별로 따로 따로 저장해서 연결해준다. 불필요한 공간을 없애줘서 효율, 속도빠름.
 - 데이터베이스 단에서 웬만한건 다 처리하겠다는 방향.
 
<br/>
 
### ABAP 프로그램 종류

1. Executable Program (Type 1) = Report Program = List Program   
      - 조회 목적으로 사용. 조회조건을 입력하는 Selection Screen이 존재.
      - 프로그램 실행 시 Selection Screen 존재 시 ‘1000’ 스크린 자동호출.
      - ‘REPORT + 프로그램명’ 구문을 시작으로 프로그램 작성.
      - 프로그램 Flow는 EVENT BLOCK Processing이라 하여 각 Event Block 순서대로 진행.
      - Screen 프로세서가 이벤트 블록 단위로 실행되는 방식.
      - 사용자가 Selection Screen으로 조회 및 변경을 하면 ABAP 런타임 환경에서 Selection Screen 
      - 프로세서가 프로그램의 상단에 선언된 프로그램 타입을 파악한다. 그리고 이 정보를 통해 어떤 순서로 프로세싱 과정을 진행할지 결정하고 수행한다.

2.  Module Pool Program (Type M) = Screen Program = Online Program
   
      - 생성/수정/삭제/조회 의 목적으로 사용. Transaction 처리
      - T-Code 생성 후 실행가능.
      - ‘PROGRAM + 프로그램명’ 구문을 시작으로 프로그램을 작성.
      - 프로그램 Flow는 T-Code에 입력된 첫 스크린을 시작으로 다수의 스크린들이 순차적으로 호출되며 진행
      - 스크린 단위로 수행되는 프로그램. 
      - NAMING은 SAPM으로 시작 SAPMZ### SAPMY###

3.  Function Group (Type F)
   
      - NAMING은 SAPL으로 시작 SAPLZ~

4. Class Library : Interface Pool (Type J), Class Pool (Type K)

5. Include Program (Type I)
      
      - module pool에서 자주 사용하는 프로그램

<br/>

   1, 2 => 독립적으로 실행.
   
   3 ,4 ,5 => report나 screen에서 reusable unit으로 사용.
   
   1, 2, 3 => Screen을 가짐.
   
   ABAP Program Type은 프로그램 안의 프로세싱 블록과 오브젝트, 그리고 ABAP 런타임 환경에서
   어떻게 실행될지를 결정.

<br/>

- Screen 1000

  - screen 1000 = Selection Screen (PARAMETERS, SELECT-OPTIONS)
  - screen 100, 200 보통 normal default 단위이고 sub screen 사이사이 10단위로 들어가기도 함.

<br/><br/>

- Key Field 설정 이유 : 중복된 데이터를 확인하기 위해

  - Database 사용 이유 : 필요한 Data를 중복하지 않고(주목적) 잘 관리하기 위해 

- ABAP Dictionary와 Database는 별개로 생각해야한다.

-  tcode 생성
  -  se80 se93

- Processing Blocks in ABAP Programs

- Event Blocks For Selection Screens

- Event Blocks For Excutable Programs (Reports)
   
- CL_DEMO_OUTPUT 클래스를 이용한 팝업 에디트 띄우기


<br/><br/>

- sy-datum+?(?)
   - ex. sy-datum = 20210611
   - sy-datum+0(4) = 2021
   - sy-datum+4(2) = 06
   - sy-datum+6(2) = 11

<br/>

-  System fields ( WF1 p.263 )
   - 확인하는 방법 : se11 (abap dictionary)에서 syst 조회

<br/>

- SY-DATUM
  - 일자 및 시간, 시스템날짜 출력
- SY-UZEIT
  - 시스템 시간 출력
- SY-DBCNT
  - Processed Database Table Rows
  - SELECT구문으로 데이터베이스에서 읽어온 라인 수
  - (테이블에서 데이터를 SELECT하고 난 후 SELECT된 건 수)
- SY-TABIX
  - Index of Internal Tables
  - 인터널 테이블의 현재 라인번호
  - (인터널 테이블의 LOOP문 안에서 인터널테이블의 인덱스)
- SY-INDEX
  - Loop Index
- SY-SUBRC
  - Return Value of ABAP Statements
  - ABAP문장 수행 후 Return code, 정상 : 0 return
- SY-LANGU
  - Language Key of Current Text Environment, 언어 코드
- SY-TFILL
  - 내부테이블의 총 레코드 수, 현재라인수, 
- SY-PANGO
  - 리스트의 현재 페이지 번호
- SY-CPAGE
  - 현재화면상의 페이지 번호
- SY-LINCT
  - 한 페이지마다 라인의 수 리포트문의 LINE-COUNT에서 지정한 라인 수 (한 페이지의 총 라인 수)
- SY-LINNO
  - 현재 커서가 위치한 라인번호
- SY-SROWS
  - 현재 페이지의 라인 수
- SY-SCOLS
  - 현재 페이지의 칼럼 수
- SY-DYNNR
  - Current Screen Number, 현재 화면번호
- SY-CPLOG
  - 호출한 프로그램명
- SY-SYSID
  - SAP R/3 시스템 이름
- SY-UNAME
  - SAP 시스템, 사용자 로그온 이름
- SY-DATLO
  - Local Date for Current User
- SY-TIMLO
  - Local Time of Current User
- SY-ZONLO
  - Time Zone of Current User
- SY-TCODE
  - Current Transaction Code




<br/><br/>



###  SAP Systems

-  SAP NetWeaver : 통합관리 프레임워크 
   - SAP NetWeaver의 주요기능 8가지   
     1. User Productivity
     2. Business Intelligence
     3. Business Process Composition
     4. Enterprise Information Management (EIM)
     5. Sevice-Oriented Architecture (SOA) Middleware
     6. Custom Development
     7. Security and Identity Management
     8. Application Lifecycle Management (ALM)

<br/>

-  ERP 시스템을 ECC ( SAP ERP Central Component )라고도 한다.  
   - ECC쓰려면 ABAP 서버 깔아야되고, Enterprise Portal 쓰려면 java 서버를 깔아야된다.

<br/>

- SAP 3tier
   - Presentation Server / 	Application Server / Database Server 

<br/>

-  User Reequest
   - 유저가 어떠한 서비스를 요청하느냐에 따라서 abap dispatcher가 work process를 할당한다.
   - 서버, 디스패처(유저의 Request를 queue에다 저장하고 그 요청사항을 가져와서 워크프로세스 할당)
   - 워크프로세서( 5개 Dialog D Background B Lock E Update V Spool S )
   - tcode : SE50 ( 워크프로세스가 어떻게 할당되어져 있는지 확인 가능 )
   - user와 work process는 1대1연결이 아니다. 요청된 것들만 work process에서 처리된다.

<br/>

-  ABAP dispatcher의 역할
   1. 자원관리  
   2. 워크프로세스에 request 분배  
   3. presentation과 database layer 연결
   4. 통신활동 조직 
   5. 유저의 리퀘스트를 큐에 저장하고 큐에 저장되어있는 것을 처리

<br/>

-  DB interface
   - 데이터 읽어오는 구문을 처리하는 역할
   - application level의 open sql을 받아서 native sql로 변환시켜줌.

<br/>

-  Gateway Process
   - 여러 개의 서버로 구성되어 있을 때 application 서버들을 연결할 때 gateway 사용
   - system
      - system 연결할 때 사용

<br/>

- RFC ( Remote Function Call-Based )
  -  다양한 인터페이스 기술 중에 RFC를 주로 사용
  -  시스템 사이에서 연관된 프로그래밍들을 간략화하는 커뮤니케이션 SAP 통신규약.
  -  다른 장비에서 function module을 부르는 프로세스. 같은 장비에서 다른 프로그램을 부를 때도 사용.
  -  tcode : SM59

<br/>

-  BAPI ( Business Application Programming )
   - 여러 가지 비즈니스가 존재하는데 BOR ( Business Object Repository ) 밑에
   - BO ( Business Object ) 의 메소드에 BAPI가 있다.
   - 이 메소드가 RFC로 되어있다 (function module로 되어있다.)
   - tcode : BAPI

<br/>

- ALE ( Application Link Enabling )
   - 분산환경 시스템에서 애플리케이션을 통합할 수 있는게 ALE. 
   - 같은 회사 뿐만아니라 다른 회사 시스템과도 인터페이스가 가능.
   - 분산된 애플리케이션을 운영지원하는 것.

<br/>

-  Web Service Standards
   1. Extensible Markup Language (XML)
   2. Simple Object Access Protocol (SOAP)
   3. Web Services Description Language (WSDL)
   4. Universal Description, Discovery, and Integration (UDDI)

<br/>

- ABAP Repository
   - ABAP Program에서 생성된 모든 object가 저장되있는 곳
   - 생성된 프로그램 모두 Repository에 저장. 여기 저장된 모든 것들을 Object라고 한다.
   - 프로그램이나 function module이나 전부다.
   - repository infosystem , tcode : SE84

<br/>


- Object Navigator
   - SE80 : Obeject Navigator
   - SE51 : Screen Painter
   - SE41 : Menu Painter
   - SE38 : ABAP Editor
   - SE11 : ABAP Dictionary
   - SE37 : Function Builder
   - SE24 : Class Builder

<br/>

-  ABAP Programming Language Features
   - it is typed.
   - it enables multi-language application
   - it enables SQL access
   - It is enhanced as an object-oriented language
   - It is platform-independent
   - It is upward-compatible 

<br/><br/>

###  변수선언 3가지 방법

   1. ABAP Standard data type을 이용해서 변수 선언
   2. Local data type을 선언해서 변수 선언
   3. ABAP dictionary에 생성되어 있는 data element를 이용해서도 변수 선언가능

<br/>

###  data type 사용 3가지
   1. Screen Input Output filed 정의할 때 사용 (parameters 키워드) 
   2. data object 정의할 때 (variable, function
   3. interface parameter 정의할 때 (subroutine, method)

<br/>

### data type 종류 2가지 ( WF1 p.245 )
   1. Complete : 길이 정해져있는 타입, D, T, I, F, String, XString, DECFLOAT16, DECFLOAT34
      Integer 기본길이 4, F는 8, 
   2. Incomplete : 길이 정해져있지 않은 타입, C, N, X, P

<br/>

### data type Local / Global
   1. Local Data Type : 기술적 속성만 가지고 있음.
      TYPES tv_c_type TYPE c LENGTH 8.
   2. Global Data Type : 기술적 속성 + 무엇을 의미하는지 포함
      ex. s_carr_id : 항공사 코드, s_conn_id : 비행기 편명

<br/>

### data object를 data type으로 선언하는 법
   1. gv_percentage TYPE p LENGTH 3 DEMICALS 2 (incomplete, 999.99)
   2. gv_number TYPE I VALUE 17
   3. gv_number2 LIKE gv_number(변수이름)
   4. gv_carrid TYPE s_carr_id (data element)
   5. CONSTANTS gc_myconst TYPE <type_name> VALUE literal or IS INITIAL.
   6. ok_code like(type) sy-ucomm. -> 시스템 필드로할 때는 둘다 가능

<br/>

### Literal 

   - Numeric Literals 
   - Positive Integer : 123, Negative Integer : -123
   - Text Literals
   - String : ‘Hello’, Decimal Number : ‘123.45’, Floating Point Number : ‘13.45E01’
      * 소수점 들어갈 때는 ‘ ’ single quote 사용

<br/>

### 문자열 길이 확인 키워드

   - gv_length = strlen( gv_string ).

<br/>

### Value Assignment

   - MOVE gc_qf TO gv_carrid1.   =    gv_carrid1 = gc_qf.	
   - ADD 1 TO gv_count.   =   gv_count = gv_count + 1.


<br/>

### 연산자 
   - 산술연산자
      - / (Division, 결과 소수점 포함)
      - DIV ( Integral division without remainder. 결과 소수점X 정수만)
      - ** (Exponentiation, a**b)
      - MOD (Remainder after integral division)
       
   - 비교연산자
      -  = EQ 2. > GT 3. < LT 4. >= GE 5. <= LE 6. <> NE
      
   - 관계연산자
      -  CO - Contains Only / 'abc' a,b,c (1글자 단위) 만 포함이면 true입니다.
      -  CN - Contains Not Only /  1번과 반대입니다.
      -  CA - Contains Any / 'abc' a,b,c 중 하나라도 포함이면 true입니다.
      -  NA - Contains Not Any / 3번과 반대입니다.
      -  CS - Contains String 'abc' / 'abc' (문자열 전체) 를 포함하면 true입니다.
      -  NS - Contains No String / 5번과 반대입니다.
      -  CP - Covers Pattern / 패턴을 찾을때, "*" (문자열) 이나 "+" (1글자) 가능합니다.
      -  NP - No Pattern / 7번과 반대입니다.

<br/>

### 조건문
 
  -  IF
  
  ```
      IF <condition> AND <conditoin>.   변수 is initial / 변수 is not initial 도 가능  
        <statements>.
     ELSEIF <condition> OR <condition>.
        <statements>.
     ELSEIF charlen ( gv_str ) BETWEEN gv_low AND gv_hi. (Predefined Function)
        <statements>.
     ELSEIF sqrt ( abs ( gv_val1 ) ) / 3 + 7 GT ipow ( base = 2 exp = 3 ). (Arithmetic Expression)
        <statements>.
     ELSEIF ‘lit1’ && gv_str CA ‘abc’ (String Expression)  * CA(Contain Any) : a,b,c 중 하나라도 포함 true
        <statements>.
     ELSE.
        <statements>.
     ENDIF.
```

<br/>

  -  CASE
  
  ```
     CASE <variable>.
        WHEN <element>.
           <statements>.
        WHEN <element>.
           <statements>.
        WHEN OTHERS.
           <statements>.
     ENDCASE.
```

<br/>

### 반복문

 
  -  DO.
  
```
      statements.
      IF <abort_condition>. EXIT. ENDIF.
    ENDDO.
    - loop counter : sy-index
    기본적으로 무한 loop
```

<br/>

  -  DO n TIMES
  
  ```
      statements.
    ENDDO.
    - loop counter : sy-index
```

  - WHILE <condition>.
  
  ```
      statements.
     ENDWHILE.
    - loop counter : sy-index
```

  - SELECT INTO (CORRESPONDING FIELDS OF) <work area> FROM <dbtable>
  
  ```
       statements.
     ENDSELECT.
    - database table rows : sy-dbcnt
```

  - LOOP AT <internal table> INTO <structure v>  제일 많이 사용
  
  ```
       statements.
    ENDLOOP.
    - row index for internal table : sy-tabix
    internal table의 내용을 한 건씩, 즉 한 레코드씩 읽어서 work area에 move한 다음
    endloop까지 선언된 내용을 처리.
```

<br/><br/>

### Dialog Messages type 6가지 

```
   MESSAGE tnnn(message_class) [ with v1 [v2] [v3] [v4] ].    - & 최대 4개까지 사용가능(동적할당)
   ex. MESSAGE i007(BC410).
   ex. MESSAGE 'Error Message' TYPE 'E'.   
   ex. MESSAGE S000 WITH '정상 생성 되었습니다.' DISPLAY LIKE 'S’
      -> SE91 > S000은 ZMESSAGE19 에 생성 후 사용, BC410도 있음.
```

   - I : Info Message (popup box) 
   - S : Set Message (Success Message) (status bar)
   - W : Warning (빨강) (status bar) 
   - E : Error (노랑) (status bar)
   - A : Termination (popup box)
   - X : Short Dump
  
<br/>

 ### 다국어 지원 사용법
 
   1. Text Elements -> Text Symbols -> Sym, Txt 입력 후 Activate
   2. Goto -> translation -> 언어 선택 -> 밑에칸에 언어넣고 저장하면 끝!

<br/>

 ### Text Elements
 
   - Text Symbols 
     - T01 text1 , T02 text2  /  사용 : TEXT-T01
     
   - Selection Texts
     - Selection Screen 상에 있는 Parameter들의 이름을 원하는 대로 변경
     
   - List Headings
     - 결과값 맨 위에 어떤 정보인지 입력가능, 4줄까지 입력가능, 첫 줄에 쓰면 한 줄만 나옴.

<br/><br/>

### Modulazation

   1. Subroutine
     - 모듈화, 재사용, 구조화가 주 목적.
     - 재사용 목적이 아니더라도 전체적인 흐름을 쉽게 파악할 수 있도록 모듈화하는 것이 바람직함.	
     - 주로 프로그램 내부에서 사용. 재사용성 목적이 다른 거 보다 작음.
     
     ```
     <main program>
     PERFORM calc_percentage  (subroutine 호출)
              USING pa_int1 pa_int2
              CHANGING gv_result.
     ```


```
<subroutine>
FORM calc_percentage USING pv_act TYPE I
                             pv_max TYPE I
                 CHANGING  cv_result TYPE tv_result.
ENDFORM.
```

<br/>

    
### subroutine에서 parameter 사용 (순서, 개수 맞아야함)

   -  Interface Parameter (Actual Parameter – Formal Parameter)
   -  PERFORM(main)의 USING CHANGING => Actual Parameter
   -  FORM(sub)의 USING CHANGING => Formal Parameter
   -  USING => Input Parameter
   -  CHANGING => Output Parameter

<br/>

### pass type 3가지

   -  Call by value => Subroutine에서 Using Value 구문
      - Formal Parameter값이 변경되더라도 Actual Parameter값이 변경되지 않는다.
        
   - Call by value and result => Subroutine에서 Changing Value 구문
 	  - Subroutine이 정상적으로 종료될 경우 Actual Parameter값이 변경된다.
    
   - Call by reference => changing만
      - Formal Parameter값이 변경되면 Actual Parameter값이 변경된다.

   - 호출방법 4가지
        1) Internal Call : 같은 프로그램에서 Call
        2) External Call : 다른 프로그램에서 Call, subroutine은 public 속성을 가지고 있음.
        3) Dynamic Call : Runtime에 서브루틴을 바꾸어 call할 때
        4) List Call : List를 이용한 Call, 파라미터를 전달할 수 없다.
 
<br/>
 
### Function Module

  - 여러 프로그램에서 다 실행 가능.
  - Subroutine과 목적이 유사(모듈화 및 재사용).
  - Function Group안에 포함.
  - 단일 테스트 가능, 에러발생 시 예외처리 가능.
  - SE80(Object navigator), SE37(function builder) 수정가능
  - Naming Rule : (Main) 'SAPL'+Function group명 / (Include) 'L'+Function group명+ ‘TOP'/'UXX'
  - FUNCTION MODULE은 STANDARD로 많이 정의되어있음. 찾아서 쓰면됨.    
  - Import, Export, Changing, Table 탭에서 Function Module의 Interface를 정의하고 
  - Source Code에서 코딩  
  
```
      CALL FUNCTION ‘BC400_MOS_POWER’
      EXPORTING
       iv_base = pa_int1
       iv_power = pa_int2
      IMPORTING
       ev_result = gv_result.
      EXCEPTIONS
       POWER_VALUE_TOO_HIGH = 1.
       OTHERS                  = 2.
       
       Function Module에서의 parameter naming rule
       iv_act => I = import
       ev_percentage => e = export
       v = variable, s : structure, t : table
```

<br/>

### RFC (Remote Function Call)

 - 물리적 또는 논리적으로 다른 시스템의 상대 프로그램(function)을 호출하고 실행 할 수 있는 Function Module을 말한다.
 - Legacy system과 인터페이스 하기 위해 사용
 - Remote function을 사용하기 위해서는 먼저 Target 시스템의 Destination을 등록하고 Remote 
 - Logon 할 수 있도록 Network Interface 환경을 설정해야 한다. 또한 호출되어지는 function 
 - Module은 Remote function supported Type으로 등록해야 상대 시스템에서 호출할 수 있다.

<br/>

### CLASS Method
  
  - SE24 (Class Builder) 찾을 수 있음.
     1) Instance Method 
        - Class로 Obect 생성하고 Reference Variable이 참조하게 된다.
        - 생성한 Object 여러 개 가능
        - CALL METHOD XXXX -> get_percentage
        - class에 object마다 method존재
        
     2) Static Method
        - Class 이름=>Method 이름 바로 사용 가능  (공용느낌)
        - 클래스 하나만 존재
        - CALL METHOD zcl_clc19_compute => get_percentage
        - class에 1개만 존재
        
     ex. 급여계산 method는 object별 (사원1, 사원2, 사원3...) => instance method
        사원정보는 static method
    

  -  Function Module은 Object 여러 개 생성 못하지만 Class는 Object를 많이 만들어낼 수 있다. 
  -  Function은 공용인데 Class는 Private 목적이다.
 
 <br/>
 
### BAPIs (Business Application Programming Interfaces)
   - 유저에게 편의성 제공, 이전에 BDC였는데 스크린 기반이라 느렸다.
   - 그래서 Function 개념으로 1대1 매칭.
   - Function Module인데 조금 더 큰 작업

<br/>

▪ Definition Local Type, type 만들기 ( WF2 p.394 )
   TYPES: BEGIN OF <local types 이름, ts_name>,
      TYPE 선언
   END OF <local types 이름, ts_name>. 
   
   
▪ Structure끼리 값 할당
   MOVE-CORRESPONDING <structure v1> TO <structurev2>
   structure1 값을 structure2로
   

▪ Internal Table 종류 3가지 ( WF2 p.412 )
   1. STANDARD : index로 접근가능, Component이름으로 접근가능, 중복된 data허용
   2. SORTED : index로 접근가능, Component이름으로 접근가능, 중복된 data허용/허용X 둘다
   3. HASHED : Component이름으로 접근가능, 중복된 data허용X

▪ABAP itab control하는 구문과 Open SQL 구문 비교 주의!  (ABAP Workbench Fundamentals - 2)
  ( WF2 p. 413 ) itab control 구문
  APPEND : APPEND gs TO gt_itab (마지막에 추가)
  INSERT : INSERT gs INTO TABLE gt_itab (원하는 위치에 추가)
  READ : READ TABLE gt_itab INTO gs (1건의 data 읽어올 때)
  CHANGE : MODIFY TABLE gt_itab FROM gs (특정 필드값 수정위해 read하고 수정하고 반영)
  DELETE : DELETE gt_itab (삭제)
  ( WF2 p. 493 ) open sql 구문
  INSERT (data 생성)
  UPDATE (data 수정)
  DELETE (data 삭제)
  MODIFY (insert+update, table에 data가 존재하면 update 없으면 insert)
 
▪ Internal Table에 Single Record 추가하는 2가지 방법
   1. APPEND <structure v> TO <itab>
   2. INSERT <structure v> INTO TABLE <itab> 
     INSERT gs TO TABLE itab => APPEND랑 같은 값
     INSERT gs TO itab INDEX 1. => index 1로 들어가기 때문에 위랑 반대 값

▪ Internal Table에서 Single Record (한 건의 data) 가져오는 방법
   READ TABLE <itab> INTO <structure> <condition>
   ex. READ TABLE GT_SCARR INTO GS_SCARR       WITH KEY CARRID = GS_DATA-CARRID.
▪ 특정 필드값 수정
   MODIFY TABLE <itab> FROM <structure v>.

▪ LOOP 문으로 Index에 Access 하는법 2가지
   1. Index Access
   LOOP AT gt_flightinfo INTO gs_flightinfo FROM 1 TO 5.   (FROM 2 : 2부터 끝까지)
   (한 건의 데이터 읽기)
   READ TABLE gt_flightinfo INTO gs_flightinfo INDEX 3.

   2. Key Access
   LOOP AT gt_flightinfo INTO gs_flightinfo WHERE carrid = ‘LH’.
   (한 건의 데이터 읽기)
   READ TABLE gt_flightinfo INTO gs_flightinfo WITH TABLE KEY carrid = ‘LH’
							   connid = ‘0400’
							   fldate = sy-datum.
   ( 여기서 Key component 하나라도 빠지면 Error
     WITH KEY 로 쓰면 key component 다 안와도됨. )

▪ Table에 있는 값 삭제
   REFRESH gt_flightinfo.
   CLEAR gt_flightinfo.
   FREE gt_flightinfo.

   CLEAR gt_flightinfo-carrid  => carrid 만 삭제

▪ Header Line이 있는 Internal Table
   DATA: itab TYPE STANDARD TABLE OF ts
        	   WITH NON-UNIQUE KEY carrid 
              WITH HEADER LINE.
   => itab : header line / itab[] : internal table

▪ Filling an internal table
   SELECT * FROM spfli
             APPENDING TABLE gt_spfli
             WHERE ...
   SELECT cityfrom FROM spfli
             APPENDING COREESPONDING FIELDS OF TABLE gt_spfli
             WHERE ...
   한 itab에 두 쿼리 내용을 전부 담고 싶을 때 사용, 이렇게 하면 테이블 안없어지고 또 담아짐.

Fund1 - UNIT 12 : Data Modeling and Data Retrieval

▪ Single data 가져오기 ( WF2 p.457 )
   1. SELECT SINGLE <column field name or * > FROM <table> INTO <structure v>
                                                WHERE <condition>

   SELECT SINGLE * FROM scarr INTO ls_scarr WHERE carid = iv_carrid.

   SELECT SINGLE seatsmax seatsocc FROM sflight INTO CORRESPONDING FIELDS OF ls_flight
     	WHERE carrid = iv_carrid AND connid= iv_connid AND fldate = iv_fldate
   CORRESPONDING FIELDS OF : SELECT 구절의 column과 target의 column 이름 같은 곳에 할당

▪ ABAP에서 ENDSELECT 안오는 경우 2가지
   1. SELECT SINGLE
   2. SELECT INTO 로 table가져올 때

▪ 여러 건의 data 가져오는 법 2가지
   1. SELECT ENDSELECT (LOOP)  -> select loop 는 performance적으로 좋지 않다.
      SELECT carrid connid fldate seatsocc seatsmax
      FROM sflight 
      INTO ls_flight  <structure v>  “INTO CORRESPONDING FILEDS OF ...
      WHERE carrid = iv_carrid.
      ENDSELECT.

   2. ARRAY FETCH
      SELECT carrid connid fldate seatsmax seatsocc
      FROM sflight 
      INTO TABLE et_flights <itab> “ INTO CORRESPONDING FIELDS OF TABLE
      WHERE carrid = iv_carrid.


▪ Array fetch와 select endselect 구동방식 차이 주의


Array Fetch는 한꺼번에 테이블에 받고나서 loop문으로 수정을 해주는 거니깐 modify가 되는데
Select Endselect는 하나하나 뽑기 때문에 modify를 쓰면 안된다. 저 때는 et_result가 empty이기 때문에
결과값이 0이 나와버린다.

▪ ABAP의 SELECT 구문은 3개다!
   1. SELECT SINGLE
   2. SELECT ENDSELECT
   3. ARRAY FETCH
   
▪ 다른 Client의 data가 필요한 경우
   SELECT * FROM spfli INTO ...
   CLIENT SPECIFIED
   WHERE mandt = 402
   AND carrid = ‘AA’.

Fund1 - UNIT 13 : Classic ABAP Reports

▪ 출력값에 글자색넣기, 아이콘 넣기
   ex. WRITE:/ GS_FLIGHT-CARRID COLOR COL_POSITIVE
   ABAP에서 지원하는 7가지 색
   col_heading(파랑), normal(하늘), total(노랑), key(진하늘), positive(초록), negative(빨강), group(주황)

   Use ICON ( SE11에서 ICON 검색하면 가능)
   WRITE  ICON_GREEN_LIGHT AS ICON.

▪ ABAP Event 발생 순서

load of program과 initialization은 프로그램 실행시켰을 때 한번만 실행된다.
initialization은 report program에서만, 그래서 screen program에서는 load of program으로 default 설정

AT SELECTION-SCREEN OUPUT은 screen 프로그램의 PBO와 같고
AT SELECTION-SCREEN은 screen 프로그램의 PAI와 같다.

Fund1 - UNIT 14 : Program Anaylsis Tools

▪ Code Inspector : -> program -> mouse right button -> check -> code inspector
   Inspection, Object Set, Check Variant

Fund1 - UNIT 15 : Program Calls and Memory Management

▪ 프로그램 호출 방법 2가지 ( WF2 p.562 )
   두 종류
   1. Insert program : program1에서 pr2 호출하면 pr1 과 pr2가 동시에 흐름
   2. Start New Program : pr1에서 pr2 호출하면 pr1 멈추고 pr2를 실행, 다시 pr1로 돌아가지 않음

   1. 프로그램 이름으로 호출
   1-1. SUBMIT <(report)program name> ( Start new program ) 
   1-2. SUBMIT <program name> ( Insert program ) 
       AND RETURN. 
   2. 프로그램 t-code로 호출
   2-1. LEAVE TO TRANSACTION <t-code> ( Start new program ) 
   2-2. CALL TRANSACTION <t-code> ( Insert program ) 

▪ Memory Management ( WF2 p.584 )
   - External Session : SAP GUI 로그인하면 뜨는 창 (new gui window)
     구성 -> ABAP memory와 여러개의 Internal Session
   - Internal Session : 여기서 프로그램이 실행
     구성 -> MPG(Main Program Group)과 APG(Additional Program Group)
     Main에는 Main Program과 Global data object
     Additional에는 Function group과 global class, Global data object, static attributes
 
   - Program Groups : 구성 -> 여러 개의 External Session과 SAP memory
    
   - WF2 p.597
     ABAP Memory는 External Section마다 존재, Internal Section간 데이터 주고받을 때 사용
     SAP Memory는 다른 External Section과도 소통 가능
    
   - Access SAP Memory WF2 p.599
     SET PARAMETER ID / GET PARAMETER ID

Fund1 - UNIT 16 : ABAP Open SQL

▪ Classical Open SQL 과 New Open SQL
   <Classical open sql>
     - fixed value ‘A’ as a X
     - column과 colmun 연산 X
     - CASE X
     - Right Outer Join X
     - UNION X
     - Subquery는 where절만 사용 가능, select, from X

   <New open sql>
     - SELECT column1, column2 구분자 콤마사용
     - 변수 앞에 @사용
     - CASE O
     - CAST로 형변환 O
     - Column 연산가능
     - UNION, UNION ALL O
     - Performance가 classical에 비해 좋다.

▪ sql 정렬 ( WF2 p.619 )
   SELECT connid fldate seatsocc FROM sflight
   INTO TABLE gt_flightocc WHERE seatsocc > 200
   ORDER BY connid DESCENDING seatsocc ASCENDING.

▪ sql 중복 제거
   SELECT DISTINCT carrid connid FROM sflight INTO TABLE gt_flight WHERE seatsocc > 200.

▪ sql Aggregate function
   SELECT MIN(col) MAX(col) SUM(col) AVG(col) COUNT(*) 
   FROM ~
   ex.
   SELECT COUNT (*) MIN ( seatsocc ) MAX ( seatsocc ) SUM ( seatsocc )
   FROM sflight INTO gs_flightocc.
   중복제거 응용
   SELECT COUNT ( * ) COUNT ( DISTINCT connid ) SUM ( DISTINCT seatsocc )    
   FROM sflight INTO gs_flightocc.

▪ sql group ( WF2 p.632 )
   SELECT carrid connid SUM ( seatsocc ) FROM sflight INTO TABLE gt_flightocc
   WHERE fldate > ‘20090101’
   GROUP BY carrid connid HAVING SUM ( seatsocc ) < 500.

   carrid connid 중심으로 묶어줌.
   group by 뒤 having절로 조건 표시

WF2 p.629부터 sql 정리 다시

Fund2 - UNIT 1 : Introduction to the ABAP Dictionary

▪ Database Interface : Open SQL을 native SQL로 변환시키는 역할인데 이 때 ABAP Dictionary에서
      	  	      참조해서 변환시킨다.

Fund2 - UNIT 2 : Data Types in the ABAP Dictionary

▪ ABAP Dictionary 기능 (WF3 p.11)
   - Database Object 기능에 해당 ( Database table, View )
   - Datatype에 대한 Definition ( Data type, Type Group, Domain )
   - Service 부분 ( Search Help, Lock Object ) 

▪ Domain과 Data Element (WF3 p.14)
   - Domain : Element Field에 대한 Technical Attribute(길이, 타입) 정의
              Domain은 Data Element 가 사용한다. (Certi)
              SE11에서 생성가능. 
              case-sensitive는 이 domain을 사용하는 field는 대소문자를 구별하겠다는 의미.
              Value Range 탭에서 Fixed Value 입력 가능 ( 필드에 입력할 수 있는 값 지정 )
   - Data Element : Domain + Semantic(의미) Properties를 포함
     사용 : Internal Table의 Line Type(열이 하나인 테이블)  
           ex. DATA: gt_flight TYPE sflight-carrid.
           DB table의 fields,
           structure의 components.

▪ Internal Table 변수 선언 7가지 방법


▪ Create Table ( WF3 p.32 )
   - Internal Table의 Line Structure 의 타입으로 쓸 수 있는 것
     1) Data Element  / TYPE TABLE OF s_carr_id
       
        
     2) View의 Fields  / TYPE dv_flights
     3) transparent table  / TYPE scarr
     4) Structure의 Components / TYPE TABLE OF BC400_S_FLIGHT

     SE11에서 Table Type 이라고 되있는 것 ex. BC400_T_FLIGHT 는 바로 TYPE ##
              Transparent Table은 TYPE TABLE OF ## ex. SCARR

     SE11에서 Table Type 만들 때 ZT##가 아니라 ZY## 이렇게 naming 한다.

▪ Structure 변수 선언 6가지


- Structure의 Components로 쓸 수 있는 것
  1) Data Element / Simple Structure
  2) View의 Fields / DATA: GT_FLIGHT TYPE TABLE OF DV_FLIGHTS,                        GS_FLIGHT LIKE LINE OF GT_FLIGHT.

                    DATA: wa1 TYPE DV_FLIGHTS.

  3) DB Table의 Fields / DATA: GT_FLIGHT TYPE TABLE OF SFLIGHT,                        GS_FLIGHT LIKE LINE OF GT_FLIGHT.
           
   			DATA: wa1 TYPE SPFLI.

  4) Internal Table의 Line Structure

▪ Create Structure 종류 3가지 ( WP3 p.23 )
   1. Simple : Component 모두가 data element로만 되어있는 Structure
              Access) GS_FLIGHT-CARRID
   2. Nested : Component type에 structure type이 올 수 있다.
              Access) GS_FLIGHT-FROM-AIRPFROM
                     GS_FLIGHT-FROM-CITYFROM
          => Nested보다 .Include 더 많이 사용함, Access) GS_FLIGHT-AIRPFROM
     .Include => Standard에서 공통적인 필드만들 때 사용, 하나 만들어놓고 다같이 공유, 
                 수정사항있으면 한번에 고칠 수 있는 장점 ( WF3 p.59 )
   3. Deep : Component type에 table type이 올 수 있음.
             data가 Header와 Detail로 나눠지는 경우 많이 사용
             ex. FI전표, SALES, PP Purchase Order에 따른 Items
             핸들링하기 위해서 Work Area가 필요하고 출력시 LOOP문 사용


   table, structure 든 생성할 때 amount field들 currency, quantity 들은
   Currency Field 탭에 가서 Reference table (ex. zcargo_clc19)와 Reference Field (Currency) 입력해야함.

▪ Type Group ( WP3 p.44 )
   실무에서 자주 사용하는 상수값을 Type Group으로 만든다.
   ex. Type Group : ABAP
      => ABAP_true ( ‘X’ ) , ABAP_false ( ‘ ’ ), ABAP_undefined ( ‘_’ )

    CASE abap_true.
       WHEN CREATE.   -> 이런식으로 사용 가능

▪ ABAP Dictionary 의 Transparent Table는 Database server에 table을 생성하기 위한 정의서, 설명서.
   그래서 ABAP Program에서 Data Type 으로 사용가능
   Transparent Table의 주목적은 Database Server의 Table 생성이다.
   Activate 하면 database 서버에 물리적인 table 생성 

Fund2 - UNIT 3 : Database Tables

▪ DB Table = Transparent Table => Structrue Type으로 사용가능 ( WF3 p.77 )
   Transparent Table의 주목적 : DB에 테이블을 생성하기 위함(Activate)
   ABAP Dictionary의 3개 table type => Transparent Table, Cluster Table, Pool Tables

Fund2 - UNIT 4 : Performance During Table Access

▪ Data Access Performance 증가 3가지 ( WF3 p.97 ) ( WF4 p.485 )
   1. Key field
   2. Secondary Index => SE11 > create indexes / 딱 필요한 field만 index 설정
                         보통 short description에 뭘로 key를 가지는지 적음 mandt+cargo_desc
   3. Table Buffer => CBO에선 Application Server별로 Buffer가 존재하기 때문에 잘 사용하지 않는다.
                     Application Server와 Database Server가 Synchronization이 안될 수 있다.
                     Master Table 같이 수정 잘 안일어나는 테이블에 Table Buffer 사용이 좋음.
      1) Pull Buffering
      2) Generic Buffering : generic key = 1 ~ key field수-1
      3) Single-record Buffering

   마스터테이블은 수정이 잘 안일어나니깐 마스터테이블 같은 곳에 Buffer를 많이 사용한다.

Fund2 - UNIT 5 : Input Checks

▪ Domain에서 Fixed Value 입력가능
   - fixed value 이외의 값이 들어오면 input check
     PARAMETERS: ### Type ### Value Check  =>  F4 유효값만 display

▪ Foreign key를 이용한 input check ( WF3 p.126 )
   - foreign key 설정 주 목적 : table간 연결에 있어서 data의 일관성을 주기 위해
   - se11 -> utilities -> table contents -> create entries
     field에서 열쇠모양(foreign key) 선택 -> value table 설정했으면 물어본다.
     -> yes : 자동적으로 필드 입력 -> Enter   ( 다시 해보기 )

▪ Text Table : text관리할 때 code table에 text만 넣어서 하나의 table로 관리할 수 있다.
   ( WF3 p.147 )



Fund2 - UNIT 6 : Dictionary Object Dependencies

▪ data element, domain -> Utilities -> where-used-list에서 어디서 사용되고 있는지 확인가능

Fund2 - UNIT 7 : Table Changes

▪ Standard를 바꾸는 법 2가지 ( WF3 p.194 )
   1. Enhancement
     Standard Table에 구멍 뚫어서 필드 추가하고 싶을 때 APPEND STRUCTURE 사용 (ZZ, YY)
   2. Modification
     Standard를 완전히 바꾸는 것.          
   
   Include는 standard에서 공통적인 필드만들 때 사용

▪ Program 개발? 순서
   Standard -> Enhancement -> CBO -> Modification

Fund2 - UNIT 8 : Views and Maintanance Views

▪ Table 합치기 ( WF3 p.210 ) ( WF4 p.492 ) ( WF2 p.651 )
   1. View 생성
     - 해당 Data가 여러 프로그램에서 사용될 때는 View 생성 (Reusable)
     1) database view : program에서 사용, 하나 이상의 테이블로 뷰 생성, INNER JOIN만 사용
     2) projection view : program에서 사용, 하나의 테이블로만 뷰 생성
                    하나의 테이블에 있는 특정 필드의 data를 보여주지 않고자 할 때 ex. HR 급여t
     3) maintenance view : program에서 사용X, 생성 수정 유지보수할 때 사용, LEFT OUTER JOIN
                           개발자는 SE11, SE16과 같은 Workbench Tool을 이용해서 data를 
                           생성, 수정, 삭제할 수 있지만 일반 User들은 Maintenance View와 
                           Maintenance Dialog를 통해 생성, 수정, 삭제한다.
        	  	   Foreign Key 필수 / 생성 ( WF3 p.231 )
      			   two step -> overview + detail
     4) help view : Program에서 사용X, Search Help에서 사용, F4키 눌렀을 때 따는 화면에서 사용,
                   LEFT OUTER JOIN

     SE11 -> View 생성 ZV### 생성 
     -> Table/Join Condition : 가져올 Table
     -> View Field : Table Fields 에서 원하는 Table 가져오기, 중복 유의해서 선택
     -> Selection Conditions : 조건입력
    
     Dynamic한 field 생성 
     view field에서 * 로 모든 필드를 받아온다. 
     - / table name / field name 으로 중복된 값 빼준다. ex. - / SCARR / mandt

     View 생성할 때는 View Field에 MANDT가 꼭 와야한다. (없으면 모든 클라이언트의 데이터를 가져옴)  

     View도 data type으로 사용가능. (Structure type) ex. wa_flights TYPE sv_flights.   
  
   2. Join 구문 확인
     - data 읽어오는 거 한번만 사용할 거면 join사용	
       Inner Join	
       SELECT <fieldlist> INTO <target>
          FROM <dbtab1> [AS <alias1>]
          INNER JOIN <dbtab2> [AS <alias2>]
          ON <alias1>~<dbtab1-field1> = <alias2>~<dbtab2-field1>
          AND <alias1>~<dbtab1-field2> = <alias2>~<dbtab2-field2>
          AND ...
          WHERE ...
       ENDSELECT.

Fund2 - UNIT 9 : Search Helps

▪ input check에서 F4누르면 나오는 화면을 Possible Entry (Possible Value) 라고 함.
▪ Search Help = Input Help = After Help
▪ SE11에서 craete -> Selection Method에는 Table이나 View가 올 수 있다. ( WF3 p.252 )
   만드는 거 다시ㅣㅣㅣㅣ 모르겠음.
▪ Search Help로 Attach 할 수 있는 곳
   1. Structure의 필드 2. Table의 필드 3. Data Element 4. Screen Painter 직접연결 (비추천)

Fund2 - UNIT 10 : Selection Screen

▪ Selection Screen 생성 2가지 ( WF3 p.292 )
   1. Single Selections : 입력값 한가지 받을 때
      PARAMETERS: pa_con TYPE spfli-connid.
      
      - PARAMETERS에 사용할 수 있는 구문
        변수이름은 최대 8자리까지 가능
        TYPE <data type> LIKE <variable> DECIMALS <n> 
        MEMORY ID <parmeter id> : SAP memory에 올리고 싶을 때. <pid에 임의 id 가능>
        OBLIGATORY : 필수 입력 필드로 만듬(Mandantory Field)
        DEFAULT <value> : 초기값
        LOWER CASE : 대소문자 구분   /    VALUE CHECK : Fixed Value Check (Domain)      
        AS CHECKBOX : 체크박스 생성, 다f중 체크 가능
        RADIOBUTTON GROUP <grp> : 라디오버튼 생성, grp 중 하나만 체크 가능.
                      		       DEFAULT값 안주면 첫 번째 라디오버튼이 클릭되있음.
        MODIFY ID <mod> : Runtime시에 변경할 수 있는데 하나의 그룹으로 묶어서 변경하고싶을 때
                             전에 친 거 나갔다와도 저장되있음.

        CHECKBOX => IF문으로 조건 생성
        CONSTANTS gc_mark VALUE 'X'.
        IF pa_name EQ gc_mark <statements> ENDIF.
        IF pa_curr EQ gc_mark <statements> ENDIF.


        RADIOBUTTON => CASE문으로 조건 생성
        CASE gc_mark
           WHEN pa_lim1 <statements>.
           WHEN pa_lim2 <statements>.
           WHEN pa_lim3 <statements>.
        ENDCASE.

   2. Complex Selections : 입력값 범위로 받을 때  ( WF2 p.524 )
      DATA gs_spfli TYPE spfli.
      SELECT-OPTIONS :
      so_car FOR gs_spfli-carrid. => data typeX 선언되어져 있는 변수의 특정 필드이름
      Elementary 변수이름, Structure의 특정 Field 이름
     
      DATA: GV_CARR TYPE SCARR-CARRID. -> FOR GV_CARR      DATA: GS_SCARR TYPE SCARR. -> FOR GS_SCARR-CARRID

      SELECT * FROM spfli
      INTO gs_spfli WHERE carrid IN so_car.

      SELECT-OPTIONS 선언하면 so_car 같은 이름의 변수가 두 개 생성됨(header, Itab)
      SELECT-OPTIONS는 Internal Table이다.	

      selection condition 
      sign : I (Include), E (Exclude)
      option : EQ, BT ... 10개
      low : 첫 번째 field에 입력되어지는 값
      high : 두 번째 field에 입력되어지는 값
      
      값을 넣을 때 sign, option, low 이렇게 다 들어가야지 입력이 된다. 하나 빼먹으면 안되드라.

      ex. 초기값 설정
      * 400 ~ 402 포함 초기값
      INITIALIZATION.         SO_CON-SIGN = 'I'.  “ so_con header line에 들어가는 내용        SO_CON-OPTION = 'BT'.        SO_CON-LOW = 400.        SO_CON-HIGH = 402.        APPEND SO_CON.   “ so_con[] internal table에 들어감.

      - SELECT-OPTIONS에 사용할 수 있는 구문
        변수이름은 최대 8자리까지 가능
        SELECT-OPTIONS <seltab> FOR <f>
        DEFAULT <value> : low 초기값 설정
        DEFAULT <value1> TO <value2> : low, high 초기값 설정
        MEMORY ID <pid> : SAP Memory에 올리고 싶을 때, pid: parameter id, 임의값 가능
        LOWER CASE : 대소문자 구분
        OBLIGATORY : 필수 필드로 만듬
        NO-EXTENSION : Hidden Multiple-Selection Button
        NO INTERVALS : Hidden to button
        MODIF ID <mod> : Runtime 시 변경할 시 하나의 그룹으로 묶어서 변경하고자할 때

        - Block 생성
          SELECTION-SCREEN BEGIN OF BLOCK <name> WITH FRAME TITLE <text>.
 	  SELECTION-END OF BLOCK <name>.

          SELECTION-SCREEN BEGIN OF LINE.
          SELECTION-SCREEN END OF LINE.  : 가로로 줄세우기

          SELECTION-SCREEN COMMENT pos(len) <text> FOR FIELD <field>
          : pos는 포지션, 위치, len 길이, pos위치에 pos_low(파라미터 위치에서 시작),
           pos_high(report 라인 끝에서 시작) 도 가능
            <text> For ~~ 는 이 텍스트와 field는 연관되어있다.를 의미. text 클릭해도 check box 클릭
           ex. SELECTION-SCREEN COMMENT 1 (20) TEXT-T01 FOR FIELD PA_COL.

        - Multiple Selection Screen => Tabstrips   ( WF3 p.310 )
          Tapstrips : Selection Screen에 필드가 많을 때 간결하게 배치하고자할 때
          1. 서브스크린 생성  2. 탭 생성

          1. SELECTION-SCREEN BEGIN OF SCREEN 101 AS SUBSCREEN.
		SELECT-OPTIONS : so_car ~~~~~
    	    SELECTION-SCREEN END OF SCREEN 101.

    	  2. SELECTION-SCREEN BEGIN OF TABBED BLOCK <name> FOR 5 LINES.
		SELECTION-SCREEN TAB (10) tab1 USER-COMMAND comm1 DEFAULT SCREEN 101.
		SELECTION-SCREEN TAB (10) tab2 USER-COMMAND comm2 DEFAULT SCREEN 102. 
 		SELECTION-SCREEN TAB (10) tab3 USER-COMMAND comm3 DEFAULT SCREEN 103.
             SELECTION-SCREEN END OF BLOCK <name>.      
             (10) : tab title에 대한 길이
    	     user-command : tab click 위치
             5 lines : 5라인이 넘어가면 세로 스크롤이 생긴다.
 
            INITIALIZATION   ->  tab title 설정
 	    tab1 = ‘Connection’.  
	    tab2 = ‘Flight’.
    	    tab3 = ‘Booking‘.
	    tab_block-activetab = ‘COMM2’.   ” Default값으로 다른 탭 페이지 뜨게할 때
            tab_block-dynnr = 102 

Fund2 - UNIT 11 : Introduction to Screen Programming

▪ Screen Program
   Screen Painter : SE51 => 보통 SE80에서 CREATE SCREEN을 주로 씀.
   - 프로그램 구조
     MZDEMOTOP : Global Declataions. 
     MZDEMO-E01 : ABAP Events 처리  E01 ~ E99
     MZDEMO_F01 : Form routines (subroutine) 여러 스크린에서 공통적으로 사용되어지는 로직
     MZDEMO_<Module>I01 : PAI module 
     MZDEMO_<Module>O01 : PBO module 
     
     Include를 꼭 사용해야하는 것은 아니다. Main에다가 다 할 수 있지만 나눠서 관리하기 위함이다.
     report program에서도 사용된다.

   - PBO (Process Before Output) : screen 100 오기 전에 실행, 
                           report program의 abap event인 AT SELECTION-SCREEN OUTPUT과 비슷
   - PAI (Process After Input) : screen에서 event(action) 발생하면 실행
   - Screen : Screen Element를 담을 수 있는 Container
   - Normal Screen은 100번 단위 생성, Subscreen과 modal dialog들은 10번 단위로 생성
   - Screen 1000 => Layout => Screen Painter 에 Screen Element 넣는 법 2가지
     1. Element들 생성 (끌어오기)
     2. ABAP Dictionary Structure에서 가져오기 
       Dictionary/Program fields window (F6) => ex. SDYN_CONN 입력 -> 필드 선택
       Painter에서 Field 더블클릭 -> attribute 창 -> mandantory(required), SAP memory (SET, GET)...

   - Identical Names
     Screen에다가 Screen Element 생성하고 각각 Element에 존재하는 이름을 ABAP Program에도 
     똑같은 이름으로 선언해서 연결한다.
     ex. Dyn-field 인 SDYN_CONN (screen structure connection)
     TOP 에 TABLES : SDYN_CONN ( = DATA: sdyn_conn TYPE sdyn_conn. ) 선언
     ABAP Program에서 data가져올 때 사용할 Work Area 선언
     PBO에서 MOVE-CORRESPONDING으로 Work-Area와 Dyn-Field 값 계속 할당

   - PBO PAI screen 100 동작방법 ( WF4 p.359 )
     1) PBO 100  (내용X)
     2) 100 PAI PBO 100(event)
     3) PAI PBO 100(event)
     4) PAI PBO 100(event) ...
     
   - Screen Go Back
     leave to screen 0

   - Dynamic Screen Modification 만들기 + 그룹화  ( clear_okcode 앞에 넣기 )
     1. Layout -> painter에서 pushbutton 생성 -> attribute의 Fct Code에 'BTN'
        Output Field 전부 그룹화 첫 번째 Field 에 'CHG'
     2. Element List -> type이 ok 인 친구 ok_code로 name 지정
     3. TOP include에서 ok_code TYPE sy-ucomm. ( function code 컨트롤하기 위해 생성 )     
        sy-ucomm : 명령버튼의 function code
       gv_mode TYPE char1 VALUE 'D'. 선언
     4. PAI
       MODULE USER-COMMAND 에 CASE IF 문으로 WHEN 'BTN' IF gv_mode = 'D' ... ELSE 'C' ...
       PBO
       MODULE MODIFY_SCREEN 에 IF gv_mode = ‘C' 
       LOOP AT SCREEN.
        IF SCREEN-GROUP1 = 'CHG'. 
	   SCREEN-INPUT = 1. “ input, output, active 등등 1/0 으로 true/false
           MODIFY SCREEN. " 변경값을 Screen이라는 System Table에 반영 

       Report Program에서는 Selection Options 의 Modify로 그룹화할 수 있다.
       그 다음 AT SELECTION-SCREEN OUTPUT에서 LOOP문으로 변경가능.

   - Screen 은 Header까 있는 Internal Table과 유사하지만 Itab은 아니다.
    PBO가 실행되기 전에 Element List에 있는 정보들이 Screen이라는 System Table에 카피되어진다.
    Runtime 시 변경하고자할 때는 PBO에서 처리해야한다.

   - 스크린 순서 디자인
     1. Static -> Attribute -> Next Screen
     2. Dynamic -> PAI -> LEAVE TO SCREEN, CALL SCREEN
    
     Leave To Screen : 넘어가면 끝. 다시 돌아오지 못함.
     Call Screen : 현재 체인이 중단되고 다음 스크린을 부른다. 그 스크린에서 Go back 하면 
                중단된 체인으로 다시 돌아올 수 있다. 다시 돌아오면 Call Screen 밑에 있는 코드실행
     Set Screen : 혼자 쓰면 Call과 같지만 보통 Set Screen 300. Leave Screen. 같이 쓴다.
       
   - Dialog Box 띄우기 -> 팝업창
     Screen 150 생성 -> Dynpro Type에서 Modal Dialog Box 선택 
     호출 CALL SCREEN 150 
           STARTING AT lc ur
           ENDING AT rc lr.

Fund2 - UNIT 12 : The Program Interface

   - User Interface 
     PBO 의 MODULE STATUS_0100
     SET PF-STATUS 'S100'
      -> Function Key, Application Toolbar, Menu Bar 설정가능
         - Status type 3종류
           1) Normal Screen
     	   2) Dialog Box : 팝업이라 메뉴바가 없다. ( dialog box에서 작업할 때 사용 )
  	   3) Context Menu : 우클릭하면 나오는 팝업메뉴
         * Subscreen은 Subscreen Area에서 사용되기 때문에 Status는 없다.

      -> 이렇게 Function Code 지정해주면 (PAI) USER_COMMAND_0100에서 
         CASE문으로 ok_code 설정해줘야함.

     SET TITLEBAR 'T100'   ( WITH TEXT-T01 )
      -> Title 입력가능, 동적구성 & 9개 까지 가능. 써주고 All Title에서 Activate 해야함.

Fund2 - UNIT 13 : Simple Screen Elements

   - Status Icon 만드는 법 
     1. Screen Painter에서 아이콘 생성  
     2. Top Include에서 변수생성 TYPE icons-text
     3. PBO 모듈 생성 MODULE set_icon
       ICON-CREATE function module 사용
       name : ‘아이콘 이름’  result : status icon name
 
   - Input/Output Fields 생성 2가지 방법
     1. Screen Painter에서 만들고 Top Include에서 변수선언
     2. Top Include에서 변수 선언하고 Screen Paiter의 Dict.Program Field (F6)에서 변수이름 가져오기

        Structure 변수도 선언하고 가져올 수 있지만 text는 안뜬다.

   - Dropdown Box
     보통 data가 20건 내외일 때 사용.

   - Radio Buttons 실행
    1. Radio Buttons 만들고 name들 같은 Group으로 묶어주고 FctCode ( TOGGLE )도 설정해준다.  
    2. PAI에서 user_command_100에서 (status에서도 사용 가능, 라디오 버튼에 따른 제목변경)
       CASE ok_code
          WHEN 'TOGGLE'.
   	     CASE 'X'.
  		WHEN display.  <statement>.
  		WHEN change.  <statement>.
  		WHEN create.  <statement>.


Fund2 - UNIT 14 : Screen Error Handling

   - Input Check ( WF4 p.463 )
     Screen PAI에서 FIELD 라는 키워드를 사용
     CHAIN.
	FIELD <Field Name>, <fn2>, <fn3>.
 	  MODULE check_input ON CHAIN-INPUT  ( REQUEST )
     * INPUT : 초기값이라도 있으면 넘어간다. / REQUEST : 필드에 새로운 값이 입력되었을 때만 체크
     ENDCHAIN.
        
     => MODULE check_input INPUT.
         <statements>
         MESSAGE E ...   ( WF4 p.458 , 6가지 dialog messages )
         ENDMODULE.    

     Input Check 에 걸리면 display 화면에서 check 걸린 input field만 활성화되고 나머지는 비활성화

     동작원리  ex. moduel check a / check b / check cd / check cb
     Screen -> (action) -> PAI -> FIELD CHECK -> field와 chain안의 field문 통과 후 
     -> check cb에서 걸렷을 경우 -> A, D는 output필드로 바뀌고 C, B만 바꿀 수 있게 input필드로
     남겨짐 (Ready for input again) -> 입력 후 다시 Field check b부터 logic 시작된다.
   - Automatic Input Check
     1. Mandantory field check
     2. Field format check
     3. Fixed value check
     4. Foreign key check

   - Automatic Check 없이 Screen 빠져나오는 법
     1. Status 100에서 Exit와 Cancel의 fuction type을 ‘E'로 변경
     2. PAI 에 MODULE exit AT EXIT-COMMAND. 생성
        CASE ok_code.
  	  WHEN 'CANCEL'.
	     CLEAR ok_code.
             LEAVE TO SCREEN 0.
          WHEN 'EXIT'.
             LEAVE PROGRAM.

▪ 초기값 설정 방법
   Report Program
    1. Parameter이나 Selection Options에 Default 값으로 설정
    2. INITIALIZATION abap event를 통한 설정
    3. MEMORY ID <pid>
   Screen Program
    1. SAP Memory를 사용. Get Parameter Id,  Set Parameter id
    2. 시작과 동시에 모든 필드에 데이터 초기값으로 넣기
      INCLUDE에 E01 프로그램 생성
      LOAD-OF-PROGRAM. 이벤트 생성
      GET PARAMETER ID: <pid> FIELD <field-name>
      SELECT SINGLE * 로 SFLIGHT에 있는 값 가져오기
 
Fund2 - UNIT 15 : Subscreens

▪ 서브스크린 생성방법 (같은 프로그램 내 서브스크린)   ( WF4 p.487 )
   1. Screen에 Subscreen Area 생성
     Screen 100 -> Screen Painter -> Subscreen Area -> name 지정 'SUB'
   2. Screens에서 Subscreen 생성 ( type을 subscreen으로 ) 
     Layout에서 화면 구성 ( 필드 추가 )
   3. Screen 110 -> PBO MODULE get_spfli 추가
      SELECT SINGLE * FROM SPFLI INTO CORRESPONDING FIELDS OF SDYN_CONN
        WHERE <condition>
   4. Screen 100 -> PBO -> CALL SUBSCREEN SUB
                              INCLUDING sy-cprog '0110'     * sy-cprog : 호출한 프로그램명
                    PAI -> CALL SUBSCREEN SUB.
   ( 동작 구조 ) 100 PBO > 110 PBO > 100 Screen > (action) > 100 PAI > 110 PAI >  100 Screen

▪ 서브스크린 생성방법 (다른 프로그램의 서브스크린)   ( WF4 p.494 )
   Function Group의 Function Module을 이용한다. 
   ( 동작 구조 )  
    
Screen 100
Sub Area 생성
Function Group에
SubScreen 생성 (필드들)
PBO
MODULE exporting pa_##
CALL SUBSCREEN
Function Module 1
importing is_##  ( 필드들에 맞는 변수 )
PBO에서 pa 데이터를 주면 FM1에서는 import 변수에 담고 그 값이 필드들로 올라가서
SubScreen에 Display되고 screen 100에서는 CALL SUBSCREEN에 의해 Sub Area에 담는다.
PAI
MODULE importing pa_##
CALL SUBSCREEN
Function Module 2
exporting es_##
screen100에서 user가 직접 pa_##를 쳐서 action 하면 FM2 값으로 가서 똑같이 subscreen에
display하고 CALL SUBSCREEN으로 Sub Area에 담는다.


▪ 서브스크린 번호에 따라 조건 주고 싶을 때
   1. TOP INCLUDE에 subscreen 번호를 할당할 변수 선언 
      DYNNR TYPE SY-DYNNR             * sy-dynnr : Screen Number
   2. PBO -> MODULE SET_DYNNR 생성F
      CASE 'X'.  WHEN <ok_code> dynner = 110. ~~~~ 120 130

   여러 서브스크린을 넣었을 때 110 120 130 각각에 필드를 다 넣어주었을 때
   각각 스크린의 PBO에서 get_data를 해줘야한다.

Fund2 - UNIT 16 : Tabstrip Controls

▪ Create Tabstrip
   1. Tabstrip Area
      Screen 100 -> Layout Editor -> TabStrip 생성, name : tab_strip

   2. Tab Title
     Attribute에서 tab 개수 설정, tab name, text, FctCode, FctType 설정
     TAB1 Dep. TAB1 P ( ) / TAB2 Arr. TAB2 P ( ) / TAB3 Info TAB3 P ( ) 

   3. Subscreen Areas
   3-1. Local Scrolling ( P )
     각 Tab마다 Area생성 name 지정.  SUB1 SUB2 SUB3
   4. TOP INCLUDE 에 변수선언
     CONTROLS: tab_strip TYPE tabstrip.
   5. PBO -> CALL SUBSCREEN SUB1
                INCLUDING sy-cprog '0110'   .... sub2 sub3
     PAI -> CALL SUBSCREEN SUB1.  ... sub2 sub3. 

   3-2. PAI Scrolling ( none )
     Tab1 에만 Area 생성 name 지정. SUB4 ,  Tab2, 3의 Ref. Field 에 SUB4
   4. TOP INCLUDE 에 변수선언
     CONTROLS: tab_strip TYPE tabstrip.
   5. PAI -> module user-command에서
         WHEN 'TAB1' OR 'TAB2' OR 'TAB3'.   => Function Code 이름들임              TAB_STRIP-ACTIVETAB = OK_CODE.
     PBO -> module set_dynnr에서
       CASE TAB_STRIP-ACTIVETAB.          WHEN 'TAB1'.  => Function Code 이름들              DYNNR = 110.   ... 120 130
       WHEN OTHERS. " 할당되어진 값이 아무것도 없으면 1로             DYNNR = 110.             TAB_STRIP-ACTIVETAB = 'TAB1'.
     PAI ->  CALL SUBSCREEN SUB4                 INCLUDING SY-CPROG DYNNR.
      PBO -> CALL SUBSCREEN SUB4.  
Rep - UNIT 1 : Introduction to ABAP Reports
Rep - UNIT 2 : Selection Screens
▪ Report 프로그램에서 Screen 만들기
   - screen 100 생성하고 layout에 subscreen area 만들고 
     PROCESS BEFORE OUTPUT.        CALL SUBSCREEN SUB          INCLUDING SY-CPROG '1100'.     PROCESS AFTER INPUT.        CALL SUBSCREEN SUB.
   - E01에 END-OF-SELECTION. 에 CALL SCREEN 100.

   - TOP에서 스크린들 생성 후 프로그램에서 우측클릭 Additional Function -> Rebuild object list
     하면 스크린 자동생성됨.

▪ Input Check
   AT SELECTION-SCREEN ON ( <field> <seltab> radiobutton group <grp> block <block> )
   AT SELECTION-SCREEN ON so_dept
      IF so_dept <> 0.
        MESSAGE '안돼요.‘ TYPE 'E'.

   Screen에서는 Chain, Field로 하고 실제 체크는 MODULE에서 했다.

▪ Push Button 넣기 ( R p.69 )
   TOP INCLUDE에
   1. TABLES: sscrfields.  " 이게 선언되어져 있어야됨.
   2. SELECTION-SCREEN PUSHBUTTON /pos_low(20) pushbtn USER-COMMAND BTN. 
   E01에서 INITIALIZATION. " 1000번 스크린에서 display / AT SELECTION-SCREEN에서 처리 (PAI)
   3. pushbtn = 'change/display'.

   4. AT SELECTION-SCREEN OUTPUT에서 초기값 설정
      IF GV_MODE = '0'.         LOOP AT SCREEN.           IF SCREEN-NAME CS 'SO_FDT'.              SCREEN-INPUT = 0.              MODIFY SCREEN.           ENDIF.         ENDLOOP.       ENDIF.
   5. AT SELECTION-SCREEN에서 PUSH 버튼 처리
     CASE SSCRFIELDS-UCOMM.    	WHEN 'BTN'.      	   IF gv_mode = '1'.             gv_mode = '0'.           ELSE.             gv_mode = '1'.           ENDIF.      ENDCASE.
   
   DATA: GV_MODE TYPE N VALUE '1'.  변수선언

   Runtime 시 변경할 때는 AT SELECTION-SCREEN OUTPUT (PBO) 에서 처리.
   SELECTION-SCREEN SKIP (숫자). 숫자만큼 띄움

▪ event하고 screen keyword 정리
https://dhan-description.tistory.com/116

Rep - UNIT 3 : SAP List Viewer (ALV) Creation ★★★
▪ ALV 생성  
   1. Area 생성  ( R p.107 )
     1) SFLIGHT 타입 Itab, structure 변수생성
     2) Select-Options : so_car, so_con 생성
     3) START-OF-SELECTION에 Select문으로 SFLIGHT 데이터를 gt_flights로 가져오기
     4) START-OF-SELECTION에 CALL SCREEN 100.
     5) screen 100 생성, layout에 Custom Control 생성 ( name, resizing, length 설정 )
     6) PBO에 MODULE STATUS_100 생성 후 Function key 설정 ( BACK, EXIT, CANCEL )
                                           Title 설정
     7) Element list에서 OK_CODE 설정, 메인 프로그램에서 sy-ucomm 타입 변수 설정
     8) PAI MODULE USER_COMMAND_0100에서 CASE문으로 Function키에 대한 조건설정
   2. Container 생성 ( R p.115 )
     1) 변수생성 
        DATA: GO_CONTAINER TYPE REF TO CL_GUI_CUSTOM_CONTAINER
     2) PBO -> MODULE CREATE_AND_TRANSFER 생성, 
        Object 생성 -> (Pattern) Call Object, Instance : go_container, Class : cl_gui_custom_container
         IF go_container is initial.
           EXPORTING CONTAINER_NAME = 'MY_CONTROL_AREA' " container를 area에 담기
   3. ALV Grid Control 생성
     1) 변수생성
        DATA: GO_ALV_GRID TYPE REF TO CL_GUI_ALV_GRID.
     2) PBO MODULE CREATE_AND_TRANSFER에 Object 생성, container와 연결
        (Pattern) Call Object, Instance : go_alv_grid, Class : cl_gui_alv_grid
        IF go_container is initial 안에
        EXPORTING I_PARENT = go_container " alv_grid를 container에 담기 
     3) MODULE CREATE_AND_TRANSFER 에 display하기위한 메소드 사용
        IF go_container is initial 안에
        (Pattern) Call Method, Instance : go_alv_grid, Class : cl_gui_alv_grid, 
                 Method : set_table_for_first_display
        EXPORTING I_STRUCTURE_NAME : 'SFLIGHT' " 이걸로 alv grid의 필드가 정해짐
        CHANGING IT_OUTTAB = gt_flights " actual 값으로 alv grid에 display할 data를 담고있는 Itab
     4) 만약 display한 내용을 다른 action 이후에도 남겨놓을려면 
        IF go_container is initial 의 마지막 ELSE문에 go_alv_grid->refresh_table_display 메소드 사용
        ( R p.132 ) -> 이거 쓰면 안쓰면 똑같은 내용만 뜸.

( R p.125 )
PERFORM FREE_CONTROL_RESOURCES
객체를 새로 지우고 쓰는거 
다른 성질이 DATA를 띄우고 싶을 때 객체 지우고 다시 띄우고 싶을 때 사용한ㄷ.


Rep - UNIT 4 : ALV Design

▪ Layout Variant : ALV display된 화면 저장/불러오기 ( R p.144 )
   1. 저장하기
      1) 변수선언 gs_variant TYPE disvariant.
      2) display 메소드 위에 gs_variant-report = sy-cprog "현재 프로그램 이름 할당
      3) display 메소드 안에 IS_VARIANT  = gs_variant.
                            I_SAVE = 'A'   " U, X, A 가능
        is_variant 넣으면 결과화면에 Choose layout, Change layout 생기고
        I_save 넣으면 결과화면에 Save layout, Manage layout 생김
   2. 불러오기
      1) PARAMETERS: pa_lv TYPE disvariant-variant.
      2) display 메소드 위에 gs_variant-variant = pa_lv
         " pa_lv가 variant 필드에 들어가고 gs_변수가 is_값에 들어가면서 초기화면 실행
      3) 원하는 결과 save layout 하고 parameter값에 layout이름 넣어주면 됨.





▪ Layout 꾸미기 ( R p.153 )
   1. 변수선언 DATA: gs_layout TYPE lvc_s_layo. " 60여개의 필드들로 layout변경가능
   2. display 메소드에 is_layout = gs_layout
   3. display 메소드 위에 필드값 입력, 깔끔하게 서브루틴 사용해도됨 PERFORM set_layout
      gs_layout-zebra = 'X'.

▪ Exception Columns 신호등 ( R p.156 )
   1. 변수선언 
      TYPES: BEGIN OF gty_book.
	      INCLUDE TYPE sbook.	
      TYPES: LIGHT TYPE char1,
             END OF gty_book.
      => sbook의 모든 필드와 light 필드를 가진 local type 생성
      DATA: gt_data TYPE TABLE OF gty_book,
            gs_data LIKE LINE OF gt_data.
    2. loop문 돌면서 light필드에 값 채우기
      CALL FUNCTION 'DATE_GET_MONTH_LASTDAY'         EXPORTING            I_DATE = SY-DATUM " 오늘날짜         IMPORTING            E_DATE = GV_EDATE. " 오늘날짜기준 마지막일자

       LOOP AT GT_DATA INTO GS_DATA. " gt에있는내용을 gs한줄씩 보겠다.         IF GS_DATA-FLDATE < SY-DATUM.            GS_DATA-LIGHT = 1.         ELSEIF GS_DATA-FLDATE >= SY-DATUM                AND GS_DATA-FLDATE <= GV_EDATE.                   GS_DATA-LIGHT = 2.         ELSEIF GS_DATA-FLDATE > GV_EDATE.             GS_DATA-LIGHT = 3.         ENDIF.        MODIFY GT_DATA FROM GS_DATA. " gs한줄로부터 gt에수정한다.      ENDLOOP.
     3. display 메소드 위에 gs_layout-excp_fname = 'LIGHT'. 추가
 
▪ Sort ( R p.157 )
   1. 변수선언
      DATA : gt_sort TYPE lvc_t_sort,
             gs_sort TYPE lvc_s_sort.
   2. display 메소드 위에, 서브루틴 써도되고 바로 써도되고 PERFORM set_sort
      gs_sort-fieldname = 'CUSTOMID'.  " Default : Ascending
      APPEND gs_sort TO gt_sort.  
      
      gs_sort-fieldname = 'ORDER_DATE'.
      gs_sort-down = 'X'.   " Descending
      APPEND gs_sort TO gt_sort.
   3. display 메소드 안에
      CHANGING it_sort = gt_sort.

▪ Record에 색 표시 ( R p.158 )
   1. 필드가 하나더 필요함, 원래 만들어놓은 begin of ~~~ end of 에 넣는다.
      TYPES : COLOR TYPE CHAR4,  
      “ 4칸짜리 문자로 이루어지는데 첫 번째는 무조건 C, 두 번째는 색이름 (0~7 or 색 상수값),
       세 번째는 진하게(1), 그냥(0), 네 번째는 글자배경 색반전(1), 그냥(0) / 11은 잘 안돼
   2. LOOP문 안에  
     IF gs_data-smoker = 'X'.
       gs_data-color = 'C' && COL_NEGATIVE && '10'.
     ENDIF.
     * CONCATENATE 'C' COL_NEGATIVE '10' INTO GS_DATA-COLOR
   3. display 메소드 위에 layout     
      gs_layout-info_fname = 'COLOR'.

▪ Cell에 색 표시 ( R p.160 )
   1. ITab 필드 추가 IT_COL TYPE LVC_T_SCOL, 	  " deep structure
   2. Work Area 추가 DATA: gs_col TYPE lvc_s_scol  “ nested structure
   3. LOOP문 안에
      IF GS_DATA-INVOICE = 'X'.   -> O X 값은 is not initial 로 활용 가능         GS_COL-FNAME = 'INVOICE'.   "색주고 싶은 필드명         GS_COL-COLOR-COL = 3.         GS_COL-COLOR-INT = 1.         GS_COL-COLOR-INV = 0.         APPEND GS_COL TO GS_DATA-IT_COL.  “ 수정한 structure를 itab에 적용       ENDIF.
   4. display 메소드 위에 layout
      GS_LAYOUT-CTAB_FNAME = 'IT_COL'.

▪ Standard function 숨기기 ( R p.163 )
   1. 변수선언
      DATA: GT_TOOLBAR TYPE UI_FUNCTIONS.
   2. display 메소드 위에 서브루틴 하나 만들기 PERFORM set_toolbar
     APPEND CL_GUI_ALV_GRID=>MC_FC_PRINT TO GT_TOOLBAR.
     SE24 class builder에서 cl_gui_alv_grid > attribute에서 상수값들 확인 가능 mc_fc, mc_mb
   3. diplay method 안에서  
     EXPORTING IT_TOOLBAR_EXCLUDING = GT_TOOLBAR
   


▪ Field Catalog ( R p.179 )
   1. Field Catalog 사용방법 3가지
     1) 자동구성 
        display 메소드에 I_STRUCTURE_NAME에 dictionary에 있는 테이블 넣어서 사용
     2) 수동구성        display 메소드 CHANGING에 IT_FIELDCATALOG를 이용해서 필드를 하나씩 생성
        서브루틴으로 gs_fcat-fieldname = 'CARRID'. ... 이런식으로 다 만들어주기
     3) 둘다 같이 사용
        I_STRUCTURE_NAME하고 IT_FIELDCATALOG 이거 같이 사용
        Dictionary에서 가져와서 field 쓰고 추가해서 쓸 때
   2. Field Catalog 설정방법 3가지
     1) Fieldname, ref_field, ref_table 포함 사용
     2) 1)에 더해서 추가적인 속성값을 사용하는 방법
     3) 참조테이블, 참조 필드 없이 이름과 다른 속성값만 설정하는 방법 
   
   만약 ALV grid에 새로운 필드를 추가하고 싶다.
   1. 필드추가
      1) PHONE TYPE SCUSTOM-TELEPHONE,        2) CANCEL_ICON TYPE ICON-ID
   2. Itab 생성
      DATA: GT_FCAT TYPE	LVC_T_FCAT.
   3. display 메소드 위 서브루틴 생성 PERFORM SET_FCAT.
      DATA: LS_FCAT TYPE LVC_S_FCAT.
   3-1.       LS_FCAT-FIELDNAME = 'PHONE'.      LS_FACT-REF_FIELD = 'TELEPHONE'.      LS_FACT_REF_TABLE = 'SCUSTOM'.      LS_FCAT-COL-POS   = 6.      APPEND LS_FCAT TO GT_FCAT.
   3-2.
      CLEAR: LS_FCAT.      LS_FCAT-FIELDNAME = 'CANCEL_ICON'.      LS_FCAT-ICON      = 'X'. " 이 필드는 아이콘으로 표시하겠다.       LS_FCAT-COLTEXT   = 'Cancelled'. " Column 헤드라인 이름      APPEND LS_FCAT TO GT_FCAT.
   4. LOOP문안에 조건 달기
   4-1. 다른 테이블 SCUSTOM에서 data 가져오기
      SELECT SINGLE TELEPHONE 
         INTO GS_DATA-PHONE         FROM SCUSTOM         WHERE ID = GS_DATA-CUSTOMID.
   4-2. 
      IF GS_DATA-CANCELLED = 'X'.   “ SFLIGHT에서 X된 것만         GS_DATA-CANCEL_ICON = ICON_INCOMPLETE. ” X아이콘으로 표시      ENDIF.
   5. display 메소드 안에 
     CHANGING   IT_FIELDCATALOG = GT_FCAT

   그 외에도 Field Catalog의 다양한 필드들을 통해서 layout을 수정할 수 있다.

   MODIFY ~~ TRANSPORTING 필드명 쓰면 퍼포먼스는 더 좋아진다.
   안쓰면 전부 업데이트
   쓰면 쓴 필드만 업데이트

Rep - UNIT 5 : ALV Events and Methods

▪ 이벤트 처리 ( R p.206 )
  1. local class 생성, 정의 -> 변수 선언 밑에    이건 static method
     CLASS LCL_EVENT_HANDLER DEFINITION.        PUBLIC SECTION.          CLASS-METHODS: ON_DOUBLE_CLICK FOR EVENT DOUBLE_CLICK          OF CL_GUI_ALV_GRID          IMPORTING ES_ROW_NO E_COLUMN.     ENDCLASS.
    " cl_gui_alv_grid 이 클래스에서 double click 이벤트를 발생시키면 on_double 메소드에서 처리하겠다.
  2. method 구현
    CLASS LCL_EVENT_HANDLER IMPLEMENTATION.      METHOD ON_DOUBLE_CLICK.         MESSAGE I016(BC405_408) WITH ES_ROW_NO-ROW_ID E_COLUMN-FIELDNAME.      ENDMETHOD.    ENDCLASS.
  3. method 등록
    display 메소드 위에
    SET HANDLER LCL_EVENT_HANDLER=>ON_DOUBLE_CLICK FOR GO_ALV_GRID.
    클래스이름=>메소드이름 : static은 해당 클래스에 하나만 존재하기 때문에
  * 이 참조변수(오브젝트) (go_alv) 에서 이벤트 (double click) 이 일어나면 이 클래스 (lcl_evnet_handler)의    이 메소드 (on_double_click) 에서 처리하겠다.
  
  - instance method
    1. class-methods -> methods
     CLASS LCL_EVENT_HANDLER DEFINITION.        PUBLIC SECTION.          METHODS: ON_DOUBLE_CLICK FOR EVENT DOUBLE_CLICK          OF CL_GUI_ALV_GRID          IMPORTING ES_ROW_NO E_COLUMN.     ENDCLASS.
    2. class 선언 밑에 
      DATA: GO_HANDLER TYPE REF TO LCL_EVENT_HANDLER.
    3. display 메소드 위에 
      오브젝트 생성 CREATE OBJECT GO_HANDLER.
      이제 오브젝트 안에 메소드가 있는 것
      SET HANDLER GO_HANDLER->ON_SPOT_CLICK FOR GO_ALV.
      참조변수->메소드

  - 응용
    더블클릭 시 팝업띄우기
    1. screen dialog 타입 만들고 layout 구성, PBO 에 movecorresponding으로 정보 가져와주고
    2. local class 구현부에서 
      READ TABLE GT_DATA INTO GS_DATA INDEX ES_ROW_NO-ROW_ID.        CALL SCREEN 110          STARTING AT 5 3.
    3. 더 응용하자면 팝업창에 status로 save, cancel 만들고 input창에 정보 입력하게 해서
      PAI에서 모듈만들어서 저장하게끔.

▪ Static vs Instance method
   static은 객체 생성없이 메소드 사용. 메소드가 클래스에 하나만 존재
   instance는 각각 생성한 객체마다 메소드 사용 가능
   
   grid에서 instance를 쓰는 경우는 컨테이너를 두 개로 나누었을 때 활용가능

▪ 정리 
   생성순서 : 클래스 -> 참조변수 -> 객체
   참조변수와 객체는 이름이 같다. 한 객체에 여러 참조변수가 포인트할 수 있다.

▪Custom Toolbar ( R p.217 )
  1. local class 정의에 추가. 툴바 정의
     CLASS LCL_EVENT_HANDLER DEFINITION.       PUBLIC SECTION.         CLASS-METHODS:           ON_TOOLBAR FOR EVENT TOOLBAR             OF CL_GUI_ALV_GRID             IMPORTING E_OBJECT.     ENDCLASS.

  2. 툴바 구현
     CLASS LCL_EVENT_HANDLER IMPLEMENTATION.
           METHOD ON_TOOLBAR.         DATA: LS_BUTTON TYPE STB_BUTTON.         LS_BUTTON-BUTN_TYPE = 3.         APPEND LS_BUTTON TO E_OBJECT->MT_TOOLBAR.         CLEAR: LS_BUTTON.         LS_BUTTON-FUNCTION = 'FC1'.         LS_BUTTON-BUTN_TYPE = 0.         LS_BUTTON-ICON =  ICON_CREATE.         ls_button-TEXT = 'Button'.         ls_button-quickinfo = 'ALV Toolbar Button'.         APPEND LS_BUTTON TO E_OBJECT->MT_TOOLBAR.       ENDMETHOD.
     ENDCLASS.

         E_OBJECT->MT_TOOLBAR.
        여기서 e_object는 toolbar 이벤트의 파라미터로 있는데 cl_alv_event_toolbar_set 클래스를 
        참조하고 있는 참조변수이다. 이 클래스는 인스턴스 속성을 가지고 있어서 객체를 자동적으로 
        만든다. 그래서 e_object가 cl_alv_event_toolbar_set 클래스의 객체가 가지고 있는
        attribute인 mt_toolbar라는 테이블을 가르키는 것.

  3. 툴바 등록, diplay 메소드 위에
    SET HANDLER LCL_EVENT_HANDLER=>ON_TOOLBAR FOR GO_ALV.
   
▪ Custom Toolbar 버튼 활성화
   1. 정의
      CLASS LCL_EVENT_HANDLER DEFINITION.       PUBLIC SECTION.         CLASS-METHODS:           ON_USER_COMMAND FOR EVENT USER_COMMAND             OF CL_GUI_ALV_GRID             IMPORTING E_UCOMM.      ENDCLASS.

    2. 구현
        METHOD ON_USER_COMMAND.         DATA: LV_BIZCNT TYPE I,               LV_TOTCNT TYPE I,               LV_MSGTXT TYPE STRING.         DATA: LT_TMP LIKE GT_DATA. " lv_bizcnt itab         CASE E_UCOMM.           WHEN 'FC1'.             LV_TOTCNT = LINES( GT_DATA ).  
             " ( itab ) -> 해당 인터널테이블이 가지고 있는 레코드 개수를 리턴해준다. 해당변수로             LT_TMP = GT_DATA.             DELETE LT_TMP WHERE CUSTTYPE <> 'B'. " B가아닌건다삭제             lv_bizcnt = lines( lt_tmp ).             LV_MSGTXT = 'Total Record : ' && LV_TOTCNT &&                           ' Business Customer : ' && LV_BIZCNT.             MESSAGE LV_MSGTXT TYPE 'I'.           WHEN OTHERS.         ENDCASE.       ENDMETHOD.
    3. 등록
    SET HANDLER LCL_EVENT_HANDLER=>ON_USER_COMMAND FOR GO_ALV.

▪ 필드에 버튼넣기
   1. 변수로 Itab 추가, work area 추가     TYPES:   LIGHT       TYPE CHAR1, 		          ...              BTN         TYPE LVC_T_STYL, " 버튼생성 itab 선언              BTN_TXT     TYPE CHAR15,       END OF GTY_BOOK.

     DATA: GS_BTN TYPE LVC_S_STYL. " itab 컨트롤하기 위한 work area.

    2. invoice가 x인것만 체크한다고 했을 때 
       start-of-selection에서 
       IF GS_DATA-INVOICE = 'X'.		...
         APPEND GS_COL TO GS_DATA-IT_COL.         GS_BTN-FIELDNAME = 'BTN_TXT'.         GS_BTN-STYLE = CL_GUI_ALV_GRID=>MC_STYLE_BUTTON.         APPEND GS_BTN TO GS_DATA-BTN.         GS_DATA-BTN_TXT = 'Invoice'.

    3. layout과 field catalog 변경
       GS_LAYOUT-STYLEFNAME = 'BTN'.

       CLEAR: LS_FCAT.       LS_FCAT-FIELDNAME = 'BTN_TXT'.       LS_FCAT-COLTEXT = 'Buttons'.       ls_fcat-col_pos = 1. "첫번째필드에       APPEND LS_FCAT TO GT_FCAT.

▪ ALV 클래스에 있는 메소드 사용하기 ( R p.232 )
   ex. get_selected_rows.
       implementation 부분 method on_user_command에
       DATA: LT_ROW TYPE LVC_T_ROID, " get selected rows itab             LS_ROW TYPE LVC_S_ROID. " itab work area

       pattern : instance : go_alv, class : cl_gui_alv_grid, method : get_selected_rows
       CALL METHOD GO_ALV->GET_SELECTED_ROWS       IMPORTING          ET_ROW_NO = LT_ROW.


Rep - UNIT 6 : Data Retrieval With Local Databases

▪ LDB ( Logical DataBase )
   - t-code : se36   
   - 사용목적(활용) :
     1) 여러 유사 프로그램에서 공통된 SELECTION-SCREEN을 사용하고자 할 때
        (반복되는 프로그램에서 selection screen을 코딩할 필요없음)
     2) 아래의 이미지 처럼 SELECTION SCREEN에 Dynamic Selection을 구현할 때

    - 구성 3요소
      1) Structure(구조) : 여기서 Node의 상하관계는 Selection을 구성했을때 순서 및 의존관계를 
                         가지고 설정한다.
      2) Selections(선택사항) : 계층적 구조에 의해 포함된 Node중 Selection-Screen에서 사용할 Node를 
                             선택한다.
      3) Database Program : Put Event를 가지며, ABAP에 의해서 Get으로 호출시 구동된다. 
                            (실질적인 Data Select부분을 코딩한다)

    - 탈출 3방법 : [Start-of-selection]화면에서 탈출할 수 있는 코드는 아래와 같다
      1) CHECK	: 주어진 조건을 만족하지 못하면 바로위의 GET으로 돌아간다.
      2) EXIT : 일반적인 Loop 탈출명령과 동일하다, 현재의 이벤트를 바로 탈출한다.
      3) STOP : Exit와 동일하며 탈출시 [End-of-selection]을 호출한다.

      NODES: SPFLI, SFLIGHT, SBOOK.   
      NODE는 ldb에서 테이블을 사용할 때 선언하는 것.
      NODE : LDB에서는 dbtab 각 1개를 1개의 노드라 한다.
      GET SPFLI.
      GET은 ldb에서 select 구문과 유사한 역할을 수행한다.

Rep - UNIT 7 : Data Retrieval without Logical Databases

▪ LDB 없이 데이터 가져오기. open sql  
   여러 테이블에서 데이터를 internal table로 가져오는 방법 중 하나.
   - FOR ALL ENTRIES
     주의할 점
     1. itab에 데이터가 한 건도 없을 경우 모든 데이터를 읽어온다. 그래서 itab에 데이터가 있는지
       없는지 먼저 확인해야함.
     2. Where절에 조건거는 필드로 sort하고 중복된 데이터를 제거해야한다.
       sort안되있으면 중복제거 안됨.
       SORT gt_spfli.   DELETE ADJACENT DUPLICATES FROM gt_spfli.     

     FOR ALL ENTRIES 구문은 인터널 테이블의 서브 쿼리와 유사한 기능을 수행합니다. 
     FOR ALL ENTRIES 구문을 사용할 때 WHERE 구문의 조건은 itab에 존재하는 필드만 가능합니다.

     SELECT * FROM sflight
     INTO CORRESPONDING FIELDS OF TABLE gt_sflight
     FOR ALL ENTRIES IN  gt_spfli
     WHERE carrid  = gt_spfli-carrid AND connid = gt_spfli-connid.

​

Rep - UNIT 8 : The Call of Other Programs from ABAP Reports (안한대)
Rep - UNIT 9 : Background Processing
Rep - UNIT 10 : ALV Object Model

Data - UNIT 1 : Database Updates with ABAP Open SQL

▪ Transparent Table Data Control ( DU p.5 )
   1. Insert Single Data
   1-1. INSERT INTO ZSCARR_CLC19<dbtab> VALUE GS_SCARR<work area>.
   1-2. INSERT ZSCARR_CLC19<dbtab> FROM GS_SCARR<work area>.
   2. Insert Multiple Data
       INSERT ZSCARR_CLC19<dbtab> FROM TABLE GT_SCARR<itab>[ACCEPTING DUPLICATE KEYS].
       accepting은 보통 insert구문에서 key의 중복으로 table에 넣을 때 덤프떨어지는데 이걸 쓰면 
       덤프X.
   3. Update Single Data
   3-1. UPDATE ZSCARR_CLC19<dbtab> FROM GS_SCARR<work area>.
   3-2. UPDATE ZSCARR_CLC19<dbtab> SET CURRCODE = 'USD'    <field>=<내용>                                   URL = 'http://www.jaepyo.so.com'          WHERE CARRID = 'DL'. " key field, <full qualified key>
   4. Update Multiple Data
   4-1. UPDATE ZSCARR_CLC19<dbtab> FROM TABLE GT_SCARR<itab>.
   4-2. UPDATE ZSCARR_CLC19 SET CURRCODE = 'USD'          WHERE CARRID BETWEEN 'AA' AND 'DL'.
   5. Modify
   5-1. MODIFY ZSCARR_CLC19<dbtab> FROM GS_SCARR<work area>.
   5-2. MODIFY ZSCARR_CLC19<dbtab> FROM TABLE GT_SCARR<itab>.
   6. Delete Single Data 
   6-1. DELETE ZSCARR_CLC19<dbtab> FROM GS_SCARR<work area>.
   6-2. DELETE FROM ZSCARR_CLC19<dbtab> WHERE CARRID = 'Z1'. " key field 전부
   7. Delete Multiple Data 
   7-1. DELETE ZSCARR_CLC19 FROM TABLE GT_SCARR<itab>.
   7-2. DELETE FROM ZSCARR_CLC19<dbtab> WHERE currcode = 'USD'.

Data - UNIT 2 : Database Change Bundling

▪ Logical Unit of Work (LUW)  ( DU p.36 )
   데이터는 번들로 처리한다.
   All or Nothing principle
   유저의 request는 work process에서 처리되는데 시스템의 자원에 한계가 있기 때문에 1대1로 매칭
   되지 않는다. 화면이 display될 때 database에 자동 commit 된다.

Data - UNIT 3 : SAP Locking

▪ T-code : SM12 통해서 코딩 중간에 발생한 Lock의 상태를 확인하도록 한다.

▪ 서로 다른 프로그램에서 같은 테이블을 사용할 때 특정 프로그램이 수정하고 있으면 다른 프로그램이 
   수정못하게, 접근못하게끔 하는 동작이 필요함. ( 데이터 일관성 )
   첫 DB Commit부터 마지막 DB Commit까지 Lock 설정

▪ Lock Object 생성
   1. SE11 Lock Object에서 create, EZ_SPFLI_CLC19
   2. Primary Table에 ZSPFLI_CLC19 입력
   3. Active하면 Enqueue, Dequeue Function Module 2개 생성.
      menu bar -> goto -> lock modules에서 모듈확인 가능

▪ Lock들은 Container에 저장되있다가 FLUSH_ENQUEUE function module을 만나면 한번에 
   Lock Table에 저장

▪ Lock mode
   1. Exclusive lock (E) : 오직 한명의 사용자에 대해서만 접근 가능
   2. Shared Lock (S) : 여러 명의 사용자의 데이터를 읽을 수 있으나, 특정 사용자에 의해서 변경이
                       시작되면 모두 잠긴다.
   3. Exclusive Lock (X) : 작업 Transaction 내에서 단 한번만 Lock을 요청할 수 있다.

Data - UNIT 4 : Organization of Database Updates
▪졸음
Data - UNIT 5 : LUWs Across Multiple Programs
▪졸음
Data - UNIT 6 : Number Assignment
Data - UNIT 7 : Database Change Logging
Data - UNIT 8 : Object Services
Data - UNIT 9 : Cluster Tables
Data - UNIT 10 : Program-Controlled Program Calls

Con1 - UNIT 1 : Introduction to Object-Oriented Programming
▪ Main Program에서 Function Module 호출하면 Function Group이 통째로 호출됨.
▪ Procedural 프로그램과 Object-oriented 프로그램의 차이 정리
▪ 클래스, 오브젝트, 메서드 정리
▪ UML 다이어그램 ( unified modeling language )
   통합 모델링 언어를 사용하여 시스템 상호작용, 업무흐름, 시스템 구조, 컴포넌스 관계 등을 그린 도면
   사용 이유 : 프로그래밍을 단순화시켜 표현하여 의사소통하기 좋고 또 대규모 프로젝트 구조의 로드맵을 
   만들거나 개발을 위한 시스템 구축에 기본을 마련한다.
▪ OOP의 특징
   - 상속, 다형성, 추상화, 이벤트 컨트롤
   - 클래스는 오브젝트에 대한 설명서, 틀
   - 클래스에서의 data는 attribute, behavior는 method라고 함.

Con1 - UNIT 2 : Fundamental Object-Oriented Syntax 

▪ Local Class 만들기 ( WC p.39 ) 
▪ Constructor ( WC p.83 )   -> 어디서 활용해서 쓸 수 있지?-> 실무에서 잘 안씀.
   - Call method로 호출할 수 없는 특별한 메서드
   - instance constructor 
     해당 클래스에서 하나만 존재, 클래스에서 오브젝트 생성될 때 자동 호출 (호출방법 1가지)
     public section에만 선언, importing과 exception만 가능
     call method로 호출 불가

     METHODS constructor IMPORITING ~~ 	
     예외적으로 호출해줘야되는 경우 : 상속관계에서 서브클래스에 constructor 가 있고 슈퍼클래스의 
     constructor가 있을 때 서브클래스에서 constructor를 호출해줘야함.

   - Static Constructor
     프로그램에 의해 클래스에 처음 접근할 때 자동적으로 호출, 한번만 호출되어짐.
     어떠한 파라미터도 가질 수 없음, public section에만 가능, call method로 호출 불가
     4가지 경우에 의해 자동 호출
     1. 해당 클래스에서 오브젝트 생성될 때
     2. Static Attribute에 Access할 때
     3. Static Method를 호출할 때
     4. 이벤트의 핸들러 Method 등록할 때

     Class-Methods class-constructor ~ 

     스태틱이랑 인스턴스랑 같이 만나면 스태틱이 우선 실행.

▪ Self-Reference ( WC p.96 )
   me->
   해당 메소드의 component와 변수이름이 같은 게 있을 때 attribute이름에 me->이름 접근한다.     

Con1 - UNIT 3 : Inheritance and Casting

▪ 상속 : 서브 클래스는 수퍼클래스의 데이터와 메서드를 얻을 수 있다. ( 재사용성 )
▪ ABAP Object에서는 Super Class(부모), Sub Class(자식)
▪ 서브클래스에서 상속받은 메소드를 수정가능. REDEFINITION. -> 다형성
▪ 상속관계에서 sub클래스에서 super클래스에 있는 constructor를 구현할 때 반드시 호출해야함.
▪ Upcast ( WC p.135 )
   자식클래스의 object를 부모클래스 참조변수에 할당
   부모 = 자식
   슈퍼 클래스 타입인 참조변수가 서브 클래스의 상속해준 component에 접근이 가능한 것.
   할당을 해줘야함. 우리가 한 예시에서는 테이블 자체가 부모클래스를 상속하고 있기 때문에
   오브젝트들이 들어갈 때 자동적으로 형변환이 일어난 것.
▪ Downcast ( WC p.155 )
   upcast되어진 부모참조변수를 자식클래스 참조변수에 할당
   자식 ?= 부모
   부모클래스 변수에서 자식클래스 변수에 할당하는 것. ( ?= )
   자식 메서드에서도 부모 메서드 사용 가능
   자손타입 -> 부모타입 ( up-casting ) : 형변환 생략가능 , 부모가 자식꺼 사용 
   자손타입 <- 부모타입 ( down-casting ) : 형변환 생략불가, 
▪ 다형성 ( WC p.147 )
   인터페이스와 상속은 다형성이라는 객체지향 프로그램의 특징을 구현하는 방식이다.
   ABAP 다형성은 메서드를 호출하면 메서드를 호출하는 개체의 유형에 따라 다른 메서드가 실행됨을
   의미한다.
   하나의 메서드를 가지고 display_attribute를 가지고 max seats, cargo 이런거 추가해서
   다른 내용으로 display 하는 것.




Con1 - UNIT 4 : interfaces and Casting
▪ 인터페이스 ( WC p.174 )
   여러 클래스들의 성격이 다 달라서 super class로 뽑아낼 수 없을 때 사용

   공통적인 component가 존재할 때 인터페이스를 가지고 컴포넌트를 정의하고
   정의된 컴포넌트를 클래스들이 상속받아서 각각 클래스에 맞게 구현해서 사용

   정의만 있고 구현은 없다. (사용하는 클래스에서 구현)
   정의할 때는 INTERFACE, 구현할 때는 INTERFACES
   클래스에서 여러 개의 인터페이스 상속이 가능하다
   인터페이스에 있는 모든 component는 public이다.
   프로토콜이라고도 함.
   추상메서드의 모음(정의만)
  
   클래스 definition에서 private에 속성을 만들어놔도 인터페이스를 만들어서   
   implementation에서 메서드를 만들면 private에 있는 값도 가져올 수 있다.
   은닉하고있던걸 보여주니깐 때에 따라서는 위험할 수 있다. (보안)
   
   
Con1 - UNIT 5 : Object-Oriented Events	
Con1 - UNIT 6 : Object-Oriented Repository Objects

▪ Global Class -> 실무에서 많이 사용함.
▪ tcode : SE24 ( class builder )
  se80 -> package -> create -> class library -> class

Con1 - UNIT 7 : ABAP Object-Oriented Examples
Con1 - UNIT 8 : Class-Based Exceptions

▪ 내용적으로만 알고 있으면 된다.

Con1 - UNIT 9 : Object-Oriented Design Patterns

▪ CASE TYPE OF ... WHEN TYPE ... INTO ( WC2 P.406 )

Con1 - UNIT 10 : Program Calls and Memory Management
Con1 - UNIT 11 : Dynamic Programming
▪ Generic Data Type
   제네릭은 클래스나 메소드에서 사용할 내부 데이터 타입을 컴파일 시에 미리 지정하는 방법

▪ Field Symbol ( WC2 p.483 )
   ABAP 프로그램 내에서 data object에 동적인 접근이 가능하게한다.
   C언어의 포인터 개념과 유사, 주소값
   FIELDS-SYMBOLS : <fs_tab> TYPE ANY TABLE.
   필드심볼 이름은 꺽쇠안에 넣어야함.

   ASSIGN gt_scarr TO <fs_tab> " 필드심볼로 테이블 포인트
   IF <fs_tab> IS ASSIGNED.   -> IS NOT ASSIGNED, IS BOUND, IS NOT BOUND

▪ 클래스에서 object 생성하는 법 2가지
   1. CREATE OBJECT
   2. NEW # (   ) 

▪ Create Data
   불특정한 data object 생성할 경우 사용

part2는 SAP에서 제공해주는 Standard 솔루션을 수정하는 내용
standard 수정하는 방법 2가지 : Enhancement(끼워넣기), Modification(original 수정)
Con2 - UNIT 1 : Adjustment of SAP Standard Software

▪ Standard 수정하는 법 ( WC3 p.3 )
   1. Personalization : 
   2. Modification : Standard 수정
   3. Enhancement : Standard에 Customer가 원하는 로직을 끼워넣는 것
      1) abap dictionary (z, y 말고 standard)
      2) program enhancements (standard program에 로직을 추가한다던지)
      3) menu enhancements (menu bar에 menu를 추가한다던지)
      4) screen enhancements (screen에다가 원하는 필드를 추가한다던지)

▪ Table Enhancement ( WC3 p.9 ) ( WC3 p.23 )
   1. standard 테이블에 APPEND STRUCTURE을 이용해서 필드 추가 (ZZ##, YY##)
     모든 테이블에 가능
     하나의 append structure는 하나의 table에만 사용 가능
     하나의 table에는 여러개의 append structure가 올 수 있음.
   2. standard 테이블에 CI_Include 추가 (ZZ##, YY##)
     테이블에 data element가 CI_## 로 시작하는 structure가 있을 때만 사용 가능  ( .include )

▪ Program Enhancement
   Program Exit이라고 한다.?
   1. User Exit 2. Customer Exit 3. Business Transaction Event 4. Business Add-in

▪ Menu Enhancements
   cutomer function code는 function code 앞에 +를 붙어있다. 
   PAI에 program branch로 프로그램 호출하는 부분이 있다. 
   여기에 function module이나 method로 구현할 수 있다.

▪ Enhancement Options ( WC3 p.17 )
   1. 스탠다드 function module과 method에 interface parameter 추가가능
   2. 스탠다드 class 에 attributes와 methods 추가가능
   3. 스탠다드 method 전, 후에 method를 추가가능 (pre-method, post-method)
   4. 스탠다드 method를 대체할 수 있는 method를 사용할 수 있는 기능도 이용가능

Con2 - UNIT 2 : Enhancement of Dictionary Elements 

▪ Standard table 필드 추가는 append structure과 include로 한다 이것만 알고 있으면 됨.
▪ tcode : COMD  

Con2 - UNIT 3 : Customer Exits

▪ Customer Exit 3가지 기능, Enhancement Project를 생성해야함.
   1. Program Exit	
      cmod -> enhancement 찾고 -> exit_module -> include -> add custom source code -> activate
   2. Menu Exit 
      cmod -> enhancement 찾고 -> exit_module -> menu exit에서 function text 입력
      Function exit에서 custom logic source 추가  -> activate
   3. Screen Exit
      sflight19에서 append struture 필드추가 -> cmod -> enhancement 찾고 
      -> screen exit screen 500 생성 -> layout에 필드 추가 -> function exit에서 include 
      zxbc425g19top 생성해서 freeseats 변수 생성 -> screen 500에서 필드 추가
      -> include 003에서 freeseats 구문 생성 -> activate

     SAP standard 프로그램에 subscreen area 생성하고 Call subscreen으로 어느 function group에
     설정할지와 몇 번 스크린인지 입력하고 subscreen에서 값을 읽어가기 위해 function module을 사용



Con2 - UNIT 4 : Classic Business Add-Ins

▪ BAdi : SE19
▪ Classic BAdi
   1. Program Exit
      sapbc425_booking_19에서 search로 cl_exithandler -> 참조변수 -> 클래스 -> where used
      -> BAdi 찾고 -> SE19에서 바디 만들고 -> implementation 이름 설정 -> utput에서 넣고 싶은 
      필드 변수선언, 데이터가져오기, write문으로 쓰고 -> change vline에서 vertical 라인 + 해주고 
   2. Menu Exit
   3. Screen Exit

▪ New BAdi
   classic 보다 퍼포먼스 좋음. 
   1. Program Exit
     
Con2 - UNIT 5 : New Business Add-Ins
Con2 - UNIT 6 : Explicit Enhancement Options
▪ Points, Sections
▪ new badi와 explicit는 enhancement spot에 의해 관리된다.

Con2 - UNIT 7 : Implicit Enhancement
Con2 - UNIT 8 : Modifications of the SAP Standard Application
Con2 - UNIT 9 : Introduction of Web Dynpro

▪ Context Mapping, Data binding
   1. Component Controller 생성, node, attribute 생성
   2. View Controller 에 drag ( Context Mapping )
   3. View -> layout -> rootuielementcontainer -> insert element -> cap_01/caption text 입력
   4. application 생성 program -> create -> web dynpro application -> url로 web에서 실행
   5. attribute  한번에 담기 insert element -> inp_1 / infut_field -> property : value -> binding
      -> flight nodes 선택 

▪ Navigation Between Views ( Inbound and Outbound Plugs )
   1. insert element -> btn_next/button -> outbound plugs ( to_result ) , inbound plugs ( from_result )       -> layout btn_next 의 events의 onAction 에 Action에 'next' outbound plug 'to_result'
   2. views 에 result_view 하나 더 만들고 btn_first/button 생성
     -> outbound plugs ( to_main ) , inbound plugs ( from_main )
     -> layout -> events - onAction -> action 'BACK' -> actions의 BACK 더블클릭 메소드 
     -> web dynpro code wizard (ctrl+F7) -> general tab -> start navigation - outbound 'to_main'
   3. navtigation link -> windows에서 main_win에서 result_view를 main_win에 drag&drop
     -> main_view의 to_result에 create navigation link를 result_view로 넣어주고
        result_view의 to_main에 create navigation link를 main_view로 넣어준다.
   
     inbound plug 생성하면 자동적으로 event handler가 만들어진다.

Con2 - UNIT 10 : Web Dynpro Controllers
Con2 - UNIT 11 : Web Dynpro Context

▪ MatrixLayout + display button 클릭시 get_data 
   1. COMPONENTCONTROLLER에서 context 추가 nodes : flights, subnode : bookings
      bookings의 attribute : carrid connid fldate price currency planetype seatsmax seatsecc
   2. MAIN_VIEW에서 create container form, layout은 matrixLayout 
     attribute 2개당 하나씩 layoutData에 MatrixHeadData 입력 해서 열 맞춤
   3. button 추가, onAction : SEARCH
   4. COMPONENTCONTROLLER의 메소드에 get_data 추가 
      Web Dynpro Code Wizard 	-> context -> flight -> Read

      SELECT SINGLE * c        INTO CORRESPONDING FIELDS OF LS_FLIGHTS        FROM SFLIGHT        WHERE CARRID = LS_FLIGHTS-CARRID          AND CONNID = LS_FLIGHTS-CONNID          AND FLDATE = LS_FLIGHTS-FLDATE.

      Web Dynpro Code Wizard 	-> context -> flight -> Set
      이 부분만 남기기      * set all declared attributes        LO_EL_FLIGHTS->SET_STATIC_ATTRIBUTES(        STATIC_ATTRIBUTES = LS_FLIGHTS ).

    5. MAIN_VIEW의 Actions에 SEARCH 메소드 
       WD_COMP_CONTROLLER->GET_DATA( ). 추가

    6. MAIN_VIEW의 메소드 WDDINIT
      Web Dynpro Code Wizard 	-> context -> flight -> Set

      LS_FLIGHTS-CARRID = 'AA'.      LS_FLIGHTS-CONNID = '0017'.      LS_FLIGHTS-FLDATE = '20210114'.      * set all declared attributes 이거 위에 입력
      초기값으로 뜨게 설정
Con2 - UNIT 12 : Web Dynpro User Interface 
▪ FlowLayout  ( WC3 p.345 )
   field들이 한 라인에 나오다가 창 사이즈 줄이면 field들이 자동적으로 내려감.
   znet310_clc19_layout	
   1. componentcontroller -> node 추가 'ns_flight' -> attribute -> sflight의 carrid connid fldate price
   2. main_view에서 context mapping
   3. layout 에서 insert element ID: flowlayout / TYPE: transparent container
   4. flowlayout -> 우측클릭 -> create containerform -> context 'ns_flight'
   5. create application

▪ RowLayout  
   1. insert element -> id: rowlayout / type : group (head text 입력가능)
   2. create container form -> context 'flight' -> layout of new container : rowdata 확인 
      layoutdata를 rowheaddata로 바꾸면 줄이 내려옴
   
▪ MatrixLayout ( 제일 많이 사용 )
   1. insert element -> id: matrixlayout / type : tray (접었다 폈다 할 수 있음)
   2. create container form -> context 'flight' -> layout of new container : matrixdata 확인 
      matrixheaddata하면 위로 올라옴.

   matrix는 caption의 길이에 따라 왔다갔다 하지 않고 정해진 행렬에 따라 정렬

▪ GridLayout ( 제일 많이 사용 )
   한 라인에 몇 개의 컬럼을 사용할 건지 정해줘야함.
   1. insert element -> id: gridlayout / type : panel ( 이것도 접었다 폈다 가능 )
     property : layout을 gridlayout으로 변경, colCount : 4 (한 라인에 ui element가 네 개씩 배치)
   2. create container form -> context 'flight' -> layout of new container : matrixdata 확인 
      matrixheaddata하면 위로 올라옴.   

   컨테이너는 Group, Transparent Contrainer를 많이 사용

▪ FormLayout, TitleLayout ( 행렬 구분 가능 ) 	

▪table
  znet310_clc19_table
  1. COMPONENTCONTROLLER -> node 'ns_cond' -> spfli carrid connid
    node 'nt_data' cardinality : 0..n -> sflight carrid connid fldate seatsmax seatsocc
  2. MAIN_VIEW -> context -> ns_cond nt_data mapping
  3. layout -> insert element -> id : cond , type : group -> property : gridlayout, colCount : 4
    cond -> create container form -> context : ns_cond 
  4. insert element -> id : search / type : button , action : display
  5. insert element -> id : result / type : transparent container
    result container 안에 insert element -> id : tab_data / type : table
    tab_data -> 우측클릭 -> create binding -> context 'nt_data' 
  6. COMPONENETCONTROLLER  -> methods tab page -> get_data_flight 생성 -> 메소드 구현
     Web Dynpro Code Wizard 	-> context -> ns_cond -> Read
     Web Dynpro Code Wizard 	-> context -> nt_data -> Set , as table operation

     ** @TODO compute values     ** e.g. call a model function     *  
     이 주석 위에
     SELECT *       INTO CORRESPONDING FIELDS OF TABLE LT_NT_DATA       FROM SFLIGHT       WHERE CARRID = LS_NS_COND-CARRID         AND CONNID = LS_NS_COND-CONNID.
   7. MAIN_VIEW -> action 'DISPLAY' method -> 
      WD_COMP_CONTROLLER->GET_DATA_FLIGHT( ). 추가
   8. Create Web Dynpro Application 
     
   tab_data 의 visibleRowCount로 스크롤 하기전의 최대 dipslay 개수

   web dynpro에서의 Nodes : 변수이름 / Elements : 변수에 저장되어진 data, record
   0..n 1..n 은 internal table 로 생각

Con2 - UNIT 13 : Controller and Context Programming

▪ Supply Functions
   znet310_clc19_supply
   1. COMPONETCONTROLLER -> node name : nt_scarr, cardinality : 0..n
     attribute : scarr / carrid carrname currcode
     nt_scarr에 우측클릭 create node, name : nt_spfli, cardinality : 0..n, singleton : Yes, 
     supply function : get_spfli 생성
     attribute : spfli / carrid connid countryfr cityfrom coutryto cityto airpto
   2. method tab 페이지 보면 get_spfli의 type이 supply function으로 되있음.
      get_spfli 들어가서
      
      DATA LS_PARENT_ATTRIBUTES TYPE WD_THIS->ELEMENT_NT_SCARR.      PARENT_ELEMENT->GET_STATIC_ATTRIBUTES(        IMPORTING          STATIC_ATTRIBUTES = LS_PARENT_ATTRIBUTES ).

       DATA LT_NT_SPFLI TYPE WD_THIS->ELEMENTS_NT_SPFLI.

       SELECT *         INTO CORRESPONDING FIELDS OF TABLE LT_NT_SPFLI         FROM SPFLI         WHERE CARRID = LS_PARENT_ATTRIBUTES-CARRID.     ** bind all the elements         NODE->BIND_TABLE(         NEW_ITEMS            =  LT_NT_SPFLI         SET_INITIAL_ELEMENTS = ABAP_TRUE ).

  3. WDDOINIT에서 web dynpro wizard -> context 'scarr' -> set, as table operation

     SELECT *       INTO CORRESPONDING FIELDS OF TABLE LT_NT_SCARR       FROM SCARR.    ** @TODO compute values    ** e.g. call a model function    *
  4. MAIN_VIEW에서 Context tab 페이지에서 mapping
  5. layout에서 insert element -> id : tab_scarr / type : table
     create binding -> context 'nt_scarr'
  6. root에 insert element -> id : tab_spfli / type : table
     create binding -> context 'nt_spfli'
  7. create application 

--------