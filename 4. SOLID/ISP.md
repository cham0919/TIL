# ISP : 인터페이스 분리 원칙


 
ISP에 대한 개념은 다음과 같다.
 
 > 클라이언트가 자신이 이용하지 않는 메서드에 의존하면 안된다
 
 즉, 하나의 책임만을 가지는 작은 규모의 인터페이스로 분리해야한다.
  
  
 <br/><br/>
 
# 배포
 
 
<br/>
 <p align="center">
    <img src="https://images.velog.io/images/cham/post/29f8cd46-0f61-4504-a938-db131ecccfef/image.png" width="80%" />
 </p>
 <br/>
 
 다수의 사용자가 OPS 클래스의 오퍼레이션을 사용한다. User1은 op1, User2는 op2, User3는 op3만을 사용한다 가정하자.


이 경우 User1은 op2, op3를 사용하지 않음에도 User1의 소스 코드는 이 두 메서드에 의존하게 된다. 때문에 op2가 변경이 된다면 User1도 컴파일한 후 재배포해야한다. 

 이는 다음과 같이 해결할 수 있다.
 
 

 <br/>
 <p align="center">
    <img src="https://images.velog.io/images/cham/post/b4841433-f6a4-475a-bb33-6295c297744e/image.png" width="80%" />
 </p>
 <br/>
 

 User1은 U1Ops와 op1에 의존하지만 OPS에는 의존하지 않는다. 따라서 OPS에 대한 변경사항이 User1과는 관계없게 되고 User1을 다시 컴파일하고 배포하는 상황은 발생하지 않는다.





<br/><br/>
 
 


 
# 결론



불필요한 짐을 실은 무언가에 의존하면 예상치 못한 문제에 빠질 수 있다.
 
 <br/><br/><br/> <br/><br/><br/>
 


참조

---

 - 클린 아키텍처(소프트웨어 구조와 설계의 원칙) -  로버트 C. 마틴
 - [[클린아키텍처] 3부. 설계원칙 ISP](https://32kb.tistory.com/38?category=1186843)
 
 
 