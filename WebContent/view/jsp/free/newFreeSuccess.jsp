<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>게시글 등록</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(function(){
			
		});	//jQuery
	</script>
</head>
<body>
<div align="center" style="position: relative; top:300px;">
<img src="<%=request.getContextPath()%>/view/imgs/logo.png" width="300px" height="300px" />
	<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	<hr/>
	

		
	<h1>게시글을 등록했습니다</h1>
	<br/>
	<a href="${ctxPath}../free/list.do"><input type="button" value="게시글목록보기"></a>
	<a href="<%=request.getContextPath()%>/free/read.do?no=${newFreeNo}"><input type="button" value="게시글 상세내용보기"></a>
</body>
</html>








