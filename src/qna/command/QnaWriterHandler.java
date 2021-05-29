package qna.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.mem.User;
import mvc.command.CommandHandler;
import qna.model.WriterDTO;
import qna.service.QnaWriterRequest;
import qna.service.QnaWriterService;

public class QnaWriterHandler implements CommandHandler {

	private static final String FORM_VIEW =  "../view/jsp/qnaboard/newQnaForm.jsp";

	QnaWriterService qnaService = new QnaWriterService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//메소드 방식 구분 요청 처리
		if(request.getMethod().equalsIgnoreCase("GET")) {
			return processForm(request, response);
		}else if(request.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(request, response);
		}else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		request.setAttribute("errors", errors);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println("QnaWriterHandler의 processSubmit()진입");
		
		System.out.println("title 후의 값 "+title);
		System.out.println("content 후의 값 "+content);
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("AUTHUSER");
		System.out.println("user = "+user);
		
		QnaWriterRequest qnaWriterReq = createWriteRequest(user,request);
		System.out.println("QnaWriterRequest qnaWriterReq =" +qnaWriterReq);
		qnaWriterReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		Integer newQnaNo = qnaService.writer(qnaWriterReq);
		System.out.println("newQnaNo ="+newQnaNo);
		request.setAttribute("newQnaNo", newQnaNo);
		

		return "../view/jsp/qnaboard/newQnaSuccess.jsp";
	}

	private QnaWriterRequest createWriteRequest(User user, HttpServletRequest request) {
		
		System.out.println("user.getId())="+user.getId());
		
		WriterDTO writerDto = new WriterDTO(user.getId());
		System.out.println("writerDto ="+writerDto);
		
		return new QnaWriterRequest(writerDto, request.getParameter("title"), request.getParameter("content"), request.getParameter("res"));
		//return new QnaWriterRequest(new WriterDTO(user.getId()), request.getParameter("title"), request.getParameter("content"));
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response) {
		return FORM_VIEW;
	}

}
