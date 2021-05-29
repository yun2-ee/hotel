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
	  $(function() {
	        $(window).scroll(function() {
	            if ($(this).scrollTop() > 150) {
	                $('#top').fadeIn();
	            } else {
	                $('#top').fadeOut();
	            }
	        });
	        
	        $("#MOVE_TOP_BTN").click(function() {
	            $('html, body').animate({
	                scrollTop : 0
	            }, 400);
	            return false;
	        });
	    });
	</script>
</head>
<body>
	<div id="top">
		<a id="MOVE_TOP_BTN" href="#"> 
		<img id="topBtn" src="<%=request.getContextPath()%>/view/imgs/top.png" alt="top"/>
		</a>
	</div>
</body>
</html>