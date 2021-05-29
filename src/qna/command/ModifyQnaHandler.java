package qna.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import qna.service.ModifyQnaRequest;
import qna.service.ModifyQnaService;
import qna.service.QnaContentNotFoundException;
import qna.service.QnaData;
import qna.service.QnaNotFoundException;
import qna.service.ReadQnaService;

public class ModifyQnaHandler implements CommandHandler {
	
	private static final String FORM_VIEW = "../view/jsp/qnaboard/answerModifyForm.jsp";
	private ReadQnaService readService = new ReadQnaService();
	private ModifyQnaService modifyService = new ModifyQnaService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) throws QnaNotFoundException {
		System.out.println("---------------------------ModifyQnaHandler의 processSubmit() 진입 완료---------------------------");
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("int no = " + no);
		String title = request.getParameter("title");
		System.out.println("String title = " + title);
		String content = request.getParameter("content");
		System.out.println("String content = " + content);
		HttpSession session = request.getSession();
		User authUser = (User)session.getAttribute("AUTHUSER");
		System.out.println("processSubmit = "+no);
		ModifyQnaRequest modReq = new ModifyQnaRequest(authUser.getId(),no,title,content);
		
		Map<String,Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",  errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		modifyService.modify(modReq);
		
		request.setAttribute("modReq", modReq);
		
		return "../view/jsp/qnaboard/modifyQnaSuccess.jsp";
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws IOException, QnaContentNotFoundException {
		
		String pageNo = request.getParameter("pageNo");
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		
		try {
			QnaData qnaData = readService.getQnaDTO(no, false);
			
			HttpSession session = request.getSession();
			User authUser = (User)session.getAttribute("AUTHUSER");
		

			ModifyQnaRequest modReq = new ModifyQnaRequest(authUser.getId(), no, qnaData.getQnaDto().getTitle(), qnaData.getQnaDto().getContent());
			
			request.setAttribute("modReq", modReq);
			request.setAttribute("pageNo", pageNo);
			
			return FORM_VIEW;
			
		}catch(QnaNotFoundException e) {
			request.getServletContext().log("no qna", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
}
