package member.memCommand;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import member.memService.LoginService;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "../view/jsp/member/loginForm.jsp";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	

	private String processSubmit(HttpServletRequest req, HttpServletResponse res)  {
	
		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if(id == null || id.isEmpty())
			errors.put("id", Boolean.TRUE);
		if(password == null || password.isEmpty())
			errors.put("password", Boolean.TRUE);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		User user = loginService.login(id,password,errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		HttpSession session = req.getSession();
		session.setAttribute("AUTHUSER", user);
		return "../index.jsp";
	}
	
	private String trim(String str) {
		return str==null? null:str.trim();
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}
	


}
