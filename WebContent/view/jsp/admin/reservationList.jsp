<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/myReservationList.css?ff">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   
   <title></title>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>
      $(function(){
    	  $("#searchtext").keypress(function(event){
    		  if(event.keyCode==13){
    			  $("#serach").click();
    			  return false;
    		  }
    	  });
    	  $("#checkIn").click(function(){
    		  if(!$("input:checked[Name='selectNo']").is(":checked")){
    			  alert('체크된 예약번호가 없습니다.');
    			  return false;
    		  }
    	  });
    	  $("#checkOut").click(function(){
    		  if(!$("input:checked[Name='selectNo']").is(":checked")){
    			  alert('체크된 예약번호가 없습니다.');
    			  return false;
    		  }
    	  });
    	  $("#depositX").click(function(){
    		  if(!$("input:checked[Name='selectNo']").is(":checked")){
    			  alert('체크된 예약번호가 없습니다.');
    			  return false;
    		  }
    	  });
    	  $("#depositOk").click(function(){
    		  if(!$("input:checked[Name='selectNo']").is(":checked")){
    			  alert('체크된 예약번호가 없습니다.');
    			  return false;
    		  }
    	  });
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
		<form name="reservationList" id="reservationList" 
				     action="reservationList.do" method="post">
		   	<h1>예약 목록 리스트</h1>
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
					<br/><br/>
		   <table border="1" class="table2">
		   	<thead>
				  		<tr>
				  			<th width ="100">선택</th>
				  			<th width ="150">회원아이디</th>
				  			<th width ="150">회원이름</th>
				  			<th width ="250">회원연락처</th>
				  			<th width ="150">예약신청일</th>
				  			<th width ="150">예약시작일</th>
				  			<th width ="150">예약종료일</th>
				  			<th width ="150">객실명</th>
				  			<th width ="250">애완동물투숙수</th>
				  			<th width ="150">결제상태</th>
				  			<th width ="150">이용상태</th>
				  			<th width ="200">관리</th>
				  		</tr>
		   		</thead>
		   		<tbody>
		   			<%-- 페이징처리에서 한페이지에 출력할 게시물 건수만큼 반복처리
		   						컨트롤러에서 model로 받아왔다.
								List<ReservationDTO> list = reservationListService.getReservationPage2(pageNo);
								request.setAttribute("Reservaion",list);
		   						 --%>
		   						<%--  articles-->${articles} --%>
					<c:if  test = "${reservationListPage.hasNoReservation() }">
			   			<tr>
			   				<td colspan="12" style="text-align:center;">
			   					조회된 내용이 없습니다.
			   				</td>
			   			</tr>
			   		</c:if>
				  		<c:forEach var ="reservation" varStatus="status" items="${reservationListPage.content}">
				  		<tr>
				  			<td width ="100"><input type="checkbox" name="selectNo" value="${reservation.reserve_no}" /></td>
				  			<td width ="150">${reservation.mem_id}</td>
				  			<td width ="150">${reservation.mem_name}</td>
				  			<td width ="250">${reservation.mem_call}</td>
				  			<td width ="150"><fmt:formatDate pattern ="yyyy.MM.dd" value="${reservation.reserve_day}" /></td>
				  			<td width ="150"><fmt:formatDate pattern ="yyyy.MM.dd" value="${reservation.reserve_start}" /></td>
				  			<td width ="150"><fmt:formatDate pattern ="yyyy.MM.dd" value="${reservation.reserve_end}" /></td>
				  			<td width ="150">${reservation.room_name}</td>
				  			<td width ="250">${reservation.reserve_pet_number}</td>
				  			<td width ="150">${reservation.reserve_pay}</td>
				  			<td width ="150">${reservation.room_use}</td>
				  			<td>
				  			<input type="hidden" name="no" id="no" value="${reservation.reserve_no}">
				  			<input type="submit" name="operate" id="delete" value="삭제" class="operate_btn">
				  			<input type="button" name="modify" id="modify" value="수정" onClick="location.href='<%=request.getContextPath() %>/admin/reserveChange.do?no=${reservation.reserve_no}&btn=0'" class="operate_btn"/>
				  			</td>
			  			</tr>
			  			</c:forEach>
		   		</tbody>
		   </table>
		   
		      <%-- 페이징 처리 --%>
		 	<c:if  test = "${reservationListPage.hasReservation() }">  
			   <table>
				   	<tbody>
				   	
					
				  			<tr>
				  				<td  style="text-align:center">
				  				<%--예정 조건문 활용 이전으로 넘어갈 수 있는 겨우에는
				  							 <<pre 출력 --%>
								 	<c:if test="${reservationListPage.startPage>10}">
									 		<a href="<%=request.getContextPath()%>/admin/reservationList.do?pageNo=${reservationListPage.startPage-10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'substr(mem_call,-4,4)'}&searchtext=${param.searchtext}">&lt;&lt;prev</a>
									 	</c:if>
				  				<%-- 페이징처리에서 한번에 출력할 페이지수 만큼 반복처리 --%>
				  					<%--pageNo 가 null일때 1번을 출력하는데 , 이때 startPage가 1이되며, 1번을 제외한것만 링크가 걸려야한다. --%>
				  					<c:if test="${param.pageNo==null }">
					  					<c:if test="${reservationListPage.startPage==0}"><span class="selectPno">1</span></c:if>
					  					<c:if test="${reservationListPage.startPage!=0}"><span class="selectPno">${reservationListPage.startPage}&nbsp;</span></c:if>
				  					</c:if>
				  					<c:if test="${param.pageNo==null }">
				  						<c:forEach begin="${reservationListPage.startPage+1}" end="${reservationListPage.endPage}" step ="1" var="pNo">
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/admin/reservationList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'substr(mem_call,-4,4)'}&searchtext=${param.searchtext}">${pNo}&nbsp;</a></c:if>
										</c:forEach>
		  							</c:if>
		  							<c:if test="${param.pageNo!=null }">
				  						<c:forEach begin="${reservationListPage.startPage}" end="${reservationListPage.endPage}" step ="1" var="pNo">
					  						<c:if test="${param.pageNo==pNo }"><span class="selectPno">${pNo}&nbsp;</span></c:if>
			  								<c:if test="${(param.pageNo!=pNo)}"><a href="<%=request.getContextPath() %>/admin/reservationList.do?pageNo=${pNo}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'substr(mem_call,-4,4)'}&searchtext=${param.searchtext}">${pNo}&nbsp;</a></c:if>
										</c:forEach>
		  							</c:if>
				  					<%--예정 조건문 활용 다음으로 넘어갈 수 있는 겨우에는
				  							 next>> 출력 --%>
				  					<c:if test="${reservationListPage.endPage<reservationListPage.totalPage}">
								 		<a href="<%=request.getContextPath() %>/admin/reservationList.do?pageNo=${reservationListPage.startPage+10}&searchyear=${(param.searchyear>0)?param.searchyear :''}&searchmonth=${(param.month>0)?param.searchmonth:''}&searchtype=${param.searchtype!=null?param.searchtype:'substr(mem_call,-4,4)'}&searchtext=${param.searchtext}">next&gt;&gt;</a>
								 	</c:if>
				  				</td>
				  			</tr>
				   	</tbody>
			   	</table>
		   	</c:if>
			<div class="searchSubmit">
				<select name="searchtype" id="searchtype" >
					<option ${(param.searchtype == "substr(mem_call,-4,4)")?"selected":"" } value = "substr(mem_call,-4,4)">
					번호(뒷자리)
					</option> 
					<option  ${(param.searchtype == "mem_name")?"selected":"" } value = "mem_name">
					예약자명 
					</option>
				</select>
					<input type ="text" name="searchtext" id="searchtext" value="${param.searchtext}" />
					<input type ="submit" name="operate" id="serach" value="검색" />
			</div>						
		   	<div class="changeSubmit">
						<input type ="submit" name="operate" id="checkIn" value="체크인" class="operate_btn"/>
						&nbsp;
						<input type ="submit" name="operate" id="checkOut" value="체크아웃" class="operate_btn"/>
						&nbsp;
						<input type ="submit" name="operate" id="depositX" value="입금대기처리" class="operate_btn"/>
						&nbsp;
						<input type ="submit" name="operate" id="depositOk" value="입금완료처리" class="operate_btn"/>
			</div>
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