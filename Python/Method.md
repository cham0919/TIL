## 특수 메서드


### 도입

앞에 __가 붙는다. (예: __init__)

```python
class Stock(object):
    def __init__(self):
        ...
    def __repr__(self):
        ...
```

특수 메서드는 많이 있지만, 그중 몇 가지만 예를 들어 보자.

<br/>

#### 문자열 변환을 위한 특수 메서드

객체에는 두 가지 문자열 표현이 있다.

```python
>>> from datetime import date
>>> d = date(2012, 12, 21)
>>> print(d)
2012-12-21
>>> d
datetime.date(2012, 12, 21)
>>>
```

str() 함수를 사용해 멋지게 프린트할 수 있는 출력을 생성한다.

```python
>>> str(d)
'2012-12-21'
>>>
```

repr() 함수는 개발자를 위한 상세한 표현에 사용된다.

```python
>>> repr(d)
'datetime.date(2012, 12, 21)'
>>>
```


```python
class Date(object):
    def __init__(self, year, month, day):
        self.year = year
        self.month = month
        self.day = day

    # Used with `str()`
    def __str__(self):
        return f'{self.year}-{self.month}-{self.day}'

    # `repr()`과 함께 사용
    def __repr__(self):
        return f'Date({self.year},{self.month},{self.day})'
```
<br/><br/>

### 수학을 위한 특수 메서드

다음과 같은 메서드가 수학 연산에 사용된다.

```python
a + b       a.__add__(b)
a - b       a.__sub__(b)
a * b       a.__mul__(b)
a / b       a.__truediv__(b)
a // b      a.__floordiv__(b)
a % b       a.__mod__(b)
a << b      a.__lshift__(b)
a >> b      a.__rshift__(b)
a & b       a.__and__(b)
a | b       a.__or__(b)
a ^ b       a.__xor__(b)
a ** b      a.__pow__(b)
-a          a.__neg__()
~a          a.__invert__()
abs(a)      a.__abs__()
```
<br/>

### 항목 접근을 위한 특수 메서드

컨테이너를 구현하는 데 다음과 같은 메서드를 사용한다.

```python
len(x)      x.__len__()
x[a]        x.__getitem__(a)
x[a] = v    x.__setitem__(a,v)
del x[a]    x.__delitem__(a)
```

이것들을 다음과 같이 클래스에서 사용할 수 있다.

```python
class Sequence:
    def __len__(self):
        ...
    def __getitem__(self,a):
        ...
    def __setitem__(self,a,v):
        ...
    def __delitem__(self,a):
        ...
```

<br/>


### 메서드 호출

메서드 호출은 두 단계 과정으로 이뤄진다.

- 메서드가 있는지 확인(lookup): . 연산자
- 메서드 호출: () 연산자

```python
>>> s = Stock('GOOG',100,490.10)
>>> c = s.cost  # 메서드 확인
>>> c
<bound method Stock.cost of <Stock object at 0x590d0>>
>>> c()         # 메서드 호출
49010.0
>>>
```

<br/>

### 바운드 메서드

함수 호출 연산자 ()에 의해 호출되지 않은 메서드를 바운드 메서드(bound method)라 한다. 바운드 메서드는 그것이 어느 인스턴스에서 왔는지를 반환한다.

```python
>>> s = Stock('GOOG', 100, 490.10)
>>> s
<Stock object at 0x590d0>
>>> c = s.cost
>>> c
<bound method Stock.cost of <Stock object at 0x590d0>>
>>> c()
49010.0
>>>
```

바운드 메서드는 부주의하고 명백하지 않은 오류의 원인이 되곤 한다. 예:

```python
>>> s = Stock('GOOG', 100, 490.10)
>>> print('Cost : %0.2f' % s.cost)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: float argument required
>>>
```

또는 디버깅하기 힘든 잘못된 행동을 한다.

```python
f = open(filename, 'w')
...
f.close     # 아차, 아무 일도 하지 않았다. `f`는 여전히 열려 있다.
```

두 경우 모두 괄호를 붙이는 것을 잊었기 때문에 오류가 발생했다. 예: s.cost() 또는 f.close().

<br/>

### 어트리뷰트 액세스

어트리뷰트에 액세스, 조작, 관리하는 또 다른 방법이 있다.

```python
getattr(obj, 'name')          # obj.name과 같음
setattr(obj, 'name', value)   # obj.name = value와 같음
delattr(obj, 'name')          # del obj.name과 같음
hasattr(obj, 'name')          # 어트리뷰트가 존재하는지 테스트
```

예:
```python
if hasattr(obj, 'x'):
    x = getattr(obj, 'x'):
else:
    x = None
```

참고: getattr()에도 유용한 기본값 arg가 있다.

```x = getattr(obj, 'x', None)```





<br/><br/><br/>
---
참조 : 
- [실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84383)
 