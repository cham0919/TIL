## Class


### Class

class 문을 사용해 새로운 객체를 정의한다.

```python
class Player:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.health = 100

    def move(self, dx, dy):
        self.x += dx
        self.y += dy

    def damage(self, pts):
        self.health -= pts

```

<br/><br/>


### 인스턴스 데이터

인스턴스에는 자체 로컬 데이터가 있다.

```python
>>> a.x
2
>>> b.x
10
```

이 데이터는 __init__()에 의해 초기화된다.

```python
class Player:
    def __init__(self, x, y):
        # `self`에 저장된 값은 인스턴스 데이터다
        self.x = x
        self.y = y
        self.health = 100
```

어트리뷰트의 개수나 유형에는 제한이 없다.

<br/><br/>

### 인스턴스 메서드

인스턴스 메서드는 객체의 인스턴스에 적용되는 함수다.

```python
class Player:
    ...
    # `move`는 메서드다
    def move(self, dx, dy):
        self.x += dx
        self.y += dy
```

객체 그 자체가 항상 첫 번째 인자로 전달된다.

```def move(self, dx, dy):```
관례적으로 인스턴스를 self라고 한다. 그러나 실제로 어떤 이름을 쓰는지는 중요하지 않다. 객체는 항상 첫 번째 인자로 전달된다. 이 인자를 self라고 부르는 것은 파이썬 프로그래밍 스타일일 뿐이다.

<br/>
<br/>

### 클래스 스코핑

클래스는 이름의 스코프를 정의하지 않는다.

```python
class Player:
    ...
    def move(self, dx, dy):
        self.x += dx
        self.y += dy

    def left(self, amt):
        move(-amt, 0)       # NO. 글로벌 `move` 함수를 호출
        self.move(-amt, 0)  # YES. 위에 정의한 `move` 메서드를 호출.
```

인스턴스에 대한 연산을 하고 싶으면, 항상 명시적으로 참조한다(예: self).



<br/><br/><br/>
---
참조 : 
- [실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84383)
 