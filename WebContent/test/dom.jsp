<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>      
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title></title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script>
		$(function(){
			var count = 1; // 첨부파일 줄(tr)의 개수를 저장하기위한 변수
			
			//첨부파일 추가버튼 클릭시
			$("#btnAdd").click(function(){ 
				count++;
				
				if(count>3){
					alert("첨부파일의 수는 최대 3개까지 가능해요");
					count=3;
					return;
				}
				//넣고자 하는 html부분의 코드를 문자열형태로 변수에 저장한다
			  var tr ='<tr><td colspan="2"><input type="file" name="file1" id="file'+count+'"/></td></tr>';
				
				//특정위치에 위치
				//==>id가 "id1"인 요소의 앞에 추가한다
				/*The before() method inserts specified content in front of (before) the selected elements.
				 Tip:  To insert content after selected elements, use the after() method.*/
				$("#id1").before(tr);
				
			});
			
			//첨부파일 삭제버튼 클릭시
			$("#btnDelete").click(function(){ 
				count--;
				if(count==0){
					alert("첨부파일 TR은 최소 1개는 있어야해요");
					count=1;
					return;
				}
				
				//추가된 tr줄 제거
				/*The remove() method removes the selected elements, including all text and child nodes.
					This method also removes data and events of the selected elements.
				  ------------
				  The empty() method removes all child nodes and content from the selected elements.
				  Note: This method does not remove the element itself, or its attributes.
				*/
				var removeTr=$("#id1").prev();
				removeTr.remove();
				
			});
		});
	</script>
</head>
<body>
	<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	<hr/>
	
	<h2>DOM을 활용한 동적PAGE</h2>
	
	<form>
		<table border="1" width="400">
			<tr>
				<th>id</th>
				<td><input type="text" name="id" id="id" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td><input type="button" value="추가" id="btnAdd" />
						<input type="button" value="삭제" id="btnDelete" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="file" name="file1" id="file1"/>
				</td>
			</tr>
			<tr id="id1">
				<td colspan="2">
					<input type="button" value="글등록" id="btnArticleAdd"/>
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>