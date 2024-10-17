![image](https://github.com/user-attachments/assets/df705cf1-acd3-45d6-9408-18bdd1bbbad7)![image](https://github.com/user-attachments/assets/5574b88c-c86a-4bdc-afa4-fb8a01527b43)# 스프링 부트 웹 서비스 프로젝트

## 개요

**스프링부트**를 사용한 간단한 웹 애플리케이션을 개발하고 
**도커**, **쿠버네티스**, 그리고 **AWS**를 통해 배포하는 연습을 위한 레포지토리

## 기술 스택
- **JDK 1.8.0**: 프로그래밍 언어
- **Spring Boot 2.1.9**: 웹 애플리케이션을 위한 프레임워크
- **롬복(Lombok)**: 자바에서 보일러플레이트 코드를 줄여주는 라이브러리
- **H2 데이터베이스**: 개발 및 테스트를 위한 인메모리 데이터베이스
- **Docker**: 컨테이너 플랫폼
- **Kubernetes**: 컨테이너 오케스트레이션
- **AWS (Amazon Web Services)**: 클라우드 호스팅 플랫폼

## 중요 개념에 대한 메모
1. **ORM**(Object-Relational Mapping) 이란??
   객체와 DB 테이블 사이의 관계를 자동으로 매핑해주는 기술!
   Hibernate, JPA 등이 여기에 속한다.
   SQL과 객체 지향 프로그래밍이 바라보는 다른 패러다임을 **일원화**시켜주는 기술이다.
2. **Gradle**과 의존성 관리
   compile : gradle 3.4 이전의 방식 - 모든 의존성이 **컴파일과 런타임에 노출**
   Implementation : 그 이후 권장되는 방식 **모듈 내부에만 의존성 사용 -> 빌드 속도와 유지보수성의 향상**
3. **DTO**(Data Transfer Object)
   계층간 데이터 전달용 객체
   **Entity**와 분리되어, 필요한 정보만 포함, **보안과 유연성**을 높인다.
4. **DAO**(Data Access Object)
   **DB와 상호작용**하는 객체, CRUD작업
   JpaRepository 인터페이스를 통해 기본적인 작업 제공!
   save(), findById(), deleteById() 등의 기본 메소드, 커스텀 쿼리 메소드 findBy~ 등도 있다!
   책에서는 **domain**이라는 표현과 패키지로 관리
   -> 사실은 다르다?!
   Domain 패키지와 DAO 패키지는 무엇이 다른가?
   DAO는 SQL 쿼리, 실행, 매핑을, 도메인 패키지는 비즈니스 로직과 객체의 상태를 관리한다.
6. DTO와 DAO의 **관계**는?
   DAO는 DB에서 직접 데이터를 가져오고, 저장하고, 수정하는 역할 **"즉, DB와의 상호작용"**
   DTO는 그 데이터를 가공해서 **서비스**, **컨트롤러** 계층에서 사용할 수 있도록 전달하는 역할 **"계층간 데이터를 전송"**
7. **더티 체킹**이란?
   JPA에서 Entity의 변경 사항을 자동으로 감시, 이를 DB에 자동 반영하는 메커니즘을 뜻한다.
   즉, Transaction 내에서 **엔티티 필드값을 수정**하면, 이를 자동으로 감지하여 **Transaction이 끝날때 수정한 값들을 DB에 업데이트**해준다.
   **영속성 컨텍스트** 내에서만 가능하다!
   **영속성 컨텍스트**란? JPA에서 엔티티 객체를 관리하는 일종의 캐시, 혹은 저장소를 말한다.
   즉, **Entity를 DB와 연결해둔 상태로 관리하는 메모리 공간**
