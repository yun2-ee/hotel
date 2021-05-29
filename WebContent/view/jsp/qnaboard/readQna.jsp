<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
<link href="../view/css/qnaread.css" rel="stylesheet" type="text/css">
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
	<div id="all">
			<table id="readQna_table" style="word-break:break-all;">
				<tr width="1200px">
					<td colspan="3" id="title">${qnaData.qnaDto.title}</td> <%-- 게시물 제목 --%>
				</tr>
				<tr width="1200px">
					<td width="400px" id="writer"><span>작성자</span>  ${qnaData.qnaDto.writer.id}</td> 
					<td width="400px" id="wrday"><span>작성일</span>  ${qnaData.qnaDto.wrday}</td>
					<td width="400px" id="readcount"><span>조회수</span>  ${qnaData.qnaDto.readcount}</td>
				</tr>
				<tr>
					<td colspan="3" width="1200px" id="readQna_content"><p style="height:300px; text-align:left; width:1200px;">${qnaData.qnaDto.content}</p></td> <%-- 문의 내용 --%>
				</tr>
				<tr>
				<td><br></td>
				</tr>
				<tr>
					<td colspan="3">
						<c:choose>
							<c:when test="${qnaData.qnaDto.res eq null}">
								<c:if test="${AUTHUSER.grade=='관리자'}"> 
									<%-- 답변이 없을 경우 작성폼이 나온다 --%>
									<form action="<%=request.getContextPath()%>/qnaboard/answer.do" method="post">
										<input type="hidden" name="qnaNo" id="qnaNo" value="${qnaData.qnaDto.no}">
										<textarea name="res" id="res" placeholder="답변을 작성하세요"></textarea>
										<input type="submit" value="작성" id= "resSuccess" style="width:100px; height:70px">
	<%-- 							<input type="text" name="res" id="res" style="width:1100px; height:200px;" placeholder="답변을 작성하세요."> 답변내용작성 --%>
									</form>
								</c:if>
								<%-- 답변 없을 경우 회원에게 보여지는 화면 --%>
								<c:if test="${AUTHUSER.id ne 'sea'}">
									<strong style="height:500px; text-align:left; width:495px;">답변 준비중</strong>
								</c:if>
							</c:when>
							<c:otherwise>
								<p style="height:300px; text-align:left; width:1200px;">${qnaData.qnaDto.res}</p>
							</c:otherwise> 
						</c:choose>
					</td>
				</tr>
			</table>
			
			<input type="button" value="목록" onclick="location.href='qna.do'" class="button">
			
				<%-- 수정 버튼 --%>
				<c:if test="${AUTHUSER.id == qnaData.qnaDto.writer.id && qnaData.qnaDto.res == null}">
					<input type="button" value=수정 onclick="location.href='reqna.do?no=${qnaData.qnaDto.no}'" class="button">
				</c:if>
			
			<%-- 관리자용 게시물 삭제 버튼  --%>
			<c:if test="${AUTHUSER.grade=='관리자'}">
				<input type="button" value=삭제 onclick="location.href='qnadel.do?no=${qnaData.qnaDto.no}'" class="button">
			</c:if>
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