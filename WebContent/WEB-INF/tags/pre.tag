<%-- %@ tag지시어(directive)는
				JSP페이지의  page지시어(directive)와 동일
				태그 파일의 정보를 명시 
		 body-content속성	은 몸체의 종류를 입력	
		 pageEncoding속성은 page자체의 character Encoding을 지정
		 trimDirectiveWhitespaces속성은
		 	출력결과에서  공백을 제거할지 말지의 여부를 지정
  --%>
<%@ tag body-content="empty" pageEncoding="utf-8" %>
<%@ tag trimDirectiveWhitespaces="true" %>

<%-- attribute지시어(directive)는
			속성을 이용. 태그 파일을 실행하는데 필요한 값들을 전달받을 수 있도록.
			
		 name속성은	attribute name(속성명)의미
		                   태그파일 내에서  EL변수의 이름으로 사용한다.
		  				각각의   ttribute지시어(directive)의 name속성값을 달라야 한다            
		 type속성은	속성값의 type을 의미.
		 					주의) int사용불가 => java.lang.Integer사용해야 한다
		 required속성은 필수여부를 지정
		 					필수이면 true를 지정. 기본값 false
   --%>
<%@ attribute name="value" 
              type="java.lang.String" required="
              true"%>
           
<%                         
	value = value.replace("\r\n","\r\n<br/>");
	value = value.replace("&","&amp;");
	value = value.replace(" ","&nbsp;");
%>

<%= value %>


