<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute("replaceChar", "\r\n"); %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>   
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/freeread.css">
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
 
		<div id="container">
			<div id="read">
				<td colspan="4"><a href="write.do"><input type="button" value="게시글 쓰기"></a></td>
			</div>
			
			<div>
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th width="10%">번호</th>
							<td>${freeData.free.no}</td>
						</tr>
						<tr>
							<th width="50%">제목</th>
							<th>${freeData.free.title}</th>
						</tr>
						<tr>
							<th width="10%">작성자</th>
							<th>${freeData.free.id}</th>
						</tr>
						<tr>
							<th width="70%">내용</th>
							<th><u:pre value="${freeData.free.content}"/></th>
						</tr>
					</thead>
						<tbody>
							<tr>
							<c:set var="pageNo" value="${empty param.pageNo ? '1': param.pageNo}"/>
							<td colspan=="5">
								<a href="list.do?pageNo=${pageNo}"><input type="button" value="목록"></a>
								<c:if test="${AUTHUSER.id == freeData.free.id}">
									<a href="modify.do?no=${freeData.free.no}&pageNo=${pageNo}"><input type="button" value="게시글 수정"></a>
									<a href="delete.do?no=${freeData.free.no}&pageNo=${pageNo}"><input type="button" value="게시글 삭제"></a>
								</c:if>
								
							</td>
						</tr>
						</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="frame">
	    <!--footer 영역 -->
		<jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>	
</div>	
</body>
</html>