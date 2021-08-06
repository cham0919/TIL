# 포크/조인 프레임워크

포크/조인 프레임워크는 병렬화할 수 있는 작업을 재귀적으로 작은 작읍으로 분할한 다음 서브테스크 각각의 결과를 합쳐 전체 결과를 만들도록 설계되었다.

<br/>

## RecursiveTask

스레드 풀을 이용하기 위해 ```RecursiveTask\<R>```의 서브클래스를 만들어야한다.

R은 병렬화된 테스크가 생성하는 결과 형식 또는 결과가 없을 때는 ```RecursiveAction``` 형식이다.

```RecursiveTask```를 정의하기 위해선 ```compute```를 구현해야한다.

<br/>

### compute

```compute``` 메서드는 테스크를 서브테스크로 분할하는 로직과 더 이상 분할할 수 없을 때 개별 서브테스크의 결과를 생산할 알고리즘을 정의한다.

ex)

```java
if (테스크가 충분히 작거나 더 이상 분할할 수 없으면) {
    순차적으로 테스크 계산
} else {    
    테스크를 두 서브테스크로 분할
    테스크가 다시 서브테스크로 분할되도록 이 메서드 재귀 호출
    모든 서브테스크의 연산이 완료될 때까지 기다림
    각 서브테스크의 결과 합침
}
```

<br/>

## 예제

범위의 숫자를 더하는 예제를 살펴보자

![image](https://user-images.githubusercontent.com/61372486/128542024-8ddd0a12-326a-415f-b224-1400193ad9ad.png)

<br/>

**ForkJoinSumCalculator**

```java
import java.util.concurrent.RecursiveTask;

// RecursiveTask를 상속받아 포크조인 프레임워크에서 사용할 테스크 생성
public class ForkJoinSumCalculator extends RecursiveTask<Long> {  
        
    private final long[] numbers; // 더할 숫자 배열
    private final int start; // 이 서브테스크에서 처리할 배열의 초기 위치와 최종 위치
    private final int end;
    public static final long THRESHOLD = 10_000; // 이 값 이하의 서브테스크는 더 이상 분할 x


    // 메인 테스크를 생성할 때 사용할 공개 생성자
    public ForkJoinSumCalculator(long[] numbers) { 
        this(numbers, 0, numbers.length);
    }

    // 메인 테스트의 서브테스크를 재귀적으로 만들 때 사용할 private 생성자
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start; // 이 테스크에서 더할 배열의 길이
        if (length <= THRESHOLD) {
            // 기준값과 같거나 작으면 순차적으로 결과 계산
            return computeSequentially();
        } 
        // 배열의 첫 번째 절반을 더하도록 서브테스크 생성
        ForkJoinSumCalculator leftTask = 
                new ForkJoinSumCalculator(numbers, start, start+length/2);
        // ForkJoinPool의 다른 스레드로 새로 생성한 테스크를 비동기로 실행
        leftTask.fork();
        // 배열의 나머지 절반 더하는 서브테스크 생성
        ForkJoinSumCalculator rightTask =
                new ForkJoinSumCalculator(numbers, start, start+length/2);
        // 두번째 서브테스크 동기 실행. 추가 분할 발생할 수 있음
        Long rightResult = rightTask.compute();
        // 첫번째 서브테스크 결과를 읽거나 아직 결과가 없으면 기다림
        Long leftResult = leftTask.join();
        // 두 서브테스크의 결과를 조합한 값이 이 테스크의 결과값
        return rightResult + leftResult; 
    }

    // 더 분할할 수 없을 때 서브테스크의 결과를 계산하는 알고리즘
    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }
}

```

위는 n까지의 자연수 덧셈을 병렬로 수행하는 방법을 직관적으로 보여준다. 다음 처럼 원하는 수의 배열을 넘겨줘 사용할 수 있다.

```java
long[] numbers = LongStream.rangeClosed(1, n).toArray();
ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
new ForkJoinPool().invoke(task);
```

일반적으로 애플리케이션에서는 둘 이상의 ```ForkJoinPool```을 사용하지 않는다. 

즉, 한 번만 인스턴스화해서 정적 필드에 싱글턴으로 저장한다.

<br/>

### ForkJoinSumCalculator 실행

```ForkJoinSumCalculator```를 ```ForkJoinPool```로 전달하면 풀의 스레드가 ```ForkJoinSumCalculator```의 ```compute```를 실행하면서 작업을 수행한다.


![image](https://user-images.githubusercontent.com/61372486/128543813-707f116d-52ff-47b4-909b-26b5cf544787.png)


하지만 이 또한 충분한 이해없이 사용하게 된다면 오히려 성능 저하를 야기한다.

예를 들어 이와 같이 사용한다면 병렬 스트림을 사용할 때보다 성능이 나빠질 수 있는데 이는 전체 스트림을 long[]으로 변환했기 때문이다.

```java
// 스트림을 long[]로 변환하는 과정에서 성능 저하가 발생
long[] numbers = LongStream.rangeClosed(1, n).toArray();
```

<br/>

## 포크/조인 프레임워크 사용 주의 사항

- join 메서드를 테스크에 호출하면 테스크가 생산하는 결과가 준비될 때까지 호출자를 블록시킨다.
  - 그러므로, 두 서브테스크가 모두 시작된 다음에 join을 호출해야한다.
  - 그렇지 않으면, 각각의 서브테스크가 다른 테스크가 끝나길 기다리게 되며 순차 알고리즘보다 느린 프로그램이 될 가능성이 높다

- ```RecursiveTask``` 내에서는 ```ForkJoinPool```의 ```invoke``` 메서드를 사용하지 않아야 한다.
  - 대신 ```compute```나 ```fork``` 메서드를 직접 호출할 수 있다. ```invoke```는 순차 코드에서 병렬 계산을 시작할 때만 invoke를 사용한다.
  
- 서브테스크에 fork 메서드를 호출해서 ```ForkJoinPool```의 일정을 조절할 수 있다.
  - 양쪽 작업 모두 ```fork``` 메서드를 호출하는 것이 자연스러울 것 같으나 한쪽 작업에는 ```compute```를 호출하는 것이 효율적이다.
  - 두 서브테스크이 한 테스크에는 같은 스레드를 재사용할 수 있어 pool에서 불필요한 테스크를 할당하는 오버헤드를 피할 수 있다.
  
- 포크/조인 프레임워크를 이용하는 병렬 계산은 디버깅이 어렵다
  - 포크/조인 프레임워크에서는 fork라 불리는 다른 스레드에서 compute를 호출하므로 스택 트레이스가 도움되지 않는다.
  
- 멀티코어에 포크/조인 프레임워크를 사용하는 것이 순차 처리보다 무조건 빠르지는 않다.
  - 병렬 처리로 성능 개선하려면 테스크를 여러 독립적인 서브테스크로 분할할 수 있어야 한다.
  - 각 서브테스크의 실행시간은 새로운 테스크를 포킹하는데 드는 시간보다 길어야한다.
  - JOT 컴파일어에 의해 최적화되려면 몇 차례의 준비 과정 및 실행 과정을 거쳐야 한다.
  - 따라서 성능 측정은 여러번 프로그램을 실행한 결과를 측정해야한다.
  
<br/>

## 작업 훔치기

코어 개수와 관계없이 적절한 크기로 분할된 많은 테스크를 포킹하는 것은 바람직하다. 

이론적으로는 코어 개수만큼 병렬화된 테스크로 작업부하를 분할하면 모든 CPI 코어에서 이를 실행할 것이고, 크기가 같은 각각의 테스크는 같은 시간에 종료될 것 같지만 현실에서는 작왑완료 시간이 제각각으로 달라질 수 있다.

이를 작업 훔치기 기법으로 해결한다.

이는 ```ForkJoinPool```의 모든 스레드를 공정하게 분할한다. 각각의 스레드는 할당된 테스크를 포함하는 이중 연결 리스트를 참조하며 작업이 완료될 때마다 큐의 헤드에서 다른 테스크를 가져와 작업을 처리한다.

이때 한 스레드는 다른 스레드보다 할당된 테스크를 빨리 처리할 수 있다. 이 때 작업이 완료된 스레드는 유휴 상태로 바뀌는게 아닌 다른 스레드 큐의 꼬리에서 작업을 훔쳐온다.

따라서 테스크 크기를 작게 나누어야 작업자 스레드 간의 작업부하를 비슷한 수준으로 유지 가능하다.


<br/>

![image](https://user-images.githubusercontent.com/61372486/128546418-8054aa6b-8adc-4352-affa-46b74afc154c.png)




<br/><br/><br/><br/><br/><br/><br/>


Reference

---

- 라울-게이브리얼 우르마, 『모던 자바 인 액션』, 우정은, 한빛미디어(2018)