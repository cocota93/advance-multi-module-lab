# advance-multi-module-lab [![Build Status](https://travis-ci.org/cocota93/advance-multi-module-lab.svg?branch=master)](https://travis-ci.org/cocota93/advance-multi-module-lab)


### 사용된 기술
- spring boot
- gradle
- JPA 
- Jpa data 
- QueryDsl 
- Postgre sql 
- spring security form방식 - 어드민서버 적용
- spring security jwt방식 - api서버 적용
- swagger(springdoc openapi) - 어드민서버 적용
- restdoc - api서버 적용

### 프로젝트 특징
- 기존에 진행하던 [multi-module-lab](https://github.com/cocota93/multi-module-lab) 프로젝트를 [멀티모듈 설계이야기 - 우아한형제들 기술블로그](https://woowabros.github.io/study/2019/07/01/multi-module.html)
를 바탕으로 새롭게 시작한 프로젝트 입니다.
- 어드민계정(Operator)과 일반 유저계정(Member)을 완전히 분리하여 진행
- 시간이 흘러 다운받아 실행했을때 문제없이 실행될수 있도록 개발환경에 민감한 주제는 제외(이미지 업로드, 메일인증)

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

### 코드를 보는 방법
- spring security form : Operator를 중점으로
- spring security jwt : Member를 중점으로
- crud : Notice를 중점으로
- restdoc : api서버 기동후 [restdoc구경하기](http://localhost:8081/docs/index.html) 클릭 (에러페이지 나올경우 프로젝트 폴더 최상단에서 터미널을 열고 'gradlew build' or 'gradle build' 를 수행해주세요)