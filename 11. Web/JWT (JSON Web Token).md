# 1. JWT란?


 JWT란 JSON Web Token의 약자로 당사자간에 정보를 JSON 객체로 안전하게 전송하기위한 토큰이다. 


 로그인 인증 구현을 할때, 보통 두가지 방식이 있다. 

 - Session 기반 인증
 - Token 기반 인증
 
 
 Session 기반 인증을 사용할 때 문제점은 서버 측에서 유저들의 정보를 기억하고 있어야 하므로 서버 메모리에 대한 부담이 커진다. 

 또한 확장성에도 문제가 생기는데, 서버를 확장하며 앞단에 L4같은 로드밸런싱을 두게 되면, Session 공유가 되지 않는다는 점이다. 이를 해결하기 위해 세션 스토리지(보통 Redis)를 구축해야하는 번거로움이 생긴다. 

<br/>

![](https://images.velog.io/images/cham/post/6d877ba1-c710-4821-bda4-7ffcd41aad0e/image.png)

 
**Session 정보 불일치 발생** 
 
 ![](https://images.velog.io/images/cham/post/1faf8411-65d7-4bf2-939c-03c286774340/image.png)
 
**위와 같이 따로 Session 스토리지 구축 필요**
 
 <br/><br/>
 


 JWT를 사용하게 되면 토큰 기반 인증으로 유저 정보를 서버가 아닌 클라이언트가 관리하므로 서버에 대한 부담을 줄일 수 있게 된다. 즉 **stateless**한 서버를 만들 수 있게 된다. 또한 확장성이 용이하다. 인증에 필요한 토큰 자체를 클라이언트에서 관리하니, 세션 기반처럼 공유해야하는 스토리지가 필수가 아니게 된다.
 
 


<br/><br/><br/>
  

# 2. JWT 인증 방식


 JWT의 인증방식은 다음과 같다.


<br/>

![](https://images.velog.io/images/cham/post/edbc8e1e-564f-4935-9032-86356c1f86e4/image.png)



 
 <br/><br/>
 

1. Browser : Login 요청
2. Server : 인증 후, 사용자 데이터를 암호화한 토큰을 Browser로 응답
3. Browser : 전달받은 Token을 저장 후, 매 요청마다 Header에 Token을 추가하여 전달
4. Server : Token 확인 후, 사용자 정보 추출해 사용


<br/><br/>


 서버에서는 토큰을 확인하여 변조가 되었는지 확인 후, 토큰 내부에서 사용자 정보를 추출해 사용하면 되니 세션 기반보다 훨씬 가볍게 사용할 수 있다. 




<br/><br/><br/>
  

# 3. JWT 구조


 JWT는 크게 3가지로 구성되어 있다.


![](https://images.velog.io/images/cham/post/79cf702b-235b-4dd7-a485-5a96d1a5a331/image.png)

<br/>
<br/><br/>

### 3.1 Header

 Header에는 두가지의 정보를 담고 있다
 
 ![](https://images.velog.io/images/cham/post/e4f7a889-6ac1-479e-9d73-4f0080968ef3/image.png)
 
 1. ```typ```: 토큰의 타입을 지정
 2. ```alg```: 해싱 알고리즘을 지정.  해싱 알고리즘으로는 보통 HMAC SHA256 혹은 RSA 가 사용되며, 이 알고리즘은, 토큰을 검증 할 때 사용되는 signature 부분에서 사용된다.

그런 다음이 JSON은 Base64Url로 인코딩되어 JWT의 첫 번째 부분을 형성한다.

<br/>

### 3.2 Payload

 Payload에는 사용자의 정보를 담고 있다. 여기에 담는 정보의 한 조각을 클레임(claim) 이라고 부르고, 이는 name / value 의 한 쌍으로 이뤄져있다. 토큰에는 여러개의 클레임들을 넣을 수 있다.
 
 클레임의 종류는 크게 3가지로 볼 수 있다.
 
 1. Registered claims
 
 서비스에서 필요한 정보들이 아닌, 토큰에 대한 정보들을 담기위하여 이름이 이미 정해진 클레임들. 필수는 아니지만 권장되는 미리 정의 된 클레임 집합. 
 
 2. Public claims
 
 JWT를 사용하는 사람들이 원하는대로 정의 할 수 있는 클레임. Name 충돌을 방지하기 위해서는, 클레임 이름을 URI 형식으로 짓는다.
 
 3. Private claims
 
  당사자간에 정보를 공유하기 위해 생성 된 사용자 지정클레임.
 
![](https://images.velog.io/images/cham/post/69736dc9-4962-4688-bb45-ef6a87e246bf/image.png)
 
Payload를 생성하자면 위와 같다. 그런 다음 페이로드는 Base64Url로 인코딩되어 JSON 웹 토큰의 두 번째 부분을 형성하게 된다.


>서명 된 토큰의 경우이 정보는 변조로부터 보호되지만 누구나 읽을 수 있으니 암호화되지 않은 경우 JWT의 페이로드 또는 헤더 요소에 비밀 정보를 넣지 않아야 한다



<br/>

### 3.3 Signature

  이 Signature는 Header의 인코딩값과, Payload의 인코딩값을 합친후 주어진 비밀키로 해쉬를 하여 생성한다. 
 예를 들어, Header의 alg값에 HS256이 설정되어 있어 HMAC SHA256 알고리즘을 사용하려면 아래와 같다.
 
 
 
 ![](https://images.velog.io/images/cham/post/6b383f08-be47-45ec-b368-0151b0956765/image.png)
 
 
 <br/><br/><br/><br/>
 
 이렇게 생성된 3가지의 값을 합쳐 JWT를 만들게 된다.
 
 ![](https://images.velog.io/images/cham/post/3237b77b-bb8e-46f0-a590-abc4feca8e11/image.png)
 
 ![](https://images.velog.io/images/cham/post/811b8408-778f-43a2-8556-c5f2da76e9cc/image.png)



누군가 토큰을 탈취하여  PayLoad의 데이터를 변조한 뒤 요청하게 된다면, Signature값과 달라지게되어 변조를 확인할 수 있다. 즉, SecretKey가 유출되지 않는 이상 데이터 변조에 대해서는 안전하다 볼 수 있다.



<br/>
 


<br/><br/><br/>
  

# 4. JWT 장단점


그렇다면 무조건 JWT를 사용해야할까? 장점은 맨 상단에서 설명했지만 정리하여 장단점을 알아보자


장점

 - 토큰을 사용하면 세션을 통한 방식과 달리 서버측 부하를 낮출 수 있다
 
 - 추가적인 스토리지가 필수가 아니라 확장성 및 유지보수에도 용이하다
 
 - 토큰 기반으로 하는 다른 인증 시스템에 접근이 가능히다
 
   - 예를 들어 Facebook 로그인, Google 로그인 등은 모두 토큰을 기반으로 인증. 선택적으로 이름이나 이메일 등을 받을 수 있다



단점

  - 서버가 아닌 클라이언트에서 token을 관리하기 때문에, 세션과 달리 변조의 위협이 생겨도 만료시간까지 토큰을 삭제하지 못한다
  
 - 토큰 탈취 시, PayLoad안의 데이터가 유출될 수 있기 때문에 담을 수 있는 데이터가 한정적이다.
 
 - 저장할 필드 수에 따라서 토큰이 커질 수 있다. 즉, 호출 시 매 호출마다 헤더에 붙어서 가야하기 때문에, 길이가 길다는 것은 그만큼 네트워크 대역폭 낭비가 심하다는 의미이다.
 
  
  
<br/><br/><br/><br/>


---

참조
- https://jwt.io/introduction
 - https://brunch.co.kr/@springboot/491
 - https://joosjuliet.github.io/jwt-session/
 - https://velopert.com/2389