<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/qnaModify.css">
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
		<form action="reqna.do" method="post">
		<input type="hidden" name ="no" id="no"  value="${modReq.qnaNumber}">
		<input type="hidden" name ="pageNo" id="pageNo"  value="${pageNo}">
			<table width="1200px">
				<tr>
					<td colspan="2"><input type="text" name="title" id="title" size="100" value="${modReq.title}"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea rows="30" cols="55" name="content" id="content" style="resize: none;">${modReq.content}</textarea></td>
				</tr>
				<tr class="bt">
					<td><input type="submit" value="수정" class="submit">
					<input type="button" value="목록" class="button" onclick="location.href='qna.do'"></td>
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