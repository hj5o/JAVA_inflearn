## 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 - Inflearn 김영한님 <br>

### 스프링을 왜 쓸까?<br>
##### 객체지향적인 설계는 다형성 활용 가능
##### How?
##### SpringContainer가 지원해주고 dependencies injection 덕분에 굉장히 편리하기 때문
##### Application Assembly 코드만 만지면 나머지 실제 Application에 관련된 코드는 만질 필요가 없음 <br><br>
#### [1. 순수 Jdbc](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/main/java/hello/hellospring/Repository/JdbcMemberRepository.java) <br>
과거(20년 전) 개발자들이 Spring 없이 jdbc repository 구현 방법<br>

#### [2. Spring JdbcTemplate](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/main/java/hello/hellospring/Repository/JdbcTemplateMemberRepository.java) <br>
DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체 <br>
스프링의 DI를 사용하면 기존 코드를 전혀 손대지 않고, 설정만으로 구현 클래스를 변경 가능 <br>
#### Spring Bean을 등록하는 방법  
<li> @Component 사용
<li> @Configuration 선언된 클래스에 @Bean 등록

#### [3. JPA](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/main/java/hello/hellospring/Repository/JpaMemberRepository.java) <br>
객체 중심의 설계, ORM
JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환 가능 + 개발 생산성 ↑
기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행 * JPA를 통한 모든 변경은 Transaction 내에서 실행 // Service..(@Transactional)
  
#### [4. Spring Data JPA](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/main/java/hello/hellospring/Repository/SpringJpaMemberRepository.java) <br>
JPA를 편리하게 사용하도록 도와주는 기술 (Interface) <br>
JpaRepository를 상속받는데 Spring Data JPA가 Spring Bean에 구현체를 만들어서 자동으로 등록 (개발자가 구현체를 만들 필요 없음) <br>
JpaRepository에서 공통적인 Method제공 ( save(), findById(Long id), ... ) <br>
findbyName 같이 주관적인 코드는 createQuery로 직접 작성 (Interface를 통한 CRUD 가능) + QueryDSL lib를 통해 복잡한 동적 쿼리 가능<br>

#### [a. Spring Integration Test](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/test/java/hello/hellospring/Service/MemberServiceIntegrationTest.java)
<li> @SpringBootTest : 스프링 컨테이너와 테스크를 함께 실행한다. (진짜 Spring을 띄워서 테스트)
<li> @Transactional : 테케를 실행할 때 Transactional을 먼저 실행하고 DB의 데이터를 insert query하고 다 넣은 다음에 Test가 끝나면 Rollback <br> <br>
 원래 테스트에 사용된 데이터도 DB에 저장된다. 그래서 AfterEach로 초기화해주었는데, Transactional 어노테이션을 이용하면 그럴 필요가 없다.
<pre><code>@AfterEach
public void afterEach() {
    memberRepository.clearStore();
}</code></pre>
  
#### [b. AOP](https://github.com/hj5o/JAVA_inflearn/blob/master/%EC%8A%A4%ED%94%84%EB%A7%81%20%EC%9E%85%EB%AC%B8/hello-spring/src/main/java/hello/hellospring/AOP/TimeTraceAop.java) 
각 서비스 method가 작성된 상황에서 호출 시간을 측정하고 싶다면? <br>
이미 완성된 로직은 무수히 많은 상태, 하나하나 추가할 수는 있지만 시간이 너무 오래 걸린다. <br>
-> AOP class에 @Aspect 후 호출 시간 로직 구현 <br>
@Around를 이용해 타겟 범위를 정함 <br><br>
