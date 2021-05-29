<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
	alert('탈퇴되었습니다.')
	location.href="<%=request.getContextPath()%>/member/logout.do";
</script>
</body>
</html>