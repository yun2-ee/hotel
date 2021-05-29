<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="jdbc.connection.ConnectionProvider" %>
    <%@ page import="java.sql.Connection, java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>dbconnTest</h2>
	<p>이 문서는 db connection test입니다.</p>
	<%
		 //Connection이 필요할 때는
		 //ConnectionProvider.java파일의 getConnection()호출하면된다
		 try{
		 	Connection conn = ConnectionProvider.getConnection();
		 	out.println("getConnection success");
		 }catch(SQLException e){
			 out.println("getConnection Fail"+e.getMessage());
			 application.log("getConnection faile",e);
		 }
	%>
	
</body>
</html>