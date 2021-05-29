package admin.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.service.AdminMemberChangeService;
import admin.service.AdminMemberData;
import admin.service.AdminMemberModifyRequest;
import admin.service.AdminMemberService;
import admin.service.MemberNotFoundException;
import member.mem.User;
import mvc.command.CommandHandler;

public class AdminMemberHandler implements CommandHandler {

	private static final String FORM_VIEW =  "../view/jsp/admin/adminMemberUpdate.jsp";
	private AdminMemberChangeService changeService = new AdminMemberChangeService();
	private AdminMemberService adminService = new AdminMemberService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		
		String newgrade = request.getParameter("newgrade");
		try {
			AdminMemberData adminData = adminService.getAdminMember(id, newgrade);
			HttpSession session = request.getSession();
			User authUser = (User)session.getAttribute("AUTHUSER");
			AdminMemberModifyRequest modReq = new AdminMemberModifyRequest(authUser.getId(), id,
																				adminData.getMemberDto().getName(),
																				adminData.getMemberDto().getHp(), 
																				adminData.getMemberDto().getEmail(),
																				adminData.getMemberDto().getAddr(), 
																				adminData.getMemberDto().getGrade());
			request.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}catch(MemberNotFoundException e) {
			request.getServletContext().log("no Member", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

	}
	private String processSubmit(HttpServletRequest request, HttpServletResponse response)throws Exception {
		System.out.println("--------------------AdminMemberHandler�� processSubmit() ���� �Ϸ�--------------------");
		String id = request.getParameter("id");
		System.out.println(id);
		
		String newgrade = request.getParameter("newgrade");
		System.out.println("#####################"+newgrade);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("AUTHUSER");
		changeService.change(id, newgrade);
		return "../view/jsp/admin/adminMemberUpdateSuccess.jsp";
	}

}
