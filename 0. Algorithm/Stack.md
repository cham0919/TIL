#  Stack


<br/>

##  Stack이란?

<br/>


- 데이터를 일시적으로 저장하기 위해 사용되는 자료구조
- 데이터의 입력과 출력의 순서는 후입선출(LIFO, Last In First Out), 가장 나중에 넣은 데이터를 먼저 꺼냄
- 데이터를 넣는 작업을 push, 데이터를 꺼내는 작업을 pop 이라고 함
- 푸시와 팝을 하는 위치를 top 이라 하고, 가장 아래 부분을 bottom 이라고 함
- Java 프로그램에서 메서드를 호출하고 실행 할 때 프로그램 내부에서 스택을 사용한다.


![](https://images.velog.io/images/cham/post/7d344393-484a-43fb-90eb-034bd33d5ff3/image.png)


<br/>

## Stack 만들기

  
<br/>


- int 형의 스택 구현 프로그램 Class IntStack 

<br/>

```java
public class IntStack{
  private int max; // 스택 용량(최대 데이터 수)
  private int ptr; // 스택 안 데이터 수(스택 포인터)
  private int[] stk; // 스택 본체용 배열

  public class EmptyIntStackException extends RuntimeException{
	public EmptyIntStackException(){  }
  } // 실행 시 예외 : 스택이 비어있음
  public class OverflowIntStackException extends RuntimeException{
	public OverflowIntStackException(){  }
  } // 실행 시 예외 : 스택이 가득 참

  public IntStack(int capacity){ // 생성자, 매개변수는 stack의 용량
 	ptr = 0; 
	max = capacity;
	try{
	  stk = new int[max]; // 스택 본체 배열 생성
	} catch(OutOfMemoryError e){ // 생성할 수 없음
	  max = 0;
	}
  }
  ……
}

```


<br/>

##  Stack Method

  
<br/>


- push() : 스택에 데이터를 push(추가)하고 그 값을 반환하는 메서드


```java
public int push(int x) throws OverflowIntStackException{
    if(ptr >= max) // 스택이 가득 차서 push할 수 없는 경우 예외 던지기
	throw new OverflowIntStackException();
    return stk[ptr++] = x; // 전달받은 데이터를 배열 str[ptr]에 
                           // 저장하고 스택 포인터 증가     
}
```


- pop() : 스택의 꼭대기에서 데이터를 pop(제거)하고 그 값을 반환하는 메서드


```java
public int push(int x) throws EmptyIntStackException{
    if(ptr <= 0) // 스택이 비어 있어 pop할 수 없는 경우 예외 던지기
	throw new EmptyIntStackException();
    return stk[--ptr] ; // 스택 포인터(ptr)를 감소시키고 stk[ptr]에 저장된 값을 반환    
}

```

- peek() : 스택의 꼭대기에 있는 데이터를 빼꼼 확인만 하는 메서드

```java
public int peek( ) throws EmptyIntStackException{
    if(ptr <= 0) // 스택이 비어 있음
	throw new EmptyIntStackException();
    return stk[ptr-1] ; // stk[ptr-1]에 저장된 값을 반환    
}
```

- indexOf() : 스택 배열에 x와 같은 값의 데이터가 포함되어 있는지 조사하는 메서드


```java
public int indexOf(int x){
    for(int i=ptr-1; i>=0; i--) // 동일한 값이 있을 시 
                                // 가장 먼저 pop되는 데이터를 찾기 위해 꼭대기 쪽부터 스캔
       if(stk[i] == x) 
	return i; // 검색 성공
    return -1; // 검색 실패
}

```


- clear() : 스택의 모든 데이터를 삭제하는 메서드

```java
public void clear(int x) {
    ptr = 0;; // 스택 비우기
}


```


- capacity() : 스택의 용량(max의 값)을 확인하는 메서드

```java
public int capacity(int x) {
    return max ; // 스택 용량을 반환
}


```


- size() : 스택의 현재 데이터 수(ptr의 값)를 확인하는 메서드

```java
public int size( ) {
    return ptr; // 스택에 쌓여 있는 데이터 수를 반환
}

```


- isEmpty() : 스택이 비어 있는지 검사하는 메서드 (스택이 비어 차있으면 true)

```java
public boolean isEmpty( ) {
    return ptr <= 0; // 스택.. 비어있니?
}

```


- isFull() : 스택이 가득 차있는지 검사하는 메서드 (스택이 가득 차있으면 true)

```java
public boolean isFull( ) {
    return ptr >= max; // 스택.. 풀방이니?
}

```


- dump() : 스택 안에 있는 모든 데이터를 표시하는 메서드

```java
public void dump( ){
    if(ptr <= 0) // 스택이 비어있을 때 메시지 출력
         System.out.println(“스택이 비어있습니다.”); 
    else { // 스택 안의 모든 데이터를 바닥에서 꼭대기 순으로 출력
         for(int i=0; i<ptr; i++)
	System.out.print(stk[i]+” ”);
         System.out.println();
    }
}

```





<br/><br/><br/><br/><br/><br/><br/>

---
참고 도서
- Do it! 자바 프로그래밍 입문
