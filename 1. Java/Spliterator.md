# Spliterator

Java 8은 병렬 작업에 특화되어 있는 ```Spliterator```라는 새로운 인터페이스를 제공한다.

***Spliterator***

```java
public interface Spliterator<T> {
    
    boolean tryAdvance(Consumer<? super T> action);
    Spliterator<T> trySplit();
    long estimateSize();
    int characteristics();
    ...
}
```

T는 ```Spliterator```를 탐색하는 요소의 형식을 가리킨다.

```tryAdvance```는 ```Spliterator``` 요소를 하나씩 소비하면서 탐색해야 할 요소가 남아있는지 여부를 반환한다.

```trySplit```는 ```Spliterator```  일부 요소(자신이 반환한 요소)를 분할해서 두 번째 ```Spliterator``` 를 생성한다.

```Spliterator```에서는 ```estimateSize``` 로 탐색해야 할 요소 수 정보를 제공할 ㅜㅅ 있다.

<br/>

## 분할 과정


![image](https://user-images.githubusercontent.com/61372486/128547827-033a007a-9a64-421f-9785-1cda9062a0d6.png)

<br/>

- 첫 번째 ```Spliterator```에  ```trySplit```를 호출하면 두 번째 ```Spliterator```가 생성된다.

- 두 개의 ```Spliterator```에 다시 ```trySplit```를 호출하면 네 개의 ```Spliterator```가 생성된다.

- ```trySplit```의 결과가 null이 될 때까지 반복한다.

- 재귀 분할 과정이 종료된다.



<br/>

## 특성

```characteristics```는 ```Spliterator``` 자체의 특성 직합을 포함하는 int를 반환한다.

|특성|의미|
|---|---|
|ORDERED|리스트처럼 요소에 정해진 순서가 있으므로 요소를 탐색하고 분할할 때 이 순서에 유의해야한다|
|DISTINCT|x, y 두 요소를 방문했을 때 ```x.equals(y)```는 항상 false를 반환한다.|
|SORTED|탐색된 요소는 미리 정의된 정렬 순서를 따른다|
|SIZED|크기가 알려진 소스로 ```Spliterator```를 생성했으므로 ```estimateSize```는 정확한 값을 반환한다.|
|NONNULL|탐색하는 모든 요소는 null이 아니다|
|IMMUTABLE|이 ```Spliterator```의 소스는 불변이다. 즉, 요소를 탐색하는 동안 요소를 추가하거나, 삭제, 수정이 불가하다.|
|CONCURRENT|동기화 없이 ```Spliterator```의 소스를 여러 스레드에서 동시에 고칠 수 있다.|
|SUBSIZED|```Spliterator``` 그리고 분할되는 모든 ```Spliterator```는 SIZED 특성을 갖는다.|

<br/>

## 커스텀 Spliterator 예제

문자열의 단어 수를 계산하는 메서드 구현

```java
class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;
    public WordCounterSpliterator(String string) {
        this.string = string;
    }
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 현재 문자를 소비한다.
        action.accept(string.charAt(currentChar++));
        //소비할 문자가 남아있으면 true를 반환한다.
        return currentChar < string.length();
    }
    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // 파싱할 문자열을 순차 처리할 수 있을 만큼 충분히 작아졌음을 알리는 null을 반환
        if (currentSize < 10) {
            return null;
        }
        for (int splitPos = currentSize / 2 + currentChar;
                 // 파싱할 문자열의 중간을 분할 위치로 설정
                 splitPos < string.length(); splitPos++) {
            // 다음 공백이 나올 때까지 분할 위치를 뒤로 이동
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 처음부터 분할 위치까지 문자열을 파싱할 새로운 WordCounterSpliterator 생성 
                Spliterator<Character> spliterator =
                   new WordCounterSpliterator(string.substring(currentChar,
                                                               splitPos));
                // 이 WordCounterSpliterator의 시작 위치를 분할 위치로 설정
                currentChar = splitPos;
                // 공백을 찾았고 문자열을 분리했으므로 루프를 종료
                return spliterator;
            }
        }
        return null;
    }
    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }
    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NON-NULL + IMMUTABLE;
    }
}
```


커스텀한 ```WordCounterSpliterator```를 사용할 수 있다.

```java
Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
Stream<Character> stream = StreamSupport.stream(spliterator, true);
```




<br/><br/><br/><br/><br/><br/><br/>


Reference

---

- 라울-게이브리얼 우르마, 『모던 자바 인 액션』, 우정은, 한빛미디어(2018)
