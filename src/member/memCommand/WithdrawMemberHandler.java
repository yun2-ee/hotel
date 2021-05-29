package member.memCommand;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import member.memService.ChangePwdService;
import member.memService.InvalidPwdException;
import member.memService.MemberNotFoundException;
import member.memService.WirhdrawMemberService;
import mvc.command.CommandHandler;

public class WithdrawMemberHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "../view/jsp/member/withdraw.jsp";
	WirhdrawMemberService wirthdawService = new WirhdrawMemberService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("회원 탈퇴 진입");
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request,response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) { 
			return processSubmit(request,response);
		}else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("processForm() 진입 -------------");
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("processSubmit() 진입");
		System.out.println("processForm() 진입");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		String id = user.getId();
		try {
			wirthdawService.Wirthdraw(id);
			return "../view/jsp/member/withdrawSuccess.jsp";
		}catch(MemberNotFoundException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}


}
