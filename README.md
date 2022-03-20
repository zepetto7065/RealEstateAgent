# RealEstateAgent
부동산 중개 플랫폼

## 실행 방법
- 실행 Test는 postman 기준으로 작성하였습니다.

### 준비
해당 프로젝트 clone 후, local에서 기동해주시기 바랍니다. h2-console 주소는 http://localhost:8080/h2-console 이며 접속 정보도 같이 기재드립니다.
<img width="492" alt="스크린샷 2022-03-20 오후 5 42 36" src="https://user-images.githubusercontent.com/44112221/159154752-568cc3e3-8918-4417-9616-14ad71c96ae5.png">


### 1. 가입
<img width="635" alt="스크린샷 2022-03-20 오후 5 41 14" src="https://user-images.githubusercontent.com/44112221/159154717-8e83d673-ce42-4040-bfad-5b2a98d12bb9.png">

테스트 계정을 가입해주시기 바랍니다. 

### 2. 인증

가입한 계정을 JWT 인증하는 단계입니다.

<img width="818" alt="스크린샷 2022-03-20 오후 5 43 37" src="https://user-images.githubusercontent.com/44112221/159154793-af67fef3-6d64-43b1-8ee5-e2d2f64b130f.png">
<img width="737" alt="스크린샷 2022-03-20 오후 5 43 44" src="https://user-images.githubusercontent.com/44112221/159154799-4f970b33-c369-49b9-bf3c-c1c33d9e8d77.png">
 인증 후, POSTMAN에서 받은 인증 토큰을 계속 사용하기 위해 두번째 스크린샷과 같이 'Tests'탭에서 다음과 같이 설정 부탁드립니다.

<이하 해당 코드>
var jsonData = JSON.parse(responseBody)
pm.globals.set("jwt_token", jsonData.token);


### 3.. 등록/수정/삭제/조회
<img width="1090" alt="스크린샷 2022-03-20 오후 5 45 16" src="https://user-images.githubusercontent.com/44112221/159154838-b5103495-9be0-45f9-b27b-4a76bffbb562.png">

등록/수정/삭제/조회 프로세스는 모두 동일하게 위에서 global하게 적용한 Token을 Authorization 탭 > Type을 'Bearer Token' > Token은 2번 인증단계에서 지정한 변수명 {{jwt_token}}을 입력하여 주시면 , 2번 단계에서 받은 토큰이 API 통신간 적용이 되어 테스트가 가능합니다.

<이하 등록/수정/삭제/조회에 대한 url 및 파라미터 값을 스크린샷 찍어드립니다.>
#### 등록
<img width="647" alt="스크린샷 2022-03-20 오후 5 47 36" src="https://user-images.githubusercontent.com/44112221/159154910-ded49732-ecb5-417f-a541-99c423b31b1d.png">

#### 수정
<img width="621" alt="스크린샷 2022-03-20 오후 5 48 11" src="https://user-images.githubusercontent.com/44112221/159154929-921a3f19-904a-4e48-9a8a-c3f7a8570dbf.png">

#### 삭제
<img width="685" alt="스크린샷 2022-03-20 오후 5 48 32" src="https://user-images.githubusercontent.com/44112221/159154946-913e1373-725a-4a81-8d47-a7ebac6d83f8.png">

#### 조회
<img width="546" alt="스크린샷 2022-03-20 오후 6 05 19" src="https://user-images.githubusercontent.com/44112221/159155483-8a83ad65-26ad-473d-a0be-edd55cd26fb8.png">

#### 검색 조건
roomType (방 타입) : ONEROOM,TWOROOM,THREEROOM
payType (지불 방식) : M-월세 , Y-전세
minDeposit , maxDeposit : 가격단위는 보증금만 개발하였습니다.
