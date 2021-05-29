package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//CommandHandler 인터페이스를 구현하는 클래스
//CommandHandler 인터페이스에서 선언한
//process method를 반드시 override 하고있다
public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) 
	throws Exception {
		//HttpServletResponse.SC_NOT_FOUND는
		//Status code (404) indicating that the requested resource is notavailable.
		//
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
