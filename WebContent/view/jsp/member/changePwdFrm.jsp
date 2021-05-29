<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/changePwdFrm.css?fhshhgsghhg">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/mem_menu.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script>
	$(function(){
		$('#changePwdFrm').on('submit',function(){
			if($('#newPwd').val()!=$('#newPwdcheck').val()){
				alert('새 비밀번호 확인 불일치');
				$('#newPwd').focus();
				return false;
			}else{
				submit();
			}
		});
	});
</script> 

</head>
<body>
<div class="wrap">     
	<div class="frame">
				<!-- header영역-로그인,회원가입 등등 버튼 -->
				 <jsp:include page="../../module/header.jsp" flush="false"/>
				 <!-- 메뉴바 -->
		      	 <jsp:include page="../../module/menu.jsp" flush="false"/>
	</div>
	
    <!-- mem_menu 영역 확인해보기 -->
    <jsp:include page="../../module/mem_menu.jsp" flush="true"/>
	 <!-- main 내용 영역 -->
	 <div id="content">
		<div class="changeFrm">
		<div class="logoImg"><img class="logo" src ="<%=request.getContextPath()%>/view/imgs/logo.png" width="60px" height="60px" /><h1 class="loginLogo">비밀번호 변경</h1> </div>
			<div class="logTextFrm">
			<form name="changePwdFrm" id="changePwdFrm" 
						action="changePwd.do" method="post">
				<div class="TextAlign">
				<label for="curPwd">현재 암호</label>
				</div>
				<div class="text"><input type="password" name="curPwd" id="curPwd" class ="loginText" />
				</div>
				<div class="curPwdCheck">
					<c:if test="${errors.curPwd}"><h4 class="checkPw" >현재 암호를 입력하세요.</h4></c:if>
					<c:if test="${errors.badCurPwd}"><h4 class="checkPw" >현재 암호가 일치하지 않아요.</h4></c:if>
				</div>
				<br/>
				<div class="TextAlign">
				<label for="newPwd">새 비밀번호</label>
				</div>
				<div class="text">
					<input type="password" name="newPwd" id="newPwd" class ="loginText"/>
				</div>
				<div class="newPwdCheck" >
					<c:if test="${errors.newPwd}"><h4 class="checkPw" >새 암호를 입력하세요.</h4></c:if>
				</div>
				<div class="TextAlign">
					<label for="newPwd">새 비밀번호 확인</label>
				</div>
				<div class="text">
					<input type="password" name="newPwd" id="newPwdcheck" class ="loginText"/>
				</div>
				<br/>
					<br/>  
				<div class="submitButton">
					<input type="submit" value="암호 변경" id="changeSubmit" class="loginSubmit"/>
					<input type="reset"  value="취소" class="loginSubmit" />
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






