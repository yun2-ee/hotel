package free.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import free.service.FreeContentNotFoundException;
import free.service.FreeData;
import free.service.FreeNotFoundException;
import free.service.ModifyFreeService;
import free.service.ModifyRequest;
import free.service.ReadFreeService;
import member.mem.User;
import mvc.command.CommandHandler;

public class ModifyFreeHandler implements CommandHandler{
	
	private static final String FORM_VIEW
	= "../view/jsp/free/modifyForm.jsp";

	private ReadFreeService readService
		= new ReadFreeService();

	private ModifyFreeService modifyService  
		= new ModifyFreeService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("ModifyArticleHandler의 process()진입/method방식="+request.getMethod());
		
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
		int no = Integer.parseInt(request.getParameter("no"));//글번호
		int pageNo=Integer.parseInt(request.getParameter("pageNo")); //페이지번호.릴레이용
		String title = request.getParameter("title");	 //제목
		String content = request.getParameter("content"); //내용
		
		System.out.println("processSubmit() pageNo="+pageNo);
		
		HttpSession session=request.getSession();
		User authUser = (User)session.getAttribute("AUTHUSER");
		
		ModifyRequest modReq = 
			new ModifyRequest(authUser.getId(),no,title,content);
		
		//P670 77라인
		Map<String,Boolean> errors = new HashMap<String,Boolean>();
		request.setAttribute("errors", errors);
		modReq.validate(errors);
		if(!errors.isEmpty()) { 
			return FORM_VIEW; 
		}
		
		modifyService.modify(modReq);
		
		//3.Model
		request.setAttribute("modReq", modReq); 
		request.setAttribute("pageNo", pageNo);
	
		return "../view/jsp/free/ModifySuccess.jsp";
	}

	private String processForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		System.out.println("ModifyArticleHandler의 processForm()진입");
		
		String pageNo = request.getParameter("pageNo");
		String strNo  = request.getParameter("no");
		int no = Integer.parseInt(strNo);

		try {
			FreeData freeData = readService.getFree(no,false);
			
			HttpSession session=request.getSession();
			User authUser = (User)session.getAttribute("AUTHUSER");

			boolean reuslt = canModify(authUser,freeData);
			if(!reuslt) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			
			ModifyRequest modReq = new ModifyRequest(
					authUser.getId(),
					no,
					freeData.getFree().getTitle(),
					freeData.getFree().getContent()
				);
			
	     	request.setAttribute("modReq",modReq);
	     	System.out.println("수정하기전 pageNo"+pageNo);
	     	request.setAttribute("pageNo",pageNo); 
			
			return FORM_VIEW;
			
		}catch(FreeNotFoundException |
			   FreeContentNotFoundException e) {
			request.getServletContext().log("no free",e);
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
	}//processForm
	
	private boolean  canModify(User authUser,FreeData freeData) {
		String loginUserId = authUser.getId();  
		String freeId 
		= freeData.getFree().getId();
		return  loginUserId.equals(freeId);
	}

	

}
