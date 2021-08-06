# Cloud Computing

- 클라우드 컴퓨팅은 서로 다른 물리적인 위치에 존재하는 컴퓨터들의 리소스를 가상화 기술로 통합해 제공하는 기술을 말한다.

<br/>

## 가상화와 클라우드 컴퓨팅의 차이

가상화는 기술 / 클라우드는 방법론

<br/>

#### 가상화

- VMware나 Virtualbox와 같이 단일한 물리 하드웨어 시스템에서 여러 환경이나 자원을 생성할 수 있는 기술이다
- 하이퍼바이저 라고 불리는 소프트웨어가 하드웨어에 직접 연결되며 가상 머신을 만들 수 있다.
- 가상 머신은 하이퍼바이저의 자원을 적절하게 배분받은 후 사용한다.

<br/>

#### 클라우드 컴퓨팅

- 클라우드 컴퓨팅은 네트워크 전체에서 컴퓨팅, 네트워크, 스토리지 인프라 자원, 서비스, 플랫폼, 애플리케이션을 사용자에게 제공하는 접근 방식이다.
-  가상화는 단일한 물리 하드웨어 시스템에서 여러 시뮬레이션 환경이나 전용 리소스를 생성할 수 있는 기술이고, 클라우드는 네트워크 전체에서 확장 가능한 리소스를 추상화하고 풀링하는 IT 환경이다


<br/>

# IaaS, PaaS, SaaS

![image](https://user-images.githubusercontent.com/61372486/128214549-06bf2622-16f5-4707-8029-9d4b160db517.png)

<br/>

- 얼마만큼 사용자가 관리하고 얼마만큼 클라우드에서 제공받는가에 따라 네 가지로 나누어져 있다.
- 노란색의 You manage는 사용자가 관리해야 할 부분이고, 흰색의 Managed by vendor는 기업(클라우드)에서 관리해주는 부분이다.

<br/>

## Packaged Software

![image](https://user-images.githubusercontent.com/61372486/128215798-7cb3b6fe-73ad-45e4-8463-444d3e6e084e.png)

<br/>

- 물리적인 장치, 하드웨어(CPU, RAM, Storage, Network device 등등)을 모두 직접 구매해야 한다.
- 직접 OS를 설치해야 힌다.
- 네트워크 환경을 직접적으로 구성해야 한다.
- 서버 관리를 직접적으로 해야 한다. (트래픽, 프로지버닝 등등)
- 이런 모든 것을 직접 사용자가 다 준비해야 하기 때문에 매우 큰 시간과 돈을 소비하게 된다.


<br/>

## IaaS(Infrastructure as a service)

![image](https://user-images.githubusercontent.com/61372486/128215841-e084a568-0aae-4b12-874f-fd1df10b0cd7.png)

<br/>

- IaaS는 기업이 준비해놓은 환경에서 우리가 선택할 수 있다는 점에서 차이가 있다.
- 일반적으로 적은 OS가 지원된다. (아마존은 일부 Linux와 Windows Server 제공)
- 고객은 OS와 어플리케이션을 직접 관리해야 한다.
- 관리 측면에서 개발자와 인프라 관리자의 역할을 분담시킬 수 있다.

<br/>

#### 장점

- 고객은 가상 서버 하위의 레벨에 대해서는 고려할 필요가 없다.

<br/>

#### 단점

- 가상 서버 하위의 레벨에 대해서는 전혀 고객이 접근하거나 컨트롤할 수 없다.
- 가상 서버 하위의 레벨에 대해서 고려할 필요가 없는 사용자가 쓰기에 적합한 모델이다.

<br/>

#### 예시

- AWS의 EC2
  - EC2를 이용하면 우리는 물리적인 서버와 Network, Storage 등등을 직접 구매하거나 준비하지 않아도 원하는 OS를 깔아 서버로 사용할 수 있다.
  - 선택권을 주고 OS의 종류나 다양한 자원들을 사용자가 선택하므로 대표적인 IasS라고 불리고 있다.
  
<br/>


## PaaS(Platform as a service)


![image](https://user-images.githubusercontent.com/61372486/128215885-38198d50-f647-4f59-9d29-4a70a75be5d0.png)

<br/>

- 개발자가 응용 프로그램을 작성할 수 있도록 플랫폼 및 환경을 제공하는 모델이다
- 운영 팀이 인프라를 모니터링할 필요가 없다.
- 사용자는 OS, Server 하드웨어, Network 등등을 고려할 필요가 없다.
- 사용자는 어필리케이션 자체에만 집중할 수 있다. 즉 개발자는 빠르게 어플리케이션을 개발하고 서비스 가능하게 할 수 있다.
- 아마존과 같은 서비스가 VM을 제공하는 IaaS라면, PaaS는 node.js, Java와 같은 런타임을 미리 깔아놓고, 거기에 소스코드를 넣어서 돌리는 구조입니다. 다시 한번 얘기하면 우리는 소스코드만 적어서 빌드 하는 것이고, 컴파일은 클라우드에서 하여 결과만 가져오는 거라고 생각하면 된다.

<br/>

#### 장점

- PaaS의 경우 이미 설치된 미들웨어 위에 코드만 돌리면 되기 때문에, 아무래도 관리가 매우 편하다.
- 가장 이상적인 어플리케이션 플랫폼 관점의 클라우드 모델로 업계에 받아들여지고 있다.

<br/>

#### 단점

- 하나의 인프라를 기반으로 개발할 수 있다는 것 자체가 장점이자 단점이 될 수 있다.
- 어플리케이션이 플랫폼에 종속되어 개발되기 때문에 다른 플랫폼으로의 이동이 어려울 수도 있다.

<br/>

#### 예시

- PaaS의 제공 업체로는 Heroku, Google App Engine, IBM Bluemix, OpenShift, SalesForce가 있다

<br/>

## SaaS(Software as a service)


![image](https://user-images.githubusercontent.com/61372486/128216368-4bba03bb-e8ea-46f3-93e5-7e9679840988.png)

<br/>

- 설치할 필요 없이 클라우드를 통해 제공되는 SW를 말한다.
- 모든 것을 기업(클라우드)에서 제공함으로 사용자는 별도의 설치나 부담이 필요 없이 SW를 사용할 수 있다.
- SaaS는 소비 관점에서 제공되는 IT 방식의 서비스로 정리할 수 있다.
- 구독의 방식으로 돈을 벌거나 트래픽 기반으로 돈을 벌 수 있다.


<br/>

#### 장점

- Public Cloud에 있는 SW를 웹 브라우저로 불러와 언제 어디서나 사용할 수 있다.
- 사용자는 웹만 접속하면 되기 때문에 사용하기 매우 쉽고, 최신 SW 업데이트를 즉시 제공받을 수 있다. 


<br/>

#### 단점

- SaaS의 특성상 반드시 인터넷에 접속할 수 있어야만 사용할 수 있고, 외부의 데이터 노출에 대한 위험이 있다.

<br/>

#### 예시

- 웹 메일, 구글 클라우드, 네이버 클라우드, MS오피스365, 드롭박스 등이 있다.




<br/><br/><br/><br/>

---
참조:
- [클라우드 컴퓨팅, IaaS, PaaS, SaaS이란?](https://wnsgml972.github.io/network/2018/08/14/network_cloud-computing/)
- [클라우드 컴퓨팅(Cloud Computing)이란](https://www.cisp.or.kr/archives/12017)
- [Cloud offering: Comparison between IaaS, PaaS, SaaS, BaaS](https://assist-software.net/blog/cloud-offering-comparison-between-iaas-paas-saas-baas)


