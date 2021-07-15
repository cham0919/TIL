# OCP : 개방-폐쇄 원칙


 
OCP에 대한 개념은 다음과 같다.
 
 > 소프트웨어 개체는 확장에 열려 있어야 하고, 변경에는 닫혀 있어야 한다.
 
 즉, 소프트웨어 개체의 행위는 확장할 수 있어야 하지만, 이때 개체를 변경해서는 안 된다. 
  
  
  
<br/>

  
  # 사고 실험
  
  
  
  재무재표를 웹 페이지로 보여주는 시스템이 있다 가정하자. 
  
  이 시스템은 재무재표 데이터를 기반으로 스크롤이나 데이터에 색을 적용해 표현한다.
  
  시스템을 운영하는 도중 추가 요구사항이 왔다.
  
  - 보고서 형태로 변환해서 흑백 프린터로 출력하는 기능을 추가해주세요
  
이 요구사항에 대해 SRP를 적용하면 다음과 같은 간단한 설계를 얻을 수 있다.
  
 
<br/>

 <p align="center">
    <img src="https://images.velog.io/images/cham/post/7286a72b-c3cd-40b7-acc1-5c3a0d97988f/image.png" width="80%"/>
 </p>
 
 
 
<br/>
 

보고서 생성이 두 개의 책임으로 분리되는 것을 볼 수 있다. 
이렇게 책임을 분리했다면, 두 책임 중 하나에서 변경이 발생하더라도 다른 하나는 변경되지 않도록 의존성도 확실히 조직화해야한다. 또 행위가 확장될 때 변경이 발생하지 않음을 보장해야한다. 

<br/>

요구사항을 각각의 4개 컴포넌트로 나누면 다음과 같다.


<br/>


 <p align="center">
    <img src="https://images.velog.io/images/cham/post/3302bbc6-3d67-4e12-912c-aff923d6dac2/image.png"/>
 </p>
 
<br/>

```<I>```는 인터페이스,  ```<DS>```는 데이터 구조다. 열린 화살표는 사용(Using)관계이며, 닫힌 화살표는 구현(implemet) 또는 상속(inheritance) 관계이다.

여기서 주목할 점은 모든 의존성은 소스 의존성을 나타낸다는 것이다. 
예를 들어 화살표가 A -> B로 되어있다면 A는 B를 호출하지만 B는 A를 전혀 호출하지 않는다. 

또한 이중선은 화살표와 오직 한 방향으로만 교차한다는 점도 주목해야한다. 이는 모든 컴포넌트 관계는 단방향으로 이루어진다는 것을 알 수 있다. 이 화살표들은 변경으로부터 보호하려는 컴포넌트를 향하도록 그려진다.


<br/>


 <p align="center">
    <img src="https://images.velog.io/images/cham/post/9aa5e709-1dca-4861-a63b-6bb6164e5c43/image.png"/>
 </p>
 
<br/>

즉, A 컴포넌트에서 발생한 변경으로부터 B 컴포넌트를 보호하려면 반드시 A 컴포넌트가 B 컴포넌트에 의존해야한다.

위에서는 ```Presenter```에서 발생한 변경으로부터 ```Controller```를 보호하고 있다. ```Interactor```는 모든 변경으로부터 보호하고자 한다. 즉, OCP를 가장 잘 준수할 수 있는 곳에 위치한다.

결국 이러한 보호의 계층 구조는 화살표의 방향에 의해 "수준(Level)"을 개념으로 생성되며, 위의 컴포넌트 방향 그림으로 보았을때 컴포넌트의 보호 계층 순위를 나누어 보면,

Interactor가 1순위.  Controller가 2순위. Presentor가 3순위. View가 4순위의 보호 수준을 이루게 된다.

이것이 바로 아키텍처 수준에서 OCP가 동작하는 방식으로서, 기능을 분리하고 분리된 기능을 컴포넌트의 계층구조로 조직화 시키는 것이다.

이를 통해, 저수준 컴포넌트에서 발생한 변경으로부터 고수준 컴포넌트를 보호할 수 있는 원칙이 세워지게 된다.



<br/><br/>
 
 
# 정보은닉
 
 
 
 ```FinancialReportRequester```인터페이스는  방향성 제어와는 다른 목적을 가진다. 이는 ```FinancialReportController```가 ```Interactor``` 내부에 대해 너무 많이 알지 못하도록 막기 위함이다.
 
  Controller에서 발생한 변경으로부터 Interactor를 보호하는 일의 우선순위가 가장 높지만, 반대로 Interactor에서 발생한 변경으로부터 Controller도 보호되기를 바라기 때문에 Interactor 내부를 은닉한다.
 
 
<br/><br/>

 
# 결론



OCP의 목표는 시스템을 확장하기 쉬운 동시에 변경으로 인해 시스템이 받는 영향을 최소화하는데 있다. 이를 위해 시스템을 컴포넌트 단위로 분리하고 저수준 컴포넌트에서 발생한 변경으로부터 고수준 컴포넌트를 보호할 수 있는 형태의 의존성 계층구조가 만들어지도록 해야 한다.
 
 <br/><br/><br/> <br/><br/><br/>
 


참조

---

 - 클린 아키텍처(소프트웨어 구조와 설계의 원칙) -  로버트 C. 마틴
 - [[클린아키텍처] 3부. 설계 원칙 SRP](https://32kb.tistory.com/35)
 - [8장 OCP : 개방-폐쇄 원칙](https://m.blog.naver.com/lovemycat/221919597881)
 
 
 