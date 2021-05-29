<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>후기목록</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   <link href="<%=request.getContextPath()%>/view/css/reviewlist.css" rel="stylesheet" type="text/css">
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
   		<h1>후기게시판</h1>
   		<%-- 글쓰기 --%>
		<form name="reviewList" id="reviewList" 
				     action="reviewList.do" method="get">
		
		   <table width="500" class="table1">
		   	<tbody>
		  			<tr>
		  				<td style="text-align:left">
									<select name="searchyear" id="searchyear" >					
			  								<option ${(param.searchyear >0)?"selected":"" } value = "${param.searchyear}"><c:if test = "${param.searchyear>0}">${param.searchyear}</c:if>
			  																																									<c:if test = "${param.searchyear<1}">년도</c:if>
			  																																									<c:if test = "${param.searchyear==null}">년도</c:if></option>
			  							<c:forEach begin="2015" end="2030" step ="1" var="year">
			  								<option value = "${year}">${year}</option>
										</c:forEach>
		  							</select>
									<select name="searchmonth" id="searchmonth" >
			  								<option ${(param.searchmonth >0)?"selected":"" } value = "${param.searchmonth}"><c:if test = "${param.searchmonth>0}">${param.searchmonth}</c:if>
			  																																									<c:if test = "${param.searchmonth<1}">월</c:if>
			  																																									<c:if test = "${param.searchmonth==null}">월</c:if></option>
			  							<c:forEach begin="1" end="12" step ="1" var="month">
			  								<option value = "${month}">${month}</option>
										</c:forEach>
		  							</select>
		  				</td>
		  			</tr>
		   	</tbody>
		   </table>
		   
		
		   <table class="table2" width="1200">
		   	<thead>
				  		<tr>
				  			<th width ="50" class="rownum">번호</th>
				  			<th width ="100">회원명</th>
				  			<th width ="100">객실명</th>
				  			<th width ="600">글제목</th>
				  			<th width ="200">작성일</th>
				  			<th width ="100">점수</th>
				  			<th width ="50" class="readcount">조회수</th>
				  		</tr>
		   		</thead>
		   		<tbody>
		   			<%-- 페이징처리에서 한페이지에 출력할 게시물 건수만큼 반복처리
		   						컨트롤러에서 model로 받아왔다.
								List<ReservationDTO> list = reservationListService.getReservationPage2(pageNo);
								request.setAttribute("Reservaion",list);
		   						 --%>
		   						<%--  articles-->${articles} --%>
		   			
					<c:if  test = "${reviewListPage.hasNoReservation() }">
			   			<tr>
			   				<td colspan="11" style="text-align:center;">
			   					조회된 내용이 없습니다.
			   				</td>
			   			</tr>
			   		</c:if>
				  		<c:forEach var ="review" items="${reviewListPage.content}">
				  		<tr class="tr">
				  			<td width ="50" class="rownum">${review.rownum}</td>
				  			<td width ="100">${review.mem_name}</td>
				  			<td width ="100">${review.room_name}</td>
				  			<td width ="600" class="title"><a href="<%=request.getContextPath()%>/detailReview.do?pageNo=${reviewListPage.currentPage}&reviewNo=${review.review_no}">${review.review_title}</a></td>
				  			<td width ="200">${review.review_wrday}</td>
				  			<td width ="100">${review.review_score}</td>
				  			<td width ="50" class="readcount">${review.readcount}</td>
			  			</tr>
			  			</c:forEach>
		   		</tbody>
		   </table>
		 
		      <%-- 페이징 처리 --%>
		 	<c:if  test = "${reviewListPage.hasReservation() }">  
			   <table>
				   	<tbody>
				   	
					
				  			<tr>
				  				<td  style="text-align:center">
				  				<%--예정 조건문 활용 이전으로 넘어갈 수 있는 겨우에는
				  							 <<pre 출력 --%>
				  							
								 	<c:if test="${reviewListPage.startPage>10}">
									 		<a href="<%=request.getContextPath()%>/reviewList.do?pageNo=${reviewListPage.startPage-10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'mem_name'}&searchtext=${param.searchtext}">&lt;&lt;prev</a>
									 	</c:if>
				  				<%-- 페이징처리에서 한번에 출력할 페이지수 만큼 반복처리 --%>
				  					<%--pageNo 가 null일때 1번을 출력하는데 , 이때 startPage가 1이되며, 1번을 제외한것만 링크가 걸려야한다. --%>
				  
				  					<c:if test="${param.pageNo==null }">[${reviewListPage.startPage}]</c:if>
				  					<c:if test="${param.pageNo==null }">
				  						<c:forEach begin="${reviewListPage.startPage+1}" end="${reviewListPage.endPage}" step ="1" var="pNo">
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/reviewList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'mem_name'}&searchtext=${param.searchtext}">[${pNo}]</a></c:if>
										</c:forEach>
		  							</c:if>
		  							<c:if test="${param.pageNo!=null }">
				  						<c:forEach begin="${reviewListPage.startPage}" end="${reviewListPage.endPage}" step ="1" var="pNo">
					  						<c:if test="${param.pageNo==pNo }">[${pNo}]</c:if>
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/reviewList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'mem_name'}&searchtext=${param.searchtext}">[${pNo}]</a></c:if>
										</c:forEach>
		  							</c:if>
				  					<%--예정 조건문 활용 다음으로 넘어갈 수 있는 겨우에는
				  							 next>> 출력 --%>
				  					<c:if test="${reviewListPage.endPage<reviewListPage.totalPage}">
								 		<a href="<%=request.getContextPath() %>/reviewList.do?pageNo=${reviewListPage.startPage+10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'mem_name'}&searchtext=${param.searchtext}">next&gt;&gt;</a>
								 	</c:if>
				  				</td>
				  			</tr>
				   	</tbody>
			   	</table>
		   	</c:if>
		   	<table>
		   	<tbody>
		  			<tr>
		  				<td style="text-align:right">
		  							<select name="searchtype" id="searchtype" >
		  								<option ${(param.searchtype == "mem_name")?"selected":"" } value = "mem_name">
		  								회원명
		  								</option>
		  								<option  ${(param.searchtype == "room_name")?"selected":"" } value = "room_name">
		  								객실명
		  								</option>
		  							</select>
		  								<input type ="text" name="searchtext" id="searchtext" value="${param.searchtext}" />
		  								<input type ="submit" name="operate" id="serach" value="검색" />
		  				</td>
		  			</tr>
		   	</tbody>
		   </table>
		</form>


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