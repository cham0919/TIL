# 로깅


## logging 모듈

logging 모듈은 진단 정보를 기록하기 위한 표준 라이브러리 모듈이다. 

<br/><br/>

## 예외 되돌아보기



**fileparse.py**

```python
def parse(f, types=None, names=None, delimiter=None):
    records = []
    for line in f:
        line = line.strip()
        if not line: continue
        try:
            records.append(split(line,types,names,delimiter))
        except ValueError as e:
            print("Couldn't parse :", line)
            print("Reason :", e)
    return records
try-except 문에 집중하자. except 블록에서 무슨 일을 해야 할까?
```

경고 메시지를 출력해야한다면

```python
try:
    records.append(split(line,types,names,delimiter))
except ValueError as e:
    print("Couldn't parse :", line)
    print("Reason :", e)
```

경고 메시지 무시한다면

```python
try:
    records.append(split(line,types,names,delimiter))
except ValueError as e:
    pass
```

<br/><br/>

## logging 모듈 사용하기



**fileparse.py**

```python
import logging
log = logging.getLogger(__name__)

def parse(f,types=None,names=None,delimiter=None):
    ...
    try:
        records.append(split(line,types,names,delimiter))
    except ValueError as e:
        log.warning("Couldn't parse : %s", line)
        log.debug("Reason : %s", e)
```

경고 메시지 또는 특수한 Logger 객체를 발행하도록 코드를 수정한다. 

```logging.getLogger(__name__)```으로 생성한 것이다.

<br/><br/>

## 로깅 기초

로거 객체를 생성한다.

```python
log = logging.getLogger(name)   # name은 문자열이다
```

로그 메시지를 발행한다.

```python
log.critical(message [, args])
log.error(message [, args])
log.warning(message [, args])
log.info(message [, args])
log.debug(message [, args])
```

각 메시지는 심각도(severity) 수준이 각기 다르다.

이것들은 모두 포매팅된 로그 메시지를 생성한다. 
메시지를 생성하기 위해 args를 % 연산자와 함께 사용한다.

```python
logmsg = message % args # 로그에 기록됨
```

<br/><br/>

## 로깅 설정

로깅 작동을 각각 설정할 수 있다.

**main.py**

```python
...

if __name__ == '__main__':
    import logging
    logging.basicConfig(
        filename  = 'app.log',      # Log output file
        level     = logging.INFO,   # Output level
    )
```

일반적으로, 프로그램이 시작할 때 한 번만 설정한다. 설정은 logging을 호출하는 코드와 분리된다.

<br/><br/>

## 부연 설명

- 로깅은 설정할 수 있는 폭이 넓다. 
- 출력 파일, 수준, 메시지 포맷 등 모든 것을 설정할 수 있다.
- logging을 사용하는 코드는 그런 것에 신경쓰지 않아도 된다.


<br/><br/><br/>
---
참조 : 
[실용 파이썬 프로그래밍: 프로그래밍 유경험자를 위한 강의](https://wikidocs.net/84432)