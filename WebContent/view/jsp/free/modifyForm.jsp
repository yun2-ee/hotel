<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 수정</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/freeModify.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
#content { width: 70%; margin: 0 auto;
</style>
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
		<form action="modify.do" method="post">
		<input type="hidden" name ="no" id="no"  value="${modReq.freeNo}">
		<input type="hidden" name ="pageNo" id="pageNo"  value="${pageNo}">
		<table >
			<tr>
				<td>
					<input type="hidden" name="no" value="${modReq.freeNo}">
					번호:${modReq.freeNo}
				</td>
			</tr>
			<br/>
			<tr>
				<td>
					제목: <br/> <input type="text" name="title" style="width:400px;" value="${modReq.title}">
					<c:if test="${modReq.title}">제목을 입력하세요.</c:if>
				</td>
			</tr>
			<br/>
			<tr>
				<td>내용: <br/>
					<textarea name="content" rows="5" cols="30" style="width:800px;">${modReq.content}</textarea>
				</td>
			</tr>
			
		</table>
		<input type="submit" value="글 수정"/>
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