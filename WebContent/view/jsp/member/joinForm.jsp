<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/joinForm.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
	var petMax = 1;
	var name = "petnumber";
	var idNum=1;	
	$(function(){
						
				$('#petsend').click(function(){

						
						if(petMax<=3){
						
							id = name+petMax;
							var str= '<div id="'+id+'"><table class="petCard"> <tbody> <tr> <th><label for="pname">펫 이름</label></th> <td><input type="text" name="pname" value="cat" required="required"/> <c:if test="${errors.pname}">펫이름을 입력하세요.</c:if> </td> </tr> <tr> <th><label for="pbirth">펫 생일</label></th> <td><input type="date" name="pbirth" required="required"/> <c:if test="${errors.pbirth}">반려동물 생일을 입력하세요.</c:if> </td> </tr> <tr> <th><label for="pname">펫 몸무게</label></th> <td><input type="text" name="weight" placeholder="kg단위로 작성" value="" required="required"/> <c:if test="${errors.weight}">반려동물 몸무게를 입력하세요.</c:if> </td> </tr> <tr> <th><label for="neuter" >중성화수술 여부</label></th> <td><input type="checkbox" name="neuter" value="예" />예 <input type="checkbox" name="neuter" value="아니오" checked/>아니오 <c:if test="${errors.neuter}">증성화수술 여부를 체크하세요.</c:if> </td> </tr> <tr> <th><label for="medical">접종 여부</label></th> <td><select name="medical"> <option value="해당없음" selected>해당없음</option><option value="1차">1차</option><option value="2차">2차</option><option value="3차">3차</option><option value="4차">4차</option> <option value="5차">5차</option> <c:if test="${errors.medical}">종합 접종 여부를 체크하세요.</c:if> </td> </tr> <tr> <th><label for="memo">memo</label></th> <td><input type="text" name="memo" value=""/> <c:if test="${errors.memo}"></c:if> </td> </tr> </tbody> </table></div>'
						 	$(str).appendTo('#pet');
							petMax += 1;
							
							
							var idSelector="#"+id; 
							  $(idSelector+' input[type="checkbox"]').on('click', function() {
	                              $(idSelector+' input[type="checkbox"]').not(this).prop("checked", false);
                          		});
						}
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
					
				
					
					var user_id = $("input[name='id']").val();
					user_id = $.trim(user_id);
					
					if( !user_id  ){
						alert("id필수입력입니다");
						$("input[name='']").focus();//포커스위치
						return false;//함수종료
					}
					
					if( user_id.length<6 || user_id.length>12){
						alert("id는 6글자이상 12글이하이어야 합니다");
						$("input[name='id']").val(""); //초기화
						$("input[name='id']").focus();//포커스위치
						return false;//함수종료
					}
					
					
					if(!$("input[name='name']").val()){
						alert("이름은 필수 입력입니다.");
						$("input[name='name']").focus();//포커스위치
						return false;//함수종료
					}
					
					if(!$("input[name='password']").val()){
						alert("비밀번호 필수입력입니다");
						$("input[name='password']").focus();//포커스위치
						return false;//함수종료
					}
					
					
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
<meta charset="UTF-8">
<title>가입</title>
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
 
		<div class="regPage">
			<form action="join.do" method="post" id="regPageFrm">
			<div class="personReg">
			 		<div class="pageTitle"><img class="logo" src ="<%=request.getContextPath()%>/view/imgs/logo.png" width="60px" height="60px" /><h1 class="join">회원가입</h1></div>
					<table id="join_table">
						<tbody>
							<tr>
								<th><label for="id">아이디</label></th>
								<td><input type="text" name="id" value=""/>
									<c:if test="${errors.id}">ID를 입력하세요.</c:if>
									<c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="name">	이름</label></th>
								<td><input type="text" name="name" value=""/>
										<c:if test="${errors.name}">이름을 입력하세요.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="password">	비밀번호</label></th>
								<td><input type="password" name="password" value=""/>
										<c:if test="${errors.passsword}">비밀번호을 입력하세요.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="confirmPassword">	비밀번호 확인</label></th>
								<td><input type="password" name="confirmPassword" value=""/>
															<c:if test="${errors.confirmPassword}">확인을 입력하세요.</c:if>
								<c:if test="${errors.notMatch}">암호가 불일치 합니다.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="birthdate">		생년월일</label></th>
								<td><input type="date" name="birthdate"/>
													<c:if test="${errors.birthdate}">생년월일을 입력학세요.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="ponnumber">휴대폰 번호</label></th>
								<td>
										<select name="ponnumber1">
										   <option selected value="010">010</option>
										   <option value="011">011</option>
										   <option value="016">016</option>
										   <option value="019">019</option>
									   </select>
										&nbsp;-&nbsp;&nbsp;<input type="text" name="ponnumber2" maxlength="4"/>
										&nbsp;-&nbsp;&nbsp;<input type="text" name="ponnumber3" maxlength="4"/>
										<input type="hidden" name="ponnumber" />
										<c:if test="${errors.ponnumber}">번호를 입력학세요.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="address">주소</label></th>
								<td><input type="text"  class="address" name="address" value="">
										<c:if test="${errors.address}">주소를 입력하세요.</c:if>
								</td>
							</tr>
							<tr>
								<th><label for="email">email</label></th>
								<td><input type="text" name="emailId" value="">&nbsp;@  
										<input type="text" name="emailSite" readonly>	
										 <select name="emailSiteSelect">
									 		<option>선택하세요</option>
										   <option value="naver.com">naver.com</option>
										   <option value="daum.net">daum.net</option>
										   <option value="gmail.com">gmail.com</option>
										   <option value="nateon.com">nateon.com</option>
										   <option value="hanmail.com">hanmail.com</option>
										   <option>직접입력</option>
									   </select>
									   <input type="hidden" name="email">
												<c:if test="${errors.email}">email를 입력하세요.</c:if>
								</td>
							</tr>
			
							</tbody>
					</table>  
			 </div>  
			<div class="petButton">		
					<br/>
					<input type="button" class="operate_btn" id="petsend" name="petsend" value="펫 정보 추가">
					<input type="button" class="operate_btn" name="petdelete" id="petdelete" value="펫 정보 제거">
					<h4>최대 3마리까지 가능합니다.</h4> 
					<br/>
					<br/>
					
					
					<br/>
			</div>		
			<div id="pet" class="petCardContainer">
			
			</div> 
			<div class="submitButton">
				<input type="submit" value="가입" class="operate_btn" />
			</div>
			</form>
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