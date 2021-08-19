# Backing service

Outer Architecture 중 Backing service를 정리한다

Backing Service란, 어플리케이션이 실행되는 가운데 네트워크를 통해서 사용할 수 있는 모든 서비스를 말하며 MySQL과 같은 데이터베이스, 캐쉬 시스템, SMTP 서비스 등 어플리케이션과 통신하는 attached Resource들을 지칭하는 포괄적인 개념이다.
 
<br/>

# 특징

마이크로 서비스에서의 Backing service는 메세지큐를 활용한 비동기 통신 패턴을 많이 사용한다

현대 MSA의 특징 중 하나는 하나의 Micro Service에 이벤트(장애 발생, 트래픽 증가, 소스 반영 등)가 발생할 경우, Micro Service 오케스트레이션이 진행되며, 마이크로서비스의 신규 생성, 재생성, 서비스 인스턴스의 삭제 등의 작업이 빈번하게 이루어진다는 것이다.

![image](https://user-images.githubusercontent.com/61372486/130054974-04cad859-743d-41f0-a6e1-59de39949cc6.png)

 그림과 같이 하나의 비즈니스 프로세스를 수행하기 위해 여러 마이크로 서비스간 통신이 필요한 경우를 생각해보자.
 
 하나의 트랜잭션에서 여러 마이크로 서비스들이 강하게 결합되어 처리되는 방식의 경우, Orchestration이 진행되면서 진행 중인 트랜잭션이 끊어지게 되고, 해당 서비스 요청을 보존할 수 없게 된다.
 
MSA에서는 Message queue를 활용하여 트랜잭션을 분리하는 전략을 많이 취한다. 

특히 Message queue를 사용할 경우 서비스의 영속성( 서비스에 장애가 발생하더라도 event message가 consumer에 전달되는 것이 보장됨 )을 유지할 수 있다는 장점이 있다.



<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [MSA 제대로 이해하기-(5)Backing Service](https://velog.io/@tedigom/MSA-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-5Backing-Service-lqk3b7560w)
          
 
 
