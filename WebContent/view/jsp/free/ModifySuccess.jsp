<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
<div align="center" style="position: relative; top:300px;">
<img src="<%=request.getContextPath()%>/view/imgs/logo.png" width="300px" height="300px" /> 	
<h1>게시글을 수정 했습니다.</h1>
<br>
${ctxPath = pageContext.request.contextPath}
<a href="${ctxPath}/free/list.do">[게시글목록보기]</a>
<a href="${ctxPath}/free/read.do?no=${modReq.freeNo}">[게시글 내용 보기]</a>
</div>

	
</body>
</html>