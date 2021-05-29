<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
<link href="../view/css/qnaboard.css" rel="stylesheet" type="text/css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
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
	<div id="all">
	<h1>QnA</h1>
		<form action="<%=request.getContextPath()%>/qnaboard/qna.do" id="seachall">
			<input type="text" name="qnaseach" id="qnaseach" placeholder="ID 또는 제목을 입력하세요">
			<input type="submit" value="검색" name="seachbt" id="seachbt">
			<a href="<%=request.getContextPath()%>/qnaboard/qna.do?qnaseach=${AUTHUSER.id}"><input type="button" name="myqna" id="myqna" value="내 문의 글"></a>
			<a href="<%=request.getContextPath()%>/qnaboard/newqna.do"><input type="button" name="newqna" id="newqna" value="문의 작성"></a>
		</form>
			<table id="table2" width="1200">
				<tr id="main">
					<td width="150" id="no">번호</td>
					<td width="650" id="title">제목</td>
					<td width="200" id="writer">작성자</td>
					<td width="250" id="wrday">작성일</td>
					<td width="150" id="check">답변완료</td>
					<td width="150" id="readcount">조회수</td>
				</tr>
				<c:if test="${qnaPage.hasNoQnas()}">
					<tr>
						<td colspan="6" style="text-aling:center">게시글이 없습니다.</td>
					</tr>
				</c:if>
				
				<c:forEach var="qnaboard" items="${qnaPage.content}">
					<tr>
						<td width="150" id="no">${qnaboard.no}</td>
						<td width="650" id="title"><a href="read.do?no=${qnaboard.no}">${qnaboard.title}</a></td>
						<td width="200" id="writer">${qnaboard.writer.id}</td>
						<td width="250" id="wrday">${qnaboard.wrday}</td>
						<td width="150"  id="check">${qnaboard.check}</td>
						<td width="150" id="readcount">${qnaboard.readcount}</td>
					</tr>
				</c:forEach>
			</table>
		<c:if test="${qnaPage.hasQnas()}">
			<div style="margin-top: 30px; text-decoration:none; text-align:center;">
				<c:if test="${qnaPage.endPage>1}">
					<c:if test="${qnaPage.startPage>5}">
						<a href="<%=request.getContextPath()%>/qnaboard/qna.do?pageNo=${qnaPage.startPage-5}">&lt;</a>
					</c:if>
					
					<c:forEach begin="${qnaPage.startPage}" end="${qnaPage.endPage}" step="1" var="pNo">
						<a href="<%=request.getContextPath()%>/qnaboard/qna.do?pageNo=${pNo}">${pNo}</a>
					</c:forEach>
					
					<c:if test="${qnaPage.endPage<qnaPage.totalPages}">
						<a href="<%=request.getContextPath()%>/qnaboard/qna.do?pageNo=${qnaPage.startPage+5}">&gt;</a>
					</c:if>
				</c:if>
			</div>
		</c:if>
		</div>
 
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