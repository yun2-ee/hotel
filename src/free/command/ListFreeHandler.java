package free.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import free.service.FreePage;
import free.service.ListFreeService;
import mvc.command.CommandHandler;

public class ListFreeHandler implements CommandHandler{
	
	private ListFreeService listService = new ListFreeService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if(pageNoVal!=null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		FreePage freePage = listService.getFreePage(pageNo);
		req.setAttribute("freePage", freePage);
		
		return "../view/jsp/free/listFree.jsp";
	}

	
	}


