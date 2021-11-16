*FUNCTION
**서브루틴과 비슷하게 기능별로 모듈화, 재사용 가능하게 지원
*서브루틴은 로컬 모듈화면, FUNCTION은 글로벌 모듈화

*FUNCTION과 Subroutine 차이
**Function Module은 Function Group라고 불리는 POOL에 소속되어야 함
**Function Module은 예외 처리 기능을 제공해 에러 발생하면 예외 사항을 호출한 프로그램에 전달 가능
**Function Module은 호출 프로그램 상관없이 Stand-alone 모드에서 테스트 가능



*Function Module
** 중앙 라이브러리에 저장되는 특별한 글로벌 서브 루틴.

*** Import Parameters - Function Module에 전달하는 값이며 선택 사항
*** Export Parameters - Function Module로부터 ABAP 프로그램에 전달받는 값이며 선택 사항.
*** Changing Parameters - Function Module에 값을 넘기고, 그 값을 바꿀 수 있다
*** Tables - 인터널 테이블을 Function Module에 전달하고 받을 수 있다
*** Exceptions - 에러에 대한 정보를 제공한다


*Function Group
** 개발 패키지처럼 유사 기능의 Function 등을 모아놓은 컨테이너를 의미
** 1. Function 호출
** 2. 시스템은 호출한 프로그램의 Internal 세션 안으로 Function Group 전체를 Load한다
** 3. 이는 Function Group 내에서 데이터를 공유하고, 스크린을 생성해 호출, perform 서브루틴 등 공유할 수 있게 한다

** 주의
*** Function이 실행될 때 Function이 소속된 그룹 내 모든 Function이 영향을 받는다
*** 하나에 에러 발생하면 동일 그룹 내 모든 Function 작동 멈춘다.

** Function Group 이름은 최대 26자까지 가능
** Function Builder 통해 Function Group 생성하면 시스템은 자동으로 Main Program과 Include Program 생성
** Main Program 앞에 'SAPL'이 붙는다.
** SAPL 프로그램은 다음 Include 프로그램을 포함한다
***** L<fgrp>TOP .- FUNCTION-POOL 구문을 포함. FUNCTION GROUP 전체에 사용할 수 있는 전역 변수를 선언
***** L<fgrp>UXX. - Include 프로그램 L<fgrp>U01, L<fgrp>U02와 같이 기술. 실제 Function Module을 포함

