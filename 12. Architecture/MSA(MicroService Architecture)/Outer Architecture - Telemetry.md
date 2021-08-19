# Telemetry

Outer Architecture 중 Telemetry를 정리한다

MSA에서는 상당수의 마이크로서비스가 분산환경에서 운영되기 때문에 서비스들의 상태를 일일이 모니터링하고, 이슈에 대응 하는 것은 굉장히 힘들고 오랜 시간이 걸린다.

Telemetry는 서비스들을 모니터링하고, 서비스별로 발생하는 이슈들에 대응할 수 있도록 환경을 구성하는 역할을 한다.

Telemetry는 Grafana, Prometheus, EFK와 같이 오픈소스로 직접구현하는 방법, Datadog와 같은 상용 솔루션을 이용하는 방법, 그리고 AWS Cloud watch, GCP Stackdriver와 같이 public cloud의 SaaS를 이용하는 방법으로 구현할 수 있다.

<br/>

# 주요 기능

### Monitoring

Monitoring을 위해서는 metric 수집, logging, Tracing 영역에서의 데이터 수집 및 분석이 필요하다.

AWS의 Cloud watch, Azure의 Monitor, GCP의 Stackdriver가 Public cloud에서 Monitoring을 담당하는 요소이며, OSS로는 Prometheus등이 있다.

Monitoring은 각 대상에서 수집한 Metric을 통해 대상의 리소스 사용률이나, 트래픽 등을 대시보드로 볼 수 있게 해준다.

<br/>

### Logging

마이크로 서비스 아키텍쳐는 Monolithic 어플리케이션보다 실행하는 프로세스의 수가 훨씬 많기 때문에 장애가 발생시에 root cause를 파악하기 힘들다.

AWS에서는 Amazon Elastic Search 등이 Logging을 담당하는 요소이며, OSS로는 EFK(Elastic Search - FluentD - Kibana)가 대표적으로 있다.

Log정보들을 수집하고, Log Server(Aggregator)를 통해 취합된다. 최종적으로 Kibana와 같은 툴을 통해 시각화된 데이터를 관리자에게 보여진다.

![image](https://user-images.githubusercontent.com/61372486/130055926-cdc29107-6ef4-4177-84d6-29d670204858.png)

<br/>

### Tracing

Tracing은 마이크로 서비스 아키텍처에서 발생한 Event를 발생한 순서대로 나열하여 추적할 수 있게 해주는 기능이다.

AWS에서의 Amazon X-Ray 등이 Tracing을 담당하는 요소이며, OSS로는 Zipkin Jaeger등이 대표적이다.


<br/><br/><br/><br/><br/><br/><br/>

---
Reference

- [MSA 제대로 이해하기-(6)Telemetry](https://velog.io/@tedigom/MSA-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-6Telemetry)
          
 
 


 