<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link href="../view/css/withdraw.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/mem_menu.css">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   <title></title>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>

   </script>
</head>
<body>
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
		<h2 id="withdraw">회원탈퇴</h2>
	 	<form action="<%=request.getContextPath()%>/member/withdraw.do" method="post">
	 		<table width="1200px">
	 			<tr>
	 				<td>
	 					<textarea rows="15" cols="40" readonly="readonly">
	 							
	 							약관내용
	 							
1. 탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.삭제된 데이터는 복구되지 않습니다.

2. 탈퇴 후에도 게시판형 서비스에 등록한 게시물은 그대로 남아 있습니다.
						
3. 탈퇴 후 사용하셨던 아이디는 재사용 불가합니다.
						
4. 탈퇴 후 서비스 사용을 원하시면 언제든지 재가입 후 서비스 이용 가능합니다.
						
						
						
						
					위 내용 동의하시면 "동의합니다." 체크하여주세요.
							 					</textarea>
	 				</td>
 				</tr>
 				<tr>
	 				<td>
	 					<input type="checkbox">동의합니다.
	 				</td>
 				</tr>
 				<tr>
 				<td>
 					<input type="submit"  value="탈퇴">
 					<input type="button"  value="취소" onclick="location.href='../onlyView/main.do'">
 				</td>
 				</tr>
	 		</table>
	 	</form>
 
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