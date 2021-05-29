<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/detailReview.css">
	<title></title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		function deleteConfirm(){
	
		var confirmflag = confirm("정말로 삭제하시겠습니까?");
		//var param='';
		var param = "reviewNo=${reviewdto.review_no}&id=${reviewdto.mem_id}";
		//param += "id="+${reviewdto.mem_id};
	   if(confirmflag){
		   	
	        		 //확인 버튼 클릭 true ->삭제 그대로 진행
	        		 //location.href="http://www.nate.com";
	        		 //$(location).attr("href","http://www.naver.com");
	        		// window.location.href="/delete.do";
	        window.location.href="<%=request.getContextPath()%>/deleteReview.do?" + param;
		}
	   else{
		   return ;
		   }
	   }//deleteConfirm()
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
		<c:if test="${!empty WriterErrors}">
			<script type="text/javascript">
				alert("수정불가- 작성자가 아닙니다.");
			</script>
		</c:if>
		<form name="review" method="post">
		
			<table id="detailReview_table" style="word-break:break-all;">
				<tr>
					<td colspan="5" id="title">${reviewdto.review_title}</td>
			 	</tr>
				<tr class="tr">
					<td><span>작성자</span>${reviewdto.mem_name}</td> 
					<td><span>객실명</span>${reviewdto.room_name}</td> 
					<td><span>작성일</span>${reviewdto.review_wrday}</td> 
					<td><span>평점</span>${reviewdto.review_score}</td>
					<td><span>조회수</span>${reviewdto.readcount}</td>
				</tr>
				<tr>
					<td colspan="5" id="content"><p width="1200px" style="white-space: pre-line;">${reviewdto.review_content}</p></td>
				</tr>
			</table>
			<br><br>
		<c:if test="${AUTHUSER.id==reviewdto.mem_id||AUTHUSER.grade=='관리자'}">
			<button type="button" name="btns" value="수정" onclick="location.href='<%=request.getContextPath()%>/modifyReview.do?reviewNo=${reviewdto.review_no}&pageno=${pageNo}';">수정</button>
			<button type="button" name="btns" value="삭제" onclick="deleteConfirm()">삭제</button>
		</c:if>	
			<button type="button" name="btns" value="목록" onclick="location.href='<%=request.getContextPath()%>/reviewList.do';">목록</button>
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