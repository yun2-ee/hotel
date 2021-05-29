<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>

   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title> 
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/Main.css">	
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyBHMTbb3PSi-VRIRBK03vKE3nqMXEqwkk0" ></script>
   <script>
      $(function(){
   		$('.mainImg').hover(
   				function(){$('#Double').addClass('buttonColor')},
   				function(){$('#Double').removeClass('buttonColor')}		
   		)
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
	<div class="mainView">
	   	<div class="mainImg">
	   		<img src="<%=request.getContextPath()%>/view/imgs/dog2.jpg" width="100%" height="800px" /> 
   		</div>
	   	<div class="mainImgText">
	   		<h1 class="mainContent">사랑하는 내 아이를 위한</h1> 
	   		<h1 class="mainContent2">최고의 경험</h1>
	   	</div>
		<div class="mainImgContainer"></div>
   		
   		<div class="firstMainText">
	   		<div class="firstMainTextCenter">
		   		<span id="top_title">SeaDog</span> 의 <br>
		   		<h1>좋은 서비스</h1>
				<img src="<%=request.getContextPath()%>/view/imgs/arrow.png" /><br>
					싱글룸부터 트리플룸까지!
		   		<br/> 수준높은 케어
		   		<br/> 반려동물이 좋아하는 환경제공하는
		   		<br/> 프리미엄 반려동물 호텔
	   		</div>
   		</div> 
 	
		<div class="roomFirst">  	
		   	<div class="middleImg">
				<div class="roomlink"><a href="<%= request.getContextPath()%>/onlyView/introRoom.do">객실</a></div>
				<img src="<%=request.getContextPath()%>/view/imgs/arrow.png" /><br>
		  		<div class="roomName"><span>싱글룸</span></div><div class="roomName"><span>더블룸</span></div><div class="roomName"><span>트리플룸</span><br/></div>
		   		<div class="roomImgContainer">
			   		<img src="<%=request.getContextPath()%>/view/imgs/room1in.jpg" width="350px" height="350px" class="roomImg" />
			   	</div> 
			   	<div class="roomImgContainer"> 	 
			   		<img src="<%=request.getContextPath()%>/view/imgs/room2in.jpg" width="350px" height="350px" class="roomImg" />
			   	</div>  
			   	<div class="roomImgContainer">	 
			   		<img src="<%=request.getContextPath()%>/view/imgs/room3in.jpg" width="350px" height="350px" class="roomImg" />
			   	</div>	
		   </div> 
	 	</div>  	

	   	<div class="faImgContainer">
			<div class="falink">
				<span id="top_title">SeaDog</span> 의 <br>
		   		<h1>행복한 공간</h1>
		   		<img src="<%=request.getContextPath()%>/view/imgs/arrow.png" /><br>
		   	</div>
	  		<div class="fa">대기실<span class="eng"> WaitingRoom</span class="eng"></div><div class="fa">교육 <span class="eng"> Education</span></div><div class="fa">공원 <span class="eng">Park</span></div><div class="fa">스파 <span class="eng">Spa</span><br/></div>
		   		<img src="<%=request.getContextPath()%>/view/imgs/main2-4.jpg" width="280px" height="280px" class="faImg" />
		   		<img src="<%=request.getContextPath()%>/view/imgs/main2-2.jpg" width="280px" height="280px" class="faImg" />
		   		<img src="<%=request.getContextPath()%>/view/imgs/main2-3.jpg" width="280px" height="280px" class="faImg" />
		   		<img src="<%=request.getContextPath()%>/view/imgs/main2-1.jpg" width="280px" height="280px" class="faImg" />
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