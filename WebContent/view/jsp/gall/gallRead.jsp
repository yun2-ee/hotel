<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>      
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/gallRead.css?gg">
   <title></title>
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
 
	   <table width="1200px" style="word-break:break-all;" id="gallRead_table">
	   	<tbody>
	   		<tr>
	   			<th colspan="6" class="title">${gallData.gallDto.gall_title}</th>
	   		</tr>
	   		<tr>
	   			<th width="200px" class="name">&emsp;작성자 </th><td> ${gallData.gallDto.mem_name}</td>
	   			<th width="200px" class="no">&emsp;글번호 </th><td> ${gallData.gallDto.gall_no}</td>
	   			<th width="200px" class="readcount">&emsp;조회수 </th><td> ${gallData.gallDto.gall_readcount}</td>
	   		</tr>	
	   		<tr>	
	   			<th width="200px" class="wrday">&emsp;작성일 </th><td colspan="2"> <fmt:formatDate pattern  ="yyyy.MM.dd HH:mm" value="${gallData.gallDto.gall_wrday}" /></td>
	   			<th width="200px" class="moday">&emsp;수정일 </th><td colspan="2"><fmt:formatDate pattern  ="yyyy.MM.dd HH:mm" value="${gallData.gallDto.gall_moday}" /></td>
	   		</tr>	
   			<tr>	
   				<th width="200px" class="Img">&emsp;파일첨부 </th>
				<td colspan="5"> 
					<c:forEach var="gallImg" items="${gallData.gallImgDto}">
						<c:if test="${gallImg!=null}">
							<a download href="<%=request.getContextPath()%>/images/${gallImg.img_new}">${gallImg.img_old}</a><br/>
						</c:if>
					</c:forEach>
				</td>
	   		</tr>
   			<tr> 
	   			<td colspan="6">
	   				<c:forEach var="gallImg" items="${gallData.gallImgDto}">
		   				<c:if test="${gallImg!=null}">
		   					<a href="<%=request.getContextPath()%>/images/${gallImg.img_new}"><img src="<%=request.getContextPath()%>/images/${gallImg.img_new}" width="600" height="500"/></a><br/>
		   				</c:if>
	   				</c:forEach>
   				  <%    // 뷰 페이지 용
                     //치환 변수 선언합니다.
                      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
                      pageContext.setAttribute("br", "<br/>"); //br 태그
               		%> 
               		${fn:replace(gallData.gallDto.gall_content, crcn, br) }
	   			</td>
	   		</tr>   
	   		<tr  class="bt">
	   			<c:set var="pNo" value="${empty param.gPageNo? '1':param.gPageNo}" />
	   			<td colspan="6">
	   				<a class ="gallReadButton"  href="../board/gallList.do?gPageNo=${pNo}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">목록보기</a>
	   				<c:if test="${AUTHUSER.id==gallData.gallDto.mem_id||AUTHUSER.grade=='관리자'}">
	   					<a class ="gallReadButton" href="../board/gallModify.do?gPageNo=${pNo}&no=${gallData.gallDto.gall_no}&gSearchType=${param.gSearchType!=null?param.gSearchType:'mem_name' }&gSearchText=${param.gSearchText}">수정하기</a>
	   					<a class ="gallReadButton" href="../board/gallDelete.do?gPageNo=${pNo}&no=${gallData.gallDto.gall_no}">삭제하기</a>
	   				</c:if>
	   			</td>
	   		</tr>
	   	
	   	</tbody>
	   </table> 
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