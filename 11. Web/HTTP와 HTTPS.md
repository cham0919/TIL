# HTTP

HTTP(Hyper Text Transfer Protocol)란 서버/클라이언트 모델을 따라 데이터를 주고 받기 위한 프로토콜이다.

즉, HTTP는 인터넷에서 하이퍼텍스트를 교환하기 위한 통신 규약으로, 80번 포트를 사용하고 있다. 따라서 HTTP 서버가 80번 포트에서 요청을 기다리고 있으며, 클라이언트는 80번 포트로 요청을 보내게 된다.

## HTTP의 구조

HTTP는 애플리케이션 레벨의 프로토콜로 TCP/IP 위에서 작동한다. HTTP는 상태를 가지고 있지 않는 Stateless 프로토콜이며 Method, Path, Version, Headers, Body 등으로 구성된다.

![image](https://user-images.githubusercontent.com/61372486/128402995-d02b3651-c6ab-40cc-a960-fd514da9e8f6.png)


하지만 HTTP는 암호화가 되지 않은 평문 데이터를 전송하는 프로토콜이기 때문에, HTTP로 비밀번호나 주민등록번호 등 민감한 데이터를 주고 받으면 제3자가 정보를 조회할 수 있다.

이러한 문제를 해결하기 위해 HTTPS가 등장하게 되었다.

<br/>

# HTTPS

HTTPS는 HTTP에 데이터 암호화가 추가된 프로토콜이다. HTTPS는 HTTP와 다르게 443번 포트를 사용하며, 네트워크 상에서 중간에 제3자가 정보를 볼 수 없도록 암호화를 지원하고 있다.

HTTPS는 SSL(Secure Socket Layer)/TLS(Transport Layer Security) 전송 기술을 사용한다.
 
TCP, UDP와 같은 일반적인 인터넷 통신에 안전한 계층(layer)을 추가하는 방식이다.
 
이 기술을 구현하기 위해 웹 서버에 설치하는 것이 SSL/TLS 인증서다.
 
 TLS는 SSL의 개선 버전으로, 최신 인증서는 TLS를 사용하지만 편의적으로 SSL 인증서라고 통용하고 있다.
 
 <br/>
 
## 대칭키

정보의 송신자와 수신자가 서로 알고있어야하며, 송신자는 이 대칭키를 사용해 암호화를 수신자는 대칭키를 사용해 복호화를 하게된다.


<br/>

### 단점

정보의 송신자와 수신자(통신에서는 서버와 클라이언트)만 이 공개키를 알고있어야 하는데, 이 공개키를 통신으로 보낼경우 노출되고, 공개키가 노출되는 순간 더이상 안전하게 암호화 되었다고 볼 수 없다.

<br/>

## 비대칭키

대칭키의 약점을 해결하는 방식이다.
비대칭키 방식에서는 키가 두개 존재 한다.(Public Key, Private Key)

A라는 키를 사용해 암호화 했다면, B라는 키를 사용해 복호화 하여야만 복호화가 가능하다

<br/>

## 비대칭키를 이용한 인증

위의 방식대로라면 공개키를 이용해 암호화를 했다면 비밀키로만 복호화할 수 있다.

이 방법을 반대로 사용하여 비밀키로 암호화한 다음 공개키로 복호화한다면?

암호로써 역할을 다하진 못하지만 공개키로 정상적으로 복호화가 된다면 공개키와 짝을이루는 비밀키를 사용한 사람만이 공개키를 통해 복호화할 수 있는 정보를 보낼 수 있기때문에,
이 정보가 특정 기관이 보낸 정보임을 보장할 수 있다.  

이것이 인증서의 원리가 된다.

<br/>

## Certificate Authority

인증서의 기능은 크게 두가지다.

1. 클라이언트가 접속한 서버가 신뢰할 수 있는 서버임을 보증
2. SSL 통신에 사용할 공개키 제공

이 역할을 하는 민간기업을 CA라고 부른다. SSL을 통해 암호화된 통신을 제공하려는 서비스는 이 CA에서 인증서를 구입해서 사용하여야 하며, 내부적인 용도나 개발의 용도로 사용할때는 사설 CA, 즉 자기자신이 CA가되는 방법도 존재한다.

각 브라우저는 CA의 리스트와 공개키를 기본적으로 알고있다.

<br/>

# TLS Handshake



![image](https://user-images.githubusercontent.com/61372486/128404243-e82c94b1-de52-49b5-bf9b-28deeac4cd04.png)


<br/>

## 1. Client Hello

클라이언트가 서버에게 다음과 같은 정보를 전송한다.

- 클라이언트에서 생성한 랜덤 값
- 클라이언트가 지원하는 암호화 방식
- (이전에 Handshake가 일어난 경우) Session ID


<br/>

## 2. Server Hello

서버가 클라이언트에게 다음과 같은 정보를 전송한다.

- 서버에서 생성한 랜덤 값
- 클라이언트가 제공한 암호화 방식 중 서버가 선택한 암호화 방식
- 인증서

<br/>

## 3. Server Certificate ~ Server Hello Done

클라이언트는 서버가 제공한 인증서가 CA에 의해 제공된 것인지 확인하기 위해, 브라우저가 가지고있는 CA의 리스트를 확인한다.

만약 리스트에 없다면 경고, 리스트에 있다면 해당하는 CA의 PublicKey를 사용해 인증서가 복호화되는지 확인한다.
 
이 과정에서 복호화가 정상적으로 진행된다면 서버는 해당 CA에 의해 인증되었음을 뜻하고, 이 과정에서 서버의 Public Key를 얻을 수 있다.

<br/>

## 4. Exchange Pre-master Secret

클라이언트는 1에서 생성한 랜덤값과 2에서 생성한 랜덤값을 조합하여 Pre-master Secret이라는 대칭키를 생성하게 된다. 

또한 서버의 공개키를 사용하여 이 Pre-master Secret Key를 암호화 시키고 서버에 전송한다

<br/>

## 5. Master Secret, Session Key

서버는 PrivateKey를 통해 Pre-master Secret Key를 복호화해내고, 서버와 클라이언트 모두 일련의 과정을 거쳐 Master-Secret이라는 값을 얻어낸다.

Master-Secret은 Session Key값을 생성하고, 생성된 Session-Key는 데이터의 송수신 시에 대칭키 값으로 사용되고, 해당 세션이 만료되면 폐기된다.



<br/><br/><br/><br/>

 
---
참조:
- [HTTPS 통신과정 쉽게 이해하기 #3(SSL Handshake, 협상)](https://aws-hyoh.tistory.com/entry/HTTPS-%ED%86%B5%EC%8B%A0%EA%B3%BC%EC%A0%95-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-3SSL-Handshake)
- [TLS Handshake 의 이해](https://velog.io/@kykevin/TLS-Handshake-%EC%9D%98-%EC%9D%B4%ED%95%B4)
- [[Web] HTTP와 HTTPS 및 차이점](https://mangkyu.tistory.com/98)








