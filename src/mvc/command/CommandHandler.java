package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//p531
//이 interface는 Controller역할을 하는 클래스들의 Super class로
//하위의 Controller클래스들의 요청함수명을 통일시켜주는 역할
public interface CommandHandler {
	
	//추상함수 = abstract method
	//method의 구현부 (body,{})가 없는 method를 뜻한다.
	//실제적인 구현내용은
	//interface를 상속받은 class에서 overrride를 통해 작성하게 된다.
	public String process(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
}
