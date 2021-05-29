<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>      
<!DOCTYPE html>
<html>
<head>
<meta charset="${encoding}">
<meta name="viewport" content="widtd=device-widtd, initial-scale=1.0">
<title>예약하기</title>

 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/reserveChange.css?fghhghtf">
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script> 
   $(function(){

	   	  var slider = $("#numofdogs");
    	  var result = $("#value");
		 
		  result.html(slider.val());// 초기값 표시
    	  //객실명이 선택되면 투숙수의 최대값이 정해진다.
   		  $('.user_room').on('click', function() {
    		    var room = $('.user_room:checked').val(); 
			
				//초기화
    		    slider.val(1);
				result.html(slider.val());
				
				<c:forEach var="roomList" items="${ROOMS}">
				if (room == "${roomList.r_name}") {
					slider.attr('max', "${roomList.r_pet_max}");
    		    } 
				</c:forEach>
			});
    	 
		 //range에다가 실시간 반영
    	  slider.on('input', function() {
		  		result.html( $(tdis).val());
			});
		
    	  //입실날짜와 오늘 날짜 비교	
    	  $('#firstday').on('blur', function() {
    		  var today = new Date();
    		  var firstday = new Date($('#firstday').val());
    		  if(firstday < today){
    			  alert("입실은 다음날부터 가능합니다.");
    			  $('#firstday').val('');
    		  }
    	  });//$('#firstday').on('blur')
    	  
    	  //입실시간, 퇴실시간을 작성하면 checkhandler로 넘어가서 결과를 출력한다. 
    	  $('#lastday').on('blur', function() {
    		  
  		      var roomName =  $('.user_room:checked').val(); 
  		      var userDog =  $('#numofdogs').val(); 
  		      var firstday = $('#firstday').val(); 
    		  var lastday = $('#lastday').val(); 
			  
    		  //입실,퇴실 날짜가 안 맞을때
    		  if(firstday > lastday){
    			  alert("입실시간과 퇴실 시간을 다시 정해주세요.");
    			  $('#firstday').val('');
    			  $('#lastday').val(''); 
    			  return 0;
    		  }
    		
    		  //확인전에 내용이 하나라도 비어있을 경우 다시 작성하러보냄.
    		  if(roomName=='' || userDog==''  || firstday==''){
    			  location.href="<%=request.getContextPath()%>/admin/reserveChange.do?no=${no}&btn=0";
    		  }
    		  else{
	    		  var param = "room="+roomName+"&check_in="+firstday+"&check_out="+lastday+"&numofdogs="+userDog +"&btn=1&no=${no}";
				  location.href="<%=request.getContextPath()%>/admin/reserveChange.do?" + param ;
			  }
  		  }); // $('#lastday').on('blur')
  
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
 
 		<h1>예약 변경</h1>
		<div id="left">
			<div id="explanations">
			<h2 class="beforeChange">현재 예약상황</h2><br/><br/>
				<table>
				<tbody>
					<tr>
						<td>예약자명</td>
						<td>${rsDto.mem_name}</td>
					</tr>
					<tr>
						<td>번호</td>
						<td>${rsDto.mem_call}</td>
					</tr>
					<tr>
						<td>투숙 강아지 수</td>
						<td>${rsDto.reserve_pet_number}</td>
					</tr>
					<tr>
						<td>객실명</td>
						<td>${rsDto.room_name}</td>
					</tr>
					<tr>
						<td>투숙기간</td>
						<td><fmt:formatDate pattern  ="yyyy.MM.dd" value="${rsDto.reserve_start}" /> ~ <fmt:formatDate pattern  ="yyyy.MM.dd" value="${rsDto.reserve_end}" /> </td>
					</tr>
					<tr>
						<td>금액</td>
						<td>${rsDto.reserve_price}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div id="right">
		<!-- 예약하기 폼-->
		<h2>변경 사항</h2>
		<div id="reservationForm">
			<form name="reservationChange"  action= "./reserveChange.do" method="post">
				<input type ="hidden" name="no" value="${no}" />
				<table>
				     <tr>
						 <td>객실</td>
						 <td>
							<div class="containerOuter">
							  <div class="container">
							    <input type="radio" class="user_room" id="input1" name="room" value="single" ${param.room=="single"?"checked":""}>
							    <label class="entry" for="input1"><div class="circle"></div><div class="entry-label">SINGLE</div></label>
							    <input type="radio" class="user_room" id="input2" name="room" value="double" ${param.room=="double"?"checked":""}>
							    <label class="entry" for="input2"><div class="circle"></div><div class="entry-label">DOUBLE</div></label>
							    <input type="radio" class="user_room" id="input3" name="room" value="triple"  ${param.room=="triple"?"checked":""}>
							    <label class="entry" for="input3"><div class="circle"></div><div class="entry-label">TRIPLE</div></label>
							    <div class="highlight"></div>
							    <div class="overlay"></div>
							  </div>
							</div>
						</td>
					</tr> 
					<tr> 
						<td>투숙 강아지수 </td>
						<td>
							<input type="range" name="numofdogs" id ="numofdogs" min="1"  max="3" step="1"  value = "${param.numofdogs}" />
				       		<span id="value">${param.numofdogs}</span> 
			        	</td>
					</tr>
					<tr>
							<td rowspan="2">숙박기간</td>
							<td>입실 <input type="date" id="firstday" name="check_in" value = "${param.check_in}"></td>
					</tr>
					<tr>
							<td>퇴실 <input type="date" id="lastday" name="check_out" onchange="check()" value = "${param.check_out}"></td>
					</tr>
					<tr>
							<td>예약자명 </td>
							<td>${RESERVATION.user.name}</td>
					</tr>
					<tr>
							<td>투숙기간 </td>
							<td>${RESERVATION.userReq.checkIn} 
							<c:if test="${!empty RESERVATION}"> ~ </c:if>
							 ${RESERVATION.userReq.checkOut} </td>
					</tr>
					<tr>
							<td>객실명</td>
							<td>${RESERVATION.userReq.roomName} </td>
					</tr>
					<tr>
							<td>금액</td>
							<td>${RESERVATION.resultRoomdto.price} </td>
					</tr>
					<tr>
							<td  colspan="2">
								<button type="submit" class="reserve_btns" name="reserveBtns" value="변경하기">변경하기</button>
								<button type="button"  class="reserve_btns" value="home" onclick="location.href='<%=request.getContextPath()%>/index.jsp';">뒤로가기</button>
							</td>
					</tr>
				</table>
			</form>
			<!-- 객실없을때 에러창 -->
			<c:if test="${!empty RESERVEERRORS.ROOMERRORS}">
				<script type="text/javascript">
					alert("객실 없습니다."); 
				 	location.href="<%=request.getContextPath()%>/reserve.do?btn=0";</script>
			</c:if>
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