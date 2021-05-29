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
   <script>
      $(function(){
   
      });
   </script>
</head>
<body>
   <a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
   <hr/>
   	<c:if test="${result}">
		<script>
			/*삭제 성공시 메시지창을 띄워서 user에게 알려준 후 목록보기로 이동
				http://localhost/bo/article/list.do */
			alert("수정이 완료되었습니다.");
		</script>
	</c:if>
	<c:if test="${!result}">
		<script>
			alert("수정에 실패하였습니다.");
		</script>
	</c:if>
	<script>
		location.href="../admin/reserveChange.do?no=${no}&btn=0"
		//location.href="list.do"
		//send redirect를 하면 해당 페이지 안보이고 지나가기 때문에 이렇게 처리
	</script>
   
</body>
</html>