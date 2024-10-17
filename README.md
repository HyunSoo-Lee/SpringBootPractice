## 개요

**스프링부트**를 사용한 간단한 웹 애플리케이션을 개발하고 
**도커**, **쿠버네티스**, 그리고 **AWS**를 통해 배포하는 연습을 위한 레포지토리
<br/>
## 기술 스택
- **JDK 1.8.0**: 프로그래밍 언어
- **Spring Boot 2.1.9**: 웹 애플리케이션을 위한 프레임워크
- **롬복(Lombok)**: 자바에서 보일러플레이트 코드를 줄여주는 라이브러리
- **H2 데이터베이스**: 개발 및 테스트를 위한 인메모리 데이터베이스
- **Docker**: 컨테이너 플랫폼
- **Kubernetes**: 컨테이너 오케스트레이션
- **AWS (Amazon Web Services)**: 클라우드 호스팅 플랫폼
<br/><br/>
## Spring의 웹 계층

<br/>

![image](https://github.com/user-attachments/assets/b57d26b2-f7da-4499-8b01-cfcce2f63efd)

<br/>

1. **Web Layer**<br/>
   컨트롤러(@Controller)와 JSP/Freemarker 등의 **뷰 템플릿 영역**.<br/>
   이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 **외부 요청과 응답**에 대한 전반적인 영역.<br/><br/>
2. **Service Layer**<br/>
   @Service에 사용되는 **서비스 영역**.<br/>
   일반적으로 **Controller와 Dao의 중간**에서 사용.(@Transactional이 필요함.)<br/><br/>
3. **Repository Layer**
   **DB에 접근하는 영역**.<br/>
   **Dao 영역**이라고 생각해도 괜찮음.<br/><br/>
4. **Dtos**<br/>
   **DTO들의 영역**.<br/>
   Ex) 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등<br/><br/>
5. **Domain Model**<br/>
   **도메인이라 불리는 개발 대상**을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것.<br/>
   **비즈니스 로직을 처리하는 영역**.<br/>
   Ex) 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있음.<br/>
   @Entity가 사용된 영역 역시 도메인 모델이라고 이해할 수 있음.<br/>
   다만, 무조건 데이터베이스의 테이블과 관계가 있어야 하는 것은 아니다. VO처럼 값 객체들도 이 영역에 해당하기 때문이다.<br/><br/>


## Spring 중요 개념에 대한 메모
1. **ORM**(Object-Relational Mapping) 이란??<br/>
   객체와 DB 테이블 사이의 관계를 자동으로 매핑해주는 기술!<br/>
   Hibernate, JPA 등이 여기에 속한다.<br/>
   SQL과 객체 지향 프로그래밍이 바라보는 다른 패러다임을 **일원화**시켜주는 기술이다.<br/>
   
2. **Gradle**과 의존성 관리<br/>
   compile : gradle 3.4 이전의 방식 - 모든 의존성이 **컴파일과 런타임에 노출**<br/>
   Implementation : 그 이후 권장되는 방식 **모듈 내부에만 의존성 사용 -> 빌드 속도와 유지보수성의 향상**<br/>

3. **DTO**(Data Transfer Object)<br/>
   계층간 데이터 전달용 객체<br/>
   **Entity**와 분리되어, 필요한 정보만 포함, **보안과 유연성**을 높인다.<br/>

4. **DAO**(Data Access Object)<br/>
   **DB와 상호작용**하는 객체, CRUD작업<br/>
   JpaRepository 인터페이스를 통해 기본적인 작업 제공!<br/>
   save(), findById(), deleteById() 등의 기본 메소드, 커스텀 쿼리 메소드 findBy~ 등도 있다!<br/>
   책에서는 **domain**이라는 표현과 패키지로 관리<br/><br/>
   -> 사실은 다르다?!
   Domain 패키지와 DAO 패키지는 무엇이 다른가?<br/>
   DAO는 SQL 쿼리, 실행, 매핑을, 도메인 패키지는 비즈니스 로직과 객체의 상태를 관리한다.<br/>

5. DTO와 DAO의 **관계**는?<br/>
   DAO는 DB에서 직접 데이터를 가져오고, 저장하고, 수정하는 역할 **"즉, DB와의 상호작용"**<br/>
   DTO는 그 데이터를 가공해서 **서비스**, **컨트롤러** 계층에서 사용할 수 있도록 전달하는 역할 **"계층간 데이터를 전송"**<br/>

6. **더티 체킹**이란?<br/>
   JPA에서 Entity의 변경 사항을 자동으로 감시, 이를 DB에 자동 반영하는 메커니즘을 뜻한다.<br/>
   즉, Transaction 내에서 **엔티티 필드값을 수정**하면, 이를 자동으로 감지하여 **Transaction이 끝날때 수정한 값들을 DB에 업데이트**해준다.<br/>
   **영속성 컨텍스트** 내에서만 가능하다!<br/><br/>
   ->**영속성 컨텍스트**란? JPA에서 엔티티 객체를 관리하는 일종의 캐시, 혹은 저장소를 말한다.<br/>
   즉, **Entity를 DB와 연결해둔 상태로 관리하는 메모리 공간**<br/>
<br/>

## Kubernates의 기본 구조
<br/>

![image](https://github.com/user-attachments/assets/1c991f63-16c5-4b9a-8008-da253df91d1f)

<br/>

## K8s, Docker 중요 개념에 대한 메모
1. **Pod**<br/>
   가장 작은 실행 단위, 실제 애플리케이션의 컨테이너를 포함하고 있다.<br/>

2. **Node**<br/>
   Pod를 실행하는 가상(혹은 물리)머신 - 마스터/워커 노드!<br/>

3. **Deployment**<br/>
   Pod의 배포와 관리를 정의하며, 여러 Pod를 Replication하거나, Update한다.<br/>

4. **etcd**<br/>
   클러스터 내 모든 정보를 저장해두는 일종의 메모장<br/>

5. **API 서버**<br/>
   k8s의 중앙 통신 허브.<br/>
   클러스터와 상호작용 할 수 있는 진입점이고, **사용자의 요청 작업을 받아들이고, 처리**한다.<br/>
   작업에 필요한 **구성요소에 전달**하는 역할도 함<br/>

6. **Controller Manager**<br/>
   k8s 클러스터 관리에 필요한 컨트롤러들을 관리하는 역할!<br/>
   상태를 모니터링하고, **원하는 상태와 현재 상태**의 일치를 위해 동작한다.<br/>
   파드 개수의 유지, 유지보수를 위한 파드 위치 변경 등이 주요 예시<br/>

7. **Kublet**<br/>
   각 노드에서 실행되는 에이전트, 노드에서 실제로 파드를 실행하고 관리하는 역할<br/>
   **API에서 명령을 받아 노드 내에서 파드를 생성 및 모니터링하며, 이를 API에 다시 보고한다.**<br/>
