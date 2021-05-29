package free.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import free.service.WriteFreeService;
import free.service.WriteRequest;
import member.mem.User;
import member.memService.request;
import mvc.command.CommandHandler;



//글쓰기폼 보여주기 요청(GET방식) + 글쓰기처리 요청(POST방식)
public class WriteFreeHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "../view/jsp/free/newFreeForm.jsp";
	private WriteFreeService writeService = new WriteFreeService(); 
	
	
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
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		System.out.println("processSubmit 진입");

		String title = request.getParameter("title");	//제목
		String content = request.getParameter("content");//내용
		
		HttpSession session = req.getSession();
		
		User user = (User)session.getAttribute("AUTHUSER");		
		WriteRequest writeReq = createWriteRequest(user, req);
		System.out.println(writeReq.getId()+"제목"+writeReq.getTitle()+"내용"+writeReq.getContent());
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newFreeNo = writeService.write(writeReq);
		req.setAttribute("newFreeNo", newFreeNo);
		
		System.out.println("newFreeNo="+newFreeNo);
		
		return "../view/jsp/free/newFreeSuccess.jsp";
	}
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(
				new String(user.getId()),
				req.getParameter("title"),
				req.getParameter("content"));
	}

}