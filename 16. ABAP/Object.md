# Object

<br/>

### Class

```
CLASS c1 DEFINITION. " class 구성 항목을 정의한다
        PUBLIC SECTION.
          METHODS meth.
ENDCLASS.


CLASS c1 IMPLEMENTATION. " 클래스 method 기능을 구현한다
        METHOD meth.
            WRITE / 'CALSS TEST'.
            ENDMETHOD.
  ENDCLASS.


  DATA : go_cref TYPE REF TO c1.

  START-OF-SELECTION.
      CREATE OBJECT go_cref.
      CALL METHOD    go_cref->meth.
```

<br/>


- 클래스 내 값을 받아오고 값을 전달하는 메서드 2개 선언 및 출력

```
CLASS c1 DEFINITION.
      PUBLIC SECTION.
        DATA : l_char TYPE char20.
        METHODS : set_data IMPORTING p_imp TYPE char20,
                          get_data EXPORTING p_exp TYPE char20.
ENDCLASS.

CLASS c1 IMPLEMENTATION.
      METHOD set_data.
        l_char = p_imp.
      ENDMETHOD.

      METHOD get_data.
          p_exp = l_char.
      ENDMETHOD.
ENDCLASS.

DATA: go_cref TYPE REF TO c1.
DATA: gv_data TYPE char20.

START-OF-SELECTION.

CREATE OBJECT go_cref.
CALL METHOD: go_cref->set_data
                      EXPORTING p_imp = 'Test Class Method'.

CALL METHOD: go_cref->get_data
                      IMPORTING p_exp = gv_data.

WRITE gv_data.


CLASS c1 DEFINITION.
     PROTECTED SECTION.
       DATA : l_num TYPE i.
  ENDCLASS.

CLASS c2 DEFINITION INHERITING FROM     C1.
    PUBLIC SECTION.
        METHODS : set_data IMPORTING p_imp TYPE i,
                           get_data EXPORTING p_exp TYPE i.
ENDCLASS.


  CLASS c2 IMPLEMENTATION.
      METHOD set_data.
        L_NUM = P_IMP.
    ENDMETHOD.

    METHOD get_data.
        P_EXP = L_NUM + 1.
     ENDMETHOD.
   ENDCLASS.

   DATA: go_cref TYPE REF TO c2.
   DATA: gv_data TYPE i.

   START-OF-SELECTION.

   CREATE OBJECT go_cref.
   CALL METHOD : go_cref->SET_DATA
                        EXPORTING P_IMP = '10'.

   CALL METHOD: go_cref->get_data
                        IMPORTING p_exp = gv_data.

   WRITE : 'GET_DATA Method : ', GV_DATA.
```

<br/><br/>

# 객체 참조

- Object Component 접근
- 일반 - cref->attr / static - class=>attr

<br/>

### 객체 참조 할당


```
*Class Declarations
CLASS CLS DEFINITION INHERITING FROM object.
    PUBLIC SECTION.
      METHODS: CREATE.
 ENDCLASS.

* CLASS IMPLEMENTATION
 CLASS CLS IMPLEMENTATION.
   METHOD CREATE.
 WRITE : / 'Object is created'.
  ENDMETHOD.
 ENDCLASS.

* Global DATA
 DATA : GO_OBJ1 TYPE REF TO CLS,
            GO_OBJ2 TYPE REF TO CLS.

* Classical Processing Blocks
 START-OF-SELECTION.
  CREATE OBJECT : go_obj1.
  go_obj2 = go_obj1.

  CALL METHOD: go_obj2->create.
```

<br/>

### CASTING(?)

- 서로 다른 클래스에서 파생된 객체를 참고하여 또 다른 객체 생성이 가능하다
- 자식클래스는 부모가 가진 속성과 메서드를 재정의하거나 추가할 수 있다


```
CLASS VEHICLE DEFINITION INHERITING FROM object.
  PUBLIC SECTION.
    METHODS: CREATE,
                   DRIVE.
    PROTECTED SECTION.
      DATA SPEED TYPE I VALUE '100'.
 ENDCLASS.

 CLASS PLANE DEFINITION INHERITING FROM VEHICLE.
   PUBLIC SECTION.
    METHODS: FLY.
   ENDCLASS.

 CLASS VEHICLE IMPLEMENTATION.
   METHOD CREATE.

     ENDMETHOD.
   METHOD DRIVE.
      SPEED = SPEED + 100.
      WRITE : 'Driving is possible, ',
      / 'Current Speed: ', SPEED.
   ENDMETHOD.
  ENDCLASS.

  CLASS PLANE IMPLEMENTATION.
    METHOD FLY.
      SPEED = SPEED + 1000.
    ENDMETHOD.
  ENDCLASS.

  DATA: CAR TYPE REF TO VEHICLE,
            AIR TYPE REF TO PLANE.

  START-OF-SELECTION.
  CREATE OBJECT: CAR.
*  AIR ?= CAR.
  MOVE CAR ?TO AIR.

  CALL METHOD: CAR->DRIVE.
*  CALL METHOD: AIR->FLY.
```


<br/>

### METHOD

- IMPORTING, EXPORTING, CHANGING, RETURNING를 이용해 파라미터 인터페이스 정의 가능

```
CLASS c1 DEFINITION.
  PUBLIC SECTION.
    DATA : gt_itab TYPE TABLE OF sflight,
               gs_str TYPE sflight.
    METHODS: get_data,
                     wri_data.
 ENDCLASS.


 CLASS c1 IMPLEMENTATION.
   METHOD: get_data.
     SELECT * INTO TABLE gt_itab
       FROM sflight
       UP TO 5 ROWS.
   ENDMETHOD.

   METHOD: wri_data.
     LOOP AT gt_itab INTO gs_str.
       WRITE :/ gs_str-carrid, gs_str-connid.
    ENDLOOP.
    ENDMETHOD.
   ENDCLASS.

   DATA go_oref TYPE REF TO c1.
   DATA gv_mth TYPE string.

   FIELD-SYMBOLS <fs> TYPE ANY.

   START-OF-SELECTION.
   CREATE OBJECT GO_OREF.
   GV_MTH = 'GET_DATA'.
   CALL METHOD GO_OREF->(gv_mth).

   gv_mth = 'WRI_DATA'.
   CALL METHOD GO_OREF->(gv_mth).
```

<br/>

### Event

- ABAP Object에서 이벤트를 트리거하고 핸들링한다는 것은 상속 관계에 있지 않은 자신의 클래스 메서드에서 다른 클래스의 메서드를 호출하여 이벤트를 발생시키는 것
- 이벤트를 등록하려면 다음 사항이 전제되어야 한다
    - 이벤트가 등록된 클래스가 존재해야함
    - 핸들러 메서드가 정의된 클래스가 정의되어야함
    - 메인 프로그램에서는 핸들러 메서드를 등록하는 부분이 존재햐아함

<br/>

- 볼링 동호회에 가입한 회원들 간 볼링 치러가는 이베트 발생시키기 위한 과정 예제

```
CLASS C1 DEFINITION.
  PUBLIC SECTION.
    EVENTS: BOWL. " 이벤트 등록

    METHODS GO_BOWL.
 ENDCLASS.

 CLASS C2 DEFINITION.
   PUBLIC SECTION.
    METHODS LETSGO FOR EVENT BOWL OF C1. " 핸들러 메서드 등록
  ENDCLASS.

  CLASS C1 IMPLEMENTATION.
    METHOD GO_BOWL.
      WRITE : 'WAIT A MINUTE~'.

      RAISE EVENT BOWL. " 이벤트 발생 - 트리거

    ENDMETHOD.
  ENDCLASS.

  CLASS C2 IMPLEMENTATION.

     METHOD LETSGO.
       WRITE / 'OK, LETS GO'.
     ENDMETHOD.
   ENDCLASS.

   DATA : KIM TYPE REF TO C1,
               LEE TYPE REF TO C2.
   START-OF-SELECTION.
   CREATE OBJECT: KIM, LEE.

   SET HANDLER LEE->LETSGO FOR KIM. " 이벤트 발생

   KIM->GO_BOWL( ). " 트리거
```


<br/>

-   이벤트 실행 쉽게 이해 위한 순서 나열 예제

```
   TABLES: sflight.

   SELECT-OPTIONS: s_carrid FOR sflIght-carrid. " s_carrid 선택 옵션 추가

   CLASS C1 DEFINITION.
     PUBLIC SECTION.
     TYPES: BEGIN OF t_sflight,
       carrid TYPE sflight-carrid,
       connid TYPE sflight-connid,
       fldate TYPE sflight-fldate,
     END OF t_sflight.

     DATA: gt_itab TYPE STANDARD TABLE OF t_sflight,
               gs_str TYPE t_sflight.

                EVENTS: e1. " 5. 이벤트 트리거. 클래스 C1에는 이벤트 E1이 등록되어 있고, 자신의 이벤트에 해당하는 핸들러 메서드들을 선언하고 있다.

     METHODS: get_data, display_data, no_data
     FOR EVENT e1 OF c1.
  ENDCLASS.

  CLASS c1 IMPLEMENTATION. "3. 메서드 실행. get_data 메서드를 실행한다. 데이터가 없으면 RAISE EVENT를, 있으면 DISPLAY_DATA 호출
    METHOD: get_data.
      SELECT carrid connid fldate FROM sflight
        INTO TABLE gt_itab
        WHERE carrid IN s_carrid.
        IF sy-subrc <> 0. " 4. 이벤트 호출. 적중한 데이터가 없으면 이벤트를 발생시켜 결과를 반환한다.
          RAISE EVENT e1.
        ELSE.
          CALL METHOD display_data.
        ENDIF.
       ENDMETHOD.

       METHOD: display_data.
         LOOP AT gt_itab INTO gs_str.
           WRITE : /10 gs_str-carrid.
           WRITE : 40 gs_str-connid.
           WRITE : 60 gs_str-fldate.
         ENDLOOP.
        ENDMETHOD.

        METHOD no_data.
          WRITE : /10 'There is no data.'.
        ENDMETHOD.
     ENDCLASS.

     DATA: go_obj TYPE REF TO c1.

     START-OF-SELECTION.
      CREATE OBJECT: go_obj.

      SET HANDLER go_obj->no_data FOR: go_obj. " 1. 핸들러 메서드 등록. obj는 자신의 메서드 no_data에 핸들러 메서드를 등록했다. 핸들러 메서드 중 no_data 메서드를 등록했다

      CALL METHOD go_obj->get_data. " 2. get_data 메서드는 이벤트 e1의 핸들러 메서드다
```
 
 

<br/><br/><br/><br/><br/><br/><br/><br/><br/>

------------------------------------------------
Reference

- Easy ABAP 2.0 - 김성준 
 
 