<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/modifyReview.css">
	<title></title>
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
	<h2>수정하기</h2>

		<form name="modify" method="post">
			<input type="hidden" name="no" value="${modReq.reviewNo}"/>
			<input type="hidden" name="pageNo" value="${pageNo}"}/>
			제목: <input type="text" size="30" name="title" value="${modReq.title}"><br/>
			객실: ${modReq.room_name}<br>
			점수: <input type="text" size="30"  name = "score" value="${modReq.score}"><br/>
			<br>
			내용:	<textarea  rows="30" name="content">${modReq.content}</textarea><br>
		
		<button type="submit" class="submit" value="확인" onclick="javascript: modify.action='<%=request.getContextPath()%>/modifyReview.do';">확인</button>
		<button type="button" value="취소"  onclick="location.href='<%=request.getContextPath()%>/detailReview.do?reviewNo=${modReq.reviewNo}&pageNo=${pageNo}';">취소</button>
		</form>	
	
  
	<!--topbtn 영역 -->
	<jsp:include page="../../module/top.jsp" flush="false"/>
	
	<div class="frame">
	    <!--footer 영역 -->
		<jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>	
</div>
</body>
</html>