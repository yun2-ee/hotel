<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title> 
<link href="<%=request.getContextPath()%>/view/css/adminMember.css?ffhjhhhhj" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
</head>
<script> 
	$(function(){
			
	
		
	});
</script>


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
  
	<h1>회원 목록</h1> 
	<form action="<%=request.getContextPath()%>/admin/list.do" class="seachform">
		<strong>ID</strong>	<input type="text" name="seach" id="searchtext" >
			<input type="submit" value="검색" id="serach" name="seachbt">
			<br/><br/>	
	</form>   
		<table class="table2" id="join_table">
			<tr class="title">
				<th id="id">ID</th>
				<th id="name">이름</th>
				<th id="hp">연락처</th>
				<th id="adr">주소</th>
				<th id="grade">회원등급</th>
			</tr>
			
			<c:if test="${memberPage.hasNoMembers()}">
				<tr>
					<td colspan="6">회원이 없습니다</td>
				</tr>
			</c:if>
			
			<!-- 회원 정보 가져옴 -->
			<c:forEach var="member" items="${memberPage.content}">
				<tr>
					<td id="memid">${member.id}</td>
					<td id="memname">${member.name}</td>
					<td id="memhp">${member.hp}</td>
					<td id="memadr">${member.addr}</td>
					 
					<td>
					<form action="<%=request.getContextPath()%>/admin/memupdate.do" method="post">
						<input type="hidden" name="id" id="id" value="${member.id}">
						<select name="newgrade" id="newgrade" >
							<option value="${member.grade}" selected>${member.grade}</option>
							<option value="일반">일반</option>
							<option value=휴면>휴면</option>
							<option value="탈퇴">탈퇴</option>
							<option value="관리자">관리자</option>
						</select>
						<input type="submit" value="등급변경" id="gradeSuccess" name="gradeSuccess" class= "operate_btn" style="float: right; ">
						<input type="button" value="펫정보조회" id="petList" name="petList" class= "operate_btn" style="float: right; " onClick="location.href='<%=request.getContextPath() %>/admin/list.do?id=${member.id}'" />
					</form>
					</td>
				</tr>
			</c:forEach>
		</table>

	<!--	페이징 처리 -->
	<c:if test="${memberPage.hasMembers()}">
		<div style="margin-top: 30px; text-decoration:none; text-align:center;">
			<c:if test="${memberPage.endPage>1}">
				<c:if test="${memberPage.startPage>6}">
					<a href="<%=request.getContextPath()%>/admin/list.do?pageNo=${memberPage.startPage-5}">&lt;</a>
				</c:if>
				
				<c:forEach begin="${memberPage.startPage}" end="${memberPage.endPage}" step="1" var="pNo">
					<a href="<%=request.getContextPath()%>/admin/list.do?pageNo=${pNo}">${pNo}</a>
				</c:forEach>
				
				<c:if test="${memberPage.endPage<memberPage.totalPages}">
					<a href="<%=request.getContextPath()%>/admin/list.do?pageNo=${memberPage.startPage+5}">&gt;</a>
				</c:if>
			</c:if>
		</div>
	</c:if>
		<c:if test="${!empty petList}">
			<div class="petList">
							<h2 class="petOner">${petOrner}님의 애견 정보</h2>
			<c:forEach var="petList" items="${petList}" >				
				<table class="PetIdCard">
					<tbody>
						<tr>    
							<td  class="dogIcon"><img src="<%=request.getContextPath()%>/view/imgs/dog.png" style="width:70px; height:70px;" /></td>
							<td><h4 class="petListTd">이름</h4><br/>${petList.pname}<hr/></td>
						</tr>
						<tr>
							<td><h4 class="petListTd">생일</h4><br/>${petList.pbirth}<hr/></td>			
							<td><h4 class="petListTd">몸무게</h4><br/>${petList.weight}kg<hr/></td>
						</tr>
						<tr>
							<td><h4 class="petListTd">중성화 여부</h4><br/> ${petList.neuter}<hr/></td>
							<td><h4 class="petListTd">접종 여부</h4><br/>  ${petList.medical}<hr/></td>
						</tr>
						<tr>
							<td colspan="2" class="memoContent"><h4 class="petListTd">MEMO</h4><br/>${petList.memo}</td>
						</tr>
					</tbody>
				</table>
			</c:forEach>
			</div>
		</c:if>
	</div>	
	
		<!--topbtn 영역 -->
	<jsp:include page="../../module/top.jsp" flush="false"/>
	 
 <!--footer 영역 -->
    <div class="frame">
      <jsp:include page="../../module/footer.jsp" flush="false"/>
    </div>
</div>   
</body>
</html>