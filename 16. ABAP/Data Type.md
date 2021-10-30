* [DATA TYPE]

* 변수 타입 정의
* 선언하게 되면 해당 프로그램에서만 사용 가능.
* 변수명은 _ 기호 포함해 30자까지 가능


* TYPE 지정

DATA  gv_num      TYPE   i.

DATA: gv_num1     TYPE i,
          gv_num2     TYPE i.

*LIKE
*기존 생성한 Data Variable와 동일한 타입 변수 선언

DATA: gv_num3    LIKE gv_num.


*VALUE
*기본 Default 값 설정

DATA: gv_num4 TYPE i VALUE 123,
          gv_num5            VALUE 'X',
          gv_num6            VALUE IS INITIAL,
          gv_num7             LIKE gv_num4 VALUE 45.

*WRITE:  /  gv_num4, gv_num5,  gv_num6,  gv_num7.  " 123 X 45


*LENGTH n
*필드 길이 설정
*c, n, p, x 타입만 사용 가능

DATA: gv_num8 TYPE n LENGTH 2.

gv_num8  = 22222.

*WRITE: /  gv_num8.


*DECIMALS
*타입 p에만 사용 가능하며 1~14 사이 소수 자리 수를 설정

DATA: gv_num9 TYPE p DECIMALS 3.

gv_num9 = '1.222222'.

*WRITE: / gv_num9.


* 기본 8가지 타입
* d, f, i는 길이 정의 생략 가능
* c, n, x는 길이 정의해야함
*p는 DECIMALS 정의 안할 시, 소수 자리 인식 X

DATA: gv_num10                  TYPE i,                               " 숫자
       gv_num11                     TYPE f,                               " 소수
       gv_num12                     TYPE p        DECIMALS 2,    " 정확한 소수
       gv_num13                     TYPE c        LENGTH 5,       " 텍스트
       gv_num14                     TYPE d,                             " 날짜
       gv_num15                     TYPE n       LENGTH 5,        " 숫자 텍스트
       gv_num16                     TYPE t,                             " 시간
       gv_num17                     TYPE x      LENGTH 5.         " 헥사


gv_num10 = '1'. " 정수 타입 아니면 반올림 => 2
gv_num11 = '1.1'.
gv_num12 = '1.1'. " 프로그램 세팅 중 Fixed point arithmetic 체크 비활 시 정수로 표현
gv_num13 = 'AAA'.
gv_num14 = '20211027'.
gv_num15 = 1234.
gv_num16 = '092911'.
gv_num17 = 11.



WRITE:  / gv_num10,
  / gv_num11,
  / gv_num12,
  / gv_num13,
  / gv_num14,
  / gv_num15,
  / gv_num16,
  / gv_num17.






*타입 선언


*구조체 타입선언

TYPES: BEGIN OF t_ren,
  name(20)        TYPE c,
  country(15)      TYPE c,
  city(10)            TYPE c,
  END OF t_ren.


*  구조체 변수 선언
  DATA gs_people TYPE t_ren.

  GS_PEOPLE-CITY = '서울'.
  GS_PEOPLE-NAME = '참'.
  GS_PEOPLE-COUNTRY = '한국'.

*  WRITE: / GS_PEOPLE.



*NEST 구조체 타입 선언

TYPES: BEGIN OF t_info.
  INCLUDE TYPE T_REN as ren.
  TYPES: phone(10) TYPE c,
  END OF t_info.

  DATA gs_add_people TYPE T_INFO.

 GS_ADD_PEOPLE-COUNTRY = '한국'.
  GS_ADD_PEOPLE-CITY = '서울'.
  GS_ADD_PEOPLE-NAME = '참'.
  GS_ADD_PEOPLE-PHONE = '000'.

*  WRITE:/ GS_ADD_PEOPLE.


*  구조체 참조


  DATA: BEGIN OF gs_person.
  INCLUDE STRUCTURE GS_ADD_PEOPLE as p.
  DATA: mbti(10) TYPE c,
  END OF gs_person.

  WRITE: / gs_person.





* Table, View를 이용한 TYPE 선언
* DataBase, View를 참고해 구조체 및 인터널 테이블 선언 가능

DATA: gs_sflight TYPE sflight.

*Table 개별 필드 참고 선언

DATA: GV_CARRID TYPE SFLIGHT-CARRID.



*DATA TYPE을 이용한 TYPE 선언


*테이블 SFLIGHT의 S_CARR_ID를 참고하며 선언

DATA gs_carrid TYPE s_carr_id.
GS_CARRID = 'AA'.


* 테이블 STRUCTURE 참조하여 선언

DATA gs_flight TYPE SFLIGHT.
gs_flight-CARRID = '1'.


*여러 타입을 그룹으로 묶어 선언
TYPE-POOLS name.





-----


