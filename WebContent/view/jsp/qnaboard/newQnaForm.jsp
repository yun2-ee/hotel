<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/newQna.css">
</head>
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

	<form action="newqna.do" method="post">
		<table>
			<tr>
				<td>
					<input type="text" name="title" id="title" size="100" placeholder="제목을 입력하세요.">
				</td>
			</tr>
			<tr>
				<td>
					<textarea rows="30" cols="55" name="content" id="content" style="resize: none;" placeholder="내용을 입력하세요."></textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" class="submit" value="작성">
					<input type="button" class="button" value="목록" onclick="location.href='qna.do'">
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