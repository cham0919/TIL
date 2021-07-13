
#  Queue



<br/>

## Queue란?

<br/>

- 데이터를 일시적으로 저장하기 위해 사용되는 자료구조
- 데이터의 입력과 출력의 순서는 선입선출(FIFO, First In First Out), 가장 먼저 넣은 데이터를 먼저 꺼냄
- 데이터를 넣는 작업을 인큐(enqueue), 데이터를 꺼내는 작업을 디큐(dequeue) 이라고 함
- 데이터를 꺼내는 위치를 프런트(front)라 하고, 데이터를 넣는 위치를 리어(rear)라고 함


<br/>



![](https://images.velog.io/images/cham/post/1647a1af-250b-4015-a3f8-828685e670cf/image.png)



1. 24를 인큐 - 	rear의 데이터가 저장된 요소의 다음 요소에 데이터를 저장. 이 처리의 복잡도는 O(1)
2. 19를 디큐 - front에 위치한 19를 꺼낸 다음 두 번째 이후에 위치하는 요소를 모두 맨 앞으로 옮김.
	이 처리의 복잡도는 O(n) -> 데이터를 꺼낼 때 마다 이 처리를 하게 되면 효율이 떨어짐




<br/><br/>



## Ring Buffer란?


<br/>


- 배열 요소를 앞쪽으로 옮기지 않는 큐
- 이를 위해 배열의 처음이 끝과 연결되어 있다고 보는 링 버퍼(ring buffer) 자료구조를 사용
- 논리적으로 어떤 요소가 첫 요소이고 마지막 요소인지 식별하기 위한 변수 front와 rear
- 맨 처음 요소의 인덱스를 프런트(front)라 하고, 맨 끝 요소의 하나 뒤의 인덱스(데이터를 인큐 하게 될 위치)를 리어(rear)라고 함


<br/>


![](https://images.velog.io/images/cham/post/4263359a-d191-4411-aaea-d1365e0481b4/image.png)


1. 82를 인큐 - rear가 가리키고 있는 위치에 82를 저장하고 rear 값을 1만큼 증가
2. 35를 디큐 - 	front의 요소 값 35를 빼고 front 값을 1만큼 증가 
 front와 rear 값을 업데이트하며 인큐와 디큐를 수행하기 때문에
	앞에서 발생한 요소 이동 문제를 해결할 수 있다. 처리의 복잡도는 O(1)


<br/><br/>



## Ring Buffer Queue 만들기

<br/>


```java
public class IntQueue{
  private int max; // 큐의 최대 용량(최대 데이터 수)
  private int front; // 첫 번째 요소 인덱스
  private int rear; // 마지막 넣은 요소의 하나 뒤 인덱스
  private int num; // 큐 안의 현재 데이터 수
  private int[] que; // 큐 본체용 배열

  public class EmptyIntQueueException extends RuntimeException{
	public EmptyIntQueueException(){  }
  } // 실행 시 예외 : 큐가 비어있음
  public class OverflowIntQueueException extends RuntimeException{
	public OverflowIntQueueException(){  }
  } // 실행 시 예외 : 큐가 가득 참

  public IntQueue(int capacity){ // 생성자, 매개변수는 queue의 용량
 	num = front = rear = 0; 
	max = capacity;
	try{
	  que = new int[max]; // 스택 본체 배열 생성
	} catch(OutOfMemoryError e){ // 생성할 수 없음
	  max = 0;
	}
  }
  ……
}

```


- rear로 다음 인큐 시에 데이터가 저장될 요소의 인덱스를 미리 준비해 둠
- front와 rear의 값이 같은 경우 큐가 비어있는지, 가득 찼는지 구별하기 위해 num이 필요
- 큐가 비어있을 때 num은 0이고, 가득 찼을 때는 num과 max 값이 같음



<br/><br/>
##  Queue Method

<br/>


- enque() : 큐에 데이터를 인큐(추가)하고 그 값을 반환하는 메서드

```java
public int enque(int x) throws OverflowIntQueueException{
    if(num >= max) // 큐가 가득 차서 인큐 할 수 없는 경우 예외 던지기
	throw new OverflowIntQueueException();
    que[rear++] = x; num++; // 데이터를 배열의 rear에 저장하고 rear 증가, num 증가
    if(rear == max) // 인큐 전의 rear 값이 max-1값과 같다면 인큐 후 max와 같아져
                    // 없는 공간을 가리키게 되므로  
	rear = 0; // rear의 값을 0으로 바꿔주는 작업 필요
    return x; 
}

```



- deque() : 큐에서 데이터를 디큐(제거)하고 그 값을 반환하는 메서드


```java
public int push(int x) throws EmptyIntQueueException{
    if(num <= 0) // 큐가 비어 있어 디큐 할 수 없는 경우 예외 던지기
	throw new EmptyIntQueueException();
    int x = que[front++] ; num--; // 데이터를 배열의 front에서 꺼내고 front 증가,num 감소
    if(front == max) // 디큐 전의 front 값이 max-1값과 같다면 디큐 후 max와 같아져 
                     // 없는 공간을 가리키게 되므로  
	front = 0; // front의 값을 0으로 바꿔주는 작업 필요
    return x;     
}
```




- peek() : 큐의 맨 앞(front)에 있는 데이터를 몰래 확인만 하는 메서드


```java
public int peek( ) throws EmptyIntQueueException{
    if(num <= 0) // 큐가 비어 있음
	throw new EmptyIntQueueException();
    return queue[front] ; // que[front]에 저장된 값을 반환    
}
```


- indexOf() : 큐 배열에 x와 같은 값의 데이터가 포함되어 있는지 조사하는 메서드

```java
public int indexOf(int x){
    for(int i=0; i<num; i++) {// front에서 rear쪽으로 스캔
       int idx = (i+front)%max; // 스캔의 시작은 배열의 첫 요소가 아닌 큐의
                                // 첫 요소(front)이기 때문
       if(que[idx] == x) 
	return idx; // 검색 성공
    }
    return -1; // 검색 실패
}

```



- clear() : 큐의 모든 데이터를 삭제하는 메서드


```java
public void clear() {
    num = front = rear = 0 ; // 큐를 비움
}
```


- capacity() : 큐의 용량(max의 값)을 확인하는 메서드

```java
public int capacity(int x) {
    return max ; // 큐의 용량을 반환
}
```


- size() : 큐의 현재 데이터 수(num 값)를 확인하는 메서드

```java
public int size( ) {
    return num; // 큐에 쌓여 있는 데이터 수를 반환
}
```
- isEmpty() : 큐가 비어 있는지 검사하는 메서드 (큐가 비어 차있으면 true)

```java
public boolean isEmpty( ) {
    return num <= 0; 
}
```

- isFull() : 큐가 가득 차있는지 검사하는 메서드 (가득 차있으면 true)

```java
public boolean isFull( ) {
    return num >= max;
}
```

- dump() : 큐 안에 있는 모든 데이터를 표시하는 메서드

```java
public void dump( ){
    if(num <= 0) // 큐가 비어있을 때 메시지 출력
         System.out.println(“큐가 비어있습니다.”); 
    else { // 큐 안의 모든 데이터를 front에서 rear순으로 출력
         for(int i=0; i<num; i++)
	System.out.print(que[( i + front ) % max]+” ”);
         System.out.println();
    }
}
```


<br/>




- 링 버퍼는 오래된 데이터를 버리는 용도로 사용할 수 있다.
- 요소의 개수가 n인 배열에 계속해서 데이터가 입력 될 때, 가장 최근에 들어온 데이터 n 개만 저장하고 오래된 데이터는 버리는 용도로 사용
- 입력은 무한히 가능하지만, 남아있는 데이터는 최근에 입력한 데이터 뿐이다.


<br/><br/>


##  java.util.Queue에서 제공하는 Method


<br/>

- 큐에는 front와 rear라는 변수가 있어서, 각각 맨 앞과 맨 뒤의 인덱스를 가리키고 있다.
- 큐의 맨 앞에서는 삭제 또는 반환, 맨 뒤에서는 삽입만 한다.
- add() : 맨 뒤(rear다음)에 데이터를 삽입, 성공하면 true반환, 공간 부족 시 Exception발생
-  offer() : 맨 뒤에 데이터를 삽입. 성공하면 true, 실패하면 false반환
- peek() : 맨 앞(front)가 가리키는 값을 반환. 비어있다면 null반환
- poll() : 맨 앞(front)가 가리키는 값을 반환하고 삭제. 비어있다면 null 반환
- front = rear = -1 이면 queue가 초기상태
- front와 rear값이 같으면 queue가 공백상태

<br/>

```java
Queue q = new LinkedList(); // Queue의 인터페이스 구현체인 LinkedList
```

java에서는 Stack은 클래스로 구현하여 제공하지만 Queue는 인터페이스만 있고 별도의 클래스가 없다. 따라서 Queue인터페이스를 구현한 클래스들을 사용해야 한다


- PriorityQueue : 저장 순서에 관계없이 우선순위가 높은 것부터 꺼내게 된다
- Deque(Double-Ended Queue) : 양쪽 끝에서 추가/삭제가 가능한 Queue이다.
  - ArrayDeque와 LinkedList가 주로 사용된다.
  - Offer() = offerLast() / pollFirst() = poll() / peekFirst() = peek()
  - Stack과 Queue를 하나로 합쳐놓은 것과 같아서 Stack으로도 사용할 수도 있다.



<br/><br/><br/><br/><br/><br/><br/>

---
참고 도서
- Do it! 자바 프로그래밍 입문
