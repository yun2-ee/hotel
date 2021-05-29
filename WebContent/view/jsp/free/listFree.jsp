<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 목록</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/freelist.css">
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
 
		<h1 id="free_title">SeaDog bord</h1>
		<div id="container">
			
			<div id="write">
				<td colspan="4"><a href="<%=request.getContextPath()%>/free/write.do"><input type="button" value="게시글 쓰기"></a></td>
			</div>
			
			<div>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="10%">번호</th>
							<th width="50%">제목</th>
							<th width="10%">작성자</th>
							<th width="10%">조회수</th>
						</tr>
					</thead>
					<tbody>
							<c:if test="${freePage.hasNoFree()}">
								<tr>
									<td colspan="4">게시글이 없습니다.</td>
								</tr>
							</c:if>
							<c:forEach var="free" items="${freePage.content}">
								<tr>
									<td>${free.no}</td>
									<td>
									<a href="read.do?no=${free.no}&$pageNo=${freePage.currentPage}">
										<c:out value="${free.title}"/>
									</a>
									</td>
									<td>${free.id}</td>
									<td>${free.readCount}</td>
								</tr>
							</c:forEach>
								
							<c:if test="${freePage.hasFree()}">
								<tr>
									<td colspan="4">
										<c:if test="${freePage.startPage > 5}">
										<a href="list.do?pageNo=${freePage.startPage - 5}">[이전]</a>
										</c:if>
										<c:forEach var="pNo"
											begin="${freePage.startPage}"
											end="${freePage.endPage}">
										<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
										</c:forEach>
										<c:if test="${freePage.endPage < freePage.totalPages}">
										<a href="list.do?pageNo=${freePage.startPage + 5}">[다음]</a>
										</c:if>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</div>
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