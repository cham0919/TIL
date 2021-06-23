## collections 모듈



## Counter

#### 배열 안의 값들을 합산할 때

```python
portfolio = [
    ('GOOG', 100, 490.1),
    ('IBM', 50, 91.1),
    ('CAT', 150, 83.44),
    ('IBM', 100, 45.23),
    ('GOOG', 75, 572.45),
    ('AA', 50, 23.15)
]
```

```python
from collections import Counter

total_shares = Counter()
for name, shares, price in portfolio:
    total_shares[name] += shares

total_shares['IBM']     # 150
```

<br/>

#### 배열을 합산할 때


```python
dict1 = {'banana': 1}
dict2 = {'apple': 2}

counter = ChainMap(dict1, dict2)
print(counter) # ChainMap({'banana': 1}, {'apple': 2})
print(counter['apple']) # 2

```


<br/>

#### 배열 안의 요소들 카운트할 때

```python
text = list("gallend")
c = Counter(text)
print(c)
>>Counter({'l': 2, 'g': 1, 'a': 1, 'e': 1, 'n': 1, 'd': 1})
print(c['a'])
>>1
```

<br/>

#### 배열을 연산할 때


```python
c = Counter(a=4, b=2)
d = Counter(a=2, b=3)
print(c-d) # Counter({'a': 2})
c.subtract(d)
print(c) # Counter({'a': 2, 'b': -1})
```


- 여러 계산 방법

|연산자|내용|
|------|---
|+=|더한다
|-=|뺀다. 결과가 음수면 그 요소는 삭제된다.
|&=|key의 요소를 삭제한다. 요소의 값은 둘 중 작은 쪽의 값이 된다.
|&#124;=|2개의 Counter 객체 전체의 요소로부터 새롭게 Counter 객체를 생성한다. <br/> key가 같으면 두 값 중 큰 쪽의 값이 된다.

위 누계 연산자에서 =를 빼고 +, -, &, | 만 사용할 경우 이항 연산자로 작용한다.

         
         

<br/>

## defaultdict

 하나의 키를 여러 개의 값에 매핑하려고 할때

```python
portfolio = [
    ('GOOG', 100, 490.1),
    ('IBM', 50, 91.1),
    ('CAT', 150, 83.44),
    ('IBM', 100, 45.23),
    ('GOOG', 75, 572.45),
    ('AA', 50, 23.15)
]
```

앞의 예와 같이, IBM을 키로 삼으면 두 개의 튜플이 있다.

#### defaultdict 사용

```python
from collections import defaultdict
holdings = defaultdict(list)
for name, shares, price in portfolio:
    holdings[name].append((shares, price))
holdings['IBM'] # [ (50, 91.1), (100, 45.23) ]
```

defaultdict을 사용하면 키에 액세스할 때마다 기본값을 얻는다.

#### defaultdict 기본값 사용

```python
total_shares = defaultdict(lambda :0)
print(total_shares['dw']) # 0
```

기본 default값을 설정할 수 있다


## deque

 마지막 N개의 이력(history)을 유지할 때

deque 사용

```python
from collections import deque

history = deque(maxlen=N)
with open(filename) as f:
    for line in f:
        history.append(line)
        ...
```

<br/>

## namedtuple


#### 튜플형태로 데이터 구조 저장할 때

```python
Student = namedtuple('Student', ['name', 'age'])

a = Student('cham', age=27)
print(a) # Student(name='cham', age=27)

print(a.name) # cham    
print(a.age) # 27
```






<br/><br/><br/>
---
참조 : 
- [실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84383)
- [collections 모듈](https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ouo7581&logNo=221543909505)
- [파이썬 collections, heapq 모듈 설명](https://greeksharifa.github.io/%ED%8C%8C%EC%9D%B4%EC%8D%AC/2020/01/10/collections-heapq/)