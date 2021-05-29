package qna.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import qna.service.AnswerRequest;
import qna.service.AnswerService;
import qna.service.QnaContentNotFoundException;
import qna.service.QnaData;
import qna.service.QnaNotFoundException;
import qna.service.ReadQnaService;

public class AnswerHandler implements CommandHandler {

	private static final String FORM_VIEW = "../view/jsp/qnaboard/answerSuccess";
	private ReadQnaService readService = new ReadQnaService();
	private AnswerService answerService = new AnswerService();
	
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
		System.out.println("AnswerHandler¿« processSubmit() ¡¯¿‘" );
		int no = Integer.parseInt(request.getParameter("qnaNo"));
		System.out.println(no);
		String res = request.getParameter("res");
		System.out.println(res);
		HttpSession session = request.getSession();
		User authUser = (User)session.getAttribute("AUTHUSER");
		System.out.println(res);
		AnswerRequest ansReq = new AnswerRequest(authUser.getId(),no, res);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors",  errors);
		ansReq.validate(errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
			
		}
		
		answerService.modify(ansReq);
		request.setAttribute("ansReq", ansReq);
		
		
		return "../view/jsp/qnaboard/answerSuccess.jsp";
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) throws QnaContentNotFoundException, IOException {
		String pageNo = request.getParameter("pageNo");
		String strNo = request.getParameter("no");
		int no = Integer.parseInt(strNo);
		try {
			QnaData qnaData = readService.getQnaDTO(no, false);
			
			HttpSession session = request.getSession();
			User authUser = (User)session.getAttribute("AUTHUSER");
			
			AnswerRequest ansReq = new AnswerRequest(
					authUser.getId(), no, qnaData.getQnaDto().getRes()
					);
			request.setAttribute("ansReq", ansReq);
			request.setAttribute("pageNo", pageNo);
			
			return FORM_VIEW;
		}catch(QnaNotFoundException e) {
			request.getServletContext().log("no qna", e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}

}
