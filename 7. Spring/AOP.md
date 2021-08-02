# AOP(Aspect Oriented Programming)

AOP는 Aspect Oriented Programming의 약자로 관점 지향 프로그래밍이라고 불린다. 

AOP는 Application 또는 program내 여러 파트들이 공통으로 필요로하는 관심/기능을 하나의 클래스 단위(aspect)로 분리해서 캡슐화한다.

이 방법을 사용하여 코드의 재사용성과 유지보수성을 높일 수 있는 점을 가져간다.

<br/>


# AOP와 프록시 패턴

- [프록시 패턴 바로가기](../3.%20디자인%20패턴/프록시%20패턴(Proxy%20Pattern).md)

- 프록시 객체가 원래 객체를 감싸서 client의 요청을 처리하게 하는 패턴이다.

- 주로 접근을 제어하고 싶거나, 부가 기능을 추가하고 싶을 때 사용한다.

- AOP는 이 프록시 패턴을 기반으로 런타임시, 동적으로 프록시객체를 만들어준다.


<br/>

# AOP의 구성요소

- Join Point : 횡단관심 모듈이 삽입되어 동작할 위치

- Point Cut : Join Point에서 횡단 관심사항과 핵심 관심사항의 Weaving(결합) 지점

- Advice : Join Point에 삽입될 코드(실행 코드)

- Aspect : Point Cut에 Advice 작성 단위, Class와 같은 프로그램 단위

<br/><br/><br/><br/>


참조

---

- [[Spring] 프록시 패턴 & 스프링 AOP](https://velog.io/@max9106/Spring-%ED%94%84%EB%A1%9D%EC%8B%9C-AOP-xwk5zy57ee)
- [[Spring] AOP란?](https://velog.io/@max9106/Spring-AOP%EB%9E%80-93k5zjsm95)
- [AOP (Aspect Oriented Programming)](https://velog.io/@miscaminos/AOP)
- [AOP(Aspect Oriented Programming)](https://m.blog.naver.com/gnakon1/222047048693)


 