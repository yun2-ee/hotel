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
	<script>
		$(function(){
	
		});
	</script>
</head>
<body>

	<div id="mem_menu">
		<div  id = "mypage">
			<div  id = "cover1">
		 		<img  id="menu1" src="<%=request.getContextPath()%>/view/imgs/menu1.png" alt="me1"/>
		 		<span  id="me_text"> 마이페이지</span>
			</div>
		</div>
	    <ul id="menu_ul">
	      <li class="me"><a href="<%=request.getContextPath()%>/member/editprofile.do"><img class="menu2" src="<%=request.getContextPath()%>/view/imgs/menu2.png" alt="me2"/> 개인정보수정</a></li>
	      <li class="me"><a href="<%=request.getContextPath()%>/member/changePwd.do"><img class="menu2" src="<%=request.getContextPath()%>/view/imgs/menu2.png" alt="me2"/> 비밀번호수정</a></li>
	   	  <li class="me"><a href="<%=request.getContextPath()%>/member/withdraw.do"><img class="menu2" src="<%=request.getContextPath()%>/view/imgs/menu2.png" alt="me2"/> 회원탈퇴</a></li>
	    </ul>
  </div>
	
</body>
</html>