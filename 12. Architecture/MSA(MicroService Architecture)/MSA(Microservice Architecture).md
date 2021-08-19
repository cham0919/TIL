# MSA(MicroService Architecture)

- 하나의 큰 어플리케이션을 여러개의 작은 어플리케이션으로 쪼개어 변경과 조합이 가능하도록 만든 아키텍쳐

애플리케이션 개발 초기에는 전체 애플리케이션의 소스 코드를 하나의 배포 유닛(war 또는 ear)으로 내장시키는 '모놀리식' 방식을 이용했지만 기존 애플리케이션에 최소한의 변경 사항이 있어도 대규모 업데이트를 해야 하거나 일부 애플리케이션의 업데이트로 오류가 발생한 경우 전체를 오프라인으로 전환하고 문제를 해결하는 등 다운타임이 발생하게 되었다.

> 서비스 복잡도가 증가하면서 모놀리틱 아키텍처가 가지는 문제점
>1) 배포 시간의 증가
>2) 부분적 스케일 아웃의 어려움
>3) 안정성의 감소
>4) 프로그래밍 언어, 프레임워크 변경의 어려움

이러한 문제점을 해결하기 위해 전통적인 모놀리식(monolithic) 접근 방식과 다르게 애플리케이션을 핵심 기능으로 세분화하여 하는 MicroService(MS)라는 아키텍처 기반의 접근 방식이 탄생하게 되었다. 


![image](https://user-images.githubusercontent.com/61372486/130049755-93871203-7143-4ae8-baf0-5c0b1300593b.png)


<br/>

# 특징

- 각각 자체 프로세스에서 실행, 대개 HTTP API와 통신
- 비즈니스 기능을 중심으로 구축되며 독립적으로 배포 가능
- 최소한의 중앙 집중식 관리
- 다른 프로그래밍 언어, 다른 데이터베이스 사용 가능

<br/>


# 장단점

- 장점
  - 분산형 개발을 통해 효율적인 개발 가능(출시 기간 단축)
  - 개별 서비스가 다른 서비스에 부정적인 영향을 주지 않으면서 작동할 수 있음(뛰어난 복구 능력)
  - 다른 서비스들과 유연하게 결합하며(언어의 제약 X) 향후 확장 및 새로운 기능 통합 등에 대비할 수 있음(높은 확장성)
  - 기존의 모놀리식에 비해 더욱 모듈화되었기 때문에 배포에 따른 우려 사항들이 적어짐(손쉬운 배포)
  - 개발자들이 각각의 서비스를 파악하고 개선하기에 용이해짐(편리한 액세스)

- 단점
  - 큰 프로젝트에는 많은 서비스들이 존재하므로, 모든 서비스를 모니터링 하는 오버헤드가 증가함
  - 서비스에서 다른 서비스를 호출하므로 서비스에 장애가 발생한 경우 경로 및 장애 추적이 힘들 수 있음
  - 서비스별로 로그가 생성되기 때문에 중앙 로그 모니터링은 존재하지 않음
  - 각 서비스는 API를 통해 통신하므로 네트워크 통신에 의한 오버헤드가 발생
    

<br/>

# 종류

MicroService Architecture는 크게 Inner Architecture와 Outer Architecture로 구분할 수 있다.

![image](https://user-images.githubusercontent.com/61372486/130050704-9b826991-bac0-483a-9fd4-88e2e9214d4c.png)

위 그림에서 남색 부분은 Inner Architecture의 영역이고, 회색 부분은 Outer Architecture 부분이다.

<br/>

## Inner architecture

![image](https://user-images.githubusercontent.com/61372486/130050813-f12c3547-9d68-4e9f-bfca-55e0b4a64512.png)


Inner architecture는 내부 서비스와 관련된 architecture다. 쉽게 말해, 내부의 서비스를 어떻게 잘 쪼개는지에 대한 설계다.

<br/>

### 고려사항

- 서비스 정의
  - 쇼핑몰에서 주문, 장바구니를 같은 서비스로 넣을 것인지, 다른 서비스로 분리할 것인지는 그 비즈니스나 시스템의 특성에 따라 정의되어야 한다.
  - 서비스를 정의하기 위해 고려해야 할 사항은 비즈니스, 서비스 간의 종속성, 배포 용이성, 장애 대응, 운영 효율성 등이 있다.
  
- DB Access 구조 설계
  - Microservice가 사용하는 데이터는 일반적으로 일관된 API를 통해서 접근한다.
  - 또한 각 서비스에는 자체의 데이터베이스를 가질 수 있는데, 일부의 비즈니스 트랜잭션은 여러 서비스를 걸쳐 있기 때문에, 각 서비스에 연결된 데이터베이스의 정합성을 보장해 줄 수 있는 방안이 필요하다.

- 서비스 내 api 설계
- 논리적인 컴포넌트들의 layer 설계 방식

<br/>


## Outer architecture

위의 그림에서는 MSA의 Outer architecture을 총 6개의 영역으로 분류하고 있다.

- External Gateway
- Service Mesh
- Container Management
- Backing Services
- Telemetry
- CI/CD Automation

<br/>

### External Gateway

External Gateway는 전체 서비스 외부로부터 들어오는 접근을 내부 구조를 드러내지 않고 처리하기 위한 요소를 말한다.
사용자 인증(Consumer Identity Provider)과 권한 정책관리(policy management)를 수행하며, API Gateway가 여기서 가장 핵심적인 역할을 담당한다.

![image](https://user-images.githubusercontent.com/61372486/130051501-192b9867-6aa7-4968-ba2f-fcf67d4983b2.png)

API Gateway는 서버 최앞단에 위치하여 모든 API 호출을 받는다. 받은 API 호출을 인증한 후, 적절한 서비스들에 메세지를 전달될 수 있도록 한다.



<br/>

### Service Mesh

![image](https://user-images.githubusercontent.com/61372486/130051588-4fbd1d6b-5857-4e54-8d1d-a8f29a9d97b5.png)

Service Mesh는 마이크로서비스 구성 요소간의 네트워크를 제어하는 역할을 한다.

서비스 간에 통신을 하기 위해서는 service discovery, service routing, 트래픽 관리 및 보안 등을 담당하는 요소가 있어야 한다.

Service Mesh는 위에 언급된 기능들을 모두 수행한다.

<br/>

### Container Management

![image](https://user-images.githubusercontent.com/61372486/130051708-ca49bd6b-b757-4ce5-846a-163ad98237c7.png)

컨테이너 기반 어플리케이션 운영은 유연성과 자율성을 가지며, 개발자가 손쉽게 접근 및 운영할 수 있는 인프라 관리 기술이기 때문에 MSA에 적합하다고 평가받고 있다.

대표적인 컨테이너 관리 환경인 Kubernetes가 Container management에 많이 사용되고 있다.


<br/>

### Backing Service

![image](https://user-images.githubusercontent.com/61372486/130051830-90c16ffe-6fb9-4358-98e5-408a1b0edc61.png)

Backing Service는 어플리케이션이 실행되는 가운데 네트워크를 통해서 사용할 수 있는 모든 서비스를 말하며, My SQL과 같은 데이터베이스, 캐쉬 시스템, SMTP 서비스 등 어플리케이션과 통신하는 attached Resource들을 지칭하는 포괄적인 개념이다.

MSA에서의 특징적인 Backing service들 중 하나는 Message queue다.
 
MSA에서는 메세지의 송신자와 수신자가 직접 통신하지 않고 Message Queue를 활용하여 비동기적으로 통신하는 것을 지향한다.

이를 통해 하나의 서비스가 죽어도 해당 서비스 요청을 보존할 수 있고, REST 통신으로 트랜잭션 실패에 대한 처리도 가능해진다.

<br/>

### Telemetry

MSA에서는 상당수의 마이크로서비스가 분산환경에서 운영되기 때문에 서비스들의 상태를 일일이 모니터링하고, 이슈에 대응 하는 것은 힘들고 오랜 시간이 걸린다.
 
Telemetry는 서비스들을 모니터링하고, 서비스별로 발생하는 이슈들에 대응할 수 있도록 환경을 구성하는 역할을 한다.

<br/>

### CI/CD Automation

CI/CD는 어플리케이션 개발 단계를 자동화하여, 어플리케이션을 보다 짧은 주기로 고객에게 제공하는 방법이다.

지속적인 통합(Continuous Integration), 지속적인 전달(Continuous Delivery), 지속적인 배포(Continuous Deployment)가 CI/CD의 기본 개념으로, 이를 자동화하는 것은 배포가 잦은 MSA 시스템에 꼭 필요한 요소 중 하나다.

 

<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [[Server] MSA(MicroService Architecture)란?](https://mangkyu.tistory.com/108)
- [[MSA]MSA란 무엇인가?](https://velog.io/@wjdtmdgml/MSAMSA%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80)
- [MSA 제대로 이해하기 -(2) 아키텍처 개요](https://velog.io/@tedigom/MSA-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-2-MSA-Outer-Architecure)
          
 
 



  