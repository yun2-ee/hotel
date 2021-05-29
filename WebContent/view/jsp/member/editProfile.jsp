<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/editProfile.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/mem_menu.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<body>

<div class="wrap">     
	<div class="frame">
				<!-- header영역-로그인,회원가입 등등 버튼 -->
				 <jsp:include page="../../module/header.jsp" flush="false"/>
				 <!-- 메뉴바 -->
		      	 <jsp:include page="../../module/menu.jsp" flush="false"/>
	</div>
	
    <!-- mem_menu 영역 확인해보기 -->
    <jsp:include page="../../module/mem_menu.jsp" flush="true"/>
	 <!-- main 내용 영역 -->
	 <div id="content">
 
 
	<div class="regPage">
	<div class="pageTitle"><img class="logo" src ="<%=request.getContextPath()%>/view/imgs/logo.png" width="60px" height="60px" /><h1 class="join">회원정보 수정</h1></div>
	<form action="editprofile.do" method="post" id="regPageFrm">
			<table id="editProFile_table">
				<tbody>
					<tr>
						<th><label for="id">아이디</label></th>
						<td><input type="text" name="id" value="${USERINFO.id}" readonly class="readOnly" />
						</td>
					</tr>
					<tr>
						<th><label for="name">	이름</label></th>
						<td><input type="text" name="name" value="${USERINFO.name}" readonly class="readOnly"/>
						</td>
					</tr>
					<tr>
						<th><label for="password">	비밀번호</label></th> 
						<td><input type="password" name="password" required />
								<c:if test="${errors.notMatchPassword}"><h4 class="checkPw">비밀번호가 일치하지 않습니다.</h4></c:if>
						</td>
					</tr>
					<tr>
						<th><label for="birthdate">		생년월일</label></th>
						<td><input type="date" name="birthdate" value="${USERINFO.birthdate}" readonly class="readOnly"/>
						</td>
					</tr>
					<tr>
						<th><label for="ponnumber">휴대폰 번호</label></th>
						<td>
								<select name="ponnumber1" >
								   <option <c:if test="${frontNum eq '010'}"> selected </c:if>  value="010">010</option>
								   <option <c:if test="${frontNum eq '011'}"> selected </c:if> value="011">011</option>
								   <option <c:if test="${frontNum eq '016'}"> selected </c:if> value="016">016</option>
								   <option <c:if test="${frontNum eq '019'}"> selected </c:if> value="019">019</option>
							   </select>
								&nbsp;-&nbsp;&nbsp;<input type="text" name="ponnumber2" value="${middleNum}" maxlength="4"/>
								&nbsp;-&nbsp;&nbsp;<input type="text" name="ponnumber3"  value="${endNum}" maxlength="4"/>
								<input type="hidden" name="ponnumber" />
								<c:if test="${errors.ponnumber}">번호를 입력학세요.</c:if>
						</td>
					</tr>
					<tr>
						<th><label for="address">주소</label></th>
						<td><input type="text" name="address" class="address" value="${USERINFO.address}" >
								<c:if test="${errors.address}">주소를 입력하세요.</c:if>
						</td>
					</tr>
					<tr>
						<th><label for="email">email</label></th>
						<td><input type="text" name="emailId" value="${email[0]}">@  
								<input type="text" name="emailSite" value="${email[1]}" readonly>	
								 <select name="emailSiteSelect">
							 		<option>선택하세요</option>
								   <option <c:if test="${email[1] eq 'naver.com'}"> selected </c:if> value="naver.com">naver.com</option>
								   <option <c:if test="${email[1] eq 'daum.net'}"> selected </c:if> value="daum.net">daum.net</option>
								   <option <c:if test="${email[1] eq 'gmail.com'}"> selected </c:if> value="gmail.com">gmail.com</option>
								   <option <c:if test="${email[1] eq 'nateon.com'}"> selected </c:if> value="nateon.com">nateon.com</option>
								   <option <c:if test="${email[1] eq 'hanmail.com'}"> selected </c:if> value="hanmail.com">hanmail.com</option>
								   <option>직접입력</option>
							   </select>
							   <input type="hidden" name="email">
										<c:if test="${errors.email}">email를 입력하세요.</c:if>
						</td>
					</tr>
	
					</tbody>
			</table>
	
			
			
			<br/>
			<c:if test="${!empty petList}">
				<c:set var="petMaxs" value="${fn:length(petList)+1}" />
			</c:if>
			<c:if test="${empty petList}">
				<c:set var="petMaxs" value="1" />
			</c:if>
			<div class="petButton">		
				<br/>
				<input type="button" id="petsend" name="petsend" value="펫 정보 추가" class="operate_btn" />
				<input type="button" name="petdelete" id="petdelete" value="펫 정보 제거" class="operate_btn" />
				<h4>최대 3마리까지 가능합니다.</h4>
				<br/>
				<br/>
			</div>	
			
			
	<div id="pet" class="petCardContainer">
		<c:forEach var="petList" varStatus="i" items="${petList}">
				<div id="petnumber${i.count}">
					<c:set var ="petListNum" value="petnumber${i}" />
					<table class="petCard"> 
						<tbody> 
							<tr> 
								<th>
									<label for="pname">펫 이름</label>
								</th> 
								<td>
								<input type="text" name="pname" value="${petList.pname}" required="required"/> 
									<c:if test="${errors.pname}">펫이름을 입력하세요.</c:if> 
								</td> 
								</tr> 
							<tr> 
								<th>
									<label for="pbirth">펫 생일</label>
								</th> 
								<td>
									<input type="date" name="pbirth"  value="${petList.pbirth}" required="required" /> <c:if test="${errors.pbirth}">반려동물 생일을 입력하세요.</c:if> 
								</td> 
							</tr> 
							<tr> 
								<th>
									<label for="weight">펫 몸무게</label>
								</th> 
								<td>
									<input type="text" name="weight" placeholder="kg단위로 작성" value="${petList.weight}" required="required" /> <c:if test="${errors.weight}">반려동물 몸무게를 입력하세요.</c:if> 
								</td> 
							</tr> 
							<tr> 
								<th>
									<label for="neuter" >중성화수술 여부</label>
								</th>
								<td>
									<input type="checkbox" <c:if test="${petList.neuter eq '예'}">checked</c:if>  name="neuter" value="예">예
									<input type="checkbox"  <c:if test="${petList.neuter eq '아니오'}">checked</c:if> name="neuter" value="아니오" >아니오 
									<c:if test="${errors.neuter}">증성화수술 여부를 체크하세요.</c:if> 
								</td> 
							</tr> 
							<tr> 
								<th>
									<label for="medical">접종 여부</label>
								</th> 
								<td>
									<select name="medical"> 
										<option value="해당없음" <c:if test="${petList.medical eq '해당없음'}">selected</c:if> >해당없음</option>
										<option value="1차" <c:if test="${petList.medical eq '1차'}">selected</c:if>>1차</option>
										<option value="2차" <c:if test="${petList.medical eq '2차'}">selected</c:if>>2차</option>
										<option value="3차" <c:if test="${petList.medical eq '3차'}">selected</c:if>>3차</option>
										<option value="4차" <c:if test="${petList.medical eq '4차'}">selected</c:if>>4차</option>
										<option value="5차" <c:if test="${petList.medical eq '5차'}">selected</c:if>>5차</option>
									</select>
									<c:if test="${errors.medical}">종합 접종 여부를 체크하세요.</c:if> 
								</td> 
							</tr> 
							<tr> 
								<th>
									<label for="memo">memo</label>
								</th> 
								<td>
									<input type="text" name="memo" value="${petList.memo}"/> <c:if test="${errors.memo}"></c:if> 
								</td> 
							</tr> 
						</tbody> 
					</table>
				</div>
		</c:forEach>
	</div>
		<div class="submitButton">
			<input type="submit" value="변경" class="operate_btn" />
		</div>
	</form>
	</div>
	
	
	<script>
		
	$(function(){
		var petMax = ${petMaxs};
		var name = "petnumber";
		var idNum=1;	
					$('#petsend').click(function(){
						
						if(petMax<=3){
						
							id = name+petMax;
							var str= '<div id="'+id+'"><table class="petCard"> <tbody> <tr> <th><label for="pname">펫 이름</label></th> <td><input type="text" name="pname" value="cat" required="required"/> <c:if test="${errors.pname}">펫이름을 입력하세요.</c:if> </td> </tr> <tr> <th><label for="pbirth">펫 생일</label></th> <td><input type="date" name="pbirth" required="required"/> <c:if test="${errors.pbirth}">반려동물 생일을 입력하세요.</c:if> </td> </tr> <tr> <th><label for="pname">펫 몸무게</label></th> <td><input type="text" name="weight" placeholder="kg단위로 작성" value="30" required="required"/> <c:if test="${errors.weight}">반려동물 몸무게를 입력하세요.</c:if> </td> </tr> <tr> <th><label for="neuter" >중성화수술 여부</label></th> <td><input type="checkbox" name="neuter" value="예" />예 <input type="checkbox" name="neuter" value="아니오" checked/>아니오 <c:if test="${errors.neuter}">증성화수술 여부를 체크하세요.</c:if> </td> </tr> <tr> <th><label for="medical">접종 여부</label></th> <td><select name="medical"> <option value="해당없음" selected>해당없음</option><option value="1차">1차</option><option value="2차">2차</option><option value="3차">3차</option><option value="4차">4차</option> <option value="5차">5차</option> <c:if test="${errors.medical}">종합 접종 여부를 체크하세요.</c:if> </td> </tr> <tr> <th><label for="memo">memo</label></th> <td><input type="text" name="memo" value=""/> <c:if test="${errors.memo}"></c:if> </td> </tr> </tbody> </table></div>'
						 	$(str).appendTo('#pet');
							petMax += 1;
							
							var idSelector="#"+id; 
							  $(idSelector+' input[type="checkbox"]').on('click', function() {
				                  $(idSelector+' input[type="checkbox"]').not(this).prop("checked", false);
							});
						}
				});
					
				$('#petnumber1 input[type="checkbox"]').on('click', function() {
	                      $('#petnumber1 input[type="checkbox"]').not(this).prop("checked", false);
          		});
					 
				$('#petnumber2 input[type="checkbox"]').on('click', function() {
	                      $('#petnumber2 input[type="checkbox"]').not(this).prop("checked", false);
          		});
					 
				$('#petnumber3 input[type="checkbox"]').on('click', function() {
	                      $('#petnumber3 input[type="checkbox"]').not(this).prop("checked", false);
          		});
					 
				$('#petdelete').on('click',function(){
						if(petMax>1){
							petMax -=1;
							deleteIdSelector = "#"+name+petMax;
							$(deleteIdSelector).remove();
						}
				});	
					
					$("select[name='emailSiteSelect']").change(function(){
					var idxSelect = $("select[name='emailSiteSelect'] option").index( $("select[name='emailSiteSelect'] option:selected"));
					 selectVal=$("select[name='emailSiteSelect']").val();
						if(idxSelect==6){
							$("input[name='emailSite']").prop('value',"");
							$("input[name='emailSite']").attr('readonly',false);
						}else{
							$("input[name='emailSite']").prop('value',selectVal);
							$("input[name='emailSite']").attr('readonly',true);
							
						}
					});
	 
					
					
					$("#regPageFrm").submit(function(){
						if(!$("input[name='ponnumber2']").val()){
							alert("휴대폰 번호 필수입력입니다");
							$("input[name='ponnumber2']").focus();//포커스위치
							return false;//함수종료
						}
						if(!$("input[name='ponnumber3']").val()){
							alert("휴대폰 번호 필수입력입니다");
							$("input[name='ponnumber3']").focus();//포커스위치
							return false;//함수종료
						}
						var phonnumber = $("select[name='ponnumber1']").val()+"-"+$("input[name='ponnumber2']").val()+"-"+$("input[name='ponnumber3']").val();
						$("input[name=ponnumber]").attr('value',phonnumber);
						
						
						if(!$("input[name='emailId']").val()){
							alert("이메일 필수입력입니다");
							$("input[name='emailId']").focus();//포커스위치
							return false;//함수종료
						}	
						if(!$("input[name='emailSite']").val()){
							alert("이메일 사이트 필수입력입니다");
							$("input[name='emailId']").focus();//포커스위치
							return false;//함수종료
						}
						var idx = $("select[name='emailSiteSelect'] option").index( $("select[name='emailSiteSelect'] option:selected"));
						var emailfull = $("input[name='emailId']").val()+"@"+$("input[name='emailSite']").val();
						$("input[name='email']").attr('value',emailfull);
						
						
						});
			});	
	</script>
	
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