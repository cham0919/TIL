# Service Mesh

Outer Architecture 중 Service Mesh를 정리한다.

Service Mesh는 쉽게말해 마이크로 서비스 간의 통신(네트워크)을 담당하는 요소다.

마이크로 서비스 구성 요소간 상호 통신을 위해서는 Service Discovery, 서비스 라우팅, Failure recovery, load balancing(트래픽 관리), 보안 등의 문제를 처리할 수 있는 메커니즘이 필요하다.

Service Mesh는 통신 및 네트워크 기능을 비즈니스 로직과 분리한 네트워크 통신 인프라를 말한다.

모든 서비스의 인프라 레이어로서 서비스들 간의 통신을 처리하며, 위의 언급된 많은 기능들을 포함하고 있다.

![image](https://user-images.githubusercontent.com/61372486/130053750-dd436d84-aeb6-4022-903f-c84aa4b4e4cd.png)


<br/>

# API Gateway와 Service Mesh 차이


||API Gateway|Service Mesh|
|---|---|---|
|라우팅 주체|서버|요청하는 서비스|
|라우팅 구성요소|별도의 네트워크를 도입하는 독립적인 API gateway 구성요소|서비스 내 sidecar로 Local network 스택의 일부가 됨|
|로드 밸런싱|단일 엔드포인트를 제공하고, API Gateway 내 로드밸런싱을 담당하는 구성요소에 요청을 redirection하여 해당 구성요소가 처리함|Service Registry에서 서비스 목록을 수신함. sidecar에서 로드밸런싱 알고리즘을 통해 수행함|
|네트워크|외부 인터넷과 내부 서비스 네트워크 사이에 위치함|내부 서비스 네트워크 사이에 위치하며, 응용프로그램의 네트워크 경계 내에서만 통신을 가능하게 함|
|분석|API에 대한 사용자 및 공급자에 대한 모든 호출에 대해 수집되고 분석됨|Mesh 내 모든 마이크로서비스 구성요소에 대해 분석가능|



<br/>

최근 MSA에서 API Gateway는 노출되는 부분(External)에 위치하여 내부서비스를 보호 및 제어하는 역할을 하고, Service Mesh는 내부 서비스(Internal)에 위치하여 서비스를 관리하는 구조로 많이 사용되고 있다.

<br/>

# 종류

Service mesh는 현재 크게 세가지 유형으로 구분할 수 있습니다.

## PaaS (Platform as a Service)의 일부로 서비스 코드에 포함되는 유형

Microsoft Azure Service fabric, lagom, SENECA 등이 이 유형에 해당되며, 프레임워크 기반의 프로그래밍 모델이기 때문에, 서비스메쉬를 구현하는데에 특화된 코드가 필요하다. ( Mesh-native Code )

<br/>

## 라이브러리로 구현되어 API 호출을 통해 Service mesh에 결합되는 유형

Spring Cloud, Netflix OSS(Ribbon/Hystrix/Eureka/Archaius), finagle 등이 이 유형에 해당되며, 프레임워크 라이브러리를 사용하는 형태다.
 
서비스 메시를 이해하고 코드를 작성해야한다. (Mesh Aware Code)

<br/>

##  Side car proxy를 이용하여 Service mesh를 마이크로서비스에 주입하는 유형

Istio/Envoy, Consul, Linkerd 등이 이 유형에 해당되며, sidecar proxy 형태로 동작된다.
 
따라서 서비스메시와 무관하게 코드를 작성할 수 있다.

<br/>

# 주요 기능 

- Service Discovery
- Load balancing (지연시간 기반 / 대기열 기반 )
- Dynamic Request Routing
- Circuit Breaking
- 암호화 (TLS)
- 보안
- Health check, Retry and Timeout
- Metric 수집

<br/>

# Sidecar pattern

![image](https://user-images.githubusercontent.com/61372486/130054453-4945eb9c-c70a-49c8-b454-5626bfe807f3.png)

Sidecar pattern은 (컨테이너 배포방식의 경우) 모든 응용 프로그램 컨테이너에 추가로 sidecar 컨테이너가 배포된다.
 
Sidecar는 서비스에 들어오거나 나가는 모든 네트워크 트래픽을 처리하게 된다.

가장 큰 특징은, 비즈니스 로직이 포함된 실제 서비스와 sidecar이 병렬로 구성되어있기 때문에, 서비스 호출에서 서비스가 직접 서비스를 호출하는 것이 아니라 proxy 를 통해서 호출하게된다.

따라서, 대규모의 마이크로서비스 환경이라고 하여도 개발자가 별도의 작업 없이 서비스의 연결 뿐만 아니라, 로깅, 모니터링, 보안, 트래픽 제어와 같은 다양한 이점을 누릴 수 있다.






<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [MSA 제대로 이해하기 -(4)Service Mesh](https://velog.io/@tedigom/MSA-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-4Service-Mesh-f8k317qn1b)
          
 
 
