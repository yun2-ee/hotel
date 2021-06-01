# hotel

> 개인이 운영하는 반려견 호텔 예약 서비스를 제공하는 사이트입니다. 

### 프로젝트 개요

반려견 호텔 예약 기능 제공 <br>
호텔 가격 객실 정보 제공<br>
반려견 사진, 글 작성 게시판 제공 <br>
QnA , 후기 작성 게시판 제공 <br>

#### 개발기간
2021.02.10 - 2021.03.17
#### 개발인원 
4명<br>
나의 역할 <br>
예약 기능 추가 
후기게시판 제공 
#### 개발환경
| 종류 | 내용
|----| ----- | 
| HardWare | Window 64비트 운영체제, x64 기반 프로세서
| 개발언어 | Java,JSP/Servlet|
| front | HTML5, CSS3, JavaScript, jQuery
| 개발툴 | Eclipse 2019.09(4.13) Enterprise|
| DBMS | Oracle 11.2.0.2.0|
| 웹서버 | Tomcat 8.5 |
| Browser| Chrome 90.0.4430.212

#### DB 설계
![db](https://user-images.githubusercontent.com/85010698/120094542-e601e280-c15b-11eb-872d-d72a8966c51b.png)


#### 프로젝트 내용
![화면구조](https://user-images.githubusercontent.com/85010698/120280974-598a2800-c2f3-11eb-8ee6-930281168b94.png)
-<jsp:include page="" flush="false"/> 를 사용해서 
-header,menu,footer 는 모든 view 파일에 들어가고, main 내용만 다릅니다. 

###### 메인화면 <br>
<hr/>

![seadog-main](https://user-images.githubusercontent.com/85010698/120109573-f68a7b00-c1a4-11eb-8baf-4a6d8676ee52.gif)

<br/>

&nbsp; 로그인한 회원이 관리자일 경우, 상단 메뉴바에 '관리' 항목 추가된다. 
![관리자버튼](https://user-images.githubusercontent.com/85010698/120111729-0a86aa80-c1ae-11eb-9a16-d3a8ee88cf67.png)

<br>

###### 예약하기 <br>
<hr/>

![seadog-reserve](https://user-images.githubusercontent.com/85010698/120110202-82050b80-c1a7-11eb-8875-d83202774abe.gif)

<br/>
- 객실을 선택하면 객실 최대 투숙수에 맞게 ‘투숙 강아지수’의 최대값이 정해진다. <br>
- 예약된 객실 테이블에서 입실과 퇴실 날짜 사이의 예약된 객실을 조사해서 하나라도 존재하면 1, 없으면 0 의 값을 배열에 담는다. <br>
- 사용자가 원하는 객실 중에 배열 값이 모두 1이면 방이 존재하지 않으므로 session 에 키 값은 ‘ROOMERRORS’이고, 값은 ‘TRUE’ 라고 저장한다.      <br>                                         하나라도 0이 발견되면 그 방의 번호를 저장한다.<br>

![확인](https://user-images.githubusercontent.com/85010698/120111970-dfe92180-c1ae-11eb-818b-80723cc5a94b.png)

###### 개인 예약 조회

###### 후기 게시판 
-CRUD 가능한 후기 게시판
#### 버전
v 0.0.1 : 예약 및 여러 게시판,  기능 추가

