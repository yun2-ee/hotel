<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/newFreeForm.css">
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>
	   $(function(){
		   
	   });
   </script>
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
		<form id="free_form" action="../free/write.do" method="post">
			<table>
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" id="title" style="width:400px;"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea rows="30" cols="30" name="content" style="resize: none;"></textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" class="submit" value="작성"></td>
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