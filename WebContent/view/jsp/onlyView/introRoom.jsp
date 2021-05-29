<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/IntroRoom.css?sdfgggfsa2">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
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
    
     <!-- main 내용 -->
    <div class="introContainer">
    	<div class="serviceTitle">
    		<h2>“최고의 힐링 공간, 호텔&데이케어”<br/> </h2>
			<h3>최첨단 공조시스템(음압설비)이 적용된 국내 최고의 5성급 호텔 서비스를 제공합니다.</h3>
		</div>  
		
		<div class="serviceFile">				
			<div class="serviceBox">			
				<div class="serviceimage"><img src="<%=request.getContextPath()%>/view/imgs/service1.jpg" alt="" class="service_img">
				</div>
					<div class="servicedesc">
						<div class="servicetitle">
							<h3>24시간 전문가 상주</h3>
						</div>
						<div class="servicecontent">
							<p>전문가가 24시간 상주하고 있으므로<br>안심하고 맡기실 수 있습니다.</p>
						</div>									
					</div>
		</div>
			
			<div class="serviceBox">	
			<div class="serviceimage"><img src="<%=request.getContextPath()%>/view/imgs/service2.jpg" alt="" class="service_img"></div>
				<div class="servicedesc">
					<div class="servicetitle">
						<h3>적응 교육</h3>
					</div>
					<div class="servicecontent">
						<p>반려견이 새로운 환경에도 쉽게<br>적응할 수 있도록 특성에 맞는<br>교육 서비스를 제공합니다.</p>
					</div>									
				</div>
			</div>
			<div class="serviceBox">
			<div class="serviceimage"><img src="<%=request.getContextPath()%>/view/imgs/service3.jpg" alt="" class="service_img"></div>
				<div class="servicedesc">
					<div class="servicetitle">
						<h3>넓은 여유 공간</h3>
					</div>
					<div class="servicecontent">
						<p>주기적으로 넓은 공간에서<br>놀이나 배변 활동을 할 수 있습니다.</p>
					</div>									
				</div> 
			</div>
			<div class="serviceBox">	
			<div class="serviceimage"><img src="<%=request.getContextPath()%>/view/imgs/service4.jpg" alt="" class="service_img"></div>
				<div class="servicedesc">
					<div class="servicetitle">
						<h3>데일리 수의사 회진</h3>
					</div>
					<div class="servicecontent">
						<p>반려견의 일상 건강 관리가<br>가능합니다.</p>
					</div>						 			
				</div>
			</div>
    	</div>  
    	
    	
    	<div class=roomIntroText><h1>객실 안내</h1><br/></div>
		<div class="roomContainer">
			<div class="singleRoom">
				<div class="singleRoomImg col_half"><img src="<%=request.getContextPath()%>/view/imgs/room1in.jpg"  class="roomImg"></div>
				<div class="singleRoomText">		
					<br/><br/>		
					<h2>싱글룸</h2>
					<p>한 마리의 강아지가 이용가능한 룸입니다.</p>
					<p>소형견이 사용하기에 적합합니다.</p>
					<p>이용요금 : 3만원</p>
				</div>
			</div>				
			<div class="doubleRoom">
				<div class="doubleRoomImg col_half"><img src="<%=request.getContextPath()%>/view/imgs/room2in.jpg"  class="roomImg" ></div>
				<div class="doubleRoomText">		
					<br/><br/>
					<h2>더블룸</h2>
					<p>1~2 마리의 강아지가 이용가능한 룸입니다.</p>
					<p>중형견이 사용하기에 적합합니다.</p>
					<p>이용요금 : 6만원</p>
				</div>
			</div>				
			<div class="tripleRoom">
				<div class="tripleRoomImg col_half"><img src="<%=request.getContextPath()%>/view/imgs/room3in.jpg"  class="roomImg"  ></div>
					<div class="tripleRoomText">	
						<br/><br/>
						<h2>트리플룸</h2>
						<p>2~3 마리의 강아지가 이용가능한 룸입니다.</p>
						<p>대형견이 사용하기에 적합합니다.</p>
						<p>이용요금 : 7만원</p>
					</div>
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