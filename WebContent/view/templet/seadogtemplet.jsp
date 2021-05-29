<%@ page language="java" contentType="text/html; charset=${encoding}"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="${encoding}">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   <title></title>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>
      $$(function(){
   
      });
   </script>
</head>
<body>
  <body>
<div class="wrap">     
	<div class="frame">
	<%--
				<!-- header영역-로그인,회원가입 등등 버튼 -->
				 <jsp:include page="../../module/header.jsp" flush="false"/>
				 <!-- 메뉴바 -->
		      	 <jsp:include page="../../module/menu.jsp" flush="false"/>
		      	 --%>
	</div>
    
    
	 <!-- main 내용 영역 -->
	 <div id="content">
 
 
 
 
 여기에여기에 내용 넣어주세요!!!!!!! id가 content 인 div 영역안에다가요~~~~~

 
 
 
 
   </div>
	<!--topbtn 영역 --><%-- 
	<jsp:include page="../../module/top.jsp" flush="false"/>
	
	<div class="frame">
	    <!--footer 영역 -->
		<jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>	 --%>
</div>
</body>
</html>