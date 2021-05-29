package free.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free.service.DeleteFreeService;
import mvc.command.CommandHandler;

public class DeleteFreeHandler implements CommandHandler{

	private DeleteFreeService deleteService
	 = new DeleteFreeService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteArticleHandler의 process()진입");
		int no = Integer.parseInt(request.getParameter("no")); 
		System.out.println("삭제할 글번호no="+no);
		
		int result = deleteService.delete(no);
		
		request.setAttribute("result", result);
		
		return "../view/jsp/free/deleteSuccess.jsp";
	}





}
