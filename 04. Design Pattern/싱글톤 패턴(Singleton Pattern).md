## 싱글톤(Singleton) 패턴


객체의 인스턴스가 오직 1개만 생성되는 패턴
 
 
 
### 구현

```java
public class Singleton {

    private static Singleton instance;

	static {
	  if (instance == null) {
	      synchronized (Singleton.class) {
	          if (instance == null) {
	              instance = new Singleton();
	          }
	      }
	  }
	}
    
    private Singleton() {}

    public static Singleton getInstance() {
	    return instance;
	}
}
``` 
 
synchronized를 사용해 멀티 스레드에서의 안전성 보장 
 
 
### 이점

 - 최초 한번의 new 연산자를 통해서 고정된 메모리 영역을 사용하기 때문에 추후 해당 객체에 접근할 때 메모리 낭비 방지 가능
 - 이미 생성된 인스턴스를 활용함으로 인한 속도 이점
 - 전역으로 사용되는 인스턴스이기 때문에 다른 클래스의 인스턴스들이 접근하여 사용할 수 있는 데이터 공유 이점
  
  
### 문제점

- 동시성 문제 해결을 위해 syncronized 키워드를 사용함으로 인한 코드량 증가
- new 키워드를 직접 사용하여 클래스 안에서 객체를 생성하고 있으므로  DIP를 위반하게 되고 OCP 원칙 또한 위반할 가능성 상승



<br/><br/><br/>
---
참조 : 
[싱글톤(Singleton) 패턴이란?](https://woowacourse.github.io/javable/post/2020-11-07-singleton/)