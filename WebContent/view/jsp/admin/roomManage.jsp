<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
      <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css?after">
      <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/roomManage.css?ss">
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>
      $(function(){
   			$('.triple').css('display','inline-block');
   			$('.double').css('display','none');
   			$('.single').css('display','none');
   			$('#Single').click(function(){
   	   			$('.triple').css('display','none');
   	   			$('.double').css('display','none');
   	   			$('.single').css('display','inline-block');
   			});
   			$('#Double').click(function(){
   	   			$('.triple').css('display','none');
   	   			$('.double').css('display','inline-block');
   	   			$('.single').css('display','none');
   			});
   			$('#Triple').click(function(){
   	   			$('.triple').css('display','inline-block');
   	   			$('.double').css('display','none');
   	   			$('.single').css('display','none');
   			});
   			$('#Single').hover(	
				function(){	$('#Single').addClass('buttonColor')},
   				function(){$('#Single').removeClass('buttonColor')}
   			);
   			$('#Double').hover(	
				function(){	$('#Double').addClass('buttonColor')},
   				function(){$('#Double').removeClass('buttonColor')}
   			);
   			$('#Triple').hover(	
				function(){	$('#triple').addClass('buttonColor')},
   				function(){$('#triple').removeClass('buttonColor')}
   			);
   			$('#changeList').hover(	
				function(){	$('#changeList').addClass('buttonColor')},
   				function(){$('#changeList').removeClass('buttonColor')}
   			);
      });
   </script>
</head>
<body>
<div class="wrap">
	<div class="frame">
				<!-- header??????-?????????,???????????? ?????? ?????? -->
				 <jsp:include page="../../module/header.jsp" flush="false"/>
				 <!-- ????????? -->
		      	 <jsp:include page="../../module/menu.jsp" flush="false"/>
	    </div>
	    	
	    
	       <!-- main ?????? ?????? -->
<div id="content">  
	<div class="roomManageFrm">  
		<div class="changeButton">
			<input type="button" name="Single" id="Single" value="Single" class="roomChange">    
			<input type="button" name="Double" id="Double" value="Dobule" class="roomChange">    
			<input type="button" name="Triple" id="Triple" value="Triple" class="roomChange">    
		</div>
		<div class="roomFrm">
		  <form name="roomList" id="roomList" method="post" action="roomManage.do">
				<c:forEach var="room" varStatus="i" items="${roomList}">
					<div class="${room.room_name}">
			   			<table border="4">
			   				<tbody style="text-align:center">
								<tr>
			   				
				   					<th>????????????</th><td>${room.room_no}
				   						<input type="hidden" name ="changeNo" value="${room.room_no}" /></td>
								</tr>
								<tr>
				   					<th>?????????</th><td>${room.room_name}</td>
								</tr>
								<tr>
				   					<th>?????????????????????</th><td>${room.room_pet_max}</td>
								</tr>
								<tr>
				   					<th>????????????</th>
				   					<td>	
				   								<select name="roomstatus" id="roomstatus">
				   								<option value="${room.room_status}" selected>${room.room_status}</option>
				   								<option value="????????????">????????????</option>
				   								<option value="????????????">????????????</option>
				   								<option value="?????????">?????????</option>
			   								</select>
			   						</td>
								</tr>
								<tr>
				   					<th>????????????</th>
				   					<td>
				   						<input type="text" name="price" id="price" value="${room.room_price}" />
				   					</td>
								</tr>
				   				
				   					
			   				</tbody>
		  				</table>
		  				</div>
				</c:forEach>
			<div class="changeButton">
					<input type="submit"  name ="changeList"  id="changeList" value="??????"  class="roomChange" />
			</div>		
			</form>
		</div>
		</div>	
	</div>
			<!--topbtn ?????? -->
	<jsp:include page="../../module/top.jsp" flush="false"/>
	 
	
 <!--footer ?????? -->
    <div class="frame">
      <jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>
</div>
</body>
</html>