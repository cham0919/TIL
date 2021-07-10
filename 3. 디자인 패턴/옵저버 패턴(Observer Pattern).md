# 옵저버 패턴(Observer Pattern)


- 행위(Behavioral) 패턴
 


-  한 객체의 상태 변화에 따라 다른 객체의 상태도 연동되도록 일대다 객체 의존 관계를 구성 하는 패턴



- 데이터의 변경이 발생했을 경우 상대 클래스나 객체에 의존하지 않으면서 데이터 변경을 통보하고자 할 때 유용하다







<br/><br/>

## 구조


![](https://images.velog.io/images/cham/post/dd0cc138-4d4f-4430-85e2-34d9eb44ca3d/image.png)

- Observer
  - 데이터의 변경을 통보 받는 인터페이스
  - 즉, Subject에서는 Observer 인터페이스의 update 메서드를 호출함으로써 ConcreteSubject의 데이터 변경을 ConcreteObserver에게 통보한다.
- Subject
  - ConcreteObserver 객체를 관리하는 요소
  - Observer 인터페이스를 참조해서 ConcreteObserver를 관리하므로 ConcreteObserver의 변화에 독립적일 수 있다.
- ConcreteSubject
  - 변경 관리 대상이 되는 데이터가 있는 클래스(통보하는 클래스)
  - 데이터 변경을 위한 메서드인 setState가 있다.
  - setState 메서드에서는 자신의 데이터인 subjectState를 변경하고 Subject의 notifyObservers 메서드를 호출해서 ConcreteObserver 객체에 변경을 통보한다.
- ConcreteObserver
  - ConcreteSubject의 변경을 통보받는 클래스
  - Observer 인터페이스의 update 메서드를 구현함으로써 변경을 통보받는다.
  - 변경된 데이터는 ConcreteSubject의 getState 메서드를 호출함으로써 변경을 조회한다.






<br/><br/>


## 구현


- 여러 가지 방식으로 성적 출력하기
  - 입력된 성적 값 출력
  - 성적의 최소/최대 값만 출력

<br/>

![](https://images.velog.io/images/cham/post/cb394346-9dbf-44f7-b0ff-d26247e45a2e/image.png)


**Observer.class**

```java
public interface Observer {

    void update();
}

```

<br/>

- 데이터 변경을 통보했을 때, 처리하는 메서드 선언


<br/><br/>


**Subject.class**

```java
public abstract class Subject {
    // 추상화된 통보 대상 목록 (즉, 출력 형태에 대한 Observer)
    private List<Observer> observers = new ArrayList<Observer>();

    // 통보 대상(Observer) 추가
    public void attach(Observer observer) { observers.add(observer);}
    // 통보 대상(Observer) 제거
    public void detach(Observer observer) { observers.remove(observer);}
    // 각 통보 대상(Observer)에 변경을 통보. (List<Observer>객체들의 update를 호출)
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}
```

<br/>

 - 변경되었을 때, 통보 대상들에게 알림을 보내는 역할 선언

<br/><br/>

**ScoreRecord.class**

```java
/* 구체적인 변경 감시 대상 데이터 */
// 출력형태 2개를 가질 때
public class ScoreRecord extends Subject{
    private List<Integer> scores = new ArrayList<>(); // 점수를 저장함
    // 새로운 점수를 추가 (상태 변경)
    public void addScore(int score) {
        scores.add(score); // scores 목록에 주어진 점수를 추가함
        notifyObservers(); // scores가 변경됨을 각 통보 대상(Observer)에게 통보함
    }
    public List<Integer> getScoreRecord() { return scores; }
}
```

<br/>

- 변경 감시 대상 객체
- 성적이 추가되면 ```notifyObservers()```를 통해 통보 대상들에게 알림을 보낸다


<br/><br/>

**DataSheetView.class**

```java
/* 1. 출력형태: 목록 형태로 출력하는 클래스 */
public class DataSheetView implements Observer{
    private ScoreRecord scoreRecord;
    private int viewCount;

    public DataSheetView(ScoreRecord scoreRecord, int viewCount) {
        this.scoreRecord = scoreRecord;
        this.viewCount = viewCount;
    }

    // 점수의 변경을 통보받음
    public void update() {
        List<Integer> record = scoreRecord.getScoreRecord(); // 점수를 조회함
        displayScores(record, viewCount); // 조회된 점수를 viewCount 만큼만 출력함
    }

    private void displayScores(List<Integer> record, int viewCount) {
        System.out.println("List of " + viewCount + " entries: ");
        for (int i = 0; i < viewCount && i < record.size(); i++) {
            System.out.println(record.get(i) + " ");
        }
        System.out.println();
    }
}
```

<br/>


- ```update()```을 통해 통보를 받아 성적을 출력한다.


<br/><br/>

**MinMaxView.class**

```java
/* 2. 출력형태: 최소/최대 값만을 출력하는 형태의 클래스 */
public class MinMaxView implements Observer{
    private ScoreRecord scoreRecord;
    // getScoreRecord()를 호출하기 위해 ScoreRecord 객체를 인자로 받음
    public MinMaxView(ScoreRecord scoreRecord) {
        this.scoreRecord = scoreRecord;
    }
    // 점수의 변경을 통보받음
    public void update() {
        List<Integer> record = scoreRecord.getScoreRecord(); // 점수를 조회함
        displayScores(record); // 최소값과 최대값을 출력함
    }
    // 최소값과 최대값을 출력함
    private void displayScores(List<Integer> record) {
        int min = Collections.min(record, null);
        int max = Collections.max(record, null);
        System.out.println("Min: " + min + ", Max: " + max);
    }
}
```

<br/>

- ```update()```를 통해 통보받았을 때, 최대 최소값을 출력한다

<br/><br/>




**Main.class**

```java
    public static void main(String[] args) {
        ScoreRecord scoreRecord = new ScoreRecord();

        // 3개까지의 점수만 출력함
        DataSheetView dataSheetView = new DataSheetView(scoreRecord, 3);
        // 최대값, 최소값만 출력함
        MinMaxView minMaxView = new MinMaxView(scoreRecord);

        // 각 통보 대상 클래스를 Observer로 추가
        scoreRecord.attach(dataSheetView);
        scoreRecord.attach(minMaxView);

        // 10 20 30 40 50을 추가
        for (int index = 1; index <= 5; index++) {
            int score = index * 10;
            System.out.println();
            System.out.println("Adding " + score);
            // 추가할 때마다 최대 3개의 점수 목록과 최대/최소값이 출력됨
            scoreRecord.addScore(score);
        }
    }
```


<br/>

실행결과
```java
>>>Adding 10
>>>List of 3 entries: 
>>>10 

>>>Min: 10, Max: 10

>>>Adding 20
>>>List of 3 entries: 
>>>10 
>>>20 

>>>Min: 10, Max: 20

>>>Adding 30
>>>List of 3 entries: 
>>>10 
>>>20 
>>>30 

>>>Min: 10, Max: 30

>>>Adding 40
>>>List of 3 entries: 
>>>10 
>>>20 
>>>30 

>>>Min: 10, Max: 40

>>>Adding 50
>>>List of 3 entries: 
>>>10 
>>>20 
>>>30 

>>>Min: 10, Max: 50
```


<br/><br/>

## 장단점


- 장점
  - 실시간으로 한 객체의 변경사항을 다른 객체에 전파할 수 있다
  - 느슨한 결합으로 시스템이 유연하고 객체간의 의존성을 제거할 수 있다


- 단점
  - 너무 많이 사용하게 되면, 상태 관리가 힘들 수 있다 
  - 데이터 배분에 문제가 생기면 자칫 큰 문제로 이어질 수 있다
  - Thread safe 하지 않아 구독을 신청/취소하는 동안 원하는 결괏값을 얻기 힘들수도 있다
  - observer를 제때 제거해주지 않으면 메모리 누수가 일어날 수 있다
  - 비동기 방식이기 때문에 이벤트 구독을 원하는 순서대로 받지 못할 수 있다


<br/><br/><br/><br/>

---
참조:
- [[Design Pattern] 옵저버 패턴이란](https://gmlwjd9405.github.io/2018/07/08/observer-pattern.html)
- [[Design Pattern] 옵저버 패턴(Observer Pattern)에 대하여](https://coding-factory.tistory.com/710)
- [[Design Pattern] Observer 패턴](https://kscory.com/dev/design-pattern/observer)

