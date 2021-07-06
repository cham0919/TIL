# 디버깅

프로그램 충돌 상황

```python
bash % python3 blah.py
Traceback (most recent call last):
  File "blah.py", line 13, in ?
    foo()
  File "blah.py", line 10, in foo
    bar()
  File "blah.py", line 7, in bar
    spam()
  File "blah.py", 4, in spam
    line x.append(3)
AttributeError: 'int' object has no attribute 'append'
```

<br/><br/>

## 트레이스백 읽기

마지막 행이 문제를 일으킨 주범이다.

```python
bash % python3 blah.py
Traceback (most recent call last):
  File "blah.py", line 13, in ?
    foo()
  File "blah.py", line 10, in foo
    bar()
  File "blah.py", line 7, in bar
    spam()
  File "blah.py", 4, in spam
    line x.append(3)
```

<br/>

### 충돌 원인

```python
AttributeError: 'int' object has no attribute 'append'
```

- 읽고 이해하기가 쉽지 않다

<br/><br/>

## REPL 사용하기

-i 옵션을 사용해 스크립트를 사용할 때 파이썬이 살아있도록 한다.

```python
bash % python3 -i blah.py
Traceback (most recent call last):
  File "blah.py", line 13, in ?
    foo()
  File "blah.py", line 10, in foo
    bar()
  File "blah.py", line 7, in bar
    spam()
  File "blah.py", 4, in spam
    line x.append(3)
AttributeError: 'int' object has no attribute 'append'
>>>
```

이것은 인터프리터 상태를 유지한다.
충돌 이후에 여기저기 찔러볼 수 있다. 변숫값과 기타 상태를 확인한다.

<br/><br/>

## 프린트를 사용한 디버깅

repr()을 사용하라.

```python
def spam(x):
    print('DEBUG:', repr(x))
    ...
```

repr()은 값의 정확한 표현(representation)을 나타낸다. 
출력을 예쁘게 프린트하는 것과는 다르다.

```python
>>> from decimal import Decimal
>>> x = Decimal('3.4')
# `repr` 없이
>>> print(x)
3.4
# `repr` 사용
>>> print(repr(x))
Decimal('3.4')
>>>
```

<br/><br/>

## 파이썬 디버거

이 프로그램에서 디버거를 수작업으로 실행할 수 있다.

```python
def some_function():
    ...
    breakpoint()      # 디버거 진입(파이썬 3.7 이상)
    ...
```

이것은 breakpoint() 호출에서 디버거를 시작한다.

이전 버전에서는 방법이 약간 다르다. 다른 디버깅 안내서에서 다음과 같이 설명하는 것을 볼 수 있을 것이다.

```python
import pdb
...
pdb.set_trace()       # `breakpoint()` 대신 이것을 사용하라
...
```

<br/>

### 디버거에서 실행하기

전체 프로그램을 디버거에서 실행할 수도 있다.

```python
bash % python3 -m pdb someprogram.py
```

이렇게 하면 첫 번째 문장 이전에 디버거에 자동으로 진입한다. 
중단점(breakpoint)을 설정하고 구성을 바꿔볼 수 있다.

일반적인 디버거 명령:

```python
(Pdb) help            # 도움말
(Pdb) w(here)         # 스택 트레이스(stack trace)를 프린트
(Pdb) d(own)          # 한 스택 수준 아래로 이동
(Pdb) u(p)            # 한 스택 수준 위로 이동
(Pdb) b(reak) loc     # 중단점 설정
(Pdb) s(tep)          # 한 인스트럭션(instruction)을 실행
(Pdb) c(ontinue)      # 계속 실행
(Pdb) l(ist)          # 소스 코드 보기
(Pdb) a(rgs)          # 현재 함수의 인자를 프린트
(Pdb) !statement      # 문장(statement)을 실행
```

중단점 위치는 다음 중 하나다.

```python
(Pdb) b 45            # 현재 파일의 45행
(Pdb) b file.py:34    # file.py의 34행
(Pdb) b foo           # 현재 파일의 foo() 함수
(Pdb) b module.foo    # module의 foo()
```


<br/><br/><br/>
---
참조 : 
[실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84433)