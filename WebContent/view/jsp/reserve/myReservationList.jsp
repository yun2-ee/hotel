<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>예약조회</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/myReservationList.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function modifyReserve(){
		alert("예약 수정 및 취소는  02-1514-2536 로 전화주세요.");
	}
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
		<h1>나의 예약 목록</h1>
		
		<!-- 에러창 -->
		<c:if test="${!empty HASREVIEW}">
		<script type="text/javascript">alert("이미 후기가 존재합니다.");</script>
		</c:if>
		
		<form name="myReservationList" id="myReservationList"  method="get">
			<table width="500"  class="table1"> <!--검색 : (년도와 일) -->
				   	<tbody>
				  			<tr>
				  				<td style="text-align:left">
											<select name="searchyear" id="searchyear" >					
					  								<option ${(param.searchyear > 0)?"selected":"" } value = "${param.searchyear}"><c:if test = "${param.searchyear>0}">${param.searchyear}</c:if><!-- 찾는년도가 0보다 크면 출력 -->
					  																																						  <c:if test = "${param.searchyear<1}">년도</c:if>
					  																																						  <c:if test = "${param.searchyear==null}">년도</c:if>
					  								</option>
					  								<c:forEach begin="2015" end="2030" step ="1" var="year">
					  									<option value = "${year}">${year}</option>
													</c:forEach>
				  							</select>
											<select name="searchmonth" id="searchmonth" >
					  								<option ${(param.searchmonth >0)?"selected":"" } value = "${param.searchmonth}"><c:if test = "${param.searchmonth>0}">${param.searchmonth}</c:if>
					  																																							  <c:if test = "${param.searchmonth<1}">월</c:if>
					  																																							  <c:if test = "${param.searchmonth==null}">월</c:if>
												  	</option>
						  						  	<c:forEach begin="1" end="12" step ="1" var="month">
						  								<option value = "${month}">${month}</option>
													</c:forEach>
				  							</select>
				  				</td>
				  			</tr>
				   	</tbody>
			</table>
	   
			<!-- 로그인 유저 정보를 가져와서 로그인한 회원의 예약 내역을 가져온다.  -->
			 <table  class="table2">
			   	<thead>
					  		<tr>
					  			<th width ="100">번호</th>
					  			<th width ="100">상태</th>
					  			<th width ="100">예약번호</th>
					  			<th width ="140">예약신청일</th>
					  			<th width ="140">예약시작일</th>
					  			<th width ="140">예약종료일</th>
					  			<th width ="120">객실명</th>
					  			<th width ="120">애완동물투숙수</th>
					  			<th width ="120">결제상태</th>
					  			<th width ="140">관리</th>
					  		</tr>
			   	</thead>
			   	<tbody>
			   			<%-- 페이징처리에서 한페이지에 출력할 게시물 건수만큼 반복처리
			   						컨트롤러에서 model로 받아왔다.
									List<ReservationDTO> list = reservationListService.getReservationPage2(pageNo);
									request.setAttribute("Reservaion",list);
			   						 --%>
			   						<%--  articles-->${articles} --%>

						<c:if  test = "${MyreservationListPage.hasNoReservation() }"> <!--total(예약 내역 수) 이 아예 없다면 --> 
				   			<tr>
				   				<td colspan="11" style="text-align:center;">
				   					조회된 내용이 없습니다.
				   				</td>
				   			</tr>
				   		</c:if>
					  		<c:forEach var ="reservation" items="${MyreservationListPage.content}"> <!-- 예약 내역이 있다면 -->
						  		<tr>
						  			<td width ="90">${reservation.rownum}</td>
						  			<td width ="100">${reservation.room_use}</td>
						  			<td width ="90">${reservation.reserve_no}</td>
						  			<td width ="140"><fmt:formatDate pattern  ="yyyy.MM.dd" value="${reservation.reserve_day}" /></td>
						  			<td width ="140"><fmt:formatDate pattern  ="yyyy.MM.dd" value="${reservation.reserve_start}" /></td>
						  			<td width ="140"><fmt:formatDate pattern  ="yyyy.MM.dd" value="${reservation.reserve_end}" /></td>
						  			<td width ="120">${reservation.room_name}</td>
						  			<td width ="120">${reservation.reserve_pet_number}</td>
						  			<td width ="120">${reservation.reserve_pay}</td>
						  			<td width ="140" >
						  				<c:if test="${reservation.room_use eq '이용완료'}">
						  				<button type="button" class="operate_btn" name="operate"  value="후기작성"  
						  				onclick="location.href='<%=request.getContextPath()%>/writeReview.do?reNo=${reservation.reserve_no}&rName=${reservation.room_name}&pageNo=${MyreservationListPage.currentPage}';">
						  				후기작성</button>
						  				</c:if>
						  				<button type="button" class="operate_btn" name="operate" value="수정" onclick="modifyReserve()">수정</button>
						  			</td>
					  			</tr>
				  			</c:forEach>
			   		</tbody>
			   </table>
		
			 <%-- 페이징 처리 --%>
		 	<c:if  test = "${MyreservationListPage.hasReservation() }">  <!-- 예약내역이 있다면 -->
			   <table class="paging">
				   	<tbody>
				  			<tr>
				  				<td  style="text-align:center">
				  				<%--예정 조건문 활용 이전으로 넘어갈 수 있는 겨우에는
				  							 <<pre 출력 --%>
								 	<c:if test="${MyreservationListPage.startPage>10}">
									 	<a href="<%=request.getContextPath()%>/myReservationList.do?pageNo=${MyreservationListPage.startPage-10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'room_use'}&searchtext=${param.searchtext}">&lt;&lt;prev</a>
									</c:if>
				  				<%-- 페이징처리에서 한번에 출력할 페이지수 만큼 반복처리 --%>
				  				<%--pageNo 가 null일때 1번을 출력하는데 , 이때 startPage가 1이되며, 1번을 제외한것만 링크가 걸려야한다. --%>
				  					<c:if test="${param.pageNo==null }">[${MyreservationListPage.startPage}]</c:if>
				  					<c:if test="${param.pageNo==null}">
				  						<c:forEach begin="${MyreservationListPage.startPage+1}" end="${MyreservationListPage.endPage}" step ="1" var="pNo">
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/myReservationList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'room_use'}&searchtext=${param.searchtext}">[${pNo}]</a></c:if>
										</c:forEach>
		  							</c:if>
		  							<c:if test="${param.pageNo!=null }">
				  						<c:forEach begin="${MyreservationListPage.startPage}" end="${MyreservationListPage.endPage}" step ="1" var="pNo">
					  						<c:if test="${param.pageNo==pNo }">[${pNo}]</c:if>
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/myReservationList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'room_use'}&searchtext=${param.searchtext}">[${pNo}]</a></c:if>
										</c:forEach>
		  							</c:if>
				  					<%--예정 조건문 활용 다음으로 넘어갈 수 있는 겨우에는
				  							 next>> 출력 --%>
				  					<c:if test="${MyreservationListPage.endPage<MyreservationListPage.totalPage}">
								 		<a href="<%=request.getContextPath() %>/myReservationList.do?pageNo=${MyreservationListPage.startPage+10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'room_use'}&searchtext=${param.searchtext}">next&gt;&gt;</a>
								 	</c:if>
				  				</td>
				  			</tr>
				   	</tbody>
			   	</table>
		   	</c:if>
		   	
		   	<table class="seach2">
		   		<tbody>
		  			<tr>
		  				<td style="text-align:right">
		  							<select name="searchtype" id="searchtype" >
		  								<option ${(param.searchtype == "room_use")?"selected":"" } value = "room_use">상태</option>
		  								<option  ${(param.searchtype == "room_name")?"selected":"" } value = "room_name">객실명 </option>
		  							</select>
		  							<input type ="text" name="searchtext" id="searchtext" value="${param.searchtext}" />
		  							<button type="submit" name="operate"   id="serach" value="검색"  onclick="location.href='<%=request.getContextPath()%>/myReservationList.do';">검색</button>
		  				</td>
		  			</tr>
		   		</tbody>
		   </table>
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