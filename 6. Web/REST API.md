# REST API란?

Representational State Transfer (표현의 상태 전달) 

자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태를 주고 받는 모든 것을 의미함.

자원: 해당 소프트웨어가 관리하는 모든 것 (문서, 그림, 데이터, 해당 소프트웨어 자체 등)

>자원의 표현: 그 자원을 표현하기 위한 이름 (DB의 학생 정보가 자원일 때, 'students'가 자원의 표현 임)

상태 전달은 데이터가 요청되어지는 시점에서 자원의 상태(정보)를 전달합니다. JSON 혹은 XML를 통해 데이터를 주고 받는 것이 일반적입니다.

<br/>

## REST의 구체적인 개념

HTTP URI(Uniform Resource Identifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 자원에 대한 CRUD Operation을 적용하는 것을 의미합니다.

<br/>

## REST의 요소

 - Method
 
 
| Method  | 의미 | Idempotent |
| -----   | --- | ---------- |
| POST   | Create | No |
| GET   | Select | Yes |
| PUT   | Update | Yes |
| PATCH   | Update | Yes |
| DELETE   | Delete | Yes |

Idempotent : 한 번 수행하냐, 여러 번 수행했을 때 결과가 같은지

<br/>

## REST Message

메시지 포맷이 존재한다.

JSON, XML 과 같은 형태가 있음 (최근에는 JSON 을 씀)

```java
HTTP POST, http://myweb/users/
{
	"users" : {
		"name" : "terry"
	}
}
```
        
        
<br/>

## REST 특징

 - Uniform Interface

  - HTTP 표준만 맞는다면, 어떤 기술도 가능한 Interface 스타일

  - 예) REST API 정의를 HTTP + JSON로 하였다면, C, Java, Python, IOS 플랫폼 등 특정 언어나 기술에 종속 받지 않고, 모든 플랫폼에 사용이 가능한 Loosely Coupling 구조

포함

 - Self-Descriptive Messages

  - API 메시지만 보고, API를 이해할 수 있는 구조 (Resource, Method를 이용해 무슨 행위를 하는지 직관적으로 이해할 수 있음)



<br/><br/><br/><br/>

참조

---

- [REST API](https://github.com/gyoogle/tech-interview-for-developer/blob/master/Web/%5BWeb%5D%20REST%20API.md)