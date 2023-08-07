# reservation
Spring 기술과제

[메인 페이지]

페이지 메인 : http://localhost:8080 <br>
관리자 메인 : http://localhost:8080/admin/main.do <br>
키오스크 메인 : http://localhost:8080/kiosk <br>
매장 정보 페이지 : http://localhost:8080/store <br>


[패키지 구조]

controller : 웹 주소에 접근했을때, 요청/응답을 해주는 클래스 <br>
configuration : 인증 관련 패키지 <br>
entity : jpa entity <br>
dto : DTO(Data Transafer Object)를 위치시키는 곳, Controller에서 요청/응답에 사용할 클래스, 로직 내부에서 데이터 전송에 사용할 클래스 패키지 <br>
exception : 커스텀 Exception, Exception Handler 클래스 패키지 <br>
repository : Repository(DB에 연결할 때 사용하는 인터페이스)가 위치하는 패키지 <br>
service : 비즈니스 로직을 담는 서비스 클래스 패키지 <br>
type : 상태타입, 에러코드, 거래종류 등의 다양한 enum class들의 패키지 <br>
model : 데이터를 처리하는 데 사용하는 패키지 <br>
mapper : db와 자바 객체간 매핑 담당 패키지 <br>
components : 메일 컴포넌트 패키지 <br>
util : 페이징, 비밀번호 관련 동작 패키지 <br>

[클래스]

list : 목록을 보여주기 위한 함수 <br>
add : 항목을 추가하기 위한 함수 <br>
del : 항목을 제거하기 위한 함수 <br>
update : 정보를 업데이트 하기 위한 함수 <br>
detail : 특정 항목에 대한 상세정보를 보여주는 함수 <br>
status : 사용자의 상태(권한), 매장 상태, 예약정보의 상태를 보여주기 위한 함수 <br>
dto : Controller에서 요청/응답에 사용할 함수 <br>
category : 매장 종류에 의한 카테고리 기능 함수 <br>
mapper : db와 자바 객체간 매핑 담당 함수 <br>
Input : 데이터를 처리하는 데 사용하는 함수 <br>
param : 파라미터에 사용되는 함수 <br>
repository : DB에 연결하기 위해 사용하는 함수 <br>
service의 기능을 serviceImpl에서 구현 <br>
result : http응답을 위한 데이터를 담는데 사용 <br>
security : 인증 및 권한을 위해 사용한 함수 <br>
expection : 에러가 발생했을 때를 대비해 작성한 커스텀 함수 <br>

※ 구글 API Key는 과제 제출에 첨부하였습니다
