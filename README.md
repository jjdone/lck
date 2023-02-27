# Simple LCK Info

관리자 페이지 예시:

<img width="730" alt="스크린샷 2023-02-27 오후 3 33 57" src="https://user-images.githubusercontent.com/85011505/221493776-36998918-3413-430f-abc2-95165c2731df.png">

<img width="1229" alt="스크린샷 2023-02-27 오후 3 48 11" src="https://user-images.githubusercontent.com/85011505/221493727-f6471e26-3058-4a00-be5a-ad6b25ce0e1f.png">

1. 프로젝트 소개:

본인 취미를 즐기며 백엔드 프로젝트 경험을 쌓고자 간단한 LCK 대회 정보를 알려주는 서비스(백엔드 부분)를 개발하게 되었다.
간단한 정보 조회 기능을 위한 프로젝트이기 때문에 비교적 다양한 기능이 포함되어 있지 않다. 이 부분은 추후에 업데이트를 통해 추가할 생각이다.
이는 독학으로 관련 기술을 학습하며 본인 혼자서 진행한 프로젝트이다.

2. 개발 환경:
  * 개발 언어: Java 11
  * 프레임워크: Spring Boot 2.7.8
  * 빌드: Gradle 7.6
  * 데이터베이스: H2
  * JPA(Spring Data JPA)
  
3. 의존성:
  * Spring Web
  * Spring Boot Dev Tools
  * Lombok
  * Thymeleaf
  * Spring Data JPA
  * H2 Database

4. DB 설계:

![ERD drawio (2)](https://user-images.githubusercontent.com/85011505/220539778-16be999b-8421-422a-803b-73af6268f598.png)

5. 요구사항 분석:
  * 관리자 기능:
    * 팀: 팀 추가, 전체 팀 조회, 한 팀 상세 조회, 한 팀의 소속 선수 조회, 팀 점수 업데이트, 팀 순위 조회
    * 선수: 선수 추가, 전체 선수 조회, 한 선수 상세 조회, 선수 정보 업데이트, 선수 순위 조회
    * 경기: 경기 추가, 전체 경기 조회, 한 경기 상세 조회, 경기 점수 업데이트
  
  * 서비스 기능: 서비스 페이지는 개발 미정이며, 추후에 가능하다면 추가할 생각이다. 서비스 기능은 api로 정보를 넘겨주는 것으로 대체한다.

6. API 설계:

![화면 캡처 2023-02-22 165634](https://user-images.githubusercontent.com/85011505/220557748-d7039714-f286-4f18-aca9-c094152c7d9d.png)

---

마치며: ...
