<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
	<!-- 드롭다운 메뉴 만들 예정-->
	<!-- 일반회원이 로그인했을때는 예약 / 마이페이지  / 회원마당(?)
	     관리자로 로그인했을때는 예약 / 관리/ 회원마당 -->

	<nav role="navigation">
		  <ul id="main-menu">
		  	<li><a href="#">SEADOG</a>
		      <ul id="sub-menu">
		        <li><a href="<%= request.getContextPath()%>/onlyView/introHotel.do" aria-label="submenu">호텔소개</a></li>
		        <li><a href="<%= request.getContextPath()%>/onlyView/introRoom.do" aria-label="submenu">객실소개</a></li>
		      </ul>
		    </li>
		    <li><a href="#">예약</a>
		      <ul id="sub-menu">
		        <li><a href="<%= request.getContextPath()%>/reserve.do?btn=0" aria-label="submenu">예약하기</a></li>
		        <li><a href="<%= request.getContextPath()%>/myReservationList.do" aria-label="submenu">예약조회</a></li>
		      </ul>
		    </li>
		    
		    <c:set var="admin" value="sea" />
		    <c:if test="${AUTHUSER.grade eq '관리자'}"><!-- 로그인한 유저가 관리자일때 -->
		    <li><a href="#">관리</a>
		      <ul id="sub-menu">
		        <li><a href="<%=request.getContextPath()%>/admin/reservationList.do" aria-label="submenu">예약관리</a></li>
		        <li><a href="<%=request.getContextPath()%>/admin/roomManage.do" aria-label="submenu">객실관리</a></li>
		        <li><a href="<%=request.getContextPath()%>/admin/list.do" aria-label="submenu">회원관리</a></li>
		      </ul>
		    </li>
			</c:if>
			
		    	 <li><a href="#">마이페이지</a>
		     	 <ul id="sub-menu">
		         <li><a href="<%=request.getContextPath()%>/member/editprofile.do" aria-label="submenu">개인정보수정</a></li>
		         <li><a href="<%=request.getContextPath()%>/member/changePwd.do" aria-label="submenu">비밀번호수정</a></li>
		         <li><a href="<%=request.getContextPath()%>/member/withdraw.do" aria-label="submenu">회원탈퇴</a></li>
		      </ul>
		    </li>

		    <li><a href="#">고객센터</a>
		      <ul id="sub-menu">
		        <li><a href="<%=request.getContextPath()%>/free/list.do" aria-label="submenu">자유게시판</a></li>
		        <li><a href="<%= request.getContextPath()%>/reviewList.do" aria-label="submenu">후기게시판</a></li>
		        <li><a href="<%=request.getContextPath()%>/qnaboard/qna.do" aria-label="submenu">QnA</a></li>
		        <li><a href="<%=request.getContextPath()%>/board/gallList.do" aria-label="submenu">갤러리</a></li>
		
		      </ul>
		    </li>
		  </ul>
		  
		  <!-- 로그인 전 메인페이지 -->
		  <div class="login">
		  <c:if test="${empty AUTHUSER}">
			<button class="mem_btns"><a href="<%=request.getContextPath()%>/member/login.do">로그인</a></button>
			<button class="mem_btns"><a href="<%=request.getContextPath()%>/member/join.do">회원가입</a></button>	
		</c:if>
		</div>
		
		<!-- 로그인 후 메인페이지 -->
		<div class="login">
		<c:if test="${!empty AUTHUSER}">
			<span class="loginArlm">${AUTHUSER.id}님 환영합니다!</span>
			<button class="mem_btns"><a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a></button>
		</c:if>
		</div>
</nav>

</html>