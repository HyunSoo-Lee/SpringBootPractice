---

# 목차

1. [개요](#개요)  
2. [기술 스택](#기술-스택)  
3. [Spring의 웹 계층](#spring의-웹-계층)  
4. [OAuth 로그인 기능 순서](#oauth-로그인-기능-순서)  
5. [MVC와 스프링 계층형 구조의 차이점](#mvc와-스프링-계층형-구조의-차이점)   
6. [Spring 중요 개념](#spring-중요-개념)  
7. [Kubernetes 기본 구조](#kubernetes-기본-구조)  

---

## 개요

**스프링부트**를 사용한 간단한 웹 애플리케이션을 개발하고 **도커**, **쿠버네티스**, 그리고 **AWS**를 통해 배포하는 연습을 위한 레포지토리

---

## 기술 스택

<div align=left>
   <img src="https://img.shields.io/badge/openjdk-000000?style=for-the-badge&logo=openjdk&logoColor=white">
   <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
   <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
   <img src="https://img.shields.io/badge/kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white">
   <img src="https://img.shields.io/badge/aws%20ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
   <br>

- **JDK 1.8.0**: 프로그래밍 언어
- **Spring Boot 2.1.9**: 웹 애플리케이션을 위한 프레임워크
- **롬복(Lombok)**: 자바에서 보일러플레이트 코드를 줄여주는 라이브러리
- **H2 데이터베이스**: 개발 및 테스트를 위한 인메모리 데이터베이스
- **Docker**: 컨테이너 플랫폼
- **Kubernetes**: 컨테이너 오케스트레이션
- **AWS (Amazon Web Services)**: 클라우드 호스팅 플랫폼

---

## Spring의 웹 계층

![Web Layer Diagram 1](https://github.com/user-attachments/assets/b57d26b2-f7da-4499-8b01-cfcce2f63efd)
![Web Layer Diagram 2](https://github.com/user-attachments/assets/fb1598e0-a064-48c9-818a-106872a01833)

1. **Web Layer**  
   컨트롤러(@Controller)와 JSP/Freemarker 등의 **뷰 템플릿 영역**. 이외에도 **필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice)** 등을 통해 **외부 요청과 응답**을 처리.

2. **Service Layer**  
   @Service에 사용되는 **서비스 영역**. 주로 **Controller와 Dao**의 중간에서 사용되며, **@Transactional**이 필요함.

3. **Repository Layer**  
   **DB에 접근하는 영역**, 흔히 **Dao 영역**이라고 부름.

4. **DTOs (Data Transfer Objects)**  
   **DTO들의 영역**. 예: 뷰 템플릿 엔진에서 사용되거나 Repository Layer에서 결과로 반환된 객체.

5. **Domain Model**  
   **도메인**은 개발 대상의 비즈니스 로직을 처리하는 영역으로, @Entity가 사용된 영역과 관련됨.

---

## OAuth 2.0의 동작 방식

![image](https://github.com/user-attachments/assets/0a4e87a6-012c-4692-8397-73445691798d)

1. **구성 요소**  
   **클라이언트(Client)**: 사용자를 대신해 자원에 접근하고자 하는 애플리케이션. (예: 스프링 부트 애플리케이션)   
   **자원 소유자(Resource Owner)**: 데이터나 자원을 소유하고 있는 사용자. (예: 사용자)   
   **인증 서버(Authorization Server)**: 사용자의 신원을 확인하고, 권한을 부여하여 토큰을 발급하는 서버. (구글의 OAuth 2.0 서버)   
   **자원 서버(Resource Server)**: 클라이언트가 접근하려는 자원(데이터나 서비스)이 위치한 서버. (구글의 사용자 데이터 서버)   

2. **흐름의 단계**  
   **단계 A: 권한 요청**   
   클라이언트는 자원 소유자(사용자)에게 **권한 요청**.   

   **단계 B: 권한 부여**   
   자원 소유자(사용자)가 허용하면, 클라이언트에게 권한 부여(authorization grant)를 제공.   
   **코드나 토큰의 형식**으로 해당 클라이언트가 권한을 가지고 있다는것을 입증    

   **단계 C: 권한 부여 전달**   
   클라이언트는 받은 **권한을 인증 서버에 전달**하여 접근 토큰을 요청.   
   이 단계에서는 클라이언트가 사용자로부터 권한을 받았는지 인증 서버가 검증.   

   **단계 D: 접근 토큰 발급**   
   인증 서버는 권한 부여를 확인한 후, **접근 토큰을 클라이언트에게 발급**.   
   이 토큰은 **클라이언트가 사용자를 대신해** 자원에 접근할 수 있게 해줌.   

   **단계 E: 자원 서버에 접근 토큰 전달**   
   클라이언트는 접근 토큰을 가지고 **자원 서버에 요청**을 보냄.   
   클라이언트는 이 접근 토큰을 사용하여 **특정 자원에 접근할 수 있는 권한이 있음**을 증명.   

   **단계 F: 보호된 자원 접근**   
   자원 서버는 **접근 토큰을 검증하고, 유효한 경우 보호된 자원(예: 사용자의 데이터)에 접근을 허용**.   
   최종적으로 클라이언트는 요청한 데이터나 서비스를 받아서 전체 흐름이 완료됩니다.   

---

## OAuth 로그인 기능 순서

1. **OAuth2 사용자 정보 수신**  
   `CustomOAuth2UserService`가 Google 또는 Naver와 같은 OAuth2 제공자로부터 사용자 정보를 수신.

2. **OAuthAttributes로 사용자 정보 가공**  
   사용자 정보를 **일관된 형식으로 가공**하고, 이를 **User 엔티티** 및 **SessionUser 객체**로 변환하여 DB와 세션 관리에 사용.

3. **User 엔티티로 DB 저장**  
   변환된 정보를 바탕으로 **User 엔티티 객체**를 생성하거나 업데이트.

4. **SessionUser로 세션에 저장**  
   **세션 관리**를 위해 직렬화 가능한 **SessionUser 객체**를 생성하여 세션에 저장.

5. **왜 두 가지로 나누어 처리하는가?**  
   **User 엔티티**는 복잡한 비즈니스 로직을 포함하므로 **직렬화에 따른 성능 문제**가 있을 수 있음. 이를 피하기 위해 **SessionUser** 객체를 사용.

---

## MVC와 스프링 계층형 구조의 차이점

### 1. MVC란?

**Spring MVC**는 Model-View-Controller (MVC) 디자인 패턴을 기반으로 하는 웹 애플리케이션 구조.  
웹 애플리케이션을 **모델(Model)**, **뷰(View)**, **컨트롤러(Controller)** 로 나눔

- **Model**: 애플리케이션의 **핵심 데이터**와 **비즈니스 로직**을 처리하며, 데이터베이스와 상호작용.
- **View**: 사용자에게 **화면을 출력**하는 역할. HTML, JSP, Thymeleaf 같은 템플릿 엔진을 사용.
- **Controller**: **사용자 요청을 처리**, 모델과 뷰 사이의 **중재자 역할**. 요청을 모델로 보내고, 처리된 데이터를 뷰로 전달.

### 2. 공통점

- **역할의 분리**  
  둘 다 **책임 분리**에 중점을 두며, **Separation of Concerns** 원칙 사용.  
  Spring MVC는 **모델, 뷰, 컨트롤러**로, 계층형 구조는 **Service Layer**와 **Repository Layer**로 나뉨.

- **요청 처리 흐름**  
  사용자 요청은 **Web Layer (Controller)** 에서 시작하여, **Service Layer**로 비즈니스 로직을 처리하고, **Repository Layer**에서 데이터를 주고받음. MVC의 **Controller → Model → View** 흐름과 유사

- **컨트롤러의 역할**  
  **Controller**가 사용자 요청을 처리하고, 비즈니스 로직으로 전달하는 **중재자 역할**을 수행.

### 3. 차이점

- **모델의 역할**  
  - **MVC**: 모델은 주로 뷰와 관련된 데이터를 처리, DTO 또는 엔티티 객체를 사용해 뷰로 데이터를 전달.  
  - **계층형 구조**: 모델은 비즈니스 로직과 데이터베이스 작업에 집중, **Repository Layer**에서 데이터 저장소와 상호작용.

- **비즈니스 로직 처리 위치**  
  - **MVC**: 비즈니스 로직이 일부 **컨트롤러**에서 처리됨.  
  - **계층형 구조**: 비즈니스 로직은 **Service Layer**에서만 처리되고, 컨트롤러는 요청만 처리.

- **구조의 세분화**  
  - **MVC**: 구조가 **Model, View, Controller**로 단순함!  
  - **계층형 구조**: **Web Layer, Service Layer, Repository Layer**로 더 세분화되며, 각 계층의 역할이 전문화됨.

- **데이터 처리의 범위**  
  - **MVC**: 모델은 주로 뷰로 전달될 데이터를 처리.  
  - **계층형 구조**: 모델은 **비즈니스 로직**과 **데이터베이스 작업**을 포함한 애플리케이션 상태 관리가 목적.

---

## Spring 중요 개념

1. **ORM (Object-Relational Mapping)**  
   객체와 DB 테이블 간의 관계를 자동으로 매핑해주는 기술. Hibernate, JPA 등이 대표적.

2. **Gradle 의존성 관리**  
   - `compile`: Gradle 3.4 이전 방식으로 모든 의존성이 **컴파일 및 런타임에 노출**.
   - `implementation`: 이후 권장 방식으로 **모듈 내부에서만 의존성 사용**.

3. **DTO (Data Transfer Object)**  
   계층 간 **데이터 전달용 객체**로, **Entity와 분리**되어 필요한 정보만 포함.

4. **DAO (Data Access Object)**  
   **DB와 상호작용**하는 객체로, CRUD 작업을 수행.

5. **더티 체킹 (Dirty Checking)**  
   JPA에서 **Entity의 변경 사항을 자동 감시**하고, 이를 DB에 반영.

6. **영속성 컨텍스트**  
   JPA에서 **Entity 객체를 관리**하는 일종의 캐시 역할을 하는 메모리 공간.

7. **JPA Auditing**  
   **Entity의 생성 및 수정 시점**을 기록하는 기능.

8. **Optional**  
   **null 값을 안전하게 처리**하는 도구로, null로 인한 오류를 방지.

---

## Kubernetes 기본 구조

![Kubernetes Diagram](https://github.com/user-attachments/assets/1c991f63-16c5-4b9a-8008-da253df91d1f)

1. **Pod**  
   가장 작은 실행 단위로, 실제 애플리케이션 컨테이너를 포함.

2. **Node**  
   Pod를 실행하는 가상 또는 물리 머신.

3. **Deployment**  
   Pod의 배포와 관리를 정의.

4. **etcd**  
   클러스터 내 모든 정보를 저장하는 저장소.

5. **API 서버**  
   Kubernetes의 중앙 통신 허브로, **사용자 요청을 받아 처리**하고 구성 요소에 전달.

6. **Controller Manager**  
   클러스터 관리에 필요한 **컨트롤러들을 관리**하고 **원하는 상태와 현재 상태의 일치**를 위해 동작.

7. **Kublet**  
   각 노드에서 **Pod를 실행하고 관리**하는 에이전트로, API에서 명령을 받아 Pod를 생성 및 모니터링.

---
