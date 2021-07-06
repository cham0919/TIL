# 인터프리터 패턴(Interpreter Pattern)


- 행위(Behavioral) 패턴
 


-  간단한 언어의 문법을 정의하고 해석하는 패턴

-  언어가 주어지면 해당 표현을 사용하여 언어로 문장을 해석하는 인터프리터를 사용하여 문법 표현을 정의하는 방법




<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/5f3c1161-fbdb-44bb-9a1e-479d36bb49c7/image.png)






<br/><br/>


## 구현




<br/>


**Expression.class**

```java
public interface Expression {
    boolean interpreter(String con);
}
```

- 규칙(rule)을 생성하고 표현을 파싱하는 것을 보이기 위한 Expression 선언


<br/><br/>


**TerminalExpression.class**

```java
public class TerminalExpression implements Expression {

    private String data;

    public TerminalExpression(String data){
        this.data = data;
    }

    @Override
    public boolean interpreter(String context) {

        if(context.contains(data)){
            return true;
        }
        return false;
    }
}
```

<br/>

**AndExpression.class**

```java
public class AndExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpreter(String context) {
        return expr1.interpreter(context) && expr2.interpreter(context);
    }
}
```

<br/>

**OrExpression.class**

```java
public class OrExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpreter(String context) {
        return expr1.interpreter(context) || expr2.interpreter(context);
    }
}

```

<br/>

- ```interpreter``` 구현체
- ```TerminalExpression```을 통해 데이터를 저장하고 각 구현체별로 정해진 기능을 사용



<br/><br/>


**Main.class**

```java
public class Main {

    public static Expression getMaleExpression(){
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    public static Expression getMarriedWomanExpression(){
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }


    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male? " + isMale.interpreter("John"));
        System.out.println("Julie is a married women? " + isMarriedWoman.interpreter("Married Julie"));
    }
}
```


<br/>

실행결과
```java
>>>John is male? true
>>>Julie is a married women? true
```

- 각 and, or를 통해 쉽게 기능 사용 가능

<br/><br/>




<br/><br/>

## 장단점


- 장점
  -  각 문법 규칙을 클래스로 표현하기 때문에 언어를 쉽게 구현할 수 있다
  - 문법이 클래스에 의해 표현되기 때문에 언어를 쉽게 변경하거나 확장할 수 있다
  -  클래스 구조에 메소드만 추가하면 프로그램을 해석하는 기본 기능 외에 보기 쉽게 출력하는 기능이나 더 나은 프로그램 확인 기능 같은 새로운 기능을 추가할 수 있다


- 단점
  - 문법 규칙의 개수가 많아지면 복잡해진다
  - 다양한 문법이 생성될때 성능 저하가 발생한다


<br/><br/><br/><br/>

---
참조:
- [[디자인 패턴] 인터프리터 패턴(Interpreter Pattern)](https://always-intern.tistory.com/11)
- [[디자인 패턴] 인터프리터 패턴](https://pacientes.github.io/posts/2020/12/design_pattern_interpreter/)
- [[행위 패턴] Interpreter pattern (인터프리터 패턴)](https://kunoo.tistory.com/entry/%ED%96%89%EC%9C%84-%ED%8C%A8%ED%84%B4-Interpreter-pattern-%EC%9D%B8%ED%84%B0%ED%94%84%EB%A6%AC%ED%84%B0-%ED%8C%A8%ED%84%B4)
- [[디자인패턴] Interpreter Pattern 인터프리터패턴](https://httt.tistory.com/2)
- [기타 여러가지 패턴들 정리](https://thefif19wlsvy.tistory.com/50)

