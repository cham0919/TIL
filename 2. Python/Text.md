## 문자열


리터럴 텍스트(Literal Text) 표현
프로그램에서 문자열 리터럴은 따옴표를 사용한다.

### 작은따옴표
```python
a = 'test용 문자열입니다'
```

### 큰따옴표
```python
b = "computer says no"
```

### 따옴표 세 개
```python
c = '''
Look into my eyes, look into my eyes, the eyes, the eyes, the eyes,
not around the eyes,
don't look around the eyes,
look into my eyes, you're under.
'''
```

 - 따옴표 세 개를 사용하면 모든 포매팅을 포함해 여러 행에 걸친 텍스트가 문자열이 된다.
 - 작은따옴표(')와 큰따옴표(")사이에 차이는 없다. 

<br/>

### 문자열 이스케이프 코드(escape code)

이스케이프 코드는 제어 문자나 키보드에서 타이핑하기 힘든 문자를 나타내는 데 사용한다.
 
이스케이프 코드의 예:
```
'\n'      라인 피드(Line feed)
'\r'      캐리지 리턴(Carriage return)
'\t'      탭(Tab)
'\''      작은따옴표(Literal single quote)
'\"'      큰따옴표(Literal double quote)
'\\'      백슬래시(Literal backslash)
```
<br/>

### 문자열 표현

문자열의 각 문자는 내부적으로 유니코드(Unicode) "코드 포인트(code-point)"로 저장된다. 다음과 같이 이스케이프 시퀀스(escape sequence)를 사용해 정확한 코드 포인트를 지정할 수 있다.

```python
a = '\xf1'          # a = 'ñ'
b = '\u2200'        # b = '∀'
c = '\U0001D122'    # c = '𝄢'
d = '\N{FOR ALL}'   # d = '∀'
```

사용가능한 전체 캐릭터 코드는 유니코드 문자 데이터베이스(Unicode Character Database)를 참조한다.

<br/>

### 문자열 인덱싱(String Indexing)

문자열은 문자에 액세스함에 있어서 배열(array)과 비슷하게 작동한다. 0부터 시작하는 정수 인덱스(index)를 사용한다. 음수 인덱스는 문자열의 끝에서부터 상대적 위치를 가리킨다.

```python
a = 'Hello world'
b = a[0]          # 'H'
c = a[4]          # 'o'
d = a[-1]         # 'd'(문자열의 끝)
```

<br/>

또한 콜론(:)으로 인덱스 범위를 지정함으로써 슬라이스하거나 부분 문자열을 선택할 수 있다.

```python
d = a[:5]     # 'Hello'
e = a[6:]     # 'world'
f = a[3:8]    # 'lo wo'
g = a[-5:]    # 'world'
```

끝 인덱스의 문자는 포함되지 않는다. 인덱스를 생략하면 문자열의 시작 또는 끝으로 가정한다.

<br/>

### 문자열 연산

이어붙이기(concatenation), 길이(length), 멤버십(membership), 복제(replication)

<br/>

#### 이어붙이기(+)

```python
a = 'Hello' + 'World'   # 'HelloWorld'
b = 'Say ' + a          # 'Say HelloWorld'
```

<br/>

#### 길이(len)

```python
s = 'Hello'
len(s)                  # 5
```

<br/>

#### 멤버십 테스트(`in`, `not in`)

```python
t = 'e' in s            # True
f = 'x' in s            # False
g = 'hi' not in s       # True
```

<br/>

### 복제(s * n)

```python
rep = s * 5             # 'HelloHelloHelloHelloHello'
```

<br/>

### 문자열 메서드

문자열에는 문자열 데이터에 대해 다양한 연산을 수행하는 메서드가 있다.

예: 맨 앞이나 맨 뒤의 화이트 스페이스 제거.
```python
s = '  Hello '
t = s.strip()     # 'Hello'
```

<br/>

예: 대소문자 변환

```python
s = 'Hello'
l = s.lower()     # 'hello'
u = s.upper()     # 'HELLO'
```

<br/>

예: 텍스트 교체.

```python
s = 'Hello world'
t = s.replace('Hello' , 'Hallo')   # 'Hallo world'
```

그외의 문자열 메서드

```python
s.endswith(suffix)     # 문자열이 suffix로 끝나는지 확인
s.find(t)              # s에서 t가 처음 나타나는 곳
s.index(t)             # s에서 t가 처음 나타나는 곳
s.isalpha()            # 문자가 영문자인지 여부
s.isdigit()            # 문자가 숫자인지 여부
s.islower()            # 문자가 소문자인지 여부
s.isupper()            # 문자가 대문자인지 여부
s.join(slist)          # s를 구분자(delimiter)로 삼아 문자열의 리스트를 붙이기(join)
s.lower()              # 소문자로 변환
s.replace(old,new)     # 텍스트 교체
s.rfind(t)             # 문자열의 끝에서부터 t를 검색
s.rindex(t)            # 문자열의 끝에서부터 t를 검색
s.split([구분자])       # 문자열을 분할해 부분 문자열의 리스트를 만듦
s.startswith(prefix)   # 문자열이 prefix로 시작하는지 확인
s.strip()              # 앞뒤의 공백을 제거
s.upper()              # 대문자로 변환
```

<br/>

### 문자열의 변경가능성(Mutability)

문자열은 "변경불가능(immutable)", 즉 읽기 전용이다. 한번 생성하면 값이 바뀌지 않는다.

```python
>>> s = 'Hello World'
>>> s[1] = 'a'
Traceback (most recent call last):
File "<stdin>", line 1, in <module>
TypeError: 'str' object does not support item assignment
>>>
```

>문자열 데이터를 조작하는 모든 연산과 메서드는 항상 새로운 문자열을 생성한다.

<br/>

### 문자열 변환

다른 타입의 값을 문자열로 변환하려면 str()을 사용한다. 그 결과로 print() 문이 생성하는 것과 같은 텍스트가 반환된다.

```python
>>> x = 42
>>> str(x)
'42'
>>>
```

<br/>

### 바이트 열(Byte String)

8비트 바이트가 끈(string)처럼 이어진 것으로 일반적으로 저수준 입출력(I/O)에 사용되며, 다음과 같이 쓴다.

```python
data = b'Hello World\r\n'
```

첫 번째 따옴표 바로 앞에 소문자 b를 붙이면, 텍스트 열(text string)이 아닌 바이트 열로 지정된다.

일반적인 문자열 연산은 대부분 작동한다.

```python
len(data)                         # 13
data[0:5]                         # b'Hello'
data.replace(b'Hello', b'Cruel')  # b'Cruel World\r\n'
```

인덱싱은 좀 다르게 작동하는데, 바이트 값을 정수로 반환하기 때문이다.

```python
data[0]   # 72 ('H'의 ASCII 코드)
```

<br/>

### 텍스트 열과의 변환.

```python
text = data.decode('utf-8') # 바이트 열 -> 텍스트 열
data = text.encode('utf-8') # 텍스트 열 -> 바이트 열
```

'utf-8' 인자(argument)는 문자 인코딩을 지정한다. 'ascii'와 'latin1'도 일반적으로 사용하는 값이다.

<br/>

### 원시 문자열(Raw String)

원시 문자열은 백슬래시를 해석하지 않는 문자열 리터럴이다. 소문자 "r"을 앞에 붙여 원시 문자열임을 나타낸다.

```python
>>> rs = r'c:\newdata\test' # 원시(백슬래시를 해석하지 않음)
>>> rs
'c:\\newdata\\test'
```

문자열은 입력한 그대로의 리터럴 텍스트다. 백슬래시가 특별히 중요할 때 이것을 사용하면 편리하다. 예: 파일명, 정규 표현식(regular expression) 등

<br/>

### f 문자열(f-String)

포매팅된 표현식 대체가 있는 문자열이다.

다음과 같이 문자열 앞에 f 접두사를 붙이면 f 문자열 포매팅 기능을 사용할 수 있다.

```python
>>> name = '홍길동'
>>> age = 30
>>> f'나의 이름은 {name}입니다. 나이는 {age}입니다.'
'나의 이름은 홍길동입니다. 나이는 30입니다.'
```

<br/>

또한 f 문자열 포매팅은 표현식을 지원하기 때문에 다음과 같은 것도 가능하다.

```python
>>> age = 30
>>> f'나는 내년이면 {age+1}살이 된다.'
'나는 내년이면 31살이 된다.'
```

<br/>

딕셔너리는 f 문자열 포매팅에서 다음과 같이 사용할 수 있다.

```python
>>> d = {'name':'홍길동', 'age':30}
>>> f'나의 이름은 {d["name"]}입니다. 나이는 {d["age"]}입니다.'
'나의 이름은 홍길동입니다. 나이는 30입니다.'
```

<br/>

정렬은 다음과 같이 할 수 있다.

```python
>>> f'{"hi":<10}'  # 왼쪽 정렬
'hi        '
>>> f'{"hi":>10}'  # 오른쪽 정렬
'        hi'
>>> f'{"hi":^10}'  # 가운데 정렬
'    hi    '
```
<br/>

공백 채우기는 다음과 같이 할 수 있다.

```python
>>> f'{"hi":=^10}'  # 가운데 정렬하고 '=' 문자로 공백 채우기
'====hi===='
>>> f'{"hi":!<10}'  # 왼쪽 정렬하고 '!' 문자로 공백 채우기
'hi!!!!!!!!'
```

<br/>

소수점은 다음과 같이 표현할 수 있다.

```python
>>> y = 3.42134234
>>> f'{y:0.4f}'  # 소수점 4자리까지만 표현
'3.4213'
>>> f'{y:10.4f}'  # 소수점 4자리까지 표현하고 총 자리수를 10으로 맞춤
'    3.4213'
```

<br/>

f 문자열에서 { } 문자를 표시하려면 다음과 같이 두 개를 동시에 사용해야 한다.

```python
>>> f'{{ and }}'
'{ and }'
```

참고: f 문자열은 파이썬 3.6 이상에서 지원한다.

<br/><br/><br/><br/>
---
참조: 
- [실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84383)
- [점프 투 파이썬](https://wikidocs.net/13#_14) 
