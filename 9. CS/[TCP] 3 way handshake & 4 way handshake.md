# [TCP] 3 way handshake & 4 way handshake

연결을 성립하고 해제하는 과정을 말한다

<br/>

## 3 way handshake - 연결 성립

TCP는 정확한 전송을 보장해야 한다. 따라서 통신하기에 앞서, 논리적인 접속을 성립하기 위해 3 way handshake 과정을 진행한다.


![image](https://user-images.githubusercontent.com/61372486/127892826-23bf63e8-0a40-41d9-adac-097133614f96.png)


- 클라이언트가 서버에게 SYN 패킷을 보냄 (sequence : x)

- 서버가 SYN(x)을 받고, 클라이언트로 받았다는 신호인 ACK와 SYN 패킷을 보냄 (sequence : y, ACK : x + 1)

- 클라이언트는 서버의 응답은 ACK(x+1)와 SYN(y) 패킷을 받고, ACK(y+1)를 서버로 보냄


이렇게 3번의 통신이 완료되면 연결이 성립된다. (3번이라 3 way handshake인 것)


<br/>

## 4 way handshake - 연결 해제

연결 성립 후, 모든 통신이 끝났다면 해제해야 한다.

![image](https://user-images.githubusercontent.com/61372486/127892943-353bf71b-ffb7-423b-87e4-75a3edb4d4d0.png)

- 클라이언트는 서버에게 연결을 종료한다는 FIN 플래그를 보낸다.

- 서버는 FIN을 받고, 확인했다는 ACK를 클라이언트에게 보낸다. (이때 모든 데이터를 보내기 위해 CLOSE_WAIT 상태가 된다)

- 데이터를 모두 보냈다면, 연결이 종료되었다는 FIN 플래그를 클라이언트에게 보낸다.

- 클라이언트는 FIN을 받고, 확인했다는 ACK를 서버에게 보낸다. (아직 서버로부터 받지 못한 데이터가 있을 수 있으므로 TIME_WAIT을 통해 기다린다.)

    - 서버는 ACK를 받은 이후 소켓을 닫는다 (Closed)

    - TIME_WAIT 시간이 끝나면 클라이언트도 닫는다 (Closed)
    
    
이렇게 4번의 통신이 완료되면 연결이 해제된다.


<br/>

만약 "Server에서 FIN을 전송하기 전에 전송한 패킷이 Routing 지연이나 패킷 유실로 인한 재전송 등으로 인해 FIN패킷보다 늦게 도착하는 상황"이 발생한다면 이 패킷은 Drop되고 데이터는 유실될 것이다. 

이러한 현상에 대비하여 Client는 Server로부터 FIN을 수신하더라도 일정시간(디폴트 240초) 동안 세션을 남겨놓고 잉여 패킷을 기다리는 과정을 거치게 되는데 이 과정을 **TIME_WAIT** 라고 한다.





<br/><br/><br/><br/>

참조

---

- [[TCP] 3 way handshake & 4 way handshake](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Computer%20Science/Network/TCP%203%20way%20handshake%20%26%204%20way%20handshake.md)
- [[ 네트워크 쉽게 이해하기 22편 ] TCP 3 Way-Handshake & 4 Way-Handshake](https://mindnet.tistory.com/entry/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%EC%89%BD%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-22%ED%8E%B8-TCP-3-WayHandshake-4-WayHandshake)




 