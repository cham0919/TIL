
## 개념


<br/>

**하나 이상의 노드(node)로 이루어진 유한집합**

### 특성

-  하나의 루트 노드
-  나머지 노드들은 n개의 분리집합으로 분할

### 용어

- 노드 : 한 정보의 아이템 + 다른 노드로 뻗어진 가지
- 노드의 차수(degree) : 노드의 서브트리 수
- 단말(리프) 노드 : 차수 = 0
- 비단말 노드 : 차수 != 0
- 자식 : 노드 X의 서브트리의 루트 (<-> 부모)
- 형제 : 부모가 같은 자식들
- 트리의 차수 = max{노드의 차수}
- 조상 : 루트까지의 경로상에 있는 모든 노드
- 노드 레벨 : 루트-레벨1, 자식 레벨=부모 레벨+1
- 트리의 높이(깊이) = max{노드 레벨}



![](https://images.velog.io/images/cham/post/68bbf23c-f513-4c1e-866c-6695fea4bca5/image.png)


<br/><br/>

## Node 구조


![](https://images.velog.io/images/cham/post/b5e43d31-6330-4717-bbd6-7533f89d8969/image.png)

- left child : 가장 왼쪽에 있는 child 포인터
- right sibling : 자신의 오른쪽 sibling에 대한 포인터

  
<br/><br/>

## 이진 트리(binary tree)


공백이거나 두 개의 분리된 이진 트리로 구성된 노드의 유한 집합


### 특성
- 한 노드는 최대 두 개의 가지
- 왼쪽 서브트리와 오른쪽 서브트리 구별
- 0개의 노드를 가질 수 있음

<br/>

### 이진 트리와 일반 트리의 차이점

 - 공백 이진 트리가 존재
 - 자식의 순서 구별
   - 하위의 트리는 서로 다른 두 이진 트리
   
   
![](https://images.velog.io/images/cham/post/c2a309c5-41d3-4e2a-9aa0-e604e0f0cda7/image.png)


<br/><br/>

## 이진 트리 성질

#### 최대 노드수
 - 레벨 i에서의 최대 노드 수 : 2<sup>i-1</sup>(i >= 1) 
 - 깊이가 k인 이진 트리가 가질 수 있는 최대 노드 수 : 2<sup>k-1</sup>(k >= 1) 


<br/>

 
 
#### 리프 노드 수와 차수가 2인 노드 수와의 관계

- n<sub>0</sub> = n<sub>2</sub> + 1
  - n<sub>0</sub> : 리프 노드 수
  - n<sub>2</sub> : 차수가 2인 노드 수

<br/>

 
#### 포화 이진 트리

- 깊이가 k이고, 노드수가 2<sub>k-1</sub>인 이진 트리
- 모든 리프 노드의 레벨이 동일하고 내부 노드들은 2개의 자식을 가짐
- 노드 번호 1~2<sub>k-1</sub>까지 순차적 부여 가능


![](https://images.velog.io/images/cham/post/ff5f5279-ae24-463b-bdc7-6f47f08e8d8c/image.png)


<br/>

 
#### 완전 이진 트리

- 깊이가 k이고, 노드수가 n인 이진 트리
- 각 노드들이 깊이가 k인 포화 이진 트리에서 1부터 n까지 번호를 붙인 노드와 1대 1로 일치
- n 노드 완전 이진 트리의 높이 : ***log<sub>2</sub>(n+1)***



<br/><br/>

## 이진 트리 표현


### 배열 표현
- 1차원 배열에 노드를 저장
- 완전 이진 트리 : 낭비되는 공간 없음
- 편향 트리 : 공간 낭비

ex) <br/>
![](https://images.velog.io/images/cham/post/954335f7-3ee9-4b1d-a004-9aa62a82e708/image.png)


### 연결 표현

- 노드 표현 <br/>
![](https://images.velog.io/images/cham/post/5c6d09ab-db20-4ed4-85e6-716b492a09d0/image.png)

- 부모 노드 알기 어려움
  - parent 필드 추가
  

ex) <br/>

![](https://images.velog.io/images/cham/post/10961339-7981-449b-afde-ff1bb16dfcd0/image.png)




<br/>

## 이진 트리 순회


### 트리 순회
 - 트리에 있는 모든 노드를 한 번씩만 방문
 - 순회 방법 : LVR, LRV, VLR, VRL, RVL, RLV
   - L : 왼쪽 이동, V : 노드 방문, R : 오른쪽 이동
 - 왼쪽을 오른쪽보다 먼저 방문(LR)
   - LVR : 중위 순회
   - VLR : 전위 순회
   - LRV : 후위 순회


![](https://images.velog.io/images/cham/post/8008665c-1f2c-48ac-bcd8-3c509d326656/image.png)


<br/>


### 중위 순회

- **LVR : A / B \* C \* D + E**


![](https://images.velog.io/images/cham/post/e0c41435-91c5-4b9b-8f02-ec31f5cab357/image.png)


<br/>


### 전위 순회

- **VLR : + \* \* / A B C D E**


<br/>


### 후위 순회

- **LRV : A B / C \* D \* E +**




<br/><br/>

## 스레드 이진 트리 


n 노드 이진 트리의 연결 표현 <br/>

 - 총 링크의 수 : 2n
 - 널 링크의 수 : n + 1
 

 
![](https://images.velog.io/images/cham/post/b9d6de68-18b3-473d-8e9a-45f9339d6929/image.png)

스레드 이진 트리는 널 링크 필드를 다른 노드를 가리키는 포인터로 대치


![](https://images.velog.io/images/cham/post/982a5e9b-6668-4ff1-a30c-994bb3493eca/image.png)





<br/><br/>

### 노드 구조

![](https://images.velog.io/images/cham/post/604062a0-04ee-491e-a5ae-39ee1d6a4c63/image.png)


- leftThread == false if leftChild가 포인터 <br/>
           　           　　　== true if leftChild가 스레드
- rightThread == false if rightChild가 포인터<br/>
           　           　　　== true if rightChild가 스레드



<br/>



<br/><br/>

## 최대 힙 트리



- 최대(최소)트리 : 각 노드의 키 값이 그 자식의 키 값보다 작지(크지) 않은 트리
- 최대히프 : 최대 트리이면서 완전 이진 트리
- 최소히프 : 최소 트리이면서 완전 이진 트리


![](https://images.velog.io/images/cham/post/77974c80-3ac6-4b7f-908c-30bff565c152/image.png)

※ Heap을 통해 우선순위 큐 구현 가능





<br/><br/>
## 이원 탐색 트리


이진트리로서 공백가능하고, 만약 공백이 아니라면
1. 모든 원소는 서로 상이한 키를 갖는다
2. 왼쪽 서브트리의 키들은 루트의 기보다 작다
3. 오른쪽 서브트리의 키들은 루트의 키보다 크다
4. 왼쪽과 오른쪽 서트트리도 이원 탐색 트리이다

![](https://images.velog.io/images/cham/post/f69182b9-c158-4afe-a958-46c7e1a27313/image.png)

<br/><br/>

### 삽입

- x의 key값을 가진 노드를 탐색
- 탐색이 성공하면 이 키에 연관된 원소를 변경
- 탐색이 실패하면 탐색이 끝난 지점에 쌍을 삽입

![](https://images.velog.io/images/cham/post/fc8ca217-76f3-42ab-af95-4e9e10ee6d26/image.png)


<br/><br/>

### 삭제

- 리프 원소의 삭제
  - 부모의 자식 필드에 0을 삽입. 삭제된 노드 반환
- 하나의 자식을 가진 비리프 노드의 삭제
  - 삭제된 노드의 자식을 삭제된 노드의 자리에 위치
  
![](https://images.velog.io/images/cham/post/65f887cd-a56f-42df-9cee-4df202af5ddc/image.png)

- 두 개의 자식을 가진 비리프 노드의 삭제
  - 왼쪽 서브트리에서 가장 큰 원소나 오른쪽 서브트리에서 가장 작은 원소로 대체
  - 대체된 서브트리에서 대체한 원소의 삭제 과정 진행
- 시간 복잡도 : **O(h)**




<br/><br/>

## 승자 트리


- 런
  - 하나의 순서 순차로 합병될 k개의 순서 순차
  - 각 런은 key 필드에 따라 비감소 순서로 정렬


- 각 노드가 두 개의 자식 노드 중 더 작은 노드를 나타내는 완전 이진 트리
- 루트 노드 : 트리에서 가장 작은 노드
- 리프 노드 : 각 런의 첫번째 레코드
- 이진 트리에 대한 순차 할당 기법으로 표현


ex) <br/>
K=8인 경우의 승자 트리

![](https://images.velog.io/images/cham/post/787db623-99a8-4074-ae93-78eb72ca854f/image.png)
- 승자 트리에서 한 레코드가 출력되고 나서 재구성
  - 새로 삽입된 노드에서 루트까지의 경로를 따라 토너먼트 재수행
  
  
  

![](https://images.velog.io/images/cham/post/880a083b-b810-48c7-9aad-ed71c1c6d6b5/image.png)



<br/><br/>

## 패자 트리


- 승자 트리의 문제점
  - 재구성할 때 루트까지의 경로를 따라 형제 노드들 사이에 토너먼트 발생
- 형제 노드는 전에 시행되었던 토너먼트의 패자들

- 패자 트리
  - 비리프 노드가 패자 레코드에 대한 포인터 유지
  - 전체 토너먼트의 승자를 표현하기 위한 노드 0 첨가
  - 토너먼트는 부모와 비교
  - 형제 노드에는 접근하지 않음

![](https://images.velog.io/images/cham/post/22ac79bb-7004-4c41-8e76-be58caf7f714/image.png)


<br/><br/>

## 포리스트 


- n개의 분리 트리들의 집합

![](https://images.velog.io/images/cham/post/8f88439f-8440-44a6-a846-a74283334b09/image.png)

<br/><br/><br/><br/>

---

블로그 : [[자료구조] 트리(Tree)](https://velog.io/@cham/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%ED%8A%B8%EB%A6%ACTree)


