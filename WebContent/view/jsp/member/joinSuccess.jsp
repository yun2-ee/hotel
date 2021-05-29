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
		alert("${param.name}님 가입을 축하합니다 !");
   		location.href="../index.jsp"
      });
   </script>
</head>
<body>
   
</body>
</html>
