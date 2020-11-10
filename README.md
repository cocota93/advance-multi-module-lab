# advance-multi-module-lab


### 사용된 기술
- spring boot
- gradle
- JPA 
- jpa data 
- query dsl 
- Postgre sql 
- spring security form방식
- spring security jwt방식 
- swagger(springdoc openapi)

### 프로젝트 특징
- 기존에 진행하던 [multi-module-lab](https://github.com/cocota93/multi-module-lab) 프로젝트를 [멀티모듈 설계이야기 - 우아한형제들 기술블로그](https://woowabros.github.io/study/2019/07/01/multi-module.html)
를 바탕으로 새롭게 시작한 프로젝트 입니다.
- 어드민계정(Operator)과 일반 유저계정(Member)을 완전히 분리하여 진행
- 시간이 흘러 다운받아 실행했을때 문제없이 실행될수 있도록 개발환경에 민감한 주제는 제외(소셜로그인, 이미지 업로드, https, 메일인증)

### 멀티모듈 구조
```
application계층
  - blahblah-app-admin
  - blahblah-app-api

in system available계층
  - web-core

system domain계층
  - member-core
  - notice-core
  - operator-core
  
system core계층  
  - system-core
```
