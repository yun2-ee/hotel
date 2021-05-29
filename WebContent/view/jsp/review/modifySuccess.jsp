<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>
<body>

	
	<script>
		alert("수정했습니다.");
	</script>
	<script>
	var param = "reviewNo=${modReq.reviewNo}&pageNo=${pageNo}";
	 window.location.href="<%=request.getContextPath()%>/reviewList.do?" +param;
	</script>
	
</body>
</html>