<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<script>
		alert("예약되었습니다.");
	 	window.location.href="<%=request.getContextPath()%>/onlyView/main.do";
	</script>
</body>
</html>