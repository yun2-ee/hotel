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

<footer>
	<div id="footer_content">
		<div id= "footer_logo">
			<img id="logo" src="<%=request.getContextPath()%>/view/imgs/logo.png" alt="logo"/><br>
			<a href="<%=request.getContextPath()%>/index.jsp">
			<span id="footer_logo_text">SEADOG</span><br/></a>
		</div> 
		<div id= "footer_app">
			<ul>
				<li><a href=""><img class="app" src="<%=request.getContextPath()%>/view/imgs/facebook.png" alt="facebook"/></a></li>
				<li><a href=""><img class="app" src="<%=request.getContextPath()%>/view/imgs/instagram.png" alt="instagram"/></a></li>
				<li><a href=""><img class="app" src="<%=request.getContextPath()%>/view/imgs/kakao.png" alt="kakao"/></a></li>
			</ul>
		</div> 
		<div id="footer_right">
			<div id= "footer_inform">
				<table id= "footer_table">
					<tr>
						<td><img class="footer_icon" src="<%=request.getContextPath()%>/view/imgs/foot_call.png" alt="call"/></td>
						<td>02-1514-2536</td>
					</tr>
					<tr>
						<td><img class="footer_icon" src="<%=request.getContextPath()%>/view/imgs/foot_email.png" alt="email"/></td>
						<td>seadog@naver.com</td>
					</tr>
					<tr>
						<td><img class="footer_icon" src="<%=request.getContextPath()%>/view/imgs/foot_location.png" alt="location"/></td>
						<td>서울특별시 구로구 구로동 구로중앙로34길 33-4 (경영기술개발원 3층)</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</footer>
</body>

</html>