package board.gallCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.gallService.DeleteService;
import mvc.command.CommandHandler;

public class GallDeleteHandler implements CommandHandler {
	private static final String SUCCESS="../view/jsp/gall/gallDeleteSuccess.jsp";
	private DeleteService deleteService = new DeleteService();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));
		String realPath = request.getServletContext().getRealPath("/images");
		System.out.println(realPath);
		int result = deleteService.delete(no,realPath);
		
		request.setAttribute("result", result);
		return SUCCESS;
		}
}


