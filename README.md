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
- crud : notice를 중점으로


### 앞으로 해야할것들
- 로그인시 아이디 틀렸을때, 비밀번호 틀렸을때 각각 다른 커스텀 필터 처리, 다른 예외 던지기
- 모든 모듈이 루트 바로 아래에 있어서 보기 불편
- restdoc작성을 위해 만들었던 파일들의 경로, 디렉토리이름, 파일이름등이 좀 더 명확하게 수정될 필요
- aws에 ec2서버 올리기
- 개인블로그 운영에 필요한 api들을 api서버에 추가 [myblog](https://github.com/cocota93/myblog)
- redis
- oEmbed데이터 가져올때 별도의 dto만들어서 받기. 수신되는 데이터가 변경될수있으니까 그 여파를 기존 엔터티에 전달하지 않기위해
- oEmbed데이터 가져올때 요청경로가 하드코딩되어있음.
- '커맨드와 쿼리의 분리'를 어떤식으로 적용했는지에 대한 문서 필요  


### 하면 좋을것 같긴한데 중요하지는 않은것들 
- git-flow브런치(혼자하는데 굳이 필요할까?)
- aws s3(하게 되도 가장 후순위로?)
- jenkins(필요할까?)
- build-gradle파일 좀 더 깔끔하게정리할수 있을듯함
- 어드민에 커스텀 404에러페이지
