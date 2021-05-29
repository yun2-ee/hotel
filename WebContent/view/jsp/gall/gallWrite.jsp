<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/frame.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/view/css/gallWrite.css?ss">
   <title></title>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script>
   $(function() {
			$("#img1").on('change',function(e){
				$('#imgs1').remove();
				 var files = e.target.files[0];	
				readURL1(files);
			});
		   function readURL1(f){
				   var fileName = f.name;
				   fileName= fileName.replace(/(\s*)/g, "");
				   if(fileName.length>10){
					   fileName =fileName.substr(0,6)+"...";
				   }
				   var str = '<div id="imgs1" style="display: inline-flex; padding:10px;"><li>';
				   str += '<span>'+fileName+'</span><br>';
				   if(f.type.match('image.*')){
					   var reader = new FileReader();
					   reader.onload = function (e){
						   str += '<img src="'+e.target.result+'" title="'+f.name+'" width=200 height=200 />';
						   str += '</li></div>;'
						   $(str).appendTo('#img');
					   }
					   reader.readAsDataURL(f);
				   }else{
					   str += '<img src="http://placehold.it/250x200" title="'+f.name+'" width=200 height=200 />';
					   $(str).appendTo('#img');
				   }
		   }
		   
		   
			$("#img2").on('change',function(e){
				$('#imgs2').remove();
				 var files = e.target.files[0];	
				readURL2(files);
			});
		   function readURL2(f){
				   var fileName = f.name;
				   fileName= fileName.replace(/(\s*)/g, "");
				   if(fileName.length>10){
					   fileName =fileName.substr(0,6)+"...";
				   }
				   var str = '<div id="imgs2" style="display: inline-flex; padding:10px;"><li>';
				   str += '<span>'+fileName+'</span><br>';
				   if(f.type.match('image.*')){
					   var reader = new FileReader();
					   reader.onload = function (e){
						   str += '<img src="'+e.target.result+'" title="'+f.name+'" width=200 height=200 />';
						   str += '</li></div>;'
						   $(str).appendTo('#img');
					   }
					   reader.readAsDataURL(f);
				   }else{
					   str += '<img src="http://placehold.it/250x200" title="'+f.name+'" width=200 height=200 />';
					   $(str).appendTo('#img');
				   }
		   }
		   
		   
		   
			$("#img3").on('change',function(e){
				$('#imgs3').remove();
				 var files = e.target.files[0];	
				readURL3(files);
			});
		   function readURL3(f){
				   var fileName = f.name;
				   fileName= fileName.replace(/(\s*)/g, "");
				   if(fileName.length>10){
					   fileName =fileName.substr(0,6)+"...";
				   }
				   var str = '<div id="imgs3" style="display: inline-flex; padding:10px;"><li>';
				   str += '<span>'+fileName+'</span><br>';
				   if(f.type.match('image.*')){
					   var reader = new FileReader();
					   reader.onload = function (e){
						   str += '<img src="'+e.target.result+'" title="'+f.name+'" width=200 height=200 />';
						   str += '</li></div>;'
						   $(str).appendTo('#img');
					   }
					   reader.readAsDataURL(f);
				   }else{
					   str += '<img src="http://placehold.it/250x200" title="'+f.name+'" width=200 height=200 />';
					   $(str).appendTo('#img');
				   }
		   }	   
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
	   <form name="gallWriteForm" id="gallWriteForm" method="post" action="gallWrite.do" enctype="multipart/form-data">
	   		<table width="1200px">
	   			<tr>
	   				<th colspan="2" style="text-align:left"><input type="text" name="title" id="title" placeholder="제목을 입력해주세요" size="100" autofocus required="required"></th>
	   			</tr>
	   			<tr>
	   				<td width="50px"><input type="file" name="img1" id="img1" /></td>
					<td class="mb">이미지 첨부 용량 제한 : 10MB</td>
	   			</tr>
	   			<tr>
	   				<td width="50px"><input type="file" name="img2" id="img2" /></td>
	
	   			</tr>
	   			<tr>
	   				<td width="50px"><input type="file" name="img3" id="img3" /></td>
	   			</tr>
	   			<tr>
	   				<td colspan="2">
	   					<textarea name="content" id="content" rows="30" cols="55" placeholder="내용을 입력하세요" required="required"></textarea>
	   				</td>
	   			</tr>
	   			<tr>
	   				<td colspan="2"><div id="img"> </div></td>
	   			</tr>
	   			<tr> 
	   				<td colspan="2" class="bt"> 
	   					<input type="submit" name="submit" id="submit" value="작성완료">
		   				<input type="button" name="gallList" id="gallList" value="글목록" onClick="location.href='<%=request.getContextPath() %>/board/gallList.do'" />
	
	   				</td>
	   			</tr>
	   		</table>
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