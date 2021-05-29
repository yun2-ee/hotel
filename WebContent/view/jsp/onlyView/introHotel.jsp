<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title></title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/IntroHotel.css?fsassssss1">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script type="text/javascript" src="http://maps.google.com/maps/api/js?key=AIzaSyBHMTbb3PSi-VRIRBK03vKE3nqMXEqwkk0" ></script>
   <style>
		#map_ma {width:100%; height:400px; clear:both; border:solid 1px black;}
	</style> 
   <script>
	   $(document).ready(function() {
			var myLatlng = new google.maps.LatLng(37.500886,126.884675); // 위치값 위도 경도
			var Y_point			= 37.500886;		// Y 좌표
			var X_point			= 126.884675;		// X 좌표
			var zoomLevel		= 18;				// 지도의 확대 레벨 : 숫자가 클수록 확대정도가 큼
			var markerTitle		= "SeaDog호텔";		// 현재 위치 마커에 마우스를 오버을때 나타나는 정보
			var markerMaxWidth	= 300;				// 마커를 클릭했을때 나타나는 말풍선의 최대 크기
			
			// 말풍선 내용
			var contentString	= '<div>' +
			'<h2>SeaDog호텔</h2>'+
			'</div>';
			var myLatlng = new google.maps.LatLng(Y_point, X_point);
			var mapOptions = {
								zoom: zoomLevel,
								center: myLatlng,
								mapTypeId: google.maps.MapTypeId.ROADMAP
							}
			var map = new google.maps.Map(document.getElementById('map_ma'), mapOptions);
			var marker = new google.maps.Marker({
													position: myLatlng,
													map: map,
													title: markerTitle
			});
			var infowindow = new google.maps.InfoWindow(
														{
															content: contentString,
															maxWizzzdth: markerMaxWidth
														}
					);
			google.maps.event.addListener(marker, 'click', function() {
				infowindow.open(map, marker);
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
    
    <!-- main 내용  -->
	<div class="contentguide">
		<h2 class="reviewcenter"><strong>이용안내</strong>  </h2>
		<br/>
		<div class="time">
			<h4>
			<strong>운영시간</strong><br />
			<span>매일 10:30~21:00</span>
			</h4>
		</div>
		
		<hr/>
			<ul>
				<li>SeaDog는 연중 무휴로 운영됩니다.</li>
				<li>호텔은 사전예약 시 8시부터 입실이 가능합니다.(사전예약 필수)</li>
				<li>사전입실 시 개인 상담이 어려우니 양해해 주시기 바랍니다.</li>
			</ul>
		<br/>
		<div class="call">	
		<h4><i class="fas fa-phone-alt"></i>
		<strong>상담문의</strong><br />
		<span>예약 및 상담시간 08:00 ~ 20:00</span>
		</h4>
		</div>
			<hr/>
			<ul>
				<li>Tel: 02-0000-0000</li>
			</ul>
		<br/>
		<div class="notice">	
		<h4><i class="fas fa-clipboard-list"></i>
		<strong>안내사항</strong><br />
		<span>이용 시 주의사항</span>	
		</h4>
		</div>
		<hr/>
			<ul>
				<li>
					1년 이내 5가지 접종(종합, 코로나, 켄넬코프, 신종 인플루엔자, 광견병) 받지 않은  반려견은 입실이 제한됩니다.
					<br>* 접종기록은 접종병원명이 꼭 필요하므로 증빙 자료는 미리 준비해주세요.
				</li>
				<li>
					1살 미만의 반려견인 경우 항체 검사가 필수 입니다.
					<br>(단, 1살 이상 시 재접종 기록 확인)
				</li>
				<li>생리,건강문제 또는 공격성이 강해 타 고객의 이용에 피해가 되는 반려견 일 경우 입실이 제한됩니다.</li>
			</ul>
		<br/>	
		<div class="place">
		<h4 class="come"><i ></i>
			<strong>오시는 길</strong><br />
		</h4>
		</div>
			<hr/>
			<ul class="address">
				<li class="compass"><i ></i>
					서울특별시 구로구 구로동 구로중앙로34길 33-4 (경영기술개발원 3층)
					<br /><b>※ 주차안내 : 평일주차 무료, 주말주차 30분 무료(이후 10분 당 500원)</b>
				</li>
				<li class="subway"><i ></i>
					<br />
					지하철 이용 시
					<br />1호선 (구로역 1번출구) , 도보 10분
				</li>
			</ul>
	
	<div id="map_ma" style="width:250px; height:250px;"></div>		
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