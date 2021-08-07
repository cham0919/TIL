# 퍼사드 패턴(Facade Pattern)


- 구조(Structural) 패턴
 


-  서브시스템을 더 쉽게 사용할 수 있도록 higher-level 인터페이스를 정의하고, 제공하는 패턴

-  단순화된 인터페이스를 통해서 서브시스템을 더 쉽게 사용할 수 있도록 하기위한 용도로 쓰인다.





<br/><br/>

## 구조



![](https://images.velog.io/images/cham/post/4b22d9e8-1df0-42cd-965f-3bb9f1fddcc4/image.png)





<br/><br/>

## 구현


DataBase Connection을 통해 HTML 리포트 또는 PDF 리포트를 생성

<br/><br/>


**MySqlHelper.class**

```java
public class MySqlHelper {

    public static Connection getMySqlDBConnection(){
        // 실제 커넥션을 리턴해야 하지만, 예제이기에 null 을 리턴하겠습니다.
        return null;
    }

    public void generateMySqlPDFReport(String tableName, Connection con){
        // get data from table and generate pdf report
    }

    public void generateMySqlHTMLReport(String tableName, Connection con){
        // get data from table and generate pdf report
    }
}
```


<br/>


**OracleHelper.class**

```java
public class OracleHelper {

    public static Connection getOracleDBConnection(){
        // 실제 커넥션을 리턴해야 하지만, 예제이기에 null 을 리턴하겠습니다.
        return null;
    }

    public void generateOraclePDFReport(String tableName, Connection con){
        // get data from table and generate pdf report
    }

    public void generateOracleHTMLReport(String tableName, Connection con){
        // get data from table and generate pdf report
    }

}
```

- MySql과 Oracle Helper 생성
- Connection 생성과 PDF, HTML Report 생성을 담당한다


<br/><br/>


**HelperFacade.class**

```java
public class HelperFacade {
    
    public static void generateReport(DBTypes dbType, ReportTypes reportType, String tableName){
        Connection con = null;
        switch (dbType){
            case MYSQL:
                con = MySqlHelper.getMySqlDBConnection();
                MySqlHelper mySqlHelper = new MySqlHelper();
                switch(reportType){
                    case HTML:
                        mySqlHelper.generateMySqlHTMLReport(tableName, con);
                        break;
                    case PDF:
                        mySqlHelper.generateMySqlPDFReport(tableName, con);
                        break;
                }
                break;
            case ORACLE:
                con = OracleHelper.getOracleDBConnection();
                OracleHelper oracleHelper = new OracleHelper();
                switch(reportType){
                    case HTML:
                        oracleHelper.generateOracleHTMLReport(tableName, con);
                        break;
                    case PDF:
                        oracleHelper.generateOraclePDFReport(tableName, con);
                        break;
                }
                break;
        }

    }

    public static enum DBTypes{
        MYSQL,ORACLE;
    }

    public static enum ReportTypes{
        HTML,PDF;
    }
}
```


- Client가 호출할 Class부분. 
- Enum Type을 전달받아 해당하는 DB 벤더에 맞춰 기능을 동작한다

<br/><br/>


**Main.class**

```java
    public static void main(String[] args) {
        String tableName="Test";

        //generating MySql HTML report and Oracle PDF report using Facade
        HelperFacade.generateReport(HelperFacade.DBTypes.MYSQL, HelperFacade.ReportTypes.HTML, tableName);
        HelperFacade.generateReport(HelperFacade.DBTypes.ORACLE, HelperFacade.ReportTypes.PDF, tableName);
    }
```

- Client는 깔끔하게 다양한 db에 맞춰 요청할 수 있다


<br/><br/>

## 장단점


- 장점
  - 클라이언트에서는 다뤄야할 서브 시스템의 객체 수를 줄여준다
  - 클라이언트와 서브시스템간의 결합도를 낮춰준다
  


- 단점
  - 클라이언트에게 서브시스템까지 숨길 수는 없다


<br/><br/><br/><br/>

---
참조:
- [[구조 패턴] 퍼사드 패턴(Facade Pattern) 이해 및 예제](https://readystory.tistory.com/193)
- [[Design Pattern] 퍼사드 패턴 고찰](https://corock.tistory.com/374)
