<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="${encoding}">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/writeReview.css">
<title>Insert title here</title>
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
		<!-- >/writeReview.do?reNo=${reservation.reserve_no}&rName=${reservation.room_name}'; 이렇게 MyReservationList.do 에서 넘어옴. -->
		<form method ="post">
			<input type="hidden" name="no" id="no" value="<%=request.getParameter("reNo")%>">
			
			<table>
				<tr>
					<td width="20%" class="tr">제목</td>
					<td><input type="text" name ="title"></td>
				</tr>
				<tr>
					<td class="tr">객실명</td>
					<td><input type="text" size="10" name ="roomName" value="<%=request.getParameter("room_name")%>"  readonly></td>
					
				</tr>
				<tr>
					<td class="tr">점수</td>
					<td><input type="text" size="2" id="score" name ="score">  /  5</td>
				</tr>
				<tr>
					<td class="tr">글 내용</td>
					<td><textarea rows="30" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" class="button">
						<button type="submit"  value="save" class="save" onclick="javascript: reservation.action='<%=request.getContextPath()%>/writeReview.do';">저장</button>
						<button type="button" value="golist" class="golist" onclick="location.href='<%=request.getContextPath()%>/myReservationList.do';">목록</button>
					</td>
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