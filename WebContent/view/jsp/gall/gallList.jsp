<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/gallList.css">
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
 
	   <h1>갤러리</h1>
	   	<table width="1200px" class="write">
			<tbody>
				<tr>
					<td style="text-align:right;">
					<button type="button" id="writeGall"  value="갤러리작성"  onclick="location.href='<%=request.getContextPath()%>/board/gallWrite.do';">
						  글쓰기</button>
					</td>
				</tr>
			</tbody>
		</table>
	
	   <form name="gallList" id="gallList" method="get" action="gallList.do">
	   		<table class="list">
	   			<tbody style="text-align:center">
					
					<c:if test="${gallListPage.hasNoGallArticle()}">
						<h2>검색된 결과가 없습니다.</h2>
					</c:if>
					<c:if test="${gallListPage.hasGallArticle()}">
		   				<c:forEach var="gall" varStatus="i" items="${gallListPage.content}">
		   				
		   					
		   					<c:if test="${i.count%4==0 && i.count>0}"></tr></c:if>
		   					<c:if test="${i.count%4==0}"><tr></c:if>
		   					<td><a href="gallRead.do?no=${gall.gall_no}&gPageNo=${gallListPage.currentPage}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">
		   					<c:if test="${gallListPage.img[i.index]!=null}">
		   						<img src="<%=request.getContextPath()%>/images/${gallListPage.img[i.index].img_new}" width="250" height="200"/><br/>${gall.gall_title}
		   					</c:if>
		   					<c:if test="${gallListPage.img[i.index]==null}">
		   						<img src="http://placehold.it/250x200" width="250" height="200"/><br/>${gall.gall_title}
		   					</c:if>
		   					</a><br/><fmt:formatDate pattern  ="yyyy.MM.dd HH:mm" value="${gall.gall_wrday}" /> / ${gall.mem_name}</td>
		   				</c:forEach>
	   				</c:if>
	   			</tbody>
	   		</table>
	   		
			<div>
				<%-- 페이징 처리 --%>
				<c:if test="${gallListPage.startPage>10}">
					<a href ="<%=request.getContextPath()%>/board/gallList.do?gPageNo=${gallListPage.startPage-10}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">&lt;&lt;</a>
				</c:if>
				
				
				<c:if test="${param.gPageNo==null }">
						<c:if test="${gallListPage.startPage==0}"><span class="selectPno">1</span></c:if>
						<c:if test="${gallListPage.startPage!=0}"><span class="selectPno">${gallListPage.startPage}</span></c:if>
				</c:if>
			  	<c:if test="${param.gPageNo==null }">
					<c:forEach begin="${gallListPage.startPage+1}" end="${gallListPage.endPage}" step ="1" var="pNo">
						<c:if test="${(param.gPageNo!=pNo)}"><a href="<%=request.getContextPath() %>/board/gallList.do?gPageNo=${pNo}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">${pNo}&nbsp;</a></c:if>
					</c:forEach>		  		
			  	</c:if>
			  	<c:if test="${param.gPageNo!=null }">
					<c:forEach begin="${gallListPage.startPage}" end="${gallListPage.endPage}" step ="1" var="pNo">
						<c:if test="${param.gPageNo==pNo }"><span class="selectPno">${pNo}&nbsp;</span></c:if>
						<c:if test="${(param.gPageNo!=pNo)}"><a href="<%=request.getContextPath() %>/board/gallList.do?gPageNo=${pNo}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name'}&gSearchText=${param.gSearchText}">${pNo}&nbsp;</a></c:if>
					</c:forEach>		  		
			  	</c:if>						
				<c:if test="${gallListPage.endPage<gallListPage.totalPage}">
					<a href ="<%=request.getContextPath()%>/board/gallList.do?gPageNo=${gallListPage.startPage+10}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">&gt;&gt;</a>
				</c:if>
			</div>
			   		
			<div>
				<select name= "gSearchType" class="gSearchType">
					<option ${(param.gSearchType=="mem_name")?"selected":"" } value="mem_name">작성자</option>
					<option ${(param.gSearchType=="gall_title")?"selected":"" } value="gall_title">제목</option>
				</select>
				<input type = "text" name ="gSearchText" class="gSearchText" value="${param.gSearchText}">
				<input type = "submit" name = "serach" class="serach" value="검색">
				
			</div>
	   	</form>
		</div><!-- content영역 div -->
  
  
	<!--topbtn 영역 -->
	<jsp:include page="../../module/top.jsp" flush="false"/>
	
	<div class="frame">
	    <!--footer 영역 -->
		<jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>	
</div>
</body>
</html>