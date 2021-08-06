

## 정렬


<br/>

### 정렬의 두가지 중요한 사용법
 - 탐색에서 보조로 사용
 - 리스트의 엔트리를 비교하는 방법으로 사용
 - 최적화, 그래프 이론, 잡 스케줄링 등
 
<br/>

### 정렬 방법
 - 내부 방법
    - 정렬할 리스트가 작아 전체적인 정렬이 메인 메모리 상에서 실행될 수 있을 때 사용
 - 외부 방법
    - 큰 리스트에 사용
 
 
 
<br/><br/>

## 삽입 정렬

- 새로운 레코드를 i개의 정렬된 레코드 리스트에 끼워 넣어 크기가 i+1인 정렬된 결과 레코드 리스트를 만든다

![](https://images.velog.io/images/cham/post/8260c3b6-2f68-4a30-a4dc-e72e63ac18ef/image.png)

![](https://images.velog.io/images/cham/post/95d8d156-a2a5-4d2b-a80c-c6571270f364/image.png)


- insert(e, a, i)는 최악의 경우 삽입 전 i+1번 비교해야함
   - insert 복잡도 : O(i)
- insertionSort의 복잡도
   - i = j-1 = 1,2,,,,n-1일 때 insert를 호출
   - 복잡도 : O(n<sup>2</sup>)


#### 삽입 정렬의 최악의 예

  - n = 5, 입력 키 순서 : 5, 4, 3, 2, 1
  - 입력 리스트가 역순이므로 새로운 레코드가 정렬된 부분에 삽입될 때마다 전체 정렬된 부분이 오른쪽으로 이동
  
  
  ![](https://images.velog.io/images/cham/post/9503df81-ad20-4864-9d1d-be4d423c6f32/image.png)
  
  
 - insertionSort는 안정적
 - 작은 n (ex - n <= 30)에 대해 가장 빠른 정렬 방법
  

  
<br/><br/>

## 퀵 정렬


- 평균 성능이 가장 좋은 정렬 방법

<br/>

#### 퀵 정렬 단계

  1) 정렬할 레코드 중 피벗(pivot) 레코드를 선택
  2) 정렬할 레코드들을 다시 정돈
    - 피벗의 왼쪽 : 피벗의 키보다 작거나 같은 레코드들을 위치
    - 피벗의 오른쪽 : 피벗의 키보다 크거나 같은 레코드들을 위치
  3) 퀵 정렬을 순환적으로 사용
    - 피벗의 왼쪽과 오른쪽의 레코드들은 서로 독립적으로 정렬


<br/>

ex)
**키 (26, 5, 37, 1, 61, 11, 59, 15, 48, 19)를 가진 10개의 레코드로 된 리스트를 퀵 정렬 사용하여 정렬**

![](https://images.velog.io/images/cham/post/f32e8b2f-9d05-43f7-9ef1-18a3b4f58d83/image.png)

- quickSort를 호출할 때마다의 리스트 상태
- 대괄호는 계속해서 정렬할 서브리스트를 나타냄


<br/>

#### quickSort 분석

  - 최악의 경우 복잡도 : O(n<sup>2</sup>)

<br/> 
 
- 한 레코드의 위치가 정확히 정해질 때마다 그 레코드의 왼쪽과 오른쪽의 서브리스트 크기가 같을 경우
  - 크기가 n/2인 두 개의 서브리스트를 정렬하는 작업과 동일
  - 크기가 n인 리스트에서 한 레코드를 위치시키는데 필요한 시간 : O(n)
  
<br/> 

- 평균 연산 시간 : O(nlogn)
  
  

<br/><br/>

## 합병 정렬


- 두개의 정렬된 리스트를 하나의 정렬된 리스트로 합병

<br/>

### 반복 합병 정렬(Iterative Merge Sort)

- 입력 리스트를 길이가 1인 n개의 정렬된 서브리스트로 간주
 
<br/>


- 반복 합병 정렬 단계
  - 첫번째 합병 단계 : 리스트들을 쌍으로 합병하여 크기가 2인 n/2개의 리스트를 얻는다
     - n이 홀수면 리스트 하나는 크기가 1
  - 두번째 합병 단계 : n/2개의 리스트를 다시 쌍으로 합병하여 n/4개의 리스트를 얻는다
  - 합병 단계는 하나의 서브 리스트가 남을 때까지 계속된다
     - 한번 합병할 때마다 서브 리스트의 수는 반으로 줄어든다
               
<br/>
 
ex)

 
**입력 리스트 : (26, 5, 77, 1, 61, 11, 59, 15, 48, 19)**

 
 
 ![](https://images.velog.io/images/cham/post/96d23d6b-3d2e-481e-9e93-a46080062bd9/image.png)

<br/>


#### mergeSort 분석

 - 합병의 각 단계에 걸리는 시간 : O(n)
 - 총 연산 시간 : O(nlogn)


<br/>

### 순환 합병 정렬 (Recursive Merge Sort)

 - 정렬할 리스트를 거의 똑같이 두 개로 나눈다
   - left 서브리스트와 right 서브리스트
 - 서브리스트들은 순환적으로 정렬
 - 정렬된 서브리스트들은 합병된다
 
 
<br/>

ex)
**입력 리스트 : (26, 5, 77, 1, 61, 11, 59, 15, 49, 19)**

![](https://images.velog.io/images/cham/post/24379864-4004-4ef5-8624-bf748d475431/image.png)

<br/>

#### 순환적 합병 정렬의 서브 리스트 분할

 - 두 개의 서브리스트를 만든다
   - left ~ [(left+right)/2], [(left+right)/2]+1 ~ right
   
 - 정수 배열 link[1:n]을 사용
   - 함수 merge가 사용될 때 일어나는 레코드 복사를 피하기 위해 정수 포인터를 각 레코드에 연관시키기 위함
   - list[i] - 정렬된 서브리스트에서 레코드 I 다음에 있는 레코드
   - 이 배열의 사용으로 레코드 복사는 링크 변경으로 대체되고 정렬 함수의 실행 시간은 레코드 크기 s에 독립적이 됨
   - 필요한 추가 공간 : O(n)
   - 최종 체인이 결정하는 정렬 순서로 레코드들을 물리적으로 재정돈해야하는 후처리 필요
   - 연산시간 : O(nlogn)


<br/><br/>


## 히프 정렬


- 합병 정렬의 문제점
  - 정렬한 레코드 수에 비례하여 저장 공간이 추가로 필요
  
<br/>

- 히프 정렬(heap sort)
  - 일정한 양의 저장 공간만 추가로 필요
  - 최악의 경우 연산 시간과 평균 연산 시간 : O(nlogn)
    - 합병 정렬보다 약간 느림
  - 최대-히프 구조 사용
    - 최대-히프와 연관된 삭제, 삽입 함수 : O(nlogn) 정렬 방법
    - adjust 함수 사용 - 최대 히프를 재조정

<br/>

- adjust 함수
  - 왼쪽 및 오른쪽 서브 트리가 모드 히프인 이진 트리에서 시작하여 이진 트리 전체가 최대 히프가 되도록 재조정
  - 연산 시간 : O(d) (d는 트리의 깊이)
  
  ![](https://images.velog.io/images/cham/post/27e0dd65-ef46-4d74-b2ff-993fa9d503c3/image.png)
  
  ![](https://images.velog.io/images/cham/post/dde7dc5f-2ff7-4db3-8bea-21bab3653bdf/image.png)
  
<br/>


#### 정렬 과정

- adjust를 반복적으로 호출 -> 최대 히프 구조를 만든다
- 히프의 첫번째 레코드와 마지막 레코드 교환
  - 최대 키를 가진 레코드가 정렬된 배열의 정확한 위치에 자리잡게 됨
- 히프의 크기를 줄인 후 히프를 재조정
- 전체 연산 시간 : O(nlogn)

<br/>


ex)
**이진 트리로 변환된 배열**

![](https://images.velog.io/images/cham/post/1d7ec105-57fe-4ab8-a351-c4dcb9b974be/image.png)

![](https://images.velog.io/images/cham/post/d06cb652-dc01-4548-b15c-a1a53e16232c/image.png)

![](https://images.velog.io/images/cham/post/99017993-b337-49a9-a9ae-38c0299b12da/image.png)

<br/><br/>


## 여러 키에 의한 정렬

- K<sup>1</sup>,K<sup>2</sup>,,,K<sup>t</sup>(K<sup>1</sup>은 최대 유효키, K<sup>t</sup>는 최소 유효키)의 여러개의 키를 갖는 레코드의 정렬
  - 모든 레코드 쌍 i, j에 대하여 
    - i < j, (K<sub>i</sub><sup>1</sup>,,,K<sub>i</sub><sup>t</sup>) <= (K<sub>j</sub><sup>1</sup>,,,K<sub>j</sub><sup>t</sup>) 이 성립하면 레코드 R<sub>1</sub>...R<sub>n</sub>의 리스트는 키 K<sub>1</sub>...K<sub>t</sub>
  
<br/>

- 카드 뭉치를 정렬하는 문제
  - 두 개의 키 (무늬, 숫자)에 대한 정렬 문제
    - K<sup>1</sup>[무늬] : ♣ < ◆ < ♥ < ♠
    - K<sup>2</sup>[숫자] : 2 < 3 < 4 <...< 10 < J < Q < K < A
  - 정렬된 카드 뭉치 -> 2♣,,,,A♣,,,,,,2♠,,,,,A♠


<br/>


#### MSD(most-significant-digit-first) 정렬

- 최대 유효 숫자 우선 정렬
  - 먼저 최대 유효 키 K<sup>1</sup>로 정렬 -> K<sup>1</sup>에 대해 같은 값을 가지는 여러 레코드 파일들이 만들어짐
  - 각 파일에 대해 독립적으로 K<sup>2</sup>로 정렬 -> K<sup>1</sup>, K<sup>2</sup>에 대해 같은 값을 가지는 서브 파일들이 만들어짐
  - 각 서브파일에 대해서는 K<sup>3</sup>으로 정렬
  - 최종적으로 이렇게 얻어진 파일들을 합친다
  
  
<br/>


#### LSD(least-significant-digit-first) 정렬

- 최소 유효 숫자 우선 정렬
  - 카드 숫자 값(키 K<sup>2</sup>)에 따라 13개 파일 만듦
  - 3들을 2들 위에, king들을 queen들 위에, ace들을 king들 위에 올려놓음
- 카드 뭉치를 뒤집어 놓고 안정된 정렬 방법을 이용하여 무늬(K<sup>1</sup>)에 따라 4개의 파일로 만듦
- 4개의 파일들은 키 K<sup>2</sup>에 따라 정렬되게 합침

<br/>

 - LSD가 MSD보다 더 단순
   - 생성된 파일과 서브 파일을 독립적으로 정렬할 필요가 없으므로 오버헤드가 적게 든다
   
<br/>


#### 기수(radix) 정렬

 - 어떤 기수 r을 이용하여 정렬 키를 몇 개의 숫자로 분해
    - r=10 : 키를 십진수로 분할
    - r=2 : 키를 이진수로 분할
 - 기수-r 정렬에서는 r개의 빈(bin)이 필요
    - 정렬되어야 하는 레코드가 R<sub>1</sub>,,,R<sub>n</sub>일 때, 레코드의 키는 기수-r을 이용하여 분할 -> 0~(r-1) 사이의 d자리 키가 된다
    - 각 빈의 레코드는 빈의 첫 레코드를 가리키는 포인터와 마지막 레코드를 가리키는 포인터를 통해 체인으로 연결되며, 체인은 큐처럼 동작

<br/>

ex)
**범위가 [0,999]인 십진수를 정렬 (d-3, r=10)**
 
![](https://images.velog.io/images/cham/post/80cc5062-9a2c-4ccc-9c9e-35aa09074d71/image.png)
![](https://images.velog.io/images/cham/post/3f07d95d-e2bd-4840-a2f3-03cd8346b2b5/image.png)
![](https://images.velog.io/images/cham/post/5543f185-7ab9-425d-acf6-75801758b97f/image.png)
   
   
<br/>

## 내부 정렬 요약

#### 내부 정렬 비교

 - 삽입 정렬 : 리스트가 부분적으로 정렬되어 있을 때 좋음. 작은 n에 대해 가장 좋은 정렬 방법
 - 합병 정렬 : 최악의 경우에 가장 좋은 방법. 히프 정렬에 비해 더 많은 공간을 필요로 함
 - 퀵 정렬 : 평균 성능이 가장 좋음 / 최악의 경우 : O(n<sup>2</sup>)
 - 기수 정렬 : 성능이 키의 크기와 r의 선택에 영향을 받음
 
 ![](https://images.velog.io/images/cham/post/6a217548-61c6-4d78-804c-739368dc5acc/image.png)
 
 
<br/><br/><br/><br/>
   
---
참조
- 고려대학교 김상곤 교수님 강의 중

