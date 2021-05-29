<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

${modReq.qnaNumber}
${qnaNumber}
<script type="text/javascript">
	alert('수정되었습니다.')
	location.href="./read.do?no=${modReq.qnaNumber}";
</script>
</body>
</html>