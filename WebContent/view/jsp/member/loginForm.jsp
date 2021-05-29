<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <c:set var="contextPath" value="<%= request.getContextPath()%>" />
<!DOCTYPE html>
<html>  
<head> 
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/login.css?fshhshjjg">
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
</head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>  
	
$(function(){
	$('#id').focus(function(){
		$(this).css('background-image', 'url(" ")');
	});
	$('#password').focus(function(){
		$(this).css('background-image', 'url(" ")');
	});

 
});  
    
</script> 
<body>
<div class="wrap">     
	<div class="frame">
				<!-- header영역-로그인,회원가입 등등 버튼 -->
				 <jsp:include page="../../module/header.jsp" flush="false"/>
				 <!-- 메뉴바 -->
		      	 <jsp:include page="../../module/menu.jsp" flush="false"/>
	</div>
    
    
	 <!-- main 내용 영역 -->
	 <div id="content">
 
		   <div class="main">
			<div class="logFrm">
			   
				<form action="login.do" method="post"> 
				<div class="logoImg"><img class="logo" src ="<%=request.getContextPath()%>/view/imgs/logo.png" width="60px" height="60px" /><h1 class="loginLogo">Login</h1> </div>
				<div class="logTextFrm">
				<c:if test="${errors.idOrPwNotMatch}">아이디와 비밀번호가 일치 하지않습니다.</c:if>
					<div class="TextAlign">ID:</div>
					<div class="text"><input type="text" name="id" value=""  id="id" class="loginText">
					
				  	</div>
				  	<div class="checkIdDiv">
				  	<c:if test="${errors.id}"><h4 class="checkID">ID를 입력하세요.</h4></c:if>
				  	<c:if test="${errors.unUser}"><h4 class="checkID">탈퇴된 회원입니다.</h4></c:if>
					<c:if test="${errors.idCheck}"><h4 class="checkID">존재하지 않는 ID입니다.</h4></c:if>
				  	</div>   
					  <br/>    
					<div class="TextAlign">PW:</div>
						<div class="text">
								<input type="password" name="password" value="" id="password" class="loginText">
						</div>
						<div class="checkPwDiv">
							<c:if test="${errors.password}"><h4 class="checkPw">비밀번호를 입력하세요.</h4></c:if>
							<c:if test="${errors.pwdCheck}"><h4 class="checkPw">비밀번호가 일치하지 않습니다.</h4></c:if>
						</div>
					</div>
				
					<div class="submitButton">
						<input type="submit" value="로그인" class="loginSubmit">
					</div>
				</form> 
			</div>
		</div>	
 </div>
	<!--topbtn 영역 -->
	<jsp:include page="../../module/top.jsp" flush="false"/>
	
	<div class="frame">
	    <!--footer 영역 -->
		<jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>	
</div>

</body>
</html>