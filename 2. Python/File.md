## 파일 관리


### 파일 입력과 출력

파일을 연다.

```python
f = open('foo.txt', 'rt')     # 읽기를 위해 열기(텍스트)
g = open('bar.txt', 'wt')     # 쓰기를 위해 열기(텍스트)
```

모든 데이터를 읽는다.

```python
data = f.read()
```


### 'maxbytes' 바이트까지만 읽음

```python
data = f.read([maxbytes])
```

텍스트를 기록한다.

```python
g.write('some text')
```

마쳤으면 파일을 닫는다.

```python
f.close()
g.close()
```

파일을 열었으면 제대로 닫아야 하는데, 이 단계를 잊어버리기 쉽다. 따라서, 다음과 같이 with 문을 사용하면 좋다.

```python
with open(filename, 'rt') as file:
    # `file` 파일을 사용
    ...
    # 명시적으로 닫지 않아도 된다
```

이렇게 하면 들여쓴 코드 블록에서 벗어날 때 파일이 자동으로 닫힌다.

<br/>

### 파일 데이터를 읽는 일반적인 방법

파일 전체를 한번에 읽어 문자열로 처리한다.

```python
with open('foo.txt', 'rt') as file:
    data = file.read()
    # `data`는 `foo.txt`의 텍스트 전체로 된 문자열이다
```

파일을 한 행씩 읽어 내려가기.

```python
with open(filename, 'rt') as file:
    for line in file:
        # 행을 처리
```

<br/>

### 파일에 쓰는 일반적인 방법

문자열 데이터를 기록한다.

```python
with open('outfile', 'wt') as out:
    out.write('Hello World\n')
    ...
```

print 함수의 출력을 재지정(redirect)한다.

```python
with open('outfile', 'wt') as out:
    print('Hello World', file=out)
    ...
```


<br/><br/><br/>
---
참조 : 
[실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84383)